<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" charset="utf-8" src="<%=path%>/script/ueditor1.3.6/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=path%>/script/ueditor1.3.6/ueditor.all.min.js"> </script>
<script type="text/javascript" charset="utf-8" src="<%=path%>/script/ueditor1.3.6/lang/zh-cn/zh-cn.js"></script>
<script src="<%=path %>/bootstrap/bootstrap.min.js" type="text/javascript"></script>
 <link href="<%=path %>/bootstrap/bootstrap.min.css" rel="stylesheet"/>

<script language="javascript">
function init(){
	var configJson = {
			toolbars:[
	        ]
	        ,autoHeightEnabled:false	
	        ,readonly:true
	};
	UE.getEditor('mailContent',configJson);
}
function replyMail(mailId){
	var flag  = window.confirm("立即回复该邮件?");
	if (flag == true){
		var deleteURL = "<%=path%>/MailAction!reply?mailId="+mailId+"";
		window.location.href = deleteURL;
	}
}

</script>
<title>查看</title>
</head>
<body onload="init();">
	<table class= "table table-bordered" width="100%" border="1" style="font-size:12px;">
		<tr>
			<td width="25%">标题</td>
			<td><s:textfield class="form-control selectpicker" style="width:30%" name="mailTitle" disabled="true" /> </td>
		</tr>
		<tr>
			<s:if test="#userid  == receiverId">
				<td>发件人</td>
			<td><s:textfield class="form-control selectpicker" style="width:30%" name="senderName" disabled="true" /> </td>
			</s:if>
			<s:if test="#userid   == senderId">
				<td>收件人</td>
			<td><s:textfield class="form-control selectpicker" style="width:30%" name="receiverName" disabled="true" /> </td>
			</s:if>
		</tr>
		<tr>
			<td>内容</td>
			<td>
			<script id="mailContent" name="mailContent" 
					type="text/plain" 
					style="width:700px;height:200px;">${mailContent}</script>
			</td>
		</tr>
		<tr>
			<td>附件1</td>
			<td><s:property value="filename1" />
				<s:if test="filepath1 != null && filepath1 != ''">
					<a  href="<%=path%>/MailAction!download?mailId=<s:property value="mailId"/>&&filename=<s:property value="filename1"/>&&filepath=<s:property value="filepath1"/>">下载</a>
				</s:if>
			</td>
		</tr>	
		<tr>
			<td>附件2</td>
			<td><s:property value="filename2" />
				<s:if test="filepath2 != null && filepath2 != ''">
					<a  href="<%=path%>/MailAction!download?mailId=<s:property value="mailId"/>&&filename=<s:property value="filename2"/>&&filepath=<s:property value="filepath2"/>">下载</a>
				</s:if>
			</td>
		</tr>	
		<tr>
			<td>附件3</td>
			<td><s:property value="filename3" />
				<s:if test="filepath3 != null && filepath3 != ''">
					<a  href="<%=path%>/MailAction!download?mailId=<s:property value="mailId"/>&&filename=<s:property value="filename3"/>&&filepath=<s:property value="filepath3"/>">下载</a>
				</s:if>
			</td>
		</tr>	
		<tr>
			<td colspan="2">
				<input class="btn btn-inverse" type="button" value="立即回复" onclick="replyMail('<s:property value="mailId"/>')" />
				<input class="btn btn-inverse" type="button" value="返回" onclick="window.history.go(-1);" />
			</td>
		</tr>
	</table>
</body>
</html>