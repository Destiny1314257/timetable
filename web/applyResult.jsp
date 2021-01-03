<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2021/1/2
  Time: 22:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>申请结果</title>
</head>
<body>
    ${application.getAttribute("str")}
    <button type="button" class="edit" onclick="window.location.href='/index.jsp'">
        <i class="fa fa-arrow-left"></i>
        返回
    </button>
</body>
</html>
