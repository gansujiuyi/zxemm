<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/content/base/tag.inc" />
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<HEAD>
		<TITLE>修改操作员信息</TITLE>
		<META http-equiv=Content-Type content="text/html; charset=utf-8">
		<META http-equiv=Expires content=0>
		<script type="text/javascript" src="<%=path%>/js/jquery.js">
		</script>
		<script type="text/javascript" src="<%=path%>/js/base.js">
		</script>
		<script type="text/javascript" src="<%=path%>/js/formValidate.js">
		</script>
		<link href="<%=path%>/css/Style1.css" rel="stylesheet" type="text/css" />
		<link href="<%=path%>/css/Style2.css" rel="stylesheet" type="text/css" />
		
		<link href="<%=path%>/js/select2-3.5.2/select2.css" rel="stylesheet"/>
		<script type="text/javascript" src="<%=path%>/js/select2-3.5.2/select2.js"></script>
		<script type="text/javascript" src="<%=path%>/js/select2-3.5.2/select2_locale_zh-CN.js"></script>
		
		
<SCRIPT language=JavaScript>
<!--

$(document).ready(function(){
	
	$("#wxBranchId").on("change", function (e) {
		$("#wxBranchName").val(e.added.text);
	});
	
	$("#wxBranchId").select2({
		ajax: {
			url: "<%=path%>/wxBranch/searchBranch.do",
			dataType: 'json',
			delay: 250,
			data: function (params) {
				return {
					name: params,
					q: params.term, // search term
					page: params.page,
				};
			},
            results: function (data) {            	
            	var rsts = [];
            	
            	if(data.branchs == null){
            		{
                        results: rsts;
                    };
            	}
            	
            	for(var i = 0; i < data.branchs.length; i++){
            		var branch = data.branchs[i];
            		var tmp = {};
            		tmp.id = branch.id;
            		tmp.text = branch.name;
            		rsts.push(tmp);
            	}
            	
                return {
                    results: rsts
                };
            },
			cache: true
		},
		minimumInputLength: 0,
	
	});
	
	//初始化选择项。
	$("#s2id_wxBranchId").find(".select2-chosen").html($("#wxBranchName").val());
});


//按钮提交事件
function submitForm(url, act)
{
    if(act == "add")
    {
		var operName = document.getElementById("operName");
		var operMobile = document.getElementById("operMobile");
		var roleId = document.getElementById("roleId");
		var institutionId = document.getElementById("institutionId");
		var department = document.getElementById("department");
		
		var operLvl = document.getElementById("operLvl");
		
		if(!checkFormdata(operName,"操作员姓名",20,true,true,false,false,false))
		{	
			return false;
		}
		if(institutionId.value=="")
		{
			alert("请选择操作员所属机构！");
			institutionId.focus();
			return false;
		}
		if(department.value=="")
		{
			alert("请选择操作员所属部门！");
			department.focus();
			return false;
		}
		if(roleId.value=="")
		{
			alert("请选择操作员所属角色！");
			roleId.focus();
			return false;
		}		
		if(operMobile.value!="")
		{
			if(checkTel(operMobile.value.trim())==0)
			{
				alert("联系电话输入有误！");
				operMobile.select();
				return false;
			}
			if(!checkFormdata(operMobile,"联系电话",20,false,false,false,false,false)||(operMobile.value.trim().charAt(0)=='1'&& ! isMobile(operMobile.value.trim())))
			{	
				operMobile.select();
				return false;
			}
		}
		
		if(operLvl.value==""){
			alert("请选择操作员级别！")
			operLvl.focus();
			return false;
		}
		
    	document.PageForm.action = url;
    	document.PageForm.submit();
	}
}

-->
</SCRIPT>
</HEAD>
<BODY>
	<div id="mainzone">
		<div id="body">
			<!-- 提交表单 -->
			<FORM name=PageForm  method=post>
				<!-- 系统功能路径 -->
				<div class="loc">
					<div class="groupmenu" id="groupmenu"></div>
					<!--  
					<a href="<%=path %>/login/mainFrame.do"></a> &raquo;
					-->
					<span>系统管理</span> &raquo;
					<span>机构与用户管理</span> &raquo;
					<a href="<%=path %>/acegi/queryOper.do">操作员维护</a> &raquo;
					<span class="last">修改操作员</span>
				</div>

				<!-- 主体DIV -->
				<div class="tab_cntr">
					<!-- 
					<ul class="opt">
						<li>
							<a class="cur" href="javascript:void(0)">基本属性</a>
						</li>
					</ul>
					-->
					<!-- 内容填写表格 -->
					<table class="editorTb" style="" id="tbBase" name="editorTab">
						<tr>
							<td class="hd" colspan="2">
								修改系统操作员信息（<font color="red">* 为必填项</font>）
							</td>
						</tr>
								<input id="operNo" name="oper.operNo" value="${oper.operNo }" type="hidden" maxlength="20" class="txt-150"/>
						<tr>
							<td class="titl">
								<b>操作员名称：</b>
							</td>
							<td>
								<input id="operName" name="oper.operName" value="${oper.operName }" type="text" maxlength="20" class="txt-150"/>
								<font color="red">&nbsp;*</font>
							</td>
						</tr>						
						<tr>
							<td class="titl">
								<b>机构：</b>
							</td>
							<td>
								<select id="institutionId" name="oper.inst.institutionId" class="sel-250">
				              		<option value="">--请选择所属机构--</option>
									<!-- 输出一级机构 -->
									<c:forEach var="inst" items="${insts}">									
											<option value="${inst.institutionId }" <c:if test="${inst.institutionId == fn:trim(oper.inst.institutionId)}">selected</c:if>>
												${inst.institutionNo }-${inst.institutionName }
											</option>
											<!-- 输出二级机构 -->
											<c:forEach var="inst2" items="${insts}">
												<c:if test="${inst2.parentInstitutionId==inst.institutionId}">
													<option value="${inst2.institutionId }" <c:if test="${inst2.institutionId == fn:trim(oper.inst.institutionId)}">selected</c:if>>
														&nbsp;|---${inst2.institutionNo }-${inst2.institutionName }
													</option>
													<!-- 输出三级机构 -->
													<c:forEach var="inst3" items="${insts}">
														<c:if test="${inst3.parentInstitutionId==inst2.institutionId}">
															<option value="${inst3.institutionId }" <c:if test="${inst3.institutionId == fn:trim(oper.inst.institutionId)}">selected</c:if>>
																&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|---${inst3.institutionNo }-${inst3.institutionName }
															</option>
														</c:if>
													</c:forEach>
												</c:if>
											</c:forEach>
									</c:forEach>
				              	</select>
				              	<font color="red">&nbsp;*</font>
							</td>
						</tr>
						<tr>
							<td class="titl">
								<b>操作员网点：</b>
							</td>
							<td>
								<input type="hidden" class ="sel-300" id="wxBranchId" name="oper.wxBranchId" value="${oper.wxBranchId}"/>
				              	<font color="red">&nbsp;*</font>当用户为网点操作员时请选择正确的银行网点，按网点名中文搜索，只显示前20条。
								<input type="hidden" id="wxBranchName" name="oper.wxBranchName" value="${oper.wxBranchName}"/>
							</td>
						</tr>
						<tr>
							<td class="titl">
								<b>操作员部门：</b>
							</td>
							<td>
								<select id="department" name="oper.department" class="sel-250">
				              		<option value="">请选择</option>
				              		<c:forEach var="enum" items="${sysEnum.sysEnumItems}">
										<c:if test="${enum.status=='1'}">
											<option value="${enum.fieldValue }"
											<c:if test="${fn:trim(enum.fieldValue) == fn:trim(oper.department) }">selected</c:if>
											>${enum.displayValue }</option>
										</c:if>
									</c:forEach>
				              	</select>
				              	<font color="red">&nbsp;*</font>
							</td>
						</tr>
						<tr>
							<td class="titl">
								<b>角色：</b>
							</td>
							<td>
								<select id="roleId" name="oper.role.roleId" class="sel-250">
				              		<option value="">请选择</option>
				              		<c:forEach var="roleT" items="${roles}">
										<option value="${roleT.roleId }"
										<c:if test="${fn:trim(roleT.roleId) == fn:trim(oper.role.roleId) }">selected</c:if>
										>${roleT.roleName }-${roleT.description }</option>
									</c:forEach>
				              	</select>
				              	<font color="red">&nbsp;*</font>
				              	<a style="color: blue;margin-left: 10px;" href="<%=path %>/acegi/queryRoleShow.do">查看角色功能权限</a>
							</td>
						</tr>
						<tr>
							<td class="titl">
								<b>联系电话：</b>
							</td>
							<td>
								<input id="operMobile" name="oper.operMobile" value="${oper.operMobile }" type="text" maxlength="20" class="txt-150"/>
							</td>
						</tr>
						<tr>
							<td class="titl">
								<b>操作员级别：</b>
							</td>
							<td>
								<select id="operLvl" name="oper.operLvl" class="sel-250">
				              		<option value="" >--请选择级别--</option>
				              		<option value="1" <c:if test="${oper.operLvl == '1' }">selected</c:if>>行员级</option>
				              		<option value="2" <c:if test="${oper.operLvl == '2' }">selected</c:if>>机构级</option>
				              		<option value="3" <c:if test="${oper.operLvl == '3' }">selected</c:if>>管理员级</option>
				              		<!-- <option value="4" <c:if test="${oper.operLvl==4 }">selected</c:if>>全辖级</option> -->
				              	</select>
				              	<font color="red">&nbsp;*</font>
							</td>
						</tr>
						<tr>
							<td class="titl"><b><font color="red">操作员级别说明：</font></b></td>
							<td>
								<font color="blue">1. <font color=red>【行员级】</font>：凡是有客户经理号输入的地方，都已经客户经理号固定为本人</font>
								<br/>
								<font color="blue">2. <font color=red>【机构级】</font>：客户经理号未固定，机构下拉列表只有本机构及下级机构</font>
								<br/>
								<font color="blue">3. <font color=red>【管理员级】</font>：可以看全部机构以及《--全部--》</font>
								<!-- 
								<br/>
								<font color="blue">4. <font color=red>【全辖级】</font>：可以看本机构、下级机构以及《--全部--》</font>
								 -->
							</td>
						</tr>
					</table>

					<!-- 提交按钮 -->
					<div class="btn-wz">
						<div style="width: 250px; float: left;">
							<input name="button" type=button onclick="javascript:history.go(-1)"
								value="" class="return" />
						</div>
						<input type="button" name="btnSave" value=""
							onclick="submitForm('acegi/saveUpdatedOper.do','add')"
							id="btnSave" class="save" />
						<input name="Submit" type="reset" value="" class="reset" />
						<div class="clr"></div>
					</div>
				</div>
			</FORM>
		</div>
	</div>
	</BODY>
</HTML>