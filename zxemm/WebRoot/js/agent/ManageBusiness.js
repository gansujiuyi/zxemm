var resPath;
var imgIndex;
var editor1;
var editor2;
$(document).ready(function(){
	resPath = $("#resPath").val();//显示图片的路径
	imgIndex = $("#imgIndex").val();//图片编号，直接从后台获取
	if(imgIndex == null){
		imgIndex = 0;
	}else{
		imgIndex++;
	}

	//绑定上传图片
	uploadTools("#uploadOutBluePrintImg", CarZhuTuImgSuccessFunc);
});
/**
 * 上传经纪人logo图后的回调
 * @param file
 * @param rs
 * @param resp
 */
function CarZhuTuImgSuccessFunc(file, rs, resp){

	var imgcount = 1;
	var imgLeftover = imgcount - $("#OutBlueImgUl").children().length;

	if(imgLeftover!=0){
		var tag = makeHtml(rs,"#OutBlueImgUl");
	
		if(tag){
			var imgLeftover_1 = imgcount - $("#OutBlueImgUl").children().length;
			//$("#InnerImgLeft").html(imgLeftover_1);
			//当图片数量足够时，直接弹窗提示，并禁用按钮
			if(imgLeftover_1 == 0){
				alert("最多只能传" + imgcount + "张logo图，" +"您已经传了" + imgcount + "张，无法再传了！");
				$("#uploadOutBluePrintImg").uploadify('disable', true);
			}
			imgIndex ++;
		}
  }
}
/**
 * 上传图片完成后回显图片
 * @param rs 服务器返回的报文
 * @param ulName 图片显示的控件位置
 * @returns {Boolean}
 */
function makeHtml(rs, ulName){
	var data = eval('(' + rs + ')');
	if(data.msg == null || data.msg == ""){
		var html = "<li id='imgli'>";
		html += "<div style='float:left'><img src='" + resPath + "/" + 
					data.fileSaveFullName + "' width='118' height='110'/></div>";
		html += "<div class='upload_link'><a href=\"javascript:setAsIndexImg;\"></a>";
	    html +=	"<a href=\"javascript:delUnSavedImg();\">删除</a></div>";
		html += "<input type='hidden' name='agencyInfo.logo' value='" + data.fileSaveFullName + "'/>";
		html += "</li>";
		$(ulName).append(html);		
		return true;
	}else{
		alert(data.msg);
		return false;
	}
}


/**
 * 删除还未保存的图片
 * @param obj this
 */
function delUnSavedImg(){
	$("#imgli").remove();
}

