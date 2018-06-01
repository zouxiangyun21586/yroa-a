<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>查看考勤</title>
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
<form:form class="layui-form" style="width:80%;" modelAttribute="studentCheck" id = "HolidayForm">
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="magb15 layui-col-md4 layui-col-xs12">
            <label class="layui-form-label">届次名称</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" id="classCode" name="classCode" lay-verify="required" readonly="readonly" value="${studentCheck.classCode}-${studentCheck.className}" />
            </div>
        </div>
        <div class="magb15 layui-col-md4 layui-col-xs12">
            <label class="layui-form-label">学生姓名</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" id="studentName" name="studentName" lay-verify="required" readonly="readonly" value="${studentCheck.studentName}" />
            </div>
        </div>
        <div class="magb15 layui-col-md4 layui-col-xs12">
            <label class="layui-form-label">考勤时间段</label>
            <div class="layui-input-block">
                <input class="layui-input" id="checkTimeName" name="checkTimeName" lay-verify="required" readonly="readonly" value="${studentCheck.checkTimeDesc}" />
            </div>
        </div>
        <div class="magb15 layui-col-md4 layui-col-xs12">
            <label class="layui-form-label">考勤日期</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" id="checkTime" name="checkTime" lay-verify="required" readonly="readonly" value="${studentCheck.checkTime}" />
            </div>
        </div>
        <div class="magb15 layui-col-md4 layui-col-xs12">
            <label class="layui-form-label">考勤时间</label>
            <div class="layui-input-block">
                <input class="layui-input" id="startTime" name="startTime" lay-verify="required" readonly="readonly" value="${studentCheck.startTime}" />
            </div>
        </div>
        <div class="magb15 layui-col-md4 layui-col-xs12">
            <label class="layui-form-label">考勤时间</label>
            <div class="layui-input-block">
                <input class="layui-input" id="startTime" name="startTime" lay-verify="required" readonly="readonly" value="${studentCheck.startTime}" />
            </div>
        </div>
        <div class="magb15 layui-col-md4 layui-col-xs12">
            <label class="layui-form-label">签到时间</label>
            <div class="layui-input-block">
                <input class="layui-input" id="retyTime" name="retyTime" lay-verify="required" readonly="readonly" value="${studentCheck.retyTime}" />
            </div>
        </div>
        <div class="magb15 layui-col-md4 layui-col-xs12">
            <label class="layui-form-label">迟到</label>
            <div class="layui-input-block">
                <input class="layui-input" id="lateTime" name="lateTime" lay-verify="required" readonly="readonly" value="${studentCheck.lateTime}分钟" />
            </div>
        </div>
        <div class="magb15 layui-col-md4 layui-col-xs12">
           <label class="layui-form-label">考勤状态</label>
           <div class="layui-input-block">
               <input type="text" class="layui-input" id="statusName" name="statusName" lay-verify="required" readonly="readonly" value="${statusName}" />
           </div>
        </div>
        <div class="layui-form-item layui-row layui-col-xs12">
            <label class="layui-form-label">请假条</label>
            <div class="layui-input-block">
                <input class="layui-input" id="isNote" name="isNote" readonly="readonly" value="${note}" />
            </div>
        </div>
    </div>
</form:form>
<script type="text/javascript" src="<%=request.getContextPath() %>/layui/layui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/attendance/attendanceView.js"></script>
</body>
</html>