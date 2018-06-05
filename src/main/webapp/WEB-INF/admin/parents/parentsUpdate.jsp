<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>修改家长数据</title>
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
<form:form class="layui-form" style="width:80%;" modelAttribute="parents" id = "ParentsForm">
    <form:hidden path="id"/>
    <input type="hidden" name="_method" value="PUT">
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="magb15 layui-col-md4 layui-col-xs12">
            <label class="layui-form-label">家长姓名</label>
            <div class="layui-input-block">
                <form:input type="text" class="layui-input" path="name" lay-verify="required" placeholder="请输入假期名称" />
            </div>
        </div>
        <div class="magb15 layui-col-md4 layui-col-xs12">
            <label class="layui-form-label">家长电话</label>
            <div class="layui-input-block">
                <form:input type="text" class="layui-input" path="tel" placeholder="请输入家长电话号码" lay-verify="required" />
            </div>
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <shiro:hasPermission name="/yroa-a/parents/update">
            <button class="layui-btn layui-btn-sm" lay-submit="updateParents" lay-filter="updateParents">修改</button>
            </shiro:hasPermission>
            <button type="reset" class="layui-btn layui-btn-sm layui-btn-primary">重置</button>
        </div>
    </div>
</form:form>
<script type="text/javascript" src="<%=request.getContextPath() %>/layui/layui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/parents/parentsUpdate.js"></script>
</body>
</html>