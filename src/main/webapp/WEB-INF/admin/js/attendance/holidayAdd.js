layui.use([ 'layer', 'form' ,'laydate'], function() {
	var layer = layui.layer, 
	form = layui.form, 
	$ = layui.jquery,
	laydate = layui.laydate,
	strFullPath = window.document.location.href,
  	strPath = window.document.location.pathname,
	pos = strFullPath.indexOf(strPath),
	prePath = strFullPath.substring(0, pos),
	path = strPath.substring(0, strPath.substr(1).indexOf('/') + 1)+"/";
	laydate.render({
	    elem: '#startDate'
	});
	laydate.render({
		elem: '#endDate'
	});
	laydate.render({
	    elem: '#date',
	    type: 'datetime',
	    range: true
	  });
	$.ajax({
		type : "get",
		url : path + "holiday/adds",
		success : function(data) {
			var obj = eval(data);
            var objLength = obj.length;
            if(objLength>0){
                $('#claSelect').empty();
                $(obj).each(function (i) {
                    alert("进入循环");
                    alert(obj[i].code + ":" + obj[i].name);
                    form.render('select');
                    $("#claSelect").append('<option value="' + obj[i].code + '">' + obj[i].name + '</option>');

                });
            }else{
                alert("没有东西");
                $('#claSelect').find('option').remove();
                alert("应该清空")
                form.render('select');

            }
            
//			layer.msg("获得的值为："+data.listClas.);
//			$("#claSelect").append("<option value="+data+">"+ivalue+"</option>");  
//            renderForm();//表单重新渲染，要不然添加完显示不出来新的option
//            layer.close(index);
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
	form.on("submit(addUser)", function(data) {
		var index = top.layer.msg('数据提交中，请稍候', {
			icon : 16,
			time : false,
			shade : 0.8
		});
		
			$.ajax({
				type : "post",
				url : path+"userInsert",
				data : $('#UserForm').serialize(),
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
   	    if(/^[\S]{6,12}$/.test(value)){
   	    	return '用户名必须6到12位，且不能出现空格';
   	    }
   	  }
   	  ,pass: [
   	    /^[\S]{6,12}$/
   	    ,'密码必须6到12位，且不能出现空格'
   	  ] 
   	});
});