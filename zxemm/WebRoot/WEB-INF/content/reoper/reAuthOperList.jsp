<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/content/base/tag.inc" />
<%
	String path = request.getContextPath();
%>
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
   	//提交审核   
    if(act == "modify"){
        if(sltedItemCnt == 0){
		    alert("没有选择要进行审核的操作员信息！");
		    return;
		}
		var ids  = document.PageForm.chkItem;
		var idstr = "";
		var authStatus = "";
    	if(ids.id)
    	{
			idstr = ids.value;
			//authStatus = document.getElementById("authStatus0").value;
		}
		else
		{
			for( i=0 ; i < ids.length; i++ ){
	    		if( ids[i].checked == true ){
	    			idstr += ids[i].value + ",";
	    			//authStatus += document.getElementById("authStatus"+i).value+",";
	    		}
	    	}
	    }
		if(idstr.length > 0 && idstr.indexOf(",")>0){
	    	idstr = idstr.substring(0,idstr.length-1);
	    	//authStatus = authStatus.substring(0,authStatus.length-1);
		}
		document.PageForm.action = url + "?operatorNos="+idstr;//+"&authStatus="+authStatus;
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
<c:if test="${message == 'auditPass'}">
	alert("操作员审核通过！");
</c:if>
-->
</SCRIPT>
	</HEAD>
	<BODY>
		<div id="mainzone">
			<div id="body">
				<FORM id=PageForm name=PageForm method=post action=acegi/queryByOperStatus.do>
					<div id="itemList">
						<div class="loc">
							<div class="groupmenu" id="groupmenu"></div>
							<!--  
							<a href="<%=path %>/login/mainFrame.do"></a> &raquo;
							-->
							<span>系统管理</span> &raquo;
							<span>机构与用户管理</span> &raquo;
							<span class="last">操作员审核</span>
						</div>


						<!-- 全/反选 -->
						<div class="chooseCase">
							<!-- 搜索查询 -->
							<div style="float: left" class="search">
								<table width="100%" border="0">
									<tr>
						                <td width="5%" align="right">操作员名称：</td>
										<td width="10%">
		                                	<input id="operName" name=operName value="${operName }" type="text" class="txt-150" />
										</td>
										<td width="10%" align="right">机构：</td>
										<td >
		                                	<select id="institutionId" name="institutionId" class="sel-300">
							              		<option value="">--请选择所属机构--</option>
												<!-- 输出一级机构 -->
												<c:forEach var="inst" items="${insts}">									
													<c:if test="${inst.parentInstitutionId==''}">
														<option value="${inst.institutionId }" <c:if test="${fn:trim(institutionId) == fn:trim(inst.institutionId) }"> selected="selected" </c:if>>
															${inst.institutionNo }-${inst.institutionName }
														</option>
														<!-- 输出二级机构 -->
														<c:forEach var="inst2" items="${insts}">
															<c:if test="${inst2.parentInstitutionId==inst.institutionId}">
																<option value="${inst2.institutionId }" <c:if test="${fn:trim(institutionId) == fn:trim(inst2.institutionId) }"> selected="selected" </c:if>>
																	&nbsp;|---${inst2.institutionNo }-${inst2.institutionName }
																</option>
																<!-- 输出三级机构 -->
																<c:forEach var="inst3" items="${insts}">
																	<c:if test="${inst3.parentInstitutionId==inst2.institutionId}">
																		<option value="${inst3.institutionId }" <c:if test="${fn:trim(institutionId)== fn:trim(inst3.institutionId) }"> selected="selected" </c:if>>
																			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|---${inst3.institutionNo }-${inst3.institutionName }
																		</option>
																	</c:if>
																</c:forEach>
															</c:if>
														</c:forEach>
													</c:if>
												</c:forEach>
							              	</select>
										</td>
										</tr>
										<tr>
										<td width="5%" align="right">操作员状态：</td>
										<td width="10%">
											<select id="operStatus" name="operStatus" class="sel-156">
							              		<option value="">--全部--</option>
							              		<option value="1" <c:if test="${fn:trim(operStatus) == 1 }"> selected="selected" </c:if>>可用</option>
							              		<option value="2" <c:if test="${fn:trim(operStatus) == 2 }"> selected="selected" </c:if>>待审核</option>
							              		<option value="3" <c:if test="${fn:trim(operStatus) == 3 }"> selected="selected" </c:if>>锁定</option>
							              		<option value="4" <c:if test="${fn:trim(operStatus) == 4 }"> selected="selected" </c:if>>停用</option>
							              	</select>
										</td>
										<td width="10%" align="right">
											操作员部门：
										</td>
										<td >
											<select id="departmentid" name="department" class="sel-250">
							              		<option value="">--请选择所属部门--</option>
							              		<c:forEach var="enum" items="${sysEnum.sysEnumItems}">
													<c:if test="${enum.status=='1' }">
														<option value="${enum.fieldValue }" <c:if test="${fn:trim(department) eq fn:trim(enum.fieldValue) }"> selected="selected" </c:if>>${enum.displayValue }</option>
													</c:if>
												</c:forEach>
							              	</select>
											<a href="javascript:submitForm('acegi/queryByOperStatus.do','search')">
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
									<th>
										登录号
									</th>
									<th>
										姓名
									</th>
									<th>
										级别
									</th>
									<th>
										机构
									</th>
									<th>
										所属部门
									</th>
									<th>
										角色
									</th>
									<th>
										电话
									</th>
									<th>
										操作员状态
									</th>
									<th>
										创建时间
									</th>
									<th>
										最后修改时间
									</th>
									<th>
										最后审核时间
									</th>
									<th>
										审核状态
									</th>
									<th>
										末IP
									</th>
									<th>
										未时
									</th>
									<th>
										总登
									</th>
									<th>
										错登
									</th>

								</tr>
								<c:if test="${empty pageFinder.data }">
									<tr><td colspan=17 align="center" height="25"><font color="red">没有待审核操作员记录</font></td></tr>
								</c:if>
								<c:forEach var="oper" items="${pageFinder.data}"
									varStatus="status">
									<tr>
										<td>
											<input id="${status.index }" name="chkItem"
												value="${oper.operNo}" type="checkbox"
												onclick="itemCheckbox_changed();" />
										</td>
										<td>
											${oper.operNo }
										</td>
										<td>
											${oper.operName }
										</td>
										<td>
											<c:if test="${oper.operLvl=='1' }">行员级</c:if>
											<c:if test="${oper.operLvl=='2' }">机构级</c:if>
											<c:if test="${oper.operLvl=='3' }">管理员级</c:if>
										</td>
										<td>
											${oper.inst.institutionName }
										</td>
										<td>
											<c:forEach var="enum" items="${sysEnum.sysEnumItems}">
												<c:if test="${enum.status=='1'}">
													<c:if
														test="${fn:trim(enum.fieldValue) == fn:trim(oper.department) }">
													${enum.displayValue}
												</c:if>
												</c:if>
											</c:forEach>
										</td>
										<td>
											<c:if test="${!empty oper.role}">
												${oper.role.roleName }
											</c:if>
										</td>
										<td>
											${oper.operMobile }
										</td>
										<td>
											<c:if test="${fn:trim(oper.operStatus) == 1}"><font color="green">可用</font></c:if>
											<c:if test="${fn:trim(oper.operStatus) == 2}"><font color="orange">待审核</font></c:if>
											<c:if test="${fn:trim(oper.operStatus) == 3}"><font color="red">锁定</font></c:if>
											<c:if test="${fn:trim(oper.operStatus) == 4}"><font color="red">停用</font></c:if>
										</td>
										<td>
											${oper.createdDate }
										</td>
										<td>
											${oper.modifiedDate }
										</td>
										<td>
											${oper.authDate }
										</td>
										<td>
											<font color="orange">待审核</font>
										</td>
										<td>
											${oper.lastLoginIp }
										</td>
										<td>
											${oper.lastLoginTime }
										</td>
										<td>
											${oper.loginTimes }
										</td>
										<td>
											${oper.errorLoginTimes }
										</td>
									</tr>
								</c:forEach>
							</table>
						</div>

						<div class="headPageNavi">
							<!-- 增删改 -->
							<div style="float: left;" class="tools">
								<ul>
									<li>
										<a href="javascript:gotoListHome()"><img
												src="<%=path%>/images/template/b_ref.gif" alt="IMG" />刷新</a>
									</li>
									<li style="width: 85px">
										<a href="javascript:gotoListHome()" onclick="submitForm('acegi/auditOperator.do','modify')"><img
												src="<%=path%>/images/template/b_edit.png" alt="IMG" />审核通过</a>
									</li>
									<!-- 
									<li>
										<a href="javascript:gotoListHome()"
											onclick="submitForm('acegi/updateOper.do','modify')"><img
												src="<%=path%>/images/template/b_edit.png" alt="IMG" />编辑</a>
									</li>
									<li>
										<a id="lnkBtnDel"
											href="javascript:submitForm('acegi/delOper.do','delete')"><img
												src="<%=path%>/images/template/b_del.png" alt="IMG" />删除</a>
									</li>
									 -->
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