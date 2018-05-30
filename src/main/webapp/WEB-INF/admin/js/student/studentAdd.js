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
        url : path + "student/queryYear",
        success : function(result) {
            var obj = eval(result);
            var objLength = obj.length;
            if(objLength>0){
                $('#claSelect').empty();
                var a="";
                $(obj).each(function (i) {
                	a+='<option value="' + obj[i].code + '" selected>' + obj[i].year+"-"+obj[i].name + '</option>';
                });
                $("#claSelect").html(a);
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
	
	
	form.on('select(year)', function(data){
		 $(":select[name='year']").val(data.value);
	});   
	
	
	form.on('radio(isItDisplayed)',function(date){
		$("#kuang").show();
	});
	
	form.on('radio(isFinish)', function(data){
		var i ="";
		var a ="";
		if(data.value=="0"){
			$('#aaa').empty();
			$('#bbb').empty();
		}else if(data.value=="1"){
			i+="<div class='magb15 layui-col-md4 layui-col-xs12'>" +
						"<label class='layui-form-label'>毕业日期</label>" +
						"<div class='layui-input-block'>" +
							"<input type='text' class='layui-input' id='finishTime' placeholder='yyyy-MM-dd' lay-verify='required' name='finishTime'>" +
						"</div>" +
					 "</div>" +
					 "<div class='magb15 layui-col-md4 layui-col-xs12'>" +
						 "<label class='layui-form-label'>工作日期</label>" +
						 "<div class='layui-input-block userSex'>" +
						  	 "<input type='text'  class='layui-input' id='offerTime' placeholder='yyyy-MM-dd' lay-verify='required' name='offerTime'>" +
						 "</div>" +
					"</div>"
			 a+="<div class='layui-form-item layui-row layui-col-xs12'>" +
					 "<div class='magb15 layui-col-md4 layui-col-xs12'>" +
						"<label class='layui-form-label'>工资 </label>" +
						"<div class='layui-input-block'>" +
							"<input class='layui-input' placeholder='请输入你的工资' name='offerIncome' lay-verify='required'>"+
						"</div>" +
					"</div>" +
			  "</div>" 
		}
		$("#aaa").html(i);
		$("#bbb").html(a);
		laydate.render({
			elem: '#finishTime'
		});
		laydate.render({
			elem: '#offerTime'
		});
	});  
	
	form.on("submit(add)", function(data) {
		var index = top.layer.msg('数据提交中，请稍候', {
			icon : 16,
			time : false,
			shade : 0.8
		});
			$.ajax({
				type : "post",
				url : path+"student/student",
				data : $('#studentForm').serialize(),
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
	function graduated(test){
		alert("djlsdjfsldk");
	}
});

