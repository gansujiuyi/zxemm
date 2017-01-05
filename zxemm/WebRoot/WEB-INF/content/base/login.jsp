<%@page language="java" contentType="text/html;charset=utf-8" %>
<jsp:directive.include file="/WEB-INF/content/base/tag.inc" />
<%@page language="java" import="com.jiuyi.jyplat.util.Constant" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>预售房资金监管管理系统业务管理平台 </title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<meta http-equiv="Pragma"   content="no-cache"/>    
<meta http-equiv="Cache-Control"   content="no-cache"/>    
<meta http-equiv="Expires"   content="0"/>    
<link rel="shortcut icon" href ="<%=path%>/logo.ico" />
<link type="text/css" href="<%=path%>/css/login.css" rel="stylesheet"/>
<script type="text/javascript" src="<%=path%>/js/jquery-1.6.js"></script>
<script type="text/javascript" src="<%=path%>/js/login/login.js"></script>
<script type="text/javascript">
	var projcetname = "<%=path%>";
</script>
</head>

<body onkeydown="strdown(event)">
	<div class="emm-logo">
		<div class="emm-page logo-img">
				<img src="<%=path%>/images/login/login_bg_zx_.jpg"/>
		</div>
	</div>
	<div class="emm-login">
		<div class="emm-page">
			<form action="<%=path%>/login/doLogin.do" id="loginForm" name="loginForm" method="post">
				<div class="emm-login-content">
					<p class="emm-login-content-text">
						<input tabindex="1" type="text" class="wdith_168" id="username" name="operator.operNo" 
							onfocus="javascript:usernamefocus();" onblur="javascript:usernameblur();" maxlength="20" />
					</p>
					<p class="emm-login-content-text">
						<input tabindex="2" type="password" class="wdith_168" id="password" name="operator.password" maxlength="20" />
					</p>
					<p class="emm-login-content-verify co">
						<input tabindex="3" class="wdith_100" value="验证码" id="imagecode" type="text" name="imagecode" maxlength="4" />
						<img id="imgcode" width="66" height="24" align="middle" onclick="changeImgCode();"
							title="看不清？点击更换验证码" />
						<a onclick="javascript:changeImgCode();">看不清换一张</a>
					</p>
					<div class="emm-login-content-submit co">
						<input type="button" id="login" value="确 定" onclick="javascript:submitForm()"/>
						<input type="reset" value="重 置" name="button" id="button" value=""/>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="copyright">Copyright 2009-2016 甘肃久义版权所有</div>
</body>
</html>