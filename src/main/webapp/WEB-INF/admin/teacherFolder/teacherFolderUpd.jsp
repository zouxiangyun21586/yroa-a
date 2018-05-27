<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>修改老师信息</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/layui/css/layui.css" media="all" />
<style>
	.layui-col-xs12{margin-bottom:15px;}
	.layui-form-label{width:90px;}
    .layui-input-block{margin-left:120px;}
</style>
</head>
<body>
<br/>
<form:form class="layui-form" style="width:80%;" modelAttribute="teacher" id="teacherUpdForm">
	<form:hidden path="id"/>
    <input type="hidden" name="_method" value="PUT">
	<div class="layui-form-item layui-row layui-col-xs12">
		<div class="magb15 layui-col-md4 layui-col-xs12">
			<label class="layui-form-label">电话</label>
			<div class="layui-input-block">
				<form:input path="tel" class="layui-input" lay-verify="required" placeholder="请输入老师电话"/>
			</div>
		</div>
		<div class="magb15 layui-col-md4 layui-col-xs12">
			<label class="layui-form-label">教学等级</label>
			<div class="layui-input-block">
				<input type="hidden" id="tealevel" name="tealevel" value="${teacher.level}"/>
				<select name="level" lay-verify="required" id="levQuyer">
           		</select>
			</div>
		</div>
	</div>
	<div class="layui-form-item layui-row layui-col-xs12">
		<div class="magb15 layui-col-md4 layui-col-xs12">
			<label class="layui-form-label">任职状态</label>
			<div class="layui-input-block">
				<input type="hidden" id="teacherisLeave" name="teacherisLeave" value="${teacher.isLeave}"/>
				<select name="isLeave" lay-verify="required" id="islea">
            	</select>
			</div>
		</div>
	</div>
	<div class="layui-form-item layui-row layui-col-xs12">
		<div class="layui-input-block">
			<button class="layui-btn layui-btn-sm" lay-submit="" lay-filter="updTeacher">立即修改</button>
			<button type="reset" class="layui-btn layui-btn-sm layui-btn-primary">取消</button>
		</div>
	</div>
</form:form>
<script type="text/javascript" src="<%=request.getContextPath() %>/layui/layui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/teacherFolder/teacherFolderUpd.js"></script>
</body>
</html>