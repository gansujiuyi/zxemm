<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>新房开户申请</title>
		<link rel="stylesheet" type="text/css"
			href="<%=path%>/css/style_zjjg.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/public.css" />
		<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
		<link href="<%=path%>/js/select2-3.5.2/select2.css" rel="stylesheet"/>
	</head>
	<body>
		<div >
			<div style="font-size: 36px;font-weight: bold;margin-top: 223px;margin-left: 158px; border: 0px;">
				开户申请成功
<%-- 				开户申请成功，申请的账号为：${buildingInfo.regulateAccount } --%>
			</div>
			<div style="font-size: 19px;font-weight: bold;margin-top: 51px;margin-left: 258px; border: 0px;">
				<%-- 开发商登录初始名称为：${str}
				开发商登录初始密码为：	123456 --%>
			</div>
		</div>
	</body>
</html>
