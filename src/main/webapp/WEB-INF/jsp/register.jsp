<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册页</title>
<script type="text/javascript" src="${contextPath}/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="${contextPath}/js/register.js"></script>
<style type="text/css">
.login {
	color: #00F;
	font-size: 18px;
	margin-top:30px;
}

table {
	margin-top:30px;
	font-size:18px;
	width: 500px;
	height:350px; 
	align:center; 
}
fieldset{
	margin-top:40px;
	width:600px;
	height:400px;
	align:center; 
	background-color:#FFFFFF
}
input{
	height:25px;
}
td{
	align:center;
}
</style>
</head>
<body>
<div style="max-width: 95%;margin: 0px auto; background: url(${contextPath}/img/regist_bg.jpg);">
		<div class="login" align="center" >
			<form action="user/insertUser" method="post">
				<fieldset>
				<table>
					<tr style="height:30px;font-size:25px">
						<td colspan="2" align="center" style="width:100%">注册52Player</td>
					</tr>
					<tr>
						<td align="right">姓名:</td>
						<td><input type="text" name="userName" id="userName" placeholder="请输入姓名" size="40"/></td>
					</tr>
					<tr>
					<tr>
						<td align="right">账号:</td>
						<td><input type="text" name="uNo" id="uNo" placeholder="请输入账号" size="40"/></td>
					</tr>
					<tr>
						<td align="right">密码:</td>
						<td><input type="password" name="password" id="password" placeholder="请输入密码" size="40"/></td>
					</tr>
					<tr>
						<td align="right">确认密码:</td>
						<td><input type="password" id="confirmPassword" placeholder="请再次输入密码" size="40"/></td>
					</tr>
					<tr>
						<td align="right">用户类型:</td>
						<td><select name="uType"size="1" id="uType">
								<option value="2">普通用户</option>
							</select>
						</td>
					</tr>
					<tr style="background-color:#1ABC9C">
						<td colspan="2" align="center" style="height:50px;">
							<input type="button" id="saveButton" value="注册" uNo=style="height:50px;font-Size:20px" /><br>
						</td>
					</tr>
					<tr>
						<td id="result"></td>
					</tr>
				</table>
				</fieldset>
		    </form>
		</div>
  <div class="foot"></div>
</div>
</body>
</html>