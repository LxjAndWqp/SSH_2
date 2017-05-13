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
<title>用户列表</title>
 <script src="<%=path %>/script/jquery-3.2.0.min.js" type="text/javascript"></script>
 <script src="<%=path %>/bootstrap/bootstrap.min.js" type="text/javascript"></script>
 <link href="<%=path %>/bootstrap/bootstrap.min.css" rel="stylesheet"/>
<script language="javascript">
function deleteNotice(noticeId){
	var flag  = window.confirm("确定删除该公告");
	if (flag == true){
		var deleteURL = "<%=path%>/NoticeAction!del?noticeId="+noticeId+"";
		window.location.href = deleteURL;
	}
}
</script>
</head>
<body>
	<div name = "tablediv" style="height: 550px;padding-top: 100px;">
	<table id="noticetable" class="table table-striped  table-hover" >
		<thead>
			<th>序号</th>
			<th></th>
			<th>公告ID</th>
			<th>公告标题</th>
			<th>公告添加人</th>
			<th>添加时间</th>
			<th>状态</th>
			<th>操作<a class="btn btn-info btn-xs" href="<%=path%>/NoticeAction!add">添加</a>
			<input class="btn btn-inverse btn-xs" type="button" value="返回" onclick="window.history.go(-1);" /></th>
		</thead>
		<s:iterator value="noticeList" status="myStatus">
			<tr>
				<td><s:property value="#myStatus.count"/> </td>
				<td><input type="checkbox" name="notice_check" value="<s:property value="noticeId"/>"> </td>
				<td><s:property value="noticeId"/></td>
				<td><a href="<%=path%>/NoticeAction!look?noticeId=<s:property value="noticeId"/>"><s:property value="noticeTitle"/></a></td>
				<td><s:property value="noticeAdduser"/></td>
				<td><s:property value="noticeAddtime"/></td>
				<s:if test="isApprove==0">
					<td class="text-info">未审核</td>
				<td>
				<a  class="btn btn-warning btn-xs" href="<%=path%>/NoticeAction!edit?noticeId=<s:property value="noticeId"/>">修改</a>
				<a  class="btn btn-success btn-xs"  href="<%=path%>/NoticeAction!check?noticeId=<s:property value="noticeId"/>">审核</a>
				<a  class="btn btn-danger btn-xs" href="javascript:void(0);" onclick="deleteNotice('<s:property value="noticeId"/>');">删除</a>
				</td>
				</s:if>
				<s:if test="isApprove==1">
					<td class="text-success">审核已通过</td>
				<td>
					<a class="btn btn-danger btn-xs" href="javascript:void(0);" onclick="deleteNotice('<s:property value="noticeId"/>');">删除</a>
				</td>
				</s:if>
				<s:if test="isApprove==2">
					<td class="text-danger">审核未通过</td>
				<td>
					<a class="btn btn-danger btn-xs" href="javascript:void(0);" onclick="deleteNotice('<s:property value="noticeId"/>');">删除</a>
				</td>
				</s:if>
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
		var theTable = document.getElementById("noticetable");
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