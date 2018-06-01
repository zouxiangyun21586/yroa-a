<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>毕业学生信息</title>
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
		<div class="layui-form-item layui-row layui-col-xs12">
			<div class="magb15 layui-col-md4 layui-col-xs12">
				<label class="layui-form-label">学生姓名</label>
				<div class="layui-input-block">
					<input type="text" name="name"  id="name" class="layui-input" lay-verify="required" readonly="readonly" />
				</div>
			</div>
			<div class="magb15 layui-col-md4 layui-col-xs12">
				<label class="layui-form-label">年龄</label>
				<div class="layui-input-block">
					<input type="text" name="age"  id="age" class="layui-input"  lay-verify="required" readonly="readonly" />
				</div>
			</div>
			<div class="magb15 layui-col-md4 layui-col-xs12">
				<label class="layui-form-label">性别</label>
				<div class="layui-input-block userSex">
					<input type="text" name="sex"  id="sex" class="layui-input" lay-verify="required"  readonly="readonly" /> 
				</div>
			</div>
		</div>
		<div class="layui-form-item layui-row layui-col-xs12">
			<div class="magb15 layui-col-md4 layui-col-xs12">
				<label class="layui-form-label">出生日期</label>
				<div class="layui-input-block">
					<input type="text" name="birth"  id="birth" class="layui-input"  lay-verify="required" readonly="readonly" />
				</div>
			</div>
			<div class="magb15 layui-col-md4 layui-col-xs12">
				<label class="layui-form-label">届次</label>
				<div class="layui-input-block">
              		<input type="text" name="year"  id="year" class="layui-input" lay-verify="required"  readonly="readonly" />							
				</div>
			</div>
			<div class="magb15 layui-col-md4 layui-col-xs12">
				<label class="layui-form-label">电话</label>
				<div class="layui-input-block">
					<input type="text" name="tel"  id="tel" class="layui-input" lay-verify="required"  readonly="readonly" />
				</div>
			</div>
		</div>
		<div class="layui-form-item layui-row layui-col-xs12">
			<div class="magb15 layui-col-md4 layui-col-xs12">
				<label class="layui-form-label">地址</label>
				<div class="layui-input-block">
					<input type="text" name="addr"  id="addr" class="layui-input" lay-verify="required"  readonly="readonly" />
				</div>
			</div>	
			<div class="magb15 layui-col-md4 layui-col-xs12">
				<label class="layui-form-label">家长电话</label>
				<div class="layui-input-block">
					<input type="text" name="homeTel"  id="homeTel" class="layui-input" lay-verify="required"  readonly="readonly" />
				</div>
			</div>
			<div class="magb15 layui-col-md4 layui-col-xs12">
				<label class="layui-form-label">入学日期</label>
				<div class="layui-input-block">
					<input type="text" name="inTime"  id="inTime" class="layui-input" lay-verify="required"  readonly="readonly" />
				</div>
			</div>
		</div>
		<div class="layui-form-item layui-row layui-col-xs12">
			<div class="magb15 layui-col-md4 layui-col-xs12">
				<label class="layui-form-label">毕业时间</label>
				<div class="layui-input-block">
					<input type="text" name="finishTime"  id="finishTime" class="layui-input" lay-verify="required"  readonly="readonly" />
				</div>
			</div>
			<div class="magb15 layui-col-md4 layui-col-xs12">
				<label class="layui-form-label">入职时间</label>
				<div class="layui-input-block">
					<input type="text" name="offerTime"  id="offerTime" class="layui-input" lay-verify="required"  readonly="readonly" />
				</div>
			</div>
			<div class="magb15 layui-col-md4 layui-col-xs12">
				<label class="layui-form-label">工资</label>
				<div class="layui-input-block">
					<input type="text" name="offerIncome"  id="offerIncome" class="layui-input" lay-verify="required"  readonly="readonly" />
				</div>
			</div>
		</div>
		
<script type="text/javascript" src="<%=request.getContextPath() %>/layui/layui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/student/employmentInformation.js"></script>
</body>
</html>