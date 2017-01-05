<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/content/base/tag.inc" />
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<HEAD>
		<TITLE>新增系统功能</TITLE>
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
					var functionNo = document.getElementById("functionNo");
					var functionName = document.getElementById("functionName");
					var functionUrl = document.getElementById("functionUrl");
					var description = document.getElementById("description");
					var orderBy = document.getElementById("orderBy");
					if(!checkFormdata(functionNo,"功能编号",4,true,true,true,false,false))
					{	
						return false;
					}
					if(!checkFormdata(functionName,"功能名称",40,true,true,false,false,false))
					{	
						return false;
					}
					if(!checkFormdata(functionUrl,"功能路径",200,false,false,false,false,false))
					{	
						return false;
					}
					if(orderBy.value=="" || isNaN(orderBy.value) || orderBy.value.indexOf('.')>-1 || orderBy.value.trim()=="")
					{
						alert("请输入整数序号！");
						orderBy.select();
						return false;
					}
					if(!checkFormdata(description,"功能描述",50,false,false,false,false,false))
					{	
						return false;
					}
					//查询所有功能并比较是否与输入功能编号相同
					queryAllFunction(functionNo,url);					
				}
			}
			
			//查询所有系统功能
		
			function queryAllFunction(functionNo,url){
				$.ajax({
					type:'POST',
					url:"sysFunction/queryAllFunction.do?requestAction=queryByAjax",
					dateType:"json",
					success: function(msg){
					
						var flag = "true";
						var sysFuns = msg.sysFunctions;
						var funcNo = functionNo.value.trim();
						if(sysFuns != null){
						
							for(i=0;i<sysFuns.length;i++)
							{
								if(sysFuns[i].functionNo.trim()==funcNo)
								{
									//存在相同编号
									flag="false";
									alert("系统功能编号已经存在，请重新输入！");
									functionNo.select();									
									break;								
								}
							}
						}
						if(flag=="true"){  //请求后台检查菜单名称是否重复
									//document.PageForm.action = url;
									//document.PageForm.submit();
									var parentNo = document.getElementById("parentFunctionNo").value;
									var v1 = document.getElementById("functionName").value;										
									document.getElementById("functionName").value=v1.replace(/\s+/g,"");
									checkNamesubItem = document.getElementById("functionName").value;
									
									$.ajax({   //请求后台检查菜单名称是否重复
										type:'POST',
										url:"sysFunction/CheckNamesubItem.do",
										dateType:"json",	
										data:"checkNamesubItem="+checkNamesubItem+"&parentNo="+parentNo,
										success: function(msg){
											if(msg.message=="exists"){
												alert("系统菜单名称已经存在请重新输入！！");
												return false;
											}else{
												document.PageForm.action = url;
												document.PageForm.submit();
											}
										},
										error: function(msg){}
									});
						}
					},
					error: function(msg){
						alert("查询系统功能失败,请联系管理员!");
					}
				});
			}
			
			
			-->
		</SCRIPT>
	</HEAD>
	<body>
	<div id=mainzone>
		<div id="body">
			<!-- 提交表单 -->
			<FORM id=PageForm name=PageForm  method=post>
				<!-- 系统功能路径 -->
				<div class="loc">
					<div class="groupmenu" id="groupmenu"></div>
					<!-- 
					<a href="<%=path %>/login/mainFrame.do"></a> &raquo;
					-->
					<span>系统管理</span> &raquo;
					<span>功能权限管理</span> &raquo;
					<a href="<%=path %>/sysFunction/queryAllFunction.do">功能菜单维护</a> &raquo;
					<span class="last">新增功能菜单</span>
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
								新建系统功能（<font color="red">* 为必填项</font>）
							</td>
						</tr>
						<tr>
							<td class="titl">
								<b>上级功能菜单：</b>
							</td>
							<td>
								<input id="parentFunctionNo" name="sysFunction.parentFunctionNo" type="hidden" value="${functionNo }" class="txt-150"/>
								<!-- 若functionNo为空或空字符串则表示新增功能为一级功能 -->
								<c:if test='${empty functionNo }'>
									一级功能
								</c:if>
								<c:if test='${!empty functionNo }'>
									${sysFunction.functionName }
								</c:if>
							</td>
						</tr>
						<tr>
							<td class="titl">
								<b>功能编号：</b>
							</td>
							<td>
								<input id="functionNo" name="sysFunction.functionNo" type="text" class="txt-150" maxlength="4"/>
								<font color="red">&nbsp;*</font>
							</td>
						</tr>
						<tr>
							<td class="titl">
								<b>功能名称：</b>
							</td>
							<td>
								<input id="functionName" name="sysFunction.functionName" type="text" class="txt-150"/>
								<font color="red">&nbsp;*</font>
							</td>
						</tr>
						<tr>
							<td class="titl">
								<b>功能路径：</b>
							</td>
							<td>
								<textarea id="functionUrl" name="sysFunction.url" rows="2" cols="20" class="txt-300"></textarea>
							</td>
						</tr>
						<tr>
							<td class="titl">
								<b>功能序号：</b>
							</td>
							<td>
								<input id="orderBy" name="sysFunction.orderBy" type="text" class="txt-150" maxlength="8"/>
								<font color="red">&nbsp;*</font>
							</td>
						</tr>
						<tr>
							<td class="titl">
								<b>状态：</b>
							</td>
							<td>
								<INPUT type=radio name="sysFunction.status" value=1 checked />
											可用&nbsp;&nbsp;
								<INPUT type=radio name="sysFunction.status" value=0 />
											不可用
								<INPUT type=radio name="sysFunction.status" value=2 />
											可用但隐藏
							</td>
						</tr>
						<tr>
							<td class="titl">
								<b>功能描述：</b>
							</td>
							<td>
								<textarea id="description" name="sysFunction.description" rows="2" cols="20" class="txt-300"></textarea>
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
							onclick="submitForm('sysFunction/saveFunction.do','add')"
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