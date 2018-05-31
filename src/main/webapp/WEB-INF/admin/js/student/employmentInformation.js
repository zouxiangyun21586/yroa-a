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
	var id = getUrlParam('id');
	 $.ajax({
	        type : "get",
	        url : path + "student/employment?id="+id,
	        success : function(result) {
	            var obj = eval(result);
	            $("#name").val(obj.name);
	            $("#age").val(obj.age);
	            $("#birth").val(obj.birth);
	            if(obj.sex=="1"){
	            	$("#sex").val("男");
	            }else{
	            	$("#sex").val("女");
	            } 
	            $("#year").val(obj.year);
	            $("#tel").val(obj.tel);
	            $("#homeTel").val(obj.homeTel);
	            $("#addr").val(obj.addr);
	            $("#inTime").val(obj.inTime);
	            $("#finishTime").val(obj.finishTime);
	            $("#offerTime").val(obj.offerTime);
	            $("#offerIncome").val(obj.offerIncome);
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
	 function getUrlParam(name) {
	      var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
	      var r = window.location.search.substr(1).match(reg); //匹配目标参数
	      if (r != null) return unescape(r[2]); return null; //返回参数值
	    }
	
});