layui.use(['table','form','tree'], function(){
  var table = layui.table,
  form = layui.form,
  $=layui.jquery,
  tree=layui.tree,
  strFullPath = window.document.location.href,
  	strPath = window.document.location.pathname,
	pos = strFullPath.indexOf(strPath),
	prePath = strFullPath.substring(0, pos),
	path = strPath.substring(0, strPath.substr(1).indexOf('/') + 1)+"/";
  
  $.ajax({
      type : "get",
      url : path + "attendance/checkTimeCode",
      success : function(data) {
          var obj = eval(data);
          var objLength = obj.length;
          $('#stauts').empty();
          var b = "";
          b += '<option value=""></option>';
          b += '<option value=0>没迟到</option>';
          b += '<option value=1>迟到</option>';
          b += '<option value=2>旷课</option>';
          b += '<option value=3>请假</option>';
          b += '<option value=4>早退</option>';
          $("#stauts").append(b);
          if(objLength>0){
              $('#checkTimeCode').empty();
              var a="";
              a += '<option value=""></option>';
              $(obj).each(function (i) {
                  a+='<option value="'+obj[i].code+'">' + obj[i].timeName + '</option>';
              });
              $("#checkTimeCode").append(a);
              form.render('select');
          }else{
              alert("没有东西");
              $('#checkTimeCode').find('option').remove();
              //alert("应该清空")
              form.render('select');

          }
      },
      error : function() {
          setTimeout(function() {
              top.layer.close(index);
              top.layer.msg("异常！", {
                  icon : 2
              });
              layer.closeAll("iframe");
          }, 1000);
      }
  });
		table.render({
		  elem: '#demo',
		  loading:true,
		  url: path+"attendance/getAttendance", //请求路径
		  limit:7,
		  limits:[4,7,10,15],
		  page:true,
		  where: {
			   name:null,
		  },cols: [[//需显示的字段
				{type:'checkbox', fixed: 'left'},
				{type:'numbers',title:'编号',width:50},
				{field: 'classCode', title: '届次名称', unresize: true, templet: '<div>{{d.classCode}}-{{d.className}}</div>'},
				{field: 'studentName', title: '学生姓名', unresize: true},
				{field: 'checkTimeCode', title: '考勤时间',  unresize: true, templet: '<div>{{ layui.laytpl.toDateString(d.checkTime.time) }}-{{d.checkTimeDesc}}</div>'},
				{field: 'startTime', title: '上课时间',  unresize: true},
				{field: 'retyTime', title: '到达时间', unresize: true},
				{field: 'status', title:'状态', width:90,align:'center', templet: function(d){
					var state;
					if(0==d.status){
						state='<span style="font-size:5px;color:#00FF00;">没迟到</span>'
					}else if(1==d.status){
						state='<span style="font-size:5px;color:#FF2400;">迟到</span>'
					}else if(2==d.status){
						state='<span style="font-size:5px;color:#FF0000;">旷课</span>'
					}else if(3==d.status){
						state='<span style="font-size:5px;color:#0000FF;">请假</span>'
					}else if(4==d.status){
						state='<span style="font-size:5px;color:#BC1717;">早退</span>'
					}
					return state;
				}, unresize: true},
				{field: 'isNote', title: '请假条', width:80, templet: function(d){
					var isNotes;
					if(0==d.isNote){
						isNotes='<span style="font-size:5px;color:#00FF00;">没有</span>'
					}else {
						isNotes='<span style="font-size:5px;color:#FF0000;">有</span>'
					}
					return isNotes;
				},  unresize: true},
				{fixed: 'right',title:'操作', width:80, align:'center', toolbar: '#barDemo',unresize:true}
		 ]]
		});
		
		//搜索
		$(".search_btn").on("click",function(){
			if($(".searchVal").val() != ''){
				table.reload('demo',{
					where: {
					   name:$(".searchVal").val(),
					   checkTimeCode:$("select[name=checkTimeCode]").val(),
					   status:$("select[name=stauts]").val()
					 },page:{
						 curr:1
					 }
				});
			}else{
                layui.layer.tips('请输入内容', '.searchVal', {
                    tips: 3
                });
			}
		});
		
		 //监听工具条
		table.on('tool(demo)', function(obj){
		  var data = obj.data;
		  if(obj.event === 'del'){
		    layer.confirm('确定要删除么', function(index){
		    	layer.close(index);
		    	var index = top.layer.msg('正在删除...请稍候',{icon: 16,time:false,shade:0.8});
		    	$.ajax({
	    	       type:"post",
	    	       url:path+"userDelete",
	    	       data: {"id":obj.data.id,"_method":"DELETE"},
	    	       success:function(data){
	    	    	   if(200==data.code){
	    	    		   setTimeout(function(){
		   			            top.layer.close(index);
		   			        	top.layer.msg(data.msg,{icon:1});
		   			        	table.reload('demo',{
		   			        		where: {
		   			        			name:null
		   			        		}
		   			        	});
		   			        },1000);
	    	    	   }else {
	    	    		   setTimeout(function(){
		   			            top.layer.close(index);
		   			        	top.layer.msg(data.msg,{icon:2});
		   			        	table.reload('demo',{
		   			        		where: {
		   			        			name:null
		   			        		}
		   			        	});
		   			        },1000);
	    	    	   }
	    	       },error : function() {
						setTimeout(function(){
						    top.layer.close(index);
						    top.layer.msg("异常",{icon:2});
							location.reload();
						},1000);
	    	       }
		     	});
		      layer.close(index);
		    });
		  }else if(obj.event === 'edit'){
			  var index = layui.layer.open({
					title : "修改考勤",
					type : 2,
					anim : 5,
					content : "classUpdate",//修改学生的页面路径
					success : function(layero, index) {
						setTimeout(function() {
							layui.layer.tips('点击此处返回',
									'.layui-layer-setwin .layui-layer-close', {
										tips : 3
									});
						}, 500);
					}
				});
				layui.layer.full(index);
				// 改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
				$(window).on("resize", function() {
					layui.layer.full(index);
				});
		  }else if(obj.event === 'view'){
			  var index = layui.layer.open({
					title : "查看考勤",
					type : 2,
					anim : 5,
					content : "../attendance/get?id="+obj.data.id,//修改学生的页面路径
					success : function(layero, index) {
						setTimeout(function() {
							layui.layer.tips('点击此处返回',
									'.layui-layer-setwin .layui-layer-close', {
										tips : 3
									});
						}, 500);
					}
				});
				layui.layer.full(index);
				// 改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
				$(window).on("resize", function() {
					layui.layer.full(index);
				});
		  }
		});
		
		 //添加考勤
	    $(".addUser_btn").click(function(){
	    	var index = layui.layer.open({
				title : "添加考勤",
				type : 2,
				anim : 5,
				content : "attendanceAdd",
				success : function(layero, index) {
					setTimeout(function() {
						layui.layer.tips('点击此处返回',
								'.layui-layer-setwin .layui-layer-close', {
									tips : 3
								});
					}, 500);
				}
			});
			layui.layer.full(index);
			// 改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
			$(window).on("resize", function() {
				layui.layer.full(index);
			});
	    });
});