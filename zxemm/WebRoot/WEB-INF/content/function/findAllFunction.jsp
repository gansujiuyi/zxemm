<%@page import="com.jiuyi.jyplat.util.ConfigManager"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/content/base/tag.inc" />
<%
	String path = request.getContextPath();
	String base_path = ConfigManager.getString("base_path", "请在emm中设置");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=base_path%>" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title></title>
		<link href="<%=path%>/css/mainframe.css" rel="stylesheet" type="text/css" />
		<link href="<%=path%>/css/Style1.css" rel="stylesheet" type="text/css" />
		<link href="<%=path%>/css/Style2.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=path%>/js/jquery-1.6.js"></script>
		<script type="text/javascript" src="<%=path%>/js/base.js">
		</script>
		<script type="text/javascript" src="<%=path%>/js/formValidate.js">
		</script>
		<script>
		<!--
		//按钮提交事件
		function submitForm(url, act)
		{
			var functionNo = checkedFuncNo();	//获取被选中的functionNo
			
			//点击添加子菜单按钮
		    if(act == "addSubFunction")
		    {
		    	if(functionNo==null||functionNo=="")
		    	{
		    		alert("请选择一个父功能！");
		    		return;
		    	}
		    	else
		    	{
		    		//获取当前选中的系统功能的上级功能编号
		    		var parentFuncNo = document.getElementById("parentFunctionNo");//上级功能列表
		    		var sltFuncNo = parentFuncNo.options[parentFuncNo.selectedIndex].value;//上级功能号
		    		if(sltFuncNo=="")
		    		{
		    			//所选功能为一级功能，直接跳转到添加子功能页面
		    			document.PageForm.action = url+"?functionNo="+functionNo;
						document.PageForm.submit();
		    		}
		    		else
		    		{
			    		//查询所选菜单的上级菜单信息
			    		$.ajax({
							type:'POST',
							url:"sysFunction/queryFunctionById.do",
							dateType:"json",
							data:'functionNo='+ sltFuncNo,
							success: function(msg){
								if(msg.message == 'querySuccess'){
									//若选中功能的上级功能是一级功能则跳转到添加子功能页面
									if(!msg.sysFunction.parentFunctionNo || msg.sysFunction.parentFunctionNo.trim()=="")
									{
										document.PageForm.action = url+"?functionNo="+functionNo;
										document.PageForm.submit();
									}
									else
									{
										alert("选中功能为最后一级功能，无法继续添加子功能！");
									}
								}else{
									alert("根据功能编号查询失败,请联系管理员!");
								}
							},
							error: function(msg){
								alert("查询失败,请联系管理员!");
							}
						});
					}	
		    	}
		    }
		    //点击添加一级菜单按钮
		    if(act == "addTopFunction")
		    {
				document.PageForm.action = url;
				document.PageForm.submit();
		    }
		    //点击删除按钮		
		    if(act == "delete"){
		        if(functionNo == null || functionNo == ""){
			    	alert("请选择要删除的系统功能！");
			    	return;
			    }
				if(! confirm("确定要删除吗?")){
			  	  return;
				}
				//查询是否有子功能
				queryChildFunction(url,functionNo);				
		    }		    
		    		
		    if(act == "modify")
		    {
				var functionName = document.getElementById("functionName");
				var functionUrl = document.getElementById("functionUrl");
				var description = document.getElementById("description");
				var orderBy = document.getElementById("orderBy");
				var functionNo = document.getElementById("functionNo").value;
				if(functionNo == null || functionNo == "" || functionNo.length == 0){
					alert("请从下面列表中选择一个要修改的功能菜单");
					return false;
				}
				//if(!checkFormdata(functionName,"功能名称",40,true,true,false,false,false))
				//{	
				//	return false;
				//}
				if(!checkFormdata(functionUrl,"功能路径",200,false,false,false,false,false))
				{	
					return false;
				}
				if(orderBy.value=="" || isNaN(orderBy.value) || orderBy.value.indexOf('.')>-1 || orderBy.value.trim()=="")
				{
					alert("请输入整数序号！");
					orderBy.select();
					return false;
				}
				if(!checkFormdata(description,"功能描述",50,false,false,false,false,false))
				{	
					return false;
				}
				
				var v1 = document.getElementById("functionName").value;
				var v2 = document.getElementById("functionUrl").value;
				var v3 = document.getElementById("description").value;
									
				document.getElementById("functionName").value=v1.replace(/\s+/g,"");
				document.getElementById("functionUrl").value=v2.replace(/\s+/g,"");
				document.getElementById("description").value=v3.replace(/\s+/g,"");
				
				var parentNo = document.getElementById("parentFunctionNo").value;
				var v1 = document.getElementById("functionName").value;										
				document.getElementById("functionName").value=v1.replace(/\s+/g,"");
				checkNamesubItem = document.getElementById("functionName").value;
				functionNo = document.getElementById("functionNo").value;

				$.ajax({   //请求后台检查菜单名称是否重复
					type:'POST',
					url:"sysFunction/CheckNamesubfunUP.do",
					dateType:"json",	
					data:"checkNamesubItem="+checkNamesubItem+"&parentNo="+parentNo+"&functionNo="+functionNo,
					success: function(msg){
						if(msg.message=="exists"){
							alert("系统菜单名称已经存在请重新输入！！");
							return false;
						}else{
							document.PageForm.action = url;
							document.PageForm.submit();
						}
					},
					error: function(msg){}
				});
		    }
		}
		
		//获取被选中的功能编号
		function checkedFuncNo(){
			var checkedFunctions = document.getElementsByName("checkedFunction");	//获取页面所有的系统功能单选按钮
			if(checkedFunctions!=null){
				for(var i=0;i<checkedFunctions.length;i++){
					if(checkedFunctions[i].checked){//被选中
				       	functionNo = checkedFunctions[i].value;
				       	return functionNo;
					}
				}
			}
		}
		
		//查询对应的所有子功能
		
		function queryChildFunction(url,functionNo)
		{
			var functionNos = "";
			$.ajax({
				type:'POST',
				url:"sysFunction/queryAllChildFunction.do?functionNo="+ functionNo,
				dateType:"json",
				success: function(msg){
					//查询到有子功能
					if(msg.message == 'hasChildFunction'){
						var functions = msg.sysFunctions;
						if(functions!=null && functions.length>0)
						{
							for(i in functions)
							{
								functionNos += functions[i].functionNo+",";
								
							}
						}
					}
					if(functionNos!="")
					{
						if(confirm("此功能下面包含子功能，是否一并删除？"))
						{
							functionNo = functionNos+functionNo;
							document.PageForm.action = url+functionNo;
							document.PageForm.submit();	
						}
						else
						{
							alert("删除操作已取消！");
						}
					}
					else
					{
						document.PageForm.action = url+functionNo;
						document.PageForm.submit();	
					}
				},
				error: function(msg){
					alert("查询子功能失败,请联系管理员!");
				}
			});
		}
		
		//查询某个功能的详细信息
		function queryActionByFuncNo()
		{
			var functionNo = checkedFuncNo();	//获取被选中的functionNo
			$.ajax({
				type:'POST',
				url:"sysFunction/queryFunctionById.do",
				dateType:"json",
				data:'functionNo='+ functionNo,
				success: function(msg){
					if(msg.message == 'querySuccess'){
						//调用填充sysFunction对象的方法
						fillText(msg.sysFunction);
						
					}else{
						alert("查询失败,请联系管理员!");
					}
				},
				error: function(msg){
					alert("查询失败,请联系管理员!");
				}
			});
		}
		//显示查询到的Function信息
		function fillText(sysFunction)
		{
			document.getElementById("functionNo").value=sysFunction.functionNo;
			document.getElementById("functionName").value=sysFunction.functionName;
			document.getElementById("orderBy").value=sysFunction.orderBy;
			
			var parentSel = document.getElementById("parentFunctionNo");//获取页面所有父功能号选项
			var ops = parentSel.options;
			for(var i = 0;i<ops.length;i++)
			{
				if(sysFunction.parentFunctionNo==null||trim(sysFunction.parentFunctionNo)=="")
				{
					ops[1].selected = true;	//选择一级功能
					break;
				}			
				if(ops[i].value==sysFunction.parentFunctionNo)
				{
					ops[i].selected = true;
					break;
				}
			}
			document.getElementById("functionUrl").value=sysFunction.url;
			//document.getElementById("orderBy").value=sysFunction.orderBy;
			document.getElementById("description").value=sysFunction.description;
			document.getElementById("orderBy").value=sysFunction.orderBy;
			
			//选中功能状态
			if(sysFunction.status==1)
			{
				document.getElementById("status1").checked=true;
			}
			else
			{   if(sysFunction.status==0)
					document.getElementById("status0").checked=true;
				else
					document.getElementById("status2").checked=true;
			}
		}
		
		//trim方法删除左右两端的空格
		function trim(str){ 
			return str.replace(/(^\s*)|(\s*$)/g, "");
		}
		-->
		</script>

	</head>

	<body>
	<div id="mainzone">
	<div id="body">
		<form id=PageForm name=PageForm method=post>
			<input name="hidpub" type=hidden value=0 />
			<div id="itemList">
				<div class="loc">
					<!--  
					<a href="<%=path %>/login/mainFrame.do"></a> &raquo; 
					-->
					<span>系统管理</span> &raquo;
					<span>功能权限管理</span> &raquo;
					<a href="javascript:gotoListHome()">功能菜单维护</a> &raquo; <span class="last">功能菜单列表</span>
				</div>
				
				
				<div class="chooseCase">
					<div style="float: left" class="search">
						<table width="100%" border="0">
							<tr>
				                <td width="5%" align="right">
				                	<input id="functionNo" type="hidden" name="sysFunction.functionNo" value="" class="txt-150"/>功能名称：
				                </td>
								<td width="10%">
                                	<input id="functionName" class="txt-150" type="text" name="sysFunction.functionName" value=""/>
                                </td>
								<td width="10%" align="right">上级功能：</td>
								<td width="10%">
										<select id="parentFunctionNo" name="sysFunction.parentFunctionNo" class="sel-156">
											<option value="-1"> --请选择功能菜单-- </option>
											<option value=""> 一级功能 </option>
											<c:forEach var="fun1" items="${sysFunctions}">
												<!-- 输出一级功能菜单 -->								
												<c:if test="${empty fun1.parentFunctionNo || fn:trim(fun1.parentFunctionNo)==''}">
													<option value="${fun1.functionNo }">
														${fun1.functionName }
													</option>
													<!-- 输出二级功能菜单 -->
													<c:forEach var="fun2" items="${sysFunctions}">
														<c:if test="${fn:trim(fun2.parentFunctionNo)==fn:trim(fun1.functionNo)}">
															<option value="${fun2.functionNo }">
																&nbsp;|---${fun2.functionName }
															</option>
															<!-- 输出三级功能菜单 -->
															<c:forEach var="fun3" items="${sysFunctions}">
																<c:if test="${fn:trim(fun3.parentFunctionNo)==fn:trim(fun2.functionNo)}">
																	<option value="${fun3.functionNo }">
																		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|---${fun3.functionName }
																	</option>
																</c:if>
															</c:forEach>
														</c:if>
													</c:forEach>
												</c:if>
											</c:forEach>
										</select>
                                </td>
                                <td width="10%" align="right">URL：</td>
								<td >
									<input id="functionUrl" class="txt-150" type="text" name="sysFunction.url" value=""/>
                                </td>
                                <td></td>
                            </tr>
                            <tr>
				              	 <td width="5%" align="right">功能说明：</td>
								 <td width="10%">
								 	<input id="description" class="txt-150" type="text" name="sysFunction.description" value=""/>
								 </td>
								 <td width="10%" align="right">功能序号：</td>
								 <td width="10%" >
								 	<input id="orderBy" class="txt-150" type="text" name="sysFunction.orderBy" value=""/>
								 </td>	
								 <td width="10%" align="right">状态：</td>
								 <td width="10%">
								 	<INPUT id="status1" type=radio name="sysFunction.status" value=1 />
									可用&nbsp;&nbsp;
									<INPUT id="status0" type=radio name="sysFunction.status" value=0 />
									不可用	
									<INPUT id="status2" type=radio name="sysFunction.status" value=2 />
									可用但隐藏				
									<input type="button" class="comon-btn" value="修改" onclick="submitForm('sysFunction/updateFunction.do','modify')"/>
								 	<!-- <a style="cursor: hand" onclick="submitForm('sysFunction/updateFunction.do','modify')" class="btn_search">修改</a> -->
								</td>
			              	</tr>
                        </table>
					</div>
			</div>
			
			
			<table class=list_table cellspacing=1 cellpadding=0 width="100%"
				align=center border=0>
				<tbody>
					${functionMenu }
				</tbody>
			</table>
			<div class="headPageNavi">
					<!-- 增删改 -->
					<div style="float: left" class="tools">
						<ul>
							<li>
								<a href="javascript:gotoListHome()"><img
										src="<%=path%>/images/template/b_ref.gif" alt="IMG" />刷新</a>
							</li>
							<li style="width: 105px">
								<a href="javascript:submitForm('sysFunction/addFunction.do?functionNo=','addTopFunction')"><img
										src="<%=path%>/images/template/b_new.png" alt="IMG" />添加一级菜单</a>
							</li>
							<li style="width: 90px">
								<a href="javascript:submitForm('sysFunction/addFunction.do','addSubFunction')"><img
										src="<%=path%>/images/template/b_new.png" alt="IMG" />添加子菜单</a>
							</li>
							<li>
								<a id="lnkBtnDel"
									href="javascript:submitForm('sysFunction/delFunction.do?functionNo=','delete')"><img
										src="<%=path%>/images/template/b_del.png" alt="IMG" />删除</a>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</form>
		</div>
		</div>
	</body>
</html>
