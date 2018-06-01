<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<title>添加考勤</title>
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
<form:form class="layui-form" style="width:80%;" id="attForm" modelAttribute="stck">
	<div class="layui-form-item layui-row layui-col-xs12">
		<div class="magb15 layui-col-md4 layui-col-xs12">
			<label class="layui-form-label">时间段</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input" lay-verify="required" placeholder="请输入考勤时间段,如：上午，下午、晚上" name="checkTimeName" id="checkTimeName">
			</div>
		</div>
	</div>
	<div class="layui-form-item layui-row layui-col-xs12">
		<label class="layui-form-label">考勤状态</label>
			<div class="layui-input-block">
				<select name="status" lay-verify="required" id="status">
              	
              	</select>
			</div>
	</div>
	<div class="layui-form-item layui-row layui-col-xs12">
		<div class="magb15 layui-col-md4 layui-col-xs12" style="display:none;" id="kuang">
			<label class="layui-form-label">是否有假条</label>
			<div class="layui-input-block">
				<input type="radio" name="isNote" lay-filter="isNote" value="0" title="没有假条">
				<input type="radio" name="isNote" lay-filter="isNote" value="1" title="有假条" checked>
			</div>
		</div>
		<div id="sta">
			
		</div>
	</div>
	<div class="layui-form-item layui-row layui-col-xs12">
		<div class="layui-input-block">
			<button class="layui-btn layui-btn-sm" lay-submit="" lay-filter="attAdd">立即添加</button>
			<button type="reset" class="layui-btn layui-btn-sm layui-btn-primary">取消</button>
		</div>
	</div>
</form:form>
<script type="text/javascript" src="<%=request.getContextPath() %>/layui/layui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/attendance/attendanceList.js"></script>
</body>
</html> 