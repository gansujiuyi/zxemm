<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/content/base/tag.inc" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" style="overflow:auto;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<meta http-equiv=Expires content=0 />
<link rel="shortcut icon" href ="<%=request.getContextPath() %>/logo.ico" />
<title><s:text name='domain.title'/></title>
</head>
<frameset rows="101,*" cols="*" frameborder="no" border="0" framespacing="0">
  <frame src="login/topFrame.do" name="topFrame" frameborder="no" scrolling="No" noresize="noresize" id="topFrame" />
  <frameset name="myFrame" cols="199,7,*" frameborder="no" border="0" framespacing="0">
    <frame src="login/leftFrame.do" name="leftFrame" frameborder="no" scrolling="No" noresize="noresize" id="leftFrame" />
	<frame src="login/switchFrame.do" name="midFrame" frameborder="no" scrolling="No" noresize="noresize" id="midFrame" />
	    <frame src="login/mainFrame.do" name="mainFrame" frameborder="no" scrolling="Yes" noresize="noresize" id="mainFrame" title="mainFrame" />
  </frameset>
</frameset>
<noframes><body>
  <p>此网页使用了框架，但您的浏览器不支持框架。</p>
</body></noframes>
</html>