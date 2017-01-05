<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/content/base/tag.inc" />
<%String path = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>异常数据报表查询</title>
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
			<form id="PageForm" name="PageForm" method="post">
				<div id="itemList">
					<div class="loc">
						<div class="groupmenu" id="groupmenu"></div>
						<span>客服报表查询</span> &raquo;
						<a href="javascript:">异常数据报表查询</a> &raquo; 
						<span class="last">异常数据统计列表</span>
					</div>
						<!-- 全/反选 -->
						<div class="chooseCase">
							<!-- 搜索查询 -->
							<div style="float: left" class="search">
							<table width="100%" border="0">
								<tr>
									<td width="5%" >地幢号：<input   name="buildId" value="${buildId}" type="text" class="txt" /></td>
									<td width="5%" >流水号：<input   name="transactionSeqNum" value="${transactionSeqNum}" type="text" class="txt" /></td>
									<td width="10%" >
										<a href="javascript:submitForm('<%=path%>/transport/qurBofficePayTransport.do','search')"><img src="<%=request.getContextPath() %>/images/search-btn.jpg" style="vertical-align:middle; padding-left:10px;"/></a>
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
										资金监管账户
									</th>
									<th>
										流水号
									</th>
									<th>
										金额
									</th>
									<th>
										合同号
									</th>
									<th>
									      创建时间
									</th>
									<th>
									       错误信息
									</th>
								</tr>
								<c:if test="${empty pageFinder.data }">
									<tr><td colspan=6 align="center" height="25"><font color="red">没有查询到相关记录</font></td></tr>
								</c:if>
								<c:forEach var="errorInfo" items="${pageFinder.data}"
									varStatus="status">
									<tr>
									    <td>
											${errorInfo.buildId }
										</td>
										<td>
											${errorInfo.sysAccount }
										</td>
										<td>
											${errorInfo.transactionSeqNum }
										</td>
										<td>
											${errorInfo.transactionAmount }
										</td>
										<td>
										    ${errorInfo.contractNo }
										</td>
										<td>
										    ${errorInfo.createtime}
										</td>
										<td>
										    <span style="color:red">${errorInfo.msg }</span>
										</td>
									</tr>
								</c:forEach>
							</table>
						</div>
					 	<div class="headPageNavi">
					 	<div style="float: right" class="pageNavi" id="pagerBtm">
								<c:import url="../base/page.jsp?formId=PageForm"
									charEncoding="utf-8"></c:import>
							</div>
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
</html>