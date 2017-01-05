<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/content/base/tag.inc" />
<%String path = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<HEAD>
<title>开户申请</title>
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
    var buildingId=$.trim($("#buildingValue").val());
    if(buildingId==""){
   // window.location.href = "accountOpen/AccountOpenApplly.do";
    alert("地幢号不能为空！！");
    return;
    }else{
	document.PageForm.action = url;
	document.PageForm.submit();
    }
}

function accountApply(buildingId){
	$("#buildingId").val(buildingId);//订单号
	$("#accountOpenApplly").submit();
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
						<span>新房资金监管</span> &raquo;
						<span>预售开户管理</span> &raquo; 
						<a href="javascript:gotoListHome()" ><span class="last">开户申请</span></a> 
					</div>
					<!-- 全/反选 -->
					<div class="chooseCase">
						<!-- 搜索查询 -->
						<div style="float: left" class="search">
							<table width="100%" border="0">
								<tr>
								<td width="5%" >地幢号：</td>
									<td >
	                                	<input id="buildingValue" name="buildingValueId" value="${buildingValue}" type="text" class="txt-150" />						
									</td>
									<td width="5%" >
										<a href="javascript:submitForm('accountOpen/AccountOpenInfo.do')">
										<img src="<%=request.getContextPath()%>/images/search-btn.jpg" style="vertical-align:middle; padding-left:10px;" />
										</a>
									</td>		
				              	</tr>
				              	
	                        </table>
						</div>
					</div>	
					
					<!-- 数据列表 -->
					<div id="PrintContent" class="mainCon">
							<table class="mainTab" id="itemListTab">
								<tr id="tabHeader">
									<th class="thStyle">企业号</th>
									<th class="thStyle">项目编号</th>
									<th class="thStyle">地幢号</th>
									<th class="thStyle">开户状态</th>
									<th class="thStyle">资金监管状态</th>
									<th class="thStyle">操作</th>
								</tr>
								<c:if test="${empty pageFinder.data }">
								<tr><td colspan=11 align="center" height="25"><font color="red">没有查询到相关记录</font></td></tr>
								</c:if>
								<c:forEach var="building" items="${pageFinder.data}">
									<tr >
										<td class="thStyle">${building.companyId}</td>
										<td class="thStyle">${building.blockNo}</td>
										<td class="thStyle">${building.buildingId}</td>
										<td class="thStyle">
											<c:choose>
												<c:when test="${building.status eq '1001'}">
													已开户
												</c:when>
												<c:when test="${building.status eq '1002'}">
													未开户
												</c:when>
											     <c:when test="${building.status eq '1003'}">
													等待审核
												</c:when>
											</c:choose>	
										</td>
											<td class="thStyle">
											<c:choose>
												<c:when test="${building.regulateStatus eq '0001'}">
												   未监管
												</c:when>
												<c:when test="${building.regulateStatus eq '0002'}">
												    已监管
												</c:when>
												<c:when test="${building.regulateStatus eq '0003'}">
												    撤销监管
												</c:when>
											</c:choose>	
										</td>
										<td class="thStyle">
										<c:choose>
												<c:when test="${building.regulateStatus eq '0001'}">
												    <a href="javascript:accountApply('${building.buildingId}')">
													<img src="<%=path%>/images/template/b_search.png" alt="IMG" />申请开户</a>
												</c:when>
												<c:when test="${building.regulateStatus eq '0002'}">
												   <a href="<%=path %>/accountOpen/AccountOpenBackout.do?buildingId=${building.buildingId}">
												   <img src="<%=path%>/images/template/b_search.png" alt="IMG" />撤销监管</a>
												</c:when>
												<c:when test="${building.regulateStatus eq '0003'}">
												    已撤销
												</c:when>
											</c:choose>	
										</td>
									 </tr>
								</c:forEach>	
							</table>
						</div>
					<div class="headPageNavi">

						<!-- 分页 -->
					<%-- 	<div style="float: right" class="pageNavi" id="pagerBtm">
							<c:import url="../base/page.jsp?formId=PageForm" charEncoding="utf-8"></c:import>
						</div> --%>
					</div>
				</div>
			</form>
		</div>
<form id="accountOpenApplly" action="accountOpen/AccountOpenAppllyInfo.do" method="post">
	<input type="hidden" id="buildingId" name="buildingId"/>
</form>

		</div>
	</body>
</HTML>