<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/content/base/tag.inc" />
<%String path = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>关键字列表</title>
		<meta http-equiv=Content-Type content="text/html; charset=utf-8" />
		<meta http-equiv=Expires content=0 />
		<script type="text/javascript" src="<%=path%>/js/jquery-1.6.js"></script>
		<script type="text/javascript" src="<%=path%>/js/base.js"></script>
		<link href="<%=path%>/css/Style1.css" rel="stylesheet" type="text/css" />
		<link href="<%=path%>/css/Style2.css" rel="stylesheet" type="text/css" />
		<script language=JavaScript>
		//按钮提交事件 
		function submitForm(url, act)
		{
			//调用获取被选中的网点Id的方法
			itemCheckbox_changed();
		
		    if(act == "delete"){
		        if(sltedItemCnt == 0){
				    alert("请选择关键字！");
				    return;
				}
				if(! confirm("确定要删除吗(共"+sltedItemCnt+"项)?")){
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
				document.PageForm.action = url + "?ids=" + idstr;
				document.PageForm.submit();
		    }
		
		   else  if(act == "modify"){
		        if(sltedItemCnt == 0){
				    alert("请选择要进行修改的关键字信息！");
				    return;
				}
				if(sltedItemCnt > 1){
				    alert("请选择唯一的关键字！");
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
				document.PageForm.action = url + "?id=" + idstr;
				document.PageForm.submit();
		    }
		
		    //重新查询
		   else if(act == "search")
		    {
		    	if(url.indexOf("?")>0)
		    	{
					url = url + "&forSearch=true";
				}
				else
				{
					url = url + "?forSearch=true";
				}
		        document.PageForm.action = url;
		        document.PageForm.submit();
		    }
		    else
		    {
		        window.location.href=url;
		    }
		}
		</script>
	</head>
	<body>
	<div id=mainzone>
		<div id="body">
			<form id="PageForm" name="PageForm" method="post" action="keyword/queryAllKeyWord.do">
				<div id="itemList">
					<div class="loc">
						<div class="groupmenu" id="groupmenu"></div>
						<span>关键字管理</span> &raquo;
						<a href="javascript:gotoListHome()">关键字维护</a> &raquo; 
						<span class="last">关键字列表</span>
					</div>
					<div class="chooseCase" style="background-color: white">
						<div style="float: left" class="search">
							<table width="100%" border="0">
								
							</table>
						</div>
					</div>
					<!-- 首次请求本页面仅显示查询门店的条件 -->
					<c:if test="${!empty pageFinder}">
						<!-- 全/反选 -->
						<div class="chooseCase">
							<!-- 搜索查询 -->
							<div style="float: left" class="search">
							<table width="100%" border="0">
								<tr>
									<td width="5%" >关键字key：<input id="key" name="keyword.key" value="${keyword.key }" type="text" class="txt" /></td>
									<td width="5%" >关键字value：<input id="value" name="keyword.value" value="${keyword.value }" type="text" class="txt" /></td>
									<td width="10%" >
										<a href="javascript:submitForm('keyword/queryAllKeyWord.do','search')"><img src="<%=request.getContextPath() %>/images/search-btn.jpg" style="vertical-align:middle; padding-left:10px;"/></a>
									</td>
								</tr>
							</table>
							</div>
						</div>
						<!-- 数据列表 -->
						<div id="PrintContent" class="mainCon">
							<table class="mainTab" id="itemListTab">
								<tr id="tabHeader">
									<th style="width: 50px" class="p">
										<input name="chkAll" type="checkbox"
											onclick="checkAll(this.checked);" value="checkbox" />
									</th>
									<th>
										关键字key
									</th>
									<th>
										关键字value
									</th>
									<th>
										描述
									</th>
									<th>
										创建时间
									</th>
								</tr>
								<c:if test="${empty pageFinder.data }">
									<tr><td colspan=8 align="center" height="25"><font color="red">没有查询到相关记录</font></td></tr>
								</c:if>
								<c:forEach var="k" items="${pageFinder.data}"
									varStatus="status">
									<tr>
										<td>
											<input id="keyId_${k[0] }" name="chkItem"
												value="${k[0] }" type="checkbox"
												onclick="itemCheckbox_changed();" />
										</td>
										<td>
											${k[1] }
										</td>
										<td>
											${k[2] }
										</td>
										<td>
											${k[4] }
										</td>
										<td>
											${k[3] }
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
										<a href="javascript:submitForm('keyword/saveKeyWord.do','add')"><img
												src="<%=path%>/images/template/b_new.png" alt="IMG" />新建</a>
									</li>
									<li>
										<a href="javascript:submitForm('keyword/queryKeyWordById.do','modify')"><img
												src="<%=path%>/images/template/b_edit.png" alt="IMG" />编辑</a>
									</li>
									<li>
										<a
											href="javascript:submitForm('business/delKeyWord.do','delete')"><img
												src="<%=path%>/images/template/b_del.png" alt="IMG" />删除</a>
									</li>
									<li>
										<a
											href="javascript:submitForm('business/reloadRAM.do','reload')"><img
												src="<%=path%>/images/template/b_del.png" alt="IMG" />更新内存</a>
									</li>
								</ul>
							</div>
							<!-- 分页 -->
							<div style="float: right" class="pageNavi" id="pagerBtm">
								<c:import url="../base/page.jsp?formId=PageForm"
									charEncoding="utf-8"></c:import>
							</div>
						</div>
					</c:if>
				</div>
			</form>
		</div>
	</div>
	</body>
	<!-- 首次请求本页面仅显示查询门店的条件 -->
	<c:if test="${!empty pageFinder}">
		<script type="text/javascript"> 
		//已选中项计总数
		var sltedItemCnt = 0;
		//奇偶行变色
		altRow(1, "itemListTab", "odd", "even");
		//鼠标划过行时变色
		hoverRow(1, "itemListTab", "over");
		</script>
	</c:if>
</html>