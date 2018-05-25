<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath() %>/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/public.css" media="all" />
<title>Spting mvc-user</title>
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
							<input type="text" class="layui-input searchVal" placeholder="根据账号搜索"
							onkeydown="if(event.keyCode==13){document.getElementById('selectuser').click();return false;}"/>
						</div>
						<a class="layui-btn search_btn" data-type="reload" id="selectuser">搜索</a>
					</div>
					<div class="layui-inline">
						<a class="layui-btn layui-btn-normal addUser_btn">添加用户</a>
					</div>
				</div>
			</form>
		</blockquote>
		<table id="demo" lay-filter="demo"></table>
	</form>
</body>
<script type="text/javascript" src="<%=request.getContextPath() %>/layui/layui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/roleJs/roleList.js"></script>
<script type="text/html" id="barDemo">
<div class="layui-dropdown">
      <button type="button" class="layui-btn layui-btn-xs layui-btn-primary" data-toggle="dropdown">操作 <span class="layui-icon" style="font-size: 14px"></span></button>
      <ul class="layui-dropdown-menu">
		  <<li><a lay-event="state"><i class="layui-icon" style="font-size:18px;">&#xe612; </i>{{d.status==0?'禁用账号':'启用用户'}}</a></li>
		  <li><a lay-event="edit"><i class="layui-icon" style="font-size:18px;">&#xe642; </i>编辑</a></li>
          <li><a lay-event="resetPassword"><i class="layui-icon" style="font-size:18px;">&#xe640; </i>重置密码</a></li>
      </ul>
  </div>
</script>
<!--<li><a lay-event="edit"><i class="layui-icon" style="font-size:18px;">&#xe642; </i>编辑</a></li>  -->
</html> 