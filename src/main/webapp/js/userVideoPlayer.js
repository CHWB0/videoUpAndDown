

function play0(){
    var file = document.getElementById('v_file').files[0];
    var url = URL.createObjectURL(file);
    console.log(url);
    $("#video").attr("src",url);
    $("#video").attr("autoplay","autoplay");
    //$("#video").setAttribute("autoplay",autoplay);
//    $("#v_file").hide();
}

function play2(path){
	var url = "contextPath/" + path;
	$("video").attr("src",url);
}

$(function(){
	$("#videoIframe").css("display","none");
	$("#historyInf").click(function(){
		$("#videoIframe").css("display","none");
		$("#historyIframe").css("display","block");
		doSelectClass(this);
		$.ajax({
			url:"videoPlayingHistory0",
			type : "post",    //请求类型
			dataType : "json",//返回的数据类型
			contentType : "application/json;charset=utf-8",
			success: function (result) {
			//成功处理
			//console.log(result);  //在控制台打印服务器端返回的数据
			//在此处将返回的数据注入到你的页面中
			var tb = $("#historyIframe").contents().find("#tb");
			tb.empty();
			for (i in result){
				var videoName = result[i].titleOrig;
				var uploadTime = new Date(result[i].uploadTime).toLocaleString();
				var tr = "<tr><td width=\"150px\">"+videoName+"</td><td align=\"center\">"+uploadTime+"</td><td align=\"center\"><button id=\"button\" onclick=\"play('"+result[i].path+"','"+result[i].fileId+"','"+result[i].playingAmount+"')\">播放</button></td></tr>"
				tb.append(tr);
				}
			},
			error: function (result) {
				//错误处理
				alert("=====");
			}
		});
	});
	$("#videoInf").click(function(){
		$("#videoIframe").css("display","block");
		$("#historyIframe").css("display","none");
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
