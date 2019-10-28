
function play(path,id){
	var url =  path;
	//console.log(url);
	$("#video").attr("src",url);
	$("#video").attr("autoplay","autoplay");
	var fileId = id;
	$.ajax({
		url:"video/updateVideoPlayingAmount",
		type:"post",
		data:{"fileId":fileId},
		success:function(data){
			//console.log("111"+data);
			$("#"+fileId).text("点击量：" + data);
			console.log($("#amount").text());
		}
});
	
/*	另外一种异步请求获取参数方式
 * var getAmountUrl = "video/getVideoPlayingAmount/" + fileId;
	$.ajax({
		url:getAmountUrl,
		type : "post",    //请求类型
		dataType : "json",//返回的数据类型
		contentType : "application/json;charset=utf-8",//发送信息至服务器时内容编码类型
		success:function(data){
			console.log("111"+data);
			$("#"+fileId).text("点击量：" + data);
			console.log($("#amount").text());
		}
	});*/
}

function play0(){
    var file = document.getElementById('v_file').files[0];
    var url = URL.createObjectURL(file);
    console.log(url);
    $("#video").attr("src",url);
    $("#video").attr("autoplay","autoplay");
//    $("#v_file").hide();
}

function play2(path){
	var url = "contextPath/" + path;
	$("video").attr("src",url);
	$("#video").attr("autoplay","autoplay");
}

$(function(){
	$("#historyIframe").css("display","none");
	$("#videoInf").click(function(){
		$("#videoIframe").css("display","block");
		$("#historyIframe").css("display","none");
		doSelectClass(this);
	});
	$("#historyInf").click(function(){
		$("#videoIframe").css("display","none");
		$("#historyIframe").css("display","block");
		doSelectClass(this);
	});
	//修改样式
	function doSelectClass(obj){
		$(".videoFile input").each(function(i){
			$(this).css("color","#000");
			$(this).css("background-color","#dbdbdb");
		});
		$(obj).css("color","#fff");
		$(obj).css("font-weight","bold");
		$(obj).css("background-color","#209e85");
	}
});
