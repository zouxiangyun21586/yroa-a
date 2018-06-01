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
	    elem: '#date',
	    type: 'datetime',
	    range: true
	  });
	
//	$.getUrlParam = function (name) {  
//		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象  
//		var r = window.location.search.substr(1).match(reg);  //匹配目标参数  
//		if (r != null) return decodeURI(r[2]); return null; //返回参数值  
//	}
//	$.getUrlParam('code');
	
	$.ajax({
        type : "get",
        url : path + "stckDic",
        success : function(result) {
            var obj = eval(result);
            var objLength = obj.length;
            if(objLength>0){
                $('#status').empty();
                var a="";
                $(obj).each(function (i) {
                    a+='<option value="' + obj[i].keyv + '" selected>' + obj[i].val + '</option>';
                });
                $("#status").append(a);
                form.render('select');
            }else{
                alert("没有东西");
                $('#status').find('option').remove();
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
	
	form.on('radio(isNote)', function(data){
		var i ="";
		if(data.value=="0"){
			$('#sta').empty();
		}else if(data.value=="1"){
			 i+="<div class='layui-form-item layui-row layui-col-xs12'>" +
					 "<div class='magb15 layui-col-md4 layui-col-xs12'>" +
						"<label class='layui-form-label'>工资 </label>" +
						"<div class='layui-input-block'>" +
							"<button class='layui-btn layui-btn-sm' id='imgUrl' name='imgUrl'>上传请假纸制证明</button>"+
						"</div>" +
					"</div>" +
				"</div>" 
		}
		$("#sta").html(i);
		laydate.render({
			elem: '#finishTime'
		});
		laydate.render({
			elem: '#offerTime'
		});
	}); 
	
	form.on("submit(attAdd)", function(data) {
		var index = top.layer.msg('数据提交中，请稍候', {
			icon : 16,
			time : false,
			shade : 0.8
		});
		
			$.ajax({
				type : "post",
				url : path+"attendance/addAttendance?code="+obj.data.studentCode,
				data : $('#attForm').serialize(),
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
});