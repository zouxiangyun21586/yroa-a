<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>查看假期</title>
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
<form:form class="layui-form" style="width:80%;" modelAttribute="holiday" id = "HolidayForm">
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="magb15 layui-col-md4 layui-col-xs12">
            <label class="layui-form-label">假期名称</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" id="name" name="name" lay-verify="required" readonly="readonly" value="${holiday.name}" />
            </div>
        </div>
        <div class="magb15 layui-col-md4 layui-col-xs12">
            <label class="layui-form-label">放假开始日期</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" id="startDate" name="startDate" lay-verify="required" readonly="readonly" value="${holiday.startDate}" />
            </div>
        </div>
        <div class="magb15 layui-col-md4 layui-col-xs12">
            <label class="layui-form-label">放假开始时间</label>
            <div class="layui-input-block">
                <input class="layui-input" id="startTime" name="startTime" lay-verify="required" readonly="readonly" value="${holiday.startTime}" />
            </div>
        </div>
        <div class="magb15 layui-col-md4 layui-col-xs12">
            <label class="layui-form-label">放假结束日期</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" id="endDate" name="endDate" lay-verify="required" readonly="readonly" value="${holiday.endDate}" />
            </div>
        </div>
        <div class="magb15 layui-col-md4 layui-col-xs12">
            <label class="layui-form-label">放假结束时间</label>
            <div class="layui-input-block">
                <input class="layui-input" id="endTime" name="endTime" lay-verify="required" readonly="readonly" value="${holiday.endTime}" />
            </div>
        </div>
        <div class="magb15 layui-col-md4 layui-col-xs12">
           <label class="layui-form-label">届次名称</label>
          <div class="layui-input-block">
              <input type="text" class="layui-input" id="classCode" name="classCode" lay-verify="required" readonly="readonly" value="${holiday.classCode}" />
          </div>
        </div>
        <div class="magb15 layui-col-md4 layui-col-xs12">
           <label class="layui-form-label">发布状态</label>
          <div class="layui-input-block">
              <input type="text" class="layui-input" id="statusName" name="statusName" lay-verify="required" readonly="readonly" value="${holiday.statusName}" />
          </div>
        </div>
        <div class="layui-form-item layui-row layui-col-xs12">
            <label class="layui-form-label">备注</label>
            <div class="layui-input-block">
                <textarea class="layui-textarea userDesc" id="info" name="info" readonly="readonly" >${holiday.info}</textarea>
            </div>
        </div>
    </div>
</form:form>
<script type="text/javascript" src="<%=request.getContextPath() %>/layui/layui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/attendance/holidayView.js"></script>
</body>
</html>