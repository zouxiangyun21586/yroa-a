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
	<form class="layui-form" style="width:80%;" id="studentForm" modelAttribute="student">
		<input type="hidden" name="_method" value="PUT">
		<div class="layui-form-item layui-row layui-col-xs12">
			<div class="magb15 layui-col-md4 layui-col-xs12">
				<label class="layui-form-label">学生姓名</label>
				<div class="layui-input-block">
					<input class="layui-input" readonly="readonly" value="唐子豪">
				</div>
			</div>
			<div class="magb15 layui-col-md4 layui-col-xs12">
				<label class="layui-form-label">就业日期</label>
				<div class="layui-input-block">
					<input class="layui-input" id="year">
				</div>
			</div>
			<div class="magb15 layui-col-md4 layui-col-xs12">
				<label class="layui-form-label">届次</label>
				<div class="layui-input-block">
				 	<input class="layui-input" value="2017" readonly="readonly">						
				</div>
			</div>
		</div>
		<div class="layui-form-item layui-row layui-col-xs12">
			<div class="magb15 layui-col-md4 layui-col-xs12">
				<label class="layui-form-label">公司名称</label>
				<div class="layui-input-block">
				 	<input class="layui-input">
				</div>
			</div>
			<div class="magb15 layui-col-md4 layui-col-xs12">
				<label class="layui-form-label">资薪</label>
				<div class="layui-input-block">
				 	<input class="layui-input">
				</div>
			</div>
			<div class="magb15 layui-col-md4 layui-col-xs12">
				<label class="layui-form-label">公司地址</label>
				<div class="layui-input-block">
				 	<input class="layui-input">
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
<script type="text/javascript" src="<%=request.getContextPath() %>/js/student/jy.js"></script>
</body>
</html>