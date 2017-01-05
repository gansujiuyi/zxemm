<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/content/base/tag.inc" />
<%String path = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<HEAD>
<title>枚举信息列表</title>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<META http-equiv=Expires content=0>
<script type="text/javascript" src="<%=path%>/js/jquery-1.6.js"></script>
<script type="text/javascript" src="<%=path%>/js/base.js"></script>
<link href="<%=path%>/css/Style1.css" rel="stylesheet" type="text/css" />
<link href="<%=path%>/css/Style2.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="<%=path %>/js/timerControl/WdatePicker.js"></script>
<style>
.thStyle{width: 150px;text-align: center;}
</style>
<script language=JavaScript>
function submitForm(url){
	document.PageForm.action = url;
	document.PageForm.submit();
}

function searchCapitalByIdInfo(orderId){
	$("#orderId").val(orderId);//订单号
	$("#capitalByIdInfo").submit();
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
						<a href="javascript:gotoListHome()" ><span class="last">资金监管支付审核</span></a> 
					</div>
					<!-- 全/反选 -->
					<div class="chooseCase">
						<!-- 搜索查询 -->
						<div style="float: left" class="search">
							<table width="100%" border="0">
								<tr>
									<td width="5%" >房产证号：</td>
									<td width="5%" ><input id="" name="orderinfo.sstr2" value="${orderinfo.sstr2}" type="text" class="txt-150" /></td>		
									<td width="5%" >监管流水号：</td>
									<td width="5%" colspan="3"><input id="" name="orderinfo.orderId" value="${orderinfo.orderId}" type="text" class="txt-150" />
									 &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;状态：
									<select name="orderinfo.orderStatus">
										<option value="" <c:if test="${orderinfo.orderStatus eq ''}">selected="selected"</c:if>>-请选择-</option>
										<option value="1010" <c:if test="${orderinfo.orderStatus eq '1010'}">selected="selected"</c:if>>订单刚创建</option>
										<option value="1011" <c:if test="${orderinfo.orderStatus eq '1011'}">selected="selected"</c:if>>卖方填写完毕</option>
										<option value="1012" <c:if test="${orderinfo.orderStatus eq '1012'}">selected="selected"</c:if>>双方信息填写完毕</option>
										<option value="0004" <c:if test="${orderinfo.orderStatus eq '0004'}">selected="selected"</c:if>>支付完成</option>
										<option value="1020" <c:if test="${orderinfo.orderStatus eq '1020'}">selected="selected"</c:if>>等待支付</option>
										<option value="1033" <c:if test="${orderinfo.orderStatus eq '1033'}">selected="selected"</c:if>>房产所开始过户</option>
										<option value="1034" <c:if test="${orderinfo.orderStatus eq '1034'}">selected="selected"</c:if>>房产所过户完成</option>
										<option value="1035" <c:if test="${orderinfo.orderStatus eq '1035'}">selected="selected"</c:if>>房产所过户失败</option>
										<option value="1040" <c:if test="${orderinfo.orderStatus eq '1040'}">selected="selected"</c:if>>过户成功，等待扣款</option>
										<option value="1041" <c:if test="${orderinfo.orderStatus eq '1041'}">selected="selected"</c:if>>过户成功，等待扣款</option>
										<option value="1042" <c:if test="${orderinfo.orderStatus eq '1042'}">selected="selected"</c:if>>确认打款，等待审核</option>
										<option value="1050" <c:if test="${orderinfo.orderStatus eq '1050'}">selected="selected"</c:if>>打款完成</option>
									</select>
										<a href="javascript:submitForm('capitalManage/queryCheckCapitalInfoAll.do')">
										<img src="<%=request.getContextPath()%>/images/search-btn.jpg" style="vertical-align:middle; padding-left:10px;" />
										</a>
									</td>				
				              	</tr>
				              	<tr>
					                <td width="5%" >房源编号：</td>
									<td >
	                                	<input id="" name="orderinfo.sstr1" value="${orderinfo.sstr1}" type="text" class="txt-150" />						
									</td>
									  <td width="5%" >创建时间：</td>
									<td >
	                                	<input class="Wdate"  onClick="WdatePicker({Date:'%y-%M-%d',dateFmt:'yyyy-MM-dd'})"
										readOnly style="width: 155px;"  name="startDate" value="${startDate}" />~
	                                	<input class="Wdate"  onClick="WdatePicker({Date:'%y-%M-%d',dateFmt:'yyyy-MM-dd'})"
										readOnly style="width: 155px;"  name="endDate" value="${endDate}" />								
									</td>
											
				              	</tr>
				              	
	                        </table>
						</div>
					</div>	
					
					<!-- 数据列表 -->
					<div id="PrintContent" class="mainCon">
							<table class="mainTab" id="itemListTab">
								<tr id="tabHeader">
									<th class="thStyle">监管流水号</th>
									<th class="thStyle">房源编号</th>
									<th class="thStyle">创建时间</th>
									<th class="thStyle">总金额(元)</th>
									<th class="thStyle">面积(㎡)</th>
									<th class="thStyle">位置</th>
									<th class="thStyle">监管状态</th>
									<th class="thStyle">资金状态</th>
									<th class="thStyle">操作</th>
								</tr>
								<c:if test="${empty pageFinder.data }">
								<tr><td colspan=11 align="center" height="25"><font color="red">没有查询到相关记录</font></td></tr>
								</c:if>
								<c:forEach var="capital" items="${pageFinder.data}">
									<tr >
										<td class="thStyle">${capital.orderId}</td>
										<td class="thStyle">${capital.sstr1}</td>
										<td class="thStyle">${capital.createTime}</td>
										<td class="thStyle">${capital.priceTotal}</td>
										<td class="thStyle">${capital.sstr3}</td>
										<td class="thStyle" title="${capital.lstr8}">${capital.lstr8}</td>
										<td class="thStyle">
											<c:choose>
												<c:when test="${(capital.orderStatus eq '1042') and (capital.isRefunded eq '0000')}">
													确认打款，等待审核
												</c:when>
												<c:when test="${capital.isRefunded eq '1114'}">
													退款待审核
												</c:when>
											</c:choose>	
										</td>
										<td class="thStyle">
											<c:choose>
												<c:when test="${capital.orderExStatus eq '0000'}">
													<b style="color: blue;">正常</b>
												</c:when>
												<c:when test="${capital.orderExStatus eq '1111'}">
													<b style="color: red;">冻结</b>
												</c:when>
											</c:choose>	
										</td>
										<td class="thStyle">
											<a href="javascript:searchCapitalByIdInfo('${capital.orderId}')">
											<img src="<%=path%>/images/template/b_search.png" alt="IMG" />查看详情</a>
										</td>
									 </tr>
								</c:forEach>	
							</table>
						</div>
					<div class="headPageNavi">

						<!-- 分页 -->
						<div style="float: right" class="pageNavi" id="pagerBtm">
							<c:import url="../base/page.jsp?formId=PageForm" charEncoding="utf-8"></c:import>
						</div>
					</div>
				</div>
			</form>
		</div>
<form id="capitalByIdInfo" action="capitalManage/queryCheckCapitalByIdInfo.do" method="post">
	<input type="hidden" id="orderId" name="orderinfo.orderId"/>
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