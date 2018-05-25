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
<form class="layui-form" style="width:80%;" id="accountAdd">
	<div class="layui-form-item layui-row layui-col-xs12">
		<div class="magb15 layui-col-md4 layui-col-xs12">
            <label class="layui-form-label">登录名</label>
            <div class="layui-input-block">
                <input type="text" name="username" class="layui-input userName" lay-verify="required|username" placeholder="请输入登录名">
            </div>
        </div>
        <div class="magb15 layui-col-md4 layui-col-xs12">
            <label class="layui-form-label">电话</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" name="tel" lay-verify="required|phone" placeholder="请输入正确的电话号码">
            </div>
        </div>
		<div class="magb15 layui-col-md4 layui-col-xs12">
			<label class="layui-form-label">设为管理员?</label>
			<div class="layui-input-block userSex">
				<input type="radio" name="isAdmin" value="是" title="是">
				<input type="radio" name="isAdmin" value="否" title="否" checked>
			</div>
		</div>
	</div>
	<div class="layui-form-item layui-row layui-col-xs12">
		<div class="magb15 layui-col-md4 layui-col-xs12">
			<label class="layui-form-label">密码</label>
			<div class="layui-input-block">
				<input type="password" name="password" class="layui-input linksPassWrod" lay-verify="required|password" placeholder="请输入" maxlength="16">
			</div>
		</div>
		<div class="magb15 layui-col-md4 layui-col-xs12">
			<label class="layui-form-label">确定密码</label>
			<div class="layui-input-block">
				<input type="password" name="passwords" class="layui-input linksPassWrods" lay-verify="required|password" placeholder="请输入" maxlength="16">
			</div>
		</div>
	    <div class="magb15 layui-col-md4 layui-col-xs12">
            <label class="layui-form-label">所属角色</label>
            <div class="layui-input-block">
                <select id="code" name="code" lay-verify="required">
                </select>
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
			<button class="layui-btn layui-btn-sm" lay-submit="" lay-filter="addUser">立即添加</button>
			<button type="reset" class="layui-btn layui-btn-sm layui-btn-primary">取消</button>
		</div>
	</div>
</form>
<script type="text/javascript" src="<%=request.getContextPath() %>/layui/layui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/user/userAdd.js"></script>
</body>
</html>