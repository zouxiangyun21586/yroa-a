<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>---</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="css/public.css" media="all" />
	<style>
	  .lay-ext-mulitsel .layui-input.multiple a{background-color:#5FB878 !important;}
	</style>
</head>
<body class="childrenBody">
	<blockquote class="layui-elem-quote layui-bg-green">
		<div id="nowTime"></div>
	</blockquote>
	<div class="layui-row layui-col-space10 panel_box">
		<div class="panel layui-col-xs12 layui-col-sm6 layui-col-md4 layui-col-lg2">
			<a href="javascript:;">
				<div class="panel_icon layui-bg-cyan">
					<i class="layui-anim layui-icon" data-icon="&#xe857;">&#xe857;</i>
				</div>
				<div class="panel_word outIcons">
					<span></span>
					<em>外部图标</em>
					<cite class="layui-hide">图标管理</cite>
				</div>
			</a>
		</div>
		<div class="panel layui-col-xs12 layui-col-sm6 layui-col-md4 layui-col-lg2">
			<a href="javascript:;">
				<div class="panel_icon layui-bg-blue">
					<i class="layui-anim seraph icon-clock"></i>
				</div>
				<div class="panel_word">
					<span class="loginTime"></span>
					<cite>上次登录时间</cite>
				</div>
			</a>
		</div>
	</div>
	<div class="layui-row layui-col-space10">
		<div class="layui-col-lg6 layui-col-md12">
			<blockquote class="layui-elem-quote title">系统基本参数</blockquote>
			<table class="layui-table magt0">
				<colgroup><col width="150"><col></colgroup>
				<tbody>
				<tr>
					<td>当前版本</td>
					<td class="version"></td>
				</tr>
				<tr>
					<td>开发作者</td>
					<td class="author"></td>
				</tr>
				<tr>
					<td>网站首页</td>
					<td class="homePage"></td>
				</tr>
				<tr>
					<td>服务器环境</td>
					<td class="server"></td>
				</tr>
				<tr>
					<td>数据库版本</td>
					<td class="dataBase"></td>
				</tr>
				<tr>
					<td>最大上传限制</td>
					<td class="maxUpload"></td>
				</tr>
				<tr>
					<td>当前用户权限</td>
					<td class="userRights"></td>
				</tr>
				</tbody>
			</table>
		</div>
		<div class="layui-col-lg6 layui-col-md12">
			<blockquote class="layui-elem-quote title">Echarts</blockquote>
			<div class="layui-elem-quote layui-quote-nm history_box magb0" id="main"></div>
		</div>
		<div class="layui-row layui-col-space12">
			<blockquote class="layui-elem-quote title">select级联/多选</blockquote>
			<div class="layui-elem-quote layui-quote-nm history_box magb0">
				<form class="layui-form">
				  <div class="layui-form-item">
						<label class="layui-form-label">级联下拉</label>
						<div id="cat_ids">			
			      </div><button type="button" class="layui-btn layui-btn-normal set">点击选择</button>
				  </div>
				  <div class="layui-form-item">
						<label class="layui-form-label">多选下拉框</label>
						<div class="layui-input-block" id="tag_ids" style="width:200px;">
			      </div>
				  </div>
				  <div class="layui-form-item" style="text-align:center;">
					<div class="layui-input-block">
						<button type="button" class="layui-btn" lay-submit="" lay-filter="demo">打印到控制台</button>
					    <button type="reset" class="layui-btn layui-btn-primary">重置</button></div>
				  </div>
				</form>
			</div>
		</div>
		<div class="layui-col-lg6 layui-col-md12">
			<blockquote class="layui-elem-quote title">树菜单</blockquote>
			<div class="layui-elem-quote layui-quote-nm history_box magb0">
				<form class="layui-form">
					<div id="treeTest" style="background-color: #fff; padding: 10px 0 25px 5px;height:200px;overflow:auto;"></div>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="layui/layui.js"></script>
	<script type="text/javascript" src="js/main.js"></script> 
</body>
</html>