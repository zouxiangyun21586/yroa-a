<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<title>添加假条</title>
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
<form class="layui-form" style="width:80%;" id="leaveForm">
	<div class="layui-form-item layui-row layui-col-xs12">
		<div class="magb15 layui-col-md4 layui-col-xs12">
			<label class="layui-form-label">批次名</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input" lay-verify="required" placeholder="请输入批次名" name="className" id="className">
			</div>
		</div>
		<div class="magb15 layui-col-md4 layui-col-xs12">
			<label class="layui-form-label">学生名</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input" lay-verify="required" placeholder="请输入学生姓名" name="studentName" id="studentName">
			</div>
		</div>
		<div class="magb15 layui-col-md4 layui-col-xs12">
			<label class="layui-form-label">请假日期</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input" id="year" placeholder="yyyy-MM-dd" lay-verify="required" name="leaveDate" id="leaveDate">
			</div>
		</div>
		<div class="magb15 layui-col-md4 layui-col-xs12">
			<label class="layui-form-label">请假类型</label>
			<div class="layui-input-block leaType">
				<input type="radio" name="leaveType" value="TH" title="事假" checked>
				<input type="radio" name="leaveType" value="SK" title="病假">
			</div>
		</div>
	</div>
	<div class="layui-form-item layui-row layui-col-xs12">
		<div class="magb15 layui-col-md4 layui-col-xs12">
			<label class="layui-form-label">请假时间</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input" placeholder="yyyy-MM-dd" lay-verify="required" name="leaveHour" id="leaveHour">
			</div>
		</div>
		<div class="magb15 layui-col-md4 layui-col-xs12">
			<label class="layui-form-label">请假时长</label>
			<div class="layui-input-block">
				<select lay-filter="leaveTimeLong" name="leaveTimeLong">
					<option value="30">30分钟</option>
					<option value="60">60分钟</option>
					<option value="90">90分钟</option>
				</select>
			</div>
		</div>
		<div class="magb15 layui-col-md4 layui-col-xs12">
			<label class="layui-form-label">请假附条</label>
			<div class="layui-input-block">
				<button id="imgUrl" name="imgUrl">上传请假纸制证明</button>
			</div>
		</div>
		<div class="layui-form-item layui-row layui-col-xs12">
		<label class="layui-form-label">请假事因</label>
		<div class="layui-input-block">
			<textarea placeholder="请输入请假事因" class="layui-textarea userDesc" name="leaveDesc" id="leaveDesc"></textarea>
		</div>
	</div>
	</div>
	<div class="layui-form-item layui-row layui-col-xs12">
		<div class="layui-input-block">
			<button class="layui-btn layui-btn-sm" lay-submit="" lay-filter="add">立即添加</button>
			<button type="reset" class="layui-btn layui-btn-sm layui-btn-primary">取消</button>
		</div>
	</div>
</form>
<script type="text/javascript" src="<%=request.getContextPath() %>/layui/layui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/attendance/leaveAdd.js"></script>
</body>
</html>