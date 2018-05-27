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
	
	$.ajax({
        type : "get",
        url : path + "teacherLev",
        success : function(result) {
            var obj = eval(result);
            var objLength = obj.length;
            var level = $("#tealevel").val();// jsp 页面的id
            if(objLength>0){
                $('#levQuyer').empty();
                var a="";
                $(obj).each(function (i) {
                    if (obj[i].keyv == level) { // 如果与查询出来的数据一致就作为默认值
                        a+='<option value="' + obj[i].keyv + '" selected>' + obj[i].keyv + "--" + obj[i].val + '</option>';
                    } else {
                        a+='<option value="' + obj[i].keyv + '">' + obj[i].keyv + "--" + obj[i].val + '</option>';
                    }
                });
                $("#levQuyer").append(a);
                form.render('select');
            }else{
                alert("没有东西");
                $('#levQuyer').find('option').remove();
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
	
	
	$.ajax({
        type : "get",
        url : path + "teacherIs",
        success : function(result) {
            var obj = eval(result);
            var objLength = obj.length;
            var isLeave = $("#teacherisLeave").val();// jsp 页面的id
            if(objLength>0){
                $('#islea').empty();
                var b="";
                $(obj).each(function (i) {
                    if (obj[i].keyv == isLeave) { // 如果与查询出来的数据一致就作为默认值
                        b+='<option value="' + obj[i].keyv + '" selected>' + obj[i].val+ '</option>';
                    } else {
                        b+='<option value="' + obj[i].keyv + '">' + obj[i].val + '</option>';
                    }
                });
                $("#islea").append(b);
                form.render('select');
            }else{
                alert("没有东西");
                $('#islea').find('option').remove();
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
	
	
	form.on('select(year)', function(data){
		 $(":select[name='year']").val(data.value);
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