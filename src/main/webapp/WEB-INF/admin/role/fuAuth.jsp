<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>添加用户</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/layui/css/layui.css" media="all" />
<style>
	.layui-col-xs12{margin-bottom:15px;}
</style>
</head>
<body>
    <form class="layui-form">
        <div id="resource" style="background-color: #fff; padding: 10px 0 25px 5px;height:270px;overflow:auto;"></div>
    </form>
    <ul class="layui-fixbar">
        <a class="layui-btn layui-btn-small layui-btn-normal" id="btn"><i class="layui-icon">&#xe618;</i> 确定</a>
    </ul>
</body>
<script type="text/javascript" src="<%= this.getServletContext().getContextPath() %>/layui/layui.js"></script>
<script src="<%= this.getServletContext().getContextPath() %>/js/layui-xtree.js"></script>
<script src="<%= this.getServletContext().getContextPath() %>/js/jquery-2.2.4.min.js"></script>
<script type="text/javascript">
$.getUrlParam = function (name) {  
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象  
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数  
    if (r != null) return decodeURI(r[2]); return null; //返回参数值  
}
    layui.use(['form','layer'], function() {
        var form = layui.form,
            layer = layui.layer,
            index = parent.layer.getFrameIndex(window.name),
            role_id = $.getUrlParam('code'),
            strFullPath = window.document.location.href,
            strPath = window.document.location.pathname,
            pos = strFullPath.indexOf(strPath),
            prePath = strFullPath.substring(0, pos),
            path = strPath.substring(0, strPath.substr(1).indexOf('/') + 1)+"/";;
            
            var xtree = new layuiXtree({
                elem : 'resource',
                data : path+'auth/getResource?code='+role_id,
                form : form,
                ckall: true,
                icon: {        
                    end: "&#xe672;" 
                }, color: {      
                    end: "#009688"   
                }
            });
            $('#btn').click(function(){
                var roleid = xtree.GetChecked(); 
                var value = [];
                for (var i = 0; i < roleid.length; i++) {
                    value.push(roleid[i].value);
                }
                var index = top.layer.msg('修改权限中，请稍候',{icon: 16,time:false,shade:0.8});
                if(null==value || ''==value){
                    top.layer.close(index);
                    layer.msg("至少要选择一项权限",{icon:3});
                    return;
                }else{
                    $.ajax({
                       type:"post",
                       url:path+"roleEmpowerment",
                       traditional: true,
                       data: {'resourceId':value,'roleId':role_id,'_method':'PUT'},
                       success:function(data){
                           if(0==data.code){
                               setTimeout(function(){
                                    top.layer.msg(data.msg,{icon:1});
                                    layer.closeAll("iframe");
                                    parent.location.reload();
                                },1000);
                           }else{
                               setTimeout(function(){
                                    top.layer.msg(data.msg,{icon:2});
                                },1000);
                           }
                       } ,error : function() {
                            setTimeout(function(){
                                top.layer.msg("异常！",{icon:2});
                                layer.closeAll("iframe");
                            },1000);
                       }
                    });
                }
            });
    });
</script>
</html>