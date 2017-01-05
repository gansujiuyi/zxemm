<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/content/base/tag.inc" />
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>新房开户申请</title>
		<link rel="stylesheet" type="text/css"
			href="<%=path%>/css/style_zjjg.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/public.css" />
		<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
		<script type="text/javascript" src="<%=path%>/js/base.js"></script>
		<link href="<%=path%>/js/select2-3.5.2/select2.css" rel="stylesheet"/>
<script language=JavaScript>
function accountApplyinfo(buildingId){
    var regulateAccount=$.trim($("#regulateAccount").val());
    var regulateAccName=$.trim($("#regulateAccName").val());
    if(regulateAccount=="" || regulateAccount==null){
    	$("#regulateAccount").focus();
      $("#errorMessage").html("监管账号不能为空！");
      return;
    }
    if(!/^[0-9]+.?[0-9]*$/.test(regulateAccount)){
		$("#regulateAccount").focus();
		$("#errorMessage").html("监管账号只能为数字！");
		return;
	}
     if(regulateAccName=="" || regulateAccName==null){
    	 $("#regulateAccName").focus();
      $("#errorMessage").html("监管账号名称不能为空！");
      return;
    }
	$("#accountOpenAppllysave").submit();
}
</script>
	</head>
	<body>
		<div class="w900">
			<div class="header">
				开户申请
			</div>
			<div class="center" style="margin-top: -32px;">
			<form action="accountOpen/accountOpenAppllysave.do" method="post" id="accountOpenAppllysave">
				<div class="content">
				<p  class="aply_title"  style="margin-left: 60px;padding-left: 112px;width: 143px;">楼幢信息</p>
					<%-- <div class="item">
						<span class="item-label"><label>
								企业号：
							</label> </span>
						<input class="input203 fleft" readonly="readonly" name="buildingInfo.companyId" type="text" id="companyId" value="${buildingInfo.companyId }" />
						<input class="input203 fleft" name="buildingInfo.id" type="hidden"  value="${buildingInfo.id }" />
						
					</div>
					<div class="item">
						<span class="item-label"><label>
								小区号：
							</label> </span>
						<input class="input203 fleft" readonly="readonly" name="buildingInfo.blockNo" type="text" id="blockNo" value="${buildingInfo.blockNo }" />

					</div> --%>
					<div class="item">
						<span class="item-label"><label>
								地幢号：
							</label> </span>
						<input class="input203 fleft" readonly="readonly" name="buildingInfo.buildingId" type="text" id="buildingId" value="${buildingInfo.buildingId }"/>
						<input class="input203 fleft" name="buildingInfo.id" type="hidden"  value="${buildingInfo.id }" />
						<input type="hidden" name="buildingTemp.id" value="${buildingTemp.id}"/>
					</div>
					<div class="item">
						<span class="item-label"><label>
								建筑面积：
							</label> </span>
						<input class="input203 fleft" readonly="readonly" name="buildingInfo.coveredArea" type="text" id="coveredArea" value="${buildingInfo.coveredArea }"/>
						平方米
					</div>
					<div class="item">
						<span class="item-label"><label>
								企业名称：
							</label> </span>
						<input class="input203 fleft mr10" readonly="readonly" name="buildingInfo.companyName" type="text" id="companyName" value="${buildingInfo.companyName }" />
						
					</div>
					<div class="item">
						<span class="item-label"><label>
								项目名称：
							</label> </span>
						<input class="input203 fleft mr10" readonly="readonly" name="buildingInfo.projetName" type="text" id="projetName" value="${buildingInfo.projetName }" />
					</div>
					<div class="item">
						<span class="item-label"><label>
								项目地址：
							</label> </span>
						<input class="input203 fleft" readonly="readonly" name="buildingInfo.projectAdd" type="text" id="projectAdd" value="${buildingInfo.projectAdd }" />
					</div>
					<c:if test="${status ne '1111' }">
					<div class="item">
						<span class="item-label"><label>
								项目监管账号：
							</label> </span>
						<input class="input203 fleft"  name="projectReguNo" type="text" id="projectReguNo" value="${projectReguNo }" />
					</div>
					
					<div class="item">
						<span class="item-label"><label>
								项目监管账户名称：
							</label> </span>
						<input class="input203 fleft mr10"  name="projetReguName" type="text" id="projetReguName" value="${projetReguName }" />
					</div>
					</c:if>
					
					<div class="item">
						<span class="item-label"><label>
								楼幢监管账号：
							</label> </span>
						<input class="input203 fleft"  name="buildingInfo.regulateAccount" type="text" id="regulateAccount" value="${buildingInfo.regulateAccount }" />
					</div>
					<div class="item">
						<span class="item-label"><label>
								楼幢监管账户名称：
							</label> </span>
						<input class="input203 fleft"  name="buildingInfo.regulateAccName" type="text" id="regulateAccName" value="${buildingInfo.regulateAccName }" />
					</div>
				</div>
				</form>
				<span class="tips-info pl100" id="errorMessage"></span>
				<div class="btn-div">
					<input class="item-btn" name="" type="button" value="保存" onclick="javascript:accountApplyinfo();"  />
				</div>
			</div>
		</div>
	</body>
</html>
