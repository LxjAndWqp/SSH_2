<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆界面</title>

<!-- Javascript -->
<script src="Login/assets/js/jquery-1.8.2.min.js"></script>
<script src="Login/assets/js/supersized.3.2.7.min.js"></script>
<script src="Login/assets/js/supersized-init.js"></script>
<script src="Login/assets/js/scripts.js"></script>

<script src="<%=path %>/jquery_validation_1.16.0/lib/jquery.js" type="text/javascript"></script>
<script src="<%=path %>/jquery_validation_1.16.0/dist/jquery.validate.min.js" type="text/javascript"></script>
<script src="<%=path %>/jquery_validation_1.16.0/dist/localization/messages_zh.js" type="text/javascript"></script>
<!-- CSS -->
<link rel="stylesheet" href="Login/assets/css/reset.css">
<link rel="stylesheet" href="Login/assets/css/supersized.css">
<link rel="stylesheet" href="Login/assets/css/style.css">
<script>
 $().ready(function() {
	  $("#Myform").validate({
		    rules: {
		    	username:{
			        required: true,
			        rangelength:[3,15]
			      },
		    	password:  {
			        required: true,
			        rangelength:[3,15]
			      }
		    },
	  messages: {
		 	    username: {
			        required:"请输入用户名",
			        rangelength:"用户名长度3-15"
			      },
		 	   password:  {
			        required:"请输入密码",
			        rangelength:"密码长度3-15"
			      }
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
	<div class="page-container">
		<h1>电子办公系统登陆界面</h1>
		<s:form action="LoginAction" method="post" name="Myform" namespace="/">
			<input type="hidden" name="flag" id="flag" value="${flag }">
			<s:textfield name="username" class="userid" id="username"
				placeholder="用户名" />
			<s:password name="password" class="password" id="password"
				placeholder="密码" />

			<button type="submit">登陆</button>
			<div class="error">
				<span>+</span>
			</div>
		</s:form>
	</div>
</body>
</html>