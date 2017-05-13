<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="<%=path %>/bootstrap/bootstrap.min.js" type="text/javascript"></script>
<script src="<%=path %>/jquery_validation_1.16.0/lib/jquery.js" type="text/javascript"></script>
<script src="<%=path %>/jquery_validation_1.16.0/dist/jquery.validate.min.js" type="text/javascript"></script>
<script src="<%=path %>/jquery_validation_1.16.0/dist/localization/messages_zh.js" type="text/javascript"></script>
 <link href="<%=path %>/bootstrap/bootstrap.min.css" rel="stylesheet"/>
  <script>
 $().ready(function() {
	  $("#Myform").validate({
		    rules: {
		    	dataName: "required"
		    },
	  messages: {
		 	    dataName: "请输入数据名称"
			    }
			});
		});
</script>
<style>
.error{
	color:red;
}
</style>
<title>数据字典修改</title>
</head>
<body>
		<s:form  method="post" namespace="/" action="DataAction!modify" id="Myform">
		<table class= "table table-bordered">
			<tr>
				<td align="right">数据名称:</td>
				<td><s:textfield class="form-control selectpicker" style="width:30%" name="dataName"  />
					<s:hidden name="dataId" id="dataId"/>
				</td>
			</tr>
			<tr>
				<td align="right">数据类型:</td>
				<td>
					<s:select class="form-control selectpicker" style="width:30%" list="{'int','float','double','String','enum'}" 
						 name="dataType"></s:select>
				</td>
			</tr>
			<tr>
				<td align="right">数据长度:</td>
				<td><s:textfield class="form-control selectpicker" style="width:30%"  name="dataLength" /></td>
			</tr>
			<tr>
				<td align="right">是否空值:</td>
				<td>
					<s:radio list="#{'1':'是','0':'否'}" name="isEmpty" value="1"/>
				</td>
			</tr>
			<tr>
				<td align="right">是否有范围:</td>
				<td>
					<s:radio list="#{'1':'有','0':'无'}" name="isScope" value="1"/>
				</td>
			</tr>
			<tr>
				<td align="right">父类:</td>
				<td><s:select class="form-control selectpicker" style="width:30%"  list="#map" name="parentId"></s:select></td>
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