<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>添加假期</title>
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
            <label class="layui-form-label">假期名称</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" name="name" lay-verify="required" placeholder="请输入假期名称">
            </div>
        </div>
        <div class="magb15 layui-col-md4 layui-col-xs12">
            <label class="layui-form-label">放假开始日期</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" id="startDate" placeholder="yyyy-MM-dd" lay-verify="required" name="startDate">
            </div>
        </div>
        <div class="magb15 layui-col-md4 layui-col-xs12">
            <label class="layui-form-label">放假开始时间</label>
            <div class="layui-input-block">
                <input class="layui-input" placeholder="请输入放假开始时间" name="startTime" id="startTime" lay-verify="required">
            </div>
        </div>
        <div class="magb15 layui-col-md4 layui-col-xs12">
            <label class="layui-form-label">放假结束日期</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" id="endDate" placeholder="yyyy-MM-dd" lay-verify="required" name="endDate">
            </div>
        </div>
        <div class="magb15 layui-col-md4 layui-col-xs12">
            <label class="layui-form-label">放假结束时间</label>
            <div class="layui-input-block">
                <input class="layui-input" placeholder="请输入放假结束时间" name="endTime" id="endTime" lay-verify="required">
            </div>
        </div>
        <shiro:hasPermission name="/yroa-a/holiday/clasList">
        <div class="magb15 layui-col-md4 layui-col-xs12">
           <label class="layui-form-label">届次名称</label>
          <div class="layui-input-block editWidth">
              <select name="classCode" lay-verify="required" id="claSelect">
                  
              </select>
          </div>
        </div>
        </shiro:hasPermission>
        <shiro:hasPermission name="/yroa-a/holiday/release">
        <div class="magb15 layui-col-md4 layui-col-xs12">
           <label class="layui-form-label">是否发布</label>
          <div class="layui-input-block editWidth">
              <select name="status" lay-verify="required" id="statusSelect">
                  
              </select>
          </div>
        </div>
        </shiro:hasPermission>
        <div class="layui-form-item layui-row layui-col-xs12">
	        <label class="layui-form-label">备注</label>
	        <div class="layui-input-block">
	            <textarea placeholder="请输入注意事项" class="layui-textarea userDesc" name="info"></textarea>
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
<script type="text/javascript" src="<%=request.getContextPath() %>/js/attendance/holidayAdd.js"></script>
</body>
</html>