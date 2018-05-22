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
</head>
<body>
<br/>
<div align="center">
	<form class="layui-form layui-form-pane" style="width:80%;" id="UserForm">
		<div class="layui-form-item">
			<label class="layui-form-label">用户名</label>
			<div class="layui-input-block">
				<input type="text" name="name" class="layui-input linksName" lay-verify="required" placeholder="请输入" maxlength="6">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">账号</label>
			<div class="layui-input-block">
				<input type="text" name="userName" class="layui-input linksUserName" lay-verify="required|username" placeholder="请输入" maxlength="12">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">密码</label>
			<div class="layui-input-block">
				<input type="password" name="passWord" class="layui-input linksPassWrod" lay-verify="required|pass" placeholder="请输入" maxlength="16">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">确定密码</label>
			<div class="layui-input-block">
				<input type="password" name="passWords" class="layui-input linksPassWrods" lay-verify="required|pass" placeholder="请输入" maxlength="16">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">邮箱</label>
			<div class="layui-input-block">
				<input type="text" name="email" class="layui-input linksPassWrods" lay-verify="required|email" placeholder="请输入">
			</div>
		</div>
		<input type="hidden" name="operation" value="user_insert">
	<div class="layui-form-item">
		<button class="layui-btn" lay-submit="" lay-filter="addUser">立即提交</button>
		<button type="reset" class="layui-btn layui-btn-primary">重置</button>
    </div>
	</form>
</div>
<script type="text/javascript" src="<%=request.getContextPath() %>/layui/layui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/teacher/teacherAdd.js"></script>
</body>
</html>