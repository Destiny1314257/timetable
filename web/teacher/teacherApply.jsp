<%@ page import="com.tang.model.User" %><%--
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
    <title>申请调课</title>
    <link rel="stylesheet" type="text/css" href="/css/course.css"/>
    <link rel="stylesheet" type="text/css" href="/css/font-awesome.min.css"/>
    <script src="/jquery-3.5.1/jquery-3.5.1.min.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
<div class="add">
    <form action="/ApplyServlet" method="post">
        <table class="tableadd" style="width: 50%;">
            <tr>
                <td>要调课的课程编号：</td>
                <td><input type="text" name="courseId"></td>
            </tr>
            <tr>
                <td>新的上课时间：</td>
                <td><input type="text" name="newTime"></td>
            </tr>
            <tr>
                <td>新的上课教室编号：</td>
                <td><input type="text" name="newClassroom"></td>
            </tr>
            <tr>
                <td>新的上课教室：</td>
                <td><input type="text" name="newClassroomName"></td>
            </tr>
            <tr>
                <td colspan="4" align="center">
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
