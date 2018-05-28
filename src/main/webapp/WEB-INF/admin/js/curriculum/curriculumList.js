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
		  url: path+"clas", //请求路径
		  limit:7,
		  limits:[4,7,10,15],
		  page:true,
		  where: {
			   year:null,
		  },cols: [[//需显示的字段
				{type:'checkbox', fixed: 'left'},
				{type:'numbers',title:'编号',width:50,field: 'code'},
				{field: 'year', title: '届次', unresize: true},
				{field: 'name', title: '批次名(班级)', unresize: true},
				{field: 'startTime', title: '开班时间', unresize: true},
				{field: 'teacherName', title: '教师姓名',  unresize: true},
				{field: 'isFinish', title:'是否毕业', width:90,align:'center', templet: function(d){
					var gdt;
					if(1==d.isFinish){
						gdt='<span style="font-size:5px;color:#009688;">详情</span>'
					}else{
						gdt='<span style="font-size:5px;color:#FFB800;">毕业</span>'
					}
					return gdt;
				}, unresize: true},
				{fixed: 'right',title:'操作', width:80, align:'center', toolbar: '#barDemo',unresize:true}
		 ]]
		});
		
		//搜索
		$(".search_btn").on("click",function(){
			if($(".searchVal").val() != ''){
				table.reload('demo',{
					where: {
					   year:$(".searchVal").val()
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
	    	       url:path+"clas",
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
					title : "修改届次",
					type : 2,
					anim : 5,
					content: "../getClasOnly?id="+obj.data.id,//修改学生的页面路径
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
		  }else if(obj.event === 'graduation'){
			  layer.confirm('确定此批毕业么', function(index){
			    	layer.close(index);
			    	var index = top.layer.msg('正在申请毕业...请稍候',{icon: 16,time:false,shade:0.8});
			    	$.ajax({
		    	       type:"get",
		    	       url:path+"graduation",
		    	       data: {"code":obj.data.code},
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
		  }else if(obj.event === 'openClass'){
			  layer.confirm('确定要开课么', function(index){
			    	layer.close(index);
			    	var index = top.layer.msg('正在申请开课...请稍候',{icon: 16,time:false,shade:0.8});
			    	$.ajax({
		    	       type:"get",
		    	       url:path+"openClass",
		    	       data: {"code":obj.data.code},
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
		  }else if(obj.event === 'sop'){
			  var index = layui.layer.open({
					title : "进度",
					type : 2,
					anim : 5,
					content: " ----- ",
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
		  }else if(obj.event === 'details'){
			  var index = layui.layer.open({
					title : "详情",
					type : 2,
					anim : 5,
					content: "curriculumDetails",
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
		  }else if(obj.event === 'addStudent'){
			  var index = layui.layer.open({
					title : "添加学生",
					type : 2,
					anim : 5,
					content: "../clasGet?code="+obj.data.code,
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
		function addUser(edit){
			var index = layui.layer.open({
				title : "添加届次",
				type : 2,
				anim : 5,
				content : "curriculumAdd",
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
	    $(".addUser_btn").click(function(){
	        addUser();
	    });
});