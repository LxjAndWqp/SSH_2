<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="<%=path %>/jquery_validation_1.16.0/lib/jquery.js" type="text/javascript"></script> 
<script src="<%=path %>/jquery_validation_1.16.0/dist/jquery.validate.min.js" type="text/javascript"></script>
<script src="<%=path %>/jquery_validation_1.16.0/dist/localization/messages_zh.js" type="text/javascript"></script>
<script type="text/javascript">
	var jQuery_validation = $.noConflict(true);
</script>
<link rel="stylesheet"
	href="<%=path%>/script/ztree_3.3/css/zTreeStyle/zTreeStyle.css"
	type="text/css">
<script type="text/javascript"
	src="<%=path%>/script/ztree_3.3/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/script/ztree_3.3/js/jquery.ztree.all-3.3.min.js"></script>
<script type="text/javascript" src="<%=path%>/script/jquery.json-2.4.js"></script>
<script src="<%=path%>/bootstrap/bootstrap.min.js"
	type="text/javascript"></script>
	
<script src="<%=path %>/jquery_validation_1.16.0/dist/jquery.validate.min.js" type="text/javascript"></script>
<script src="<%=path %>/jquery_validation_1.16.0/dist/localization/messages_zh.js" type="text/javascript"></script>
<link href="<%=path%>/bootstrap/bootstrap.min.css" rel="stylesheet" />

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加页面</title>
<script language="javascript">

		
	var setting = {
		data : {
			simpleData : {
				enable : true
			}
		},
		check : {
			enable : true
		}

	};
	var jsonStr = '${requestScope.jsonStr}';
	var jsonArrayObj = jQuery.evalJSON(jsonStr);

	var zNodes = jsonArrayObj;
	$(document).ready(function() {	
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	});
	
	jQuery_validation().ready(function() {
		jQuery_validation("#Myform").validate({
			    rules: {
			    	roleId:{
				        required: true,
				      },
				      roleName:  {
				        required: true
				      }
			    },
		  messages: {
			          roleId: {
				        required:"请输入角色编号"
				      },
				      roleName:  {
				        required:"请输入角色名"
				      }
				    }
				});
			});
	function saveData() {
		//获取勾选的角色的树形节点。
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		var nodeArray = treeObj.getCheckedNodes(true);
		if (nodeArray == null || nodeArray.length == 0) {
			window.alert("添加角色时，必须勾选角色所对应的权限");
		} else {
			var idArray = new Array();
			for (var i = 0; i < nodeArray.length; i++) {
				var treeNode = nodeArray[i];
				var id = treeNode.id;
				idArray.push(id);
			}
			var role_select_menuid = idArray.join(",");
			document.getElementById("role_select_menuid").value = role_select_menuid;
			if( jQuery_validation("#Myform").valid()){
				var form1 = document.getElementById("Myform");
				form1.submit();
				}
		}
	}
	

	$().ready(function() {
		  $("#Myform").validate({
			    rules: {
			    	roleId:"required"
			    },
		  messages: {
			  roleId: "dddd"
				    }
				});
			})(jv);
</script>
<style>
.error{
	color:red;
}
</style>
</head>
<body>
	<s:form id="Myform" namespace="/" action="RoleAction!save"
		method="post">
		<table class="table table-bordered">
			<tr>
				<td><span class="glyphicon glyphicon-asterisk text-danger"></span>角色编码</td>
				<td><s:textfield class="form-control selectpicker"
						style="width:30%" name="roleId" /></td>
			</tr>
			<tr>
				<td><span class="glyphicon glyphicon-asterisk text-danger"></span>角色名</td>
				<td><s:textfield class="form-control selectpicker"
						style="width:30%" name="roleName" /></td>
			</tr>
			<tr>
				<td>备注</td>
				<td><s:textarea class="form-control selectpicker"
						style="width:100%" name="roleRemark" cols="50" rows="5" /></td>
			</tr>
			<tr>
				<td><span class="glyphicon glyphicon-asterisk text-danger"></span>角色权限</td>
				<td>
					<div class="zTreeDemoBackground left"
						style="height: 180px; overflow-y: scroll;">
						<ul id="treeDemo" class="ztree"></ul>
					</div> <s:hidden name="role_select_menuid" id="role_select_menuid" />
				</td>
			</tr>
			<tr>
				<td colspan="2"><Input class="btn btn-primary" type="button"
					value="保存数据" onclick="saveData();" /> <Input class="btn btn-info"
					type="reset" value="重新输入" /> <input class="btn btn-inverse"
					type="button" value="返回" onclick="window.history.go(-1);" /></td>
			</tr>
		</table>


	</s:form>
</body>
</html>