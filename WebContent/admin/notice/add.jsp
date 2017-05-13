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


<script src="<%=path %>/jquery_validation_1.16.0/lib/jquery.js" type="text/javascript"></script>
<script src="<%=path %>/jquery_validation_1.16.0/dist/jquery.validate.min.js" type="text/javascript"></script>
<script src="<%=path %>/jquery_validation_1.16.0/dist/localization/messages_zh.js" type="text/javascript"></script>
 <link href="<%=path %>/bootstrap/bootstrap.min.css" rel="stylesheet"/>
<script language="javascript">
function saveData(){	
}
function init(){
	var configJson = {
			toolbars:[
	            ['fullscreen', 'source', '|', 'undo', 'redo', '|',
	                'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc', '|',
	                'rowspacingtop', 'rowspacingbottom', 'lineheight', '|',
	                'customstyle', 'paragraph', 'fontfamily', 'fontsize', '|',
	                'directionalityltr', 'directionalityrtl', 'indent', '|',
	                'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'touppercase', 'tolowercase', '|',
	                'link', 'unlink', 'anchor', '|', 'imagenone', 'imageleft', 'imageright', 'imagecenter', '|',
	                'insertimage', 'emotion', 'scrawl', 'insertvideo', 'music', 'attachment', 'map', 'gmap', 'insertframe','insertcode', 'webapp', 'pagebreak', 'template', 'background', '|',
	                'horizontal', 'date', 'time', 'spechars', 'snapscreen', 'wordimage', '|',
	                'inserttable', 'deletetable', 'insertparagraphbeforetable', 'insertrow', 'deleterow', 'insertcol', 'deletecol', 'mergecells', 'mergeright', 'mergedown', 'splittocells', 'splittorows', 'splittocols', 'charts', '|',
	                'print', 'preview', 'searchreplace', 'help', 'drafts']
	        ]
	        ,autoHeightEnabled:false	
	};
	UE.getEditor('noticeContent',configJson);
}

$().ready(function() {
	  $("#form1").validate({
		    rules: {
		    	noticeTitle: "required"
		    },
	  messages: {
		        noticeTitle: "请输入公告标题"
			    }
			});
		});
</script>
<style>
.error{
	color:red;
}
</style>
<title>公告添加</title>
</head>
<body onload="init();">
<s:form method="post" namespace="/" action="NoticeAction!save" id="form1" 
		enctype="multipart/form-data">
	<table class= "table table-bordered" width="100%" border="1" style="font-size:12px;">
		<tr>
			<td width="25%"><span class="glyphicon glyphicon-asterisk text-danger"></span>公告标题</td>
			<td><s:textfield class="form-control selectpicker" style="width:30%" name="noticeTitle"/> </td>
		</tr>
		<tr>
			<td>添加人员</td>
			<td>
				<s:textfield class="form-control selectpicker" style="width:30%" name="noticeAdduser_temp" disabled="true" value="%{noticeAdduser}" /> 
				<s:hidden name ="noticeAdduser"  />
			</td>
		</tr>
		<tr>
			<td>添加时间</td>
			<td><s:textfield class="form-control selectpicker" style="width:30%" name="noticeAddtime" onclick="WdatePicker();" cssClass="Wdate" value="%{#noticeAddtime}"/></td>
		</tr>
		<tr>
			<td><span class="glyphicon glyphicon-asterisk text-danger"></span>公告内容</td>
			<td>
			<script id="noticeContent" name="noticeContent" 
					type="text/plain" 
					style="width:700px;height:200px;"></script>
			</td>
		</tr>
		<tr>
			<td>公告附件</td>
			<td><s:file class = "btn btn-default" name="attch" /></td>
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