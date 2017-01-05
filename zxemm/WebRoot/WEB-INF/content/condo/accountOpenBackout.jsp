<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
		<link href="<%=path%>/js/select2-3.5.2/select2.css" rel="stylesheet"/>
<script language=JavaScript>
function backoutApplyinfo(){
     var status=$("#status").val();
     var buildingId=$.trim($("#buildingId").val());
     
     if (!confirm("确认要撤销吗？")) {
       return;
     }else{
       if(status!="0000"){
       alert("房产局暂时没有通知消息，请点击同步查询信息获取最新通知消息");
    }else{
		$.ajax({
				type : "POST",
				url : "<%=path%>/accountOpen/updateAccountOpenBackout.do",
				data : {
					"buildingId" : buildingId
				},
				success : function(data) {
					 if (data.status == '0000') {
						alert("提交成功，状态更新！");
						window.location="<%=path%>/accountOpen/AccountOpenApplly.do";
					} else if(data.status=='0001'){
					alert(data.message);
					}else{
						alert("撤销资金监管错误！");
					}
				}
			});
    }
     }
    
}
function cancelApplyinfo(){
if (!confirm("确认要取销吗？")) {
return;
}else{
window.location="<%=path%>/accountOpen/AccountOpenApplly.do";
}
}

function checkQuery(){
	 var buildingId=$.trim($("#buildingId").val());
	 $.ajax({
				type : "POST",
				url : "<%=path%>/accountOpen/checkQuery.do",
				data : {
					"buildingId" : buildingId
				},
				success : function(data) {
					 if (data.status == '0000') {
						 $("#status").val(data.status);
						$("#amt").val(data.amt);
						$("#contactNo").val(data.contactNo);
						$("#modifyAccount").val(data.modifyAccount);
						$("#logDate").val(data.logDate);
						$("#houseNo").val(data.buildingId);
						$("#msg").html(data.message);
						$("#oldMsg").html("");
					} else if(data.status=='0001'){
						alert(data.message);
					}else if(data.status=='0002'){
						alert(data.message);
					}else if(data.status=='0003'){
						alert(data.message);
					}else{
						alert("撤销资金监管错误！");
					}
				}
			});
}
</script>
	</head>
	<body>
		<div class="w900 co">
			<div class="header" style="font-size: 17px;">
				撤销资金监管
			</div>
			<div class="center co">
			<form action="accountOpen/updateAccountOpenBackout.do" method="post" id="backoutApplyinfo">
				<div class="account_aply fleft">
				<p  class="aply_title">楼幢信息</p>
					<div class="item">
						<span class="item-label"><label>
								企业号：
							</label> </span>
						<input class="input203 fleft" readonly="readonly" name="buildingInfo.companyId" type="text" id="companyId" value="${buildingInfo.companyId }" />
						<input class="input203 fleft" name="buildingInfo.id" type="hidden" id="buildingInfoid" value="${buildingInfo.id }" />
						<input class="input203 fleft" name="status" type="hidden" id="status" />
					</div>
					<div class="item">
						<span class="item-label"><label>
								小区号：
							</label> </span>
						<input class="input203 fleft" readonly="readonly" name="buildingInfo.blockNo" type="text" id="blockNo" value="${buildingInfo.blockNo }" />

					</div>
					<div class="item">
						<span class="item-label"><label>
								地幢号：
							</label> </span>
						<input class="input203 fleft" readonly="readonly" name="buildingInfo.buildingId" type="text" id="buildingId" value="${buildingInfo.buildingId }"/>
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
								监管账号：
							</label> </span>
						<input class="input203 fleft" readonly="readonly" name="buildingInfo.regulateAccount" type="text" id="regulateAccount" value="${buildingInfo.regulateAccount }" />
					</div>
					<div class="item">
						<span class="item-label"><label>
								监管账号名称：
							</label> </span>
						<input class="input203 fleft" readonly="readonly" name="buildingInfo.regulateAccName" type="text" id="regulateAccName" value="${buildingInfo.regulateAccName }" />
					</div>
					<div class="item">
						<span class="item-label"><label>
								项目地址：
							</label> </span>
						<input class="input203 fleft" readonly="readonly" name="buildingInfo.projectAdd" type="text" id="projectAdd" value="${buildingInfo.projectAdd }" />
					</div>
				</div>
				<div class="account_aply fleft">
					<p class="aply_title">房管局指令信息</p>
					<div class="item">
						<div class="item" style="margin-left: 33px;">
						<span class="item-label"><label>
								指令种类：&nbsp;&nbsp;撤销指令
							</label> </span>
					   </div>
						<%-- <span class="item-label"><label>
								划款金额：
							</label> </span>
						<input class="input203 fleft" readonly="readonly" name="noticeInstructionsLog.amt" type="text" id="amt" value="${noticeInstructionsLog.amt }" />元
						<input class="input203 fleft" name="buildingInfo.id" type="hidden"  value="${buildingInfo.id }" /> --%>
						
					</div>
					<%-- <div class="item">
						<span class="item-label"><label>
								购买合同号：
							</label> </span>
						<input class="input203 fleft" readonly="readonly" name="noticeInstructionsLog.contactNo" type="text" id="contactNo" value="${noticeInstructionsLog.contactNo }" />

					</div>
					<div class="item">
						<span class="item-label"><label>
								变更的账户：
							</label> </span>
						<input class="input203 fleft mr10" readonly="readonly" name="noticeInstructionsLog.modifyAccount" type="text" id="modifyAccount" value="${noticeInstructionsLog.modifyAccount }" />
						
					</div> --%>
					<div class="item">
						<span class="item-label"><label>
								创建时间：
							</label> </span>
						<input class="input203 fleft mr10" readonly="readonly" name="noticeInstructionsLog.logDate" type="text" id="logDate" value="${noticeInstructionsLog.logDate }" />
						
					</div>
					
					<div class="item">
						<span class="item-label"><label>
								地幢号：
							</label> </span>
						<input class="input203 fleft"  name="noticeInstructionsLog.houseNo" type="text" id="houseNo" value="${noticeInstructionsLog.houseNo }" />
					</div>
					<div class="item">
						<span  style="margin-left: 113px;color: #FF0000;">
								<c:if test="${message !=null}"><span id="oldMsg">${message}</span></c:if>
								<c:if test="${message ==null}"><span id="msg"></span></c:if>
							 </span>
					</div>
					
					
					
				</div>
				<div class="co">
					<div class="btn-div">
						<input class="item-btn"  type="button" value="撤销" onclick="javascript:backoutApplyinfo();" />
						<input class="item-btn"  type="button" value="取消" onclick="javascript:cancelApplyinfo();"  />
						<input class="item-btn"  type="button" value="同步查询" onclick="javascript:checkQuery();"  />
					</div>
				</div>
				</form>
			</div>
		</div>
	</body>
</html>
