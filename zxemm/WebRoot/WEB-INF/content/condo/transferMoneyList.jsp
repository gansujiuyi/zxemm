<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/content/base/tag.inc" />
<%String path = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>等待划款分页查询</title>
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
			<form id="PageForm" name="PageForm" method="post" action="auditTransferMoney/getPageTransferMoney.do">
				<div id="itemList">
					<div class="loc">
						<div class="groupmenu" id="groupmenu"></div>
						<span>新房资金监管</span> &raquo;
						<a href="javascript:gotoListHome()">审核划款管理</a> &raquo; 
						<span class="last">划款申请列表</span>
					</div>
						<!-- 全/反选 -->
						<div class="chooseCase">
							<!-- 搜索查询 -->
							<div style="float: left" class="search">
							<table width="100%" border="0">
								<tr>
									<td width="5%" >地幢号：<input id="houseNo" name="transferMoney.houseNo" value="${transferMoney.houseNo }" type="text" class="txt" /></td>
									<td width="10%" >
										<a href="javascript:submitForm('auditTransferMoney/getPageTransferMoney.do','search')"><img src="<%=request.getContextPath() %>/images/search-btn.jpg" style="vertical-align:middle; padding-left:10px;"/></a>
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
										地幢号
									</th>
									<th>
										划款金额
									</th>
									<th>
										出账卡号
									</th>
									<th>
										出账账户名称
									</th>
									<th>
										出账人电话
									</th>
									<th>
										出账人身份证编号
									</th>
									<th>
										操作
									</th>
								</tr>
								<c:if test="${empty auditTransferMoney }">
									<tr><td colspan=7 align="center" height="25"><font color="red">没有查询到相关记录</font></td></tr>
								</c:if>
								<c:if test="${ not empty auditTransferMoney }">
									<tr>
										<td>
											${auditTransferMoney.houseNo }
										</td>
										<td>
											<fmt:formatNumber value="${auditTransferMoney.amt }" pattern="#.00"/>元
										</td>
										<td>
											${auditTransferMoney.outCardNo }
										</td>
										<td>
											${auditTransferMoney.outCardName }
										</td>
										<td>
											${auditTransferMoney.outPhone }
										</td>
										<td>
											${auditTransferMoney.outIdNo }
										</td>
										<td>
											<c:if test="${auditTransferMoney.state == '1111'}">
											<a href="auditTransferMoney/getByHouseNo.do?transferMoney.houseNo=${auditTransferMoney.houseNo}"><font color="blue">补录信息</font></a>
											</c:if>
											<c:if test="${auditTransferMoney.state == '0000'}">
											<a href="auditTransferMoney/transferPayInfo.do?transferMoney.id=${auditTransferMoney.id}"><font color="blue">划款</font></a>
											</c:if>
											<c:if test="${auditTransferMoney.state == '0010'}">
											划款完成
											</c:if>
										</td>
									</tr>
									</c:if>
							</table>
						</div>
					<%-- 	<div class="headPageNavi">
							<!-- 分页 -->
							<div style="float: right" class="pageNavi" id="pagerBtm">
								<c:import url="../base/page.jsp?formId=PageForm"
									charEncoding="utf-8"></c:import>
							</div>
						</div> --%>
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