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
<title>审批划款流水页面</title>
<link rel="stylesheet" type="text/css"
	href="<%=path%>/css/style_zjjg.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/public.css" />
<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery-1.6.js"></script>
<script language="javascript" type="text/javascript" src="<%=path %>/js/timerControl/WdatePicker.js"></script>
<!--[if IE 6]>
    <script type="text/javascript" src="js/ie_png.js"></script>
    <script type="text/javascript">
        ie_png.fix('*');
    </script>
<![endif]-->
<script type="text/javascript">
	$(document).ready(
			function() {
				var $blocks = $('.pay-info-box div.paycons');
				$('.pay-info-ul li').click(
						function() {
							$(this).addClass('selected').siblings().removeClass('selected');
							$($blocks.get($(this).index())).removeClass(
									'hidden').siblings().addClass('hidden');
						});
			})
</script>

<script type="text/javascript">

	
	 $(document).ready(function(){	
	 	 var total="${transferMoney.amt }";
	   	 var transferId="${transferMoney.id}";
	 	 diffMoney(total,transferId);
	});
	
	
	/**
	 *  调用接口查询  pos转账
	 */
	function posWay() {
		var mid  = $("#mid").val();
		var devid  = $("#devid").val();
		var cardNo = $("#cardNo").val();
		var devStan = $("#devStan").val();
		var tranAmt = $("#tranAmt").val();
		var tranDate = $("#tranDate").val();
		var transferId="${transferMoney.id}";
		
		if(null==mid || ""==mid){
			$("#mid").focus();
			alert("商户号不能为空!");
			return;
		}
		
		if(null==devid || ""==devid){
			$("#devid").focus();
			alert("商户设备号!");
			return;
		}
		
		if(null==cardNo || ""==cardNo){
			$("#cardNo").focus();
			alert("付款账号/卡号不能为空!");
			return;
		}
		if(!/^[0-9]+.?[0-9]*$/.test(cardNo)){
			$("#cardNo").focus();
			alert("付款账号/卡号输入错误，只能是数字!");
			return;
		}
		if(null==devStan || ""==devStan){
			$("#devStan").focus();
			alert("交易流水号不能为空!");
			return;
		}
		if(!/^[A-Za-z0-9]*$/.test(devStan)){
			$("#devStan").focus();
			alert("交易流水号输入错误，只能是数字字母!");
			return;
		}
		
		if(null==tranAmt || ""==tranAmt){
			$("#tranAmt").focus();
			alert("金额不能为空!");
			return;
		}
		if(!/^(?!(0[0-9]{0,}$))[0-9]{1,}[.]{0,}[0-9]{0,}$/.test(tranAmt)){
			alert("金额输入错误!");
			$("#tranAmt").focus();
			return;
		}
		if(null==tranDate || ""==tranDate){
			$("#tranDate").focus();
			alert("刷卡日期不能为空!");
			return;
		}
		
		$.ajax({
			type : "POST",
			url : "auditTransferMoney/tmPosWay.do",
			data : {
				"mid":mid,
				"devid" : devid,
				"cardNo":cardNo,
				"devStan":devStan,
				"tranAmt":tranAmt,
				"tranDate":tranDate,
				"transferId":transferId
			},
			success : function(data) {
				if (data.message == '401') {
					alert("数据不完整");
				} else if (data.message == '200') {
					var html = "";
						html += "<tr id='hk'>";
						html +="<input type='hidden'  value='"+ data.req.devid +"'/>";
						html += "<td>" + data.req.tranDate + "</td>";
						html += "<td>" + data.req.devStan + "</td>";
						html += "<td>" + data.req.cardNo + "</td>";
						html += "<td>" + data.req.tranAmt + "</td>";
						html += "<td></td>";
						html += "<td>";
						html += "<input onclick='javascript:addPosInfo(this);' class='item-btn2' type='button' value='添加' />";
						html += "</td>";
						html += "</tr>";
					$("#payinfobody").html(html);
					$("#adddate").html(data.req.tranDate);
				}else if (data.message == '300') {
					alert("查询失败！");
				}else if (data.message == '500') {
					alert("没有查询到数据！");
				}else if (data.message == '100') {
					alert("该笔流水已使用，请重新查询！");
				}
			}
		});
	}
	

	//查询余额
   function diffMoney(total,transferId){
        var posMoney=$("td[name='payMoney']");
        var partMoney=0;
        for(var i=0;i<posMoney.length;i++){
        	var  p=parseFloat(posMoney[i].innerHTML);
        		  partMoney= partMoney+p;
        		}
        		$.ajax({
				type : "POST",
				url : "auditTransferMoney/queryPayMoney.do",
				data : {
					"transferId" : transferId
				},
				success : function(data) {
					if (data.message == '400'){
						alert("数据不完整");
					}else if(data.message == "000"){
						//partMoney=partMoney+data.paiedMoney;
					    var diffMoney=parseInt(total)-parseInt(data.paiedMoney);
		        		var a = diffMoney.toFixed(2);
		        		if(a!=0){
		        		$("#diffMoney").html("还差："+a+"元");
		        		}else{
		        		   $("#diffMoney").html("所付款金额已经等于总额");
	       			    } 
					}
				}
			});
    }
    
    /**
	 *  提交处理
	 */
	function posCommit() {
		var orderId = $('#orderId').val();
		var payDay = $("#finalForm").find("input[name='payDay']").val();
		var devid = $("#finalForm").find("input[name='devid']").val();
		var oppName = $("#finalForm").find("input[name='oppName']").val();
		var payTradeNo = $("#finalForm").find("input[name='payTradeNo']").val();
		var payCardNo = $("#finalForm").find("input[name='payCardNo']").val();
		var payMoney = $("#finalForm").find("input[name='payMoney']").val();
		var note = $("#finalForm").find("input[name='note']").val();
		var a=document.getElementById("posSelect").getElementsByTagName("tr");
		if(a.length !=2){
			alert("请只选择一条数据");
			return ;
		}
		$.ajax({
				type : "POST",
				url : "auditTransferMoney/tmPosCommit.do",
				data : {
					"devid" : devid,
					"oppName" : oppName,
					"transferId" : orderId,
					"payDay" : payDay,
					"payTradeNo" : payTradeNo,
					"payCardNo" : payCardNo,
					"payMoney" : payMoney,
					"note" : note
				},
				success : function(data) {
					if (data.message == '400') {
						alert("传入的审批划款编号为空");
					} else if (data.message == '200') {
						alert("提交成功，状态更新！");
						window.location="<%=path%>/auditTransferMoney/getPageTransferMoney.do";
					} else if (data.message == '300') {
						alert("未选择任何记录！");
					} else if (data.message == '500') {
						alert("添加审核划款支付金额出错！");
					}else if (data.message == '001') {
						alert("流水金额与划款金额不符，请核对总金额！");
					}else if (data.message == '002') {
						alert("流水提交成功，请核对总金额！");
						window.location="<%=path%>/auditTransferMoney/getPageTransferMoney.do";
					}
				}
			});
	}
	
	
	/**
	 *  删除处理
	 */
	function delPosInfo(obj) {
		if (!confirm("确认要删除？")) {
					window.event.returnValue = false;
			}else{
				$(obj).parent().parent().remove();
			}
	}
	
	
	/**
	 *  添加处理
	 */
	function addPosInfo(obj) {
		var devid = $(obj).parent().prev().prev().prev().prev().prev().prev().val();
		var oppName = $(obj).prev().val();
		var payDay = $(obj).parent().prev().prev().prev().prev().prev().html();
		var orderId = $('#orderId').val();
		var payTradeNo = $(obj).parent().prev().prev().prev().prev().html();
		var payCardNo =$(obj).parent().prev().prev().prev().html();
		var payMoney = $(obj).parent().prev().prev().html();
		var note = $(obj).parent().prev().html();
		dualForm(payDay,devid,oppName,payTradeNo,payCardNo,payMoney,note);
		var s = '<tr id="hk"><td>' + payDay + '</td>';
							s += '<td>'+ payTradeNo +'</td>'
							s += '<td>' +  payCardNo + '</td>';
							s += '<td name="payMoney">' + payMoney + '</td>';
							s += '<td>' + note + '</td>';
							s += '<td><input onclick="javascript:delPosInfo(this)" class="item-btn2" type="button" value="删除" />';
							s += '</td></tr>';
							$('#posSelect').append(s);
							$("#hk").remove();
							 $("#mid").val('');
							 $("#devid").val('');
							 $("#cardNo").val('');
							 $("#devStan").val('');
							 $("#tranAmt").val('');
							 $("#tranDate").val('');
		}
		
		
function dualForm(payDay,devid,oppName,payTradeNo,payCardNo,payMoney,note){
		$("#finalForm").find("input[name='payDay']").val(payDay);
		$("#finalForm").find("input[name='devid']").val(devid);
		$("#finalForm").find("input[name='oppName']").val(oppName);
		$("#finalForm").find("input[name='payTradeNo']").val(payTradeNo);
		$("#finalForm").find("input[name='payCardNo']").val(payCardNo);
		$("#finalForm").find("input[name='payMoney']").val(payMoney);
		$("#finalForm").find("input[name='note']").val(note);
	}

</script>
<style type="text/css">
.bg-grey{ background: #fff; width: 109px; height:120px;float: left;}
</style>
</head>

<body>
	<div class="w900">
		<div class="page">
			<div class="contract-info">
				<table width="100%" style="border-spacing:0; border-collapse:0;">
					<col width="15%" />
					<col width="85%" />
					<tr>
						<td class="table-title" valign="middle">划款基本信息
							</td>
							<td>
								<table width="100%" style="border-spacing:0; border-collapse:0;">
									<col width="15%" />
									<col width="11%" />
									<col width="12%"/>
									<col width="11%" />
									<col width="11%" />
									<col width="25%" />
									<col width="15%" />
									<tr>
										<th>地幢号</th>
										<th>小区编号</th>
										<th>企业编号</th>
										<th>划款金额(元)</th>
										<th>出账账户名称</th>
										<th>出账卡号</th>
										<th>状态</th>
									</tr>
									<tr>
									<input type="hidden" id="orderId" value="${transferMoney.id }" />
										<td>${transferMoney.houseNo }</td>
										<td>${transferMoney.blockNo }</td>
										<th>${transferMoney.enterpriseNo}</th>
										<td><fmt:formatNumber value="${transferMoney.amt }" pattern="#.00"/></td>
										<td>${transferMoney.outCardName }</td>
										<td>${transferMoney.outCardNo }</td>
										<!-- 状态代码转换为文字 -->
										<c:if test="${transferMoney.state== '1111' }">
											<td>订单刚创建</td>
										</c:if>
										<c:if test="${transferMoney.state== '0000' }">
											<td>订单确认，等待划款</td>
										</c:if>
										<c:if test="${transferMoney.state== '0010' }">
											<td>打款完成</td>
										</c:if>
										<!-- 状态代码转换为文字  end -->
									</tr>
								</table></td>
					</tr>
				</table>
			</div>
			<div class="pay-info co" style="height: 120px;">
				<div class="bg-grey">
					<ul class="pay-info-ul">
						<li class="selected">POS</li>
					</ul>
				</div>					
				<div class="pay-info-box">
					<div class="paycons pay-info-box1">
						<ul>
							<li>
								<span class="item-label"><label>商户号：</label></span> 
								<input class="input172 fleft" name="" type="text" id="mid"/>
							</li>
							<li>
								<span class="item-label"><label>商户设备号：</label></span> 
								<input class="input172 fleft" name="" type="text" id="devid"/>
							</li>
							<li>
								<span class="item-label"><label>付款账号/卡号：</label></span> 
								<input class="input172 fleft" name="" type="text" id="cardNo"/>
							</li>
							<li>
								<span class="item-label"><label>流水号：</label></span> 
								<input class="input172 fleft" name="" type="text" id="devStan"/>
							</li>
							<li>
								<span class="item-label"><label>刷卡金额：</label></span>
								<input class="input172 fleft" name="" type="text" id="tranAmt"/>
							</li>
							<li>
								<span class="item-label"><label>刷卡日期：</label></span> 
								<input id="tranDate" readonly="readonly" class="input172 Wdate fleft" style="height:23px;" name="" type="text"  onclick="WdatePicker({Date:'%y-%M-%d',dateFmt:'yyyy-MM-dd'})" />
							</li>
						</ul>
						<div class="check-btn" style="float: right;margin-right: -80px;margin-top: -36px;">
							<input class="item-btn1" type="button" onclick="posWay()" value="查询" />
						</div>
					</div>
				</div>				
			</div>
			<div class="check-result">
				<div class="check-result-box">
					<table width="100%" style="border-spacing:0; border-collapse:0;">
						<col width="15%" />
						<col width="85%" />
						<tr>
							<td class="table-title" valign="middle">查询结果
								</td>
								<td>
									<table width="100%"
										style="border-spacing:0; border-collapse:0;">
										<col width="15%" />
										<col width="15%" />
										<col width="25%" />
										<col width="15%" />
										<col width="20%" />
										<col width="10%" />
										<tr>
											<th>交易日期</th>
											<th>流水号</th>
											<th>账号</th>
											<th>金额(元)</th>
											<th>附言</th>
											<th></th>
										</tr>
										<tbody id="payinfobody"></tbody>
									</table></td>
						</tr>
					</table>
				</div>
			</div>
			<div class="check-result">
				<div class="check-result-box">
					<table width="100%" style="border-spacing:0; border-collapse:0;">
						<col width="15%" />
						<col width="85%" />
						<tr>
							<td class="table-title" valign="middle">已添加
								</td>
								<td>
									<table width="100%"
										style="border-spacing:0; border-collapse:0;" id="posSelect">
										<col width="15%" />
										<col width="15%" />
										<col width="25%" />
										<col width="15%" />
										<col width="20%" />
										<col width="10%" />
										<tr>
											<th>交易日期</th>
											<th>流水号</th>
											<th>账号</th>
											<th>金额(元)</th>
											<th>附言</th>
											<th></th>
										</tr>
										
									</table></td>
						</tr>
					</table>
				</div>
			</div>
			<div style="color: red" id="diffMoney"></div>
			<div class="btn-div">
				<input class="item-btn1 mt20"   type="button" value="提交" onclick="posCommit()" />
			</div>
			<div style="display:none" id="finalForm">
				<input type="hidden" value="" name="payDay" />
				<input type="hidden" value="" name="devid" />
				<input type="hidden" value="" name="oppName" />
				<input type="hidden" value="" name="payTradeNo" />
				<input type="hidden" value="" name="payCardNo" />
				<input type="hidden" value="" name="payMoney" />
				<input type="hidden" value="" name="note" />
			</div>
		</div>
	</div>
</body>
</html>
