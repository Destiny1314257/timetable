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
    <title>个人信息修改</title>
    <link rel="stylesheet" type="text/css" href="/css/course.css"/>
    <link rel="stylesheet" type="text/css" href="/css/font-awesome.min.css"/>
    <script src="/jquery-3.5.1/jquery-3.5.1.min.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript">
        function validate() {
            var $password=$('.password').val();
            var $password2=$('.password2').val();
            if($password!=$password2){
                alert("两次输入的密码应该相同");
                return false;
            }
            return true;
        }
    </script>
    <%
        User usr=(User)application.getAttribute("user");
    %>
</head>
<body>
<div class="add">
    <form action="/TeacherUpdateServlet" method="post" onsubmit="return validate()">
        <table class="tableadd" style="width: 50%;">
            <tr>
                <td>密码：</td>
                <td><input type="password" name="password" class="password"></td>
            </tr>
            <tr>
                <td>再次输入密码：</td>
                <td><input type="password" name="password2" class="password2"></td>
            </tr>
            <tr>
                <td>姓名：</td>
                <td><input type="text" name="name" value="${user.userName}"></td>
            </tr>
            <tr>
                <td>所在班级编号：</td>
                <td><input type="text" name="teamId" value="${user.teamId}"></td>
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
