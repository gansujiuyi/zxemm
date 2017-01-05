<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/content/base/tag.inc" />
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<HTML><HEAD><TITLE></TITLE>
<META http-equiv=Content-Type content="text/html; charset=GBK">
<META http-equiv=Expires content=0>
<link href="<%=path %>/css/mainframe.css" rel="stylesheet" type="text/css">
<link href="<%=path %>/css/Style1.css" rel="stylesheet" type="text/css">
<link href="<%=path %>/css/Style2.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=path %>/js/jquery-1.6.js"></script>
<script type="text/javascript" src="<%=path%>/js/base.js"></script>
<SCRIPT language=JavaScript>
$(document).ready(function() {
	$("input:checkbox").attr("disabled", true);
});
function saveRel(){

	if(confirm("您确认要保存当前角色对应权限?")){
		var fuctionIds = "";	//	所有需要保存的功能ID
		var roleId = "";
		var roleSel = document.getElementById("roleSel");	//	权限ID
		if(roleSel){
			var hasSelected = 0;
			var soptions = roleSel.options;
			for(var i =0; i<soptions.length; i++){
				if(soptions[i].selected)
					hasSelected += 1;
			}
			if(hasSelected == 0){
				alert("请选择一个设置角色！");
				return;
			}
			if(hasSelected > 1){
				alert("只能选择一个设置角色！");
				return;
			}
			roleId = roleSel.value
		}
		
		var   getCK=document.getElementsByTagName('input');   
		  for(var   i=0;i<getCK.length;i++){
		      whichObj=getCK[i];   
		      if(whichObj.type=="checkbox"){
		          if(whichObj.checked) {
		          	fuctionIds += whichObj.value + ",";
		          }
		      }
		  }
		  if( fuctionIds == ""){
		  	alert("选择至少一个功能!");
		  	return;
		  }else{
		  	fuctionIds = fuctionIds.substring(0,fuctionIds.length -1);
		  }
		$.ajax({
			type:'POST',
			url:"acegi/saveRoleFunction.do",
			dateType:"json",
			data:'functionIds='+ fuctionIds + "&roleId=" + roleId,
			success: function(msg){
				if('saveSuccess'==msg.message){
					alert("角色功能权限设置成功！");
				}else{
					alert("角色功能权限设置失败,请联系管理员！");
				}
			},
			error: function(msg){
				alert("角色功能权限设置失败,请联系管理员！");
			}
		});
		  
	}
}
function reloadFunByRole(roleId){
	if(roleId == "" || !roleId){reloadFun("");}
	else{
		$.ajax({
				type:'POST',
				url:"acegi/queryFunByRole.do",
				dateType:"json",
				data:"roleId=" + roleId,
				success: function(msg){
					if('querySuccess'==msg.message){
						reloadFun(msg.functionIds);
					}else{
						reloadFun("");
					}
				},
				error: function(msg){
					alert("角色功能权限设置失败,请联系管理员！");
				}
		});
	}
}
function reloadFun(functionIds){
	functionIds = functionIds.split(",");
	var   getCK=document.getElementsByTagName('input');   
		  for(var   i=0;i<getCK.length;i++){
		      whichObj=getCK[i];
		      if(whichObj.type=="checkbox"){
		      	var hasSel = false;
		      	for(var funid in functionIds){
		      		if(whichObj.value == functionIds[funid]) {
			          	hasSel = true;
			          	break;
			        }
		      	}
		      	//alert(whichObj);
		      	/*if(hasSel){
		      		whichObj.style.display="";
		      	}else{
		      		whichObj.style.display="none";
		      	}*/
		      	whichObj.checked = hasSel;
		      }
		  }
}
</SCRIPT>
</HEAD>
	<body>
		<div id="mainzone" style="height: 504px">
			<div id="body">
				<FORM name=PageForm method=post>
				<div id="itemList">					
					<div class="loc">
						<div class="groupmenu" id="groupmenu"></div>
						<!-- 
						<a href="<%=path %>/login/mainFrame.do"></a> &raquo; 
						-->
						<span>系统管理</span> &raquo;
						<span>功能权限管理</span> &raquo;
						<span class="last">角色权限查看</span>
					</div>
					<input name="status" type=hidden value="Submit">
					<BR>
					<TABLE cellSpacing=0 cellPadding=0 width="99%" align=center
						border=0 >
						<TBODY>
							<TR>
								<TD align=left width="20%" valign="top">
									<strong>1、请选择查看角色：</strong><br/>
									<select onchange="reloadFunByRole(this.value)" id="roleSel"
										size="15" style="width:320" class="sel">
										<c:forEach var="role" items="${roleList}">
											<option value="${role.roleId }">
												${role.roleName }-${role.description }
											</option>
										</c:forEach>
									</select>
								</TD>
							</TR>
							<TR><TD><br/></TD></TR>
							<TR>
								<TD align="left" width="100%" valign="top">
									<strong>2、请查看分配权限：</strong><br/>
									<TABLE class=edit_table cellSpacing=0 borderColorDark=#ffffff
										cellPadding=1 width="80%" align=left bgColor=#dddddd
										borderColorLight=#000000 border=0>
										<TBODY>
											<s:property value="allFunctionTree" escape="false" />
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
						<!-- 
						<input type="button" name="btnSave" value=""
							onclick="saveRel()"
							id="btnSave" class="save" /> -->
						<div class="clr"></div>
					</div>
					</div>
				</FORM>
			</div>
		</div>
	</BODY>
</HTML>
