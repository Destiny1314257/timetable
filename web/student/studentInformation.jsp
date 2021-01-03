<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<meta charset="utf-8">
		<link rel="stylesheet" type="text/css" href="/css/course.css"/>
		<link rel="stylesheet" type="text/css" href="/css/font-awesome.min.css"/>
		<script src="/jquery-3.5.1/jquery-3.5.1.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="/jquery-3.5.1/jquery-3.5.1.min.js" type="text/javascript" charset="utf-8"></script>
		<title>个人信息</title>
	</head>
	<body>
		<table class="tablelist">
			<thead>
				<tr>
					<th>学生编号</th>
					<th>账号</th>
					<th>姓名</th>
					<th>性别</th>
					<th>所在班级</th>
				</tr>
			</thead>
			<c:if test="${not empty applicationScope.user}">
				<tr>
					<td>${applicationScope.user.userId}</td>
					<td>${applicationScope.user.userAccount}</td>
					<td>${applicationScope.user.userName}</td>
					<td>${applicationScope.user.userSex}</td>
					<td>${applicationScope.user.teamId}</td>
				</tr>
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
