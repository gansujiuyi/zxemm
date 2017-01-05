<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/content/base/tag.inc" />
<%String path = request.getContextPath();%>
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
		<link href="<%=path%>/css/Style1.css" rel="stylesheet" type="text/css" />
		<link href="<%=path%>/css/Style2.css" rel="stylesheet" type="text/css" />
		<SCRIPT language=JavaScript>
<!--
//按钮提交事件

function submitForm(url, act)
{
	itemCheckbox_changed();
    if(act == "add"){
		document.PageForm.action = url;
		document.PageForm.submit();
    }

    if(act == "delete"){
        if(sltedItemCnt == 0){
		    alert("没有选择任务信息！");
		    return;
		}
		if(! confirm("确定要删除吗(共"+sltedItemCnt+"项)?")){
		    return;
		}
		var ids  = document.PageForm.chkItem;
		var idstr = "";
		if(ids.id)
			idstr = ids.value;
		else
			for( i=0 ; i < ids.length; i++ ){
	    		if( ids[i].checked == true ){
	    			idstr += ids[i].value + ",";
	    		}
	    	}
	    if(idstr.length > 0 && idstr.indexOf(",")>0)
	    	idstr = idstr.substring(0,idstr.length-1);
		document.PageForm.action = url + "?procIds=" + idstr;
		document.PageForm.submit();
    }

    if(act == "modify"){
        if(sltedItemCnt == 0){
		    alert("没有选择要进行修改的任务信息！");
		    return;
		}
		if(sltedItemCnt > 1){
		    alert("请选择唯一任务信息！");
		    return;
		}
		
   		var ids  = document.PageForm.chkItem;
		var idstr = "";
    	if(ids.id)
			idstr = ids.value;
		else
			for( i=0 ; i < ids.length; i++ ){
	    		if( ids[i].checked == true ){
	    			idstr += ids[i].value + ",";
	    		}
	    	}
		if(idstr.length > 0 && idstr.indexOf(",")>0)
	    	idstr = idstr.substring(0,idstr.length-1);
   			
		document.PageForm.action = url + "?sysProcCtrl.procID=" + idstr;
		document.PageForm.submit();
    }

    //重新查询
    if(act == "search"){
    	if(url.indexOf("?")>0){
			url = url + "&forSearch=true";
		}else{
			url = url + "?forSearch=true";
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
			<FORM id="PageForm" name=PageForm method=post action=points/queryAllSysProcCtrl.do>
				<div id="itemList">
					<div class="loc">
						<div class="groupmenu" id="groupmenu"></div>
<!--						<a href="<%=path %>/login/mainFrame.do"></a>-->
						系统管理&raquo;批处理管理&raquo; <a href="javascript:gotoListHome()">批处理维护</a> &raquo; <span class="last">列表</span>
					</div>
					<div style="margin:5px 0px;height:20px;border:solid 1px #e5f2f5;padding:5px 5px;">
						<div style="float:left;margin:5px 0px;">
							<h4>&nbsp;&nbsp;&nbsp;当前系统处理账务日期&nbsp;&nbsp;[<a href="<%=path %>/points/updateSysParameter4Task.do" title="点击修改" style="color: blue;cursor: pointer;">
								${sysParameter.sysWorkDate}</a>], &nbsp;&nbsp;
							执行状态&nbsp;&nbsp;
								[<a href="<%=path %>/points/updateSysParameter4Task.do" title="点击修改" style="color: blue;cursor: pointer;">
								<c:if test="${sysParameter.sysState=='0' }">正常</c:if>
								<c:if test="${sysParameter.sysState=='1' }">正在批处理</c:if>
								<c:if test="${sysParameter.sysState=='7' }">批处理异常</c:if>
								<c:if test="${sysParameter.sysState=='9' }">批处理故障排除，请求系统继续处理</c:if>
								</a>]
							</h4>
						</div>
						<div style="float:left">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" onclick="javascript:document.location.href='<%=path %>/points/updateSysParameter4Task.do'" class="comon-btn" value="修改"/>
						</div>
					</div>
					
					<!-- 全/反选 -->
					<div class="chooseCase">
						<!-- 搜索查询 -->
						<div style="float: left" class="search">
							<table width="100%" border="0">
								<tr>
					                <td width="5%" align="right">任务名称：</td>
									<td >
	                                	<input id="taskDec" name="sysProcCtrl.taskDec" value="${sysProcCtrl.taskDec }" type="text" class="txt-150" /> 
										<a href="javascript:submitForm('points/queryAllSysProcCtrl.do','search')" >
											<img src="<%=request.getContextPath()%>/images/search-btn.jpg" style="vertical-align:middle; padding-left:10px;" />
										</a>
									</td>
				              	</tr>
	                        </table>
						</div>
					</div>
					
					
					<!-- 数据列表 -->
					<div id="PrintContent" class="mainCon">
						<table class="mainTab" id="itemListTab">
							<tr id="tabHeader">
								<th style="width: 50px" class="p">
									<input name="chkAll" type="checkbox"
										onclick="checkAll(this.checked);"
										value="checkbox" />
								</th>
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
							<c:if test="${empty pageFinder.data }">
								<tr><td colspan=11 align="center" height="25"><font color="red">没有查询到相关记录</font></td></tr>
							</c:if>
							<c:forEach var="sysProcCtrl" items="${pageFinder.data}" varStatus="status">
								<tr>
									<td>
										<input id="sysProcCtrlId_${sysProcCtrl.procID }" name="chkItem" value="${sysProcCtrl.procID }" type="checkbox" onclick="itemCheckbox_changed();"/>
									</td>
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
										<Br>
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
						<!-- 增删改 -->
						<div  style="float:left" class="tools">
						    <ul>
						        <li><a href="javascript:gotoListHome()"><img src="<%=path %>/images/template/b_ref.gif" alt="IMG" />刷新</a></li>
						        <li><a href="javascript:submitForm('points/addSysProcCtrl.do','add')"><img src="<%=path %>/images/template/b_new.png" alt="IMG" />新建</a></li>
						        <li><a href="javascript:submitForm('points/updateSysProcCtrl.do','modify')"><img src="<%=path %>/images/template/b_edit.png" alt="IMG" />编辑</a></li>
						        <li><a id="lnkBtnDel" href="javascript:submitForm('points/delSysProcCtrl.do','delete')"><img src="<%=path %>/images/template/b_del.png" alt="IMG" />删除</a></li>
						    </ul>
						</div>
						
						<!-- 分页 -->
						<div style="float: right" class="pageNavi" id="pagerBtm">
							<c:import url="../page.jsp?formId=PageForm"
								charEncoding="utf-8"></c:import>
						</div>
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
