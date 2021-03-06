layui.use([ 'layer', 'form' ,'laydate'], function() {
	var layer = layui.layer, 
	form = layui.form, 
	$ = layui.jquery,
	laydate=layui.laydate,
	strFullPath = window.document.location.href,
  	strPath = window.document.location.pathname,
	pos = strFullPath.indexOf(strPath),
	prePath = strFullPath.substring(0, pos),
	path = strPath.substring(0, strPath.substr(1).indexOf('/') + 1)+"/";
	laydate.render({
	    elem: '#birth'
	});
	laydate.render({
		elem: '#inTime'
	});
	laydate.render({
		elem: '#finishTime'
	});
	laydate.render({
		elem: '#offerTime'
	});
	
	 $.ajax({
	        type : "get",
	        url : path + "student/queryYear",
	        success : function(result) {
	            var obj = eval(result);
	            var objLength = obj.length;
	            var code = $("#code").val();
	            if(objLength>0){
	                $('#claSelect').empty();
	                var a="";
	                $(obj).each(function (i) {
	                    if (obj[i].code == code) {
	                        a+='<option value="' + obj[i].code + '" selected>' + obj[i].year+"-"+obj[i].name + '</option>';
	                    } else {
	                        a+='<option value="' + obj[i].code + '">' + obj[i].year+"-"+obj[i].name + '</option>';
	                    }
	                });
	                $("#claSelect").html(a);
	                form.render('select');
	            }else{
	                alert("没有东西");
	                $('#claSelect').find('option').remove();
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
	 
	 form.on("submit(update)", function(data) {
			var index = top.layer.msg('数据提交中，请稍候', {
				icon : 16,
				time : false,
				shade : 0.8
			});
				$.ajax({
					type : "post",
					url : path+"student/student",
					data :$('#studentForm').serialize(),
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