<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/content/base/tag.inc" />
<%String path = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>合同页面</title>
		<meta http-equiv=Content-Type content="text/html; charset=utf-8" />
		<meta http-equiv=Expires content=0 />
		<script type="text/javascript" src="<%=path%>/js/jquery-1.6.js"></script>
		<script type="text/javascript" src="<%=path%>/js/base.js"></script>
		<link href="<%=path%>/css/Style1.css" rel="stylesheet" type="text/css" />
		<link href="<%=path%>/css/Style2.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
	<div id=mainzone>
		<div id="body">
			<form id="PageForm" name="PageForm" method="post" action="buyerNewHouse/getPageContactInfo.do">
				<div id="itemList">
					<div class="loc">
						<div class="groupmenu" id="groupmenu"></div>
						<span>往来账统计查询列表</span> &raquo;
						<a href="<%=path%>/capitalManage/queryContractByBuild.do?buildingId=${buildingId}">合同信息列表</a> &raquo; 
						<span class="last">合同已支付信息</span>
					</div>
						<!-- 数据列表 -->
						<div id="PrintContent" class="mainCon">
							<table class="mainTab" id="itemListTab">
								<tr id="tabHeader">
									<th>
										合同编号
									</th>
									<th>
										交易状态
									</th>
									<th>
										支付金额
									</th>
									<th>
										流水生成时间
									</th>
									<th>
										付款日期
									</th>
									<th>
										退款时间
									</th>
									<th>
										付款流水号
									</th>
									<th>
										户名
									</th>
								</tr>
								<c:if test="${empty condoPayInfos }">
									<tr><td colspan=7 align="center" height="25"><font color="red">没有查询到相关记录</font></td></tr>
								</c:if>
								<c:forEach var="contactPay" items="${condoPayInfos}"
									varStatus="status">
									<tr>
										<td>
											${contactPay.contactNo }
										</td>
										<td>
											<c:if test="${contactPay.payStatus == '0000'}">未支付</c:if>
											<c:if test="${contactPay.payStatus == '0010'}">支付完成</c:if>
											<c:if test="${contactPay.payStatus == '2222'}">退款完成</c:if>
										</td>
										<td>
											${contactPay.payMoney }元
										</td>
										<td>
											${contactPay.createTime}
										</td>
										<td>
											${contactPay.payDay }
										</td>
										<td>
											${contactPay.refundTime }
										</td>
										<td>
											${contactPay.payTradeNo }
										</td>
										<td>
											${contactPay.oppName }
										</td>
									</tr>
								</c:forEach>
							</table>
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