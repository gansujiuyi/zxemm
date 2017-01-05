<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<form id="gotologinform" method="get" target="_parent" style="display: none;" action="<%=path%>/login.do">
</form>
<script type="text/JavaScript">
	document.getElementById("gotologinform").submit();
</script>