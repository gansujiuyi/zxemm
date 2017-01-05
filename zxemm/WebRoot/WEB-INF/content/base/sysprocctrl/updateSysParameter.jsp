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
		<script language="javascript" type="text/javascript" src="<%=path %>/js/timerControl/WdatePicker.js"></script>
		<SCRIPT language=JavaScript>
			<!--
			//按钮提交事件
			function submitForm(url, act)
			{
			    if(act == "add")
				{
					if(!checkFormdata(document.getElementById("sysWorkDate"),"系统处理账务日期",20,true,true,false,false,false))
					{	
						return false;
					}
					if(!checkFormdata(document.getElementById("sysState"),"执行状态",2,true,true,false,false,true))
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
					<!-- <a href="<%=path %>/login/mainFrame.do"></a> &raquo; -->
					<span>系统管理&raquo;批处理管理</span> &raquo;
					<a href="<%=path %>/points/queryAllSysProcCtrl.do">批处理维护</a> &raquo;
					<span class="last">修改系统任务</span>
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
								系统任务（修改对象）
							</td>
						</tr>
						<tr>
							<td class="titl">
								<b>系统处理账务日期：</b>
							</td>
							<td>
								<INPUT id="sysWorkDate" name="sysParameter.sysWorkDate" value="${fn:trim(sysParameter.sysWorkDate) }" class="Wdate" onClick="WdatePicker({dateFmt:'yyyyMMdd'})"
										readOnly style="width: 150px;"/>
							</td>
						</tr>
						<tr>
							<td class="titl">
								<b>执行状态：</b>
							</td>
							<td>
								<select id="sysState" name="sysParameter.sysState" class="sel-156">
									<option value="0" <c:if test="${sysParameter.sysState=='0' }">selected="selected"</c:if>>正常</option>
									<option value="1" <c:if test="${sysParameter.sysState=='1' }">selected="selected"</c:if>>正在批处理</option>
									<option value="7" <c:if test="${sysParameter.sysState=='7' }">selected="selected"</c:if>>批处理异常</option>
									<option value="9" <c:if test="${sysParameter.sysState=='9' }">selected="selected"</c:if>>批处理故障排除，请求系统继续处理</option>
								</select>
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
							onclick="submitForm('acegi/saveSysParameter4Task.do','add')"
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