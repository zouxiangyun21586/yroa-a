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
		table.render({
		  elem: '#demo',
		  loading:true,
		  url: path+"student/student", //请求路径
		  limit:7,
		  limits:[4,7,10,15],
		  page:true,
		  where: {
			   name:null,
		  },cols: [[//需显示的字段
				{type:'checkbox', fixed: 'left'},
				{field: 'code', title: '学生编号', unresize: true},
				{field: 'name', title: '姓名', unresize: true},
				{field: 'sex', title:'性别', width:90,align:'center', templet: function(d){
					var state;
					if('0'==d.sex){
						state='女';
					}else if('1'==d.sex){
						state='男';
					}else{
						state='未知';
					}
					return state;
				}, unresize: true},
				{field: 'age', title: '年龄', unresize: true},
				{field: 'addr', title: '家庭地址', unresize: true},
				{field: 'birth', title: '出生年月', unresize: true},
				{field: 'year', title: '届次', unresize: true},
				{field: 'tel', title: '学生电话', unresize: true},
				{field: 'homeTel', title: '家长电话', unresize: true},
				{field: 'inTime', title: '入学时间', unresize: true},
				{field: 'isFinish', title:'是否毕业', width:90,align:'center', templet: function(d){
					var state;
					if('1'==d.isFinish){
						state='<span style="font-size:5px;color:#009688;">已毕业</span>';
						
					}else if('0'==d.isFinish){
						state='<span style="font-size:5px;color:#FFB800;">未毕业</span>';
					}
					return state;
				}, unresize: true},
				{fixed: 'right',title:'操作', width:80, align:'center', toolbar: '#barDemo',unresize:true}
		 ]]
		});
		//搜索
		$(".search_btn").on("click",function(){
				table.reload('demo',{
					where: {
					   name:$(".searchVal").val(),
					   modules:$("select[name=modules]").val()
					 },page:{
						 curr:1
					 }
				});
		});
		
		
		//查询已毕业或未毕业学生
		
		
		
		 //监听工具条
		table.on('tool(demo)', function(obj){
		  var data = obj.data;
		  if(obj.event === 'del'){
		    layer.confirm('确定要删除么', function(index){
		    	layer.close(index);
		    	var index = top.layer.msg('正在删除...请稍候',{icon: 16,time:false,shade:0.8});
		    	$.ajax({
	    	       type:"post",
	    	       url:path+"student/student",
	    	       data: {"id":obj.data.id,"_method":"DELETE"},
	    	       success:function(data){
	    	    	   if(0==data.code){
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
		  }else if(obj.event === 'yjy'){
			  var index = layui.layer.open({
					title : "就业信息",
					type : 2,
					anim : 5,
					content : "employmentInformation?id="+obj.data.id,
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
			  
		  }else if(obj.event === "editWjy"){
			  var index = layui.layer.open({
					title : "编辑",//修改未就业学生
					type : 2,
					anim : 5,
					content : "../student/updateWjyDisplay?id="+obj.data.id,//修改学生的页面路径
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
		  } else if (obj.event === "editYjy"){
			  var index = layui.layer.open({
					title : "编辑",//修改已就业学生
					type : 2,
					anim : 5,
					content : "../student/updateYjyDisplay?id="+obj.data.id,//修改学生的页面路径
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
		
		 //添加用户
		function addStudent(edit){
			var index = layui.layer.open({
				title : "添加学生",
				type : 2,
				anim : 5,
				content : "studentAdd",
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
	    $(".addStudent_btn").click(function(){
	    	addStudent();
	    });
});