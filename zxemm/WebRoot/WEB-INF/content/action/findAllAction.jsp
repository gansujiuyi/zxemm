<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/content/base/tag.inc" />
<%String path = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<HEAD>
		<TITLE>Action列表</TITLE>
		<META http-equiv=Content-Type content="text/html; charset=utf-8">
		<META http-equiv=Expires content=0>
		<script type="text/javascript" src="<%=path%>/js/jquery-1.6.js"></script>
		<script type="text/javascript" src="<%=path%>/js/base.js"></script>
		<link href="<%=path%>/css/Style1.css" rel="stylesheet" type="text/css" />
		<link href="<%=path%>/css/Style2.css" rel="stylesheet" type="text/css" />
		<SCRIPT language=JavaScript>
		<!--
		//按钮提交事件
		function submitForm(url, act)
		{
			//调用获取被选中的actionId的方法
			itemCheckbox_changed();
			
		    if(act == "add"){
				document.PageForm.action = url;
				document.PageForm.submit();
		    }
		
		    if(act == "delete"){
		        if(sltedItemCnt == 0){
				    alert("没有选择Action信息！");
				    return;
				}
				if(! confirm("确定要删除吗(共"+sltedItemCnt+"项)?")){
				    return;
				}
				var ids  = document.PageForm.chkItem;//获取页面checkbox元素
				var idstr = "";
				if(ids.id)
					idstr = ids.value;	//页面只有一条记录
				else
					for( i=0 ; i < ids.length; i++ ){
						//页面有多条记录（即多个checkbox元素），累加被选中的action编号（以逗号分隔）
			    		if( ids[i].checked == true ){
			    			idstr += ids[i].value + ",";
			    		}
			    	}
			    if(idstr.length > 0 && idstr.indexOf(",")>0)
			    	idstr = idstr.substring(0,idstr.length-1);
				document.PageForm.action = url + "?actionIds=" + idstr;
				document.PageForm.submit();
		    }
		
		    if(act == "modify"){
		        if(sltedItemCnt == 0){
				    alert("没有选择要进行修改的action信息！");
				    return;
				}
				if(sltedItemCnt > 1){
				    alert("请选择唯一action信息！");
				    return;
				}
				
		   		var ids  = document.PageForm.chkItem;
				var idstr = "";
		    	if(ids.id)
					idstr = ids.value;
				else
					for( i=0 ; i < ids.length; i++ ){
			    		if( ids[i].checked == true ){
			    			idstr = ids[i].value;
			    			break;
			    		}
			    	}
				document.PageForm.action = url + "?actionIds=" + idstr;
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
		
		-->
		</SCRIPT>
	</head>
	<body>
	<div id=mainzone>
		<div id="body">
			<FORM id=PageForm name=PageForm method=post action="sysAction/queryAllAction.do">
				<div id="itemList">
					<div class="loc">
						<div class="groupmenu" id="groupmenu"></div>
						<!-- 
						<a href="<%=path %>/login/mainFrame.do"></a> &raquo; 
						-->
						<span>系统管理</span> &raquo;
						<span>功能权限管理</span> &raquo;
						<a href="javascript:gotoListHome()">Action维护</a> &raquo; <span class="last">Action列表</span>
					</div>

					<!-- 全/反选 -->
					<div class="chooseCase">
						<!-- 搜索查询 -->
						<div style="float: left" class="search">
							<table width="100%" border="0">
								<tr>
					                <td width="5%"  align="right">action名称：</td>
									<td width="10%">
	                                	<input id="actionName" name=sysAction.actionName value="${sysAction.actionName }" type="text" class="txt-150"/> 
	                                </td>
									<td width="10%" align="right">action说明：</td>
									<td >
										<input id="actionName" name=sysAction.description value="${sysAction.description }" type="text" class="txt-150"/> 
										<a href="javascript:submitForm('sysAction/queryAllAction.do','search')">
											<img src="<%=request.getContextPath()%>/images/search-btn.jpg" style="vertical-align:middle; padding-left:10px;" />
										</a>
									</td>
				              	</tr>
	                        </table>
						</div>
					</div>
					
					
					<!-- 数据列表 -->
					<div id="PrintContent" class="mainCon">
						<table class="mainTab" id="itemListTab">
							<tr id="tabHeader">
								<th style="width: 10px" class="p">
									<input name="chkAll" type="checkbox"
										onclick="checkAll(this.checked);" value="checkbox" />
								</th>
								<th style="width: 10%">
									Action名称
								</th>
								<th style="width: 60%">
									ActionUrl
								</th>
								<th style="width: 22%">
									说明
								</th>
								<th style="width: 5%">
									状态
								</th>
							</tr>
							<c:if test="${empty pageFinder.data }">
								<tr><td colspan=5 align="center" height="25"><font color="red">没有查询到相关记录</font></td></tr>
							</c:if>
							<c:forEach var="sysAction" items="${pageFinder.data}"
								varStatus="status">
								<tr>
									<td style="width: 10px">
										<input id="actionId_${sysAction.actionId }" name="chkItem"
											value="${sysAction.actionId }" type="checkbox"
											onclick="itemCheckbox_changed();" />
									</td>
									<td style="width: 10%">
										${sysAction.actionName }
									</td>
									<td style="width: 60%">
										${sysAction.actionUrl }
									</td>
									<td style="width: 22%">
										${sysAction.description }
									</td>
									<td style="width: 5%">
										<c:if test="${sysAction.status==1 }"><font color="green">可用</font></c:if>
										<c:if test="${sysAction.status==0 }"><font color="red">不可用</font></c:if>
									</td>
								</tr>
							</c:forEach>
						</table>
					</div>
					<div class="headPageNavi">
						<!-- 增删改 -->
						<div style="float: left" class="tools">
							<ul>
								<li>
									<a href="javascript:gotoListHome()"><img
											src="<%=path%>/images/template/b_ref.gif" alt="IMG" />刷新</a>
								</li>
								<li>
									<a href="javascript:submitForm('sysAction/addAction.do','add')"><img
											src="<%=path%>/images/template/b_new.png" alt="IMG" />新建</a>
								</li>
								<li>
									<a href="javascript:submitForm('sysAction/queryActionById.do','modify')"><img
											src="<%=path%>/images/template/b_edit.png" alt="IMG" />编辑</a>
								</li>
								<li>
									<a id="lnkBtnDel"
										href="javascript:submitForm('sysAction/delAction.do','delete')"><img
											src="<%=path%>/images/template/b_del.png" alt="IMG" />删除</a>
								</li>
							</ul>
						</div>

						<!-- 分页 -->
						<div style="float: right" class="pageNavi" id="pagerBtm">
							<c:import url="../base/page.jsp?formId=PageForm"
								charEncoding="utf-8"></c:import>
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
