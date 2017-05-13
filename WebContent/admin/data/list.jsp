<%@ page language="java"   pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>数据列表</title>
 <script src="<%=path %>/script/jquery-3.2.0.min.js" type="text/javascript"></script>
 <script src="<%=path %>/bootstrap/bootstrap.min.js" type="text/javascript"></script>
 <link href="<%=path %>/bootstrap/bootstrap.min.css" rel="stylesheet"/>
<script language="javascript">
function deleteData(dataId){
	var flag  = window.confirm("确定删除数据");
	if (flag == true){
		var deleteURL = "<%=path%>/DataAction!deleteData?dataId="+dataId+"";
		window.location.href = deleteURL;
	}
}
</script>
</head>
<body>
	<div name = "tablediv" style="height: 550px;padding-top: 100px;">
	<table id = "datatable" class="table table-striped  table-hover">
		<thead>
			<th>序号</th>
			<th></th>
			<th>数据编码</th>
			<th>数据名称</th>
			<th>数据类型</th>
			<th>数据长度</th>
			<th>是否空值</th>
			<th>是否有范围</th>
			<th>父类</th>
			<th>操作<a class="btn btn-info btn-xs" href="<%=path%>/DataAction!add">添加</a>
			<input class="btn btn-inverse btn-xs" type="button" value="返回" onclick="window.history.go(-1);" /></th>
		</thead>
		<s:iterator value="dataList" status="myStatus">
			<tr>
				<td><s:property value="#myStatus.count"/> </td>
				<td><input type="checkbox" name="data_check" value="<s:property value="dataId"/>"> </td>
				<td><s:property value="dataId"/></td>
				<td><s:property value="dataName"/></td>
				<td><s:property value="dataType"/></td>
				<td><s:property value="dataLength"/></td>
				<td><s:property value="isEmpty"/></td>
				<td><s:property value="isScope"/></td>
				<td><s:property value="parentId"/></td>
				<td>
				<a class="btn btn-warning btn-xs" href="<%=path%>/DataAction!edit?dataId=<s:property value="dataId"/>">修改</a>
				
				<a class="btn btn-danger btn-xs"  href="javascript:void(0);" onclick="deleteData('<s:property value="dataId"/>');">删除</a></td>
			</tr>
		</s:iterator>
	</table>
	</div>
	
	<div class="gridItem"
		style="padding: 10px; width: 300px; float: left; text-align: left; height: 20px; font-size: 15px;">
		共有 <span id="spanTotalInfor"></span> 条记录 当前第<span id="spanPageNum"></span>页
		共<span id="spanTotalPage"></span>页
	</div>
	<div class="gridItem"
		style="margin-left: 50px; padding: 10px; width: 400px; float: right; text-align: center; height: 20px; vertical-align: middle; font-size: 15px;">
		<span id="spanFirst">首页</span> <span id="spanPre">上一页</span> <span
			id="spanInput"
			style="margin: 0px; padding: 0px 0px 4px 0px; height: 100%;">
			第<input id="Text1" type="text" class="TextBox" onkeyup="changepage()"
			style="height: 24px; text-align: center; width: 30px;" />页
		</span> <span id="spanNext">下一页</span> <span id="spanLast">尾页</span>
	</div>
	<!-- 分页的JS -->
	<script type="text/javascript">
		var theTable = document.getElementById("datatable");
		var txtValue = document.getElementById("Text1").value;
		function changepage() {
			var txtValue2 = document.getElementById("Text1").value;
			if (txtValue != txtValue2) {
				if (txtValue2 > pageCount()) {
				} else if (txtValue2 <= 0) {
				} else if (txtValue2 == 1) {
					first();
				} else if (txtValue2 == pageCount()) {
					last();
				} else {
					hideTable();
					page = txtValue2;
					pageNum2.value = page;
					currentRow = pageSize * page;
					maxRow = currentRow - pageSize;
					if (currentRow > numberRowsInTable)
						currentRow = numberRowsInTable;
					for (var i = maxRow; i < currentRow; i++) {
						theTable.rows[i].style.display = '';
					}
					if (maxRow == 0) {
						preText();
						firstText();
					}
					showPage();
					nextLink();
					lastLink();
					preLink();
					firstLink();
				}
				txtValue = txtValue2;
			}
		}
	</script>
	<!-- 引入分页js -->
	<script type="text/javascript" src="<%=path%>/script/pagging.js"></script>
</body>
</html>