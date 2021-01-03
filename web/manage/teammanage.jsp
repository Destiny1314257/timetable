<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="/css/course.css"/>
    <link rel="stylesheet" type="text/css" href="/css/font-awesome.min.css"/>
    <script src="/jquery-3.5.1/jquery-3.5.1.min.js" type="text/javascript" charset="utf-8"></script>
    <title>班级管理</title>
</head>
<body>
<form action="/TeamOperationServlet?method=search" method="post">
    <div class="condition">
        班级编号：<input type="text" name="teamId">
        <button type="submit">
            <i class="fa fa-search"></i>
            搜索
        </button>
        <button type="button" onclick="window.location.href='/manage/teamAdd.jsp'">
            <i class="fa fa-plus"></i>
            新增
        </button>
    </div>
    <table class="tablelist">
        <thead>
        <tr>
            <th>班级编号</th>
            <th>班级名</th>
            <th>班级人数</th>
            <th>操作</th>
        </tr>
        </thead>
        <c:if test="${not empty applicationScope.pageBeanTeam.list}">
            <c:forEach items="${applicationScope.pageBeanTeam.list}" var="team">
                <tr>
                    <td>${team.teamId}</td>
                    <td>${team.teamName}</td>
                    <td>${team.teamNumber}</td>
                    <td>
                        <button type="button" class="edit" onclick="window.location.href='/manage/teamUpdate.jsp'">
                            <i class="fa fa-edit"></i>
                            修改
                        </button>
                    </td>
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
</form>
</body>
</html>
