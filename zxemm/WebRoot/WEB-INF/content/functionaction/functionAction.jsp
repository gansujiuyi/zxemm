<%@page import="com.jiuyi.jyplat.util.ConfigManager"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/content/base/tag.inc" />
<%
	String path = request.getContextPath();
	String base_path = ConfigManager.getString("base_path", "请在emm中设置");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=base_path%>">

		<title>My JSP 'functionAction.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="<%=path%>/css/mainframe.css" rel="stylesheet" type="text/css" />
		<link href="<%=path%>/css/Style1.css" rel="stylesheet" type="text/css" />
		<link href="<%=path%>/css/Style2.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=path %>/js/jquery-1.6.js"></script>
		<script type="text/javascript" src="<%=path %>/js/base.js"></script>
		<SCRIPT>
		//定义全局变量
		var fuctionNo = "";	//选择的功能编号
		var checkedCount = 0;//选择的功能个数  
		//按钮提交事件
		function saveRel(){
			if(confirm("确认保存该功能与action关联?")){
				
				checkedFunctionNO();//调用获取选中function的方法获得被选中的functionNo
			
				//选择要关联的action，并获取以选择的action编号
				if( checkedCount != 1){
				  	alert("请选择一个功能！");
					return;
				}
				var actionIds = "";	//被选中的action编号
				
				var actionSel = document.getElementById("bindAction");	//	获取待关联的action下拉列表
				if(actionSel){
					var soptions = actionSel.options;
					if(soptions.length == 0){
						alert("请选择关联的action！");
						return;
					}
					for(var i =0; i<soptions.length; i++){
						//获取所有待关联的action编号
						actionIds += soptions[i].value+",";
					}
				}
				
				//保存function和action关联关系
				$.ajax({
					type:'POST',
					url:"<%=path%>/sysFunctionAction/addFunctionAction.do",
					dateType:"json",
					data:'functionNo='+ functionNo + "&actionIds=" + actionIds,
					success: function(msg){
						if('saveSuccess'==msg.message){
							alert("功能与action关联成功！");
						}else{
							alert("添加功能与action对应关系失败,请联系管理员!");
						}
					},
					error: function(msg){
						alert("操作失败,请联系管理员!");
					}
				});
			}
		}
		
		//获取被选中的功能编号
		function checkedFunctionNO()
		{
			var checkedFunctions = document.getElementsByName("checkedFunction");	//获取页面所有的系统功能单选按钮
			if(checkedFunctions!=null){
				for(var i=0;i<checkedFunctions.length;i++){
					if(checkedFunctions[i].checked){//若被选中则获取选中的functionNo
				       	functionNo = checkedFunctions[i].value;
				       	checkedCount = 1;
				       	break;
					}
				}
			}
		}
		
		//根据功能编号查询对应action
		function queryActionByFuncNo()
		{
			//将之前选中的action选项清空
			var actionSel = document.getElementById("actionSel"); 
			var soptions = actionSel.options;
			if(soptions!=null){
				for(var i =0; i<soptions.length; i++){
					//document.getElementById("actionSel").options[i].selected=false;
					soptions[i].selected=false;
				}
			}
			
			checkedFunctionNO();//调用获取选中function的方法获得被选中的functionNo
			
			if( checkedCount == 1){		
				$.ajax({
					type:'POST',
					url:"<%=path%>/sysFunctionAction/queryActionByFuncNo.do",
					dateType:"json",
					data:'functionNo='+ functionNo,
					success: function(msg){
						if('querySuccess'==msg.message){
							selectAction(msg.actionIds);//调用选中对应action方法
							//清空bindAction下拉框的所有选项
							document.getElementById("bindAction").options.length=0;
							//往关联action列表中添加action选项
							addAction();
						}else{
							alert("查询失败,请联系管理员!");
						}
					},
					error: function(msg){
						alert("操作失败,请联系管理员!");
					}
				});
			}
		}
		//选中对应的action
		function selectAction(actionIds){
			var acIds = actionIds.split(",");
			if(acIds.length==0){
				alert("此功能没有对应的action！");
				return;
			}
			var actionSel = document.getElementById("actionSel"); 
			
			var soptions = actionSel.options;
			
			//根据actionId选中相应选项
			for(var i =0; i<soptions.length; i++){
				for(var j=0;j<acIds.length;j++)
				{
					if(soptions[i].value==acIds[j]){
						soptions[i].selected="selected";
					}
				}
			}
		}
		
		//根据关键字选中相关的action选项
		function searchAction(){
			//获取Action关键字
			var actionKeyWord = document.getElementById("actionKeyWord");
			if(actionKeyWord.value=="" || actionKeyWord.value.trim()==""){
				alert("请输入Action关键字查询相关Action选项！");
				return;
			}
			//获取所有action选项
			var actionSel = document.getElementById("actionSel"); 
			var soptions = actionSel.options;
			//action选项全部取消选中
			for(var i =0; i<soptions.length; i++){
				soptions[i].selected = "";
			}
			//选中和ActionKeyWord匹配的Action选项
			for(var j =0; j<soptions.length; j++){
				//如果Action选项的文本值包含ActionKeyWord则将该选项选中
				if(soptions[j].text.indexOf(actionKeyWord.value)!=-1){
					soptions[j].selected="selected";
				}
			}
		}
		
		//获取所有被选中的Action对象
		function getSelectedAction(actionId){
			//获取所有action选项
			var actionSel = document.getElementById(actionId); 
			var soptions = actionSel.options;
			if(soptions.length==0){
				alert("Action列表中没有选项！");
				return;
			}
			//获取被选中的action选项
			var selectedOptions = new Array();
			//定义数组的元素个数
			var j = 0;
			for(var i =0; i<soptions.length; i++){
				//如果Action选项被选中则添加到数组中
				if(soptions[i].selected==true){
					selectedOptions[j] = soptions[i];
					j++;
				}
			}
			return selectedOptions;
		}
		
		//添加选中的Action选项到待关联的Action下拉列表中
		function addAction(){
			//获取被选中的Action对象
			var selectedOptions = getSelectedAction("actionSel");
			//获取待关联的Action下拉列表对象
			var bindAction = document.getElementById("bindAction");
			//向待关联的Action下拉列表对象中添加option
			for(i=0;i<selectedOptions.length;i++){
				//如果选中的action选项已经存在于待关联的action下拉列表中则不再重新添加该选项
				if(bindAction.options.length>0){
					var flag = true;
					for(j=0;j<bindAction.options.length;j++){
						
						if(selectedOptions[i].value == bindAction.options[j].value){
							flag = false;
							break;
						}
					}
					if(flag){
						bindAction.options.add(new Option(selectedOptions[i].text,selectedOptions[i].value));
					}
				}else{
					bindAction.options.add(new Option(selectedOptions[i].text,selectedOptions[i].value));
				}
			}
		}
		
		//移除待关联的Action下拉列表中被选中项
		function removeAction(){
			//获取bindAction下拉框中被选中的Action对象
			var selectedOptions = getSelectedAction("bindAction");
			//获取待关联的Action下拉列表对象
			var bindAction = document.getElementById("bindAction");
			//向待关联的Action下拉列表对象中添加option
			for(i=0;i<bindAction.options.length;i++){
				for(j=0;j<selectedOptions.length;j++){
					if(selectedOptions[j].value == bindAction.options[i].value){
						bindAction.options.remove(i);
					}
				}
			}
		}
		
		</SCRIPT>
	</HEAD>
	<body>
		<div id="mainzone">
			<div id="body">
		<FORM id=PageForm name=PageForm method=post>
			<input name="status" type=hidden value="Submit">
			<div class="loc">
				<div class="groupmenu" id="groupmenu"></div>
				<!--  
				<a href="<%=path %>/login/mainFrame.do"></a> &raquo; 
				-->
				<span>系统管理</span> &raquo;
				<span>功能权限管理</span> &raquo;
				<span class="last">功能操作关联</span>
			</div>
			<TABLE class=edit_table cellSpacing=0 cellPadding=0 width="100%" align=center style="border-width:0px; border-color: #B4C9C6">
				<TBODY>
					<TR style="BORDER: white 1px solid;">
						<TD width="35%"><label style="font-weight: bold">请选择功能</label></TD>
						<TD width="5%"></TD>
						<TD width="15%"><label style="font-weight: bold">关联Action</label></TD>
						<TD width="5%"></TD>
						<TD >
							<label style="font-weight: bold">Action关键字：</label>
							<input type="text" id="actionKeyWord" name="actionKeyWord" class="txt">&nbsp;&nbsp;
							<!-- <a href="javascript:searchAction()" class="btn_search">搜索</a> -->
							<a href="javascript:searchAction()">
								<img src="<%=request.getContextPath()%>/images/search-btn.jpg" style="vertical-align:middle; padding-left:10px;" />
							</a>
						</TD>
					</TR>
					<TR>
						<td style="text-align: left;width: 300px; vertical-align: top;BORDER: #B4C9C6 1px solid;">
							<TABLE cellSpacing=0 cellPadding=0 width="100%" align=center
								border=0>
								<TBODY>
									<TR>
										<TD>
											${functionMenu }
										</TD>
									</TR>
								</TBODY>
							</TABLE>
						</td>
						<td style="vertical-align:top; width:20px;"></td>
						<TD style="text-align: left;width: 220px; vertical-align: top;BORDER: #B4C9C6 1px solid;">
							<TABLE style="width:220px">
								<TBODY>
									<TR>
										<TD>
											<select id="bindAction" name="sysAction.actionId" size="22" style="width:220px; BORDER: #ffffff 0px;" multiple="multiple">
											</select>
										</TD>
									</TR>
									<TR>
										<TD>
											<!-- <a href="javascript:removeAction()" class="btn_search">&gt;&gt;移除</a> -->
											<input type="button" onclick="javascript:removeAction()" value="&gt;&gt;移除" style="cursor:pointer; border: #B4C9C6 1px solid;"/>
										</TD>
									</TR>
								</TBODY>
							</TABLE>
						</TD>
						<td style="vertical-align:top; width:20px;"></td>
						<TD style="text-align: left;width: 220px; vertical-align: top;BORDER: #B4C9C6 1px solid;" colspan="4">
							<TABLE style="width: 220px">
								<TBODY>
									<TR>
										<TD>
											<select id="actionSel" name="sysAction.actionId" size="22" style="width: 350px; BORDER: #ffffff 0px;" multiple="multiple">
												<!-- 
												<OPTION VALUE='0'>
													--请选择Action--
												</OPTION>
												 -->
												<s:iterator value="sysActions">
													<OPTION VALUE='<s:property value="actionId"/>'>
														<s:if test="description != null && !description.isEmpty() ">
															(<s:property value="description" />)&nbsp;
														</s:if>
														<s:property value="actionName" />
													</OPTION>
												</s:iterator>
											</select>
										</TD>
									</TR>
									<TR>
										<TD>
											<!-- <a href="javascript:addAction()" class="btn_search">&lt;&lt;添加</a> -->
											<input type="button" onclick="javascript:addAction()" value="&lt;&lt;添加" style="cursor:pointer; border: #B4C9C6 1px solid;"/>
										</TD>
									</TR>
								</TBODY>
							</TABLE>
						</TD>
					</TR>					
				</TBODY>
			</TABLE>
			<!-- 提交按钮 -->
			<div style="padding: 10px; padding-left: 10px;">
				<div style="width: 85px; float: left;">
					<input name="button" type=button onclick="javascript:history.go(-1)"
						value="" class="return" />
				</div>
				<input type="button" name="btnSave" value=""
					onclick="saveRel()"
					id="btnSave" class="save" />
				<div class="clr"></div>
			</div>
		</FORM>
		</div>
		</div>
		<!--<SCRIPT language=JavaScript src="../../../js/copyright.js"></SCRIPT>-->
	</BODY>
</HTML>
