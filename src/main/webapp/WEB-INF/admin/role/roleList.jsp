<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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
				    <shiro:hasPermission name="/yroa-a/role/queryfy">
						<div class="layui-inline">
							<div class="layui-input-inline">
								<input type="text" class="layui-input searchVal" placeholder="根据角色名搜索"
								onkeydown="if(event.keyCode==13){document.getElementById('selectuser').click();return false;}"/>
							</div>
							<a class="layui-btn search_btn" data-type="reload" id="selectuser">搜索</a>
						</div>
					</shiro:hasPermission>
					<shiro:hasPermission name="/yroa-a/role/add">
						<div class="layui-inline">
							<a class="layui-btn layui-btn-normal addUser_btn">添加角色</a>
						</div>
					</shiro:hasPermission>
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
		  <shiro:hasPermission name="/yroa-a/role/switchs"><li><a lay-event="state"><i class="layui-icon" style="font-size:18px;">&#xe612; </i>{{d.use==0?'禁用角色':'启用角色'}}</a></li></shiro:hasPermission>
		  <shiro:hasPermission name="/yroa-a/role/upda"><li><a lay-event="edit"><i class="layui-icon" style="font-size:18px;">&#xe642; </i>编辑</a></li></shiro:hasPermission>
		  <shiro:hasPermission name="/yroa-a/role/roleEmpowerment"><li><a lay-event="fuAuth"><i class="layui-icon" style="font-size:18px;">&#xe672; </i>赋权</a></li></shiro:hasPermission>
		  <shiro:hasPermission name="/yroa-a/auth/lookResource"><li><a lay-event="lookAuth"><i class="layui-icon" style="font-size:18px;">&#xe672; </i>查看权限</a></li></shiro:hasPermission>
          <shiro:hasPermission name="/yroa-a/role/del"><li><a lay-event="del"><i class="layui-icon" style="font-size:18px;">&#xe640; </i>删除角色</a></li></shiro:hasPermission>
      </ul>
  </div>
</script>
<!--<li><a lay-event="edit"><i class="layui-icon" style="font-size:18px;">&#xe642; </i>编辑</a></li>  -->
</html> 