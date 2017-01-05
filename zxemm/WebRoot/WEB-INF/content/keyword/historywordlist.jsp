<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/content/base/tag.inc" />
<%String path = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>热词列表</title>
		<meta http-equiv=Content-Type content="text/html; charset=utf-8" />
		<meta http-equiv=Expires content=0 />
		<script type="text/javascript" src="<%=path%>/js/jquery-1.6.js"></script>
		<script type="text/javascript" src="<%=path%>/js/base.js"></script>
		<link href="<%=path%>/css/Style1.css" rel="stylesheet" type="text/css" />
		<link href="<%=path%>/css/Style2.css" rel="stylesheet" type="text/css" />
		<script language=JavaScript>
		$(document).ready(function() {
			var sltedItemCnt = 0;
			//奇偶行变色
			altRow(1, "itemListTab", "odd", "even");
			//鼠标划过行时变色
			hoverRow(1, "itemListTab", "over");
		});
		//按钮提交事件 
		function submitForm(url, act)
		{
			//调用获取被选中的网点Id的方法
			itemCheckbox_changed();
		
		    if(act == "delete"){
		        if(sltedItemCnt == 0){
				    alert("请选择热词！");
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
				document.PageForm.action = url + "?hostWordIds=" + idstr;
				document.PageForm.submit();
		    }
		
		   else  if(act == "modify"){
		        if(sltedItemCnt == 0){
				    alert("请选择要进行修改的热词信息！");
				    return;
				}
				if(sltedItemCnt > 1){
				    alert("请选择唯一的热词信息！");
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
				document.PageForm.action = url + "?historyword.id="+ idstr+"&flag=edit";
				document.PageForm.submit();
		    }
		    else if(act == "add")
		    {
		     	document.PageForm.action = url+"?flag=add";
		        document.PageForm.submit();
		    }
		    
		}
		
function changWords(url,wordId,status){
 var url=url+"?historyword.id="+wordId+"&historyword.WORDSTATE="+status;
  window.location.href=url;
}
</script>
	</head>
	<body>
	<div id=mainzone>
		<div id="body">
			<form id="PageForm" name="PageForm" method="post" action="historyWord/queryHistoryWord.do">
				<div id="itemList">
					<div class="loc">
						<div class="groupmenu" id="groupmenu"></div>
						<span>历史搜索词管理</span> &raquo;
						<a href="javascript:gotoListHome()">历史搜索词维护</a> &raquo; 
						<span class="last">历史搜索词列表</span>
					</div>
					<div class="chooseCase" style="background-color: white">
						<div style="float: left" class="search">
							<table width="100%" border="0">
								
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
										搜索词
									</th>
									<th>
										显示词
									</th>
									 <th>
										添加时间
									</th>																	
									 <th>
										是否允许搜索
									</th>																	
									 <th>
										操作
									</th>																	
								</tr>
								<c:if test="${empty pageFinder.data }">
									<tr><td colspan=8 align="center" height="25"><font color="red">没有查询到相关记录</font></td></tr>
								</c:if>
								<s:iterator value="pageFinder.data">
								 <tr>
								  <td  width="5%">
									<input id="Id_<s:property value='id'/>" name="chkItem"
										value="<s:property value='id'/>" type="checkbox"
										onclick="itemCheckbox_changed();" />
								   </td>
								   <td><s:property value="SERCHWORD"/></td>
								   <td><s:property value="SCREENWORD"/></td>
								   <td><s:property value="ADDTIME"/></td>
								   <td>
								    <s:if test="WORDSTATE==1">是</s:if>
								    <s:elseif test="WORDSTATE==2">否</s:elseif>
								    <s:else>否</s:else>
								   </td>
								   <td>
								    <s:if test="WORDSTATE==1">
								       <span onclick="changWords('historyWord/changeWordStatus.do',<s:property value="id"/>,<s:property value="WORDSTATE"/>)" style="cursor:pointer;color: blue">&gt;&gt;关闭搜索</span>
								    </s:if>
								    <s:elseif test="WORDSTATE==2">
								      <span onclick="changWords('historyWord/changeWordStatus.do',<s:property value="id"/>,<s:property value="WORDSTATE"/>)" style="cursor:pointer;color: blue">&gt;&gt;打开搜索</span>
								    </s:elseif>
								    <s:else>
								      <span onclick="changWords('historyWord/changeWordStatus.do',<s:property value="id"/>,<s:property value="WORDSTATE"/>)" style="cursor:pointer;color: blue">&gt;&gt;打开搜索</span>
								    </s:else>
								   </td>
								 </tr>
								</s:iterator>
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
										<a href="javascript:submitForm('historyWord/toAddHistoryWordPage.do','add')"><img
												src="<%=path%>/images/template/b_new.png" alt="IMG" />添加词汇</a>
									</li>
										<li>
										<a
											href="javascript:submitForm('historyWord/delHistoryWord.do','delete')"><img
												src="<%=path%>/images/template/b_del.png" alt="IMG" />删除词汇</a>
									</li>
									<li>
										<a href="javascript:submitForm('historyWord/toAddHistoryWordPage.do','modify')"><img
												src="<%=path%>/images/template/b_edit.png" alt="IMG" />编辑词汇</a>
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
			</form>
		</div>
	</div>
	</body>
</html>