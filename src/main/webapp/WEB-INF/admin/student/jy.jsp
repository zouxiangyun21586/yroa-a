<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>修改学生信息</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/layui/css/layui.css" media="all" />
	<style>
		.layui-col-xs12{margin-bottom:15px;}
		.layui-form-radio{margin:6px -5px 5px 15px;padding-right:0px;}
	</style>
</head>
<body>
<br/>
	<form:form class="layui-form" style="width:80%;" id="studentForm" modelAttribute="jy">
		<form:hidden path="id" />
		<div class="layui-form-item layui-row layui-col-xs12">
			<div class="magb15 layui-col-md4 layui-col-xs12">
				<label class="layui-form-label">学生姓名</label>
				<div class="layui-input-block">
					<form:input path="name" class="layui-input" lay-verify="required" placeholder="请输入学生姓名" readonly="readonly"/>
				</div>
			</div>
			<div class="magb15 layui-col-md4 layui-col-xs12">
				<label class="layui-form-label">毕业时间</label>
				<div class="layui-input-block">
					<form:input path="finishTime" class="layui-input" id="finishTime" placeholder="yyyy-MM-dd" lay-verify="required" />
				</div>
			</div>
			<div class="magb15 layui-col-md4 layui-col-xs12">
				<label class="layui-form-label">入职时间</label>
				<div class="layui-input-block">
					<form:input path="offerTime" class="layui-input" id="offerTime" placeholder="yyyy-MM-dd" lay-verify="required" />
				</div>
			</div>
		</div>	
		<div class="layui-form-item layui-row layui-col-xs12">
			<div class="magb15 layui-col-md4 layui-col-xs12">
				<label class="layui-form-label">工资</label>
				<div class="layui-input-block">
					<form:input path="offerIncome" class="layui-input" placeholder="yyyy-MM-dd" lay-verify="required" />
				</div>
			</div>
		</div>
		<div class="layui-form-item layui-row layui-col-xs12">
			<div class="layui-input-block">
				<button class="layui-btn layui-btn-sm" lay-submit="" lay-filter="update">保存</button>
				<button type="reset" class="layui-btn layui-btn-sm layui-btn-primary">取消</button>
			</div>
		</div>
	</form:form>


<script type="text/javascript" src="<%=request.getContextPath() %>/layui/layui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/student/jy.js"></script>
</body>
</html>