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
<title>上传视频结果</title>
<style type="text/css">
	table{
		width:100%
		margin-top:5px;
		font-size:12px;
	}
	.hidden{
		text-overflow: ellipsis; 
		overflow: hidden; 
		white-space: nowrap; 

	}
</style>
</head>
<body>
	<div>
			<div align="center">
				<h1>视频信息</h1>
			</div>
			<div>${result}</div>
			<hr>
			<div>
				 <table width="1020px" style="table-layout:fixed;">
						<tr>
							<th>文件名称</th>
							<th>视频封面</th>
							<th width="80px">文件大小</th>
							<th width="80px">文件类型</th>
							<th width="80px">文件路径</th>
							<th width="80px">上传时间</th>
							<th>操作</th>
						</tr>
					<c:if test="${empty entity }">
						<tr>
							<td>还没有上传视频</td>
						</tr>
					</c:if>
					<c:if test="${not empty entity }">
						<c:forEach var="list" items="${entity}" >
						<tr>
							<td class="hidden">${list.titleOrig}</td>
							<td class="hidden">${list.picturePath}</td>
							<td align="center">${list.size}</td>
							<td align="center">${list.type}</td>
							<td class="hidden">${list.path}</td>
							<td align="center">${list.uploadTime}</td>
							<td align="center">
								<button onclick="play('${list.path}')">播放</button>&nbsp;&nbsp;
								<a href="video/videoUpdate?fileId=${list.fileId}&currentPage=${page.currentPage}">修改</a>&nbsp;&nbsp;
								<a href="video/videoDelete?fileId=${list.fileId}&currentPage=${page.currentPage}" onclick="del(this)">删除</a>
							</td>
						</tr>
						</c:forEach>
						<tr>
							<td colspan="9">
							<span>共${page.totalDataCount}条记录,共${page.totalPage }页</span> 
							<a href="video/videoList/1">首页</a>
								<c:if test="${page.currentPage!=1}">
									<a href="video/videoList/${page.currentPage-1}">上一页</a>
								</c:if>
								<c:if test="${page.currentPage==1}">
									<a href="javascript:void(0)">上一页</a>
								</c:if> 
								<c:if test="${page.currentPage!=page.totalPage}">
									<a href="video/videoList/${page.currentPage+1}">下一页</a>
								</c:if>
								<span>第${page.currentPage }页</span>
								<c:if test="${page.currentPage==page.totalPage}">
									<a href="javascript:void(0)">下一页</a>
								</c:if> <a href="video/videoList/${page.totalPage}">尾页</a>
							</td>
						</tr>
					</c:if>
				</table> 
			</div>
			<!-- 描述：播放器-->
	        <div hidden="hidden" class="video" id="div" align="center" style="width:50%;height:450px;">
	         	<video id="video" controls="controls" controlsList="nodownload" οncοntextmenu="return false" preload="auto" width="530px" height="350px" >
				  	<source src="${contextPath }/${entity[0].path}" type="video/mp4" />
				</video>
	        </div>
	</div>
</body>
<script type="text/javascript" src="${contextPath}/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
	var div = document.getElementById("div");
	function play(path) {
		var url =  path;
		//console.log(url);
		$("#video").attr("src",url);
		$("#video").attr("autoplay","autoplay");
		div.style.display = "block";
	};
	var vid = document.getElementById("video");
	vid.onpause = function() {
		div.style.display = "none";
	};
</script>
<script>
   //删除功能
   function del(obj) {
       var res = confirm('确认要删除吗？');
       if(res == true) {
           $(obj).parents("tr").remove();
       }
   }
</script>

</html>