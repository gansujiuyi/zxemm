<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.jiuyi.jyplat.util.DateUtils"%>
<jsp:directive.include file="/WEB-INF/content/base/tag.inc" />
<%String path = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<HEAD>
		<TITLE></TITLE>
		<META http-equiv=Content-Type content="text/html; charset=utf-8">
		<META http-equiv=Expires content=0/>
		<META http-equiv="refresh" content=30/>
		<script type="text/javascript" src="<%=path%>/js/jquery-1.6.js">
		</script>
		<script type="text/javascript" src="<%=path%>/js/base.js">
		</script>
		<link href="<%=path%>/css/Style1.css" rel="stylesheet" type="text/css" />
		<link href="<%=path%>/css/Style2.css" rel="stylesheet" type="text/css" />
	</HEAD>
	<body>
	<div id="mainzone">
		<div id="body">
			<FORM id="PageForm" name=PageForm method=post action=points/queryValidSysProcCtrl.do>
				<div id="itemList">
					<div class="loc">
						<div class="groupmenu" id="groupmenu"></div>
<!--						<a href="<%=path %>/login/mainFrame.do"></a>-->
						系统管理&raquo;系统任务管理&raquo;<span class="last">批处理监控</span>
					</div>
					<div style="margin:5px 0px;height:20px;border:solid 1px #e5f2f5;padding:5px 5px;">
						<div style="float:left;margin:5px 0px;">
							<h4>当前系统处理账务日期&nbsp;&nbsp;[<font color="blue">${sysParameter.sysWorkDate}</font>], &nbsp;&nbsp;
							执行状态&nbsp;&nbsp;
								[
								<c:if test="${sysParameter.sysState=='0' }"><font color="green">正常</font></c:if>
								<c:if test="${sysParameter.sysState=='1' }"><font color="blue">正在批处理</font></c:if>
								<c:if test="${sysParameter.sysState=='7' }"><font color="red">批处理异常</font></c:if>
								<c:if test="${sysParameter.sysState=='9' }"><font color="blue">批处理故障排除，请求系统继续处理</font></c:if>
								]
							</h4>
						</div>
					</div>
					
					
					<!-- 数据列表 -->
					<div id="PrintContent" class="mainCon">
						<table class="mainTab" id="itemListTab">
							<tr id="tabHeader">
								<th style="width: 10px"></th>
								<th>
									编号
								</th>
								<th>
									名称
								</th>
								<th>
									顺序
								</th>
								<th>
									类型
								</th>
								<th>
									周期
								</th>
								<th>
									异常等级
								</th>
								<th>
									执行日期
								</th>
								<th>
									执行状态
								</th>
								<th>
									记录状态
								</th>
								<th>
									执行描叙
								</th>

							</tr>
							<c:if test="${empty sysProcList }">
								<tr><td colspan=11 align="center" height="25"><font color="red">没有查询到相关记录</font></td></tr>
							</c:if>
							<c:forEach var="sysProcCtrl" items="${sysProcList}" varStatus="status">
								<tr style="background-color: <c:if test="${sysProcCtrl.taskProcState eq 0 && sysProcCtrl.procDate eq currentDate}">#AFD788</c:if><c:if test="${sysProcCtrl.taskProcState ne 0 && sysProcCtrl.procDate eq currentDate}">#F5A89A</c:if>">
									<td style="width: 10px"></td>
									<td>
										${sysProcCtrl.procID }
									</td>
									<td>
										${sysProcCtrl.taskDec }
									</td>
									<td>
										${sysProcCtrl.taskSeqNo }
									</td>
									<td>
										<c:if test="${sysProcCtrl.taskType=='1' }">数据下载</c:if>
										<c:if test="${sysProcCtrl.taskType=='2' }">数据导入</c:if>
										<c:if test="${sysProcCtrl.taskType=='3' }">自定义任务</c:if>
										<c:if test="${sysProcCtrl.taskType=='4' }">客户评分</c:if>
									</td>
									<td>
										<c:if test="${sysProcCtrl.taskCyc=='1' }">每日</c:if>
										<c:if test="${sysProcCtrl.taskCyc=='2' }">每旬</c:if>
										<c:if test="${sysProcCtrl.taskCyc=='3' }">每半月</c:if>
										<c:if test="${sysProcCtrl.taskCyc=='4' }">每月</c:if>
										<c:if test="${sysProcCtrl.taskCyc=='5' }">每季度</c:if>
										<c:if test="${sysProcCtrl.taskCyc=='6' }">每半年</c:if>
										<c:if test="${sysProcCtrl.taskCyc=='7' }">每年</c:if>
									</td>
									<td>
										<c:if test="${sysProcCtrl.taskErrorLvl=='0' }">一般</c:if>
										<c:if test="${sysProcCtrl.taskErrorLvl=='3' }">严重</c:if>
									</td>
									<td>
										${sysProcCtrl.procDate }
										<br/>
										${sysProcCtrl.procTime }
									</td>
									<td>
										<c:if test="${empty sysProcCtrl.taskProcState }"><font color="green">无状态</font></c:if>
										<c:if test="${sysProcCtrl.taskProcState=='0' }"><font color="green">正常</font></c:if>
										<c:if test="${sysProcCtrl.taskProcState=='1' }"><font color="red">失败</font></c:if>
									</td>
									<td>
										<c:if test="${sysProcCtrl.recState=='1' }"><font color="green">有效</font></c:if>
										<c:if test="${sysProcCtrl.recState=='0' }"><font color="red">失效</font></c:if>
									</td>
									<td>
										${sysProcCtrl.taskErrorDesc }
									</td>
								</tr>
							</c:forEach>
						</table>
					</div>
					<div class="headPageNavi">
						<!-- 增删改
						<div  style="float:left" class="tools">
						    <ul>
						        <li><a href="javascript:gotoListHome()"><img src="<%=path %>/images/template/b_ref.gif" alt="IMG" />刷新</a></li>
						        <li><a href="javascript:submitForm('points/addSysProcCtrl.do','add')"><img src="<%=path %>/images/template/b_new.png" alt="IMG" />新建</a></li>
						        <li><a href="javascript:submitForm('points/updateSysProcCtrl.do','modify')"><img src="<%=path %>/images/template/b_edit.png" alt="IMG" />编辑</a></li>
						        <li><a id="lnkBtnDel" href="javascript:submitForm('points/delSysProcCtrl.do','delete')"><img src="<%=path %>/images/template/b_del.png" alt="IMG" />删除</a></li>
						    </ul>
						</div>
						 -->
					</div>
					
				</div>
			</FORM>
		</div>
		</div>
	</BODY>

	<script type="text/javascript"> 
	//已选中项计总数
	var sltedItemCnt = 0;
	//奇偶行变色
	altRow(1, "itemListTab", "odd", "even");
	//鼠标划过行时变色
	hoverRow(1, "itemListTab", "over");
	//鼠标划过表头时变色
	//hoverTabHeader("itemListTab", "tbTitOver");
	//添加底部分页导航
	//$("#pagerTop").html($("#pagerBtm").html());
	//添加单击行事件
	//initRowClick(0);
	//初始化表头排序功能和显示当前排序字段
	//setOrderSign("[InputTime] DESC");
	</script>

</HTML>
