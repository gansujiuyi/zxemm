<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/content/base/tag.inc" />
<%String path = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<HEAD>
		<TITLE>活动审核列表</TITLE>
		<META http-equiv=Content-Type content="text/html; charset=utf-8">
		<META http-equiv=Expires content=0>
		<script type="text/javascript" src="<%=path%>/js/jquery-1.6.js"></script>
		<script type="text/javascript" src="<%=path%>/js/base.js"></script>
		<link href="<%=path%>/css/Style1.css" rel="stylesheet" type="text/css" />
		<link href="<%=path%>/css/Style2.css" rel="stylesheet" type="text/css" />
		<script language="javascript" type="text/javascript" src="<%=path %>/js/timerControl/WdatePicker.js"></script>
		<SCRIPT language=JavaScript>
		<!--
		//按钮提交事件
		function submitForm(url, act)
		{
			//调用获取被选中的供应商编号的方法
			itemCheckbox_changed();
		    if(act == "add"){
				document.PageForm.action = url;
				document.PageForm.submit();
		    }
		
		    if(act == "authAct"){
		        if(sltedItemCnt == 0){
				    alert("请选择需要审核的活动！");
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
			    if(confirm("您确定要将选中的活动进行审核操作吗?")){
					$.ajax({
						type:'POST',
						url: url + "&activityId=" + idstr,
						dateType:"json",
						success: function(msg){
							if(msg.message=="succ"){//操作成功
								alert("操作成功");
								document.location.href = "points/authActivityList.do";
							}else{//操作失败
								alert("操作失败,请稍候重试");
							}
						},
						error: function(msg){
							alert("系统错误,请稍微重试!");
						}
					});
				}
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
	</head>
	<body>
	<div id=mainzone>
		<div id="body">
			<FORM id=PageForm name=PageForm method=post action="points/authActivityList.do">
				<div id="itemList">
					<div class="loc">
						<div class="groupmenu" id="groupmenu"></div>
						<!--  
						<a href="<%=path %>/login/mainFrame.do"></a> &raquo;
						-->
						<span>积分管理</span> &raquo;
						<span>营销活动管理</span> &raquo;
						<a href="javascript:gotoListHome()">活动审核</a> &raquo; 
						<span class="last">待审核活动列表</span>
					</div>


					<!-- 全/反选 -->
					<div class="chooseCase">
						<!-- 搜索查询 -->
						<div style="float: left" class="search">
							<table width="100%" border="0">
	              <tr>
	                <td width="5%" align="right">
	                	活动名称：
	                </td>
									<td width="10%">
										<input id="activityName" name="marketing.activityName" value="${marketing.activityName }"
											type="text" class="txt" />
									</td>
									<td width="10%" align="right">
										活动描述：
									</td>
									<td width="15%">
										<input id="description" name="marketing.description" value="${marketing.description }"
											type="text" class="txt" />
									</td>
									<td width="10%" align="right">
										活动起止时间：
									</td>
									<td>
										<INPUT id="startTime" style="width: 150px;"  name="marketing.startTime" value="${marketing.startTime}" class=Wdate onClick="WdatePicker({dateFmt:'yyyyMMdd HH:mm:ss'})" readonly="readonly" />
										--
										<INPUT id="endTime" style="width: 150px;"  name="marketing.endTime" value="${marketing.endTime}" class=Wdate onClick="WdatePicker({dateFmt:'yyyyMMdd HH:mm:ss'})" readonly="readonly" />
									  <a href="javascript:submitForm('points/authActivityList.do','search')">
											<img src="<%=request.getContextPath() %>/images/search-btn.jpg" style="vertical-align:middle; padding-left:10px;" />
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
										onclick="checkAll(this.checked);" value="checkbox" />
								</th>
								<th>
									活动编号
								</th>
								<th>
									活动名称
								</th>
								<th>
									活动描述
								</th>
								<th>
									活动广告
								</th>
								<th>
									开始时间
								</th>
								<th>
									结束时间
								</th>
								<th>
									自定义积分规则
								</th>
								<th>
									规则试算结果
								</th>
								<th>
									创建人
								</th>
								<th>
									创建时间
								</th>
							</tr>
							<c:if test="${empty pageFinder.data }">
								<tr><td colspan=12 align="center" height="25"><font color="red">没有查询到相关记录</font></td></tr>
							</c:if>
							<c:forEach var="marketing" items="${pageFinder.data}"
								varStatus="status">
								<tr>
									<td>
										<input id="activityId_${marketing.activityId }" name="chkItem"
											value="${marketing.activityId }" type="checkbox"
											onclick="itemCheckbox_changed();" />
									</td>
									<td>
										${marketing.activityId }
									</td>
									<td>
										${marketing.activityName }
									</td>
									<td>
										${marketing.description }
									</td>
									<td>
										<a href="points/adPreview.do?adId=${marketing.activityAD.adId}" target="_blank"  style="color: blue;cursor: pointer;">${marketing.activityAD.adName }</a>
									</td>
									<td>
										${marketing.startTime }
									</td>
									<td>
										${marketing.endTime }
									</td>
									<td>
										${marketing.rules.rulesTitle }
									</td>
									<td>
										${marketing.ruleRunResult }
									</td>
									<td>
										${marketing.createBy.operName }
									</td>
									<td>
										${marketing.createTime }
									</td>
								</tr>
							</c:forEach>
						</table>
					</div>
					<div class="headPageNavi">
						<!-- 增删改 -->
						<div style="float: left" class="tools">
							<ul>
								<li>
									<a href="javascript:gotoListHome()"><img
											src="<%=path%>/images/template/b_ref.gif" alt="IMG" />刷新</a>
								</li>
								<li>
									<a href="javascript:submitForm('points/authActivity.do?status=0004','authAct')"><img
											src="<%=path%>/images/template/b_new.png" alt="IMG" />审核通过</a>
								</li>
								<li>
									<a href="javascript:submitForm('points/authActivity.do?status=0005','authAct')"><img
											src="<%=path%>/images/template/b_edit.png" alt="IMG" />审核失败</a>
								</li>
							</ul>
						</div>

						<!-- 分页 -->
						<div style="float: right" class="pageNavi" id="pagerBtm">
							<c:import url="../../base/page.jsp?formId=PageForm"
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