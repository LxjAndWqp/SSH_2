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
<title>欢迎登陆</title>
</head>
<body>
	<script type="text/javascript">
		var flag = window.confirm("欢迎登陆！"+ '${userBean.username }');
		if(flag==true)
			window.location.href = "<%=path%>/MainAction!list?userid="+'${userid }';
		if(flag==false)
			window.location.href = "<%=path%>/LoginAction!exit";
	</script>
</body>
</html>