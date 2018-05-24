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
							<input type="text" class="layui-input searchVal" placeholder="根据届次搜索"
							onkeydown="if(event.keyCode==13){document.getElementById('selectuser').click();return false;}"/>
						</div>
						<a class="layui-btn search_btn" data-type="reload" id="selectuser" href="<%=request.getContextPath() %>/clas/year?" + year>搜索</a>
					</div>
					<div class="layui-inline">
						<a class="layui-btn layui-btn-normal addUser_btn">添加届次</a>
					</div>
				</div>
			</form>
		</blockquote>
		<table id="demo" lay-filter="demo"></table>
	</form>
</body>
<script type="text/javascript" src="<%=request.getContextPath() %>/layui/layui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/curriculum/curriculumList.js"></script>
<script type="text/html" id="barDemo">
<div class="layui-dropdown">
      <button type="button" class="layui-btn layui-btn-xs layui-btn-primary" data-toggle="dropdown">操作 <span class="layui-icon" style="font-size: 14px"></span></button>
      <ul class="layui-dropdown-menu">
		  <li><a lay-event="edit"><i class="layui-icon" style="font-size:18px;">&#xe642; </i>编辑</a></li>
		  <li><a lay-event="edit"><i class="layui-icon" style="font-size:18px;">&#xe642; </i>开课</a></li>
		  <li><a lay-event="edit"><i class="layui-icon" style="font-size:18px;">&#xe642; </i>毕业</a></li>
		  <li><a lay-event="edit"><i class="layui-icon" style="font-size:18px;">&#xe642; </i>进度</a></li>
		  <li><a lay-event="edit"><i class="layui-icon" style="font-size:18px;">&#xe642; </i>详情</a></li>
		  <li><a lay-event="del"><i class="layui-icon" style="font-size:18px;">&#xe640; </i>删除用户</a></li>
      </ul>
  </div>
</script>
<script type="text/javascript">
	$(document).ready(function(){
		$.ajax({
			type: "get",  // 请求方式(post或get)
			async:false,  //默认true(异步请求),设置为false(同步请求)
			url:"<%=request.getContextPath() %>/clas", // 发送请求的地址
			scriptCharset: 'utf-8',
			dataType:"json",
			success:function(c){
				//$("#tch").empty(); // 每次执行前都清空数据
				//var resource = eval("("+a+")"); // 将传过来的值转为json格式
				alert(c);
				link = "";
				
				for(var i in c){
					link = "<tr><td>"+c[i].id+"</td>"
				}
				
			},error:function(XMLHttpRequest, textStatus, errorThrown){
				alert(XMLHttpRequest.status);
				alert(XMLHttpRequest.readyState);
				alert(textStatus);
	        }
		});
	});
</script>
</html> 