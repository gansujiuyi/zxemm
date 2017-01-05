<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/content/base/tag.inc" />
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<HEAD>
		<TITLE></TITLE>
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
    	var institutionNo = document.getElementById("institutionNo");
		var institutionName = document.getElementById("institutionName");
		var description = document.getElementById("description");
		if(!checkFormdata(institutionNo,"机构编号",10,true,true,true,false,false))
		{	
			return false;
		}
		if(!checkFormdata(institutionName,"机构名称",256,true,true,false,false,false))
		{	
			return false;
		}
		if(!checkFormdata(description,"机构描述",50,false,false,false,false,false))
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
					<span>机构与用户管理</span> &raquo;
					<a href="<%=path %>/acegi/queryInst.do">组织机构维护</a> &raquo;
					<span class="last">新增子组织机构</span>
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
								新建子组织机构（<font color="red">* 为必填项</font>）
							</td>
						</tr>
						<tr>
							<td class="titl">
								<b>上级机构：</b>
							</td>
							<td>
								<c:if test="${empty inst.parentInstitutionId }">
				           			<s:text name='domain.title'/>平台
				           		</c:if>
				           		<c:if test="${!empty inst.parentInstitutionId }">
				           			${inst.institutionName }
				           		</c:if>
			              		<c:if test="${empty inst.parentInstitutionId }">
			              			<input type="hidden" name="inst.parentInstitutionId" value="0">
			              		</c:if>
			              		<c:if test="${!empty inst.parentInstitutionId }">
			              			<input type="hidden" name="inst.parentInstitutionId" value="${inst.parentInstitutionId }">
			              		</c:if>
							</td>
						</tr>
						<tr>
							<td class="titl">
								<b>组织机构编号：</b>
							</td>
							<td>
								<input id="institutionNo" name="inst.institutionNo" type="text" maxlength="20" class="txt-150"/>
								<font color="red">&nbsp;*</font>
							</td>
						</tr>
						<tr>
							<td class="titl">
								<b>组织机构名称：</b>
							</td>
							<td>
								<input id="institutionName" name="inst.institutionName" type="text" maxlength="10" class="txt-150"/>
									<font color="red">&nbsp;*</font>
							</td>
						</tr>
						<tr>
							<td class="titl">
								<b>组织机构描述：</b>
							</td>
							<td>
								<textarea id="description" name="inst.description" rows="2" cols="20" class="txt-300"></textarea>
							</td>
						</tr>
					</table>

					<!-- 提交按钮 -->
					<div class="btn-wz">
						<div style="width: 250px; float: left;">
						</div>
						<input type="button" name="btnSave" value=""
							onclick="submitForm('acegi/saveAddedInst.do','add')"
							id="btnSave" class="save" />
						<input name="Submit" type="reset" value="" class="reset" />
						<div class="clr"></div>
					</div>
				</div>
			</FORM>
		</div>
		</div>
</BODY></HTML>