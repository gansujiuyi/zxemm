<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/content/base/tag.inc" />
<%String path = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<HEAD>
		<TITLE>系统内容列表</TITLE>
		<META http-equiv=Content-Type content="text/html; charset=utf-8">
		<META http-equiv=Expires content=0>
		<script type="text/javascript" src="<%=path%>/js/jquery-1.6.js"></script>
		<script type="text/javascript" src="<%=path%>/js/base.js"></script>
		<link href="<%=path%>/css/Style1.css" rel="stylesheet" type="text/css" />
		<link href="<%=path%>/css/Style2.css" rel="stylesheet" type="text/css" />
		<script language="javascript" type="text/javascript" src="<%=path %>/js/timerControl/WdatePicker.js"></script>
		<SCRIPT language=JavaScript>
		<!--
		//按钮提交事件
		function submitForm(url, act)
		{
			//调用获取被选中的供应商编号的方法
			itemCheckbox_changed();
		    if(act == "add"){
				document.PageForm.action = url;
				document.PageForm.submit();
		    }
		//启用系统内容
		    if(act == "start"){
		        if(sltedItemCnt == 0){
				    alert("没有选择内容信息！");
				    return;
				}
				if(! confirm("确定要启用吗(共"+sltedItemCnt+"项)?")){
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
				document.PageForm.action = url + "?contid=" + idstr+"&status=0004";
				document.PageForm.submit();
		    }
		//停用系统内容
		    if(act == "stop"){
		        if(sltedItemCnt == 0){
				    alert("没有选择内容信息！");
				    return;
				}
				if(! confirm("确定要停用吗(共"+sltedItemCnt+"项)?")){
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
				document.PageForm.action = url + "?contid=" + idstr+"&status=0005";
				document.PageForm.submit();
		    }
		    
		    if(act == "modify"){
		        if(sltedItemCnt == 0){
				    alert("请选择需要修改的内容！");
				    return;
				}
				if(sltedItemCnt > 1){
				    alert("只能选择一项进行编辑！");
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
				document.PageForm.action = url + "?contid=" + idstr;
				document.PageForm.submit();
		    }
		    
		    if(act == "delSyscont"){
		        if(sltedItemCnt == 0){
				    alert("请选择需要删除的内容！");
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
				document.PageForm.action = url + "?contid=" + idstr;
				if(! confirm("确定要删除吗")){
		           return;
		           }
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
		
		</SCRIPT>
	</head>
	<body>
	<div id=mainzone>
		<div id="body">
			<FORM id=PageForm name=PageForm method=post action="sys/querySyscont.do">
				<div id="itemList">
					<div class="loc">
						<div class="groupmenu" id="groupmenu"></div>
						<!--  
						<a href="<%=path %>/login/mainFrame.do"></a> &raquo;
						-->
						<span>系统管理</span> &raquo;
						<span>系统内容管理</span> &raquo;
						<a href="javascript:gotoListHome()">系统内容维护</a> &raquo; 
						<span class="last">系统内容列表</span>
					</div>


					<!-- 全/反选 -->
					<div class="chooseCase">
						<!-- 搜索查询 -->
						<div style="float: left" class="search">
							<table width="100%" border="0">
								<tr>
<!-- 									<td width="5%" align="right"> -->
<!-- 				                		内容类型： -->
<!-- 					                </td> -->
<!-- 					                <td> -->
<!-- 								<select id="conttype" name="syscont.conttype" class="sel-156"> -->
<!-- 									<option value='' selected="selected">请选择</option> -->
<!-- 									<option value='0005' selected="selected">ssss</option> -->
<!-- 							</td> -->
<!-- 									 <td width="10%" align="left"> -->
<!-- 					                	审核状态： -->
<!-- 					                </td> -->
<!-- 									<td width="10%"> -->
<!-- 									<select id="status" name="syscont.status" class="sel-156"> -->
<!-- 									<option value='' selected="selected">请选择</option> -->
<!-- 									<option value='0005' selected="selected">dsds</option> -->
<!-- 									</td> -->
									<td width="5%" align="right">
					                	内容名称：
					                </td>
									<td width="10%">
										<input id="contname" name="syscont.contname" value="${syscont.contname }"
											type="text" class="sel-156" />
									</td>
									<%-- <td width="10%" align="right">
										内容起止时间：
									</td>
									<td >
										<INPUT id="startTime" style="width: 150px;"  name="syscont.starttime" value="${syscont.starttime}" class=Wdate onClick="WdatePicker({dateFmt:'yyyyMMdd HH:mm:ss'})" readonly="readonly" />
										--
										<INPUT id="endTime" style="width: 150px;"  name="syscont.endtime" value="${syscont.endtime}" class=Wdate onClick="WdatePicker({dateFmt:'yyyyMMdd HH:mm:ss'})" readonly="readonly" />
										
									</td> --%>
									 
								</tr>
				             	
							 	<tr>
<!-- 							 	<td width="5%" align="right"> -->
<!-- 					                	展示渠道： -->
<!-- 					                </td> -->
<!-- 									<td> -->
<!-- 								<select id="onchannel" name="syscont.onchannel" class="sel-156"> -->
<!-- 									<option value='' selected="selected">请选择</option> -->
<!-- 									<c:forEach var="enum" items="${onChannel.sysEnumItems}"> -->
<!-- 										<c:if test="${enum.status=='1'}"> -->
<!-- 											<option value="${enum.fieldValue }" -->
<!-- 											<c:if test="${fn:trim(enum.fieldValue) == fn:trim(syscont.onchannel) }">selected</c:if> -->
<!-- 											>${enum.displayValue }</option> -->
<!-- 										</c:if> -->
<!-- 									</c:forEach> -->
<!-- 							</td> -->
					               
									
									
											
									
<!-- 									<td width="10%" align="right"> -->
<!-- 										内容描述： -->
<!-- 									</td> -->
<!-- 									<td > -->
<!-- 										<input id="description" name="syscont.description" value="${syscont.description }" type="text" class="txt" /> -->
                                     <td >

									
									</td>
									<td colspan="5">
										<a href="javascript:submitForm('sys/querySyscont.do','search')">
											<img src="<%=request.getContextPath() %>/images/search-btn.jpg" style="vertical-align:middle; padding-left:10px;" />
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
								<th>
									内容编号
								</th>
								<th>
									顺序
								</th>
								<th>
									内容名称
								</th>
								<th>
									内容描述
								</th>
								<th>
									开始时间
								</th>
								<th>
									结束时间
								</th>
								<th>
									内容类型
								</th>
								<th>
									内容状态
								</th>
								<th>
									创建人
								</th>
								<th>
									创建时间
								</th>
								
							</tr>
							<c:if test="${empty pageFinder.data }">
								<tr><td colspan=12 align="center" height="25"><font color="red">没有查询到相关记录</font></td></tr>
							</c:if>
							<c:forEach var="syscont" items="${pageFinder.data}"
								varStatus="status">
								<tr>
									<td>
										<input id="contid_${syscont.contid }" name="chkItem"
											value="${syscont.contid }" type="checkbox"
											onclick="itemCheckbox_changed();" />
									</td>
									<td>
										${syscont.contid }
									</td>
									<td>
										${syscont.sequence }
									</td>
									<td>
										<c:if test="${! empty syscont.contimg}">
												${syscont.contname }
										</c:if>
										<c:if test="${empty syscont.contimg}">
											${syscont.contname }
										</c:if>
									</td>
									<td>
										<c:if test="${! empty syscont.description}">
											<c:if test="${fn:length(syscont.description)>20}">
											   <a> ${fn:substring(syscont.description, 0, 20)}......</a>
											</c:if>
											<c:if test="${fn:length(syscont.description)<=20}">
											     ${syscont.description}
											</c:if>
										</c:if>
									</td>
									<%-- <td>
										<a onclick="showHtmlDetail('${syscont.contid }')">点击查看详细内容</a>
										<input type='hidden' value='${syscont.htmldetail}' id='html_${syscont.contid }'>
										</input>
									</td> --%>
									
									<td>
										${syscont.starttime }
									</td>
									<td>
										${syscont.endtime }
									</td>
									
									<td>
									    <c:if test="${fn:trim(syscont.conttype)  eq '1001' }">首页轮播广告</c:if>
									    <c:if test="${fn:trim(syscont.conttype)  eq '1002' }">首页中间广告</c:if>
										<c:if test="${fn:trim(syscont.conttype)  eq '1003' }">首页底部广告</c:if>
										<c:if test="${fn:trim(syscont.conttype)  eq '1004' }">首页右侧广告1</c:if>
									    <c:if test="${fn:trim(syscont.conttype)  eq '1005' }">首页右侧广告2</c:if>
										<c:if test="${fn:trim(syscont.conttype)  eq '2001' }">新房列表页广告1</c:if>
										<c:if test="${fn:trim(syscont.conttype)  eq '2002' }">新房列表页广告2</c:if>
									    <c:if test="${fn:trim(syscont.conttype)  eq '3001' }">新房详情页广告1</c:if>
										<c:if test="${fn:trim(syscont.conttype)  eq '3002' }">新房详情页广告2</c:if>
										<c:if test="${fn:trim(syscont.conttype)  eq '4001' }">二手房列表页广告1</c:if>
										<c:if test="${fn:trim(syscont.conttype)  eq '4002' }">二手房列表页广告2</c:if>
										<c:if test="${fn:trim(syscont.conttype)  eq '5001' }">金融超市轮播图</c:if>
									</td>
									
									<td>
										<c:if test="${syscont.status == '0003' }">待审核</c:if>
										<c:if test="${syscont.status == '0004' }">审核通过</c:if>
										<c:if test="${syscont.status == '0005' }">审核失败</c:if>
										<c:if test="${syscont.status == '0006' }">失效</c:if>
									</td>
									<td>
										${syscont.createby }
									</td>
									<td>
										${syscont.createtime }
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
									<a href="javascript:submitForm('sys/addSyscont.do','add')"><img
											src="<%=path%>/images/template/b_new.png" alt="IMG" />新建</a>
								</li>
								<li>
									<a href="javascript:submitForm('sys/updateSyscont.do','modify')"><img
											src="<%=path%>/images/template/b_edit.png" alt="IMG" />编辑</a>
								</li>
								<li>
									<a href="javascript:submitForm('sys/delSyscont.do','delSyscont')"><img
											src="<%=path%>/images/template/b_del.png" alt="IMG" />删除</a>
								</li>
								<%-- <li>
									<a id="lnkBtnDel"
										href="javascript:submitForm('sys/authSyscont.do','start')"><img
											src="<%=path%>/images/template/b_enable.png" alt="IMG" />启用</a>
								</li>
								<li>
									<a id="lnkBtnDel"
										href="javascript:submitForm('sys/authSyscont.do','stop')"><img
											src="<%=path%>/images/template/b_del.png" alt="IMG" />停用</a>
								</li> --%>
							</ul>
						</div>

						<!-- 分页 -->
						<div style="float: right" class="pageNavi" id="pagerBtm">
							<c:import url="../../base/page.jsp?formId=PageForm"
								charEncoding="utf-8"></c:import>
						</div>
					</div>
				</div>
			</FORM>
		</div>
	</div>
	</body>

<script type="text/javascript"> 
	function showHtmlDetail(html_id){
		var content =$("#html_"+html_id).val();
		if(null==content||""==content){
			alert("没有系统内容");
		}else{
			var htmlText = "<div class='mt-shopping'>";
			htmlText+=" 	<div class='TB_Add'></div>";
			htmlText+=" 		<div class='Add1' style='display: none; '>";
			htmlText+=" 			<h3><a href='javascript:void(0);' onclick='cannelDel()' style='float:right' class='close tc'><button class='close'><h3 style='color:red'>关闭</h3></button></a></h3>";
			htmlText+=" 		<div class='collect-success'>";
			htmlText+=" 		<p><i style='padding-right:10px;'></i>";
			htmlText+=" 		<span id='collectPromptText'></span></p>";
			htmlText+=" 		<p id ='goGoodsCollect_p' style='padding-left:35px;'>"+content+"</p>";
			htmlText+=" 		</div>";
			htmlText+=" 	</div>";
			htmlText+=" </div>";
			$("body").prepend(htmlText);
			$('.Add1,.mt-Add').css({
				left:($('body').width()-$('.Add1,.mt-Add').width())/2-20+'px',
				top:($(window).height()-$('.Add1,.mt-Add').height())/2+$(window).scrollTop()+'px',
				display:'block'
			});
		}
	}
	
	//关闭弹出框
	function cannelDel(){
		//$('.TB_Add,.Add1,.mt-Add').css('display','none');
		$('.TB_Add,.Add1,.mt-Add').remove();
	}
	
	
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