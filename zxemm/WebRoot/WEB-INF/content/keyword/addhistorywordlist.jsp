<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/content/base/tag.inc" />
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>新增历史搜索词</title>
		<meta http-equiv=Content-Type content="text/html; charset=utf-8" />
		<meta http-equiv=Expires content=0 />
		<script type="text/javascript" src="<%=path%>/js/jquery-1.6.js"> </script>
		<script type="text/javascript" src="<%=path%>/js/base.js"> </script>
		<script type="text/javascript" src="<%=path%>/js/formValidate.js"></script>
		<link href="<%=path%>/css/Style1.css" rel="stylesheet" type="text/css" />
		<link href="<%=path%>/css/Style2.css" rel="stylesheet" type="text/css" />
		<script language=JavaScript>
			//按钮提交事件 
			function submitForm(url, act)
			{	
				var printWord=$("#scrword").val();
				var souWord=$("#serword").val();
				
			   if(souWord==""||souWord==null){
			    alert("请输入搜索词");
			    return;
			   }
			   if(printWord==""||printWord==null){
			    alert("请输入搜索显示词");
			    return;
			   }
				document.PageForm.action = url+"?flag="+act;
				document.PageForm.submit();
			}
		</script>
	</head>
	<body>
	<div id=mainzone>
		<div id="body">
			<!-- 提交表单 -->
			<form id=PageForm name=PageForm  method=post>
				<!-- 系统功能路径 -->
				<div class="loc">
					<div class="groupmenu" id="groupmenu"></div>
					<span>历史搜索词管理</span> &raquo;
					<a href="<%=path %>/business/queryStoreByCustId.do">历史搜索词维护</a> &raquo;
					<span class="last">历史搜索词</span>
				</div>

				<!-- 主体DIV -->
				<div class="tab_cntr">
					<table class="editorTb" style="" id="tbBase"  name="editorTab">
						<tr>
							<td class="hd" colspan="2">
							  <s:if test="flag=='edit'">
							          编辑历史搜索热词（<font color="red">* 为必填项</font>）
							  </s:if>
							  <s:else>
							           新增历史搜索热词（<font color="red">* 为必填项</font>）
							  </s:else>
							</td>
						</tr>
						<tr>
							<td class="titl">
								<b>显示词：</b>
							</td>
							<td>
								<input id="scrword" name="historyword.SCREENWORD" value="${historyword.SCREENWORD}" type="text" class="txt-150" />
								<input type="hidden" name="historyword.id" value="${historyword.id}" />
								<font color="red">&nbsp;*</font>
							</td>
						</tr>
						
						<tr>
							<td class="titl">
								<b>搜索词：</b>
							</td>
							<td >
								<input id="serword" name="historyword.SERCHWORD" value="${historyword.SERCHWORD}" type="text" class="txt-150" />
								<font color="red">&nbsp;*</font>
							</td>
						</tr>												
							<tr>
							<td class="titl">
								<b>搜索词状态：</b>
							</td>
							<td >
								<select name="historyword.WORDSTATE" id="wordstatus" class="sel-156">
									<option value="1" <c:if test="${'1' eq historyword.WORDSTATE}">selected</c:if>>正常</option>
									<option value="2" <c:if test="${'2' eq historyword.WORDSTATE}">selected</c:if>>已删除 </option>
								</select>
								<font color="red"> *</font>
							</td>
						</tr>
					</table>

					<!-- 提交按钮 -->
					<div style="padding: 10px; padding-left: 10px;">
						<div style="width: 250px; float: left;">
							<input name="button" type=button onclick="javascript:history.go(-1)" class="return" />
						</div>
						<input type="button" name="btnSave"  onclick="submitForm('historyWord/saveHistoryWord.do','<s:property value="flag"/>')" id="btnSave" class="save" />
						<input name="Submit" type="reset" value="" class="reset" />
						<div class="clr"></div>
					</div>
				</div>
			</form>
			</div>
		</div>
	</body>
</html>