<%@ page import="com.tang.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
	<head>
		<meta charset="utf-8">
		<title>学生</title>
		<link rel="stylesheet" type="text/css" href="/css/course.css"/>
		<link rel="stylesheet" type="text/css" href="/css/font-awesome.min.css"/>
		<script src="/jquery-3.5.1/jquery-3.5.1.min.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			$(function(){
				$(".menux p").click(function(){
					//$(this).siblings('ul').slideUp(200);
					$(this).next('ul').slideToggle(200);
				});
				$(".menux ul a").click(function(){
					$('iframe').attr("src",$(this).attr("url"));
					$('.menu_title').html($(this).attr("title"));
				});
			})
		</script>
		<%
			User usr=(User)application.getAttribute("user");
		%>
	</head>
	<body>
		<div class="header">
			<div class="logo">排课系统</div>
			<div class="user">
				<i class="fa fa-caret-down point"></i>
				<i class="fa fa-user"></i>
				学生
				<ul>
					<li><a href="/index.jsp">退出登录</a></li>
				</ul>
			</div>
		</div>
		<div class="left">
			<div class="title">
				<i class="fa fa-home"></i>
				系统功能</div>
			<div class="menux">
				<p>
					<i class="fa fa-info-circle"></i>
					<i class="fa fa-angle-right point"></i>
					学生功能
				</p>
				<ul>
					<li>
						<a href="javascript:void(0);" url="/student/courselist.jsp" title="个人课表">
							<i class="fa fa-caret-right"></i>
							个人课表
						</a>
						<a href="javascript:void(0);" url="/student/studentInformation.jsp" title="个人信息">
							<i class="fa fa-caret-right"></i>
							个人信息
						</a>
						<a href="javascript:void(0);" url="/student/studentUpdate.jsp" title="修改个人信息">
							<i class="fa fa-caret-right"></i>
							修改个人信息
						</a>
					</li>
				</ul>
			</div>
		</div>
		<div class="main">
			<div class="location">
				<span>当前在线人数为：${applicationScope.onlineUserNum}</span>
				<i class="fa fa-home"></i>
				<span class="menu_title">调课通知：${applicationScope.str}</span>
			</div>
			<iframe src="/student/courselist.jsp" width="100%" height="90%" frameborder="0"></iframe>
		</div>
	</body>
</html>
