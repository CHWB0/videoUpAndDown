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
<title>视频信息修改页</title>
<script type="text/javascript" src="${contextPath}/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="${contextPath}/js/videoUpd.js"></script>
<style type="text/css">
	table{
		margin-top:60px;
		background-color:#ffffff;
		width:600px;
		height:300px;
	}
	#result{
		font-size: 12px;
		color: red;
		text-align: center;
}
</style>
</head>
<body>
	<div align="center"style="max-width: 95%;margin: 0px auto;background-color:#EFF2F6;height:900px;background: url(${contextPath}/img/regist_bg.jpg);">
		<form action="admin/doUpdateVideo" method="post">
			<table>
				<tr style="margin-top:50px;">
					<td colspan="2">原文件名</td>
					<td>
						<input type="hidden" name="fileId" value="${video.fileId }"/>
						<input type="text" id="titleOrig" name="titleOrig" value="${video.titleOrig }" placehoder="请输入文件名称"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">改后文件名</td>
					<td>
						<span>${video.titleAlter}</span>
						<input type="hidden" id="titleAlter" name="titleAlter" value="${video.titleAlter }" placehoder="请输入文件名称"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">视频封面</td>
					<td>
						<span>${video.picturePath}</span>
						<input type="hidden" id="picturePath" name="picturePath" value="${video.picturePath }"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">文件类型</td>
					<td><input type="text" id="type" name="type" value="${video.type }" placehoder="请输入文件类型"/></td>
				</tr>
				<tr>
					<td colspan="2">文件大小</td>
					<td>
						<span>${video.size }</span>
						<input type="hidden" id="size" name="size" value="${video.size }"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">文件路径</td>
					<td>
						<span>${video.path }</span>
						<input type="hidden" id="path" name="path" value="${video.path }"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">上传时间</td>
					<td>
						<span>${video.uploadTime }</span>
						<input type="hidden" id="uploadTime" name="uploadTime" value="${video.uploadTime }"/>
					</td>
				</tr>
				<tr align="center">
					<td align="right"><input type="button" id="saveButton" value="修改"/></td>
				</tr>
				<tr>
					<td align="center" colspan="2"><div id="result"></div></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>