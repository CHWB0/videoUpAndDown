<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>  
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="multipart/form-data; charset=utf-8" />
<title>Insert title here</title>
<script type="text/javascript" src="${contextPath}/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="${contextPath}/js/videoUp.js"></script>
<style type="text/css">
	#result{
		font-size: 12px;
		color: red;
		text-align: center;
}
</style>
</head>
<body>
	<form method="post" action="upload_video" enctype="multipart/form-data">
	<div align="center">
		<div align="center">
			<h2>上传视频</h1>
		</div>
		<hr>
		<fieldset style="margin-top:5px;height:300px;width:800px"> 
		<div class="row-fluid">${result}</div>   
		<div class="form-group" align="center" style="margin-top:60px">
			<table>
				<tr>
					<td><input type="text" value="文件上传" size="8"></td>
					<td><input type="file" class="form-control" name="file" id="file"/></td>
				</tr>
				<tr>
					<td><input type="text" value="上传视屏封面" size="8"></td>
					<td><input type="file" name="pictureFile" id="pictureFile"/></td>
				</tr>
				<tr>
					<td><input type="text" value="选择存储地址" size="8"></td>
					<td>
						<select name="shipin" id="shipin">
							<option value="/video/">video视频文件</option>
				  		</select>
					</td>
				</tr>
				<tr>
					<td align="center" colspan="2"><input type="button" id="saveButton" value="上传" size="4"/></td>
				</tr>
				<tr>
					<td align="center" colspan="2"><div id="result"></div></td>
				</tr>
			</table>
		</div>
		</fieldset> 
		</div>      
	</form>
</body>
</html>