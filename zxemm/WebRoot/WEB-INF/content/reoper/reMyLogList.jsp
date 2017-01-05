<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/content/base/tag.inc" />
<%String path = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<HEAD>
		<TITLE>我的操作日志列表</TITLE>
		<META http-equiv=Content-Type content="text/html; charset=utf-8" />
		<META http-equiv=Expires content=0 />
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
			if(url.indexOf("?")>0){
				url = url + "&forSearch=true";
			}else{
				url = url + "?forSearch=true";
			}
			document.PageForm.action = url ;
			document.PageForm.submit();
		}
		
		//功能平台选项改变时查询对应的功能模块枚举信息
		function queryModule()
		{
			/*
			//当功能平台选项不为空时执行
			if(document.getElementById("platform").value!="")
			{
				var enumId = document.getElementById("platform");
				$.ajax({
					type:'POST',
					url:"acegi/queryEnumItemByEnumId.do",
					dateType:"json",
					data:'enumId='+ enumId.value.trim(),
					success: function(msg){
						addOptions(msg);
					},
					error: function(msg){
						alert("查询枚举信息出错,请与管理员联系！");
					}
				});
			}
			*/
		}
		
		//添加功能模块选项
		function addOptions(msg)
		{
			//清空功能模块的所有选项
			var moduleSelect=document.getElementById('module');
			moduleSelect.options.length=0;
			
			//重新生成功能模块选项
			moduleSelect.options.add(new Option("--请选择功能模块--","")); 
			var modules = msg.sysEnumItems;
			for(var x in modules)
			{
				moduleSelect.options.add(new Option(modules[x].displayValue,modules[x].fieldValue));
			}
		}
	
		-->
		</script>
	</head>
	<body>
		<div id=mainzone>
			<div id="body">
				<FORM id="PageForm" name="PageForm" method=post action="acegi/queryMyLog.do">
					<div id="itemList">
						<div class="loc">
							<div class="groupmenu" id="groupmenu"></div>
							<!--  
							<a href="<%=path %>/login/mainFrame.do"></a> &raquo;
							-->
							<span>系统管理</span> &raquo;
							<span>自助服务</span> &raquo;
							<span class="last">我的操作日志</span>
						</div>
	
	
						<!-- 全/反选 -->
						<div class="chooseCase">
							<!-- 搜索查询 -->
							<div style="float: left" class="search">
								<table width="100%" border="0">
									<tr>
										<td width="5%" align="right">功能平台：</td>
										<td width="10%">
											<select id="platform" name="logInfo.platform" onchange="queryModule()" class="sel-126">
							              		<option value="">--请选择功能平台--</option>
							              		<c:forEach var="platform" items="${platforms.sysEnumItems}">
													<option value="${platform.fieldValue }" <c:if test="${fn:trim(logInfo.platform) == fn:trim(platform.fieldValue) }">selected</c:if> >${platform.displayValue }</option>
												</c:forEach>
							              	</select>
		                                </td>
		                               	<td width="10%" align="right">起止时间：</td>
										<td >
		                                	<INPUT id="startDate" class="Wdate" onClick="WdatePicker({maxDate:'%y-%M-%d',dateFmt:'yyyyMMdd'})" readOnly  size=16 name="logInfo.startDate" value="${logInfo.startDate }" style="width:121px;height:16px"/>
												-
											<INPUT id="endDate" class="Wdate" onClick="WdatePicker({minDate:'#F{$dp.$D(\'startDate\');}',dateFmt:'yyyyMMdd'})" readOnly  size=16 name="logInfo.endDate" value="${logInfo.endDate }" style="width:121px;height:16px"/>
		                                </td>
		                            </tr>
		                            <tr>
		                                <td width="5%" align="right">功能模块：</td>
										<td width="10%">
		                                	<select id="module" name="logInfo.module" class="sel-126">
							              		<option value="">--请选择功能模块--</option>
							              		<c:forEach var="module" items="${modules.sysEnumItems}">
													<option value="${module.fieldValue }" <c:if test="${fn:trim(logInfo.module) == fn:trim(module.fieldValue) }">selected</c:if> >${module.displayValue }</option>
												</c:forEach>
							              	</select>
		                                </td>
										<td width="10%" align="right">操作类型：</td>
										<td width="10%">
											<select id="operAction" name="logInfo.operAction" class="sel-126">
							              		<option value="">--请选择操作类型--</option>
							              		<c:forEach var="operAction" items="${operActions.sysEnumItems}">
													<option value="${operAction.fieldValue }" <c:if test="${fn:trim(logInfo.operAction) == fn:trim(operAction.fieldValue) }">selected</c:if> >${operAction.displayValue }</option>
												</c:forEach>
							              	</select>
											<a href="javascript:submitForm('acegi/queryMyLog.do','search')">
												<img src="<%=request.getContextPath()%>/images/search-btn.jpg" style="vertical-align:middle; padding-left:10px;" />
											</a>
										</td>
										
										<td></td>
										<td></td>
					              	</tr>
		                        </table>
							</div>
						</div>
						
						
						<!-- 数据列表 -->
						<div id="PrintContent" class="mainCon">
							<table class="mainTab" id="itemListTab">
								<tr id="tabHeader">
									<!--
									<th style="width: 50px" class="p">
										<input name="chkAll" type="checkbox"
											onclick="checkAll(this.checked);" value="checkbox" />
									</th>
									-->
									<th>
										序号
									</th>
									<th>
										日期
									</th>
									<th>
										日志信息
									</th>
									<th>
										登录IP
									</th>
									<th>
										操作员编号
									</th>
								</tr>
								<c:if test="${empty pageFinder.data }">
									<tr><td colspan=6 align="center" height="25"><font color="red">没有查询到相关记录</font></td></tr>
								</c:if>
								<c:forEach var="logInfo" items="${pageFinder.data}"
									varStatus="status">
									<tr>
										<!-- 
										<td>
											<input id="logId_${logInfo.logId }" name="chkItem"
												value="${logInfo.logId }" type="checkbox"
												onclick="itemCheckbox_changed();" />
										</td>
										 -->
										<td>
											${logInfo.logId }
										</td>
										<td>
											${fn:substring(logInfo.operTime,0,19) }
										</td>
										<td>
											<!-- 功能平台 -->
											<c:forEach var="platform" items="${platforms.sysEnumItems}">
												<c:if test="${fn:trim(platform.fieldValue) == fn:trim(logInfo.platform) }">
													${platform.displayValue}
												</c:if>
											</c:forEach>
											<font color="red">&gt;&gt;</font>
											<!-- 功能模块 -->
											<c:forEach var="module" items="${modules.sysEnumItems}">
												<c:if test="${fn:trim(module.fieldValue) == fn:trim(logInfo.module) }">
													${module.displayValue}
												</c:if>
											</c:forEach>
											<font color="red">&gt;&gt;</font>
											<!-- 操作类型 -->
											<c:forEach var="operAction" items="${operActions.sysEnumItems}">
												<c:if test="${fn:trim(operAction.fieldValue) == fn:trim(logInfo.operAction) }">
													${operAction.displayValue}
												</c:if>
											</c:forEach>
											<!-- 操作摘要 -->
											${logInfo.description }
										</td>
										<td>
											${logInfo.loginIp }
										</td>
										<td>
											${logInfo.operNo }
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