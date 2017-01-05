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
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/style_zjjg.css" />
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/public.css" />
		<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
		<link href="<%=path%>/js/select2-3.5.2/select2.css" rel="stylesheet"/>
<script language=JavaScript>
function updateContactInfo(){
    var backCardNo=$.trim($("#backCardNo").val());
    var backCardName=$.trim($("#backCardName").val());
 /*    if(backCardNo==""){
      $("#errorMessage").html("退款银行卡号不能为空！");
      return;
    }
     if(backCardName==""){
      $("#errorMessage").html("退款人名称不能为空！");
      return;
    } */
    if(backCardNo!=""){
        if(!/^[0-9]+.?[0-9]*$/.test(backCardNo)){
    		$("#backCardNo").focus();
    		$("#errorMessage").html("退款银行卡号只能是数字！");
    		return;
    	}
      }
	$("#updateContactInfo").submit();
}
</script>
	</head>
	<body>
		<div class="w900">
			<div class="header">
				补录购房合同
			</div>
			<div class="center" style="margin-top: -32px;">
			<form action="buyerNewHouse/updateContactInfo.do" method="post" id="updateContactInfo">
				<div class="content">
				<p  class="aply_title"  style="margin-left: 60px;padding-left: 112px;width: 143px;">合同信息</p>
					<div class="item">
						<span class="item-label"><label>
								合同编号：
							</label> </span>
						<input class="input203 fleft" readonly="readonly" name="contactInfo.contactNo" type="text" id="contactNo" value="${contactInfo.contactNo }" />
						<input class="input203 fleft" name="contactInfo.id" type="hidden"  value="${contactInfo.id }" />
						<input class="input203 fleft" name="contactInfo.superviseAmt" type="hidden"  value="${contactInfo.superviseAmt }" />
						<input class="input203 fleft" name="contactInfo.status" type="hidden"  value="${contactInfo.status }" />
						<input class="input203 fleft" name="contactInfo.createDate" type="hidden"  value="${contactInfo.createDate }" />
					    <input class="input203 fleft" name="contactInfo.bankCode" type="hidden"  value="${contactInfo.bankCode}" />
					</div>
					<div class="item">
						<span class="item-label"><label>
								合同金额：
							</label> </span>
						<input class="input203 fleft" readonly="readonly" name="contactInfo.contactAmt" type="text" id="contactAmt" value="${contactInfo.contactAmt }" />

					</div>
					<div class="item">
						<span class="item-label"><label>
								支付状态：
							</label> </span>
							<c:choose>
								<c:when test="${contactInfo.payStatus eq '0000'}">
								   <input class="input203 fleft" type="text" value="未支付"/>
									<input class="input203 fleft"  readonly="readonly" name="contactInfo.payStatus" type="hidden" id="payStatus" value="${contactInfo.payStatus}"/>
								</c:when>
								<c:when test="${contactInfo.payStatus eq '0010'}">
								<input class="input203 fleft" type="text" value="已支付"/>
							         <input class="input203 fleft"  readonly="readonly" name="contactInfo.payStatus" type="hidden" id="payStatus" value="${contactInfo.payStatus}"/>
								</c:when>
							</c:choose>	
					</div>
					<div class="item">
						<span class="item-label"><label>
								建筑面积：
							</label> </span>
						<input class="input203 fleft" readonly="readonly" name="contactInfo.houseArea" type="text" id="houseArea" value="${contactInfo.houseArea }"/>
						平方米
					</div>
					<div class="item">
						<span class="item-label"><label>
								地幢号：
							</label> </span>
						<input class="input203 fleft mr10" readonly="readonly" name="contactInfo.houseNo" type="text" id="houseNo" value="${contactInfo.houseNo }" />
						
					</div>
					<%-- <div class="item">
						<span class="item-label"><label>
								房屋坐落：
							</label> </span>
						<input class="input203 fleft mr10" readonly="readonly" name="contactInfo.houseAddr" type="text" id="houseAddr" value="${contactInfo.houseAddr }" />
					</div> --%>
					<div class="item">
						<span class="item-label"><label>
								室号：
							</label> </span>
						<input class="input203 fleft" readonly="readonly" name="contactInfo.roomNo" type="text" id="roomNo" value="${contactInfo.roomNo }" />
					</div>
					<div class="item">
						<span class="item-label"><label>
								项目名称：
							</label> </span>
						<input class="input203 fleft mr10" readonly="readonly" name="contactInfo.projectName" type="text" id="projectName" value="${contactInfo.projectName }" />
						
					</div>
					
					<div class="item">
						<span class="item-label"><label>
								项目编号：
							</label> </span>
						<input class="input203 fleft" readonly="readonly" name="contactInfo.projectNo" type="text" id="projectNo" value="${contactInfo.projectNo }" />
					</div>
					<div class="item">
						<span class="item-label"><label>
								企业名称：
							</label> </span>
						<input class="input203 fleft" readonly="readonly" name="contactInfo.companyName" type="text" id="companyName" value="${contactInfo.companyName }" />
					</div>
					<%-- <div class="item">
						<span class="item-label"><label>
								企业编号：
							</label> </span>
						<input class="input203 fleft" readonly="readonly" name="contactInfo.companyNo" type="text" id="companyNo" value="${contactInfo.companyNo }" />
					</div> --%>
					<div class="item">
						<span class="item-label"><label>
								缴款账号：
							</label> </span>
						<input class="input203 fleft" readonly="readonly" name="fundNo" type="text" id="fundNo" value="${fundNo}" />
					</div>
					
					<div class="item">
						<span class="item-label"><label>
								退款银行卡号：
							</label> </span>
						<input class="input203 fleft"  name="contactInfo.backCardNo" type="text" id="backCardNo" value="${contactInfo.backCardNo }" />
					</div>
					<div class="item">
						<span class="item-label"><label>
								退款人姓名：
							</label> </span>
						<input class="input203 fleft" name="contactInfo.backCardName" type="text" id="backCardName" value="${contactInfo.backCardName }" />
					</div>
				</div>
				</form>
				<span class="tips-info pl100" id="errorMessage"></span>
				<div class="btn-div">
					<input class="item-btn" name="" type="button" value="保存" onclick="javascript:updateContactInfo();"  />
				</div>
			</div>
		</div>
	</body>
</html>
