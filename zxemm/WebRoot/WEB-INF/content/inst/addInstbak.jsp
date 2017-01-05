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
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<META http-equiv=Expires content=0>
<link href="<%=path %>/css/mainframe.css" rel="stylesheet" type="text/css">
<SCRIPT language=JavaScript>
<!--
//按钮提交事件
function submitForm(url, act)
{
    if(act == "add")
    {
     if(document.getElementById("institutionNo").value==""){
       alert("请输入构机编号!");
       return;
     }
    document.PageForm.action = url;
    document.PageForm.submit();
   }
}

-->
</SCRIPT>
</HEAD>

<body>
<BR>
<FORM name=PageForm  method=post>
  <input name="status" type=hidden value="Submit">
  <table cellspacing=0 cellpadding=0 width="95%" align=center border=0>
    <tbody>
    <font color="green">>>增加机构</font> (<font color=red>*</font>为必输字段)
    <tr>
      <td height=10> <hr> </td>
    </tr>
    <tr>
      <td>
        <INPUT class=BtnAction style="CURSOR: hand" onclick=submitForm('acegi/saveAddedInst.do','add') type=button value=保存 border=0 name=保存>
        <input name="button3" type=button class=BtnAction style="CURSOR: hand"  value=清空 onclick = document.PageForm.reset();>
        <input name="button4" type=button class=BtnAction style="CURSOR: hand"  value=返回 onclick="javascript:history.go(-1)";>
      </td>
    </tr>
    <tr valign=bottom>
      <td colspan=4> <hr> </td>
    </tr>
    <tr>
      <td colspan=4>
      <TABLE width="100%" height="137" border=0 align=center cellPadding=2 cellSpacing=0
      	borderColorLight=#000000 borderColorDark=#ffffff bgColor=#dddddd class=edit_table>
          <TBODY>
            <TR>
              <TD class=edit_table_td_title noWrap>机构编号：</TD>
              <TD width="23%" height="28" noWrap class=edit_table_td_input>
              	<INPUT id="institutionNo" class=TextInput maxLength=4 size=20 name="inst.institutionNo" class="txt-150">&nbsp;<font color=red>*</font>
              </TD>
              <TD width="19%" noWrap class=edit_table_td_title>机构名：</TD>
              <TD width="44%" noWrap class=edit_table_td_input><INPUT id="institutionName" class="txt-150"  size=20 name="inst.institutionName"></TD>
            </TR>
            <TR>
              <TD class=edit_table_td_title noWrap> <DIV align=right>说明：</DIV></TD>
              <TD height="25" noWrap class=edit_table_td_input> <DIV align=left>
                  <textarea id="description" name="inst.description" rows="2" cols="20" class="txt-300"></textarea>
                </DIV></TD>
              <TD height="25" noWrap class=edit_table_td_title>上级机构：</TD>
              <TD height="25" noWrap class=edit_table_td_input>
                <select id="parentInstitutionId" name="inst.parentInstitutionId" class="sel-300">
                	<c:forEach var="operator" items="${instList}" >
                		<option value="${operator.institutionNo }">${operator.institutionName }</option>
					</c:forEach>
                </select>
             </TD>
            </TR>
        </TABLE></td>
    </tr>
  </table>
</FORM>
</BODY></HTML>