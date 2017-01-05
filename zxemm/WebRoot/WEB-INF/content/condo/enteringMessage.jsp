<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/content/base/tag.inc" />
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>审批划款补录信息页面</title>
		<meta http-equiv=Content-Type content="text/html; charset=utf-8" />
		<meta http-equiv=Expires content=0 />
		<script type="text/javascript" src="<%=path%>/js/jquery-1.6.js"> </script>
		<script type="text/javascript" src="<%=path%>/js/base.js"> </script>
		<script type="text/javascript" src="<%=path%>/js/formValidate.js"> </script>
		<link href="<%=path%>/css/Style1.css" rel="stylesheet" type="text/css" />
		<link href="<%=path%>/css/Style2.css" rel="stylesheet" type="text/css" />
		<script language=JavaScript>
			var banklist = [
				{"bankId":"302", "bankName":"中信银行"},
				{"bankId":"305", "bankName":"中国民生银行"},
            	{"bankId":"447", "bankName":"兰州银行"},
            	{"bankId":"100", "bankName":"中国邮政储蓄银行"},
            	{"bankId":"102", "bankName":"中国工商银行"},
            	{"bankId":"103", "bankName":"中国农业银行"},
            	{"bankId":"104", "bankName":"中国银行"},
            	{"bankId":"105", "bankName":"中国建设银行"},
            	{"bankId":"301", "bankName":"交通银行"},
            	{"bankId":"303", "bankName":"中国光大银行"},
            	{"bankId":"304", "bankName":"华夏银行"},
            	{"bankId":"306", "bankName":"广发银行"},
            	{"bankId":"307", "bankName":"平安银行"},
            	{"bankId":"308", "bankName":"招商银行"},
            	{"bankId":"309", "bankName":"兴业银行"},
            	{"bankId":"310", "bankName":"上海浦东发展银行"},
            	{"bankId":"311", "bankName":"恒丰银行"},
            	{"bankId":"316", "bankName":"浙商银行"},
            	{"bankId":"317", "bankName":"渤海银行"},
            	{"bankId":"401", "bankName":"上海银行"},
            	{"bankId":"402", "bankName":"厦门银行"},
            	{"bankId":"403", "bankName":"北京银行"},
            	{"bankId":"404", "bankName":"烟台银行"},
            	{"bankId":"405", "bankName":"福建海峡银行"},
            	{"bankId":"408", "bankName":"宁波银行"},
            	{"bankId":"409", "bankName":"齐鲁银行"},
            	{"bankId":"411", "bankName":"焦作市商业银行"},
            	{"bankId":"412", "bankName":"温州银行"},
            	{"bankId":"413", "bankName":"广州银行"},
            	{"bankId":"414", "bankName":"汉口银行"},
            	{"bankId":"417", "bankName":"盛京银行"},
            	{"bankId":"418", "bankName":"洛阳银行"},
            	{"bankId":"419", "bankName":"辽阳银行"},
            	{"bankId":"420", "bankName":"大连银行"},
            	{"bankId":"421", "bankName":"苏州银行"},
            	{"bankId":"422", "bankName":"河北银行"},
            	{"bankId":"423", "bankName":"杭州银行"},
            	{"bankId":"424", "bankName":"南京银行"},
            	{"bankId":"425", "bankName":"东莞银行"},
            	{"bankId":"426", "bankName":"金华银行"},
            	{"bankId":"427", "bankName":"乌鲁木齐市商业银行"},
            	{"bankId":"428", "bankName":"绍兴银行"},
            	{"bankId":"429", "bankName":"成都银行"},
            	{"bankId":"430", "bankName":"抚顺银行"},
            	{"bankId":"431", "bankName":"临商银行"},
            	{"bankId":"432", "bankName":"湖北银行"},
            	{"bankId":"433", "bankName":"葫芦岛银行"},
            	{"bankId":"434", "bankName":"天津银行"},
            	{"bankId":"435", "bankName":"郑州银行"},
            	{"bankId":"436", "bankName":"宁夏银行"},
            	{"bankId":"437", "bankName":"珠海华润银行"},
            	{"bankId":"438", "bankName":"齐商银行"},
            	{"bankId":"439", "bankName":"锦州银行"},
            	{"bankId":"440", "bankName":"徽商银行"},
            	{"bankId":"441", "bankName":"重庆银行"},
            	{"bankId":"442", "bankName":"哈尔滨银行"},
            	{"bankId":"443", "bankName":"贵阳市商业银行"},
            	{"bankId":"444", "bankName":"西安银行"},
            	{"bankId":"446", "bankName":"丹东银行"},
            	{"bankId":"448", "bankName":"南昌银行"},
            	{"bankId":"449", "bankName":"晋商银行"},
            	{"bankId":"450", "bankName":"青岛银行"},
            	{"bankId":"451", "bankName":"吉林银行"},
            	{"bankId":"454", "bankName":"九江银行"},
            	{"bankId":"455", "bankName":"日照银行"},
            	{"bankId":"456", "bankName":"鞍山市商业银行"},
            	{"bankId":"457", "bankName":"秦皇岛市商业银行"},
            	{"bankId":"458", "bankName":"青海银行"},
            	{"bankId":"459", "bankName":"台州银行"},
            	{"bankId":"461", "bankName":"长沙银行"},
            	{"bankId":"462", "bankName":"潍坊银行"},
            	{"bankId":"463", "bankName":"赣州银行"},
            	{"bankId":"464", "bankName":"泉州银行"},
            	{"bankId":"465", "bankName":"营口银行"},
            	{"bankId":"466", "bankName":"富滇银行"},
            	{"bankId":"467", "bankName":"阜新银行"},
            	{"bankId":"470", "bankName":"嘉兴银行"},
            	{"bankId":"472", "bankName":"廊坊银行"},
            	{"bankId":"473", "bankName":"浙江泰隆商业银行"},
            	{"bankId":"474", "bankName":"内蒙古银行"},
            	{"bankId":"475", "bankName":"湖州银行"},
            	{"bankId":"478", "bankName":"广西北部湾银行"},
            	{"bankId":"479", "bankName":"包商银行"},
            	{"bankId":"481", "bankName":"威海市商业银行"},
            	{"bankId":"483", "bankName":"攀枝花市商业银行"},
            	{"bankId":"485", "bankName":"绵阳市商业银行"},
            	{"bankId":"486", "bankName":"泸州市商业银行"},
            	{"bankId":"487", "bankName":"大同市商业银行"},
            	{"bankId":"488", "bankName":"三门峡银行"},
            	{"bankId":"489", "bankName":"广东南粤银行"},
            	{"bankId":"490", "bankName":"张家口市商业银行"},
            	{"bankId":"491", "bankName":"桂林银行"},
            	{"bankId":"493", "bankName":"江苏长江商业银行"},
            	{"bankId":"495", "bankName":"柳州银行"},
            	{"bankId":"496", "bankName":"南充市商业银行"},
            	{"bankId":"497", "bankName":"莱商银行"},
            	{"bankId":"498", "bankName":"德阳银行"},
            	{"bankId":"500", "bankName":"六盘水市商业银行"},
            	{"bankId":"502", "bankName":"曲靖市商业银行"},
            	{"bankId":"701", "bankName":"昆仑银行"},
            	{"bankId":"1401", "bankName":"上海农村商业银行"},
            	{"bankId":"1402", "bankName":"昆山农村商业银行"},
            	{"bankId":"1403", "bankName":"江苏常熟农村商业银行"},
            	{"bankId":"1404", "bankName":"深圳农村商业银行"},
            	{"bankId":"1405", "bankName":"广州农村商业银行"},
            	{"bankId":"1406", "bankName":"浙江萧山农村合作银行"},
            	{"bankId":"1407", "bankName":"广东南海农村商业银行"},
            	{"bankId":"1408", "bankName":"佛山顺德农村商业银行"},
            	{"bankId":"1409", "bankName":"昆明市农村信用合作社联合社"},
            	{"bankId":"1410", "bankName":"湖北省农村信用社联合社"},
            	{"bankId":"1411", "bankName":"徐州市市郊农村信用合作社联合社"},
            	{"bankId":"1412", "bankName":"江苏江阴农村商业银行"},
            	{"bankId":"1413", "bankName":"重庆农村商业银行"},
            	{"bankId":"1414", "bankName":"山东省农村信用社联合社"},
            	{"bankId":"1415", "bankName":"东莞农村商业银行"},
            	{"bankId":"1416", "bankName":"张家港农村商业银行"},
            	{"bankId":"1417", "bankName":"福建省农村信用社联合社"},
            	{"bankId":"1418", "bankName":"北京农村商业银行"},
            	{"bankId":"1419", "bankName":"天津农村合作银行"},
            	{"bankId":"1420", "bankName":"宁波鄞州农村合作银行"},
            	{"bankId":"1421", "bankName":"佛山市三水区农村信用合作社联合社"},
            	{"bankId":"1422", "bankName":"成都市农村信用合作社联合社"},
            	{"bankId":"1423", "bankName":"沧州市农村信用合作社联合社"},
            	{"bankId":"1424", "bankName":"江苏省农村信用合作社联合社"},
            	{"bankId":"1425", "bankName":"江门市新会农村信用合作社联合社"},
            	{"bankId":"1426", "bankName":"高要市农村信用合作社联合社"},
            	{"bankId":"1427", "bankName":"佛山农村商业银行"},
            	{"bankId":"1428", "bankName":"吴江农村商业银行"},
            	{"bankId":"1429", "bankName":"浙江省农村信用社联合社"},
            	{"bankId":"1430", "bankName":"江苏东吴农村商业银行"},
            	{"bankId":"1431", "bankName":"珠海农商银行"},
            	{"bankId":"1432", "bankName":"中山农村信用合作社联合社"},
            	{"bankId":"1433", "bankName":"太仓农村商业银行"},
            	{"bankId":"1434", "bankName":"临汾市尧都市农村信用合作社联合社"},
            	{"bankId":"1436", "bankName":"贵州省农村信用社联合社"},
            	{"bankId":"1437", "bankName":"无锡农村商业银行"},
            	{"bankId":"1438", "bankName":"湖南省农村信用社联合社"},
            	{"bankId":"1439", "bankName":"江西省农村信用社联合社"},
            	{"bankId":"1442", "bankName":"陕西省农村信用社联合社"},
            	{"bankId":"1501", "bankName":"江苏银行"},
            	{"bankId":"1502", "bankName":"邯郸市商业银行"},
            	{"bankId":"1503", "bankName":"邢台银行"},
            	{"bankId":"1504", "bankName":"承德银行"},
            	{"bankId":"1505", "bankName":"沧州银行"},
            	{"bankId":"1506", "bankName":"晋城市商业银行"},
            	{"bankId":"1507", "bankName":"鄂尔多斯银行"},
            	{"bankId":"1508", "bankName":"上饶银行"},
            	{"bankId":"1509", "bankName":"东营市商业银行"},
            	{"bankId":"1510", "bankName":"济宁银行"},
            	{"bankId":"1511", "bankName":"泰安市商业银行"},
            	{"bankId":"1512", "bankName":"德州银行"},
            	{"bankId":"1513", "bankName":"开封市商业银行"},
            	{"bankId":"1514", "bankName":"漯河市商业银行"},
            	{"bankId":"1515", "bankName":"商丘市商业银行"},
            	{"bankId":"1516", "bankName":"南阳市商业银行"},
            	{"bankId":"1517", "bankName":"浙江民泰商业银行"},
            	{"bankId":"1518", "bankName":"龙江银行"},
            	{"bankId":"1519", "bankName":"浙江稠州商业银行"},
            	{"bankId":"1520", "bankName":"安徽省农村信用联社"},
            	{"bankId":"1521", "bankName":"广西壮族自治区农村信用社联合社"},
            	{"bankId":"1522", "bankName":"海南省农村信用社联合社"},
            	{"bankId":"1523", "bankName":"云南省农村信用社联合社"},
            	{"bankId":"1524", "bankName":"宁夏黄河农村商业银行"},
            	{"bankId":"9990", "bankName":"其他城市商业银行"},
            	{"bankId":"9991", "bankName":"其他农村商业银行"},
            	{"bankId":"9992", "bankName":"其他农村合作银行"},
            	{"bankId":"9993", "bankName":"其他城市信用社"},
            	{"bankId":"9994", "bankName":"其他农村信用社"},
            	{"bankId":"3000", "bankName":"汇丰银行"},
            	{"bankId":"3001", "bankName":"东亚银行"},
            	{"bankId":"3002", "bankName":"南洋商业银行"},
            	{"bankId":"3003", "bankName":"恒生银行(中国)有限公司"},
            	{"bankId":"3004", "bankName":"中国银行（香港）有限公司"},
            	{"bankId":"3005", "bankName":"集友银行有限公司"},
            	{"bankId":"3006", "bankName":"创兴银行有限公司"},
            	{"bankId":"3007", "bankName":"星展银行（中国）有限公司"},
            	{"bankId":"3008", "bankName":"永亨银行（中国）有限公司"},
            	{"bankId":"3009", "bankName":"永隆银行"},
            	{"bankId":"3010", "bankName":"花旗银行（中国）有限公司"},
            	{"bankId":"3011", "bankName":"美国银行有限公司"},
            	{"bankId":"3012", "bankName":"摩根大通银行(中国)有限公司"},
            	{"bankId":"3013", "bankName":"三菱东京日联银行(中国）有限公司"},
            	{"bankId":"3014", "bankName":"日本三井住友银行股份有限公司"},
            	{"bankId":"3015", "bankName":"瑞穗实业银行（中国）有限公司"},
            	{"bankId":"3016", "bankName":"日本山口银行股份有限公司"},
            	{"bankId":"3017", "bankName":"外换银行（中国）有限公司"},
            	{"bankId":"3018", "bankName":"友利银行(中国)有限公司"},
            	{"bankId":"3021", "bankName":"韩国产业银行"},
            	{"bankId":"3022", "bankName":"新韩银行(中国)有限公司"},
            	{"bankId":"3023", "bankName":"韩国中小企业银行"},
            	{"bankId":"3024", "bankName":"韩亚银行（中国）有限公司"},
            	{"bankId":"3025", "bankName":"华侨银行（中国）有限公司"},
            	{"bankId":"3026", "bankName":"大华银行（中国）有限公司"},
            	{"bankId":"3028", "bankName":"泰国盘谷银行(大众有限公司)"},
            	{"bankId":"3029", "bankName":"奥地利中央合作银行股份有限公司"},
            	{"bankId":"3030", "bankName":"比利时联合银行股份有限公司"},
            	{"bankId":"3031", "bankName":"比利时富通银行有限公司"},
            	{"bankId":"3032", "bankName":"荷兰银行"},
            	{"bankId":"3033", "bankName":"荷兰安智银行股份有限公司"},
            	{"bankId":"3034", "bankName":"渣打银行"},
            	{"bankId":"3035", "bankName":"英国苏格兰皇家银行公众有限公司"},
            	{"bankId":"3036", "bankName":"法国兴业银行（中国)有限公司"},
            	{"bankId":"3037", "bankName":"法国东方汇理银行股份有限公司"},
            	{"bankId":"3038", "bankName":"法国外贸银行股份有限公司"},
            	{"bankId":"3039", "bankName":"德国德累斯登银行股份公司"},
            	{"bankId":"3040", "bankName":"德意志银行（中国）有限公司"},
            	{"bankId":"3041", "bankName":"德国商业银行股份有限公司"},
            	{"bankId":"3042", "bankName":"德国西德银行股份有限公司"},
            	{"bankId":"3043", "bankName":"德国巴伐利亚州银行"},
            	{"bankId":"3044", "bankName":"德国北德意志州银行"},
            	{"bankId":"3045", "bankName":"意大利联合圣保罗银行股份有限公司"},
            	{"bankId":"3046", "bankName":"瑞士信贷银行股份有限公司"},
            	{"bankId":"3047", "bankName":"瑞士银行"},
            	{"bankId":"3048", "bankName":"加拿大丰业银行有限公司"},
            	{"bankId":"3049", "bankName":"加拿大蒙特利尔银行有限公司"},
            	{"bankId":"3050", "bankName":"澳大利亚和新西兰银行集团有限公司"},
            	{"bankId":"3051", "bankName":"摩根士丹利国际银行（中国）有限公司"},
            	{"bankId":"3052", "bankName":"联合银行(中国)有限公司"},
            	{"bankId":"3053", "bankName":"荷兰合作银行有限公司"},
            	{"bankId":"3054", "bankName":"厦门国际银行"},
            	{"bankId":"3055", "bankName":"法国巴黎银行（中国）有限公司"},
            	{"bankId":"3056", "bankName":"华商银行"},
            	{"bankId":"3057", "bankName":"华一银行"},
            ];
	function bankType(){
		var size = banklist.length;
		var html = "";
		for(var i = 0; i < banklist.length; i++){
		var bank = banklist[i];
		if(bank == null || bank.bankId == "" || bank.bankName == ""){
			continue;
		}
		html += "<option value='" + bank.bankId + "'>" + bank.bankName + "</option>";
	}
		$("#outBankId").html(html);
	}
	
	function isCardNo(card)  
	{  
	   // 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X  
	   var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  
	   if(reg.test(card) === false){  
	       alert("身份证输入不合法");  
	       return  false;  
	   }else{
		   return true;
	   }  
	}  
	
	
	function  checkMobile(str) {
	    var  reg = /^1[3|4|5|7|8][0-9]{9}$/; //验证规则
	    if (reg.test(str)) {
	        //alert("正确");
	        return true;
	    } else {
	    	 alert("手机号码有误，请重填"); 
	    	 return false;
	    }
	}
	
	$(function(){
		bankType();
	});
			//按钮提交事件 
			function submitForm()
			{
				
					var outCardName = document.getElementById("outCardName");
					var outPhone = document.getElementById("outPhone");
					var outIdNo = document.getElementById("outIdNo");
					var outCardNo = document.getElementById("outCardNo");

					if(!checkFormdata(outCardName,"出账账户名称",50,true,true,false,false,false))
					{	
						document.getElementById("outCardName").focus();
						return;
					}
					if(!checkFormdata(outPhone,"出账人电话",11,true,true,false,false,true))
					{	
						document.getElementById("outPhone").focus();
						return;
					}
					if(!checkMobile($("#outPhone").val())){
						document.getElementById("outPhone").focus();
						return;
					}
					if(!checkFormdata(outIdNo,"出账人身份证号",18,true,true,false,false,false))
					{	document.getElementById("outIdNo").focus();
						return;
					}
					if(!isCardNo($("#outIdNo").val())){
						document.getElementById("outIdNo").focus();
						return;
					}
					if(!checkFormdata(outCardNo,"收款卡号",-1,true,true,false,false,false))
					{	
						$("#outCardNo").focus();
						return;
					}
					if(!/^[0-9]*$/.test($("#outCardNo").val())){
						$("#outCardNo").focus();
						alert("收款卡号输入错误，只能是数字!");
						return;
					}
				$.ajax({
					type : "POST",
					dataType : "json",
					data : $("#PageForm").serialize(),
					url : "auditTransferMoney/enteringOutMessage.do",
					success : function(data){
						if(data.message.indexOf("补录信息完成！") == 0){
							alert("补录信息成功！");
							window.location.href="auditTransferMoney/getPageTransferMoney.do";
						};
						if(data.message.indexOf("实名认证不通过！") == 0){
							alert("以上信息未通过实名认证，请核实！");
						};
					}
				});
				
			}
		</script>
	</head>
	<body>
	<div id=mainzone>
		<div id="body">
			<!-- 提交表单 -->
			<form id=PageForm name=PageForm  method=post>
				<!-- 系统功能路径 -->
				<div class="loc">
					<div class="groupmenu" id="groupmenu"></div>
					<!-- 
					<a href="<%=path %>/login/mainFrame.do"></a> &raquo;
					 -->
					<span>新房资金监管</span> &raquo;
					<a href="javascript:gotoListHome()">审核划款管理</a> &raquo;
					<span>划款申请列表</span> &raquo; 
					<span class="last">补录划款信息</span>
				</div>

				<!-- 主体DIV -->
				<div class="tab_cntr">
					<table class="editorTb" style="" id="tbBase"  name="editorTab">
						<tr>
							<td class="hd" colspan="2">
								补录划款信息（<font color="red">* 为必填项</font>）
							</td>
						</tr>
						<tr>
							<td class="titl">
								<b>收款账户户名：</b>
							</td>
							<td>
								<input type="hidden" name="transferMoney.houseNo" value="${transferMoney.houseNo }"/>
								<input id="outCardName" name="transferMoney.outCardName" type="text" style="width:198px;height:30px;line-height:30px;font-size:14px;color:#666;border:1px solid #ddd" />
								<font color="red">&nbsp;*</font>
							</td>
						</tr>
						<tr>
							<td class="titl">
								<b>出账人电话：</b>
							</td>
							<td>
								<input id="outPhone" name="transferMoney.outPhone" type="text" style="width:198px;height:30px;line-height:30px;font-size:14px;color:#666;border:1px solid #ddd" maxlength="11"/>
								<font color="red">&nbsp;*</font>
							</td>
						</tr>
						<tr>
							<td class="titl">
								<b>出账人身份证号：</b>
							</td>
							<td>
								<input id="outIdNo" name="transferMoney.outIdNo" type="text" style="width:198px;height:30px;line-height:30px;font-size:14px;color:#666;border:1px solid #ddd" />
								<font color="red">&nbsp;*</font>
							</td>
						</tr>
						<tr>
							<td class="titl">
								<b>银行类型：</b>
							</td>
							<td>
								<select id="outBankId" name="transferMoney.outBankId" style="width:198px;height:30px;line-height:30px;font-size:14px;color:#666;border:1px solid #ddd"></select>
								<font color="red">&nbsp;*</font>
							</td>
						</tr>
						<tr>
							<td class="titl">
								<b>收款卡号：</b>
							</td>
							<td>
								<input id="outCardNo" name="transferMoney.outCardNo" type="text" style="width:198px;height:30px;line-height:30px;font-size:14px;color:#666;border:1px solid #ddd"/>
								<font color="red">&nbsp;*</font>
							</td>
						</tr>
					</table>

					<!-- 提交按钮 -->
					<div style="padding: 10px; padding-left: 10px;">
						<div style="width: 250px; float: left;">
							<input name="button" type=button onclick="javascript:history.go(-1)" class="return" />
						</div>
						<input type="button" name="btnSave"  onclick="submitForm()" id="btnSave" class="save" />
						<input name="Submit" type="reset" value="" class="reset" />
						<div class="clr"></div>
					</div>
				</div>
			</form>
			</div>
		</div>
	</body>
</html>