<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script src="<%=path%>/jquery_validation_1.16.0/lib/jquery.js"
	type="text/javascript"></script>
<script
	src="<%=path%>/jquery_validation_1.16.0/dist/jquery.validate.min.js"
	type="text/javascript"></script>
<script
	src="<%=path%>/jquery_validation_1.16.0/dist/localization/messages_zh.js"
	type="text/javascript"></script>

<head>
<script src="<%=path%>/bootstrap/bootstrap.min.js"
	type="text/javascript"></script>
<script src="<%=path%>/jquery_validation_1.16.0/lib/jquery.js"
	type="text/javascript"></script>
<script
	src="<%=path%>/jquery_validation_1.16.0/dist/jquery.validate.min.js"
	type="text/javascript"></script>
<script
	src="<%=path%>/jquery_validation_1.16.0/dist/localization/messages_zh.js"
	type="text/javascript"></script>
<link href="<%=path%>/bootstrap/bootstrap.min.css" rel="stylesheet" />
<title>在线调查投票</title>
<script language="javascript">
	$().ready(function() {
		$("#form1").validate({
			rules : {
				options : "required"
			},
			messages : {
				options : "请至少选择一个选项"
			}
		});
	});
</script>
<style>
.error {
	color: red;
}
</style>
</head>
<s:form method="post" namespace="/" action="SurveyAction!votesave"
	id="form1" enctype="multipart/form-data">
	<table class="table table-bordered" width="100%" border="1"
		style="font-size: 12px;">
		<tr>
			<td align="center"><p>
					今日调查:<span class="text-danger"><s:property value="surveyTitle" /></span>
				</p></td>
			<s:hidden name="surveyId"></s:hidden>
		</tr>
		<tr>
			<s:if test="surveyType=='单选'">
				<td align="center"><pre><s:radio list="optionsArray" name="options" /></pre></td>
			</s:if>
			<s:if test="surveyType=='多选'">
				<td align="center"><pre><s:checkboxlist list="optionsArray" name="options"></s:checkboxlist></pre></td>
			</s:if>
		</tr>
		<tr>
			<td align="center" colspan="2"><Input class="btn btn-primary"
				type="submit" value="投票" /> <input class="btn btn-inverse"
				type="button" value="返回" onclick="window.history.go(-1);" /></td>
		</tr>
	</table>
</s:form>
</body>
</html>