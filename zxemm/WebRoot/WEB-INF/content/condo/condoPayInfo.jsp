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
<title>预售房流水页面</title>
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

	/**
	 *  调用差额查询
	 */
	 $(document).ready(function(){	
	  var total="${contactInfo.contactAmt }";
	  var contactNo="${contactInfo.contactNo}";
	  diffMoney(total,contactNo);
	});
	/**
	 *  调用接口查询  pos转账---用到yhm
	 */
	function posWay() {
		var mid  = $("#mid").val();
		var devid  = $("#devid").val();
		var cardNo = $("#cardNo").val();
		var devStan = $("#devStan").val();
		var tranAmt = $("#tranAmt").val();
		var tranDate = $("#tranDate").val();
		var contactNo="${contactInfo.contactNo}";
		$.ajax({
			type : "POST",
			url : "<%=path%>/buyerNewHouse/cdPosWay.do",
			data : {
				"mid":mid,
				"devid" : devid,
				"cardNo":cardNo,
				"devStan":devStan,
				"tranDate":tranDate,
				"contactNo":contactNo,
				"tranAmt":tranAmt,
				"state":"condo"
			},
			success : function(data) {
				if (data.message == '401') {
					alert("数据不完整");
				} else if (data.message == '200') {
					var html = "";
						html += "<tr>";
						html +="<input type='hidden'  value='"+ data.req.devid +"'/>"
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
	
	/**
	 *  添加处理---有用yhm
	 */
	function addPosInfo(obj) {
	    var contactNo="${contactInfo.contactNo}";
		var devid = $(obj).parent().prev().prev().prev().prev().prev().prev().val();
		var oppName = $(obj).prev().val();
		var payDay = $(obj).parent().prev().prev().prev().prev().prev().html();
		var payTradeNo = $(obj).parent().prev().prev().prev().prev().html();
		var payTradeNopre=$("td[name='payTradeNo']").text();
		if(payTradeNopre!=""){
		alert("只能添加一条，请提交！"+payTradeNopre);
		return;
		}
		var payCardNo =$(obj).parent().prev().prev().prev().html();
		var payMoney = $(obj).parent().prev().prev().html();
		var note = $(obj).parent().prev().html();
 		var total="${contactInfo.contactAmt }";
 		dualForm(payDay,devid,oppName,payTradeNo,payCardNo,payMoney,note);
		var s = '<tr ><td>' + payDay + '</td>';
							s += '<td name="payTradeNo">'+ payTradeNo +'</td>'
							s += '<td >' +  payCardNo + '</td>';
							s += '<td name="payMoney">' + payMoney + '</td>';
							s += '<td>' + note + '</td>';
							s += '<td><input onclick="javascript:delPosInfo(this)" class="item-btn2" type="button" value="删除" />';
							s += '</td></tr>';
							$('#posSelect').append(s);
							//diffMoney(total,contactNo);				
	}
	
	
	function dualForm(payDay,devid,oppName,payTradeNo,payCardNo,payMoney,note){
		$("#finalForm").find("input[name='tranDate']").val(payDay);
		$("#finalForm").find("input[name='devid']").val(devid);
		$("#finalForm").find("input[name='oppName']").val(oppName);
		$("#finalForm").find("input[name='payTradeNo']").val(payTradeNo);
		$("#finalForm").find("input[name='payCardNo']").val(payCardNo);
		$("#finalForm").find("input[name='payMoney']").val(payMoney);
		$("#finalForm").find("input[name='note']").val(note);
	}
	
	/**
	 *  删除处理---有用yhm
	 */
	function delPosInfo(obj) {
	    var contactNo="${contactInfo.contactNo}";
	    var total="${contactInfo.contactAmt }";
		if (!confirm("确认要删除？")) {
					window.event.returnValue = false;
			}else{
				$(obj).parent().parent().remove();
				//diffMoney(total,contactNo);	
			}
		}
	/**
	* 计算差额--有用yhm
	*/
    function diffMoney(total,contactNo){
       // var posMoney=$("td[name='payMoney']");
        var partMoney=0;
     /*    for(var i=0;i<posMoney.length;i++){
        	var  p=parseFloat(posMoney[i].innerHTML);
        		  partMoney= partMoney+p;
        		} */
        var pMoney = $("#finalForm").find("input[name='payMoney']").val();
        		$.ajax({
				type : "POST",
				url : "<%=path%>/buyerNewHouse/qurPaiedMoney.do",
				data : {
					"contactNo" : contactNo
				},
				success : function(data) {
					if (data.message == '400') {
					alert("传入的合同号不能为空！");
				  }else if(data.message == '300'){
				  	alert("支付流水总金额与已支付金额不等，请联系管理员！");
				  }else if (data.message == '200') {
					partMoney=pMoney*1+data.paiedMoney*1;
				    var diffMoney=total-partMoney;
	        		var a = diffMoney.toFixed(2);
	        		if(a!=0){
	        		$("#difMoney").html("还差："+a+"元");
	        		}else{
	        		   $("#difMoney").html("所付款金额已经等于总额");
       			         } 
					}else if (data.message == '002') {
						alert("查询已支付金额出错了！");
					}
				}
			});
    }
	
	/**
	 *  提交处理
	 */
	function condoPosCommit(obj) {
	    var contactNo = "${contactInfo.contactNo }";  
		var payDay = $("#finalForm").find("input[name='tranDate']").val();
		var devid = $("#finalForm").find("input[name='devid']").val();
		var oppName = $("#finalForm").find("input[name='oppName']").val();
		var payTradeNo = $("#finalForm").find("input[name='payTradeNo']").val();
		var payCardNo = $("#finalForm").find("input[name='payCardNo']").val();
		var payMoney = $("#finalForm").find("input[name='payMoney']").val();
		var note = $("#finalForm").find("input[name='note']").val();
		//提交时判断金额是否为空
		if(""==payMoney ||null==payMoney){
			alert("支付金额不能为空!");
			return;
		}
		var total="${contactInfo.contactAmt }";   
			$.ajax({
				type : "POST",
				url : "buyerNewHouse/cadoPosCommit.do",
				data : {
					"contactNo" : contactNo,  
					"note":note,                
					"devid" : devid,          
					"payCardNo":payCardNo,       
					"payTradeNo":payTradeNo,  
					"payMoney":payMoney,      
					"payDay":payDay,          
					"oppName":oppName         
				},
				success : function(data) {
					if (data.message == '400') {
						alert("传入的合同号为空");
					} else if (data.message == '200') {
						alert("提交成功，状态更新！");
						window.location="<%=path%>/buyerNewHouse/getPageContactInfo.do";
					} else if (data.message == '300') {
						alert("未选择任何记录！");
					}else if (data.message == '001') {
						alert("支付金额超出合同总额,请核对金额!！");
					}else if(data.message=='301'){
					    alert("添加支付金额出错!!");
					}
				}
			});
	}
</script>
<style type="text/css">
.bg-grey{ background: #eee; width: 109px; height:120px;float: left;}
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
						<td class="table-title" valign="middle">合同基本信息
							</td>
							<td>
								<table width="100%" style="border-spacing:0; border-collapse:0;">
									<col width="15%" />
									<col width="16%" />
									<col width="22%"/>
									<col width="11%" />
									<col width="11%" />
									<col width="15%" />
									<col width="10%" />
									<tr>
										<th>合同编号</th>
										<th>合同金额（元）</th>
										<th>退款银行卡姓名</th>
										<th>房屋坐落</th>
										<th>面积(㎡)</th>
										<th>室号</th>
										<th>支付状态</th>
									</tr>
									<tr>
									<input type="hidden" id="orderId" value="${orderInfo.orderId }" />
										<td>${contactInfo.contactNo }</td>
										<td id="total">${contactInfo.contactAmt }</td>
										<td>${contactInfo.backCardName }</td>
										<th>${contactInfo.houseAddr}</th>
										<td >${contactInfo.houseArea }</td>
										<td>${contactInfo.roomNo }</td>
										<!-- 状态代码转换为文字 -->
										
										
										
										<c:if test="${contactInfo.payStatus== '0000' }">
											<td>未支付</td>
										</c:if>
										<c:if test="${contactInfo.payStatus== '0010' }">
											<td>支付成功</td>
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
						<li class="selected">补充POS流水</li>
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
							<input class="item-btn1" type="button" onclick="javascript:posWay();" value="查询" />
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
										<c:if test="${(fn:length(payInfos) ne 0) or (payInfos ne null) }">
											<c:forEach items="${payInfos }" var="payInfo">
											<tr>
												<td>${payInfo.payDay }</td>
												<td>${payInfo.payTradeNo }</td>
												<td>${payInfo.payCardNo }</td>
												<td name="payMoney" >${payInfo.payMoney }</td>
												<td>${payInfo.note}</td>
												<td>
												<input type="hidden"  value="${payInfo.payId }"/>
												<input onclick="javascript:delPosInfo(this)"
													class="item-btn2" type="button" value="删除" />
												</td>
											</tr>
											</c:forEach>
										</c:if>
									</table></td>
						</tr>
					</table>
				</div>
			</div>
			<div style="color: red" id="difMoney"></div>
			<div class="btn-div">
				<input class="item-btn1 mt20"   type="button" value="提交" onclick="condoPosCommit(this)" />
			</div>
			<div style="display:none" id="finalForm">
				<input type="hidden" value="" name="tranDate" />
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
