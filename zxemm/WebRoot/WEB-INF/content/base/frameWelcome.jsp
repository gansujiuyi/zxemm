<%@page language="java" contentType="text/html;charset=utf-8"%>
<jsp:directive.include file="/WEB-INF/content/base/tag.inc" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<HEAD>
		<TITLE><s:text name='domain.title'/>业务管理平台1.0</TITLE>
		<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=utf-8">
		<META http-equiv=Expires content=0>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/common.css" type="text/css" />
		<link href="<%=request.getContextPath()%>/css/Style2.css" rel="stylesheet" type="text/css" />
	</HEAD>
	<body>
		<div id="mainzone" class="main-bg">
			<table width="80%" border="0" align="center">
				<tr>
					<td>
						<strong>尊敬的<s:property value="#session.login_system_user.operName" />，欢迎登录<s:text name='domain.title'/>业务管理平台！</strong>
					</td>
				</tr>
				<tr> 
				  <td height="42" colspan="2">
				  	<TABLE width="100%" height="137" border=0 align=left cellPadding=2 cellSpacing=0>
				         <TBODY>
				            <TR align="left">
				             <TD  noWrap>累计登录次数：</TD>
				             <TD width="70%" height="28" noWrap >
				             		<s:property value="#session.login_system_user.loginTimes" />
				            </TD>
				          </TR>
				          <TR align="left">
				            <TD noWrap >上次登录IP：</TD>
				            <TD width=70%" height="28" noWrap >
				            	<s:property value="#session.login_system_user.lastLoginIp" />
				            </TD>
				          </TR>
				          <TR align="left">
				            <TD noWrap >上次登录时间：</TD>
				            <TD width=70%" height="28" noWrap >
				            	<c:if test="${!empty session.login_system_user.lastLoginTime}">
				            		${fn:substring(session.login_system_user.lastLoginTime,0,4)}-${fn:substring(session.login_system_user.lastLoginTime,4,6)}-${fn:substring(session.login_system_user.lastLoginTime,6,8)}&nbsp;${fn:substring(session.login_system_user.lastLoginTime,9,17)}
				            	</c:if>
				            </TD>
				          </TR>
				        <%--   <TR align="left">
				            <TD width=70%" height="28" colspan="2" noWrap >
				            	<h5>当前系统处理账务日期&nbsp;&nbsp;[<a style="color: blue;cursor: pointer;">${sysParameter.sysWorkDate}</a>], &nbsp;&nbsp;
								执行状态&nbsp;&nbsp;
									[<a style="color: blue;cursor: pointer;">
									<c:if test="${sysParameter.sysState=='0' }">正常</c:if>
									<c:if test="${sysParameter.sysState=='1' }">正在批处理</c:if>
									<c:if test="${sysParameter.sysState=='7' }">批处理异常</c:if>
									<c:if test="${sysParameter.sysState=='9' }">批处理故障排除，请求系统继续处理</c:if>
									</a>]
								</h5>
				            </TD>
				          </TR> --%>
				          <c:if test="${!empty isDefaultPW && isDefaultPW == 'true' }">
				          	<TR align="left">
				             <TD noWrap  colspan="2">
				             <br/>
				             <strong>
				             <font color="red">
							温馨提示：
							<br/>您当前登录密码为初始密码，为避免风险，请尽快
							<a href="acegi/modifyPassword.do">修改您的密码</a>，谢谢！
							</font>
							</strong>
						  </TD>
				           </TR>
				          </c:if>
				          <c:if test="${!empty beAuditOper }">
				          	<TR align="left">
				             <TD noWrap  colspan="2">
				             <br/>
				             <strong>
				             <font color="red">
							操作提示：
							<br/>当前待审核的操作员记录为
							<a href="acegi/queryByOperStatus.do">
							${beAuditOper }
							</a>
							条。
				             </font>
				             </strong>
						  </TD>
				           </TR>
				          </c:if>
				        </TBODY>
				      </TABLE>
				  </td>
				</tr>
			</table>
		</div>
	</body>
</HTML>
