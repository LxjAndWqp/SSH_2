<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>用户添加</title>
<script src="<%=path %>/script/ztree_3.3/js/jquery-1.4.4.min.js" type="text/javascript"></script>
<script src="<%=path %>/bootstrap/bootstrap.min.js" type="text/javascript"></script>
<script src="<%=path %>/jquery_validation_1.16.0/lib/jquery.js" type="text/javascript"></script>
<script src="<%=path %>/jquery_validation_1.16.0/dist/jquery.validate.min.js" type="text/javascript"></script>
<script src="<%=path %>/jquery_validation_1.16.0/dist/localization/messages_zh.js" type="text/javascript"></script>
 <link href="<%=path %>/bootstrap/bootstrap.min.css" rel="stylesheet"/>
 <script>
$().ready(function() {
	  $("#Myform").validate({
		    rules: {
		    	userid: "required",   
		    	username: "required",
		    	truename: "required",
		    	userage: {
		    		digits:true
			      },
			    birthday: {
			    	dateISO:true
			      },
		    	mail: {
			        email: true
			      }
		    },
	  messages: {
		  		  userid: "请输入您的用户编码",
		  		  username: "请输入您的用户名",
		  		  truename: "请输入您的真实姓名",
		  		  userage: "请输入正确年龄",
		  		  birthday:"请输入正确格式的日期，例如：2017-01-01，2017/01/01",
		  		  mail: "请输入一个正确的邮箱"
		  		  
			    }
			});
		});
</script>
<style>
.error{
	color:red;
}
</style>
</head>
<body>
	<s:form  method="post" namespace="/" action="UserAction!save" id="Myform">
		<table class= "table table-bordered">
			<tr>
				<td align="right"><span class="glyphicon glyphicon-asterisk text-danger"></span>用户编码:</td>
				<td><s:textfield class="form-control selectpicker" style="width:30%" name="userid"  /></td>
			</tr>
			<tr>
				<td align="right"><span class="glyphicon glyphicon-asterisk text-danger"></span>用户名:</td>
				<td><s:textfield class="form-control selectpicker" style="width:30%" name="username" /></td>
			</tr>
			<tr>
				<td align="right"><span class="glyphicon glyphicon-asterisk text-danger"></span>密码:</td>
				<td><s:textfield class="form-control selectpicker" style="width:30%" name="password_temp" value = "888888" disabled="true" /></td>
				<s:hidden name= "password" value = "888888" />
			</tr>
			<tr>
				<td align="right"><span class="glyphicon glyphicon-asterisk text-danger"></span>真实姓名:</td>
				<td><s:textfield class="form-control selectpicker" style="width:30%" name="truename" /></td>
			</tr>
			<tr>
				<td align="right">性别:</td>
				<td>
					<s:radio list="#{'1':'男','0':'女'}" name="usersex" value="1"/>
				</td>
			</tr>
			<tr>
				<td align="right">年龄:</td>
				<td>
					<s:select class="form-control selectpicker" style="width:30%" list="#ageList" name="userage"></s:select>
				</td>
			</tr>
			<tr>
				<td align="right">部门:</td>
				<td>
					<s:select class="form-control selectpicker" style="width:30%" list="#deptList" name="deptId"
						listKey="dataName" listValue="dataName">
					</s:select>
				</td>
			</tr>
			<tr>
				<td align="right">薪水:</td>
				<td><s:textfield class="form-control selectpicker" style="width:30%" name="salary" /></td>
			</tr>
			<tr>
				<td align="right">电话号码:</td>
				<td><s:textfield class="form-control selectpicker" style="width:30%" name="telphone" /></td>
			</tr>
			<tr>
				<td align="right">地址:</td>
				<td><s:textfield class="form-control selectpicker" style="width:30%" name="address" /></td>
			</tr>
			<tr>
				<td align="right">出生日期:</td>
				<td><s:textfield class="form-control selectpicker" style="width:30%" name="birthday" /></td>
			</tr>
			<tr>
				<td align="right">邮件地址:</td>
				<td><s:textfield class="form-control selectpicker" style="width:30%" name="mail" /></td>
			</tr>

			<tr>
				<td align="right"><span class="glyphicon glyphicon-asterisk text-danger"></span>用户权限:</td>
				<td><s:checkboxlist  list="#roleList" name="user_roleList"
						listKey="roleId" listValue="roleName" /></td>
			</tr>
			<tr>
				<td colspan="2"><Input class="btn btn-primary" type="submit" value="保存数据" /> <Input
					class= "btn btn-info" type="reset" value="重新输入" />
					<input class="btn btn-inverse" type="button" value="返回" onclick="window.history.go(-1);" /></td>
			</tr>
		</table>

	</s:form>
</body>
</html>