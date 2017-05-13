<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>TOP</title>
<script src="<%=path%>/script/ztree_3.3/js/jquery-1.4.4.min.js"
	type="text/javascript"></script>
<script src="<%=path%>/bootstrap/bootstrap.min.js"
	type="text/javascript"></script>
<link href="<%=path%>/bootstrap/bootstrap.min.css" rel="stylesheet" />
<style>
div.container div.row div {
	margin: 5px 0px;
}

div.container div.row div {
	border: 0px solid gray;
	text-align: center;
	margin-top:0px;
}
span#top{
	font-size: 30px;
}
span#loginUser{
	font-size: 26px;
}
div#Bgdiv {
	background-image: url("<%=path%>/img/timg2.png");
	height: 100px;
}
</style>
<script type="text/javascript">
function Main(){
	var menu_href = "<%=path%>/MainAction!right";
	window.top.frames["rightFrame"].location.href = menu_href;
}
</script>
</head>
<body>
	<div id="Bgdiv">
		<div class="container">
			<div class="row">
				<div class="col-xs-12 "><span id ="top"  class="text-muted"><a onclick="Main()">电子办公管理系统</a></span></div>
			</div>
		</div>
		<div class="container">
			<div class="row">
				<div class="col-xs-4 "></div>
				<div class="col-xs-4 "></div>
				<div class="col-xs-4 ">
					<span id = "loginUser" >登陆用户：${userBean.username}</span>
					<a style="margin-top:-15px;" class ="btn btn-info btn-sm" href="<%=path%>/LoginAction!exit?userid=<s:property value="userid"/>">注销</a>
				</div>
			</div>
		</div>
	</div>





	<!--  
	登陆用户：${userBean.username}
	<a href="<%=path%>/LoginAction!exit?userid=<s:property value="userid"/>">注销</a>
	-->
</body>
</html>