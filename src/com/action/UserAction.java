package com.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.bean.DataBean;
import com.bean.RoleBean;
import com.bean.UserBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;
import com.service.DataService;
import com.service.RoleService;
import com.service.UserService;
import com.util.BaseAction;

@ParentPackage(value = "default")
@Namespace(value = "/")
@Action(value = "UserAction", results = {
		@Result(name = "list", location = "/admin/user/list.jsp"),
		@Result(name = "add", location = "/admin/user/add.jsp"),
		@Result(name = "edit", location = "/admin/user/edit.jsp")

})
public class UserAction extends BaseAction implements ModelDriven<UserBean> {
	private UserBean userBean;
	public UserBean getModel() {
		userBean = new UserBean();
		return userBean;
	}
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private DataService dataService;

	@Override
	public String execute() throws Exception {
		return null;
	}

	public String list() throws Exception {
		ActionContext context = ActionContext.getContext();
		ValueStack valueStack = context.getValueStack();
		String isadmin  = request.getParameter("isadmin");
		List<UserBean> userList = this.userService.getUserList();
		valueStack.set("userList", userList);
		valueStack.set("isadmin", isadmin);
		return "list";
	}
	
	public String add() throws Exception{
		List<RoleBean> roleList = this.roleService.getRoleList();
		List<Integer> ageList  = new ArrayList<>();
		ActionContext context = ActionContext.getContext();
		ValueStack valueStack = context.getValueStack();
		List<DataBean> deptList = this.dataService.getDataBeanListByName("部门");
		for(int i = 0;i<=99;i++){
			ageList.add(i);
		}
		context.put("ageList", ageList);
		context.put("roleList", roleList);
		context.put("deptList", deptList);
		return "add";
	}
	
	public String save() throws Exception{
		boolean flag = false;
		try {
			String[] roleArray = request.getParameterValues("user_roleList");
			this.userService.save(userBean,roleArray);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (flag == true) {
			return this.list();
		}
		return NONE;
	}
	
	public String deleteUser() throws Exception {
		String userid = request.getParameter("userid");
		boolean flag = false;
		try {
			this.userService.deleteuser(userid);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (flag == true) {
			return this.list();
		}
		return NONE;
	}
	public String edit() throws Exception{
		String userid = request.getParameter("userid");
		UserBean userBean = this.userService.getUserBeanById(userid);
		List<String> checkbox = this.userService.getUserRoleBeanById(userid);
		List<RoleBean> roleList = this.roleService.getRoleList();
		List<Integer> ageList  = new ArrayList<>();
		ActionContext context = ActionContext.getContext();
		ValueStack valueStack = context.getValueStack();
		List<DataBean> deptList = this.dataService.getDataBeanListByName("部门");
		for(int i = 0;i<=99;i++){
			ageList.add(i);
		}
		context.put("deptList", deptList);
		context.put("ageList", ageList);
		context.put("roleList", roleList);
		context.put("user_roleList", checkbox);
		if(userBean != null){
			valueStack.push(userBean);
		}
		return "edit";
	}
	public String modify() throws Exception{
		boolean flag = false;
		try {
			String[] roleArray = request.getParameterValues("user_roleList");
			this.userService.modify(userBean,roleArray);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (flag == true) {
			return this.list();
		}
		return NONE;
	}
	
	public String search() throws Exception{
		String username = request.getParameter("usernameCondition");
		String truename = request.getParameter("truenameCondition");
		String dept = request.getParameter("deptCondition");
		
		ActionContext context = ActionContext.getContext();
		ValueStack valueStack = context.getValueStack();
		List<UserBean> userList = this.userService.searchUserList(username,truename,dept);
		valueStack.set("userList", userList);
		request.setAttribute("usernameCondition", username);
		request.setAttribute("truenameCondition", truename);
		request.setAttribute("deptCondition", dept);
		return "list";
		
	}
}
