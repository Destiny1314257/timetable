<%@ page import="com.tang.model.User" %>
<%@ page import="com.tang.model.Course" %><%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2020/12/31
  Time: 9:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>个人信息修改</title>
    <link rel="stylesheet" type="text/css" href="/css/course.css"/>
    <link rel="stylesheet" type="text/css" href="/css/font-awesome.min.css"/>
    <script src="/jquery-3.5.1/jquery-3.5.1.min.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
<div class="add">
    <form action="/TeamOperationServlet?method=add" method="post">
        <table class="tableadd" style="width: 50%;">
            <tr>
                <td>班级编号：</td>
                <td><input type="text" name="teamId" required="required"></td>
            </tr>
            <tr>
                <td>班级名：</td>
                <td><input type="text" name="teamName"></td>
            </tr>
            <tr>
                <td>班级人数：</td>
                <td><input type="text" name="teamNumber"></td>
            </tr>
            <tr>
                <td colspan="3" align="center">
                    <button type="submit" class="remove">
                        <i class="fa fa-save"></i>
                        提交
                    </button>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
