<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>签到</title>
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
<form class="layui-form" style="width:80%;" id = "AttendanceForm">
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="magb15 layui-col-md4 layui-col-xs12">
           <label class="layui-form-label">签到人员</label>
          <div class="layui-input-block editWidth">
              <select name="studentCode" lay-verify="required" id="atteSelect">
                  
              </select>
          </div>
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-sm" lay-submit="" lay-filter="attendance">签到</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="<%=request.getContextPath() %>/layui/layui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/attendance/signIn.js"></script>
</body>
</html>