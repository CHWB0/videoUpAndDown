<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="multipart/form-data; charset=utf-8" />
	<title></title>
	<link rel="stylesheet" href="${contextPath }/css/index.css" type="text/css" />
	<script type="text/javascript" src="${contextPath}/js/jquery-2.1.0.js"></script>
	<script type="text/javascript" src="${contextPath}/js/videoPlayer.js"></script>
    <script>  
		function load(){  
		    document.getElementById("videoInf").click();
		    $("#button").click();
		    
		}
    </script>
    <style type="text/css">
    	.videoPicture{
			float:left;
			width:20%;
			height:155px;
			text-align:center;
		}
		.videoImg{
			float:left;
			width:20%;
			height:125px;
			text-align:center;
		    box-shadow: 1px 1px 1px rgba(0,0,0,0.3);  
		}
		.vMessage{
			float:left;
			width:30%;
			height:125px;
			text-align:center;
		    box-shadow: 1px 1px 1px rgba(0,0,0,0.3);  
		}
		td{
			overflow: hidden;
			text-overflow:ellipsis;
			white-space: nowrap;
		}
		/* .vPGroup:hover{border:1px red solid;} */
    </style>
</head>
<body onload="load()">
<div id="headerDiv" style="max-width: 95%;margin: 0px auto;">
	<!--描述：第一部分logo -->
	<div style="height: 90px;text-align: center;">
		<div class="logo" style="text-align: left;">
			<img src="${pageContext.request.contextPath }/img/logo.jpg" width="90%" height="80px"/>
		</div>
		<div class="logo">
			<form action="video/doVideoListByDate" method="post"> 
				<input type="date" name="uploadTime" size="40" style="height: 40px; width: 70%;" >	
				<input type="submit" value="视频搜索">
			</form>
		</div>
		<div class="logo">
			<a href="${contextPath }/user/showLogin"><font size="5">登录</font></a>&emsp;
			<a href="${contextPath }/user/showRegister"><font size="5">注册</font></a>&emsp;
			<a href="#"><font size="5" color="#000000">个人信息</font></a>
		</div>
	</div>
	
	<!-- 清除浮动 -->
	<div style="clear: both;"></div>
	<!-- 描述：第二部分：导航栏 -->
    <div style="background-color: black;height: 53px;">
	   	<a class="menu" href="#">首页</a>&emsp;&nbsp;
	   	<a class="menu" href="#">单词学习</a>&emsp;&nbsp;
	   	<a class="menu" href="#">阅读学习</a>&emsp;&nbsp;
	   	<a class="menu" href="#">听力学习</a>&emsp;&nbsp;
	   	<a class="menu" href="#">翻译学习</a>&emsp;&nbsp;
	   	<a class="menu" href="#">写作学习</a>
   </div>
   <div style="height:5px;width:100%"></div>
	<!-- 描述：第三部分：播放器+最近播放+打开文件视频 -->
	<div>
	    <!-- 描述：播放器-->
        <div class="videoPlayer">
         	<video id="video" controls="controls" width="730px" height="450px" >
			  	<source src="${contextPath }/${entity.path}" type="video/mp4" />
			</video>
        </div>
        <!--描述：最近播放的10个视频+新打开的文件目录 -->
        <div class="videoFile">
       		<div>
       			<input type="button" id="videoInf" value="播放记录"/>&emsp;
       			<input id="v_file" type="file" onChange="play0()"/>
       		</div>
			<div class="tab" align="center">
				<table id="tb" width="90%"  style="table-layout:fixed;font-size:12px">
						<tr>
							<th width="80px">文件名称</th>
							<th width="25px">上传时间</th>
							<th width="40px">操作</th>
						</tr>
						<tr>
							<td colspan="9">
							<span>共${page.totalPage }页</span> 
							<a href="${contextPath }/video/videoList0/1">首页</a>
								<c:if test="${page.currentPage!=1}">
									<a href="${contextPath }/video/videoList0/${page.currentPage-1}">上一页</a>
								</c:if>
								<c:if test="${page.currentPage==1}">
									<a href="javascript:void(0)">上一页</a>
								</c:if> 
								<c:if test="${page.currentPage!=page.totalPage}">
									<a href="${contextPath }/video/videoList0/${page.currentPage+1}">下一页</a>
								</c:if>
								<span>第${page.currentPage }页</span>
								<c:if test="${page.currentPage==page.totalPage}">
									<a href="javascript:void(0)">下一页</a>
								</c:if> <a href="${contextPath }/video/videoList0/${page.totalPage}">尾页</a>
							</td>
						</tr>
				</table> 
			</div> 
    	</div>
    	<!-- 第四部分：视频播放图片 -->
    	<!-- 清除浮动加一空格行 -->
   		<div style="width:100%;height:50px;clear: both;"></div>
    	<div>
    		<div>
    			<h2>热门栏目推荐<img src="${contextPath }/img/title2.jpg"></h2>
    			<hr>
    		</div>
    		<div id="dv" style="float:left;width:100%"></div>
    	</div>
    	<!-- 第五部分：最后三天上传的视频，分期显示  -->
    	<div style="max-width: 95%;margin: 0px auto;">
   		<!-- day1 -->
   		<!-- 清除浮动加一空格行 -->
   		<div style="width:100%;height:50px;clear: both;"></div>
   		<div class="group">
   			<h2>${day1}日上传视频</h2>
   			<hr>
   			<div class="dayVideo" style="float:left;width:100%">
   				<c:forEach var="day1" items="${entityDay1 }">
   					<div class="videoImg">
   						<video id="video" controls="controls" width="100%" height="125px" >
						  	<source src="${contextPath }/${day1.path}" type="video/mp4" />
						</video>
   					</div>
   					<div class="vMessage"><p>${day1.titleOrig }</p><p>${day1.uploadTime }</p><p>${day1.type }</p></div>
   				</c:forEach>
   			</div>
   		</div>
   		<!-- day2 -->
   		<div style="width:100%;height:50px;clear: both;"></div>
   		<div class="group">
   			<h2>${day2}日上传视频</h2>
   			<hr>
   			<div class="dayVideo" style="float:left;width:100%">
   				<c:forEach var="day2" items="${entityDay2 }">
   					<div class="videoImg">
   						<video id="video" controls="controls" width="100%" height="125px" >
						  	<source src="${contextPath }/${day2.path}" type="video/mp4" />
						</video>
   					</div>
   					<div class="vMessage"><p>${day2.titleOrig }</p><p>${day2.uploadTime }</p><p>${day2.type }</p></div>
   				</c:forEach>
   			</div>
   		</div>
   		<!-- day3 -->
   		<div style="width:100%;height:50px;clear: both;"></div>
   		<div class="group">
   			<h2>${day3}日上传视频</h2>
   			<hr>
   			<div class="dayVideo" style="float:left;width:100%">
   				<c:forEach var="day3" items="${entityDay3 }">
   					<div class="videoImg">
   						<video id="video" controls="controls" width="100%" height="125px" >
						  	<source src="${contextPath }/${day3.path}" type="video/mp4" />
						</video>
   					</div>
   					<div class="vMessage"><p>${day3.titleOrig }</p></p><p>${day3.uploadTime }</p><p>${day3.type }</p></div>
   				</c:forEach>
   			</div>
   		</div>
   		</div>
</div>
</body>
<script type="text/javascript" src="${contextPath}/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
$(function(){
	$("#videoInf").click(function(){
		$.ajax({
			url : "${pageContext.request.contextPath}/video/videoList1",//请求的 URL地址
			type : "post",    //请求类型
			dataType : "json",//返回的数据类型
			contentType : "application/json;charset=utf-8",//发送信息至服务器时内容编码类型
			success: function (result) {
				//成功处理
				console.log(result);  //在控制台打印服务器端返回的数据
				//在此处将返回的数据注入到你的页面中
				var tb = $("#tb");
				var dv = $("#dv");
				for (i in result){
					var videoName = result[i].titleOrig;
					var uploadTime = new Date(result[i].uploadTime).toLocaleString();
					//var uploadTime = new Date(parseInt(result[i].uploadTime)).toLocaleString('chinese',{hour12:false});
					var videoPath = result[i].path;
					console.log(videoPath);
					var picturePath = result[i].picturePath;
					var path = "${contextPath}/"+picturePath;
					console.log(path);
					var tr = "<tr><td width=\"150px\">"+videoName+"</td><td align=\"center\">"+uploadTime+"</td><td align=\"center\"><button id=\"button\" onclick=\"play('"+result[i].path+"')\">播放</button></td></tr>"
					tb.append(tr);
					//var div = '<div class="videoPicture"><img id="vp" src="'+path+'" onclick=\"paly2("'+videoPath+'")\"></img><p>'+videoName+'</p><p>'+uploadTime+'</div>'
					var div = "<div class=\"videoPicture\"><img src=\""+path+"\" onclick=\"play('"+videoPath+"')\"></img><p>"+videoName+"</p><p>"+uploadTime+"</p></div>"
					dv.append(div);
				}
			},
			error: function (result) {
				//错误处理
				alert("=====");
			}
		});
	});
});
</script> 
</html>
