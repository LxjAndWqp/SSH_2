<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="<%=path %>/bootstrap/bootstrap.min.js"
	type="text/javascript"></script>
<script src="<%=path %>/jquery_validation_1.16.0/lib/jquery.js"
	type="text/javascript"></script>
<script
	src="<%=path %>/jquery_validation_1.16.0/dist/jquery.validate.min.js"
	type="text/javascript"></script>
<script
	src="<%=path %>/jquery_validation_1.16.0/dist/localization/messages_zh.js"
	type="text/javascript"></script>
<link href="<%=path %>/bootstrap/bootstrap.min.css" rel="stylesheet" />
<title>在线调查添加</title>
<script language="javascript">
	var i = 1;
	function add() {

		document.getElementById("d").innerHTML += '<p id="p_'+i+'">内容<s:textfield name="options" /><input class="btn btn-danger btn-xs" type="button" value="删除"  onclick="del('
				+ i + ')"/></p>';
		i = i + 1;
	}
	function del(o) {
		document.getElementById("d").removeChild(
				document.getElementById("p_" + o));
	}
	$().ready(function() {
		$("#form1").validate({
			rules : {
				surveyTitle : "required"
			},
			messages : {
				surveyTitle : "请输入您的调查标题"
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
<s:form method="post" namespace="/" action="SurveyAction!save"
	id="form1" enctype="multipart/form-data">
	<table class="table table-bordered" width="100%" border="1"
		style="font-size: 12px;">
		<tr>
			<td width="25%"><span
				class="glyphicon glyphicon-asterisk text-danger"></span>标题:</td>
			<td><s:textfield class="form-control selectpicker"
					style="width:30%" name="surveyTitle" /> <s:hidden name="beginTime" />
				<s:hidden name="endTime" /></td>
		</tr>
		<tr>
			<td><span class="glyphicon glyphicon-asterisk text-danger"></span>类型:</td>
			<td><s:select class="form-control selectpicker"
					style="width:30%" list="{'单选','多选'}" name="surveyType"></s:select>
			</td>
		</tr>
		<tr>
			<td>投票人：</td>
			<td><s:radio list="#{'1':'所有人','0':'选择'}" name="voters"
					value="1" /> <s:textfield class="form-control selectpicker"
					style="width:30%" /></td>
		</tr>
		<tr>
			<td>查看人：</td>
			<td><s:radio list="#{'1':'所有人','0':'选择'}" name="lookPeople"
					value="1" /> <s:textfield class="form-control selectpicker"
					style="width:30%" /></td>
		</tr>
		<tr id="d">
			<td>内容<input class="btn btn-info btn-xs" type="button" id="b"
				value="添加" onclick="add()" /></td>

		</tr>
		<tr>
			<td colspan="2"><Input class="btn btn-primary" type="submit"
				value="保存数据" /> <Input class="btn btn-info" type="reset"
				value="重新输入" /> <input class="btn btn-inverse" type="button"
				value="返回" onclick="window.history.go(-1);" /></td>
		</tr>
	</table>
</s:form>
</body>
</html>