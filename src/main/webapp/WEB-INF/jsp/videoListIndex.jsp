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
	<title>播放记录页</title>
	<script type="text/javascript" src="${contextPath}/js/jquery-2.1.0.js"></script>
	<style type="text/css">
		table{
			font-size:12px;
		}
		table tr{
			height:20px;
		}
		td{
			overflow: hidden;
			text-overflow:ellipsis;
			white-space: nowrap;
		}
	</style>
</head>
<body>
	<div class="tab" align="center">
		 <table width="95%" style="table-layout:fixed">
				<tr>
					<th width="150px">文件名称</th>
					<th width="80px">上传时间</th>
					<th width="80px">操作</th>
				</tr>
			<c:forEach var="video" items="${entity}" >
				<tr>
					<td align="center">${video.titleOrig}</td>
					<td align="center">${video.uploadTime}</td>
					<td align="center">
						<button onclick="play('${video.path}','${video.fileId }','${video.playingAmount }')">播放</button>
					</td>
				</tr>
			</c:forEach>
		</table> 
	</div>
</body>
<script type="text/javascript">
function play(path,id,amount){
	var url = '${contextPath}/'+path;
	//console.log(url);
	$("#video", parent.document.body).attr("src",url);   //子页面调用父页面
	$("#video", parent.document.body).attr("autoplay","autoplay");
	//$("#video", parent.document.body).setAttribte("autoplay","autoplay");
	var fileId = id;
	$.ajax({
		url:"${pageContext.request.contextPath}/video/updateVideoPlayingAmount",
		type:"post",
		data:{"fileId":fileId},
		success:function(data){
			$("#"+fileId, parent.document.body).text("点击量：" + data);
			//console.log($("#amount").text());
		}
	}); 
}
</script>
</html>