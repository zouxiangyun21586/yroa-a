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
							<input type="text" class="layui-input searchDetailsVal" placeholder="根据学生名搜索"
							onkeydown="if(event.keyCode==13){document.getElementById('selectuser').click();return false;}"/>
						</div>
						<a class="layui-btn search_details" data-type="reload" id="selectuser">搜索</a>
					</div>
				</div>
			</form>
		</blockquote>
		<table id="sous" lay-filter="sous">
		</table>
	</form>
</body>
<script type="text/javascript" src="<%=request.getContextPath() %>/layui/layui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/curriculum/curriculumDetails.js"></script>
</html> 