<%--
    Created by IntelliJ IDEA.
    User: Mores
    Date: 2024/5/27
    Time: 11:33
  --%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>登录</title>
  <link rel="stylesheet" href="CSS/UserLogin.css" type="text/css">
</head>
<body>
<div class="container">
  <div class="login-wrapper">
    <div class="header">登录</div>
    <div class="form-wrapper">
      <form action="/UserLogin3" method="post">
        <input type="text" name="userName" id="userName" placeholder="账号" class="input-item">
        <input type="password" name="userPassword" id="userPassword" placeholder="密码" class="input-item">
        <button class="loginButton" id="loginButton" type="submit">登录</button>
      </form>
    </div>
    <div class="msg"><a href="UserRegister.jsp">
      没有账号？
      点此注册
    </a>
    </div>
  </div>
</div>
</body>
</html>