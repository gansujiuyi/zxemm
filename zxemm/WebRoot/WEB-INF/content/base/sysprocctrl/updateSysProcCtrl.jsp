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
				if(obj.value == "1" || obj.value == "2"  || obj.value == "3"){
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
								<b>任务编号：</b>
							</td>
							<td>
								<input readonly="readonly" id="procID" name="sysProcCtrl.procID" value="${sysProcCtrl.procID}" type="text" maxlength="20" 
									class="txt-150"/>
								<font color="red">&nbsp;*</font>
							</td>
						</tr>
						<tr>
							<td class="titl">
								<b>任务名称：</b>
							</td>
							<td>
								<input id="taskDec" name="sysProcCtrl.taskDec" value="${sysProcCtrl.taskDec }" class="txt-150"/>
							</td>
						</tr>
						<tr>
							<td class="titl">
								<b>任务类型：</b>
							</td>
							<td>
								<select id="taskType" name="sysProcCtrl.taskType" class="sel-156">
									<option value="1" <c:if test="${sysProcCtrl.taskType=='1' }">selected="selected"</c:if>>1-数据下载</option>
									<option value="2" <c:if test="${sysProcCtrl.taskType=='2' }">selected="selected"</c:if>>2-数据导入</option>
									<option value="3" <c:if test="${sysProcCtrl.taskType=='3' }">selected="selected"</c:if>>3-自定义任务</option>
									<option value="4" <c:if test="${sysProcCtrl.taskType=='4' }">selected="selected"</c:if>>4-客户评分</option>
<!--									<option value="5" <c:if test="${sysProcCtrl.taskType=='5' }">selected="selected"</c:if>>客户经理考核</option>-->
<!--									<option value="6" <c:if test="${sysProcCtrl.taskType=='6' }">selected="selected"</c:if>>机构考核</option>-->
								</select>
							</td>
						</tr>
						<tr>
							<td class="titl">
								<b>执行顺序：</b>
							</td>
							<td>
								<input id="taskSeqNo" name="sysProcCtrl.taskSeqNo" value="${sysProcCtrl.taskSeqNo }" type="text" maxlength="20" 
									class="txt-150"/>
								<font color="red">&nbsp;*</font>
								<!-- 执行时间 -->
								<input type="hidden" name="sysProcCtrl.workDate" value="${sysProcCtrl.workDate }" />
								<input type="hidden" name="sysProcCtrl.procDate" value="${sysProcCtrl.procDate }" />
								<input type="hidden" name="sysProcCtrl.procTime" value="${sysProcCtrl.procTime }" />
								<input type="hidden" name="sysProcCtrl.taskErrorDesc" value="${sysProcCtrl.taskErrorDesc }" />
							</td>
						</tr>
						<tr>
							<td class="titl">
								<b>异常等级：</b>
							</td>
							<td>
								<select id="taskErrorLvl" name="sysProcCtrl.taskErrorLvl" value="${sysProcCtrl.taskErrorLvl }" class="sel-156">
									<option value="0" <c:if test="${sysProcCtrl.taskErrorLvl=='0' }">selected="selected"</c:if>>一般</option>
									<option value="3" <c:if test="${sysProcCtrl.taskErrorLvl=='3' }">selected="selected"</c:if>>严重</option>
								</select>
							</td>
						</tr>
						<tr>
							<td class="titl">
								<b>执行周期：</b>
							</td>
							<td>
								<select id="taskCyc" name="sysProcCtrl.taskCyc" class="sel-156">
									<option value="1" <c:if test="${sysProcCtrl.taskCyc=='1' }">selected="selected"</c:if>>1-每日</option>
									<option value="2" <c:if test="${sysProcCtrl.taskCyc=='2' }">selected="selected"</c:if>>2-每旬</option>
									<option value="3" <c:if test="${sysProcCtrl.taskCyc=='3' }">selected="selected"</c:if>>3-每半月</option>
									<option value="4" <c:if test="${sysProcCtrl.taskCyc=='4' }">selected="selected"</c:if>>4-每月</option>
									<option value="5" <c:if test="${sysProcCtrl.taskCyc=='5' }">selected="selected"</c:if>>5-每季度</option>
									<option value="6" <c:if test="${sysProcCtrl.taskCyc=='6' }">selected="selected"</c:if>>6-每半年</option>
									<option value="7" <c:if test="${sysProcCtrl.taskCyc=='7' }">selected="selected"</c:if>>7-每年</option>
								</select>
							</td>
						</tr>
						<tr>
							<td class="titl">
								<b>任务(JAVA类)：</b>
							</td>
							<td>
								<input id="task" name="sysProcCtrl.task" value="${sysProcCtrl.task }" type="text"
									class="txt-300" style="width: 450px;" />
							</td>
						</tr>
						<tr>
							<td class="titl">
								<b>任务参数一：</b>
							</td>
							<td>
								<input id="para1" name="sysProcCtrl.para1" value="${sysProcCtrl.para1 }" type="text"
									class="txt-300" style="width: 450px;" />
							</td>
						</tr>
						<tr>
							<td class="titl">
								<b>任务参数二：</b>
							</td>
							<td>
								<input id="para2" name="sysProcCtrl.para2" value="${sysProcCtrl.para2 }" type="text"
									class="txt-300" style="width: 450px;" />
							</td>
						</tr>
						<tr>
							<td class="titl">
								<b>任务参数三：</b>
							</td>
							<td>
								<input id="para3" name="sysProcCtrl.para3" value="${sysProcCtrl.para3 }" type="text"
									class="txt-300" style="width: 450px;" />
							</td>
						</tr>
						<tr>
							<td class="titl">
								<b>记录状态：</b>
							</td>
							<td>
								<select id="recState" name="sysProcCtrl.recState" class="sel-156">
									<option value="1" <c:if test="${sysProcCtrl.recState=='1' }">selected="selected"</c:if>>有效</option>
									<option value="0" <c:if test="${sysProcCtrl.recState=='0' }">selected="selected"</c:if>>无效</option>
								</select>
								<!-- 执行状态 -->
								<input type="hidden" name="sysProcCtrl.taskProcState" value="${sysProcCtrl.taskProcState}" />
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
							onclick="submitForm('acegi/saveEditSysProcCtrl.do','add')"
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