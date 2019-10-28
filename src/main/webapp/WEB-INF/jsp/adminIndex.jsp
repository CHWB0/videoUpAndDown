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
	<base href="<%=basePath%>" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>管理员菜单页面</title>
	<link rel="stylesheet" href="css/adminIndex.css" type="text/css" /><!-- 必须在下面代码的前面加载 -->
	<script type="text/javascript" src="${contextPath}/js/jquery-1.12.4.min.js"></script>
	<script type="text/javascript" src="${contextPath}/js/adminIndex.js"></script>
	<script type="text/javascript" src="${contextPath}/js/pcAndPhone.js"></script>
	<style type="text/css">
		span{
			font-size:26px;
			color:blue;
		}
		.userName{
			color:red;
		}
	</style>
	
</head>
<body>
<div id="headerDiv">
	<!-- 头部 -->
		<div class="head">
			<div class="head_logo" style="text-align: left;">
				<img src="${contextPath }/img/logo2.jpg" width="100px" height="80px" />
			</div>
			<div class="head_logo" id="users">
				<span>52player播放器欢迎<span class="userName">${user.userName }</span> 用户</span>
			</div>
			<div class="head_logo" id="logout" >
				<a href="admin/showAdminIndex"><font size="5">首页 |</font></a>&emsp;
				<a href="showIndex"><font size="5">退出 |</font></a>&emsp;
				<a href="#"><font size="5">帮助 |</font></a>&emsp;
				<a href="#"><font size="5" color="#000000">联系我们 </font></a>&emsp;
			</div>
		</div>
		<!-- 清除浮动 -->
			<div style="clear: both;height: 0px;"></div>
		<!-- 下侧部分-->
		<div class="body">
			<div class="leftBody">
				<!-- 左侧 -->
				<div class="uploadInf">
					<input id="uploadInf" type="button" value="上传视频">
				</div>
				<div class="userInf">
					<input id="userInf" type="button" value="用户信息">
				</div>
				<div class="videoInf">
					<input id="videoInf" type="button" value="视频信息">
				</div>
				<div class="personInf">
					<input id="personInf" type="button" value="个人信息">
				</div>
			</div>
			<div class="rightBody">	
				<iframe src="${contextPath}/video/videoUpload" frameBorder="0" scrolling="no" id="uploadIframe"></iframe>	
				<iframe src="${contextPath}/user/userList/1" frameBorder="0" scrolling="no" id="userIframe"></iframe>
				<iframe src="${contextPath}/video/videoList/1" frameBorder="0" scrolling="no" id="videoIframe"></iframe>
				<iframe src="${contextPath }/admin/personInfo" frameBorder="0" scrolling="no" id="personIframe"></iframe>
			</div>
	    </div>
	    <div class="foot"></div>
</div>
</body>
</html>