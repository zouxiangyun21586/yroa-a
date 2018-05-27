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
	    elem: '#startTime',
	    type: 'time'
	});
	laydate.render({
	    elem: '#endTime',
	    type: 'time'
	});
	laydate.render({
	    elem: '#date',
	    type: 'datetime',
	    range: true
	  });
	$.ajax({
		type : "get",
		url : path + "holiday/clasList",
		success : function(data) {
			var obj = eval(data);
            var objLength = obj.length;
            $('#statusSelect').empty();
            var b = "";
            b += '<option value=0>不发布</option>';
            b += '<option value=1>发布</option>';
            $("#statusSelect").append(b);
            if(objLength>0){
                $('#claSelect').empty();
                var a = "";
                $(obj).each(function (i) {
                    a += '<option value="' + obj[i].code + '">' + obj[i].code+"-"+obj[i].name + '</option>';
                });
                $("#claSelect").append(a);
                form.render('select');
            }else{
                alert("没有东西");
                $('#claSelect').find('option').remove();
                //alert("应该清空")
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
	form.on("submit(addHoliday)", function(data) {
		var index = top.layer.msg('数据提交中，请稍候', {
			icon : 16,
			time : false,
			shade : 0.8
		});
		
			$.ajax({
				type : "post",
				url : path+"holiday/add",
				data : $('#HolidayForm').serialize(),
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