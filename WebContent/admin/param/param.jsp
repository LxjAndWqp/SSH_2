<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="<%=path%>/script/ztree_3.3/css/zTreeStyle/zTreeStyle.css" type="text/css" />
<script type="text/javascript" src="<%=path%>/script/jquery-3.2.0.min.js"></script>
<script type="text/javascript" src="<%=path%>/script/ztree_3.3/js/jquery.ztree.all-3.3.min.js"></script>
<script type="text/javascript" src="<%=path%>/script/jquery.json-2.4.js"></script>
<script src="<%=path %>/bootstrap/bootstrap.min.js" type="text/javascript"></script>
 <link href="<%=path %>/bootstrap/bootstrap.min.css" rel="stylesheet"/>
<script language="javascript">
function saveData(){
	var form1 = document.getElementById("form1");
	form1.submit();
}
</script>
<title>信息提醒</title>
</head>
<body>
<s:form method="post" namespace="/" action="PersonInfoAction!save" id="form1">
	<table class= "table table-bordered" width="100%" border="1" style="font-size:12px;">
		<tr>
			<td align="right">新公告：</td>
			<td>
				<s:radio class="form-control selectpicker" style="width:30%" list="#{'no':'不提醒','yes':'提醒'}" name="notice"/>
			</td>
		</tr>	
		<tr>
			<td align="right">新邮件：</td>
			<td>
				<s:radio class="form-control selectpicker" style="width:30%" list="#{'no':'不提醒','yes':'提醒'}" name="mail"/>
			</td>
		</tr>
		<tr>
			<td align="right">显示类型：</td>
			<td>
				<s:radio class="form-control selectpicker" style="width:30%" list="#{'no':'不显示','yes':'显示'}" name="display"/>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input class="btn btn-primary" type="button" value="保存设置" onclick="saveData();"/>
				
				<s:hidden name="userid"/>
			</td>
		</tr>									
	</table>
</s:form>
</body>
</html>