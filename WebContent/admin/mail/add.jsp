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


<script src="<%=path %>/jquery_validation_1.16.0/lib/jquery.js" type="text/javascript"></script>
<script src="<%=path %>/jquery_validation_1.16.0/dist/jquery.validate.min.js" type="text/javascript"></script>
<script src="<%=path %>/jquery_validation_1.16.0/dist/localization/messages_zh.js" type="text/javascript"></script>
 <link href="<%=path %>/bootstrap/bootstrap.min.css" rel="stylesheet"/>
<script language="javascript">
	function  back(){
		var flag  = window.confirm("是否存为草稿");
		if (flag == true){
			document.getElementById("status").value = "0";
			var form1 = document.getElementById("form1");
			form1.submit();
		}
		
		
	}

	function init() {
		var configJson = {
			toolbars : [ [ 'fullscreen', 'source', '|', 'undo', 'redo', '|',
					'bold', 'italic', 'underline', 'fontborder',
					'strikethrough', 'superscript', 'subscript',
					'removeformat', 'formatmatch', 'autotypeset', 'blockquote',
					'pasteplain', '|', 'forecolor', 'backcolor',
					'insertorderedlist', 'insertunorderedlist', 'selectall',
					'cleardoc', '|', 'rowspacingtop', 'rowspacingbottom',
					'lineheight', '|', 'customstyle', 'paragraph',
					'fontfamily', 'fontsize', '|', 'directionalityltr',
					'directionalityrtl', 'indent', '|', 'justifyleft',
					'justifycenter', 'justifyright', 'justifyjustify', '|',
					'touppercase', 'tolowercase', '|', 'link', 'unlink',
					'anchor', '|', 'imagenone', 'imageleft', 'imageright',
					'imagecenter', '|', 'insertimage', 'emotion', 'scrawl',
					'insertvideo', 'music', 'attachment', 'map', 'gmap',
					'insertframe', 'insertcode', 'webapp', 'pagebreak',
					'template', 'background', '|', 'horizontal', 'date',
					'time', 'spechars', 'snapscreen', 'wordimage', '|',
					'inserttable', 'deletetable', 'insertparagraphbeforetable',
					'insertrow', 'deleterow', 'insertcol', 'deletecol',
					'mergecells', 'mergeright', 'mergedown', 'splittocells',
					'splittorows', 'splittocols', 'charts', '|', 'print',
					'preview', 'searchreplace', 'help', 'drafts' ] ],
			autoHeightEnabled : false
		};
		UE.getEditor('mailContent', configJson);
	}

	$().ready(function() {
		$("#form1").validate({
			rules : {
				mailTitle : "required"
			},
			messages : {
				mailTitle : "请输入标题"
			}
		});
	});
</script>
<style>
.error{
	color:red;
}
</style>
<title>写邮件</title>
</head>
<body onload="init();">
<s:form method="post" namespace="/" action="MailAction!save" id="form1" 
		enctype="multipart/form-data">
	<table class= "table table-bordered" width="100%" border="1" style="font-size:12px;">
		<tr>
			<td width="25%"><span class="glyphicon glyphicon-asterisk text-danger"></span>邮件标题</td>
			<td><s:textfield class="form-control selectpicker" style="width:30%" name="mailTitle"/>
				<s:hidden id = "status" name = "status"  value = "1" />		
				
			</td>
		</tr>
		<tr>
			<td>收件人</td>
			<td>
				<select class="form-control selectpicker" style="width:30%" name="receiverId">
						<c:forEach items="${userList }" var="user" >
							<option value ="${user.userid }" <c:if test="${mail.receiverId}=='${user.userid}'">selected</c:if> >${user.username }</option>
						</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td><span class="glyphicon glyphicon-asterisk text-danger"></span>内容</td>
			<td>
			<script id="mailContent" name="mailContent" 
					type="text/plain" 
					style="width:700px;height:200px;"></script>
			</td>
		</tr>
		<tr>
			<td>公告附件</td>
			<td>
				<s:file class = "btn btn-default" name="file1" />
				<s:file class = "btn btn-default" name="file2" />
				<s:file class = "btn btn-default" name="file3" />
			</td>
		</tr>		
		<tr>
			<td colspan="2">
				<Input class="btn btn-primary" type="submit" value="发送邮件" /> 
				<Input class= "btn btn-info" type="reset" value="重新输入" />
				<input class="btn btn-inverse" type="button" value="返回" onclick="back()" />
			</td>
		</tr>								
	</table>
</s:form>
</body>
</html>