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
<br/>
<form class="layui-form" style="width:80%;" id="authUpd">
	<div class="layui-form-item layui-row layui-col-xs12">
        <div class="magb15 layui-col-md4 layui-col-xs12">
            <label class="layui-form-label">权限名</label>
            <div class="layui-input-block">
                <input type="text" id="name" name="name" class="layui-input userName" lay-verify="required|username" placeholder="请输入角色名">
            </div>
        </div>
        <div class="magb15 layui-col-md4 layui-col-xs12">
            <label class="layui-form-label">权限路径</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" id="url" name="url" lay-verify="required" placeholder="权限路径">
            </div>
        </div>
        <div class="magb15 layui-col-md4 layui-col-xs12">
            <label class="layui-form-label">shiro操作</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" id="caozuo" name="caozuo" lay-verify="required" placeholder="anon放行,authc需验证等...">
            </div>
        </div>
    </div>
	<!-- <div class="layui-form-item layui-row layui-col-xs12">
		<label class="layui-form-label">用户简介</label>
		<div class="layui-input-block">
			<textarea placeholder="请输入用户简介" class="layui-textarea userDesc"></textarea>
		</div>
	</div> -->
	<div class="layui-form-item layui-row layui-col-xs12">
		<div class="layui-input-block">
			<button class="layui-btn layui-btn-sm" lay-submit="" lay-filter="addUser">立即修改</button>
			<button type="reset" class="layui-btn layui-btn-sm layui-btn-primary">取消</button>
		</div>
	</div>
</form>
<script type="text/javascript" src="<%=request.getContextPath() %>/layui/layui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/authJs/authUpd.js"></script>
</body>
</html>