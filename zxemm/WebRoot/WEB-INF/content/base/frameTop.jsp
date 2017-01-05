<%@page language="java" contentType="text/html;charset=utf-8" %>
<%@page language="java" import="com.jiuyi.jyplat.util.Constant"  %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:directive.include file="/WEB-INF/content/base/tag.inc" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><s:text name='domain.title'/>业务管理平台1.0</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />

<META http-equiv=Expires content=0>

<link rel="shortcut icon" href ="logo.ico" />
<meta http-equiv=Expires content=0 />
<link href="<%=path %>/css/common.css" rel=stylesheet />
<script language="javascript" type="text/javascript" src="<%=path %>/js/jquery-1.6.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$(".header_content").css("backgroundImage","url(<%=path %>/images/frameTop_bg_zx_.jpg)");
	});
	//显示时间
	var timerID = null;
	var timerRunning = false;
	function stopclock (){
		if(timerRunning)
		clearTimeout(timerID);
		timerRunning = false;
	}
	function startclock (){
		stopclock();
		showtime();
	}
	function showtime () {
		var now = new Date();
		var year = now.getFullYear();
		var month = now.getMonth()+1;
		var date = now.getDate();
		var hours = now.getHours();
		var minutes = now.getMinutes();
		var seconds = now.getSeconds()
		var day = now.getDay();//星期
		var week = new Array('日','一','二','三','四','五','六');		
		var timeValue = year+"-"+(month<10 ? "0" + month : month)+"-"+(date<10 ? "0" + date : date)+"&nbsp;&nbsp;&nbsp;";
		timeValue += ((hours < 10) ? "0" : "") + hours;
		timeValue += ((minutes < 10) ? ":0" : ":") + minutes;
		timeValue += ((seconds < 10) ? ":0" : ":") + seconds;
		timeValue += "&nbsp;&nbsp;&nbsp;星期"+week[day];
		document.getElementById("datetime").innerHTML =timeValue;
		timerID = setTimeout("showtime()",1000);
		timerRunning = true;
	}
	
</script>
</head>
<body onload="startclock()" >
<div class="header_content_b">
<div class="header_content">
	 <div class="right_nav">
		<div class="text_right">
		<span id="show_text" style="text-align:center;">
				<strong class="colred">
				<s:property value="#session.login_system_user.operName" />，
				</strong> <span style="color:#2f2f2f; font-weight:bold;">欢迎使用<s:text name='domain.title'/>业务管理平台</span> &nbsp;&nbsp;
		</span>
		<span id="datetime" style="color: #2f2f2f;"></span>&nbsp;&nbsp;
		<ul class="nav_return" style="padding-top: 25px">
		<li> <a href="<%=path %>/transport/qurBofficePayTransport.do" target="mainFrame"><img src="<%=path %>/images/template/exclamation.png" />&nbsp;&nbsp;错误信息</a>&nbsp;&nbsp;</li>	
		<li> <a href="<%=path %>/login/mainFrame.do" target="mainFrame"><img src="<%=path %>/images/topIcon/gohome.gif" />&nbsp;&nbsp;返回首页</a>&nbsp;&nbsp;</li>
		<li> <a href="<%=path %>/acegi/modifyPassword.do" target="mainFrame"><img src="<%=path %>/images/topIcon/updatepsd.gif" />&nbsp;&nbsp;修改密码</a>&nbsp;&nbsp;</li>
		<li> <a href="<%=path %>/login/loginOut.do" ><img src="<%=path %>/images/topIcon/loginOut.gif" />&nbsp;&nbsp;退出系统</a>&nbsp;&nbsp;</li>
		</ul>
		</div>
	 </div>
</div>
</div>
</body>
</html>