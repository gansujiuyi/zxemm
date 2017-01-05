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
<link rel="stylesheet" type="text/css" href="<%=path%>/css/style_zjjg.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/public.css" />
<script type="text/javascript" src="<%=path%>/js/jquery-1.6.js"></script>
<script type="text/javascript" src="<%=path%>/js/base.js"></script>
<link href="<%=path%>/css/Style1.css" rel="stylesheet" type="text/css" />
<link href="<%=path%>/css/Style2.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="<%=path %>/js/timerControl/WdatePicker.js"></script>
<style>
.refertd{width: 271px; background: #e8e9ed none repeat scroll 0 0; font-weight:bold; text-align:center;font-size:15px;height:43px;}
.refertr{text-align: center;font-size:17px; height:28px;width: 271px;}
</style>
<script language=JavaScript>
function submitForm(url){
	if($("#strDate").val() == "" || $("#strDate").val() ==null){
		alert("请输入正确的日期格式");
		return;
	}
	if($("#institutionNo").val() == ""){
	   alert("请选择支行机构！！");
	   return;
	}
	document.PageForm.action = url;
	document.PageForm.submit();
}

function qurInstitution(){
	var instName=$.trim($("#institutionName").val());
	var jsonAry="";
	var owerHtml ="";
	$.ajax( {
		url : "referer/referQueryInst.do",
		type : "post",
		dataType : "json",
		data : {"instName":instName },
		success : function(data) {
			jsonAry = eval('('+ data.jsonStr+')');
		for(var i = 0; i < jsonAry.length; i++){
			var part = jsonAry[i];
			owerHtml +="<li value="+jsonAry[i].institutionNo+">"+jsonAry[i].institutionName+"</li>"
		}
		$("#institutionul").html(owerHtml);
		if(instName!=''){
			$("#institutionName").focus();
		}
		}
	});
}
$(document).ready(function(){
	//查询机构信息
	qurInstitution();
	//鼠标抬起事件
	$("#institutionName").bind("keyup",function(){
			$(institutionName).siblings(".text_list").show();
		qurInstitution();
		//机构
	});
	
	//机构
	$("#institutionName").focus(function(){
		$(this).siblings(".text_list").show();
		$("#institutionul li").click(function(){
			$("#institutionName").val($(this).text());
			$(this).parent(".text_list").hide();
			var institutionValue=$(this).attr("value");
			 $("#institutionNo").val(institutionValue);
		});
	
	});
	
 });


</script>
	</head>
	<body>
	<div id=mainzone>
		<div id="body">
			<form id="PageForm" name="PageForm"  action="" method="post" >
				<div id="itemList">
					<div class="loc">
						<div class="groupmenu" id="groupmenu"></div>
						<span>资金监管</span> &raquo;
						<span>资金监管管理</span> &raquo; 
						<a href="javascript:gotoListHome()" ><span class="last">资金监管报表查询</span></a> 
					</div>
					<!-- 全/反选 -->
					<div class="chooseCase">
						<!-- 搜索查询 -->
						<div style="float: left" class="search">
							<table width="100%" border="0">
								<tr>
					                <td >创建时间：</td>
									<td >
	                                	<input id="strDate" class="Wdate"  onClick="WdatePicker({Date:'%y-%M-%d',dateFmt:'yyyy-MM-dd'})"
										readOnly style="width: 155px;"  name="strDate" readonly value="${strDate}"/>~
	                                	<input id="endDate" class="Wdate" value="${endDate }" onClick="WdatePicker({Date:'%y-%M-%d',dateFmt:'yyyy-MM-dd'})" readOnly style="width: 155px;"  name="endDate"  readonly/>								
									</td>
									<td>机构</td>
									<td style="overflow:visible;">
										 <input type="text" class="input203" id="institutionName" name="instName" style="width:163px; height:16px;" value="${instName }"/>
	 									 <input  id="institutionNo" type="hidden" name="institutionNo" value="${institutionNo }"/>
								         <ul class="text_list" id="institutionul"  style="top:79px;margin-left:413px; width: 166px; height:24px;overflow: hidden;">
								         </ul>
									</td>
									<td>推荐人姓名</td>
									<td><input type="text" class="input203" id="refererName" name="refererName" value="${refererName }" style="width:120px;height:16px;"></td>
									<td width="5%">
										<a href="javascript:submitForm('referer/referOrderInfoDetail.do')">
										<img src="<%=request.getContextPath()%>/images/search-btn.jpg" style="vertical-align:middle; padding-left:10px;" />
										</a>
									</td>	
								</tr>
							</table>
						</div>
					</div>	
					<!-- 数据列表 -->
				</div>
		 <div id="PrintContent" >
			<table>
				<tr>
					<td class="refertd">总记录数</td>
					<td class="refertd">已支付记录</td>
					<td class="refertd">未支付记录</td>
					<td class="refertd">支付总额</td>
				</tr>
				<tr class="refertr">
					<td>${rowCounts }(条)</td>
					<td>${payCounts }(条)</td>
					<td>${noPayCounts }(条)</td>
					<td>${totalPay/10000 }(万元)</td>
				</tr>
			 </table>
			</div>
			</form>
		</div>
		</div>
	</body>
	<script type="text/javascript"> 
	//已选中项计总数
/* 	var sltedItemCnt = 0;
	//奇偶行变色
	altRow(1, "itemListTab", "odd", "even");
	//鼠标划过行时变色
	hoverRow(1, "itemListTab", "over"); */
	</script>
</HTML>