<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>角色列表</title>
<script src="<%=path%>/script/ztree_3.3/js/jquery-1.4.4.min.js"
	type="text/javascript"></script>
<script src="<%=path%>/bootstrap/bootstrap.min.js"
	type="text/javascript"></script>
<link href="<%=path%>/bootstrap/bootstrap.min.css" rel="stylesheet" />
</head>

<body>
<s:if test="notice == 'yes'">
	<div class="panel panel-info">
		<div class="panel-heading">公告</div>
		<div class="panel-body">
			<table class="table table-hover">
				<thead>
					<th>公告ID</th>
					<th>公告标题</th>
					<th>公告添加人</th>
					<th>添加时间</th>
					<th>状态</th>
				</thead>
				<s:iterator value="noticeList" status="myStatus">
					<tr>
						<td><s:property value="noticeId" /></td>
						<td><a
							href="<%=path%>/NoticeAction!look?noticeId=<s:property value="noticeId"/>"><s:property
									value="noticeTitle" /></a></td>
						<td><s:property value="noticeAdduser" /></td>
						<td><s:property value="noticeAddtime" /></td>
						<s:if test="isApprove==0">
							<td class="text-info">未审核</td>
						</s:if>
						<s:if test="isApprove==1">
							<td class="text-success">审核已通过</td>
						</s:if>
						<s:if test="isApprove==2">
							<td class="text-danger">审核未通过</td>
						</s:if>
					</tr>
				</s:iterator>
			</table>
		</div>
		<div class="panel-footer">
			<a href="<%=path%>/NoticeAction!list" />查看更多</a>
		</div>
	</div>
</s:if>	
<s:if test="mail == 'yes'">	
	<div class="panel panel-info">
		<div class="panel-heading">个人信息</div>
		<div class="panel-body">
			<table class="table table-hover">
				<thead>
					<th>发件人</th>
					<th>主题</th>
				</thead>
				<s:iterator value="mailList" status="myStatus">
					<tr>
						<td><s:property value="senderName" /></td>
						<td><s:property value="mailTitle" /> <s:if
								test="isReaded==0 ">
								<span class="glyphicon glyphicon-envelope text-info">新</span>
							</s:if></td>
					</tr>
				</s:iterator>
			</table>
		</div>
		<div class="panel-footer">
			<a href="<%=path%>/MailAction!receiveList" />查看更多</a>
		</div>
	</div>
</s:if>	
</body>
</html>