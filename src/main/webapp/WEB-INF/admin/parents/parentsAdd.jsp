<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>添加家长</title>
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
<form class="layui-form" style="width:80%;" id = "HolidayForm">
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="magb15 layui-col-md4 layui-col-xs12">
            <label class="layui-form-label">家长姓名</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" name="name" lay-verify="required" placeholder="请输入家长姓名">
            </div>
        </div>
        <div class="magb15 layui-col-md4 layui-col-xs12">
            <label class="layui-form-label">学生姓名</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" id="stuName" placeholder="请输入学生姓名" lay-verify="required" name="stuName">
            </div>
        </div>
        <div class="magb15 layui-col-md4 layui-col-xs12">
            <label class="layui-form-label">家长电话</label>
            <div class="layui-input-block">
                <input class="layui-input" placeholder="请输入电话号码" onkeyup="this.value=this.value.replace(/[^-\d、/\s]/g, '')" name="tel" id="tel" lay-verify="required" maxlength="11">
            </div>
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <shiro:hasPermission name="/yroa-a/holiday/add">
            <button class="layui-btn layui-btn-sm" lay-submit="" lay-filter="addHoliday">保存</button>
            </shiro:hasPermission>
            <button type="reset" class="layui-btn layui-btn-sm layui-btn-primary">取消</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="<%=request.getContextPath() %>/layui/layui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/parents/parentsAdd.js"></script>
</body>
</html>