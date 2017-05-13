package com.action;

import java.io.PrintWriter;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.bean.UserBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;
import com.service.UserService;
import com.util.BaseAction;

@ParentPackage(value = "default")
@Namespace(value = "/")
@Action(value = "LoginAction", results = {
		@Result(name = "login", location = "/Login.jsp"), 
		@Result(name = "main", location = "/indexMain.jsp") 
})
public class LoginAction extends BaseAction {
	private String password;
	private String username;
	@Autowired
	private UserService userService;
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String execute() throws Exception {

	if(this.username==null||this.username.equals(""))
	{
		this.addFieldError("username", "用户名不能为空");
	}if(this.password==null||this.password.equals(""))
	{
		this.addFieldError("password", "密码名不能为空");
	}if(this.hasFieldErrors()==true)
	{
		return "login";
	}
	List<UserBean> userList = userService.getUserList();
		for(UserBean userBean:userList)
		{
			if (this.username.equals(userBean.getUsername())) {
				if (this.password.equals(userBean.getPassword())) {
					session.setAttribute("userBean", userBean);
					return "main";
				} else {
					this.addActionMessage("密码不对");
				}
			} else {
				this.addActionMessage("用户名不对");
			}
		}if(this.hasActionMessages()==true)
		{
			return "login";
		}return null;
	}

	public String exit() throws Exception {
		String loginURL = request.getContextPath() + "/Login.jsp";
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		out.println("<script language='javascript'>");
		out.println(" window.alert('" + "安全注销" + "');");
		out.println(" window.top.location.href = '" + loginURL + "';");
		out.println("</script>");
		out.flush();
		out.close();
		request.getSession().removeAttribute("userBean");
		return NONE;

	}

}
