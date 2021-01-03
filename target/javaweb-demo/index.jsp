<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
  <meta charset="utf-8">
  <title>首页</title>
  <link rel="stylesheet" type="text/css" href="/css/index.css"/>
</head>
<body>
<div class="login_frame">
  <form method="post" action="LoginServlet">
    <div>
      <p class="logo">欢迎来到排课系统！</p>
    </div>
    <div class="span1">
      <p><label class="label_input" style="color: white;">用户名:</label></p>
      <input type="text" id="account" name="account" class="text_field" placeholder="请输入你的账号"/>
    </div>
    <div class="span2">
      <p><label class="label_input" style="color: white;">密码:</label></p>
      <input type="password" id="password" name="password" class="text_field" placeholder="请输入你的密码"/>
    </div>
    <span class="remember">
              <input checked="checked" class="tn-checkbox" value="true" type="checkbox">
              <label style="color: white; font-size: 14px;" > 记住密码</label>
        </span>
    <div id="login_control">
      <input type="submit" id="btn" value="登录"/>
      <a id="btn" href="register.jsp">注册</a>
      <a id="forget_pwd" href="forget_pwd.html">忘记密码？</a>
    </div>
  </form>
</div>

</body>
</html>
