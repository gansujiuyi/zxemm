<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/content/base/tag.inc" />
<%String path = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>新房购房合同页面</title>
		<meta http-equiv=Content-Type content="text/html; charset=utf-8" />
		<meta http-equiv=Expires content=0 />
		<script type="text/javascript" src="<%=path%>/js/jquery-1.6.js"></script>
		<script type="text/javascript" src="<%=path%>/js/base.js"></script>
		<link href="<%=path%>/css/Style1.css" rel="stylesheet" type="text/css" />
		<link href="<%=path%>/css/Style2.css" rel="stylesheet" type="text/css" />
	</head>
<script language=JavaScript>
	//按钮提交事件 
	function submitForm(url, act)
	{
	    //重新查询
	   if(act == "search")
	    {
	    	if(url.indexOf("?")>0)
	    	{
				url = url + "&forSearch=true";
			}
			else
			{
				url = url + "?forSearch=true";
			}
	        document.PageForm.action = url;
	        document.PageForm.submit();
	    }else{
	        window.location.href=url;
	    }
	}
</script>

	<body>
	<div id=mainzone>
		<div id="body">
			<form id="PageForm" name="PageForm" method="post" action="buyerNewHouse/getPageContactInfo.do">
				<div id="itemList">
					<div class="loc">
						<div class="groupmenu" id="groupmenu"></div>
						<span>新房资金监管</span> &raquo;
						<a href="javascript:gotoListHome()">购置新房管理</a> &raquo; 
						<span class="last">购房合同信息列表</span>
					</div>
						<!-- 全/反选 -->
						<div class="chooseCase">
							<!-- 搜索查询 -->
							<div style="float: left" class="search">
							<table width="100%" border="0">
								<tr>
									<td width="5%" >合同编号：<input id="contactNo" name="contactInfo.contactNo" value="${contactInfo.contactNo }" type="text" class="txt" /></td>
									<td width="10%" >
										<a href="javascript:submitForm('buyerNewHouse/getPageContactInfo.do','search')"><img src="<%=request.getContextPath() %>/images/search-btn.jpg" style="vertical-align:middle; padding-left:10px;"/></a>
									</td>
								</tr>
							</table>
							</div>
						</div>
						<!-- 数据列表 -->
						<div id="PrintContent" class="mainCon">
							<table class="mainTab" id="itemListTab">
								<tr id="tabHeader">
									<th>
										合同编号
									</th>
									<th>
										合同金额
									</th>
									<th>
										地幢号
									</th>
									<th>
										项目名称
									</th>
									<th>
										项目编号
									</th>
									<th>
										支付状态
									</th>
									<th>
										操作
									</th>
									<th>
										操作
									</th>
								</tr>
								<c:if test="${empty pageFinder.data }">
									<tr><td colspan=8 align="center" height="25"><font color="red">没有查询到相关记录</font></td></tr>
								</c:if>
								<c:forEach var="contact" items="${pageFinder.data}"
									varStatus="status">
									<tr>
										<td>
											${contact.contactNo }
										</td>
										<td>
											${contact.contactAmt }元
										</td>
										<td>
											${contact.houseNo }
										</td>
										<td>
											${contact.projectName }
										</td>
										<td>
											${contact.projectNo }
										</td>
										<td>
											<c:if test="${contact.payStatus == '0000'}">未支付</c:if>
											<c:if test="${contact.payStatus == '0010'}">支付完成</c:if>
											<c:if test="${contact.payStatus == '0011'}">部分支付</c:if>
											<c:if test="${contact.payStatus == '2222'}">退款完成</c:if>
										</td>
										<td>
											<a href="buyerNewHouse/searchContactInfoById.do?contactNo=${contact.contactNo }"><img src="<%=path%>/images/template/b_search.png" alt="IMG" />查看详情</a>
										</td>
										<td>
											<c:if test="${empty contact.operNo}">
											<a href="buyerNewHouse/getBuyHouseContactInfo.do?contactNo=${contact.contactNo }"><font color="blue">补录信息</font></a>
											</c:if>
											<c:if test="${not empty contact.operNo}">
											<%-- <c:if test="${contact.status == '1111'}">
											<a href="buyerNewHouse/refundPayInfo.do?contactNo=${contact.contactNo}"><font color="blue">申请退款</font></a>
											</c:if> --%>
											<c:if test="${contact.payStatus == '2222' && contact.status == '2222'}">--</c:if>
											<%-- <c:if test="${contact.payStatus == '0010' && contact.status == '3333'}">--</c:if> --%>
											<c:if test="${(contact.payStatus == '0000' && contact.status != '1111' && contact.status != '3333')  || contact.payStatus == '0011'}">
											<%-- <a href="buyerNewHouse/condoPayInfo.do?contactNo=${contact.contactNo}"><font color="blue">支付</font></a> --%>
											<a href="<%=path%>/contractContinual/ContractContinual.do?contactNo=${contact.contactNo}&houseNo=${contact.houseNo}"><font color="blue">支付</font></a>
											</c:if>
											<c:if test="${ contact.payStatus=='0010' && contact.status == '4444'}">
											<a href="buyerNewHouse/refundPayInfo.do?contactNo=${contact.contactNo}"><font color="blue">申请退款</font></a>
											</c:if>
											</c:if>
										</td>
									</tr>
								</c:forEach>
							</table>
						</div>
						<div class="headPageNavi">
							<%-- <!-- 分页 -->
							<div style="float: right" class="pageNavi" id="pagerBtm">
								<c:import url="../base/page.jsp?formId=PageForm"
									charEncoding="utf-8"></c:import>
							</div> --%>
						</div>
				</div>
			</form>
		</div>
	</div>
	</body>
	<!-- 首次请求本页面仅显示查询门店的条件 -->
	<c:if test="${!empty pageFinder}">
		<script type="text/javascript"> 
		//已选中项计总数
		var sltedItemCnt = 0;
		//奇偶行变色
		altRow(1, "itemListTab", "odd", "even");
		//鼠标划过行时变色
		hoverRow(1, "itemListTab", "over");
		</script>
	</c:if>
</html>