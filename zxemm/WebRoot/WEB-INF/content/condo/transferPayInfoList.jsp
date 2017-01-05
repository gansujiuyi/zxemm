<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.jiuyi.jyplat.util.ConfigManager"%>
<jsp:directive.include file="/WEB-INF/content/base/tag.inc" />
<%
	String path = request.getContextPath();
	String base_path = ConfigManager.getString("base_path", "请在emm中设置");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>审批划款流水列表页面</title>
<link rel="stylesheet" type="text/css"
	href="<%=path%>/css/style_zjjg.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/public.css" />
<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery-1.6.js"></script>
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
								<c:forEach var="transferPayInfo" items="${pageFinder.data}">
									<tr >
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
</body>
</html>
