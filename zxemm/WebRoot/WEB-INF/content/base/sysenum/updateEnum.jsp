<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/content/base/tag.inc" />
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<HEAD>
		<TITLE>修改枚举信息</TITLE>
		<META http-equiv=Content-Type content="text/html; charset=utf-8">
		<META http-equiv=Expires content=0>
		<script type="text/javascript" src="<%=path%>/js/jquery-1.6.js">
		</script>
		<script type="text/javascript" src="<%=path%>/js/base.js">
		</script>
		<script type="text/javascript" src="<%=path%>/js/formValidate.js">
		</script>
		<link href="<%=path%>/css/Style1.css" rel="stylesheet" type="text/css" />
		<link href="<%=path%>/css/Style2.css" rel="stylesheet" type="text/css" />
		<SCRIPT language=JavaScript>
		<!--
		//按钮提交事件
		function submitForm(url, act)
		{
			if(act == "update")
			{
				var enumName = document.getElementById("enumName");
				var tableName = document.getElementById("tableName");
				var fieldName = document.getElementById("fieldName");
				if(!checkFormdata(enumName,"枚举名称",50,true,false,false,false,false))
				{	
					return false;
				}
				if(!checkFormdata(tableName,"对应表名",20,true,true,true,false,false))
				{	
					return false;
				}
				if(!checkFormdata(fieldName,"字段名称",50,true,true,true,false,false))
				{	
					return false;
				}
				document.PageForm.action = url;
				document.PageForm.submit();
			}
		}
		-->
		</SCRIPT>
	</HEAD>
	<body>
	<div id=mainzone>
		<div id="body">
			<!-- 提交表单 -->
			<FORM id=mainzone name=PageForm  method=post>
				<!-- 系统功能路径 -->
				<div class="loc">
					<div class="groupmenu" id="groupmenu"></div>
					<!--  
					<a href="<%=path %>/login/mainFrame.do"></a> &raquo;
					-->
					<span>系统管理</span> &raquo;
					<span>系统参数管理</span> &raquo;
					<a href="<%=path %>/acegi/queryAllEnum.do">枚举信息维护</a> &raquo;
					<span class="last">修改枚举信息</span>
				</div>

				<!-- 主体DIV -->
				<div class="tab_cntr">
					<!-- 内容填写表格 -->
					<table class="editorTb" style="" id="tbBase" name="editorTab">
						<tr>
							<td class="hd" colspan="2">
								修改枚举对象（<font color="red">* 为必填项</font>）
							</td>
						</tr>
						<tr>
							<td class="titl">
								<b>枚举名称：</b>
							</td>
							<td>
								<input name="sysEnum.enumId"  type="hidden" value="${sysEnum.enumId }" />
								<input id="enumName" name="sysEnum.enumName" value="${sysEnum.enumName }" type="text" class="txt-150"/>
								<font color="red">&nbsp;*</font>
							</td>
						</tr>
						<tr>
							<td class="titl">
								<b>对应表名：</b>
							</td>
							<td>
								${sysEnum.tableName }
								<input id="tableName" name="sysEnum.tableName" value="${sysEnum.tableName }" type="hidden" class="txt-150"/>
							</td>
						</tr>
						<tr>
							<td class="titl">
								<b>字段名：</b>
							</td>
							<td>
								${sysEnum.fieldName }
								<input id="fieldName" name="sysEnum.fieldName" value="${sysEnum.fieldName }" type="hidden" class="txt-150"/>
							</td>
						</tr>
						<tr>
							<td class="titl">
								<b>状态：</b>
							</td>
							<td>
								<s:if test="sysEnum.status==1">
									<INPUT type=radio name="sysEnum.status" value=1 checked>
											可用&nbsp;&nbsp;
											<INPUT type=radio name="sysEnum.status" value=0>
											不可用</s:if>
								<s:if test="sysEnum.status==0">
									<INPUT type=radio name="sysEnum.status" value=1>
											可用&nbsp;&nbsp;
											<INPUT type=radio name="sysEnum.status" value=0 checked>
											不可用</s:if>
							</td>
						</tr>
						<tr>
							<td class="titl">
								<b>系统定义：</b>
							</td>
							<td>
								<s:if test="sysEnum.systemFlag==1">
									<INPUT type=radio name="sysEnum.systemFlag" value=1 checked>
											是&nbsp;&nbsp;
											<INPUT type=radio name="sysEnum.systemFlag" value=0>
											否</s:if>
								<s:if test="sysEnum.systemFlag==0">
									<INPUT type=radio name="sysEnum.systemFlag" value=1>
											是&nbsp;&nbsp;
											<INPUT type=radio name="sysEnum.systemFlag" value=0 checked>
											否</s:if>
							</td>
						</tr>
					</table>

					<!-- 提交按钮 -->
					<div class="btn-wz">
						<div style="width: 250px; float: left;">
							<input name="button" type=button onclick="javascript:history.go(-1)"
								value="" class="return" />
						</div>
						<input type="button" name="btnSave" value=""
							onclick="submitForm('acegi/updateEnum.do','update')"
							id="btnSave" class="save" />
						<input name="Submit" type="reset" value="" class="reset" />
						<div class="clr"></div>
					</div>
				</div>
			</FORM>
			</div>
		</div>
	</BODY>
</HTML>