<%@ page import="com.tang.model.User" %>
<%@ page import="com.tang.model.Course" %>
<%@ page import="com.tang.model.Team" %>
<%@ page import="com.tang.model.ClassRoom" %><%--
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
    <title>教室信息修改</title>
    <link rel="stylesheet" type="text/css" href="/css/course.css"/>
    <link rel="stylesheet" type="text/css" href="/css/font-awesome.min.css"/>
    <script src="/jquery-3.5.1/jquery-3.5.1.min.js" type="text/javascript" charset="utf-8"></script>
    <%
        ClassRoom classroom=(ClassRoom) application.getAttribute("classroom");
    %>
</head>
<body>
<div class="add">
    <form action="/ClassroomOperationServlet?method=update" method="post">
        <table class="tableadd" style="width: 50%;">
            <tr>
                <td>教室编号：</td>
                <td><input type="text" name="classroomId" required="required" value="${classroom.classroomId}"></td>
            </tr>
            <tr>
                <td>教室名：</td>
                <td><input type="text" name="classroomName" value="${classroom.classroomId}"></td>
            </tr>
            <tr>
                <td>教室类型：</td>
                <td><input type="text" name="classroomType" value="${classroom.classroomType}"></td>
            </tr>
            <tr>
                <td>教室容量：</td>
                <td><input type="text" name="classroomCapacity" value="${classroom.classroomCapacity}"></td>
            </tr>
            <tr>
                <td>教室状态：</td>
                <td><input type="text" name="classroomState" value="${classroom.classroomState}"></td>
            </tr>
            <tr>
                <td>使用时间：</td>
                <td><input type="text" name="classroomTime" value="${classroom.classroomTime}"></td>
            </tr>
            <tr>
                <td colspan="6" align="center">
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
