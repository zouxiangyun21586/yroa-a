//获取url里面的参数 ①
function urls() { //拆分取url中?后面的参数各和值,组成对象返回
	var url = location.href;// 获得全路径名
	var pars = url.split("?"); //url.substring((url.indexOf("?") + 1), url.length);
	if (pars.length == 2) {
		var parArr = pars[1].split("&");
		var parObj = new Object();
		for ( var i in parArr) {
			var s = parArr[i].split("=");
			parObj[s[0]] = s[1];
		}
		return parObj
	}else {
		return null;
	}
}
//获取url里面的参数 ②
function getPar(name) {//根据参数名得到值
	var obj = urls();
	if (null == obj) {
		return '';
		}else {
			var v = obj[name];
			return v;
	}
}
layui.use([ 'layer', 'form' ], function() {
	var layer = layui.layer, 
	form = layui.form, 
	$ = layui.jquery,
	strFullPath = window.document.location.href,
  	strPath = window.document.location.pathname,
	pos = strFullPath.indexOf(strPath),
	prePath = strFullPath.substring(0, pos),
	path = strPath.substring(0, strPath.substr(1).indexOf('/') + 1)+"/";
	var val = getPar("code");
	$.ajax({
		type : "get",
		url : path+"auth/upd_echo",
		data : {"code":val},
		success : function(data) {
			if (1 == data.code) {
				setTimeout(function() {
					top.layer.close(index);
					top.layer.msg(data.msg, {
						icon : 2
					});
				}, 1000);
			} else {
				$("#code").val(val);
				$("#name").val(data.name);
				$("#url").val(data.url);
				$("#caozuo").val(data.caozuo);
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
	form.on("submit(addUser)", function(data) {
		var index = top.layer.msg('数据提交中，请稍候', {
			icon : 16,
			time : false,
			shade : 0.8
		});
		$.ajax({
			type : "post",
			url : path+"auth/upda",
			data : $('#authUpd').serialize(),
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