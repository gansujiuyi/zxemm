//=======start 弹出框==========
var isIe=(document.all)?true:false; 

//设置select的可见状态 
function setSelectState(state) { 
	var objl=document.getElementsByTagName('select'); 
	for(var i=0;i<objl.length;i++){ 
		objl[i].style.visibility=state; 
	} 
} 

function mousePosition(ev) {
	if(ev) {
		if(ev.pageX || ev.pageY) {
			return {x:ev.pageX, y:ev.pageY}; 
		} 
		return { 
			x:ev.clientX + document.body.scrollLeft - document.body.clientLeft,y:ev.clientY + document.body.scrollTop - document.body.clientTop 
		}; 
	}
	else {
		return { 
			//x:document.body.clientWidth / 2 + document.body.scrollLeft+150,y:document.body.clientHeight / 2 + document.body.scrollTop
			x:document.documentElement.clientWidth / 2 + document.documentElement.scrollLeft+150,y:document.documentElement.clientHeight / 2 + document.documentElement.scrollTop
		};
	}
} 


//关闭窗口 
function closeWindow() { 
	if(document.getElementById('back')!=null){ 
		document.getElementById('back').parentNode.removeChild(document.getElementById('back')); 
	} 
	if(document.getElementById('mesWindow')!=null){ 
		document.getElementById('mesWindow').parentNode.removeChild(document.getElementById('mesWindow')); 
	} 
	if(isIe){ 
		setSelectState('');
	} 
}



//弹出窗口 弹出位置页面中间
function MessageBoxOnMid(title,content){
	var objPos = mousePosition(null); 
	messContent="<div style='padding:20px 0 20px 0;text-align:center'>"+content+"</div>"; 
	showMessageBox(title,messContent,objPos,350); 
}



/**
 * 图片上传的公共弹出框
 * @author chenchao 991722899@qq.com
 * @date 2014-3-10 下午2:48:21
 * @param params
 */
function ShowImgDialog(params){
	var arrStr="";
	arrStr+="<iframe name='uploadPic' id='uploadPic' style='display: none' src=''></iframe>";
	arrStr+="<form method='post' onsubmit='return isImg(this);' target='uploadPic' enctype='multipart/form-data' action='"+params['upurl']+"'>";
	arrStr+="<div id='J_tabs' class='dialog d-loadImg'>";
	arrStr+="<div class='bg-dialog'></div>";
	arrStr+="<div class='d-RuleHead'>";
    arrStr+="<ul>";
    arrStr+="<li class='current'>本地上传</li>";
    arrStr+="</ul>";
    arrStr+="<a  title='关闭' onclick='closeWindow();' class='close'>关闭</a>";
    arrStr+="</div>";
    arrStr+="<div class='dialogCon'>";
    //本地上传
  	arrStr+="<div class='d-loadImgCon current'>";
    arrStr+="<div class='cf'>";
    arrStr+="<div class='loadCon cf'>";
    arrStr+="<input type='text' class='txt' id='filevalue'/>";
	arrStr+="<input type='button' class='btn btn-table-01' value='浏览'/>";
	arrStr+="<input type='file' class='liul' name='uploadfile' id='file_info' onchange=\"document.getElementById('filevalue').value=this.value\"/>";
    arrStr+="<a class='btn btn-search' id='btnsub' style='background-color:#ff8c3c;border:none;'>上传</a>";
    arrStr+="</div>";
    arrStr+="</div>";
    arrStr+="<span class='info'>支持JPG、PNG、GIF图片类型，不能大于2MB。</span>";
    arrStr+="</div>";
    arrStr+="</div>";
    //表单信息
    for(pro in params){
    	arrStr+="<input type='hidden' name='"+pro+"' value='"+params[pro]+"'/>";
    }
    arrStr+="<input type='submit' value='提交' style='display:none;' id='btn_sub'/>";
    arrStr+="</form>";
    var objPos = mousePosition(params.ev); 
	var messContent="<div style='padding:0 0 0 0;text-align:center'>"+arrStr+"</div>"; 
	showMessageBox('选择图片',messContent,objPos,350);  
}



function isImg(f){
    if ($.trim($("#file_info").val()) == "") {
			alert("图片不能为空!");
		return false;
	}	
	var pattern = /\.wmv$|\.swf$|\.docx$|\.GIF$|\.JPEG$|\.JPG$|\.BMP$|\.PNG$/i;
	if (!pattern.test($.trim($("#file_info").val()))) {
		alert("图片必须为GIF、JPEG、JPG、PNG、BMP");
		return false;
	}
	
	var fileObj = document.getElementById("file_info");
	
	var imageName = fileObj.value.substring(fileObj.value.lastIndexOf("\\") + 1,fileObj.value.lastIndexOf("."));
	
	var namePattern = new RegExp("^[a-zA-Z0-9\u4e00-\u9fa5]+$");
	
	if(!namePattern.test(imageName)){
	    alert("图片名称不合法!");
	    return false;
	}
	return true;
}


/**
 * 弹出框初始化
 * @author chenchao 991722899@qq.com
 * @date 2014-8-13 上午10:24:32
 * @param wTitle 标题 
 * @param content 内容
 * @param pos 鼠标对象
 * @param wWidth 宽度
 */
function showMessageBox(wTitle,content,pos,wWidth) { 
	closeWindow(); 
	var bWidth=parseInt(document.documentElement.scrollWidth); 
	var bHeight=parseInt(document.documentElement.scrollHeight); 
	if(isIe){ 
		setSelectState('hidden');
	} 
	var back=document.createElement("div"); 
	back.id="back"; 
	var styleStr="top:0px;left:0px;position:absolute;background:#ffff;width:"+bWidth+"px;height:"+bHeight+"px;z-index:1000;"; 
	styleStr+=(isIe)?"filter:alpha(opacity=0);":"opacity:0;"; 
	back.style.cssText=styleStr; 
	document.body.appendChild(back); 
	showBackground(back,50); 
	
	var mesW=document.createElement("div"); 
	mesW.id="mesWindow"; 
	styleStr = "z-index:1000;";
	mesW.innerHTML=content; 
	var top = $(window).scrollTop()+($(window).height()/2)-140;
	styleStr+="left:"+(bWidth/2 - 300 )+"px;top:"+top+"px; position:absolute;width:590px;";
	mesW.style.cssText=styleStr;
	document.body.appendChild(mesW);
	//上传事件
	$("#btnsub").click(function(){
		$("#btn_sub").trigger("click");
	});
} 



//lsq
//让背景渐渐变暗 
function showBackground(obj,endInt) { 
	if(isIe){ 
	obj.filters.alpha.opacity+=1; 
	if(obj.filters.alpha.opacity<endInt) { 
		setTimeout(function(){showBackground(obj,endInt);},5); 
	} 
	}else{ 
		var al=parseFloat(obj.style.opacity);al+=0.01; 
		obj.style.opacity=al; 
		if(al<(endInt/100)){
			setTimeout(function(){showBackground(obj,endInt);},5);
		} 
	} 
} 