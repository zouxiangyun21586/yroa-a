layui.use([ 'layer', 'form' ], function() {
	var layer = layui.layer, 
	form = layui.form, 
	$ = layui.jquery,
	strFullPath = window.document.location.href,
  	strPath = window.document.location.pathname,
	pos = strFullPath.indexOf(strPath),
	prePath = strFullPath.substring(0, pos),
	path = strPath.substring(0, strPath.substr(1).indexOf('/') + 1)+"/";
	$.ajax({
		type : "get",
		url : path+"role/qrole",
		success : function(data) {
			$("#code").append("<option value=''>请选择角色</option>");
            for(var i in data){
            	$("#code").append("<option value='"+data[i].code+"'>"+data[i].name+"</option>");
            }
            form.render('select');
		},error : function() {
			setTimeout(function() {
				top.layer.close(index);
				top.layer.msg("异常！", {
					icon : 2
				});
				layer.closeAll("iframe");
			}, 1000);
		}
	});
	form.on("submit(addUser)", function(data) {
		var index = top.layer.msg('数据提交中，请稍候', {
			icon : 16,
			time : false,
			shade : 0.8
		});
		var passWord=$(":input[name='passWord']").val();
    	var passWords=$(":input[name='passWords']").val();
    	if(passWord!=passWords){
    		top.layer.close(index);
			layer.msg("2次密码不一致",{icon:2,time:2000,shade:0.5});
    	}else{
			$.ajax({
				type : "post",
				url : path+"acc/add",
				data : $('#accountAdd').serialize(),
				success : function(data) {
					if (0 == data.code) {
						setTimeout(function() {
							top.layer.close(index);
							top.layer.msg(data.msg, {
								icon : 1
							});
							layer.closeAll("iframe");
							parent.location.reload();
						}, 1000);
					} else {
						setTimeout(function() {
							top.layer.close(index);
							top.layer.msg(data.msg, {
								icon : 2
							});
						}, 1000);
					}
				},error : function() {
					setTimeout(function() {
						top.layer.close(index);
						top.layer.msg("异常！", {
							icon : 2
						});
						layer.closeAll("iframe");
					}, 1000);
				}
			});
		}
		return false;
	});
	form.verify({
	    username: function(value, item){ //value：表单的值、item：表单的DOM对象
	    if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){
	      return '用户名不能有特殊字符';
	    }
	    if(/(^\_)|(\__)|(\_+$)/.test(value)){
	      return '用户名首尾不能出现下划线\'_\'';
	    }
	    if(/^\d+\d+\d$/.test(value)){
	      return '用户名不能全为数字';
	    }
	    },password: [
	        /^[\S]{6,12}$/,
	        '密码必须6到12位，且不能出现空格'
	    ] 
	});
});