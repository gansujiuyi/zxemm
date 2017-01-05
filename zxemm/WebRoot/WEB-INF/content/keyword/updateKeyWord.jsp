<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/content/base/tag.inc" />
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>修改关键字</title>
		<meta http-equiv=Content-Type content="text/html; charset=utf-8" />
		<meta http-equiv=Expires content=0 />
		<script type="text/javascript" src="<%=path%>/js/jquery-1.6.js"> </script>
		<script type="text/javascript" src="<%=path%>/js/base.js"> </script>
		<script type="text/javascript" src="<%=path%>/js/formValidate.js"> </script>
		<link href="<%=path%>/css/Style1.css" rel="stylesheet" type="text/css" />
		<link href="<%=path%>/css/Style2.css" rel="stylesheet" type="text/css" />
		<script language=JavaScript>
			//按钮提交事件  
			function submitForm(url, act)
			{
				if(act == "update")
				{
					var key = document.getElementById("key");
					var value = document.getElementById("value");
					var description = document.getElementById("description");

					if(!checkFormdata(key,"关键字key",50,true,false,true,false,false))
					{	
						return false;
					}
					if(!checkFormdata(value,"关键字value",200,false,false,false,false,false))
					{	
						return false;
					}
					if(!checkFormdata(description,"描述",500,false,false,false,false,false))
					{	
						return false;
					}
					document.PageForm.action = url;
					document.PageForm.submit();
				}
			}
		</script>
	</head>
	<body>
	<div id=mainzone>
		<div id="body">
			<!-- 提交表单 -->
			<form id=PageForm name=PageForm  method=post>
				<!-- 系统功能路径 -->
				<div class="loc">
					<div class="groupmenu" id="groupmenu"></div>
					<!-- 
					<a href="<%=path %>/login/mainFrame.do"></a> &raquo;
					 -->
					<span>关键字管理</span> &raquo;
					<a href="<%=path %>/business/queryStoreByCustId.do">关键字维护</a> &raquo;
					<span class="last">修改关键字</span>
				</div>

				<!-- 主体DIV -->
				<div class="tab_cntr">
					<table class="editorTb" style="" id="tbBase"  name="editorTab">
						<tr>
							<td class="hd" colspan="2">
								修改关键字（<font color="red">* 为必填项</font>）
							</td>
						</tr>
						<tr>
							<td class="titl">
								<b>关键字key：</b>
							</td>
							<td>
								<input id="id" type="hidden" name="keyword.id" value="${keyword.id }" />
								<input id="key" name="keyword.key" type="text" class="txt-150" maxlength="10" value="${keyword.key}"/>
								<font color="red">&nbsp;*</font>
							</td>
						</tr>
						<tr>
							<td class="titl">
								<b>关键字value：</b>
							</td>
							<td>
								<input id="value" name="keyword.value" type="text" class="txt-150" maxlength="30" value="${keyword.value}"/>
							</td>
						</tr>
						<tr>
							<td class="titl">
								<b>描述：</b>
							</td>
							<td>
								<textarea id="description" name="keyword.description" rows="" cols=""  class="txt-150">${keyword.description}</textarea>
							</td>
						</tr>
					</table>

					<!-- 提交按钮 -->
					<div style="padding: 10px; padding-left: 10px;">
						<div style="width: 250px; float: left;">
							<input name="button" type=button onclick="javascript:history.go(-1)" class="return" />
						</div>
						<input type="button" name="btnSave"  onclick="submitForm('keyword/updateKeyWord.do','update')" id="btnSave" class="save" />
						<input name="Submit" type="reset" value="" class="reset" />
						<div class="clr"></div>
					</div>
				</div>
			</form>
			</div>
		</div>
	</body>
</html>