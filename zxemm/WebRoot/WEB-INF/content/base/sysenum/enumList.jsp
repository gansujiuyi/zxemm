<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/content/base/tag.inc" />
<%String path = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<HEAD>
		<TITLE>枚举信息列表</TITLE>
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
		    if(act == "add")
		    {
				document.PageForm.action = url;
				document.PageForm.submit();
		    }
		    
			//调用获取被选中记录的方法
			itemCheckbox_changed();
			
		    if(act == "delete"){
		        if(sltedItemCnt == 0){
			    	alert("请选择要删除的枚举信息！");
			    	return;
			    }
				if(! confirm("确定要删除吗(共"+sltedItemCnt+"项)?")){
					return;
				}
				//获取被选中的checkbox值
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
			    				
				document.PageForm.action = url+"?enumIds="+idstr;
				document.PageForm.submit();
		    }
		
		    if(act == "modify")
		    {
		        if(sltedItemCnt == 0){
			    	alert("请选择需要修改的枚举信息！");
			    	return;
				}
				if(sltedItemCnt > 1){
			  		alert("请选择唯一枚举信息！");
			 		return;
				}
				//获取被选中的checkbox值
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
			    				
				document.PageForm.action = url+"?enumId="+idstr;
				document.PageForm.submit();
		    }
		    //模糊查询
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
		
		function queryDetail(obj){
			var currTr = $(obj).parent().parent()[0];
			//如果关联枚举明细信息显示过，则仅做隐藏或显示枚举明细操作，不查询数据库
			if(currTr.hasShowed){$(currTr).next("tr").slideToggle(50);return;}
			var itr = currTr;
			$.ajax({
				type:'POST',
				url:"acegi/queryEnumItemByEnumId.do",
				dateType:"json",
				data:'enumId='+ itr.id,
				success: function(msg){
					showDetail(msg,itr);
				},
				error: function(msg){
				}
			});
		}

		//查询关联枚举明细信息
		$(document).ready(function(){
			//第一个<tr>标签行号为0，是偶数行
			$("#itemListTab tr:odd").addClass("odd");	//奇数行
			$("#itemListTab tr:not(.odd)").hide();	//偶数行隐藏
			$("#itemListTab tr:first-child").show();	//第一行显示
		});
		
		//显示关联枚举明细信息
		function showDetail(msg, trObj){
			//if(!trObj.hasShowed){
				//将查询到得枚举明细信息赋值到页面并显示
				var dlist = msg.sysEnumItems;
				if(dlist.length > 0){//有关联的枚举明细信息
					//var dtable = document.createElement("<table width=80%>");
					//兼容IE9需要将上段代码：var dtable = document.createElement("<table width=80%>");拆分成如下写法
					var dtable = document.createElement("table");
					dtable.setAttribute("width","80%")
					$(dtable).html("<tr><th noWrap>字段值</th><th noWrap>显示值</th><th noWrap>显示顺序</th><th noWrap>状态</th><th noWrap>操作</th></tr>");
					for(var x in dlist){
						var itr = dtable.insertRow();
						var itd1 = itr.insertCell();
						var itd2 = itr.insertCell();
						var itd3 = itr.insertCell();
						var itd4 = itr.insertCell();
						var itd5 = itr.insertCell();
						itd1.align = "left";itd2.align = "left";itd3.align = "left";itd4.align = "left";itd5.align = "left";
						itd1.innerHTML = dlist[x].fieldValue;	//字段值
						itd2.innerHTML = dlist[x].displayValue;	//显示值
						itd3.innerHTML = dlist[x].displayOrder;	//显示顺序
						itd4.innerHTML = (dlist[x].status=='1')?"<font color='green'>可用</font>":"<font color='red'>不可用</font>";	//状态
						itd5.innerHTML = "<a href='acegi/queryByEnumItemId.do?id="+dlist[x].id+"&enumId="+msg.enumId+"' style=\"color: blue;font: bold\">修改</a>&nbsp;|&nbsp;<a href='acegi/delEnumItem.do?id="+dlist[x].id+"' onclick='return confirm(\"确定删除吗？\");' style=\"color: blue;font: bold\">删除</a>";
					}
					$(trObj).next("tr").children()[0].innerHTML = "";
					$(trObj).next("tr").children()[0].appendChild(dtable);
				}
				trObj.hasShowed = "true";
				$(trObj).next("tr").slideToggle(50);
			//}
		}
		-->
		</SCRIPT>
	</head>
	<body>
	<div id=mainzone>
		<div id="body">
			<FORM id=PageForm name=PageForm method=post action="acegi/queryAllEnum.do">
				<div id="itemList">
					<div class="loc">
						<div class="groupmenu" id="groupmenu"></div>
						<!--  
						<a href="<%=path %>/login/mainFrame.do"></a> &raquo;
						-->
						<span>系统管理</span> &raquo;
						<span>系统参数管理</span> &raquo; 
						<a href="javascript:gotoListHome()">枚举信息维护</a> &raquo; 
						<span class="last">枚举信息列表</span>
					</div>


					<!-- 全/反选 -->
					<div class="chooseCase">
						<!-- 搜索查询 -->
						<div style="float: left" class="search">
							<table width="100%" border="0">
								<tr>
					                <td width="5%" >枚举名称：</td>
									<td >
	                                	<input id="enumName" name=enumName value="${enumName }" type="text" class="txt-150" />
										<a href="javascript:submitForm('acegi/queryAllEnum.do','search')">
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
									<th style="width: 50px" class="p">
										<input name="chkAll" type="checkbox"
											onclick="checkAll(this.checked);" value="checkbox" />
									</th>
									<th noWrap>
										枚举名称
									</th>
									<th noWrap>
										对应表名
									</th>
									<th noWrap>
										字段名称
									</th>
									<th noWrap>
										状态
									</th>
									<th noWrap>
										系统定义
									</th>
									<th noWrap>
										添加字段值
									</th>
									<th noWrap>
										显示枚举明细
									</th>
								</tr>
								<c:if test="${empty pageFinder.data }">
									<tr>
										<td colspan=8 align="center" height="25">
											<font color="red">没有查询到相关记录</font>
										</td>
									</tr>
								</c:if>
								<c:forEach var="sysEnum" items="${pageFinder.data}"
									varStatus="status">
									<tr id="${sysEnum.enumId }">
										<td>
											<input id="enumId_${status.index }" name="chkItem"
												value="${sysEnum.enumId }"
												type="checkbox" onclick="itemCheckbox_changed();" />
										</td>
										<td nowrap>
											${sysEnum.enumName }
										</td>
										<td nowrap>
											${sysEnum.tableName }
										</td>
										<td nowrap>
											${sysEnum.fieldName }
										</td>
										<td nowrap>
											<c:if test="${sysEnum.status==1 }"><font color="green">可用</font></c:if>
											<c:if test="${sysEnum.status==0 }"><font color="red">不可用</font></c:if>
										</td>
										<td nowrap>
											<c:if test="${sysEnum.systemFlag==1 }">是</c:if>
											<c:if test="${sysEnum.systemFlag==0 }">否</c:if>
										</td>
										<td nowrap>
											<a href="acegi/addEnumItem.do?enumId=${sysEnum.enumId }" style="color: blue">添加字段值</a>
										</td>
										<td nowrap>
											<span onclick="queryDetail(this)" style="cursor:pointer;color: blue">&gt;&gt;查看枚举明细</span>
										</td>
									</tr>
									<tr id="tr_${status.index}">
										<td colspan="8" align="center">
											<font color=red>无明细记录</font>
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
									<a href="javascript:submitForm('acegi/addEnum.do','add')"><img
											src="<%=path%>/images/template/b_new.png" alt="IMG" />新建</a>
								</li>
								<li>
									<a href="javascript:submitForm('acegi/queryByEnumId.do','modify')"><img
											src="<%=path%>/images/template/b_edit.png" alt="IMG" />编辑</a>
								</li>
								<li>
									<a id="lnkBtnDel"
										href="javascript:submitForm('acegi/delEnum.do','delete')"><img
											src="<%=path%>/images/template/b_del.png" alt="IMG" />删除</a>
								</li>
							</ul>
						</div>

						<!-- 分页 -->
						<div style="float: right" class="pageNavi" id="pagerBtm">
							<c:import url="../page.jsp?formId=PageForm"
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