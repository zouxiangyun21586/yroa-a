<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>修改假期</title>
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
                <form:input type="text" class="layui-input" path="name" lay-verify="required" placeholder="请输入假期名称" />
            </div>
        </div>
        <div class="magb15 layui-col-md4 layui-col-xs12">
            <label class="layui-form-label">放假开始日期</label>
            <div class="layui-input-block">
                <form:input type="text" class="layui-input" path="startDate" placeholder="yyyy-MM-dd" lay-verify="required" />
            </div>
        </div>
        <div class="magb15 layui-col-md4 layui-col-xs12">
            <label class="layui-form-label">放假开始时间</label>
            <div class="layui-input-block">
                <form:input class="layui-input" placeholder="请输入放假开始时间" path="startTime" lay-verify="required" />
            </div>
        </div>
        <div class="magb15 layui-col-md4 layui-col-xs12">
            <label class="layui-form-label">放假结束日期</label>
            <div class="layui-input-block">
                <form:input type="text" class="layui-input" path="endDate" placeholder="yyyy-MM-dd" lay-verify="required" />
            </div>
        </div>
        <div class="magb15 layui-col-md4 layui-col-xs12">
            <label class="layui-form-label">放假结束时间</label>
            <div class="layui-input-block">
                <form:input class="layui-input" placeholder="请输入放假结束时间" path="endTime" lay-verify="required" />
            </div>
        </div>
        <div class="magb15 layui-col-md4 layui-col-xs12">
           <label class="layui-form-label">届次名称</label>
          <div class="layui-input-block editWidth">
              <select name="classCode" lay-verify="required" id="claSelect">
                  
              </select>
          </div>
        </div>
        <div class="layui-form-item layui-row layui-col-xs12">
            <label class="layui-form-label">备注</label>
            <div class="layui-input-block">
                <form:textarea placeholder="请输入注意事项" class="layui-textarea userDesc" path="info"></form:textarea>
            </div>
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-sm" lay-submit="" lay-filter="addHoliday">修改</button>
            <button type="reset" class="layui-btn layui-btn-sm layui-btn-primary">重置</button>
        </div>
    </div>
</form:form>
<script type="text/javascript" src="<%=request.getContextPath() %>/layui/layui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/attendance/holidayUpdate.js"></script>
</body>
</html>