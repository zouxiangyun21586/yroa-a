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
	    elem: '#year'
	});
	laydate.render({
		elem: '#entranceYear'
	});
	form.on('select(year)', function(data){
		 $(":select[name='year']").val(data.value);
	});
	
	 $.ajax({
	        type : "get",
	        url : path + "teacherHX?id="+$("#ids").val(),
	        success : function(result) {
	        	alert(result);
	            var obj = eval(result);
	            var objLength = obj.length;
	            var code = $("#teacher").val();
	            if(objLength>0){
	                $('#levQuyer').empty();
	                $('#isLeQuery').empty();
	                var a="";
	                $(obj).each(function (i) {
	                    if (obj[i].level == level) {
	                        a+='<option value="' + obj[i].level + '" selected>' + obj[i].level+"-"+obj[i].name + '</option>';
	                    } else {
	                        a+='<option value="' + obj[i].level + '">' + obj[i].level+"-"+obj[i].name + '</option>';
	                    }
	                    if (obj[i].isLeave == isLeave) {
	                        b+='<option value="' + obj[i].isLeave + '" selected>' + obj[i].isLeave+"-"+obj[i].name + '</option>';
	                    } else {
	                        b+='<option value="' + obj[i].isLeave + '">' + obj[i].isLeave+"-"+obj[i].name + '</option>';
	                    }
	                });
	                $("#levQuyer").append(a);
	                $("#isLeQuery").append(b);
	                form.render('select');
	            }else{
	                alert("没有东西");
	                $('#levQuyer').find('option').remove();
	                $('#isLeQuery').find('option').remove();
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
	
	
    form.on("submit(updTeacher)", function(data) {
        var index = top.layer.msg('数据提交中，请稍候', {
            icon : 16,
            time : false,
            shade : 0.8
        });
        
            $.ajax({
                type : "post",
                url : path+"teacher",
                data : $('#teacherUpdForm').serialize(),
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