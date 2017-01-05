<%@page import="com.jiuyi.jyplat.util.ConfigManager"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/content/base/tag.inc" />

<%
	String path = request.getContextPath();
	String base_path = ConfigManager.getString("base_path", "请在emm中设置");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=base_path%>">

		<title>枚举信息列表</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script type="text/javascript" src="<%=path%>/js/jquery-1.6.js"></script>
		<link href="<%=path%>/css/mainframe.css" rel="stylesheet" type="text/css" />
		<link href="<%=path%>/css/Style1.css" rel="stylesheet" type="text/css" />
		<link href="<%=path%>/css/Style2.css" rel="stylesheet" type="text/css" />

		<script>
		<!--
		//按钮提交事件
		function submitForm(url, act)
		{
		    if(act == "add")
		    {
			document.PageForm.action = url;
			document.PageForm.submit();
		    }
		
		    if(act == "delete"){
		        if(document.PageForm.hidpub.value == 0){
			    	alert("请选择要删除的枚举信息！");
			    	return;
			    }
				if(! confirm("确认删除吗？")){
					return;
				}
				//获取被选中的checkbox值
				var selectedValues = getSelectedValue();				
				document.PageForm.action = url+selectedValues;
				document.PageForm.submit();
		    }
		
		    if(act == "modify")
		    {
		        if(document.PageForm.hidpub.value == 0){
			    	alert("请选择需要修改的枚举信息！");
			    	return;
				}
				if(document.PageForm.hidpub.value > 1){
			  		alert("请选择唯一枚举信息！");
			 		return;
				}
				//获取被选中的checkbox值
				var selectedValues = getSelectedValue();
				document.PageForm.action = url+selectedValues[0];
				document.PageForm.submit();
		    }
		    //模糊查询
		    if(act == "search"){
		        document.PageForm.action = url;
		        document.PageForm.submit();
		    }
		}
		
		//获取被选中的checkbox的值
		
		function getSelectedValue(){
			var enumIds=document.getElementsByName("selectedEnumId");
			var selectedValues = new Array();
			var count = 0;
			for(i=0;i<enumIds.length;i++){
				if(enumIds[i].checked){
					selectedValues[count] = enumIds[i].value;
					count++;
				}
			}
			return selectedValues;
		}
		
		
		//check框被点击时
		function publicclick(){

		    if (! window.event.srcElement.checked) {
		        document.PageForm.hidpub.value  = parseInt(document.PageForm.hidpub.value) - 1;
		    }
		    else if(window.event.srcElement.checked) {
		        document.PageForm.hidpub.value  = parseInt(document.PageForm.hidpub.value) + 1;
		    }
		}
		
		//----------------------------------------------------------------------------------------
		//查询关联枚举明细信息
		$(document).ready(function(){
			$("#dataTable tr:odd").addClass("odd");
			$("#dataTable tr:not(.odd)").hide();
			$("#dataTable tr:first-child").show();
			
			$("#dataTable tr.odd").click(function(){
				//如果关联枚举明细信息显示过，则仅做隐藏或显示枚举明细操作
				if(this.hasShowed){$(this).next("tr").slideToggle(300);return;}
				var itr = this;
				$.ajax({
					type:'POST',
					url:"acegi/queryEnumItemByEnumId.do",
					dateType:"json",
					data:'enumId='+ this.enumId,
					success: function(msg){
						showDetail(msg,itr);
					},
					error: function(msg){
					}
				});
			});
		});
	
	//显示关联枚举明细信息
	function showDetail(msg, trObj){
		if(!trObj.hasShowed){
			var dlist = msg.sysEnumItems;
			if(dlist.length > 0){//有关联的枚举明细信息
				var dtable = document.createElement("<table class=list_table width=80%>");
				$(dtable).html("<tr class=list_table_tr_title><th>字段值</th><th>显示值</th><th>显示顺序</th><th>状态</th><th>操作</th></tr>");
				for(var x in dlist){
					var itr = dtable.insertRow();
					var itd1 = itr.insertCell();
					var itd2 = itr.insertCell();
					var itd3 = itr.insertCell();
					var itd4 = itr.insertCell();
					var itd5 = itr.insertCell();
					itd1.align = "center";itd2.align = "center";itd3.align = "center";itd4.align = "center";itd5.align = "center";
					itd1.innerText = dlist[x].fieldValue;	//字段值
					itd2.innerText = dlist[x].displayValue;	//显示值
					itd3.innerText = dlist[x].displayOrder;	//显示顺序
					itd4.innerText = (dlist[x].status=='1')?"可用":"不可用";	//状态
					itd5.innerHTML = "<a href='acegi/queryByEnumItemId.do?id="+dlist[x].id+"&enumId="+msg.enumId+"'>修改</a>|<a href='acegi/delEnumItem.do?id="+dlist[x].id+"'>删除</a>";
				}
				$(trObj).next("tr").children()[0].innerHTML = "";
				$(trObj).next("tr").children()[0].appendChild(dtable);
			}
			trObj.hasShowed = "true";
		}
		$(trObj).next("tr").slideToggle(300);
	}
		-->
		</script>
	</head>

	<body>
		<form id=PageForm name=PageForm method=post
			action="acegi/queryAllEnum.do">
			<input name="hidpub" type=hidden value=0 />
			<table class=query_table height=21 cellspacing=0 cellpadding=2
				width="98%" align=center border=0>
				<tbody>
					<font color="green">>>枚举信息列表</font>
					<tr height=8>
						<td width="5%">
							枚举名称：
						</td>
						<td >
							<input class=TextInput type="text" size="15" name="enumName"
								value="${enumName }" />
							(模糊查询)
							<a href="javascript:submitForm('acegi/queryAllEnum.do','search')">
								<img src="<%=request.getContextPath()%>/images/search-btn.jpg" style="vertical-align:middle; padding-left:10px;" />
							</a>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
		<table width="98%" align=center>
			<tr>
				<td>
					<hr>
				</td>
			</tr>
		</table>
		<table id="dataTable" class=list_table cellspacing=1 cellpadding=0
			width="98%" align=center border=0>
			<tbody>
				<tr class=list_table_tr_title align=middle>
					<td width="4%" height="25" colSpan=1 noWrap>
						<div align=left>
							选择
						</div>
					</td>
					<td width="12%" noWrap>
						枚举名
					</td>
					<td width="10%" noWrap>
						对应表名
					</td>
					<td width="18%" noWrap>
						字段名
					</td>
					<td width="15%" noWrap>
						状态
					</td>
					<td width="15%" noWrap>
						系统定义
					</td>
				</tr>
				<%
					boolean flag = true;
				%>
				<c:forEach items="${pageFinder.data }" var="sysEnum">
					<tr class=<%if(flag){%> list_table_tr_even <%}else{%>
						list_table_tr_odd <%}%> align=middle enumId="${sysEnum.enumId }">
						<td nowrap>
							<div align=left>
								<input type=checkbox value="${sysEnum.enumId }"
									name=selectedEnumId onClick="publicclick()" />
							</div>
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
							<c:if test="${sysEnum.status==1 }">可用</c:if>
							<c:if test="${sysEnum.status==0 }">不可用</c:if>
						</td>
						<td nowrap>
							<c:if test="${sysEnum.systemFlag==1 }">是</c:if>
							<c:if test="${sysEnum.systemFlag==0 }">否</c:if>
						</td>
					</tr>
					<!-- 每条枚举信息记录后面插入一行，若有对应的枚举详细信息则显示关联的枚举详细信息 -->
					<tr id="tr_${status.index}">
						<td colspan="6" align="center" width="80%">
							<font color=red>无关联枚举明细记录</font>
						</td>
					</tr>
					<%
						flag = !flag;
					%>
				</c:forEach>
			</tbody>
		</table>
		<table cellspacing=0 cellpadding=0 width="98%" align=center border=0>
			<tbody>
				<tr align=left>
					<td colspan=7>
						<input name="add" type=button class=BtnAction style="CURSOR: hand"
							value=添加 onclick="submitForm('acegi/addEnum.do','add')" />
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input name="modify" type=button class=BtnAction
							style="CURSOR: hand" value=修改
							onclick="submitForm('acegi/queryByEnumId.do?enumId=','modify')" />
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input name="delete" type=button class=BtnAction
							style="CURSOR: hand" value=删除
							onclick="submitForm('sysAction/delEnum.do?enumIds=','delete')" />
					</td>
				</tr>
				<TR>
					<TD>
						<DIV align=right>
							<!--上下翻页-->

							<c:import url="../page.jsp?formId=PageForm" charEncoding="utf-8"></c:import>

						</DIV>
					</TD>
				</TR>
			</tbody>
		</table>
	</body>
</html>