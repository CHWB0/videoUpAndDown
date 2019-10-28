<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Heder-logo</title>
<link rel="stylesheet" href="${contextPath }/css/commen.css" type="text/css" />
</head>
<body>
	<div class="header" style="height:90px; background-color:#1ABC9C">
		<div class="logo" style="text-align: left;line-height: 90px;width:33%">
			<img src="${contextPath }/img/logo2.jpg" width="100px" height="90px"/>
		</div>
		<div class="logo" style="text-align: left;line-height: 40px;width:33%">
			<h2 style="font-size:26px; color:blue">欢迎使用52Player播放器</h2>
		</div>
		<div class="logo" style="text-align: right;line-height: 100px;width:33%">
			<a href="${contextPath }/user/showUserIndex"><font size="5">首页 |</font></a>&emsp;
			<a href="#"><font size="5">帮助 |</font></a>&emsp;
			<a href="#"><font size="5" color="#000000">联系我们</font></a>
		</div>
	</div>
</body>
</html>