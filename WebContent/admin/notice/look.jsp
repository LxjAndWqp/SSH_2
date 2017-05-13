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
<script language="javascript" type="text/javascript" src="<%=path%>/script/My97DatePicker4.7/WdatePicker.js"></script>
<script src="<%=path %>/bootstrap/bootstrap.min.js" type="text/javascript"></script>
 <link href="<%=path %>/bootstrap/bootstrap.min.css" rel="stylesheet"/>

<script language="javascript">
function saveData(){	
}
function init(){
	var configJson = {
			toolbars:[
	        ]
	        ,autoHeightEnabled:false	
	        ,readonly:true
	};
	UE.getEditor('noticeContent',configJson);
}
</script>
<title>查看</title>
</head>
<body onload="init();">
	<table class= "table table-bordered" width="100%" border="1" style="font-size:12px;">
		<tr>
			<td width="25%">公告标题</td>
			<td><s:textfield class="form-control selectpicker" style="width:30%" name="noticeTitle" disabled="true" /> </td>
		</tr>
		<tr>
			<td>添加人员</td>
			<td><s:textfield class="form-control selectpicker" style="width:30%" name="noticeAdduser" disabled="true" /> </td>
		</tr>
		<tr>
			<td>添加时间</td>
			<td><s:textfield class="form-control selectpicker" style="width:30%" name="noticeAddtime" onclick="WdatePicker();" cssClass="Wdate" disabled="true"/></td>
		</tr>
		<tr>
			<td>公告内容</td>
			<td>
			<script id="noticeContent" name="noticeContent" 
					type="text/plain" 
					style="width:700px;height:200px;">${noticeContent}</script>
			</td>
		</tr>
		<tr>
			<td>公告附件</td>
			<td><s:property value="filename" />
				<s:if test="filepath != null && filepath != ''">
					<a  href="<%=path%>/NoticeAction!download?noticeId=<s:property value="noticeId"/>">下载</a>
				</s:if>
			</td>
		</tr>	
		<tr>
			<td colspan="2"><input class="btn btn-inverse" type="button" value="返回" onclick="window.history.go(-1);" /></td>
		</tr>
	</table>
</body>
</html>