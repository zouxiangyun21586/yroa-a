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
	
});