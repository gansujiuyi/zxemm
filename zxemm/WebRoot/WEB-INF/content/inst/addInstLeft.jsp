<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/content/base/tag.inc" />
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>组织机构添加</title>
<meta name="Generator" content="EditPlus">
<meta name="Author" content="">
<meta name="Keywords" content="">
<meta name="Description" content="">
<script src="<%=path%>/js/jquery-1.6.js" type="text/javascript"></script>
<script type='text/javascript'
	src='<%=path%>/js/jquery.contextmenu.r2-min.js'></script>
<script src="<%=path%>/js/jquery.treeview.js" type="text/javascript"></script>

<link rel="stylesheet" href="<%=path%>/css/jquery.treeview.css" />
<link href="<%=path%>/css/Style2.css" rel="stylesheet" type="text/css" />
<style type='text/css'>
* {
	font-size: 12px;
}

.current {
	background: blue;
	color: white;
}
</style>
<script type='text/javascript'>
	/**					例子
						{"id":"1","text":"电脑整机","parentid":"0","children":[		// 第一级
							{"id":"2","text":"笔记本","parentid":"1","children":[			// 第二级
								{"id":"31","text":"SONY","parentid":"2","children":[]},		// 第三级
								{"id":"23","text":"LENOVO","parentid":"2","children":[]}
							]},
							{"id":"3","text":"上网本","parentid":"1","children":[]},
							{"id":"4","text":"平板电脑","parentid":"1","children":[]},
						]},
						{"id":"32","text":"手机","parentid":"0","children":[			// 第一级
							{"id":"33","text":"诺基亚","parentid":"32","children":[]}		// 第二级
						]}
	*/
	var dj_1 = 0, dj_2 = 0, dj_3 = 0;
// 	<c:set var="dj_1" value="0"></c:set>
// 	<c:set var="dj_2" value="0"></c:set>
// 	<c:set var="dj_3" value="0"></c:set>
	var treeNodes = [<c:forEach var="list_1" items="${instList}" varStatus="status_1" >
	<c:if test="${list_1.parentInstitutionId == 0 }">
	<c:if test="${(!empty fgf_1 && fgf_1=='true' ) || dj_1 != '0'}">,
	</c:if>{"id":"${list_1.institutionId }","text":"${list_1.institutionName }","parentid":"0","children":[<c:forEach var="list_2" items="${instList}" varStatus="status_2" ><c:if test="${list_2.parentInstitutionId == list_1.institutionId }"><c:if test="${(!empty fgf_2 && fgf_2=='true') || dj_2 != '0'}">,</c:if>{"id":"${list_2.institutionId }","text":"${list_2.institutionName }","parentid":"${list_1.institutionId }","children":[<c:forEach var="list_3" items="${instList}" varStatus="status_3" ><c:if test="${list_3.parentInstitutionId == list_2.institutionId }"><c:if test="${(!empty fgf_3 && fgf_3=='true') || dj_3 != '0'}">,</c:if>{"id":"${list_3.institutionId }","text":"${list_3.institutionName }","parentid":"${list_2.institutionId }","children":[],"noChildren":"1"}<c:set var="fgf_3" value="true"></c:set><c:set var="dj_3" value="${dj_3 + 1}"></c:set></c:if><c:if test="${list_3.parentInstitutionId != list_2.institutionId }"><c:set var="fgf_3" value="false"></c:set></c:if></c:forEach>]}<c:set var="fgf_2" value="true"></c:set><c:set var="dj_2" value="${dj_2 + 1}"></c:set><c:set var="dj_3" value="0"></c:set></c:if><c:if test="${list_2.parentInstitutionId != list_1.institutionId }"><c:set var="fgf_2" value="false"></c:set></c:if></c:forEach>]}<c:set var="fgf_1" value="true"></c:set><c:set var="dj_1" value="${dj_1 + 1}"></c:set><c:set var="dj_2" value="0"></c:set></c:if><c:if test="${list_1.parentInstitutionId != 0 }">
	<c:set var="fgf_1" value="false"></c:set></c:if></c:forEach>];	
	function createtree(d) {
        var list = new Array();
        $.each(d, function (i, n) {
            var cls = 'folder';
            if (n.children.length == 0)
                cls = 'file';

            list.push('<li><span class="' + cls + '" ref="' + n.id + '"  pid="'+n.parentid+'" id="'+n.id+'" ><A href="#">' + n.text + '</A></span>');
            if (n.children.length > 0) {
                list.push("<ul>");
                list.push(createtree(n.children));
                list.push("</ul>");
            }

            list.push("</li>");
        });
        return list.join("");
    }


	function initTree(){
		var sss = createtree(treeNodes);
		$('#product_tree').empty().append(sss);
		$('body').data('ptree', treeNodes);
		$('#product_tree .file').click(function () {
			var id = $(this).attr("ref");
			var txt = $(this).text();

			$('#product_tree span>a').removeClass('current');

			$(this).children('a').addClass('current');

			$('form').data("treeid", {"id":id,"txt":txt});

		});

		$('#product_tree span').contextMenu('myMenu1', {
			bindings: {
				'add': function(t) {
					var id = $(t).attr('id');
					var hcr="false";hcr = hasChild(treeNodes, id, hcr);
					if(hcr!="false"){alert("末级机构不能添加子机构");return false;}
					var pid = $(t).attr('pid');
					var txt = $(t).children('a').text();
					window.parent.frames['addInstRight'].location.href="acegi/addInstRight.do?inst.parentInstitutionId="+id+"&isAddOEditOper=true&inst.institutionName="+encodeURI(encodeURI(txt)) ;
				 },
				 'edit': function(t) {
					window.parent.frames['addInstRight'].location.href="acegi/updateInst.do?inst.institutionId="+$(t).attr('id')+"&isAddOEditOper=true" ;
				 },
				 'delete': function(t) {
				 	hasChile = false;
				 	checkChild(treeNodes, $(t).attr('id'));
				 	if(hasChile){
						if(confirm( "\"" + $(t).children('a').text() + "\"拥有子机构,是否继续且一并删除?" ))			 	
							window.parent.frames['addInstRight'].location.href="acegi/delInst.do?inst.institutionId="+$(t).attr('id')+"&isAddOEditOper=true" ;
				 	}else{
				 		if(confirm( "确定要删除吗?" ))
				 			window.parent.frames['addInstRight'].location.href="acegi/delInst.do?inst.institutionId="+$(t).attr('id')+"&isAddOEditOper=true" ;
				 	}
				 }
			}
		});

		$('#product_tree').treeview({
			animated: "fast",
			collapsed: true,
			control: "#mm1"
		});
	}

	$(function(){
		initTree();

		
	})
	var hasChile = false;	//	判断是否拥有子机构
	function checkChild(d, selId){
		$.each(d, function (i, n) {
			if( selId == n.parentid){
				hasChile = true;
				return false;
			}
            if (n.children.length > 0) {
               	checkChild(n.children, selId);
            }
        });
	}
	function hasChild(d, selId, hcrObj){
		$.each(d, function (i, n) {
			if( !hcrObj || hcrObj != "true" ){
				if (n.children.length > 0) {
	               	hcrObj = hasChild(n.children, selId, hcrObj);
	               	if( hcrObj && hcrObj == "true" ){
	               		return false;
	               	}
	            }
            }else
            	return false;
			if( selId == n.id){
				if(n.noChildren){hcrObj="true";return false;}
			}
			
        });
        return hcrObj;
	}
	</script>

</head>

<body>
	<div id="mainzone_one">
		<ul id="product_tree" class="filetree"></ul>

		<div class="contextMenu" id="myMenu1">
			<ul>
				<li id="add"><img src="<%=path%>/images/dtree/add.png" />
					添加子组织</li>
				<li id="edit"><img src="<%=path%>/images/dtree/edit.gif" />
					修改</li>
				<li id="delete"><img src="<%=path%>/images/dtree/delete.png" />
					删除</li>
			</ul>

		</div>
	</div>
</body>
</html>

