<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.jiuyi.jyplat.util.ConfigManager"%>
<jsp:directive.include file="/WEB-INF/content/base/tag.inc" />
<%
	String path = request.getContextPath();
	String base_path = ConfigManager.getString("base_path", "请在emm中设置");	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=base_path%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>列表详情</title>
<link rel="stylesheet" type="text/css" href="css/public.css" />
<link rel="stylesheet" type="text/css" href="css/style_list.css" />
<link rel="stylesheet" type="text/css" href="css/datePicker.css" />
<style type="text/css">
	
	
	
</style>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery-1.6.js"></script>
<script language="javascript" type="text/javascript" src="<%=path %>/js/timerControl/WdatePicker.js"></script>
<!--[if IE 6]>
    <script type="text/javascript" src="js/ie_png.js"></script>
    <script type="text/javascript">
        ie_png.fix('*');
    </script>
<![endif]-->
<script type="text/javascript">
		$(document).ready(function(){
			//翻页
			$(".page_turning a").click(function(){
				$(this).addClass('click').siblings().removeClass('click');
			});
		});
	</script>
</head>

<body>
	<div class="list_information">
	<form id="PageForm" name="PageForm" method="post" action="kernelAccountRecord/qurkernelAccount.do">
    <div class="loc">
        <div class="groupmenu" id="groupmenu"></div>
        <span>资金监管</span> &raquo;
        <span>资金监管报表统计</span> &raquo;
        <span class="last">账户余额查询</span>
    </div>
		<!--列表信息-->
		<ul class="list_info mt10">
			<li class="list_title">
				<span style="width:265px;" >监管账号</span>
				<span style="width:265px;" >账户余额</span>
				<span style="width:265px;">交易日期</span>
				<span  style="width:265px;">交易时间</span>
			</li>
			<c:forEach items="${pageFinder.data}" var="orderInfo">
			<li>
				<span  style="width:265px;">${orderInfo.cardNo}</span>
				<span  style="width:265px;">${orderInfo.balance }</span>
				<span  style="width:265px;" >${orderInfo.traDate }</span>
				<span  style="width:265px;">${orderInfo.traTime}</span>
			</li>
			</c:forEach>
		</ul>
		<!--列表信息end-->
		<div class="page_turning fright mt20">
			<c:import url="../base/page.jsp?formId=PageForm" charEncoding="utf-8"></c:import>
		</div>
        <!-- 增删改 -->
        <div class="tools mt20">
            <ul style="float:left">
                <li><a href="javascript:document.getElementById('PageForm').submit();">
                <img src="/emm/images/template/b_ref.gif" alt="IMG" />刷新</a>
                </li>
            </ul>
        </div>
	</form>
	</div>
</body>
</html>
