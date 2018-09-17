<%--
  Created by IntelliJ IDEA.
  User: LYW
  Date: 2018/9/14
  Time: 14:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--图片上传必须用 enctype="multipart/form-data"--%>
<%--get请求有长度限制，随意必须用post--%>
<form action="" enctype="multipart/form-data" method="post">
    文件上传：<input type="file" name="upload"/>
    <input type="submit" value="上传"/>
</form>
</body>
</html>
