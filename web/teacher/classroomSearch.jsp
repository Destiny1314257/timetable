<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="/css/course.css"/>
    <link rel="stylesheet" type="text/css" href="/css/font-awesome.min.css"/>
    <script src="/jquery-3.5.1/jquery-3.5.1.min.js" type="text/javascript" charset="utf-8"></script>
    <title>课表</title>
</head>
<body>
<table class="tablelist">
    <thead>
    <tr>
        <th>教室编号</th>
        <th>教室名</th>
        <th>教室类型</th>
        <th>教室容量</th>
        <th>教室状态(0 空闲 1 占用)</th>
        <th>使用时间</th>
    </tr>
    </thead>
    <c:if test="${not empty applicationScope.pageBean2.list}">
        <c:forEach items="${applicationScope.pageBean2.list}" var="classroom">
            <tr>
                <td>${classroom.classroomId}</td>
                <td>${classroom.classroomName}</td>
                <td>${classroom.classroomType}</td>
                <td>${classroom.classroomCapacity}</td>
                <td>${classroom.classroomState}</td>
                <td>${classroom.classroomTime}</td>
            </tr>
        </c:forEach>
    </c:if>
    <table class="page">
        <td>
            <button type="button">首页</button>
            <button type="button">上一页</button>
            第${pageBean.currentPage}页
            <button type="button">下一页</button>
            <button type="button">尾页</button>
            <input type="text" class="page-no" name="pageNo">
            <button type="button">转</button>
        </td>
    </table>
</table>
</body>
</html>
