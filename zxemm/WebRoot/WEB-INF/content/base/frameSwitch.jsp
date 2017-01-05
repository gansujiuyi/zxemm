<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/content/base/tag.inc" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/common.css" type="text/css" />
<title></title>
</HEAD>
<script language="JavaScript">
function Submit_onclick(){
	if(parent.myFrame.cols == "199,7,*") {
		parent.myFrame.cols="0,7,*";
		//window.frames.mainFrame.getElementById("mainzone").style.width=1000px;
		document.getElementById("ImgArrow").src="<%=request.getContextPath() %>/images/switch_right.gif";
		document.getElementById("ImgArrow").title="显示导航栏";
	} else {
		parent.myFrame.cols="199,7,*";
		document.getElementById("ImgArrow").src="<%=request.getContextPath() %>/images/switch_left.gif";
		document.getElementById("ImgArrow").title="隐藏导航栏";
	}
}

function MyLoad() {
	if(window.parent.location.href.indexOf("MainUrl")>0) {
		window.top.midFrame.document.getElementById("ImgArrow").src="<%=request.getContextPath() %>/images/switch_right.gif";
	}
}
</script>
<body onload="MyLoad()">
<div id="switchpic"><a href="javascript:Submit_onclick()"><img src="<%=request.getContextPath() %>/images/switch_left.gif" title="隐藏导航栏" id="ImgArrow" /></a></div>
</body>
</html>
