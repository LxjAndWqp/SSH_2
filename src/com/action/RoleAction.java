package com.action;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.bean.RoleBean;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;
import com.service.RoleService;
import com.util.BaseAction;

@ParentPackage(value = "default")
@Namespace(value = "/")
@Action(value = "RoleAction", results = {

		@Result(name = "list", location = "/admin/role/list.jsp"),
		@Result(name = "add", location = "/admin/role/add.jsp"),
		@Result(name = "edit", location = "/admin/role/edit.jsp") })
public class RoleAction extends BaseAction implements ModelDriven<RoleBean> {

	@Autowired
	private RoleService roleService;

	public void setRoleservice(RoleService roleService) {
		this.roleService = roleService;
	}

	private RoleBean roleBean;

	@Override
	public RoleBean getModel() {
		if (roleBean == null) {
			roleBean = new RoleBean();
		}
		return roleBean;
	}

	@Override
	public String execute() throws Exception {
		return null;
	}

	public String list() throws Exception {
		ActionContext context = ActionContext.getContext();
		ValueStack valueStack = context.getValueStack();
		List<RoleBean> roleList = this.roleService.getRoleList();
		valueStack.set("roleList", roleList);
		return "list";
	}

	public String add() throws Exception {
		List<Map<String, Object>> menu_treeList = this.roleService.getMenu_TreeList(null, "add");
		Gson gson = new Gson();
		String jsonStr = gson.toJson(menu_treeList);
		request.setAttribute("jsonStr", jsonStr);
		return "add";
	}

	public String save() throws Exception {
		boolean flag = false;
		String errorMsg = "";
		try {
			String role_select_menuid = request.getParameter("role_select_menuid");
			this.roleService.save(roleBean, role_select_menuid);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			errorMsg = e.getMessage();
		}
		if (flag == true) {
			return this.list();
		} else {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<script language='javascript'>");
			out.println("window.alert(\"" + errorMsg + "\");");
			out.println("window.history.back();");
			out.println("</script>");
		}
		return NONE;
	}

	public String deleteRole() throws Exception {
		String roleId = request.getParameter("roleId");
		String errorMsg = null;
		if (roleId == null || roleId.equals("")) {
			errorMsg = "角色编码参数为空值，不能执行删除的操作";
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<script language='javascript'>");
			out.println("window.alert(\"" + errorMsg + "\");");
			out.println("window.history.back();");
			out.println("</script>");
		} else {
			boolean flag = false;
			try {
				this.roleService.deleteRole(roleId);
				flag = true;
			} catch (Exception e) {
				e.printStackTrace();
				errorMsg = e.getMessage();
			}
			if (flag == true) {
				return this.list();
			} else {
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.println("<script language='javascript'>");
				out.println("window.alert(\"" + errorMsg + "\");");
				out.println("window.history.back();");
				out.println("</script>");
			}
		}
		return NONE;
	}

	public String edit() throws Exception {
		String roleId = request.getParameter("roleId");
		this.roleBean = this.roleService.getRoleBeanById(roleId);
		ActionContext context = ActionContext.getContext();
		ValueStack valueStack = context.getValueStack();
		if (roleBean != null) {
			valueStack.push(roleBean);
		}
		List<Map<String, Object>> menu_treeList = roleService.getMenu_TreeList(roleId, "edit");
		Gson gson = new Gson();
		String jsonStr = gson.toJson(menu_treeList);
		request.setAttribute("jsonStr", jsonStr);
		return "edit";
	}

	public String modify() throws Exception {
		boolean flag = false;
		try {
			String role_select_menuid = request.getParameter("role_select_menuid");
			this.roleService.modify(roleBean, role_select_menuid);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (flag == true) {
			return this.list();
		}
		return NONE;
	}
	
	public String search() throws Exception {
		String rolename = request.getParameter("rolenameCondition");
		ActionContext context = ActionContext.getContext();
		ValueStack valueStack = context.getValueStack();
		List<RoleBean> roleList = this.roleService.searchRoleList(rolename);
		valueStack.set("roleList", roleList);
		request.setAttribute("rolenameCondition", rolename);
		return "list";
	}
}
