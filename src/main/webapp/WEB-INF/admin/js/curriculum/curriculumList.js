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
		  url: path+"userSelect", //请求路径
		  limit:7,
		  limits:[4,7,10,15],
		  page:true,
		  where: {
			   name:null,
		  },cols: [[//需显示的字段
				{type:'checkbox', fixed: 'left'},
				{type:'numbers',title:'编号',width:50},
				{field: 'name', title: '用户名', unresize: true},
				{field: 'userName', title: '账号', unresize: true},
				{field: 'passWord', title: '密码',  unresize: true},
				{field: 'email', title: '邮箱',  unresize: true},
				{field: 'insertTime', title: '注册时间',templet: function(d) {
                    return d.insertTime.time;
                }, unresize: true},
				{field: 'status', title:'状态', width:90,align:'center', templet: function(d){
					var state;
					if(0==d.status){
						state='<span style="font-size:5px;color:#009688;">可使用</span>'
					}else if(1==d.status){
						state='<span style="font-size:5px;color:#FFB800;">未激活</span>'
					}else if(2==d.status){
						state='<span style="font-size:5px;color:#ff0000;">已禁用</span>'
					}
					return state;
				}, unresize: true},
				{fixed: 'right',title:'操作', width:80, align:'center', toolbar: '#barDemo',unresize:true}
		 ]]
		});
		
		//搜索
		$(".search_btn").on("click",function(){
			if($(".searchVal").val() != ''){
				table.reload('demo',{
					where: {
					   name:$(".searchVal").val()
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
		  if(obj.event === 'look'){
				$.ajax({
					type : "get",
					url : path+"lookRole",
					data : {"id" : obj.data.id},
					success : function(data) {
						var role="";
						for (var i=0;i<data.length;i++){
							if(0==i%3&&0!=i){
								role+="<br/>"
							}
							role+='<span class="layui-badge layui-bg-green" style="height:23px;line-height:23px;margin:5px 5px 0px 0px;">'+data[i]+'</span>';
						}
						layer.open({
						  title: '查看权限',
						  content: role
						});     
					},error : function() {
						setTimeout(function() {
							top.layer.msg("异常", {
								icon : 2
							});
							location.reload();
						}, 1000);
					}
				});
			}else if(obj.event === 'state'){
				var index= top.layer.msg('正在修改用户状态...请稍候',{icon: 16,time:false,shade:0.8});
				$.ajax({
		    	       type:"post",
		    	       url:path+"userStatus",
		    	       data: {"id":obj.data.id,"value":obj.data.status,"_method":"PUT"},
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
			}else if(obj.event === 'role'){
		    var layerRole=layer.open({
				anim: 2,
				title : '选择角色',
				type: 2, //窗口类型
				resize:false,//禁止拉伸
				maxmin:false,//最大化,最小化
				shade: [0.3,'#000'],
				area: ['300px', '400px'],//窗口宽高
				content: path+'user/role?id='+data.id
			});
		  } else if(obj.event === 'del'){
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
		  }
		});
		
		 //添加用户
		function addUser(edit){
			var index = layui.layer.open({
				title : "添加用户",
				type : 2,
				anim : 5,
				content : "userAdd",
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