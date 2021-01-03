<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
	<head>
		<meta charset="utf-8">
		<title>注册</title>
		<link rel="stylesheet" type="text/css" href="css/register.css"/>
		<script src="jquery-3.5.1/jquery-3.5.1.min.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			var xhr=false;
			function createXHR() {
				try {
					xhr=new XMLHttpRequest();
				}catch (e) {
					try {
						xhr=new ActiveXObject("Microsoft.XMLHTTP");
					}catch (e1) {
						xhr=false;
					}
				}
				if(!xhr)
					alert("初始化XMLHttpRequest对象失败！");
			}
			function ajaxValidate(accountObj) {
				createXHR();
				var url="/RegisterServlet";
				var content="type=accountAjaxValidate&account="+accountObj.value;
				xhr.open("POST",url,true);
				xhr.onreadystatechange=function () {
					if(xhr.readyState==4&&xhr.status==200){
						var responseObj=JSON.parse(xhr.responseText);
						alert(responseObj.reg)
					}
				};
				xhr.setRequestHeader("CONTENT-TYPE","application/x-www-form-urlencoded");
				xhr.send(content);
			}
			function validate() {
				var $account=$('.account').val();
				var $password=$('.password').val();
				var $password2=$('.password2').val();
				var pattern=/^([1-9]+\d*)|(0)$/;
				if(!pattern.test($account)){
					alert("账号不能为非数字！");
					return false;
				}
				if($account.length<4||$account.length>16){
					alert("账号应在4-16位之间");
					return false;
				}
				if($password.length<6||$password.length>16){
					alert("密码应在6-16位之间");
					return false;
				}
				if($password!=$password2){
					alert("两次输入的密码应该相同");
					return false;
				}
				return true;
			}
		</script>
	</head>
	<body>
		<div class="register_frame">	
			<form onsubmit="return validate()" action="RegisterServlet" method="get" >
				<p class="logo">用户注册</p>
				<hr>
				<div class="span1" style="margin-top: 5px;">
					<p><label class="label_input" style="color: white;">用户名:</label></p>
					<input class="account" type="text" id="accountValidate" placeholder="请填写用户名" required="required" name="account" onblur="ajaxValidate(this)">
				</div>
				<div class="span1">
					<p><label class="label_input" style="color: white;">密码:</label></p>
					<input class="password" id="paswd" type="password" placeholder="请填写密码" name="password" required="required">
				</div>
				<div class="span1">
					<p><label class="label_input" style="color: white;">确认密码:</label></p>
					<input class="password2" id="paswd2" type="password" placeholder="请确认密码" name="password2" required="required">
				</div>
				<div class="span1">
					<p><label class="label_input" style="color: white;">姓名:</label></p>
					<input class="name" type="text" placeholder="请填写姓名" name="name" required="required">
				</div>
				<div class="gender">
					<p><label class="label_input" style="color: white;">性别:</label></p>
					<div style="float: left; margin-top: 5px;margin-left: 10px;">
						<input type="radio" name="sex" value="男" checked>男
						<input type="radio" name="sex" value="女">女
					</div>
				</div>
				<div class="span1">
					<p><label class="label_input" style="color: white;">所在班级:</label></p>
					<input class="name" type="text" placeholder="（若是学生）请填写所在班级" name="team">
				</div>
				<div class="choose">
					<input id="student" type="radio" checked="checked" name="profession" value="student" />学生
					<input id="teacher" type="radio"  name="profession" value="teacher"/>老师
				</div>
				<div class="agree">
					<input type="checkbox" required="required">
					<label style="font-size: 16px;" > 我已阅读注册手册</label>
				</div>
				<div class="control">
					<input type="submit" id="button" value="注册">
					<a id="button" href="index.jsp">返回主界面</a>
				</div>
			</form>
		</div>
	</body>
</html>
