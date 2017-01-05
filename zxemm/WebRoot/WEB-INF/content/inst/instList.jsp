<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
		<link href="<%=path%>/css/Style1.css" rel="stylesheet" type="text/css" />
		<link href="<%=path%>/css/Style2.css" rel="stylesheet" type="text/css" />
		<SCRIPT language=JavaScript>
<!--
//按钮提交事件
function submitForm(url, act)
{
	itemCheckbox_changed();
    if(act == "add"){
		document.PageForm.action = url;
		document.PageForm.submit();
    }

    if(act == "delete"){
        if(sltedItemCnt == 0){
		    alert("没有选择组织机构信息");
		    return;
		}
		if(! confirm(confirm("确定要删除吗(共"+sltedItemCnt+"项)?"))){
		    return;
		}
		var ids  = document.PageForm.chkItem;
		var idstr = "";
		if(ids.id)
			idstr = ids.value;
		else
			for( i=0 ; i < ids.length; i++ ){
	    		if( ids[i].checked == true ){
	    			idstr += ids[i].value + ",";
	    		}
	    	}
	    if(idstr.length > 0 && idstr.indexOf(",")>0)
	    	idstr = idstr.substring(0,idstr.length-1);
		document.PageForm.action = url + "?instName=" + idstr;
		document.PageForm.submit();
    }

    if(act == "modify"){
        if(sltedItemCnt == 0){
		    alert("没有选择要进行修改的机构信息");
		    return;
		}
		if(sltedItemCnt > 1){
		    alert("请选择唯一机构信息");
		    return;
		}
		var ids  = document.PageForm.chkItem;
		var idstr = "";
    	if(ids.id)
			idstr = ids.value;
		else
			for( i=0 ; i < ids.length; i++ ){
	    		if( ids[i].checked == true ){
	    			idstr += ids[i].value + ",";
	    		}
	    	}
		if(idstr.length > 0 && idstr.indexOf(",")>0)
	    	idstr = idstr.substring(0,idstr.length-1);
		document.PageForm.action = url + "?inst.institutionId=" + idstr;;
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
}
<c:if test="${message == 'save'}">
	alert("保存组织机构成功！");
</c:if>
<c:if test="${message == 'update'}">
	alert("修改组织机构成功！");
</c:if>
<c:if test="${message == 'del'}">
	alert("删除组织机构成功！");
</c:if>
<c:if test="${!empty isAddOEditOper && isAddOEditOper=='true'}">
	window.parent.frames['addInstLeft'].location.reload();
</c:if>
-->
</SCRIPT>
	</HEAD>
	<body>
		<div id="mainzone" style="width:640px">
		<div id="body">
			<FORM id=PageForm name=PageForm method=post action=acegi/queryInst.do>
				<div id="itemList">
					<div class="loc">
						<div class="groupmenu" id="groupmenu"></div>
						<!--  
						<a href="<%=path %>/login/mainFrame.do"></a> &raquo;
						-->
						<span>系统管理</span> &raquo;
						<span>机构与用户管理</span> &raquo;
						<a href="javascript:gotoListHome()">组织机构维护</a> &raquo; 
						<span class="last">组织机构列表</span>
					</div>
					

					<!-- 全/反选 -->
					<div class="chooseCase">
						<!-- 搜索查询 -->
						<div style="float: left" class="search">
							<table width="100%" border="0">
								<tr>
					                <td width="5%" align="right">组织机构名称：</td>
									<td >
	                                	<input id="instName" name=instName value="${instName }" type="text" class="txt-150" /> 
										<a href="javascript:submitForm('acegi/queryInst.do','search')">
											<img src="<%=request.getContextPath()%>/images/search-btn.jpg" style="vertical-align:middle; padding-left:10px;" />
										</a>
									</td>
				              	</tr>
	                        </table>
						</div>
					</div>
					
					
					<!-- 数据列表 -->
					<div id="PrintContent" class="mainCon">
						<table class="mainTab" id="itemListTab" width="90%">
							<tr id="tabHeader">
								<th style="width: 1%" class="p">
									<input name="chkAll" type="checkbox"
										onclick="checkAll(this.checked);"
										value="checkbox" />
								</th>
								<th width="5%">
									组织机构代码
								</th>
								<th width="8%">
									组织机构名称
								</th>
								<th width="8%">
									上级机构名称
								</th>
								<th width="20%">
									描述
								</th>
							</tr>
							<c:if test="${empty pageFinder.data }">
								<tr><td colspan=5 align="center" height="25"><font color="red">没有查询到相关记录</font></td></tr>
							</c:if>
							<c:forEach var="inst" items="${pageFinder.data}" varStatus="status">
								<tr>
									<td>
										<input id="instId_${inst.institutionId }" name="chkItem" value="${inst.institutionId }" type="checkbox" onclick="itemCheckbox_changed();"/>
									</td>
									<td>
										${inst.institutionNo }
									</td>
									<td>
										${inst.institutionName }
									</td>
									<td>
										<c:if test="${inst.parentInstitutionId == ''}">
											一级机构
										</c:if>
										<c:if test="${inst.parentInstitutionId != ''}">
											<c:forEach var="inst1" items="${instList}">
												<c:if test="${inst1.institutionId == inst.parentInstitutionId}">
													${inst1.institutionName }
												</c:if>
											</c:forEach>
										</c:if>
									</td>
									<td>
										${inst.description }
									</td>
								</tr>
							</c:forEach>
						</table>
					</div>
					<div class="headPageNavi">
						<!-- 增删改 -->
						
						<!-- 分页 -->
						<div style="float:right" class="pageNavi" id="pagerBtm">
							<c:import url="../base/page.jsp?formId=PageForm" charEncoding="utf-8"></c:import>
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
