<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ZH-cn">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Login</title>
  <link rel="stylesheet" href="<%=request.getContextPath() %>/layui/css/layui.css">
  <link rel="stylesheet" href="<%=request.getContextPath() %>/css/login.css">
</head>

<body>
  <div class="kit-login">
    <div class="kit-login-bg"></div>
    <div class="kit-login-wapper" style="right:-30%;">
      <h2 class="kit-login-slogan">欢迎使用 <br> 一容软件 后台管理模板</h2>
      <div class="kit-login-form">
        <h4 class="kit-login-title">登录</h4>
        <form class="layui-form" id="login">
          <div class="kit-login-row">
            <div class="kit-login-col">
              <i class="layui-icon">&#xe612;</i>
              <span class="kit-login-input">
                  <input type="text" name="userName" lay-verify="required" placeholder="账号/手机号" maxlength="16"/>
              </span>
            </div>
            <div class="kit-login-col"></div>
          </div>
          <div class="kit-login-row">
            <div class="kit-login-col">
              <i class="layui-icon">&#xe64c;</i>
              <span class="kit-login-input">
                  <input type="password" name="passWord" lay-verify="required" placeholder="密码" maxlength="16"/>
              </span>
            </div>
            <div class="kit-login-col"></div>
          </div>
          <div class="kit-login-row">
            <div class="kit-login-col">
              <i class="layui-icon">&#xe60d;</i>
              <span class="kit-login-input">
                  <input type="text" name="yanZhengMa" style="width:50%;" lay-verify="required" placeholder="验证码" maxlength="6"/>
                  <img id="Login_code_img" src="<%=request.getContextPath() %>/ImageServlet" onclick="changeValidateCode();" style="width:85px;float:right;" title="点击图片刷新验证码" />
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
            <button class="layui-btn kit-login-btn" lay-submit="submit" lay-filter="login" id="denglu">登录</button>
          </div>
        </form>
      </div>
    </div>
  </div>

  <script src="<%=request.getContextPath() %>/js/polyfill.min.js"></script>
  <script src="<%=request.getContextPath() %>/layui/layui.js"></script>
</body>
<script>
layui.use(['form','layer','jquery','element'],function(){
    var form = layui.form,
        element = layui.element,
        layer = parent.layer === undefined ? layui.layer : top.layer
        $ = layui.jquery;

    //登录按钮
    form.on("submit(login)",function(data){
        var index=layer.msg('登录中...，请稍候',{icon: 16,time:false,shade:0.5});
        setTimeout(function(){
            $.ajax({
               type:"post",
               url:"log/loginYan",
               data: $('#login').serialize(),
               success:function(data){
                   if(0==data.code){
                       top.layer.msg(data.msg,{icon:1});
                       layer.msg('跳转中...，请稍候',{icon: 16,time:false,shade:0.5})
                       setTimeout(function(){
                            window.location.href = "index";
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
        return false;
    });
});
/**
 *刷新验证码
 */
function changeValidateCode() {
    var obj = $('#Login_code_img');
    var timenow = new Date().getTime();
    var url = "/yroa-a/ImageServlet?d=" + timenow;
    $(obj).attr("src", url);
}

</script>
</html>