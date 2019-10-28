$(function(){
	$("#saveButton").click(function(){
		 var titleOrig = $("#titleOrig").val();
		 var type = $("#type").val();
		 
		 if($.trim(titleOrig) == ""){
			 $("#result").text("视频原文件名不能为空！");
			 return;
		 }
		 if($.trim(type) == ""){
			 $("#result").text("视频类型不能为空！");
			 return;
		 }
		 document.forms[0].submit();
		 //alert("修改成功！");
	});
});