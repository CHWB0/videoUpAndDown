$(function(){
	$("#saveButton").click(function(){
		 var file = $("#file").val();
		 var pictureFile = $("#pictureFile").val();
		 var shipin = $("#shipin").val();
		 
		 if($.trim(file) == ""){
			 $("#result").text("未选择视频文件！");
			 return;
		 }
		 if($.trim(pictureFile) == ""){
			 $("#result").text("未选择视频封面！");
			 return;
		 }
		 if($.trim(shipin) == ""){
			 $("#result").text("请选择存储地址！");
			 return;
		 }
		 document.forms[0].submit();
		 //alert("上传成功！");
	});
});