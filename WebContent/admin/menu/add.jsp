<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script src="<%=path %>/bootstrap/bootstrap.min.js" type="text/javascript"></script>
<script src="<%=path %>/jquery_validation_1.16.0/lib/jquery.js" type="text/javascript"></script>
<script src="<%=path %>/jquery_validation_1.16.0/dist/jquery.validate.min.js" type="text/javascript"></script>
<script src="<%=path %>/jquery_validation_1.16.0/dist/localization/messages_zh.js" type="text/javascript"></script>
 <link href="<%=path %>/bootstrap/bootstrap.min.css" rel="stylesheet"/>
 <script>
$().ready(function() {
	  $("#Myform").validate({
		    rules: {
		    	menuId: "required",   
		    	menuName: "required",
		    	parentid:  {
			        required: true,
			        digits:true
			      },
		    	grade:  {
			        required: true,
			        digits:true
			      },
		    	isleaf: {
			        required: true,
			        range:[0,1]
			      },
		    },
	  messages: {
		 		  menuId: "请输入菜单编码",
		 		  menuName: "请输入菜单名",
		 		  parentid:{
				        required: "请输入菜单父节点",
				        digits:"请输入正确的父节点"
				      }, 
		 		  grade: {
				        required: "请输入菜单级别",
				        digits:"请输入正确的菜单级别"
				      }, 
		 		  isleaf:{
				        required: "请输入是否叶节点",
				        range:"请输入正确的叶节点（1是，0否）"
				      }, 
		  		  
			    }
			});
		});
</script>
<style>
.error{
	color:red;
}
</style>
<head>
<title>菜单添加</title>
</head>
<body>
	<s:form method="post" namespace="/" action="MenuAction!save"
		id="Myform">
		<table class= "table table-bordered">
			<tr>
				<td align="right"><span class="glyphicon glyphicon-asterisk text-danger"></span>菜单编码</td>
				<td><s:textfield class="form-control selectpicker" style="width:30%" name="menuId" /></td>
			</tr>
			<tr>
				<td align="right"><span class="glyphicon glyphicon-asterisk text-danger"></span>菜单名</td>
				<td><s:textfield class="form-control selectpicker" style="width:30%" name="menuName" /></td>
			</tr>
			<tr>
				<td align="right">菜单链接</td>
				<td><s:textfield class="form-control selectpicker" style="width:30%" name="menuHref" /></td>
			</tr>
			<tr>
				<td align="right">菜单目的</td>
				<td><s:textfield class="form-control selectpicker" style="width:30%" name="menuTarget" /></td>
			</tr>
			<tr>
				<td align="right"><span class="glyphicon glyphicon-asterisk text-danger"></span>父节点</td>
				<td><s:textfield class="form-control selectpicker" style="width:30%" name="parentid" /></td>
			</tr>
			<tr>
				<td align="right"><span class="glyphicon glyphicon-asterisk text-danger"></span>级别</td>
				<td><s:textfield class="form-control selectpicker" style="width:30%" name="grade" /></td>
			</tr>
			<tr>
				<td align="right"><span class="glyphicon glyphicon-asterisk text-danger"></span>是否叶子节点</td>
				<td><s:textfield class="form-control selectpicker" style="width:30%" name="isleaf" /></td>
			</tr>
			<tr>
				<td colspan="2">
					<Input class="btn btn-primary" type="submit" value="保存数据" />
					<Input class= "btn btn-info" type="reset" value="重新输入" /> 
					<input class="btn btn-inverse" type="button" value="返回" onclick="window.history.go(-1);" />
				</td>
			</tr>
		</table>

	</s:form>
</body>
</html>