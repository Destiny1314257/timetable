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
    <%
        Course course=(Course) application.getAttribute("course");
    %>
</head>
<body>
<div class="add">
    <form action="/CourseManageServlet?method=add" method="post">
        <table class="tableadd" style="width: 50%;">
            <tr>
                <td>课程编号：</td>
                <td><input type="text" name="courseId" required="required"></td>
            </tr>
            <tr>
                <td>课程名：</td>
                <td><input type="text" name="courseName"></td>
            </tr>
            <tr>
                <td>课时：</td>
                <td><input type="text" name="courseHour"></td>
            </tr>
            <tr>
                <td>上课时间：</td>
                <td><input type="text" name="courseTime"></td>
            </tr>
            <tr>
                <td>上课老师：</td>
                <td><input type="text" name="userId"></td>
            </tr>
            <tr>
                <td>上课班级：</td>
                <td><input type="text" name="teamId"></td>
            </tr>
            <tr>
                <td>上课教室：</td>
                <td><input type="text" name="classroomId"></td>
            </tr>
            <tr>
                <td>教室名：</td>
                <td><input type="text" name="classroomName"></td>
            </tr>
            <tr>
                <td>教室类型：</td>
                <td><input type="text" name="classroomType"></td>
            </tr>
            <tr>
                <td>教室容量：</td>
                <td><input type="text" name="classroomCapacity"></td>
            </tr>
            <tr>
                <td colspan="10" align="center">
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
