<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/content/base/tag.inc" />
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<HEAD>
		<TITLE>修改角色信息</TITLE>
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
					var roleName = document.getElementById("roleName");
					var description = document.getElementById("description");
					if(!checkFormdata(roleName,"角色名称",20,true,true,false,false,false))
					{	
						return false;
					}
					if(!checkFormdata(description,"角色描述",50,false,false,false,false,false))
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
		<div id="mainzone">
		<div id="body">
			<!-- 提交表单 -->
			<FORM name=PageForm  method=post>
				<!-- 系统功能路径 -->
				<div class="loc">
					<div class="groupmenu" id="groupmenu"></div>
					<!-- 
					<a href="<%=path %>/login/mainFrame.do"></a> &raquo;
					-->
					<span>系统管理</span> &raquo;
					<span>功能权限管理</span> &raquo;
					<a href="<%=path %>/acegi/queryRole.do">角色定义</a> &raquo;
					<span class="last">修改角色</span>
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
								修改系统角色（<font color="red">* 为必填项</font>）
							</td>
						</tr>
						<tr>
							<td class="titl">
								<b>角色名称：</b>
							</td>
							<td>
								<input name="role.roleId"  type="hidden" value="${role.roleId }" />
								<input id="roleName" name="role.roleName" value="${role.roleName }" type="text" maxlength="20" class="txt-150"/>
									<font color="red">&nbsp;*</font>
							</td>
						</tr>
						<tr>
							<td class="titl">
								<b>所属平台：</b>
							</td>
							<td>
								<select id="belongplat" name="role.belongplat" type="text" maxlength="20" class="sel-156"/>
									<option value="0" <c:if test="${role.belongplat == 0}">selected="selected"</c:if>>管理平台</option>
									<%-- <option value="1" <c:if test="${role.belongplat == 1}">selected="selected"</c:if>>商户平台</option> --%>
								</select>
							</td>
						</tr>
						<tr>
							<td class="titl">
								<b>角色描述：</b>
							</td>
							<td>
								<textarea id="description" name="role.description" rows="2" cols="20" class="txt-300">${role.description }</textarea>
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
							onclick="submitForm('acegi/saveUpdatedRole.do','add')"
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