<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>没有权限</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/layui/css/layui.css">
    <link rel="stylesheet" href="css/public.css" media="all" />
</head>
<body class="childrenBody">
<div class="layui-anim layui-anim-fadein">
	<div class="noFind">
		<div class="ufo">
			<i class="seraph icon-test ufo_icon"></i>
			<i class="layui-icon page_icon">&#xe638;</i>
		</div>
		<div class="page404">
			<i class="layui-icon">&#xe7ae;</i>
			<p style="font-size:25px;">我勒个去，权限不足!</p>
		</div>
	</div>
</div>
</body>
<script src="<%=request.getContextPath() %>/layui/layui.js"></script>
</html>