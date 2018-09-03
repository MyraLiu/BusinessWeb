<%--
  Created by IntelliJ IDEA.
  User: LYW
  Date: 2018/8/31
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Product Page</title>
</head>
<body>
<h1>${user.username}商品列表</h1>
<c:forEach items="${productlist}" var="p">
    商品id  ${p.id}-----> 商品名称${p.name}<br/>
</c:forEach>
</body>
</html>
