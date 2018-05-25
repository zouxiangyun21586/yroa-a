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
		  url: path+"role/queryfy", //请求路径
		  limit:7,
		  limits:[10,15,20],
		  page:true,
		  where: {
			   name:null,
		  },cols: [[//需显示的字段
				{type:'checkbox', fixed: 'left'},
				{type:'numbers',title:'编号',width:50},
				{field: 'name', title: '角色名', unresize: true},
				{field: 'code', title: '编号', unresize: true},
				{field: 'info', title: '描述',  unresize: true},
				{field: 'createTimeStr', title: '注册时间', unresize: true},
				{field: 'updateTimeStr', title: '最后修改时间', unresize: true},
				{field: 'use', title:'状态', width:90,align:'center', templet: function(d){
					var state;
					if('0'==d.use){
						state='<span style="font-size:5px;color:#009688;">使用中</span>'
					}else if('1'==d.use){
						state='<span style="font-size:5px;color:#FFB800;">未使用</span>'
					}/*else if(2==d.status){
						state='<span style="font-size:5px;color:#ff0000;">已禁用</span>'
					}*/
					return state;
				}, unresize: true},
				{fixed: 'right',title:'操作', width:80, align:'center', toolbar: '#barDemo',unresize:true}
		 ]]
		});
		
		//搜索
		$(".search_btn").on("click",function(){
			table.reload('demo',{
				where: {
				   name:$(".searchVal").val()
				 },page:{
					 curr:1
				 }
			});
		});
		
		 //监听工具条
		table.on('tool(demo)', function(obj){
		  var data = obj.data;
		  if(obj.event === 'state'){
				var index= top.layer.msg('正在修改用户状态...请稍候',{icon: 16,time:false,shade:0.8});
				$.ajax({
	    	       type:"post",
	    	       url:path+"role/switchs",
	    	       data: {"name":obj.data.username,"_method":"PUT"},
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
			}else if(obj.event === 'edit'){
			  var index = layui.layer.open({
					title : "修改用户",
					type : 2,
					anim : 5,
					content : "userAdd",//修改用户的页面路径
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
		  }else if(obj.event === 'resetPassword'){
			  $.ajax({
	    	       type:"post",
	    	       url:path+"role/reset",
	    	       data: {"username":obj.data.username,"_method":"PUT"},
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
		  }
		});
		
		 //添加用户
	    $(".addUser_btn").click(function(){
	    	var index = layui.layer.open({
				title : "添加用户",
				type : 2,
				anim : 5,
				content : "roleAdd",
				success : function(layero, index) {
					setTimeout(function() {
						layui.layer.tips('点击此处返回','.layui-layer-setwin .layui-layer-close', {
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