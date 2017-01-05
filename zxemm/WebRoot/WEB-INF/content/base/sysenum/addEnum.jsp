<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/content/base/tag.inc" />
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<HEAD>
		<TITLE>新增枚举信息</TITLE>
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
				if(act == "add")
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
					var v1 = document.getElementById("enumName").value;
					var v2 = document.getElementById("tableName").value;
					var v3 = document.getElementById("fieldName").value;					
					document.getElementById("enumName").value=v1.replace(/\s+/g,"");
					document.getElementById("tableName").value=v2.replace(/\s+/g,"");
					document.getElementById("fieldName").value=v3.replace(/\s+/g,""); 					
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
			<FORM id=PageForm name=PageForm method=post>
				<!-- 系统功能路径 -->
				<div class="loc">
					<div class="groupmenu" id="groupmenu"></div>
					<!--  
					<a href="<%=path %>/login/mainFrame.do"></a> &raquo;
					-->
					<span>系统管理</span> &raquo;
					<span>系统参数管理</span> &raquo;
					<a href="<%=path %>/acegi/queryAllEnum.do">枚举信息维护</a> &raquo;
					<span class="last">新增枚举</span>
				</div>

				<!-- 主体DIV -->
				<div class="tab_cntr">
					<!-- 
					<ul class="opt">
						<li>
							<a class="cur" href="javascript:void(0)">基本属性</a>
						</li>
					</ul>
					-->
					<!-- 内容填写表格 -->
					<table class="editorTb" style="" id="tbBase" name="editorTab">
						<tr>
							<td class="hd" colspan="2">
								新建枚举对象（<font color="red">* 为必填项</font>）
							</td>
						</tr>
						<tr>
							<td class="titl">
								<b>枚举名称：</b>
							</td>
							<td>
								<input id="enumName" name="sysEnum.enumName" type="text" class="txt-150"/>
								<font color="red">&nbsp;*</font>
							</td>
						</tr>
						<tr>
							<td class="titl">
								<b>对应表名：</b>
							</td>
							<td>
								<input id="tableName" name="sysEnum.tableName" type="text" class="txt-150"/>
								<font color="red">&nbsp;*</font>
							</td>
						</tr>
						<tr>
							<td class="titl">
								<b>字段名：</b>
							</td>
							<td>
								<input id="fieldName" name="sysEnum.fieldName" type="text" class="txt-150"/>
								<font color="red">&nbsp;*</font>
							</td>
						</tr>
						<tr>
							<td class="titl">
								<b>状态：</b>
							</td>
							<td>
								<INPUT type=radio name="sysEnum.status" value=1 checked>
											可用&nbsp;&nbsp;
								<INPUT type=radio name="sysEnum.status" value=0>
											不可用
							</td>
						</tr>
						<tr>
							<td class="titl">
								<b>系统定义：</b>
							</td>
							<td>
								<INPUT type=radio name="sysEnum.systemFlag" value=1 checked>
											是&nbsp;&nbsp;
								<INPUT type=radio name="sysEnum.systemFlag" value=0>
											否
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
							onclick="submitForm('acegi/saveEnum.do','add')"
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