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
<form class="layui-form" style="width:80%;" id="clasForm">
	<div class="layui-form-item layui-row layui-col-xs12">
		<label class="layui-form-label">班级名</label>
		<div class="layui-input-block">
			<input type="text" class="layui-input userName" lay-verify="required" placeholder="请输入" name="name" >
		</div>
	</div>
	<div class="layui-form-item layui-row layui-col-xs12">
		<div class="magb15 layui-col-md4 layui-col-xs12">
				<label class="layui-form-label">开班日期</label>
				<div class="layui-input-block">
					<input type="text" class="layui-input" placeholder="yyyy-MM-dd" lay-verify="required" name="startTime" id="entranceYear">
				</div>
			</div>
	</div>
	<div class="layui-form-item layui-row layui-col-xs12">
		<label class="layui-form-label">本届教师</label>
			<div class="layui-input-block">
				<select lay-filter="teacherCode" name="teacherCode">
					<option value="1001">钟林军</option>
					<option value="1002">林娟娟</option>
				</select>
			</div>
	</div>
	<div class="layui-form-item layui-row layui-col-xs12">
		<label class="layui-form-label">是否毕业</label>
			<div class="layui-input-block">
				<select lay-filter="isFinish" name="isFinish">
					<option value="0">未毕业</option>
					<option value="1">毕业生</option>
				</select>
			</div>
	</div>
	<div class="layui-form-item layui-row layui-col-xs12">
		<div class="layui-input-block">
			<button class="layui-btn layui-btn-sm" lay-submit="submit" lay-filter="addUser">立即添加</button>
			<button type="reset" class="layui-btn layui-btn-sm layui-btn-primary">取消</button>
		</div>
	</div>
</form>
<script type="text/javascript" src="<%=request.getContextPath() %>/layui/layui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/curriculum/curriculumAdd.js"></script>
</body>
</html>