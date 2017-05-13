<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title >电子办公系统</title>

</head>

<frameset rows="80,*" cols="*" framespacing="1" frameborder="yes" border="0" bordercolor="#000000">
  <frame src="<%=path%>/admin/main/top.jsp" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" title="topFrame" />
  <frameset rows="*" cols="180,*" framespacing="1" frameborder="yes" border="0" bordercolor="#000000">
    <frame src="<%=path%>/MainAction!left" name="leftFrame" scrolling="No" noresize="noresize" id="leftFrame" title="leftFrame" />
    <frame src="<%=path%>/MainAction!right" name="rightFrame" id="rightFrame" title="mainFrame" />
  </frameset>
</frameset>
<noframes><body>
</body>
</noframes>

</html>