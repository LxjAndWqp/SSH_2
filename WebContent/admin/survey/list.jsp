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
	
function deleteSurvey(surveyId){
	var flag  = window.confirm("确定删除该调查");
	if (flag == true){
		var deleteURL = "<%=path%>/SurveyAction!deleteSurvey?surveyId="
					+ surveyId + "";
			window.location.href = deleteURL;
		}
	}
</script>
</head>
<body>
	<div name="searchdiv" style="height: 50px;margin-top: 20px;padding-top: -10px;" class="bg-info">
		<form action="SurveyAction!search" method="post" id="Myform"
			style="padding-top: 20px; padding-left: 30px;">
			<span class="col-xs-1 ">标题</span><input class="col-xs-2 "
				name="surveyTitleCondition" value="${ surveyTitleCondition}" /> <span
				class="col-xs-1 ">开始时间</span><input class="col-xs-2 "
				name="beginTimeCondition" value="${ beginTimeCondition}" /> <span
				class="col-xs-1 ">结束时间</span><input class="col-xs-2 "
				name="endTimeCondition" value="${ endTimeCondition}" /> <input
				type="submit" value="搜索" />
		</form>
	</div>
	<div name="tablediv" style="height: 550px; padding-top: 50px;">
		<table id="surveytable" class="table table-striped  table-hover">
			<thead>
				<th>序号</th>
				<th></th>
				<th>标题</th>
				<th>开始时间</th>
				<th>结束时间</th>
				<th>类型</th>
				<th>操作<a class="btn btn-info btn-xs"
					href="<%=path%>/SurveyAction!add">添加</a> <input
					class="btn btn-inverse btn-xs" type="button" value="返回"
					onclick="window.history.go(-1);" /></th>
			</thead>
			<s:iterator value="surveyList" status="myStatus">
				<tr>
					<td><s:property value="#myStatus.count" /></td>
					<td><input type="checkbox" name="survey_check"
						value="<s:property value="surveyId"/>"></td>
					<td><s:property value="surveyTitle" /></td>
					<td><s:property value="beginTime" /></td>
					<td><s:property value="endTime" /></td>
					<td><s:property value="surveyType" /></td>

					<s:if test="beginTime.equals('') && endTime.equals('')">
						<td><a class="btn btn-warning btn-xs"
							href="<%=path%>/SurveyAction!edit?surveyId=<s:property value="surveyId"/>">编辑</a>

							<a class="btn btn-warning btn-xs"
							href="<%=path%>/SurveyAction!begin?surveyId=<s:property value="surveyId"/>">开始</a>
							<a class="btn btn-primary btn-xs"
							href="<%=path%>/SurveyAction!result?surveyId=<s:property value="surveyId"/>">结果</a>
							<a class="btn btn-danger btn-xs" href="javascript:void(0);"
							onclick="deleteSurvey('<s:property value="surveyId"/>');">删除</a></td>
					</s:if>


					<s:if test="!beginTime.equals('') && endTime.equals('')">
						<td><a class="btn btn-warning btn-xs"
							href="<%=path%>/SurveyAction!edit?surveyId=<s:property value="surveyId"/>">编辑</a>

							<a class="btn btn-success btn-xs"
							href="<%=path%>/SurveyAction!end?surveyId=<s:property value="surveyId"/>">结束</a>
							<a class="btn btn-info btn-xs"
							href="<%=path%>/SurveyAction!vote?surveyId=<s:property value="surveyId"/>">投票</a>
							<a class="btn btn-primary btn-xs"
							href="<%=path%>/SurveyAction!result?surveyId=<s:property value="surveyId"/>">结果</a>
							<a class="btn btn-danger btn-xs" href="javascript:void(0);"
							onclick="deleteSurvey('<s:property value="surveyId"/>');">删除</a></td>
					</s:if>


					<s:if test="!beginTime.equals('') && !endTime.equals('')">
						<td>
							<a class="btn btn disabled btn-xs">已结束</a> <a
							class="btn btn-primary btn-xs"
							href="<%=path%>/SurveyAction!result?surveyId=<s:property value="surveyId"/>">结果</a>
							<a class="btn btn-danger btn-xs" href="javascript:void(0);"
							onclick="deleteSurvey('<s:property value="surveyId"/>');">删除</a></td>
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
		var theTable = document.getElementById("surveytable");
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