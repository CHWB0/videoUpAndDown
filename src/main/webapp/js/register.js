$(function(){
	$("#saveButton").click(function(){
		 var password = $("#password").val();
		 var userName = $("#userName").val();
		 var uNo = $("#uNo").val();
		 var uType = $("#uType").val();
		 var confirmPassword = $("#confirmPassword").val();
		 if($.trim(password) == ""){
			 $("#result").text("密码不能为空！");
			 return;
		 }
		 if($.trim(confirmPassword) == ""){
			 $("#result").text("确认密码不能为空！");
			 return;
		 }
		 if($.trim(userName) == ""){
			 $("#result").text("用户名不能为空！");
			 return;
		 }
		 if($.trim(uNo) == ""){
			 $("#result").text("用户账号不能为空！");
			 return;
		 }
		 if($.trim(uType) == ""){
			 $("#result").text("用户类型不能为空！");
			 return;
		 }
		 if($.trim(confirmPassword) != $.trim(password)){
			 $("#result").text("两次密码不一致！");
			 return;
		 }
//		 document.forms[0].submit();
//		 alert("保存成功！");
		 console.log(uNo);
		 console.log(password);
		 $.ajax({
	        	url:"user/userChecking",
	            type:"post",
	            data:{"uNo":uNo},
	            dataType:"json",//告诉后台我前台需要的是JSON格式的数据
	            success:function (result) {
	                if (result==0){
	                	document.forms[0].submit();//document.forms[0]：表示获取当前页面的第一个表单提交到form里面的 action指向的地方
	           		 	alert("注册成功！");
	                }else if(result==1){
	                    alert("该账号已被注册，请使用其他账号！");
	                }
	            }
	        });
	});
});
