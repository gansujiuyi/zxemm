<jsp:directive.page contentType="text/html;charset=UTF-8" />
<jsp:directive.include file="/WEB-INF/content/base/tag.inc" />

<script>
var formId = "${param.formId}";
var totalRows=${pageFinders.rowCount};
var pageSize=${pageFinders.pageSize};
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
    <!-- 
    	<c:if test="${pageFinder.pageCount > 1}">
    
	    <div class="meneame">
			<c:choose>
				<c:when test="${!pageFinder.hasPrevious}">	
					<span class="disabled"><a href="javascript:alert('已经到第一页');" > < </a></span>
				</c:when>
				<c:otherwise>
					<a href="javascript:queryPage(${pageFinder.pageNo-1})"  > < </a>
				</c:otherwise>
			</c:choose>
			
				
			<c:choose>
				<c:when test="${pageFinder.pageCount < 10 }">
					<c:forEach var="row" begin="1" end="${pageFinder.pageCount}" >
						<c:choose>
							<c:when test="${pageFinder.pageNo == row}">	
								<span class="current"><a href="javascript:queryPage(${row})" > ${row}</a></span>
							</c:when>
							<c:otherwise>
								<a href="javascript:queryPage(${row});"> ${row}</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</c:when>
				<c:when test="${(pageFinder.pageCount - pageFinder.pageNo) < 5}">
					<c:forEach var="row" begin="1" end="9" >
						<c:choose>
							<c:when test="${(pageFinder.pageCount - pageFinder.pageNo) == (9- row)}">	
								<span class="current"><a href="javascript:queryPage(${pageFinder.pageCount - 9 + row})"> ${pageFinder.pageCount  - 9 + row}</a></span>
							</c:when>
							<c:otherwise>
								<a href="javascript:queryPage(${pageFinder.pageCount  - 9 + row})"  > ${pageFinder.pageCount  - 9 + row}</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<c:forEach var="row" begin="1" end="10" >
						<c:choose>
							<c:when test="${row == 8 }">...</c:when>
							<c:when test="${row == 9 }"><a href="javascript:queryPage(${pageFinder.pageCount -1 })"  > ${pageFinder.pageCount -1}</a></c:when>
							<c:when test="${row == 10 }"><a href="javascript:queryPage(${pageFinder.pageCount})" > ${pageFinder.pageCount}</a></c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${pageFinder.pageNo < 5 }">
										<c:choose>
											<c:when test="${pageFinder.pageNo == row}">	
												<span class="current"><a href="javascript:queryPage(${row});" > ${row}</a></span>
											</c:when>
											<c:otherwise>
												<a href="javascript:queryPage(${row});"> ${row}</a>
											</c:otherwise>
										</c:choose>
									</c:when>
									<c:otherwise>
										<c:choose>
											<c:when test="${row == 4}">	
												<span class="current"><a href="javascript:queryPage(${pageFinder.pageNo-4+row})"> ${pageFinder.pageNo-4+row}</a></span>
											</c:when>
											<c:otherwise>
												<a href="javascript:queryPage(${pageFinder.pageNo-4+row})" > ${pageFinder.pageNo-4+row}</a>
											</c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			
			<c:choose>
				<c:when test="${ pageFinder.pageNo == pageFinder.pageCount }">	
					<span class="disabled"><a href="javascript:alert('已经到最后一页');"  >  > </a></span>
				</c:when>
				<c:otherwise>
					<a href="javascript:queryPage(${pageFinder.pageNo+1});"> > </a>
				</c:otherwise>
			</c:choose>
			
		</div>	
	</c:if>
	-->
    	 总计<span style='color:red;margin:0 2px'>${pageFinders.rowCount}</span>条记录  共<span style='color:red;margin:0 2px'>${pageFinders.pageCount}</span>页 每页<span style='color:red;margin:0 2px'>${pageFinders.pageSize}</span>条 &nbsp;&nbsp;
    	<input name="bp" id="bp" type="button" <c:if test="${!pageFinders.hasPrevious}">disabled="disabled"</c:if> class="page_button_first" value="第一页" onClick="queryPage(1)"/> 
    	<input name="bp" id="bp" type="button" <c:if test="${!pageFinders.hasPrevious}">disabled="disabled"</c:if> class="page_button_prev" value="上一页" onClick="queryPage(${pageFinders.pageNo-1})"/> 
    	<input name="bp" id="bp" type="button" <c:if test="${!pageFinders.hasNext}">disabled="disabled"</c:if> class="page_button_next" value="下一页" onClick="queryPage(${pageFinders.pageNo+1})"/> 
    	<input name="bp" id="bp" type="button" <c:if test="${!pageFinders.hasNext}">disabled="disabled"</c:if> class="page_button_last" value="最末页" onClick="queryPage(${pageFinders.pageCount})"/>&nbsp;&nbsp;
    	<input id="query.page" name="query.page" type="text" maxlength="8" value="${pageFinders.pageNo}" size="3" onkeyup="value=value.replace(/[^\d]/g,'')"/>&nbsp;&nbsp;
    	<input name="bj" id="bj" type="button" value="  Go  " onClick="queryPage(0);" />
    	&nbsp;&nbsp;&nbsp;&nbsp;
