<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath() %>/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/public.css" media="all" />
<title>当天考勤报告</title>
<style>
.layui-table-cell .layui-form-checkbox[lay-skin=primary], .layui-table-cell .layui-form-radio[lay-skin=primary]{top:5px;}
</style>
</head>
<body class="childrenBody">
    <!-- <form class="layui-form">
        <table id="demo" lay-filter="demo"></table>
    </form> -->
    <div class="layui-col-lg6 layui-col-md12">
            <blockquote class="layui-elem-quote title">今日考勤情况</blockquote>
            <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
              <ul class="layui-tab-title">
                <li class="layui-this">上午考勤报告</li>
                <li>下午考勤报告</li>
                <li>晚上考勤报告</li>
              </ul>
              <div class="layui-tab-content" style="height: 100px;">
                <div class="layui-tab-item layui-show">
                    <table id="demo" lay-filter="demo"></table>
                </div>
                <div class="layui-tab-item">
                    <table id="demo1" lay-filter="demo1"></table>
                </div>
                <div class="layui-tab-item">
                    <table id="demo2" lay-filter="demo2"></table>
                </div>
              </div>
            </div> 
        </div>
</body>
<script type="text/javascript" src="<%=request.getContextPath() %>/layui/layui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/attendance/attendanceReport.js"></script>
</html> 