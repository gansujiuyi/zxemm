<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/content/base/tag.inc" />
<%String path = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<HEAD>
		<TITLE></TITLE>
		<META http-equiv=Content-Type content="text/html; charset=utf-8">
		<META http-equiv=Expires content=0>
		<script type="text/javascript" src="<%=path%>/js/jquery-1.6.js">
		</script>
		<script type="text/javascript" src="<%=path%>/js/base.js">
		</script>
		<script type="text/javascript" src="<%=path%>/js/formValidate.js">
		</script>
		<script language="javascript" type="text/javascript" src="<%=path %>/js/timerControl/WdatePicker.js"></script>
		<link href="<%=path%>/css/Style1.css" rel="stylesheet" type="text/css" />
		<link href="<%=path%>/css/Style2.css" rel="stylesheet" type="text/css" />
		<SCRIPT language=JavaScript>

//按钮提交事件
function submitForm(url, act)
{
	itemCheckbox_changed();
    if(act == "stop"||act == "restart"){
        if(sltedItemCnt == 0){
		    alert("没有选择要修改状态的网点信息！");
		    return;
		}
		if(! confirm("确定要修改网点信息状态吗(共"+sltedItemCnt+"项)?")){
		    return;
		}
		
		var ids  = document.PageForm.chkItem;
		var idstr = "";
		var keywordsid = "";
		if(ids.id){
			idstr = ids.value.split("_")[0];
		}else{
			for( i=0 ; i < ids.length; i++ ){
	    		if( ids[i].checked == true ){
	    			idstr += ids[i].value.split("_")[0] + ",";
	    		}
	    	}
		}
	    if(idstr.length > 0 && idstr.indexOf(",")>0)
	    	idstr = idstr.substring(0,idstr.length-1);
	    
	 //   url = url + "?wxbranchids=" + idstr;
		if(act == "stop"){
			document.PageForm.action = url  + "?status=0&wxbranchids=" + idstr;
		}if(act == "restart"){
			document.PageForm.action = url + "?status=1&wxbranchids=" + idstr;
		}
		document.PageForm.submit();
    }
    
    if(act=="del"){
    	 var del_str = "";
    	
        if(sltedItemCnt == 0){
		    alert("没有选择要删除的网点信息！");
		    return;
		}
       	if(! confirm("确定要删除网点信息吗(共"+sltedItemCnt+"项)?")){
		    return;
		}
		var ids  = document.PageForm.chkItem;
		var idstr = "";
		if(ids.id){
			idstr = ids.value.split("_")[0];
			if(ids.value.split("_")[1]=="1"){
				del_str+=ids.value.split("_")[2]+",";
// 				if(! confirm("您确定要删除["+ids.value.split("_")[2]+"]可用的网点信息吗？")){
// 				    return;
// 				}
			}
		}else{
			for(var i=0 ; i < ids.length; i++ ){
	    		if( ids[i].checked == true ){
	    			idstr += ids[i].value.split("_")[0] + ",";
	    			if(ids[i].value.split("_")[1]=="1"){
	    				del_str+=ids[i].value.split("_")[2]+",";
// 	    				if(! confirm("您确定要删除["+ids[i].value.split("_")[2]+"]可用的网点信息吗？")){
// 	    				    return;
// 	    				}
	    			}
	    		}
	    		
	    	}
		}
		if (del_str.length > 0) {
			del_str = del_str.substr(0, del_str.length - 1);
	    }
		if(! confirm("您确定要删除["+del_str+"]可用的网点信息吗？")){
			    return;
		}
	    if(idstr.length > 0 && idstr.indexOf(",")>0)
	    	idstr = idstr.substring(0,idstr.length-1);
		document.PageForm.action = url + "?wxbranchids=" + idstr;
		document.PageForm.submit();
    }
    //修改
    if(act == "modify"){
    	if(sltedItemCnt == 0){
		    alert("没有选择您要编辑的网点信息！");
		    return;
		}else if(sltedItemCnt > 1){
		    alert("请选择您要编辑的唯一网点信息！");
		    return;
		}
    	
    	var ids  = document.PageForm.chkItem;
		var idstr = "";
		if(ids.id){
			idstr = ids.value.split("_")[0];
		}else{
			for( i=0 ; i < ids.length; i++ ){
	    		if( ids[i].checked == true ){
	    			idstr += ids[i].value.split("_")[0] + ",";
	    		}
	    	}
		}
	    if(idstr.length > 0 && idstr.indexOf(",")>0)
	    	idstr = idstr.substring(0,idstr.length-1);

        document.PageForm.action = url+"?step=0&wxbranch.id="+idstr;
        document.PageForm.submit();
    }
    //重新查询
    if(act == "search"){
    	if(url.indexOf("?")>0){
			url = url + "&forSearch=true";
		}else{
			url = url + "?forSearch=true";
		}

        document.PageForm.action = url;
        document.PageForm.submit();
    }
    if(act == "add"){
        document.PageForm.action = url+"?step=0";
        document.PageForm.submit();
    }
}
<%--
//下拉框级联处理  start
var arr = new Array(); //定义数组
function changeRegion(){
	var cityId=$("#wxCity").val(); 
	$("#region").html();
	var regionStr = '<option>--请选择区域--</option>';
	for (var i = 0; i < arr.length; i++){
		var region = arr[i];
		if(region[0] == cityId){
			regionStr += "<option value="+ region[2] +">" + region[1] + "</option>";
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
 --%>
</SCRIPT>
	</HEAD>
	<body>
	<div id="mainzone">
		<div id="body">
			<FORM id="PageForm" name=PageForm method=post action="<%=path%>/wxBranch/queryAllWXBranchList.do">
				<div id="itemList">
					<div class="loc">
						<div class="groupmenu" id="groupmenu"></div>
						<!-- 
						<a href="<%=path %>/login/mainFrame.do"></a> &raquo; 
						-->
						<span>系统管理</span> &raquo;
						<span>网点维护</span> &raquo;
						<span class="last">网点信息列表</span>
					</div>
					<!-- 全/反选 -->
					<div class="chooseCase">
						<!-- 搜索查询 -->
							<table width="100%" >
						       <tr>
						        <td align="right" width="10%">网点名称：</td>
						        <td width="10%"><input id="name" name="wxbranch.name" value="${wxbranch.name }" type="text" class="txt-150" /></td>
								<td align="right" width="10%">所在市/区：</td>
						        <td width="10%">
						        	<input id="cityName" name="wxbranch.cityName" value="${wxbranch.cityName }" type="text" class="txt-150" />
						        	<!-- 
						        	<SELECT name="wxbranch.locationCity" id="wxCity" onChange="changeRegion()">
										<OPTION>--请选择城市--</OPTION>
										<c:forEach var="wxCity" items="${wxCitys}">
											<option value="${wxCity.id }">${wxCity.cityName }</option>
										</c:forEach>
									</SELECT> 
									-->
						        </td>
						        <td>
						        	<input id="region" name="wxbranch.regionName" value="${wxbranch.regionName }" type="text" class="txt-150" />
						        <!--
						       	 	<SELECT name="wxbranch.regionId" id="region" style="width:120px;" >
										<OPTION>--请选择区域--</OPTION>
									</SELECT>
								-->
						        </td>
								<td align="right" width="10%">类型：</td>
								<td >
									<select id="type" name="wxbranch.type" class="sel">
							        	<option value="">全部</option>
						        		<c:forEach var="type" items="${typesysEnum.sysEnumItems}">
						        			<c:if test="${type.status=='1'}">
												<option value="${type.fieldValue }" <c:if test="${fn:trim(wxbranch.type) == fn:trim(type.fieldValue) }">selected</c:if> >${type.displayValue }</option>
											</c:if>
										</c:forEach>
									</select>
								</td>
							   </tr>
							   <tr>
							   	<td align="right" width="10%">网点地址：</td>
								<td width="10%"><input id="address" name="wxbranch.address" value="${wxbranch.address }" type="text" class="txt-150" /></td>
						        <td align="right" width="10%">状态：</td>
						        <td width="10%">
						        	<select id="status" name="wxbranch.status" class="sel">
							        	<option value="">全部</option>
						        		<c:forEach var="status" items="${statussysEnum.sysEnumItems}">
						        			<c:if test="${status.status=='1'}">
												<option value="${status.fieldValue }" <c:if test="${fn:trim(wxbranch.status) == fn:trim(status.fieldValue) }">selected</c:if> >${status.displayValue }</option>
											</c:if>
										</c:forEach>
									</select>
						        </td>
						        <td></td>
								<td align="right" width="10%">排序：</td>
						        <td >
						        <select id="orderby" name="orderby" class="sel">
									<option value="" <c:if test="${orderby=='' }">selected="selected"</c:if>>顺序号</option>
									<option value="0" <c:if test="${orderby=='0' }">selected="selected"</c:if>>创建时间</option>
									<option value="1" <c:if test="${orderby=='1' }">selected="selected"</c:if>>修改时间</option>
								</select>
									<a href="javascript:submitForm('<%=path%>/wxBranch/queryAllWXBranchList.do','search')" >
								    <img src="<%=request.getContextPath()%>/images/search-btn.jpg" style="vertical-align:middle; padding-left:10px;" />
								    </a>
								</td>
						      </tr>
						    </table>
					</div>
					<!-- 数据列表 -->
					<div id="PrintContent" class="mainCon">
						<table class="mainTab" id="itemListTab">
							<tr id="tabHeader">
								<th style="width: 50px" class="p">
									<input name="chkAll" type="checkbox"
										onclick="checkAll(this.checked);"
										value="checkbox" />
								</th>
								<th>
									网点名称
								</th>
								<th>
									网点地址
								</th>
								<th>
									市
								</th>
								<th>
									区
								</th>
								<th>
									联系电话
								</th>
								<th>
									类型
								</th>
								<!-- 
								<th>
									设备类型
								</th>
								 -->
								<th>
									状态
								</th>
								<th>
									创建时间
								</th>
								<!--  
								<th>
									修改时间
								</th>
								-->
								<th>
								            经度
								</th>
								<th>
								            纬度
								 </th>
								 <th>
								          机构号
								 </th>
							</tr>
							<c:if test="${empty pageFinder.data }">
								<tr><td colspan="10" align="center" height="25"><font color="red">没有查询到相关记录</font></td></tr>
							</c:if>
							<c:forEach var="wx_branch" items="${pageFinder.data}" varStatus="status">
								<tr>
									<td>
										<input id="wxId_${wx_branch.id }" name="chkItem" value="${wx_branch.id }_${wx_branch.status}_${wx_branch.name }" type="checkbox" onclick="itemCheckbox_changed();"/>
									</td>
									<td>
										${wx_branch.name }
									</td>
									<td>
										${wx_branch.address }
									</td>
									<td>
										${wx_branch.cityName }
									</td>
									<td>
										${wx_branch.regionName }
									</td>
									<td>
										${wx_branch.mobilePhone }
									</td>
									<td>
										<c:forEach var="type" items="${typesysEnum.sysEnumItems}">
											<c:if test="${fn:trim(wx_branch.type) == fn:trim(type.fieldValue) }">${type.displayValue }</c:if>
										</c:forEach>
									</td>
									<td>
										<c:forEach var="status" items="${statussysEnum.sysEnumItems}">
											<c:if test="${fn:trim(wx_branch.status) == fn:trim(status.fieldValue) }">${status.displayValue }</c:if>
										</c:forEach>
									</td>
									<td>
										${wx_branch.createTime }
									</td>
									<!--  
									<td>
										${wx_branch.updateTime }
									</td>
									-->
									<td>
									     ${wx_branch.locationX } <!-- 添加经度 -->
									</td>
									<td>
									     ${wx_branch.locationY }<!--  添加纬度 -->
									</td>
									<td>
									     ${wx_branch.orgno }
									</td>
								</tr>
							</c:forEach>
						</table>
					</div>
					<div class="headPageNavi">
						<!-- 增删改 -->
						<div  style="float:left" class="tools">
						    <ul>
						        <li><a href="javascript:gotoListHome()"><img src="<%=path %>/images/template/b_ref.gif" alt="IMG" />刷新</a></li>
						        <li><a href="javascript:submitForm('<%=path %>/wxBranch/addWXBranch.do','add')"><img src="<%=path %>/images/template/b_new.png" alt="IMG" />新增</a></li>
						        <li><a href="javascript:submitForm('<%=path %>/wxBranch/modifyWXBranch.do','modify')"><img src="<%=path %>/images/template/b_edit.png" alt="IMG" />编辑</a></li>
						        <li><a href="javascript:submitForm('<%=path %>/wxBranch/delWXBranch.do','del')"><img src="<%=path %>/images/template/b_del.png" alt="IMG" />删除</a></li>
						        <li><a href="javascript:submitForm('<%=path %>/wxBranch/updateWXBranch.do','restart')"><img src="<%=path %>/images/template/b_enable.png" alt="IMG" />启用</a></li>
						        <li><a href="javascript:submitForm('<%=path %>/wxBranch/updateWXBranch.do','stop')"><img src="<%=path %>/images/template/b_disable.png" alt="IMG" />停用</a></li>
						    </ul>
						</div>
						<!-- 分页 -->
						<div style="float:right" class="pageNavi" id="pagerBtm">
							<c:import url="../../base/page.jsp?formId=PageForm" charEncoding="utf-8"></c:import>
						</div>
					</div>
					
				</div>
			</FORM>
		</div>
		</div>
	</BODY>

	<script type="text/javascript"> 
	//已选中项计总数
	var sltedItemCnt = 0;
	//奇偶行变色
	altRow(1, "itemListTab", "odd", "even");
	//鼠标划过行时变色
	hoverRow(1, "itemListTab", "over");
	//鼠标划过表头时变色
	//hoverTabHeader("itemListTab", "tbTitOver");
	//添加底部分页导航
	//$("#pagerTop").html($("#pagerBtm").html());
	//添加单击行事件
	//initRowClick(0);
	//初始化表头排序功能和显示当前排序字段
	//setOrderSign("[InputTime] DESC");
	</script>

</HTML>
