<jsp:directive.page contentType="text/html;charset=UTF-8" />
<jsp:directive.include file="/WEB-INF/content/base/tag.inc" />

<script>
var formId = "${param.formId}";
var totalRows=${agencyInfos.rowCount};
var pageSize=${agencyInfos.pageSize};
function queryPage(page){
	if(isNaN(pageSize)){
		return;
	}
	var toPage=page;
	if(page==0){
		toPage=document.getElementById("query.page").value;
		if(isNaN(toPage) || toPage<=0){
			alert("请输入大于0的整数.");
			return;
		}		
	}
	if(page < 0){
		alert('请输入大于0的正整数.'); 
		return;
	}
	//校验是跳转页是否在记录有效范围内 
	//取大于等于总页数的值
	var totalPage=Math.ceil(totalRows/pageSize);
	if(toPage>totalPage){
		toPage=totalPage;
	}
	document.getElementById("query.page").value=toPage;
	var table = (formId&&formId!="")?document.getElementById(formId):document.forms[0];

	var arr = table.elements;
	var flag = false;
	for(var i=0,j=arr.length;i<j;i++){
		if(arr[i].getAttribute("name")=="query.page"){
			flag = true;
			break;
		}
	}
	if(!flag){
		var artionUrl = table.getAttribute("action");
		if(artionUrl.indexOf("?")>0){
			artionUrl = artionUrl + "&query.page="+toPage;
		}else{
			artionUrl = artionUrl + "?query.page="+toPage;
		}
		table.setAttribute("action",artionUrl);
	}
	table.submit();
}

</script>

    	 总计<span style='color:red;margin:0 2px'>${agencyInfos.rowCount}</span>条记录  共<span style='color:red;margin:0 2px'>${agencyInfos.pageCount}</span>页 每页<span style='color:red;margin:0 2px'>${agencyInfos.pageSize}</span>条 &nbsp;&nbsp;
    	<input name="bp" id="bp" type="button" <c:if test="${!agencyInfos.hasPrevious}">disabled="disabled"</c:if> class="page_button_first" value="第一页" onClick="queryPage(1)"/> 
    	<input name="bp" id="bp" type="button" <c:if test="${!agencyInfos.hasPrevious}">disabled="disabled"</c:if> class="page_button_prev" value="上一页" onClick="queryPage(${agencyInfos.pageNo-1})"/> 
    	<input name="bp" id="bp" type="button" <c:if test="${!agencyInfos.hasNext}">disabled="disabled"</c:if> class="page_button_next" value="下一页" onClick="queryPage(${agencyInfos.pageNo+1})"/> 
    	<input name="bp" id="bp" type="button" <c:if test="${!agencyInfos.hasNext}">disabled="disabled"</c:if> class="page_button_last" value="最末页" onClick="queryPage(${agencyInfos.pageCount})"/>&nbsp;&nbsp;
    	<input id="query.page" name="query.page" type="text" maxlength="8" value="${agencyInfos.pageNo}" size="3" onkeyup="value=value.replace(/[^\d]/g,'')"/>&nbsp;&nbsp;
    	<input name="bj" id="bj" type="button" value="  Go  " onClick="queryPage(0);" />
    	&nbsp;&nbsp;&nbsp;&nbsp;
