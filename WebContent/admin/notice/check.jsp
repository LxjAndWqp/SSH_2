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
	};
	UE.getEditor('noticeContent',configJson);
}
</script>
<title>公告审核</title>
</head>
<body onload="init();">
审核公告
<s:form method="post" namespace="/" action="NoticeAction!checkSave" id="form1" 
		enctype="multipart/form-data">
	<table class= "table table-bordered" width="100%" border="1" style="font-size:12px;">
		<tr>
			<td width="25%">公告标题</td>
			<td><s:textfield class="form-control selectpicker" style="width:30%" name="noticeTitle"/>
				<s:hidden name = "noticeId" ></s:hidden>
				<s:hidden name = "filename" ></s:hidden>
				<s:hidden name = "filepath" ></s:hidden>
			</td>
		</tr>
		<tr>
			<td>添加人员</td>
			<td><s:textfield class="form-control selectpicker" style="width:30%" name="noticeAdduser"/> </td>
		</tr>
		<tr>
			<td>添加时间</td>
			<td><s:textfield class="form-control selectpicker" style="width:30%" name="noticeAddtime" onclick="WdatePicker();" cssClass="Wdate"/></td>
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
			<td><s:file class = "btn btn-default" style="width:30%" name="attch" size="40" value="#attch" /></td>
		</tr>	
		<tr>
			<td>审核公告</td>
			<td>
				<s:select class="form-control selectpicker" style="width:30%" name= "state" list="#state" listKey="key" listValue="value" >
				</s:select>
			</td>
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