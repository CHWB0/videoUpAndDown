<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/common/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath %>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>视频查询页</title>
<style type="text/css">
	td{
		text-overflow: ellipsis; 
		overflow: hidden; 
		white-space: nowrap; 

	}
	.video{
		float:left;
	}
</style>
</head>
<body oncontextmenu = "return false">
	<div align="center" style="margin-top:50px">
	    <!-- 查询条件部分 -->
		<div>
			<form action="admin/doVideoListByDate" method="post">
				<input type="date" name="uploadTime" style="height:25px; width:320px"/>
				<input type="submit" value="视频搜索">
			</form>
		</div>
	   <!-- 查询结果部分 -->
	   <div style="margin-top:60px">
			<div class="panel-heading">
				<h1 class="sub-header h3">搜索结果</h1>
			</div>
			<hr>
			<!-- 描述：播放器-->
	        <div class="video" align="center" style="width:50%;height:450px;">
	         	<video id="video" controls="controls" controlsList="nodownload" οncοntextmenu="return false" preload="auto" width="530px" height="350px" >
				  	<source src="${contextPath }/${fileEntity[0].path}" type="video/mp4" />
				</video>
	        </div>
			<div class="video" style="width:50%;height:450px;">
				 <table class="table table-hover" style="table-layout:fixed;">
						<tr>
							<th>文件名称</th>
							<th width="80px">文件大小</th>
							<th width="80px">文件类型</th>
							<th width="80px">上传时间</th>
							<th>操作</th>
						</tr>
					<c:if test="${empty fileEntity  }">
						<tr>
							<td colspan="5" align="center"><font color="red">当天没有上传视频</font></td>
						</tr>
					</c:if>
					<c:if test="${not empty fileEntity }">
					<c:forEach var="video" items="${fileEntity}" >
						<tr>
							<td>${video.titleOrig}</td>
							<td>${video.size}</td>
							<td>${video.type}</td>
							<td>${video.uploadTime}</td>
							<td>
								<button onclick="play('${video.path}','${video.fileId }','${video.playingAmount }')">播放</button>&nbsp;&nbsp;
							</td>
						</tr>
					</c:forEach>
					</c:if>
				</table> 
			</div>
	</div>
</div>
</body>
<script type="text/javascript" src="${contextPath}/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
	function play(path,id,amount){
		var url =  path;
		//console.log(url);
		$("#video").attr("src",url);
		$("#video", parent.document.body).attr("autoplay","autoplay");
		var fileId = id;
		$.ajax({
			url:"${pageContext.request.contextPath}/video/updateVideoPlayingAmount",
			type:"post",
			data:{"fileId":fileId},
			success:function(data){
				$("#"+fileId).text("点击量：" + data);
				console.log($("#amount").text());
			}
		});
		$.ajax({
			url:"${pageContext.request.contextPath}/user/insertUvRelation",
			type:"post",
			data:{"fileId":fileId},
			success:function(data){
				//console.log(data);
			}
		});
	}
</script>
<script type="text/javascript">
//删除功能
function del(obj) {
    var res = confirm('确认要删除吗？');
    if(res == true) {
        $(obj).parents("tr").remove();
    }
}
</script>
</html>