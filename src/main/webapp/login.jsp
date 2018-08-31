<%--
  Created by IntelliJ IDEA.
  User: LYW
  Date: 2018/8/30
  Time: 10:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>欢迎登录${username}</h1>
<%--<form method="post" action="/hello">时跳转到http://localhost:8080/hello绝对路径--%>
<%--<form method="post" action="hello">时跳转到http://localhost:8080/BusinessWeb/hello相对路径--%>
<form method="post" action="hello">
    <input name="username" type="text">
    <input type="submit">

</form>

</body>
</html>
