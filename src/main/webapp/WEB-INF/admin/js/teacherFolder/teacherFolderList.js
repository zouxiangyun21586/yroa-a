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
	url: path+"teacher", //请求路径
	limit:7,
	limits:[4,7,10,15],
	page:true,
	where: {
		name:null,
	},cols: [[//需显示的字段
			{type:'checkbox', fixed: 'left'},
			{type:'numbers',field: 'code',title:'编号',width:50},
			{field: 'name', title: '姓名', unresize: true},
			{field: 'sex', title: '性别', unresize: true},
			{field: 'age', title: '年龄',  unresize: true},
			{field: 'tel', title: '电话',  unresize: true},
			{field: 'level', title: '教学等级',  unresize: true},
			{field: 'in_time', title: '入职时间', unresize: true},
			{field: 'is_level', title:'是否离职', width:90,align:'center', templet: function(d){
			var state;
			if(1==d.is_level){
				state='<span style="font-size:5px;color:#009688;">已离职</span>'
			}else{
				state='<span style="font-size:5px;color:#FFB800;">未离职</span>'
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
	  if(obj.event === 'del'){
	    layer.confirm('确定要删除么', function(index){
	    	layer.close(index);
	    	var index = top.layer.msg('正在删除...请稍候',{icon: 16,time:false,shade:0.8});
	    	$.ajax({
    	       type:"post",
    	       url:path+"teacher",
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
				title : "修改教师",
				type : 2,
				anim : 5,
				content : "teacherFolderUpd",//修改学生的页面路径
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
			content : "teacherFolderAdd",
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