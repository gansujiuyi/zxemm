<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/content/base/tag.inc" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title><s:text name='domain.title'/>提示信息</title>
	</head>
	<script language="javascript">
		var message = "${message}";
		var goHref = "${goHref}";
		// var baseHref = "<s:text name='domain.name'/>";
		if( message && message != "" ){
			alert(message);
		}else{
			alert("系统未知错误!");
		}
		if( goHref && goHref != "" )
			document.location.href = "<%= request.getContextPath()%>" + goHref;
	</script>
	<body>
	</body>
</html>
