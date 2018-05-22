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
<form class="layui-form" style="width:80%;">
	<div class="layui-form-item layui-row layui-col-xs12">
		<label class="layui-form-label">课程名</label>
		<div class="layui-input-block">
			<input type="text" class="layui-input userName" lay-verify="required" placeholder="请输入登录名">
		</div>
	</div>
	<div class="layui-form-item layui-row layui-col-xs12">
		<label class="layui-form-label">课程等级</label>
			<div class="layui-input-block">
				<select name="userGrade" class="userGrade" lay-filter="userGrade">
					<option value="0">初级</option>
					<option value="1">中级</option>
					<option value="2">高级</option>
				</select>
			</div>
	</div>
	<div class="layui-form-item layui-row layui-col-xs12">
		<label class="layui-form-label">课程简介</label>
		<div class="layui-input-block">
			<textarea placeholder="请输入简介" class="layui-textarea userDesc"></textarea>
		</div>
	</div>
	<div class="layui-form-item layui-row layui-col-xs12">
		<div class="layui-input-block">
			<button class="layui-btn layui-btn-sm" lay-submit="" lay-filter="addUser">立即添加</button>
			<button type="reset" class="layui-btn layui-btn-sm layui-btn-primary">取消</button>
		</div>
	</div>
</form>
<script type="text/javascript" src="<%=request.getContextPath() %>/layui/layui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/class/classAdd.js"></script>
</body>
</html>