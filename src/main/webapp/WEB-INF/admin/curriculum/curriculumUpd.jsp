<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>修改届次</title>
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
<form:form class="layui-form" style="width:80%;" id="clasUpdForm" modelAttribute="clas">
	<form:hidden id="teacherIdCode" path="code"/>
    <input type="hidden" name="_method" value="PUT">
	<div class="layui-form-item layui-row layui-col-xs12">
		<label class="layui-form-label">本届教师</label>
			<input type="hidden" id="teacherName" name="teacherName" value="${clas.teacherName}"/>
			<div class="layui-input-block">
				<select name="teacherCode" lay-verify="required" id="teacherCode">
              	
              	</select>
			</div>
	</div>
	<div class="layui-form-item layui-row layui-col-xs12">
		<div class="layui-input-block">
			<button class="layui-btn layui-btn-sm" lay-submit="submit" lay-filter="updCurr">立即修改</button>
			<button type="reset" class="layui-btn layui-btn-sm layui-btn-primary">取消</button>
		</div>
	</div>
</form:form>
<script type="text/javascript" src="<%=request.getContextPath() %>/layui/layui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/curriculum/curriculumUpd.js"></script>
</body>
</html>