<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/content/base/tag.inc" />
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<HEAD>
		<TITLE>编辑网点/ATM信息</TITLE>
		<META http-equiv=Content-Type content="text/html; charset=utf-8">
		<META http-equiv=Expires content=0>
		<script type="text/javascript" src="<%=path%>/js/jquery-1.6.js">
		</script>
		<script type="text/javascript" src="<%=path%>/js/base.js">
		</script>
		<script type="text/javascript" src="<%=path%>/js/formValidate.js">
		</script>
		<link href="<%=path%>/css/Style1.css" rel="stylesheet" type="text/css" />
		<link href="<%=path%>/css/Style2.css" rel="stylesheet" type="text/css" />
		<SCRIPT language=JavaScript>
			
			//按钮提交事件
			function submitForm(url, act)
			{
			    if(act == "modify")
				{
			    	$("#name").val($.trim($("#name").val()));
			    	$("#cityName").val($.trim($("#wxCity").find("option:selected").text()));//将id为wxCity的文本值赋值给id为cityName
			    	$("#regionName").val($.trim($("#region").find("option:selected").text()));//
			    	$("#address").val($.trim($("#address").val()));
			    	$("#mobilePhone").val($.trim($("#mobilePhone").val()));
			    	$("#locationY").val($.trim($("#locationY").val()));
			    	$("#locationX").val($.trim($("#locationX").val()));
			    	var name = document.getElementById("name");
			    	var cityName = document.getElementById("cityName");
			    	var regionName = document.getElementById("regionName");
			    	var address = document.getElementById("address");
			    	var mobilePhone = document.getElementById("mobilePhone");
			    	if($("#type").val()==""){
			    		alert("请选择类型！");
			    		return false;
			    	}else if($("#type").val()=="2"){
			    		if($("#atmtype").val()==""){
			    			alert("请选择设备类型！");
				    		return false;
			    		}
			    	}
			    	if($("#orgno").val()==""){
			    	    alert("机构号不为空！");
			    		return false;
			    	}
			    	if(!checkFormdata(name,"名称",100,true,false,false,false,false))
						return false;
			    	if(checkLength(name.value)>100){
	    		          alert("名称不能超过100个英文或50个汉字！");
		            	  return false;
	    		    }
			    	if(!checkFormdata(address,"地址",200,true,false,false,false,false))
						return false;
			    	if(checkLength(address.value)>200){
	    		          alert("名称不能超过200个英文或100个汉字！");
		            	  return false;
	    		    }
			    	
			    	if(!checkTel($("#mobilePhone").val())){
			    		alert("联系电话不正确");
			    		return false;
			    	}
			    	/*
			    	if(!checkFormdata(mobilePhone,"联系电话",20,false,true,true,true,true))
						return false;
			    	*/
			    	if($("#region  option:selected").text()=="--请选择区域--"){
			    		alert("请选择所在地区！");
			    		return false;
			    	}
			    	if($("#locationCity").val()==""){
			    		alert("请选择所在地区！");
			    		return false;
			    	}
			    	
			    	if(!checkLength($("#locationY").val())){
			    		alert("经度格式不正确");
			    		return false;
			    	}
			    	if(!checkLength($("#locationX").val())){
			    		alert("纬度格式不正确");
			    		return false;
			    	}
			    	/*if(!testLon($("#locationY").val())){
			    		alert("经度格式不正确");
			    		return false;
			    	}
			    	if(!testLat($("#locationX").val())){
			    		alert("纬度格式不正确");
			    		return false;
			    	}*/
			    	
					document.PageForm.action = url+"?step=2";
					document.PageForm.submit();
				}
			}
			
			$(document).ready(function(){
				//$("#atmtyperows").hide();
				$("#type").change(function(e){
					if($("#type").val()=="2"){
						$("#branchorgno").hide();
						$("#atmtyperows").show();
					}else{
						$("#atmtyperows").hide();
						$("#branchorgno").show();
						$("#atmtyperows option:first").attr("selected","selected");
					}
				});
			});
				
			function testLon(v) {
				//规范的经度表示法ddd°mm′ss.sss″
				var lonTest1 = /^((\d|[1-9]\d|1[0-7]\d)[°](\d|[0-5]\d)[′](\d|[0-5]\d)(\.\d{1,6})?[\″]$)|(180[°]0[′]0[\″]$)/;
				//有分没秒的表示法ddd°mm.mmm′
				var lonTest2 = /^((\d|[1-9]\d|1[0-7]\d)[°](\d|[0-5]\d)(\.\d{1,6})?[′]$)|(180[°]0[′]$)/;
				//只有度的表示法 ddd.ddd°
				var lonTest3 = /^((\d|[1-9]\d|1[0-7]\d)(\.\d{1,6})?[°]$)|(180[°]$)/;
				var s1 = v.split("°");
				var state;
				if (s1[1] != '' && s1[1] != null) {
					var s2 = s1[1].split("′");
					if (s2[1] != '' && s2[1] != null) {
						var s3 = s2[1].split("″");
						state = lonTest1.test(v);
					} else {
						state = lonTest2.test(v);
					}
				} else {
					state = lonTest3.test(v);
				}
				return state;
			}

			function testLat(v) {
				var latTest1 = /^((\d|[1-8]\d)[°](\d|[0-5]\d)[′](\d|[0-5]\d)(\.\d{1,6})?[\″]$)|(90[°]0[′]0[\″]$)/;
				var latTest2 = /^((\d|[1-8]\d)[°](\d|[0-5]\d)(\.\d{1,6})?[′]$)|(90[°]0[′]$)/;
				var latTest3 = /^((\d|[1-8]\d)(\.\d{1,6})?[°]$)|(90[°]$)/;
				var state;
				var s1 = v.split("°");
				if (s1[1] != '' && s1[1] != null) {
					var s2 = s1[1].split("′");
					if (s2[1] != '' && s2[1] != null) {
						var s3 = s2[1].split("″");
						state = latTest1.test(v);
					} else {
						state = latTest2.test(v);
					}
				} else {
					state = latTest3.test(v);
				}
				return state;
			}
			
			
//下拉框级联处理  start
var arr = new Array(); //定义数组
function changeRegion(){
	var cityId=$("#wxCity").val(); 
	$("#region").html();
	var regionStr = '<option>--请选择区域--</option>';
	for (var i = 0; i < arr.length; i++){
		var region = arr[i];
		if(region[0] == cityId){
			regionStr += "<option  value="+ region[2] +">" + region[1] + "</option>";
		}
	}
	$("#region").html(regionStr);
}
//加载市区信息
function loadPlace(){
	var districts = "${arrList}";
	var districtArr = new Array();
	districtArr = districts.split(",");
	for(var i = 0; i< districtArr.length; i++){
		var region = districtArr[i].split("|");
		arr[i] = region;
	}
}

$(function(){
	loadPlace();
});
</SCRIPT>
	</HEAD>
	<body>
		<div id="mainzone">
		<div id="body">
			<!-- 提交表单 -->
			<FORM name=PageForm  method=post>
				<!-- 系统功能路径 -->
				<div class="loc">
					<div class="groupmenu" id="groupmenu"></div>
					<!--  
					<a href="<%=path %>/login/mainFrame.do"></a> &raquo;
					-->
					<span>系统管理</span> &raquo;
					<span>网点维护</span> &raquo;
					<span class="last">网点信息列表</span>&raquo;
					<span class="last">新建网点信息</span>
				</div>

				<!-- 主体DIV -->
				<div class="tab_cntr">
					<!-- 
					<ul class="opt">
						<li>
							<a class="cur" href="javascript:void(0)">基本属性</a>
						</li>
					</ul>
					-->
					<!-- 内容填写表格 -->
					<table class="editorTb" style="" id="tbBase" name="editorTab">
						<tr>
							<td class="hd" colspan="2">
								编辑网点信息（<font color="red">* 为必填项</font>）
							</td>
						</tr>
						<tr>
							<td class="titl">
								<b>类型：</b>
							</td>
							<td>
								<c:forEach var="type" items="${typesysEnum.sysEnumItems}">
				        			<c:if test="${type.status=='1'}">
										<c:if test="${fn:trim(wxbranch.type) == fn:trim(type.fieldValue) }"><span>${type.displayValue }</span></c:if>
									</c:if>
									<c:if test="${type.status!='1'}">
										<c:if test="${fn:trim(wxbranch.type) == fn:trim(type.fieldValue) }"><span style="background-color: orange;">${type.displayValue }</span></c:if>
									</c:if>
								</c:forEach>
								<input id="type" name="wxbranch.type" value="${wxbranch.type }" type="hidden" /><input id="id" name="wxbranch.id" value="${wxbranch.id }" type="hidden" />
								<input id="id" name="wxbranch.createOperno" value="${wxbranch.createOperno }" type="hidden" /><input id="id" name="wxbranch.createTime" value="${wxbranch.createTime }" type="hidden" />
							</td>
						</tr>
						<c:if test="${'2'==wxbranch.type }">
							<tr id = "atmtyperows">
								<td class="titl">
									<b>设备类型：</b>
								</td>
								<td>
									<select id="atmtype" name="wxbranch.atmtype" class="sel">
						        		<c:forEach var="atmtype" items="${atmtypesysEnum.sysEnumItems}">
						        			<c:if test="${atmtype.status=='1'}">
												<option value="${atmtype.fieldValue }" <c:if test="${fn:trim(wxbranch.atmtype) == fn:trim(atmtype.fieldValue) }">selected</c:if> >${atmtype.displayValue }</option>
											</c:if>
											<c:if test="${atmtype.status!='1'}">
												<option value="${atmtype.fieldValue }" <c:if test="${fn:trim(wxbranch.atmtype) == fn:trim(atmtype.fieldValue) }">selected</c:if> style="background-color: orange;" >${atmtype.displayValue }</option>
											</c:if>
										</c:forEach>
									</select><font color="red"> *</font>
								</td>
							</tr>
						</c:if>
						<tr >
							<td class="titl">
								<b>名称：</b>
							</td>
							<td>
								<input id="name" name="wxbranch.name"  class="txt-300" value="${wxbranch.name }"/><font color="red"> *</font>
							</td>
						</tr>
						<tr >
							<td class="titl">
								<b>地址：</b>
							</td>
							<td>
								<textarea id="address" name="wxbranch.address" rows="5" cols="20" class="txt txt1" >${wxbranch.address }</textarea><font color="red"> *</font>
							</td>
						</tr>
						<tr >
							<td class="titl">
								<b>所在市区：</b>
							</td>
							<td>
								<SELECT name="wxbranch.locationCity" id="wxCity" onChange="changeRegion()">
									<OPTION value="${wxbranch.locationCity }">${wxbranch.cityName }</OPTION>
									<c:forEach var="wxCity" items="${wxCitys}">
										<c:if test="${wxbranch.locationCity != wxCity.id }">
											<option  value="${wxCity.id }" >${wxCity.cityName }</option>
										</c:if>
									</c:forEach>
								</SELECT>
								<input id="cityName" name="wxbranch.cityName" value="" type="hidden" />
								
								<SELECT name="wxbranch.regionId" id="region" style="width:120px;" >
										<OPTION>${wxbranch.regionName }</OPTION>
								</SELECT>
								<input id="regionName" name="wxbranch.regionName" value="" type="hidden" />
								<font color="red"> *</font>
							</td>
						</tr>
						<tr >
							<td class="titl">
								<b>联系电话：</b>
							</td>
							<td>
								<input id="mobilePhone" name="wxbranch.mobilePhone" value="${wxbranch.mobilePhone }"  class="txt-150" />
							</td>
						</tr>
						<c:if test="${'2'!=wxbranch.type }">
						<tr id = "branchorgno">
							<td class="titl">
								<b>机构号：</b>
							</td>
							<td>
								<input id="orgno" name="wxbranch.orgno" value="${wxbranch.orgno }" class="txt-150" maxlength="5" onkeyup="value=this.value.replace(/\D+/g,'')"/><font color="red"> *</font>
							</td>
						</tr>
						</c:if>
						<tr >
							<td class="titl">
								<b>地理位置(经度-纬度)：</b>
							</td>
							<td>
								<input id="locationY" name="wxbranch.locationY"  type="text"  class="txt-150" value="${wxbranch.locationY }" />&nbsp;&nbsp;-&nbsp;&nbsp;<input id="locationX" name="wxbranch.locationX"  value="${wxbranch.locationX }"  class="txt-150" type="text" /><font color="red"> *</font>
							</td>
						</tr>
						<tr >
							<td class="titl">
								<b>状态：</b>
							</td>
							<td>
								<select id="status" name="wxbranch.status" class="sel">
					        		<c:forEach var="status" items="${statussysEnum.sysEnumItems}">
					        			<c:if test="${status.status=='1'}">
											<option value="${status.fieldValue }" <c:if test="${1 == fn:trim(status.fieldValue) }">selected</c:if> >${status.displayValue }</option>
										</c:if>
										<c:if test="${status.status!='1'}">
											<option value="${status.fieldValue }" <c:if test="${1 == fn:trim(status.fieldValue) }">selected</c:if> style="background-color: orange;" >${status.displayValue }</option>
										</c:if>
									</c:forEach>
								</select><font color="red"> *</font>
							</td>
						</tr>
					</table>

					<!-- 提交按钮 -->
					<div style="padding: 10px; padding-left: 10px;">
						<div style="width: 250px; float: left;">
							<input name="button" type=button onclick="javascript:history.go(-1)"
								value="" class="return" />
						</div>
						<input type="button" name="btnSave" value=""
							onclick="submitForm('<%=path %>/wxBranch/modifyWXBranch.do','modify')"
							id="btnSave" class="save" />
						&nbsp;&nbsp;&nbsp;
						 <input name="Submit" type="reset" value="" class="reset" /> 
						<div class="clr"></div>
					</div>
				</div>
			</FORM>
		</div>
		</div>
	</BODY>
</HTML>