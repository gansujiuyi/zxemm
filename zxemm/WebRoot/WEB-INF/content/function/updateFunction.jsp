<%@page import="com.jiuyi.jyplat.util.ConfigManager"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/content/base/tag.inc" />
<%
	String path = request.getContextPath();
	String base_path = ConfigManager.getString("base_path", "请在emm中设置");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=base_path%>">

		<title>My JSP 'updateFunction.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page"/>
		<script type="text/javascript" src="<%=path%>/js/base.js">
		</script>
		<script type="text/javascript" src="<%=path%>/js/formValidate.js">
		</script>
		<link href="<%=path%>/css/mainframe.css" rel="stylesheet"
			type="text/css" />
		<SCRIPT language=JavaScript>
			<!--
			//按钮提交事件
			function submitForm(url, act)
			{
				if(act == "update")
				{
					var functionNo = document.getElementById("functionNo");
					var functionName = document.getElementById("functionName");
					var functionUrl = document.getElementById("functionUrl");
					var description = document.getElementById("description");
					if(!checkFormdata(functionNo,"功能编号",4,true,true,true,false,false))
					{	
						return false;
					}
					if(!checkFormdata(functionName,"功能名称",20,true,true,false,false,false))
					{	
						return false;
					}
					if(!checkFormdata(functionUrl,"功能路径",100,false,false,true,false,false))
					{	
						return false;
					}
					if(!checkFormdata(description,"功能描述",50,false,false,false,false,false))
					{	
						return false;
					}
					document.PageForm.action = url;
					document.PageForm.submit();
				}
			}
			-->
		</SCRIPT>

	</head>

	<body>
		<form action="sysFunction/updateFunction.do" method="post">
			<table cellspacing=0 cellpadding=0 width="95%" align=center border=0>
				<tbody>
					<font color="green">>>修改系统功能</font> (
					<font color=red>*</font>为必输字段)
					<tr>
						<td height=10>
							<hr>
						</td>
					</tr>
					<tr>
						<td>
							<%--<%=FrontUtil.getButton("3151", authorization, "保存", "save")%>--%>
							<input type="submit" class=BtnAction value="提交"
								onClick="return submitForm('sysFunction/updateFunction.do', 'update')" />
							<input name="button4" type=button class=BtnAction
								style="CURSOR: hand" value=返回
								onclick="javascript:history.go(-1)";>
						</td>
					</tr>
					<tr valign=bottom>
						<td colspan=4>
							<hr>
						</td>
					</tr>
					<tr>
						<td colspan=4>
							<table width="100%" height="137" border=0 align=center
								cellPadding=2 cellSpacing=0 borderColorLight=#000000
								borderColorDark=#ffffff bgColor=#dddddd class=edit_table>
								<tbody>
									<tr>
										<td class=edit_table_td_title noWrap>
											功能编号：
										</td>
										<td width="23%" height="28" noWrap class=edit_table_td_input>
											<input id="functionNo" class=TextInput maxLength=4 size=20
												name="sysFunction.functionNo" class="txt-150"
												value="${sysFunction.functionNo }" readonly />
											&nbsp;
											<font color=red>*</font>
										</td>
										<td width="19%" noWrap class=edit_table_td_title>
											功能名称：
										</td>
										<td width="44%" noWrap class=edit_table_td_input>
											<inut id="functionName" class=TextInput size=20 name="sysFunction.functionName" class="txt-150"
												value="${sysFunction.functionName }" />
										</td>
									</tr>
									<tr>
										<td class=edit_table_td_title noWrap>
											父功能号：
										</td>
										<td height="28" noWrap class=edit_table_td_input>
											<input id="parentFunctionNo" class=TextInput maxLength=4 size=20
												name="sysFunction.parentFunctionNo" class="txt-150"
												value="${sysFunction.parentFunctionNo }" />
										</td>
										<td height="28" noWrap class=edit_table_td_title>
											URL：
										</td>
										<td height="28" noWrap class=edit_table_td_input>
											<input id="functionUrl" class=TextInput maxLength=100 size=20 class="txt-150"
												name="sysFunction.url" value="${sysFunction.url }" />
										</td>
									</tr>
									<tr>
										<td class=edit_table_td_title noWrap>
											说明：
										</td>
										<td height="28" noWrap class=edit_table_td_input>
											<input id="description" class=TextInput maxLength=50 size=20
												name="sysFunction.description" class="txt-150"
												value="${sysFunction.description }" />
										</td>
										<td height="28" noWrap class=edit_table_td_title>
											状态：
										</td>
										<td height="28" noWrap class=edit_table_td_input>
											
											<input type=radio name="sysFunction.status" value=1 <s:if test="sysFunction.functionNo==1">checked</s:if> />
											可用&nbsp;&nbsp;
											<input type=radio name="sysFunction.status" value=0 <s:if test="sysFunction.functionNo==0">checked</s:if> />
											不可用
											<input type=radio name="sysFunction.status" value=2 <s:if test="sysFunction.functionNo==2">checked</s:if> />
											可用但隐藏
										</td>
									</tr>
								</tbody>
							</table>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</body>
</html>
