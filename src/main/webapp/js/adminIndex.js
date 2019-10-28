$(function(){
	$("#userIframe").css("display","none");
	$("#videoIframe").css("display","none");
	$("#personIframe").css("display","none");
	
	$("#uploadInf").click(function(){
		$("#uploadIframe").css("display","block");
		$("#userIframe").css("display","none");
		$("#videoIframe").css("display","none");
		$("#personIframe").css("display","none");
		doSelectClass(this);
	});
	
	$("#userInf").click(function(){
		$("#uploadIframe").css("display","none");
		$("#userIframe").css("display","block");
		$("#videoIframe").css("display","none");
		$("#personIframe").css("display","none");
		doSelectClass(this);
	});

	$("#videoInf").click(function(){
		$("#uploadIframe").css("display","none");
		$("#userIframe").css("display","none");
		$("#videoIframe").css("display","block");
		$("#personIframe").css("display","none");
		doSelectClass(this);
	});

	$("#personInf").click(function(){
		$("#uploadIframe").css("display","none");
		$("#userIframe").css("display","none");
		$("#videoIframe").css("display","none");
		$("#personIframe").css("display","block");
		doSelectClass(this);
	});
	
	//修改样式
	function doSelectClass(obj){
		$(".leftBody input").each(function(i){
			$(this).css("color","#000");
			$(this).css("background-color","#dbdbdb");
			
		});
		$(obj).css("color","#fff");
		$(obj).css("font-weight","bold");
		$(obj).css("background-color","#209e85");
	}
});