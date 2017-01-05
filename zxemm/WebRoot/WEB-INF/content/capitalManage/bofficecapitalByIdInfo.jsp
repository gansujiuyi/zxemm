<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/content/base/tag.inc" />
<%String path = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<HEAD>
<title>资金监管-查看详情</title>
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
<script language=JavaScript>
function submitForm(url){
	document.PageForm.action = url;
	document.PageForm.submit();
}
function modifyState(orderId,state){
	$("#orderId").val(orderId);
	$("#state").val(state);
	$("#capitalStateF").submit();
}

function saveDKrecord(orderId,url,opeType){
	$("#orderId").val(orderId);
	$("#opeType").val(opeType);
	
	document.capitalStateF.action = url;
	document.capitalStateF.submit();
}

</script>
	</head>
	<body>
	<div id=mainzone>
		<div id="body">
			<form id=PageForm name=PageForm method=post action="">
				<div id="itemList">
					<div class="loc">
						<div class="groupmenu" id="groupmenu"></div>
						<span>资金监管</span> &raquo;
						<span>资金监管管理</span> &raquo; 
						<a href="capitalManage/queryCapitalInfoAll.do" >资金监管管理</a> &raquo; 
						<span class="last">资金监管查看详情</span>
					</div>
					
					<!-- 数据列表 -->
					<div id="PrintContent" class="mainCon">
							<table class="mainTab" id="itemListTab">
								<tr id="tabHeader">
									<th class="thStyle" rowspan="2">合同基本信息</th>
									<td class="thStyle">监管流水号</td>
									<td class="thStyle">房源编号</td>
									<td class="thStyle">创建时间</td>
									<td class="thStyle">现金金额（元）</td>
									<td class="thStyle">按揭金额（元）</td>
									<td class="thStyle">面积(㎡)</td>
									<td class="thStyle">位置</td>
									<td class="thStyle">监管状态</td>
									<td class="thStyle">资金状态</td>
								</tr>
								<tr >
									<td class="thStyle">${orderinfo.orderId}</td>
									<td class="thStyle">${orderinfo.sstr1}</td>
									<td class="thStyle">${orderinfo.createTime}</td>
									<td class="thStyle">${orderinfo.cashPayTotal}</td>
									<td class="thStyle">${orderinfo.mortgagePayTotal}</td>
									<td class="thStyle">${orderinfo.sstr3}</td>
									<td class="thStyle">${orderinfo.lstr8}</td>
									<td class="thStyle">
										<c:choose>
												<c:when test="${orderinfo.orderStatus eq '1010' && orderinfo.isRefunded eq '0000'}">
													订单刚创建
												</c:when>
												<c:when test="${orderinfo.orderStatus eq '1011' && orderinfo.isRefunded eq '0000'}">
													卖方填写完毕，等待填写买方信息
												</c:when>
												<c:when test="${orderinfo.orderStatus eq '1012' && orderinfo.isRefunded eq '0000'}">
													信息填写完毕，等待确认
												</c:when>
												<c:when test="${orderinfo.orderStatus eq '1020' && orderinfo.isRefunded eq '0000'}">
													订单已确认，等待支付
												</c:when>
												<c:when test="${orderinfo.orderStatus eq '0004' && orderinfo.isRefunded eq '0000'}">
													支付完成
												</c:when>
												<c:when test="${orderinfo.orderStatus eq '1032' && orderinfo.isRefunded eq '0000'}">
													银行确认，等待房产局确认过户状态
												</c:when> 
												<c:when test="${orderinfo.orderStatus eq '1033' && orderinfo.isRefunded eq '0000'}">
													房交所确认过户开始
												</c:when>
												<c:when test="${orderinfo.orderStatus eq '1034' && orderinfo.isRefunded eq '0000'}">
													房交所确认
												</c:when>
												<c:when test="${orderinfo.orderStatus eq '1040' && orderinfo.isRefunded eq '0000'}">
													过户成功，等待扣款
												</c:when>
												<c:when test="${orderinfo.orderStatus eq '1041' && orderinfo.isRefunded eq '0000'}">
													过户成功，已写入流水，等待打款
												</c:when>
												<c:when test="${orderinfo.orderStatus eq '1042' && orderinfo.isRefunded eq '0000'}">
													过户成功，已写入流水，等待审核
												</c:when>
												<c:when test="${orderinfo.orderStatus eq '1050' && orderinfo.isRefunded eq '0000'}">
													打款完成
												</c:when>
												<c:when test="${orderinfo.isRefunded eq '1113'}">
													已终止流程
												</c:when>
												<c:when test="${orderinfo.isRefunded eq '1110'}">
													行方已经确定需退款
												</c:when>
												<c:when test="${orderinfo.isRefunded eq '1111'}">
													退款中，已写退款流水，等待划款动作完成
												</c:when>
												<c:when test="${orderinfo.isRefunded eq '1112'}">
													退款完成
												</c:when>
												<c:when test="${orderinfo.isRefunded eq '1114'}">
													退款中，已写退款流水，等待审核
												</c:when>
											</c:choose>	
									</td>
									<td class="thStyle">
										<c:choose>
												<c:when test="${orderinfo.orderExStatus eq '0000'}">
													<b style="color: blue;">正常</b>
												</c:when>
												<c:when test="${orderinfo.orderExStatus eq '1111'}">
													<b style="color: red;">冻结</b>
												</c:when>
											</c:choose>
									</td>
								</tr>
							</table>
							<br/>
							<table class="mainTab" id="itemListTab">
								<tr id="tabHeader">
									<th class="thStyle" rowspan="20">卖方信息</th>
									<td class="thStyle">姓名</td>
									<td class="thStyle">手机号</td>
									<td class="thStyle">身份证</td>
									<td class="thStyle">银行卡</td>
									<td class="thStyle">分配金额</td>
								</tr>
								<c:choose>
									<c:when test="${ empty sellerInfo}">
										<tr><td colspan=11 align="center" height="25"><font color="red">没有查询到相关记录</font></td></tr>
									</c:when>
									<c:otherwise>
										<c:forEach items="${sellerInfo}" var="sellerList">
											<c:if test="${ sellerList.partType eq '0010'}">
											<tr >
												<td class="thStyle">${sellerList.realName}</td>
												<td class="thStyle">${sellerList.phone}</td>
												<td class="thStyle">${sellerList.idNo}</td>
												<td class="thStyle">${sellerList.cardNo}</td>
												<td class="thStyle">${sellerList.incomeScale}</td>
											</tr>
											</c:if>
										</c:forEach>
									</c:otherwise>
								</c:choose>			
							</table>
							<br/>
							<table class="mainTab" id="itemListTab">
								<tr id="tabHeader">
									<th class="thStyle" rowspan="20">买方信息</th>
									<td class="thStyle">姓名</td>
									<td class="thStyle">手机号</td>
									<td class="thStyle">身份证</td>
								</tr>
								<c:choose>
									<c:when test="${ empty sellerInfo}">
										<tr><td colspan=11 align="center" height="25"><font color="red">没有查询到相关记录</font></td></tr>
									</c:when>
									<c:otherwise>
										<c:forEach items="${sellerInfo}" var="buyerList">
											<c:if test="${ buyerList.partType eq '0020'}">
											<tr >
												<td class="thStyle">${buyerList.realName}</td>
												<td class="thStyle">${buyerList.phone}</td>
												<td class="thStyle">${buyerList.idNo}</td>
											</tr>
											</c:if>
										</c:forEach>
									</c:otherwise>
								</c:choose>	
							</table>
							<br/>
							<table class="mainTab" id="itemListTab">
								<tr id="tabHeader">
									<th class="thStyle" rowspan="20">支付信息</th>
									<td class="thStyle">创建时间</td>
									<td class="thStyle">支付金额</td>
									<td class="thStyle">支付时间</td>
									<td class="thStyle">支付状态</td>
								</tr>
								<c:choose>
									<c:when test="${ empty payInfo}">
										<tr><td colspan="4" align="center" height="25"><font color="red">没有查询到相关记录</font></td></tr>
									</c:when>
									<c:otherwise>
										<c:forEach items="${payInfo}" var="payInfo">
											
											<tr >
												<td class="thStyle">${payInfo.createTime}</td>
												<td class="thStyle">${payInfo.payMoney}</td>
												<td class="thStyle">${payInfo.payTime}</td>
												<c:choose>
												<c:when test="${ payInfo.payStatus eq '0001'}">
												<td class="thStyle">正在支付</td>
												</c:when>
												<c:when test="${ payInfo.payStatus eq '0002'}">
												<td class="thStyle">支付成功</td>
												</c:when>
												<c:when test="${ payInfo.payStatus eq '0003'}">
												<td class="thStyle">支付失败</td>
												</c:when>
												<c:when test="${ payInfo.payStatus eq '0004'}">
												<td class="thStyle">流水完成</td>
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
									<th class="thStyle" rowspan="20" >过户记录</th>
									<td class="thStyle">业务宗号</td>
									<td class="thStyle">时间</td>
									<td class="thStyle">处理结果</td>
									<td class="thStyle">处理结果说明</td>
								</tr>
								<c:choose>
									<c:when test="${ empty transferStatusLogInfo}">
										<tr><td colspan="4" align="center" height="25" ><font color="red">没有查询到相关记录</font></td></tr>
									</c:when>
									<c:otherwise>
										<c:forEach items="${transferStatusLogInfo }" var="tList">
											<tr >
												<td class="thStyle">${tList.workNo}</td>
												<td class="thStyle">${tList.createTime}</td>
												<td class="thStyle">${tList.retCode}</td>
												<td class="thStyle">${tList.retShow}</td>
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