<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录页面</title>
	<style type="text/css">
		fieldset{
        	margin-top: 50px;
            width: 600px;
            height: 400px;
            background-color:#FFFFFF;
        }
        table{
        	margin-top: 50px;
            width: 400px;
            height: 300px;
        }
        td{
        	height:20px;
        }
    </style>
</head>
<body>
<div style="max-width: 95%;margin: 0px auto;background-color:#EFF2F6;background: url(${contextPath}/img/regist_bg.jpg);">
	<div align="center">
		<fieldset>
			<table>
				<tr style="height:30px;font-size:25px">
					<td colspan="2" align="center">登录52Player</td>
				</tr>
				<tr style="height:30px;font-size:20px">
					<td colspan="2" align="center">请使用注册账号登录</td>
				</tr>
				<tr>
					<td align="right">账号：</td>
					<td align="center">
						<input type="text" name="uNo" id="uNo" style="height:35px;width:80%" placeholder="账号/手机号" size="40"/>
					</td>
				</tr>
				<tr>
					<td align="right">密码：</td>
					<td align="center">
						<input type="password" name="password" id="password" style="height:35px;width:80%" placeholder="密码" size="40"/>
					</td>
				</tr>
				<tr style="background-color:#1ABC9C">
					<td colspan="2" align="center" style="height:30px;">
						<input type="button" value="登   录" id="login" style="height:30px;font-Size:20px"/>
					</td>
				</tr>
				<tr>
		        	<td colspan="2" align="center" id="info"></td>
		    	</tr>
			</table>
		</fieldset>
	</div>
  <div class="foot"></div>
</div>
</body>

    <script type="text/javascript" src="${contextPath}/js/jquery-1.12.4.min.js"></script>
	<script type="text/javascript">
	  $("#login").click(function () {
	        var uNo = $("#uNo").val();
	        var password = $("#password").val();
	        var data = {'uNo':uNo,'password':password,};
	        $.ajax({
	        	url:"${pageContext.request.contextPath}/user/login",
	            type:"post",
	            data:JSON.stringify(data),
	            dataType:"json",
	            contentType:"application/json;charset=utf-8",
	            success:function (result) {
	                if (result==1){
	                	 alert("管理员登录成功！");
	                     location.href="${pageContext.request.contextPath}/admin/showAdminIndex";
	                }else if(result==2){
	                    alert("用户登录成功！");
	                    location.href="${pageContext.request.contextPath}/user/showUserIndex";
	                }else if (result==3){
	                    $("#info").css('color','green');
	                    $("#info").html("用户名或密码错误");
	                }
	            }
	        });
	    });
	</script>
</html>