<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath() %>/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/public.css" media="all" />
<title>考勤列表</title>
<style>
.layui-table-cell .layui-form-checkbox[lay-skin=primary], .layui-table-cell .layui-form-radio[lay-skin=primary]{top:5px;}
</style>
</head>
<body class="childrenBody">
    <form class="layui-form">
        <blockquote class="layui-elem-quote quoteBox">
            <form class="layui-form">
                <div class="demoTable">
                    <div class="layui-inline">
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input searchVal" placeholder="根据学生姓名搜索"
                            onkeydown="if(event.keyCode==13){document.getElementById('selectuser').click();return false;}"/>
                        </div>
                        <div class="layui-input-inline">
                            <select name="checkTimeCode" id="checkTimeCode">
                                
                           </select>
                        </div>
                        <div class="layui-input-inline">
                            <select name="stauts" id="stauts">
                                
                           </select>
                        </div>
                        <a class="layui-btn search_btn" data-type="reload" id="selectuser">搜索</a>
                    </div>
                    <shiro:hasPermission name="/yroa-a/attendance/attendanceAdd">
	                    <div class="layui-inline">
	                        <a class="layui-btn layui-btn-normal addUser_btn">添加考勤</a>
	                    </div>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="/yroa-a/attendance/testExcel">
                        <div class="layui-inline">
                            <a class="layui-btn layui-btn-normal testExcel_btn" href="/yroa-a/attendance/testExcel">导出Excel</a>
                        </div>
                    </shiro:hasPermission>
                </div>
            </form>
        </blockquote>
        <table id="demo" lay-filter="demo"></table>
    </form>
</body>
<script type="text/javascript" src="<%=request.getContextPath() %>/layui/layui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/attendance/attendanceList.js"></script>
<script type="text/html" id="barDemo">
<div class="layui-dropdown">
      <button type="button" class="layui-btn layui-btn-xs layui-btn-primary" data-toggle="dropdown">操作 <span class="layui-icon" style="font-size: 14px"></span></button>
      <ul class="layui-dropdown-menu">
          <shiro:hasPermission name="/yroa-a/attendance/get"><li><a lay-event="view"><i class="layui-icon" style="font-size:18px;">&#xe615; </i>查看</a></li></shiro:hasPermission>
      </ul>
  </div>
</script>
</html> 