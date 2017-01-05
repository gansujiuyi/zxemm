<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/content/base/tag.inc" />
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<HEAD>
		<TITLE>修改Action</TITLE>
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
				var actionName = document.getElementById("actionName");
				var actionUrl = document.getElementById("actionUrl");
				var description	= document.getElementById("description");
				if(!checkFormdata(actionName,"Action名称",40,true,true,false,false,false))
				{	
					return false;
				}
				if(!checkFormdata(actionUrl,"Action路径",200,true,false,false,false,false))
				{	
					return false;
				}
				if(!checkFormdata(description,"Action说明",50,false,false,false,false,false))
				{	
					return false;
				}
				
					var v1 = document.getElementById("actionName").value;										
					document.getElementById("actionName").value=v1.replace(/\s+/g,"");
					checkNameAction = document.getElementById("actionName").value;
					
					var v_Id = document.getElementById("actionId").value.replace(/\s+/g,"");
					$.ajax({   //请求后台检查Action名称是否重复
						type:'POST',
						url:"sysAction/CkNameForUpdate.do",
						dateType:"json",	
						data:"checkNameAction="+checkNameAction+"&checkIdAction="+v_Id,
						success: function(msg){
							if(msg.message=="exists"){
								alert("Action名称已经存在请重新输入！！");
								return false;
							}else{
								document.PageForm.action = url;
								document.PageForm.submit();
							}
						},
						error: function(msg){}
					});
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
					<a href="<%=path %>/login/mainFrame.do"></a> &raquo; -->
					<span>系统管理</span> &raquo;
					<span>功能权限管理</span> &raquo;
					<a href="<%=path %>/sysAction/queryAllAction.do">Action维护</a> &raquo;
					<span class="last">修改Action</span>
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
								修改系统Action（<font color="red">* 为必填项</font>）
							</td>
						</tr>
						<tr>
							<td class="titl">
								<b>Action名称：</b>
							</td>
							<td>
								<input name="sysAction.actionId" id="actionId"  type="hidden" value="${sysAction.actionId }" />
								<input id="actionName" name="sysAction.actionName" value="${sysAction.actionName }" type="text" class="txt-150"/>
								<font color="red">&nbsp;*</font>
							</td>
						</tr>
						<tr>
							<td class="titl">
								<b>ActionUrl：</b>
							</td>
							<td>
								<textarea id="actionUrl" name="sysAction.actionUrl" rows="2" cols="20" class="txt-300">${sysAction.actionUrl }</textarea>
									<font color="red">&nbsp;*</font>
							</td>							
						</tr>
						<tr>
							<td class="titl">
								<b>状态：</b>
							</td>
							<td>
								<s:if test="sysAction.status==1">
									<INPUT type=radio name="sysAction.status" value=1 checked>
											可用&nbsp;&nbsp;
											<INPUT type=radio name="sysAction.status" value=0>
											不可用</s:if>
								<s:if test="sysAction.status==0">
									<INPUT type=radio name="sysAction.status" value=1>
											可用&nbsp;&nbsp;
											<INPUT type=radio name="sysAction.status" value=0 checked>
											不可用</s:if>
							</td>
						</tr>
						<tr>
							<td class="titl">
								<b>说明：</b>
							</td>
							<td>
								<textarea id="description" name="sysAction.description" rows="2" cols="20" class="txt-300">${sysAction.description }</textarea>
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
							onclick="submitForm('sysAction/updateAction.do','update')"
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