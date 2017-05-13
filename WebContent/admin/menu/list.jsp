<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
%>
<html>
<head>
<script type="text/javascript"
	src="<%=path%>/script/jqtreetable/Treetable_files/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/script/jqtreetable/Treetable_files/jqtreetable.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=path%>/script/jqtreetable/Treetable_files/jqtreetable.css" />
<script type="text/javascript" src="<%=path%>/script/jquery.json-2.4.js"></script>
<script src="<%=path %>/bootstrap/bootstrap.min.js" type="text/javascript"></script>
 <link href="<%=path %>/bootstrap/bootstrap.min.css" rel="stylesheet"/>
<script type="text/javascript" charset="utf-8">
	$(function(){
		var jsonStr = '${requestScope.jsonStr}';
		var jsonArrayObj = jQuery.evalJSON(jsonStr);
		var map = jsonArrayObj;
		
		//声明参数选项
		var options = {
					openImg: "<%=path%>/script/jqtreetable/images/TreeTable/tv-collapsable.gif", 
					shutImg: "<%=path%>/script/jqtreetable/images/TreeTable/tv-expandable.gif", 
					leafImg: "<%=path%>/script/jqtreetable/images/TreeTable/tv-item.gif", 
					lastOpenImg: "<%=path%>/script/jqtreetable/images/TreeTable/tv-collapsable-last.gif", 
					lastShutImg: "<%=path%>/script/jqtreetable/images/TreeTable/tv-expandable-last.gif", 
					lastLeafImg: "<%=path%>/script/jqtreetable/images/TreeTable/tv-item-last.gif", 
					vertLineImg: "<%=path%>/script/jqtreetable/images/TreeTable/vertline.gif", 
					blankImg: "<%=path%>/script/jqtreetable/images/TreeTable/blank.gif", 
					collapse: false, column: 0, 
					striped: true, highlight: true,  
					state:false
		};
		
	   if(map!=null&&map.length>0)
		{
		  
		  $("#treet1").jqTreeTable(map, options);
		}
	  }
);
</script>
<script language="javascript">
function deleteMenu(menuId){
	var flag  = window.confirm("确定删除菜单的数据");
	if (flag == true){
		var deleteURL = "<%=path%>/MenuAction!deleteMenu?menuId="
					+ menuId + "";
			window.location.href = deleteURL;
		}
	}
</script>
</head>
<body>
	<table class="table table-hover">
		<thead>
			<tr>
				<th width="20%">菜单编码</th>
				<th width="20%">菜单名称</th>
				<th width="25%">菜单链接</th>
				<th>级别</th>
				<th>是否细级</th>
				<th>操作<a class="btn btn-info btn-xs" href="<%=path%>/MenuAction!add">添加</a>
				<input class="btn btn-inverse btn-xs" type="button" value="返回" onclick="window.history.go(-1);" />
				</th>
			</tr>
		</thead>
		<tbody id="treet1">
			<s:iterator value="#menuList" var="menuBean">
				<tr>
					<td><s:property value="menuId" /></td>
					<td><s:property value="menuName" escape="false" /></td>
					<td><s:property value="menuHref" /></td>
					<td><s:property value="grade" /></td>
					<td><s:property value="isleaf" /></td>
					<td><a class="btn btn-warning btn-xs" href="<%=path%>/MenuAction!edit?menuId=<s:property value="menuId"/>">修改</a>
						<a class="btn btn-danger btn-xs" href="javascript:void(0);" onclick="deleteMenu('<s:property value="menuId"/>');">删除</a></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</body>

</html>