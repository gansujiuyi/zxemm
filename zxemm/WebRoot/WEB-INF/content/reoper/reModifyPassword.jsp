<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/content/base/tag.inc" />
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<HEAD>
		<TITLE>修改操作员密码</TITLE>
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
		/*
		//检查原密码是否输入有误
		function checkPwd()
		{
			var oldPwd = document.getElementById("oldPassword");
			var pwd = oldPwd.value;
			var operNo = document.getElementById("operNo").value;
			$.ajax({
				type:'POST',
				url:"acegi/checkOldPassword.do",
				dateType:"json",
				data:'oldPassword='+ pwd+"&operNo="+operNo,
				success: function(msg){
					if(msg.message!="truePwd")
					{
						document.getElementById("pwdMsg").innerText="原密码输入错误，请重新输入!";				
					}
					else
					{
						document.getElementById("pwdMsg").innerText="";
					}
				},
				error: function(msg){
					alert("验证原密码操作失败!");
				}
			});
		}
		*/
		
		//验证表单输入项
		function checkForm()
		{	
			//原密码
			var oldPwd = document.getElementById("oldPassword");
			//新密码
			var newPwd = document.getElementById("newPassword");
			//确认密码
			var rePwd = document.getElementById("confirmPwd");
			if(!checkFormdata(oldPwd,"原密码",6,true,false,true,false,false)){
				oldPwd.select();
				return false;
			}
			else if(!checkFormdata(newPwd,"新密码",6,true,false,true,false,false))
			{
				newPwd.select();
				return false;
			}
			else if(rePwd.value!=newPwd.value)
			{
				alert("两次输入密码不一致！");
				rePwd.select();
				return false;
			}
			document.PageForm.action="acegi/savePassword.do";
			document.PageForm.submit();
		}
	
		//trim方法删除左右两端的空格
		function trim(str){ 
			return str.replace(/(^\s*)|(\s*$)/g, "");
		}
		-->
		</SCRIPT>
	</HEAD>
	<body>
	<div id=mainzone>
		<div id="body">
			<!-- 提交表单 -->
			<FORM id=PageForm name=PageForm method=post action="acegi/savePassword.do">
				<!-- 系统功能路径 -->
				<div class="loc">
					<div class="groupmenu" id="groupmenu"></div>
					<!--  
					<a href="<%=path %>/login/mainFrame.do"></a> &raquo;
					-->
					<span>系统管理</span> &raquo;
					<span>自助服务</span> &raquo;
					<span class="last">密码修改</span>
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
									修改密码（<font color="red">* 为必填项</font>）
								</td>
							</tr>
							<!-- session中的操作员编号为空（在登录界面点击修改密码），让操作员输入自己的编号 -->
							<c:if test="${empty login_system_user.operNo }">
								<tr>
									<td class="titl">
										<b>操作员编号:</b>
									</td>
									<td>
										<input id="operNo" name="operNo" type="text" class="txt-150"/>
									</td>
								</tr>
							</c:if>
							<!-- session中的操作员编号不为空（登录之后点击的修改密码） -->
							<c:if test="${!empty login_system_user.operNo }">
								<input type="hidden" id="operNo" name="operNo" value="${fn:trim(login_system_user.operNo) }" />
							</c:if>
								
							<tr>
								<td class="titl">
									<b>原密码：</b>
								</td>
								<td>
									<input id="oldPassword" type="password" class="txt-150" name="oldPassword" value="" />
									<font color="red">&nbsp;*</font>
									<!-- <font color="red"><span id="pwdMsg"></span></font> -->
								</td>
							</tr>
							<tr>
								<td class="titl">
									<b>新密码：</b>
								</td>
								<td>
									<input id="newPassword" type="password" class="txt-150" name="newPassword" value="" />
									<font color="red">&nbsp;*</font>
								</td>
							</tr>
							<tr>
								<td class="titl">
									<b>确认密码：</b>
								</td>

								<td>
									<input id="confirmPwd" type="password" class="txt-150" name="confirmPwd" value="" />
									<font color="red">&nbsp;*</font>
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
							onclick="return checkForm()"
							id="btnSave" class="save" style="width: 80px"/>
						<!-- <input type="button" name="btnSave" value="重置密码"
							onclick="return checkForm()"
							id="btnSave" class="btn mL18" /> -->
						<input name="Submit" type="reset" value="" class="reset" />
						<div class="clr"></div>
					</div>
				</div>
			</FORM>
			</div>
		</div>
	</BODY>
</HTML>