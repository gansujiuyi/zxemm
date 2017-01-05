<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/content/base/tag.inc" />
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + 
		":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title><s:text name='domain.title'/></title>
	<link href="<%=path %>/css/basic.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
	<div class="content">
	
	  <div class="rightmain">
			<div class="rightwz">
				<div class="rightwzimg"></div>
				<!-- <div class="rightweizi">&nbsp;&nbsp;您所在的位置:错误页面</div> -->
			</div>
			<div class="rightmain">
              <table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
                  <td align="center" valign="top" class="tablerbtbgbk">
                   <table width="600" border="0">
				          <tr>
				            <td colspan="2" valign="middle" align="center" height="95"  style="line-height:95px;"><img src="<%=path %>/images/wxts.gif" width="87" height="20" align="middle" /></td>
				          </tr>
				          <tr>
				            <td width="41%" align="right" style="padding-right:10px;"><img src="<%=path %>/images/error_img.gif" width="60" height="55" align="middle" /></td>
				            <td width="59%" align="left" valign="center" height="55" ><font color="red" id="errorFont"  class="errorfont"><s:actionmessage/></font>
										    	<SCRIPT language=JavaScript>
				                            		var ef = document.getElementById("errorFont");
				                            		if(ef.innerText.replace(/(^\s*)|(\s*$)/g, "") == ""){
				                            			ef.innerText = "系统出错了,请联系管理员...";
				                            		}
				                            		//判断是否是唯一约束错误提示
				                            		if(ef.innerText.indexOf("constraint")>0){
				                            			ef.innerText = "插入或修改的资料存在唯一性冲突，操作失败，您维护的资料可能已经存在，请检查！";
				                            		}
				                            	</SCRIPT>
				           </td>
				          </tr>
				          <tr>
				            <td colspan="2" align="center" height="40"><input name="button" type=button onclick="javascript:history.go(-1)" class="return" /></td>
				          </tr>
				    </table>
                    </td>
                </tr>
              </table>
	    </div>
		</div>
  </div>
<script language="JavaScript" type="text/JavaScript">
//document.getElementById("returnBtn").focus();
</script>
</body>
</html>
