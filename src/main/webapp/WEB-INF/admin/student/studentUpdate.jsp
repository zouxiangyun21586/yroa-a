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
</style>
</head>
<body>
<br/>
	<form:form class="layui-form" style="width:80%;" id="studentForm" modelAttribute="student">
		<form:hidden path="id" />
		<input type="hidden" id="clsC" name="clsC" value="${studnet.year}" />
		<div class="layui-form-item layui-row layui-col-xs12">
			<div class="magb15 layui-col-md4 layui-col-xs12">
				<label class="layui-form-label">学生姓名</label>
				<div class="layui-input-block">
					<form:input path="name" class="layui-input" lay-verify="required" placeholder="请输入学生姓名"/>
				</div>
			</div>
			<div class="magb15 layui-col-md4 layui-col-xs12">
				<label class="layui-form-label">出生日期</label>
				<div class="layui-input-block">
					<form:input path="birth" class="layui-input" id="year" placeholder="yyyy-MM-dd" lay-verify="required" />
				</div>
			</div>
			<div class="magb15 layui-col-md4 layui-col-xs12">
				<label class="layui-form-label">性别</label>
				<div class="layui-input-block userSex">
					<input type="radio" name="sex" value="1" title="男" checked>
					<input type="radio" name="sex" value="0" title="女">
				</div>
			</div>
		</div>
		<div class="layui-form-item layui-row layui-col-xs12">
			<div class="magb15 layui-col-md4 layui-col-xs12">
				<label class="layui-form-label">届次</label>
				<div class="layui-input-block">
					<input type="hidden" id="code" name="code" value="${student.classCode}" />	
					<select name="classCode" lay-verify="required" id="claSelect">
                  		
              		</select>							
				</div>
			</div>
			<div class="magb15 layui-col-md4 layui-col-xs12">
				<label class="layui-form-label">电话</label>
				<div class="layui-input-block">
					<form:input path="tel" class="layui-input" placeholder="请输入手机号" lay-verify="required|phone"/>
				</div>
			</div>
			<div class="magb15 layui-col-md4 layui-col-xs12">
				<label class="layui-form-label">地址</label>
				<div class="layui-input-block">
					<form:input path="addr" class="layui-input" placeholder="请输入地址" name="addr" lay-verify="required" />
				</div>
			</div>
		</div>
		<div class="layui-form-item layui-row layui-col-xs12">
			<div class="magb15 layui-col-md4 layui-col-xs12">
				<label class="layui-form-label">家长电话</label>
				<div class="layui-input-block">
					<form:input path="homeTel" class="layui-input" placeholder="请输入家长手机号" lay-verify="required|phone" />
				</div>
			</div>
			<div class="magb15 layui-col-md4 layui-col-xs12">
				<label class="layui-form-label">入学日期</label>
				<div class="layui-input-block">
					<form:input path="inTime" class="layui-input" id="entranceYear" placeholder="yyyy-MM-dd" lay-verify="required" />
				</div>
			</div>
		</div>
		<div class="layui-form-item layui-row layui-col-xs12">
			<div class="layui-input-block">
				<button class="layui-btn layui-btn-sm" lay-submit="" lay-filter="update">立即添加</button>
				<button type="reset" class="layui-btn layui-btn-sm layui-btn-primary">取消</button>
			</div>
		</div>
	</form:form>
	
	
	
<%-- <form class="layui-form" style="width:80%;" id="studentForm">
	<div class="layui-form-item layui-row layui-col-xs12">
		<div class="magb15 layui-col-md4 layui-col-xs12">
			<label class="layui-form-label">学生姓名</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input" lay-verify="required" placeholder="请输入学生姓名" name="name" />
			</div>
		</div>
		<div class="magb15 layui-col-md4 layui-col-xs12">
			<label class="layui-form-label">出生日期</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input" id="year" placeholder="yyyy-MM-dd" lay-verify="required" name="birth" />
			</div>
		</div>
		<div class="magb15 layui-col-md4 layui-col-xs12">
			<label class="layui-form-label">性别</label>
			<div class="layui-input-block userSex">
				<input type="radio" name="sex" value="1" title="男" checked>
				<input type="radio" name="sex" value="0" title="女">
			</div>
		</div>
	</div>
	<div class="layui-form-item layui-row layui-col-xs12">
		<div class="magb15 layui-col-md4 layui-col-xs12">
			<label class="layui-form-label">届次</label>
			<div class="layui-input-block">
				<select lay-filter="year" name="year" />
					<option value="2018">2018届</option>
					<option value="2017">2017届</option>
				</select>
			</div>
		</div>
		<div class="magb15 layui-col-md4 layui-col-xs12">
			<label class="layui-form-label">电话</label>
			<div class="layui-input-block">
				<input class="layui-input" placeholder="请输入手机号" name="tel" lay-verify="required|phone" />
			</div>
		</div>
		<div class="magb15 layui-col-md4 layui-col-xs12">
			<label class="layui-form-label">地址</label>
			<div class="layui-input-block">
				<input class="layui-input" placeholder="请输入地址" name="addr" lay-verify="required">
			</div>
		</div>
	</div>
	<div class="layui-form-item layui-row layui-col-xs12">
		<div class="magb15 layui-col-md4 layui-col-xs12">
			<label class="layui-form-label">家长电话</label>
			<div class="layui-input-block">
				<input class="layui-input" placeholder="请输入家长手机号" name="homeTel" lay-verify="required|phone">
			</div>
		</div>
		<div class="magb15 layui-col-md4 layui-col-xs12">
			<label class="layui-form-label">入学日期</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input" id="entranceYear" placeholder="yyyy-MM-dd" lay-verify="required" name="inTime">
			</div>
		</div>
	</div>
	<div class="layui-form-item layui-row layui-col-xs12">
		<div class="layui-input-block">
			<button class="layui-btn layui-btn-sm" lay-submit="" lay-filter="update">立即添加</button>
			<button type="reset" class="layui-btn layui-btn-sm layui-btn-primary">取消</button>
		</div>
	</div>
</form> --%>
<script type="text/javascript" src="<%=request.getContextPath() %>/layui/layui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/student/studentUpdate.js"></script>
</body>
</html>