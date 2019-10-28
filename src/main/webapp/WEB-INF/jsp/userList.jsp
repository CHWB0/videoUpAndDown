<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>  
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base href="<%=basePath %>" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>用户列表</title>
	<style type="text/css">
		table{
			margin-top:5px;
		}
	</style>
</head>
<body>
<div align="center">
	<div>
		<h1>用户信息</h1>
	</div>
	<hr>
<table cellpadding="0" cellspacing="0" width="750px">
	<thead>
		<tr>
			<th>用户ID</th>
			<th>用户账号</th>
			<th>用户姓名</th>
			<th>用户密码</th>
			<th>用户类型</th>
			<th>用户操作</th>
		</tr>
	</thead>
	<tbody>
	<c:if test="${empty user }">
		<tr>
			<td colspan="3">
				还没有用户
			</td>
		</tr>
	</c:if>
	<c:if test="${not empty user }">
		<c:forEach var="en" items="${user }">
			<tr>
				<td align="center">
					${en.uId }
				</td>
				<td align="center">
					${en.uNo}
				</td>
				<td align="center">
					${en.userName }
				</td>
				<td align="center">
					${en.password }
				</td>
				<td align="center">
					${en.uType }
				</td>
				<td align="center">
					<a href="user/userDelete?id=${en.uId }&currentPage=${page.currentPage}">删除</a>
					&nbsp;&nbsp;&nbsp;
					<a href="user/userUpdate?id=${en.uId }&currentPage=${page.currentPage}">修改</a>
				</td>
			</tr>
		</c:forEach>
	</c:if>
	<tr>
		<td colspan="9">
		<span>共${page.totalPage }页,${page.totalDataCount }条记录</span> 
		<a href="user/userList/1">首页</a>
			<c:if test="${page.currentPage!=1}">
				<a href="user/userList/${page.currentPage-1}">上一页</a>
			</c:if>
			<c:if test="${page.currentPage==1}">
				<a href="javascript:void(0)">上一页</a>
			</c:if> 
			<c:if test="${page.currentPage!=page.totalPage}">
				<a href="user/userList/${page.currentPage+1}">下一页</a>
			</c:if>
			<span>第${page.currentPage }页</span>
			<c:if test="${page.currentPage==page.totalPage}">
				<a href="javascript:void(0)">下一页</a>
			</c:if> <a href="user/userList/${page.totalPage}">尾页</a>
		</td>
	</tr>
	</tbody>
</table>
</div>
</body>
</html>