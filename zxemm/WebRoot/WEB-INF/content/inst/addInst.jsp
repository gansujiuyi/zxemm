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
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js" type="text/javascript"></script>
<script type='text/javascript' src='<%=path %>/js/jquery.contextmenu.r2-min.js'></script>
	<script src="<%=path %>/js/jquery.treeview.js" type="text/javascript"></script>
	
	<link rel="stylesheet" href="<%=path %>/css/jquery.treeview.css" />
	<style type='text/css'>
		*{font-size:12px;}
		.current{ background:blue;color:white;}
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
	var treeNodes = [
						<c:forEach var="list_1" items="${instList}" varStatus="status_1" >
							<c:if test="${list_1.parentInstitutionId == '' }">
								<c:if test="${!empty fgf_1 && fgf_1=='true'}">,</c:if>
								{"id":"${list_1.institutionNo }","text":"${list_1.institutionName }","parentid":"0","children":[
								<c:forEach var="list_2" items="${instList}" varStatus="status_2" >
									<c:if test="${list_2.parentInstitutionId == list_1.institutionId }">
										<c:if test="${!empty fgf_2 && fgf_2=='true'}">,</c:if>
										{"id":"${list_2.institutionNo }","text":"${list_2.institutionName }","parentid":"${list_1.institutionId }","children":[
										<c:forEach var="list_3" items="${instList}" varStatus="status_3" >
											<c:if test="${list_3.parentInstitutionId == list_2.institutionId }">
												<c:if test="${!empty fgf_3 && fgf_3=='true'}">,</c:if>
												{"id":"${list_3.institutionNo }","text":"${list_3.institutionName }","parentid":"${list_2.institutionId }","children":[]}
												<c:set var="fgf_3" value="true"></c:set>
											</c:if>
											<c:if test="${list_3.parentInstitutionId != list_2.institutionId }">
												<c:set var="fgf_3" value="false"></c:set>
											</c:if>
										</c:forEach>
										]}
										<c:set var="fgf_2" value="true"></c:set>
									</c:if>
									<c:if test="${list_2.parentInstitutionId != list_1.institutionId }">
										<c:set var="fgf_2" value="false"></c:set>
									</c:if>
								</c:forEach>
								]}
								<c:set var="fgf_1" value="true"></c:set>
							</c:if>
							<c:if test="${list_1.parentInstitutionId != '' }">
								<c:set var="fgf_1" value="false"></c:set>
							</c:if>
						</c:forEach>
						
					];
	
	function createtree(d) {
        var list = '';
        $.each(d, function (i, n) {
            var cls = 'folder';
            if (n.children.length == 0)
                cls = 'file';

            list += '<li><span class="' + cls + '" ref="' + n.id + '"  pid="'+n.parentid+'" id="'+n.id+'" ><A href="#">' + n.text + '</A></span>';
            if (n.children.length > 0) {
                list += "<ul>";
                list += createtree(n.children);
                list += "</ul>"
            }

            list += "</li>";
        });
        return list;
    }


	function initTree(){
		$('#product_tree').empty().append(createtree(treeNodes));
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
					var pid = $(t).attr('pid');
					var txt = $(t).children('a').text();
					alert('This node info:\n id:'+id+'\n 父ID：'+ pid+'\n文本：'+txt);
				 },
				 'edit': function(t) {
					alert('Trigger was '+t.id+'\nAction was Email');
				 },
				 'delete': function(t) {
					alert('Trigger was '+t.id+'\nAction was Delete');
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

	</script>

 </head>

 <body>
  
	<ul id="product_tree" class="filetree"></ul>

	<div class="contextMenu" id="myMenu1">
      <ul>
        <li id="add"><img src="<%=path %>/images/dtree/add.png" /> 添加</li>
        <li id="edit"><img src="<%=path %>/images/dtree/edit.gif" /> 修改</li>
        <li id="delete"><img src="<%=path %>/images/dtree/delete.png" /> 删除</li>
      </ul>

    </div>


	
 </body>
</html>

