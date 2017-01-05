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
function checkYes(orderId,orderstatus,isRefused){
	$("#passBtn").attr("href", "javascript:void(0);");//避免连续点击
	$("#orderId").val(orderId);
	if(orderstatus=='1042'){
		$("#opeType").val("1");
	}
	if(isRefused=='1114'){
		$("#opeType").val("2");
	}
	if(confirm("确定要审核该笔业务吗？") == false){
			return;
		}
	$("#capitalStateF").submit();
}

function saveDKrecordTK(orderId,url,opeType){
	$("#orderId").val(orderId);
	$("#opeType").val(opeType);
	
	document.capitalStateF.action = url;
	if(confirm("确定要经行退款操作吗？") == false){
			return;
		}
	$("#capitalStateF").submit();
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
						<a href="capitalManage/queryCheckCapitalInfoAll.do" >资金监管支付审核</a> &raquo; 
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
									<td class="thStyle">现金金额(元)</td>
									<td class="thStyle">按揭金额(元)</td>
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
												<c:when test="${orderinfo.orderStatus eq '1010'}">
													订单刚创建
												</c:when>
												<c:when test="${orderinfo.orderStatus eq '1011'}">
													填写完毕，等待确认
												</c:when>
												<c:when test="${orderinfo.orderStatus eq '1020'}">
													订单已确认，等待支付
												</c:when>
												<c:when test="${orderinfo.orderStatus eq '1030'}">
													开始支付，未支付完成
												</c:when>
												<c:when test="${orderinfo.orderStatus eq '1031'}">
													支付完成，等待银行确认
												</c:when>
												<c:when test="${orderinfo.orderStatus eq '1032'}">
													银行确认，等待房产局确认过户状态
												</c:when>
												<c:when test="${orderinfo.orderStatus eq '1040'}">
													过户成功，等待扣款
												</c:when>
												<c:when test="${orderinfo.orderStatus eq '1050'}">
													打款完成
												</c:when>
												<c:when test="${orderinfo.orderStatus eq '1110'}">
													需退款
												</c:when>
												<c:when test="${orderinfo.orderStatus eq '1111'}">
													退款中
												</c:when>
												<c:when test="${orderinfo.orderStatus eq '1112'}">
													退款完成
												</c:when>
												<c:when test="${orderinfo.orderStatus eq '1042'}">
													打款待审核
												</c:when>
												<c:when test="${orderinfo.isRefunded eq '1114'}">
													退款待审核
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
									<td class="thStyle">支付时间</td>
									<td class="thStyle">付款金额</td>
									<td class="thStyle">交易状态</td>
								</tr>
								<c:choose>
									<c:when test="${ empty payInfo}">
										<tr><td colspan="4" align="center" height="25"><font color="red">没有查询到相关记录</font></td></tr>
									</c:when>
									<c:otherwise>
										<c:forEach items="${payInfo}" var="payInfo">
											
											<tr >
												<td class="thStyle">${payInfo.createTime}</td>
												<td class="thStyle">${payInfo.payTime}</td>
												<td class="thStyle">${payInfo.payMoney}</td>
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
								<c:choose>
									<c:when test="${(orderinfo.orderStatus eq '1042') and (orderinfo.isRefunded eq '0000')}">
										<a id="passBtn" href="javascript:checkYes('${orderinfo.orderId}','${orderinfo.orderStatus}','${orderinfo.isRefunded}')" class="a-bluebtn">
											<img src="<%=path%>/images/template/b_enable.png" alt="IMG" />划款审核通过
										</a><!-- 如果是冻结状态就显示解冻 -->
									</c:when>
									<c:when test="${orderinfo.isRefunded eq '1114'}">
										<a id="passBtn" href="javascript:saveDKrecordTK('${orderinfo.orderId}','<%=path%>/capitalManage/saveDKrecord.do','2')" class="a-bluebtn">
											<img src="<%=path%>/images/template/b_enable.png" alt="IMG" />退款审核通过
										</a><!-- 如果是冻结状态就显示解冻 -->
									</c:when>
								</c:choose>
							
						<div class="clr"></div>
					</div>
			</form>
		</div>
<form id="capitalStateF" name="capitalStateF" action="capitalManage/checkDKrecord.do" method="post">
	<input id="orderId" type="hidden" name="orderinfo.orderId"/>
	<input id="opeType" type="hidden" name="opeType"/>
	<input id="jspFlag" type="hidden" name="jspFlag" value="2"/>
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