<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ZH-cn">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Login</title>
  <link rel="stylesheet" href="<%= this.getServletContext().getContextPath() %>/layui/css/layui.css">
  <link rel="stylesheet" href="<%= this.getServletContext().getContextPath() %>/css/login.css">
</head>

<body>
  <div class="kit-login">
    <div class="kit-login-bg"></div>
    <div class="kit-login-wapper" style="right:-30%;">
      <h2 class="kit-login-slogan">欢迎使用 <br> 一容软件 后台管理模板</h2>
      <div class="kit-login-form">
        <h4 class="kit-login-title">登录</h4>
        <form class="layui-form">
          <div class="kit-login-row">
            <div class="kit-login-col">
              <i class="layui-icon">&#xe612;</i>
              <span class="kit-login-input">
				  <input type="text" name="loginName" lay-verify="required" placeholder="用户名/邮箱/手机号" maxlength="16"/>
			  </span>
            </div>
            <div class="kit-login-col"></div>
          </div>
          <div class="kit-login-row">
            <div class="kit-login-col">
              <i class="layui-icon">&#xe64c;</i>
              <span class="kit-login-input">
				  <input type="password" name="password" lay-verify="required" placeholder="密码" maxlength="16"/>
			  </span>
            </div>
            <div class="kit-login-col"></div>
          </div>
		  <div class="kit-login-row">
            <div class="kit-login-col">
              <i class="layui-icon">&#xe60d;</i>
              <span class="kit-login-input">
				  <input type="text" name="code" style="width:50%;" lay-verify="required" placeholder="验证码" maxlength="6"/>
				  <img id="Login_code_img" src="images/code.jpg" style="width:85px;float:right;" title="点击图片刷新验证码" />
			  </span>
            </div>
            <div class="kit-login-col"></div>
          </div>
          <div class="kit-login-row">
            <div class="kit-login-col">
              <input type="checkbox" name="rememberMe" value="true" title="记住帐号" lay-skin="primary">
            </div>
          </div>
          <div class="kit-login-row">
            <button class="layui-btn kit-login-btn" lay-submit="submit" lay-filter="login_hash">登录</button>
          </div>
        </form>
      </div>
    </div>
  </div>

  <script src="<%= this.getServletContext().getContextPath() %>/js/polyfill.min.js"></script>
  <script src="<%= this.getServletContext().getContextPath() %>/layui/layui.js"></script>
  <script>
    //'axios', 'lodash'
    layui.use(['layer', 'form'], function() {
      var form = layui.form,
        //axios = layui.axios,
        $ = layui.jquery;
      //_ = layui.lodash;


      //监听提交
      form.on('submit(login_hash)', function(data) {
        var layIndex = layer.load(2, {
          shade: [0.1, '#393D49']
        });
        console.log(data.field);
        setTimeout(function() {
          location.href = 'index.html';
        }, 2000);
        return false;
		//ajax请求
		//返回json格式	{"code":0,"msg":"登录成功"} code代码状态码,msg显示提示内容
		setTimeout(function(){
        	$.ajax({
    	       type:"get",
    	       url:"login",
    	       data: $('#login').serialize(),
    	       success:function(data){
    	    	   if(0==data.code){
    	    		   top.layer.msg(data.msg,{icon:1});
    	    		   layer.msg('跳转中...，请稍候',{icon: 16,time:false,shade:0.5})
    	    		   setTimeout(function(){
    	    		    	window.location.href = "index";//登录成功后的页面
    			       },1000);
    	    	   }else{
    	    		   layer.close(index);
    	    		   layer.msg(data.msg,{icon:2,time:2000,shade:0.5});
    	    		   setTimeout(function(){
    	    			   location.reload();
    	    		   },1000);
    	    	   }
    	       },error : function() {
					layer.msg("异常！",{icon:2});
    	       }
         	});
        },1000);
      });
    });
  </script>
</body>

</html>