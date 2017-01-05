<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/content/base/tag.inc" />
<%String path = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<HEAD>
<title>资金监管-同步利息信息</title>
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
<script type="text/javascript"> 
	function snyInterestInfo(){
		$("#PageForm").submit();
	}
</script>
	</head>
	<body>
	<div id=mainzone>
		<div id="body">
			<form id=PageForm name=PageForm method=post action="capitalManage/snyInterest.do">
				<div id="itemList">
					<div class="loc">
						<div class="groupmenu" id="groupmenu"></div>
						<span>同步利息</span> &raquo;
					</div>
					
					<!-- 数据列表 -->
					<div id="PrintContent" class="mainCon">
							<table class="mainTab" id="itemListTab">
								<tr id="tabHeader">
									<th class="thStyle" rowspan="2">查询条件：</th>
									<td>日期：
										<input class="Wdate"  onClick="WdatePicker({Date:'%y-%M-%d',dateFmt:'yyyy-MM-dd'})"
										readOnly style="width: 155px;"  name="snyBeginDate" value="${snyBeginDate}" />~
	                                	<input class="Wdate"  onClick="WdatePicker({Date:'%y-%M-%d',dateFmt:'yyyy-MM-dd'})"
										readOnly style="width: 155px;"  name="snyEndDate" value="${snyEndDate}" />	
										
									</td>
									<td>
										金额：（保留两位小数）
										<input id="" name="snyAmount" value="" type="text" class="txt-150" />
									</td>
									<td>
										<a href="javascript:snyInterestInfo();" class="a-bluebtn">
										查询利息
										</a>
									</td>
									<td> 
									</td>
									<td> 
									</td>
								</tr>
							</table>
							<br/>
							<table class="mainTab" id="itemListTab">
								<tr id="tabHeader">
									<th class="thStyle" rowspan="20">查询结果：</th>
									<td>利息：<input value="${snyAmount }" type="text" class="txt-150" readonly/></td>
								</tr>
							</table>
							<br/>
						</div>
				</div>
			</form>
		</div>
<form id="capitalStateF" name="capitalStateF" action="capitalManage/checkDKrecord.do" method="post">
	<input id="orderId" type="hidden" name="orderinfo.orderId"/>
	<input id="opeType" type="hidden" name="opeType"/>
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