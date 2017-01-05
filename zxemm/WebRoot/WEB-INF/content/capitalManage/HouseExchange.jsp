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
			<form id=PageForm name=PageForm method=post action="houseExchange/houseExchangeOpre.do">
				<div id="itemList">
					<div class="loc">
						<div class="groupmenu" id="groupmenu"></div>
						<span>资金监管</span> &raquo;
						<span>资金监管管理</span> &raquo; 
						<a href="javascript:void(0);" ><span class="last"></span></a> 
					</div>
					<!-- 全/反选 -->
					<div class="chooseCase">
						<!-- 搜索查询 -->
						<div style="float: left" class="search">
						</div>
					</div>	
					
					<!-- 数据列表 -->
					<div id="PrintContent" class="mainCon">
							<table class="mainTab" id="itemListTab">
								<tr id="tabHeader">
									<th class="thStyle">房交所操作</th>
								</tr>
								<tr>
									<td align="center" height="35">
										订单流水号：<input type="text" id="orderId" name="orderId"/>
									</td>
								</tr>
								<tr>
									<td align="center" height="35">操作项：
										<select id="status" name="status">
											<option value="1001">通知过户开始</option>
											<option value="1002">通知过户完成</option>
											<option value="1003">通知过户失败</option>
										</select>
									</td>
								</tr>
								<tr><td colspan=11 align="center" height="35"><input type="submit" value="提交操作" /></td></tr>
							</table>
						</div>
					<div class="headPageNavi">
					</div>
				</div>
			</form>
		</div>
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