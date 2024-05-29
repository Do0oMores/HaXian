<%--
    Created by IntelliJ IDEA.
    User: Mores
    Date: 2024/5/28
    Time: 9:42
  --%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>注册</title>
  <link rel="stylesheet" href="./CSS/UserRegister.css" type="text/css">
</head>
<body>
<div class="container">
  <div class="register-wrapper">
    <div class="header">注册您的新账号</div>
    <div class="form-wrapper">
      <form action="/register" method="post">
        <input type="text" name="registerUsername" id="registerUsername" placeholder="用户名" class="input-item">
        <input type="text" name="registerPhoneNumber" id="registerPhoneNumber" placeholder="账号" class="input-item">
        <input type="text" name="registerPassword" id="registerPassword" placeholder="密码" class="input-item">
        <button class="btn" id="btn" type="submit">注册</button>
      </form>
    </div>
    <div class="msg"><a href="UserLogin.jsp">
      点此返回登录
    </a>
    </div>
  </div>
</div>
<%

%>
</body>
</html>