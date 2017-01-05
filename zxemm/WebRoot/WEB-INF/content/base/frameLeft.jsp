<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.jiuyi.jyplat.util.Constant"%>
<%@page import="com.jiuyi.jyplat.util.SessionUtil"%>
<jsp:directive.include file="/WEB-INF/content/base/tag.inc" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml">
	<HEAD>
		<META content="text/html; charset=utf-8" http-equiv=Content-Type>
		<META http-equiv=Expires content=0>
		<link rel="stylesheet"
			href="<%=request.getContextPath()%>/css/common.css" type="text/css" />

	<SCRIPT language=JavaScript>
<!--
function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_findObj(n, d) { //v4.0
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && document.getElementById) x=document.getElementById(n); return x;
}

function MM_nbGroup(event, grpName) { //v3.0
  var i,img,nbArr,args=MM_nbGroup.arguments;
  if (event == "init" && args.length > 2) {
    if ((img = MM_findObj(args[2])) != null && !img.MM_init) {
      img.MM_init = true; img.MM_up = args[3]; img.MM_dn = img.src;
      if ((nbArr = document[grpName]) == null) nbArr = document[grpName] = new Array();
      nbArr[nbArr.length] = img;
      for (i=4; i < args.length-1; i+=2) if ((img = MM_findObj(args[i])) != null) {
        if (!img.MM_up) img.MM_up = img.src;
        img.src = img.MM_dn = args[i+1];
        nbArr[nbArr.length] = img;
    } }
  } else if (event == "over") {
    document.MM_nbOver = nbArr = new Array();
    for (i=1; i < args.length-1; i+=3) if ((img = MM_findObj(args[i])) != null) {
      if (!img.MM_up) img.MM_up = img.src;
      img.src = (img.MM_dn && args[i+2]) ? args[i+2] : args[i+1];
      nbArr[nbArr.length] = img;
    }
  } else if (event == "out" ) {
    for (i=0; i < document.MM_nbOver.length; i++) {
      img = document.MM_nbOver[i]; img.src = (img.MM_dn) ? img.MM_dn : img.MM_up; }
  } else if (event == "down") {
    if ((nbArr = document[grpName]) != null)
      for (i=0; i < nbArr.length; i++) { img=nbArr[i]; img.src = img.MM_up; img.MM_dn = 0; }
    document[grpName] = nbArr = new Array();
    for (i=2; i < args.length-1; i+=2) if ((img = MM_findObj(args[i])) != null) {
      if (!img.MM_up) img.MM_up = img.src;
      img.src = img.MM_dn = args[i+1];
      nbArr[nbArr.length] = img;
  } }
}

function getNowDate(){
    var day = new Date();
    var Year = 0;
    var Month = 0;
    var Day = 0;
    var CurrentDate = "";
    Year= day.getFullYear();
    Month= day.getMonth()+1;
    Day = day.getDate();
    CurrentDate += Year;
    if (Month >= 10 ){
     CurrentDate += Month;
    }
    else{
     CurrentDate += "0" + Month;
    }
    if (Day >= 10 ){
     CurrentDate += Day ;
    }
    else{
     CurrentDate += "0" + Day ;
    }
    return CurrentDate;
 } 
//-->
</SCRIPT>

		<META content="Microsoft FrontPage 4.0" name=GENERATOR>
		<META http-equiv=Expires content=0>

		<base target="mainFrame">
		<title></title>
	</HEAD>
	<BODY>
		<div id="left_content">
			<div id="user_info">
				<!-- 根据泰安需求，由于支行不存在部门，所以此处显示操作员级别 add by lzb on 2013/04/24 -->
				[<span id="showA1" style="color: #0099CC" href="javascript:void(0);" title="<s:property value="#session.login_system_user.inst.institutionName"/>" ><s:property value="#session.login_system_user.inst.institutionName"/></span>]
				<br>[<span id="showA2" style="color: #0099CC"  href="javascript:void(0);" 
				<%if(!Constant.SYS_BANK_TYPE.toLowerCase().endsWith("ta")){ %>	
					title="<s:property value="#session.login_system_user.departmentName" />">
					<s:property value="#session.login_system_user.departmentName" /></span>]
				<%}else{ 
				  	if(SessionUtil.getOperator().getOperLvl().equals("3")){
				%>
					title="管理员级别" /><label>管理员级别</label></span>]
				<%	
					}else if(SessionUtil.getOperator().getOperLvl().equals("2")){
				%>
					title="机构级别" /><label>机构级别</label></span>]
				<%
						}else if(SessionUtil.getOperator().getOperLvl().equals("1")){
				%>
					title="行员级别" /><label>行员级别</label></span>]
				<%
						} 
					}
				%>
				<SCRIPT language=JavaScript>
					var showA1 = document.getElementById("showA1");
					if(showA1.innerText.length > 10)
						showA1.innerText = showA1.innerText.substring(showA1.innerText,9) + "...";
					var showA2 = document.getElementById("showA2");
					if(showA2.innerText.length > 10)
						showA2.innerText = showA2.innerText.substring(showA2.innerText,9) + "...";
				</SCRIPT>
			</div>
			<div id="main_nav">
				<div id="right_main_nav">
					<TABLE border=0 width=160>
						<TBODY>
							<s:property value="menuHtml" escape="false" />
						</TBODY>
					</TABLE>
				</div>
			</div>
		</div>
	</BODY>
</HTML>
