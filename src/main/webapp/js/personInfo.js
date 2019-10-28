$(function(){
	$("#saveButton").click(function(){
		 var password = $("#password").val();
		 console.log(password);
		 var userName = $("#userName").val();
		 console.log(userName);
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
		 document.forms[0].submit();
		 alert("保存成功！");
	});
});
