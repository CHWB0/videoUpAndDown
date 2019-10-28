<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>  
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户信息修改页</title>
<script type="text/javascript" src="${contextPath}/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="${contextPath}/js/personInfo.js"></script>
<link rel="stylesheet" href="${contextPath}/css/personInfo.css" type="text/css" />
<style type="text/css">
	table{
		margin-top:60px;
		background-color:#ffffff;
		width:600px;
		height:300px;
	}
</style>
</head>
<body>
  <div align="center" style="max-width: 95%;margin: 0px auto;background-color:#EFF2F6;height:555px;">
	<form action="user/doUpateUser" method="post">
		<div align="center">
		<h1>用户信息</h1>
	</div>
	<hr>
		<fieldset style="width:600px;height:450px;margin-left:150px;margin-top:20px;background-color:#ffffff;">
		<div align="center" style="margin-top:60px">
			<table>
				<tr>
					<td>账号 </td>
					<td>
						<input type="hidden" name="uId" value="${user.uId }" />
						<input type="hidden"  name="uNo" id="uNo" value="${user.uNo}" />
						<span>${user.uNo }</span>
					</td>
				</tr>
				<tr>
					<td>用户名 </td>
					<td><input type="text" name="userName" id="userName" value="${user.userName}" /></td>
				</tr>
				<tr>
					<td>密码</td>
					<td><input type="password" name="password" id="password" value="${user.password}" /></td>
				</tr>
				<tr>
					<td>确认密码</td>
					<td><input type="password"  id="confirmPassword" value="${user.password}" /></td>
				</tr>
				<tr>
					<td>用户类型  </td>
					<td>
						<span>${user.uType}</span>
			  			<input type="hidden" name="uType" id="uType" value="${user.uType }" />
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input class="saveButton" type="button" id="saveButton" value="保存" onclick="doSave()"/></td>
				</tr>
				<tr>
					<td colspan="2"><div id="result"></div></td>
				</tr>
			</table>
		</div>
		</fieldset>
	</form>
  </div>
</body>
</html>