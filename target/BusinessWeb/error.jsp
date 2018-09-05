<%--
  Created by IntelliJ IDEA.
  User: LYW
  Date: 2018/9/4
  Time: 10:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ERROR</title>
</head>
<body>
<h1>异常处理页面</h1>
<%--获取异常信息--%>
<h1>${ex.msg}</h1>
<span id="warn">${ex.warn}</span>
<script>
    <%--页面渲染完成后回调onload方法--%>
    window.onload=function () {
        //计时变量
        var counter=3;
        //每秒刷新
        window.setInterval(function () {
            //计数器自减
            counter--;
            //减到0时跳转至新页面并清除计时
            if(counter==0){
                window.location.href="${ex.url}";
                window.clearInterval();
            }
            document.getElementById("warn").innerHTML=counter+"s后页面跳转";
        },1000)
    }
</script>
</body>
</html>
