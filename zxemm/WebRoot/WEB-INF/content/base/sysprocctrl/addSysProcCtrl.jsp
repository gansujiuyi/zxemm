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
					if(!checkFormdata(document.getElementById("procID"),"任务编号",3,true,true,false,false,false))
					{	
						return false;
					}
					if(!checkFormdata(document.getElementById("taskDec"),"任务名称",20,true,true,false,false,false))
					{	
						return false;
					}
					if(!checkFormdata(document.getElementById("taskSeqNo"),"执行顺序",2,true,true,false,true,true))
					{	
						return false;
					}
					if(!checkFormdata(document.getElementById("taskErrorLvl"),"异常等级",2,true,true,false,false,true))
					{	
						return false;
					}
					document.PageForm.action = url;
					document.PageForm.submit();
				}
			}
			function typeChange(obj){
				if(obj.value == "1" || obj.value == "2" || obj.value == "3"){
					document.getElementById("task").disabled = false;
					
				}else{
					document.getElementById("task").disabled = true;
					
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
					<span class="last">新增系统任务</span>
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
								系统任务（新建对象）
							</td>
						</tr>
						<tr>
							<td class="titl">
								<b>任务编号：</b>
							</td>
							<td>
								<input id="procID" name="sysProcCtrl.procID" type="text" maxlength="20" class="txt-150"/>
								<font color="red">&nbsp;*</font>
							</td>
						</tr>
						<tr>
							<td class="titl">
								<b>任务名称：</b>
							</td>
							<td>
								<input id="taskDec" name="sysProcCtrl.taskDec" class="txt-150"/>
							</td>
						</tr>
						<tr>
							<td class="titl">
								<b>任务类型：</b>
							</td>
							<td>
								<select id="taskType" name="sysProcCtrl.taskType" onchange="typeChange(this)" class="sel-156">
									<option value="1">1-数据下载</option>
									<option value="2">2-数据导入</option>
									<option value="3">3-自定义任务</option>
									<option value="4">4-客户评分</option>
									<!--<option value="5">5-客户经理考核</option>-->
									<!--<option value="6">6-机构考核</option>-->
								</select>
							</td>
						</tr>
						<tr>
							<td class="titl">
								<b>执行顺序：</b>
							</td>
							<td>
								<input id="taskSeqNo" name="sysProcCtrl.taskSeqNo" type="text" maxlength="20" class="txt-150"/>
								<font color="red">&nbsp;*</font>
							</td>
						</tr>
						<tr>
							<td class="titl">
								<b>异常等级：</b>
							</td>
							<td>
								<select id="taskErrorLvl" name="sysProcCtrl.taskErrorLvl" class="sel-156">
									<option value="0">一般</option>
									<option value="3">严重</option>
								</select>
							</td>
						</tr>
						<tr>
							<td class="titl">
								<b>执行周期：</b>
							</td>
							<td>
								<select id="taskCyc" name="sysProcCtrl.taskCyc" class="sel-156">
									<option value="1">1-每日</option>
									<option value="2">2-每旬</option>
									<option value="3">3-每半月</option>
									<OPTION VALUE="4">4-每月</OPTION>
									<option value="5">5-每季度</option>
									<option value="6">6-每半年</option>
									<option value="7">7-每年</option>
								</select>
							</td>
						</tr>
						<tr>
							<td class="titl">
								<b>任务(JAVA类)：</b>
							</td>
							<td>
								<input id="task" name="sysProcCtrl.task" type="text" class="txt-300" style="width: 450px;" />
							</td>
						</tr>
						<tr>
							<td class="titl">
								<b>任务参数一：</b>
							</td>
							<td>
								<input id="para1" name="sysProcCtrl.para1" type="text" class="txt-300" style="width: 450px;" />
							</td>
						</tr>
						<tr>
							<td class="titl">
								<b>任务参数二：</b>
							</td>
							<td>
								<input id="para2" name="sysProcCtrl.para2" type="text" class="txt-300" style="width: 450px;" />
							</td>
						</tr>
						<tr>
							<td class="titl">
								<b>任务参数三：</b>
							</td>
							<td>
								<input id="para3" name="sysProcCtrl.para3" type="text" class="txt-300" style="width: 450px;" />
							</td>
						</tr>
						<tr>
							<td class="titl">
								<b>记录状态：</b>
							</td>
							<td>
								<select id="recState" name="sysProcCtrl.recState" class="sel-156">
									<option value="1">有效</option>
									<option value="0">无效</option>
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
							onclick="submitForm('acegi/saveAddedSysProcCtrl.do','add')"
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