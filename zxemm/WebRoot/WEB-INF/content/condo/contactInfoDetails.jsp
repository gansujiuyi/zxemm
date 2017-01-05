<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/content/base/tag.inc" />
<%String path = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<HEAD>
<title>合同-查看详情</title>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<META http-equiv=Expires content=0>
<script type="text/javascript" src="<%=path%>/js/jquery-1.6.js"></script>
<script type="text/javascript" src="<%=path%>/js/base.js"></script>
<link href="<%=path%>/css/Style1.css" rel="stylesheet" type="text/css" />
<link href="<%=path%>/css/Style2.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="<%=path %>/js/timerControl/WdatePicker.js"></script>
<style>
.thStyle{width: 150px;text-align: center;}
.mainTab th{width: 10%}
.a-bluebtn{display:inline-block; padding:5px 10px; background: #52BADF; float:right; margin-right:10px; color:#fff;}
.a-bluebtn img{margin:0 10px -4px 0;}
</style>
	</head>
	<body>
	<div id=mainzone>
		<div id="body">
			<form id=PageForm name=PageForm method=post action="">
				<div id="itemList">
					<div class="loc">
						<div class="groupmenu" id="groupmenu"></div>
						<span>新房资金监管</span> &raquo;
						<a href="javascript:gotoListHome()">购置新房管理</a> &raquo; 
						<span>新房资金监管</span> &raquo;
						<span class="last">购房合同信息详情</span>
					</div>
					
					<!-- 数据列表 -->
					<div id="PrintContent" class="mainCon">
							<table class="mainTab" id="itemListTab">
								<tr id="tabHeader">
									<th class="thStyle" rowspan="2">合同基本信息</th>
									<td class="thStyle">合同编号</td>
									<td class="thStyle">合同金额</td>
									<td class="thStyle">地幢号</td>
									<td class="thStyle">建筑面积(㎡)</td>
									<td class="thStyle">室号</td>
									<td class="thStyle">项目名称</td>
									<td class="thStyle">企业名称</td>
									<td class="thStyle">合同状态</td>
									<td class="thStyle">创建时间</td>
								</tr>
								<tr >
									<td class="thStyle">${contactInfo.contactNo}</td>
									<td class="thStyle"><fmt:formatNumber value="${contactInfo.contactAmt}" pattern="#.00"/></td>
									<td class="thStyle">${contactInfo.houseNo}</td>
									<td class="thStyle">${contactInfo.houseArea}</td>
									<td class="thStyle">${contactInfo.roomNo}</td>
									<td class="thStyle">${contactInfo.projectName}</td>
									<td class="thStyle">${contactInfo.companyName}</td>
									<td class="thStyle">
										<c:choose>
												<c:when test="${contactInfo.status eq '0000'}">
													订单刚创建
												</c:when>
												<c:when test="${contactInfo.status eq '1111'}">
													退款中
												</c:when>
												<c:when test="${contactInfo.status eq '2222'}">
													已退款
												</c:when>
												<c:when test="${contactInfo.status eq '3333'}">
													支付完成
												</c:when>
											</c:choose>	
									</td>
									<td class="thStyle">暂无</td>
								</tr>
							</table>
							<br/>
							<table class="mainTab" id="itemListTab">
								<tr id="tabHeader">
									<th class="thStyle" rowspan="20">购买人信息</th>
									<td class="thStyle">姓名</td>
									<td class="thStyle">手机号</td>
									<td class="thStyle">身份证</td>
									<td class="thStyle">卡号</td>
								</tr>
								<c:choose>
									<c:when test="${ empty buyerList}">
										<tr><td colspan=4 align="center" height="25"><font color="red">没有查询到相关记录</font></td></tr>
									</c:when>
									<c:otherwise>
										<c:forEach items="${buyerList}" var="buyer">
											<tr >
												<td class="thStyle">${buyer.buyerName}</td>
												<td class="thStyle">${buyer.phoneNo}</td>
												<td class="thStyle">${buyer.idNo}</td>
												<td class="thStyle">${buyer.cardNo}</td>
											</tr>
										</c:forEach>
									</c:otherwise>
								</c:choose>	
							</table>
							<br/>
							<table class="mainTab" id="itemListTab">
								<tr id="tabHeader">
									<th class="thStyle" rowspan="20">支付信息</th>
									<td class="thStyle">银行卡号</td>
									<td class="thStyle">付款日期</td>
									<td class="thStyle">付款金额</td>
									<td class="thStyle">交易状态</td>
								</tr>
								<c:choose>
									<c:when test="${ empty payList}">
										<tr><td colspan="4" align="center" height="25"><font color="red">没有查询到相关记录</font></td></tr>
									</c:when>
									<c:otherwise>
										<c:forEach items="${payList}" var="payInfo">
											
											<tr >
												<td class="thStyle">${payInfo.payCardNo}</td>
												<td class="thStyle">${payInfo.payDay}</td>
												<td class="thStyle"><fmt:formatNumber value="${payInfo.payMoney}" pattern="#.00"/></td>
												<c:choose>
												<c:when test="${ payInfo.payStatus eq '0010'}">
												<td class="thStyle">支付成功</td>
												</c:when>
												<c:otherwise>
												<td class="thStyle">无状态</td>
												</c:otherwise>
												</c:choose>
											</tr>
											
										</c:forEach>
									</c:otherwise>
								</c:choose>	
							</table>
							<br/>
							<table class="mainTab" id="itemListTab">
								<tr id="tabHeader">
									<th class="thStyle" rowspan="20">退款信息</th>
									<td class="thStyle">银行卡号</td>
									<td class="thStyle">付款日期</td>
									<td class="thStyle">付款金额</td>
									<td class="thStyle">交易状态</td>
								</tr>
								<c:choose>
									<c:when test="${ empty refundList}">
										<tr><td colspan="4" align="center" height="25"><font color="red">没有查询到相关记录</font></td></tr>
									</c:when>
									<c:otherwise>
										<c:forEach items="${refundList}" var="refundInfo">
											
											<tr >
												<td class="thStyle">${refundInfo.payCardNo}</td>
												<td class="thStyle">${refundInfo.payDay}</td>
												<td class="thStyle">${refundInfo.payMoney}</td>
												<c:choose>
												<c:when test="${ refundInfo.payStatus eq '0010'}">
												<td class="thStyle">支付成功</td>
												</c:when>
												<c:otherwise>
												<td class="thStyle">无状态</td>
												</c:otherwise>
												</c:choose>
											</tr>
											
										</c:forEach>
									</c:otherwise>
								</c:choose>	
							</table>
						</div>
				</div>
				<!-- 提交按钮 -->
					<div style="padding: 10px; padding-left: 10px;">
						<div style="width: 250px; float: left;">
							 <a href="javascript:javascript:history.go(-1);">
								<img src="<%=path%>/images/goback-btn.jpg" alt="IMG" />
							</a>
						</div>
						<div class="clr"></div>
					</div>
			</form>
		</div>
<form id="capitalStateF" name="capitalStateF" action="capitalManage/modifyCapitalByIdInfo.do" method="post">
	<input id="orderId" type="hidden" name="orderinfo.orderId"/>
	<input id="opeType" type="hidden" name="opeType"/>
	<input id="state" type="hidden" name="orderinfo.orderExStatus"/>
</form>
		</div>
	</body>

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