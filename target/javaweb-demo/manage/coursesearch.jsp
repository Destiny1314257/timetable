<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="/css/course.css"/>
    <link rel="stylesheet" type="text/css" href="/css/font-awesome.min.css"/>
    <script src="/jquery-3.5.1/jquery-3.5.1.min.js" type="text/javascript" charset="utf-8"></script>
    <title>查询课程</title>
</head>
<body>
    <form action="/CourseManageServlet?method=delete" method="post">
        <div class="condition">
            课程编号：<input type="text" name="courseId">
            <button type="submit" class="remove">
                <i class="fa fa-remove"></i>
                删除
            </button>
        </div>
        <table class="tablelist">
            <thead>
            <tr>
                <th>课程编号</th>
                <th>课程名</th>
                <th>课时</th>
                <th>授课教师</th>
                <th>上课时间</th>
                <th>上课班级编号</th>
                <th>上课教室</th>
            </tr>
            </thead>
            <c:if test="${not empty applicationScope.courseList}">
                <c:forEach items="${applicationScope.courseList}" var="course">
                    <tr>
                        <td>${course.courseId}</td>
                        <td>${course.courseName}</td>
                        <td>${course.courseHour}</td>
                        <td>${course.userId}</td>
                        <td>${course.courseTime}</td>
                        <td>${course.teamId}</td>
                        <td>${course.classroomId}</td>
                    </tr>
                </c:forEach>
            </c:if>
            <table class="page">
                <td>
                    <button type="button">首页</button>
                    <button type="button">上一页</button>
                    <button type="button">下一页</button>
                    <button type="button">尾页</button>
                    <input type="text" class="page-no" name="pageNo">
                    <button type="button">转</button>
                </td>
            </table>
        </table>
    </form>
</body>
</html>
