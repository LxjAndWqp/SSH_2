<%@ page language="java" pageEncoding="UTF-8"%>
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
<script src="<%=path%>/script/jquery-3.2.0.min.js"
	type="text/javascript"></script>
<script src="<%=path%>/bootstrap/bootstrap.min.js"
	type="text/javascript"></script>
<link href="<%=path%>/bootstrap/bootstrap.min.css" rel="stylesheet" />
<script language="javascript">
function deleteUser(userid){
	var flag  = window.confirm("确定删除角色的数据");
	if (flag == true){
		var deleteURL = "<%=path%>/UserAction!deleteUser?userid=" + userid
					+ "";
			window.location.href = deleteURL;
		}
	}
function search(){
	var form1 = document.getElementById("Myform");
	form1.submit();
}
</script>

</head>
<body>
	<div name = "searchdiv" style="height: 50px;" class="bg-info" >
		<form action="UserAction!search" method="post" id = "Myform" style="padding-top: 20px;padding-left: 30px;" >
			<span class="col-xs-1 ">用户名</span><input class="col-xs-2 " name="usernameCondition" value="${ usernameCondition}" />
			<span class="col-xs-1 ">真实姓名</span><input class="col-xs-2 " name="truenameCondition" value="${ truenameCondition}" />
			<span class="col-xs-1 ">部门</span><input class="col-xs-2 " name="deptCondition" value="${ deptCondition}" />
			<input type="submit" value="搜索" />
		</form>
	</div>
	<div name="tablediv" style="height: 550px; padding-top: 0px;">
		<table id="usertable"
			class="table table-striped  table-hover">
			<thead>
				<th>序号</th>
				<th></th>
				<th>用户编码</th>
				<th>用户名称</th>
				<th>真实姓名</th>
				<th>性别</th>
				<th>年龄</th>
				<th>部门</th>
				<th>薪水</th>
				<th>电话号码</th>
				<th>地址</th>
				<th>出生日期</th>
				<th>邮件地址</th>
				<th>操作
					<s:if test="isadmin == no">
					<a class="btn btn-info btn-xs"
					href="<%=path%>/UserAction!add">添加</a> 
					</s:if>
					<input
					class="btn btn-inverse btn-xs" type="button" value="返回"
					onclick="window.history.go(-1);" /></th>
			</thead>
			<s:iterator value="userList" status="myStatus">
				<tr>
					<td><s:property value="#myStatus.count" /></td>
					<td><input type="checkbox" name="user_check"
						value="<s:property value="userid"/>"></td>
					<td><s:property value="userid" /></td>
					<td><s:property value="username" /></td>
					<td><s:property value="truename" /></td>
					<td><s:if test="usersex==1">男</s:if> <s:if test="usersex==0">女</s:if>
					</td>
					<td><s:property value="userage" /></td>
					<td><s:property value="deptId" /></td>
					<td><s:property value="salary" /></td>
					<td><s:property value="telphone" /></td>
					<td><s:property value="address" /></td>
					<td><s:property value="birthday" /></td>
					<td><s:property value="mail" /></td>
					<td>
						<s:if test="isadmin == no">
							<a class="btn btn-warning btn-xs"
								href="<%=path%>/UserAction!edit?userid=<s:property value="userid"/>">修改</a>

							<a class="btn btn-danger btn-xs" href="javascript:void(0);"
								onclick="deleteUser('<s:property value="userid"/>');">删除</a>
						</s:if>
					</td>
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
		var theTable = document.getElementById("usertable");
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