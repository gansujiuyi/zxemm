<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/content/base/tag.inc" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<HEAD>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<link rel="stylesheet"
			href="<%=request.getContextPath()%>/css/common.css" type="text/css" />
	</HEAD>
	<body>
		<div id="sub_info">
			&nbsp;&nbsp;
			<img src="<%=request.getContextPath()%>/images/hi.gif" />
			&nbsp;&nbsp;
			<span id="show_text">
				<strong> 
				<s:property value="#session.login_system_user.operName" /> 
				</strong>, 欢迎使用<s:text name='domain.title'/>业务管理平台 
			</span>
		</div>
	</body>
</html>
