package com.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.bean.UserBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;
import com.service.PersonInfoService;
import com.util.BaseAction;

@ParentPackage(value = "default")
@Namespace(value = "/")
@Action(value = "PersonInfoAction", results = {
		@Result(name = "param", location = "/admin/param/param.jsp"),
})
public class PersonInfoAction extends BaseAction  {
		
	@Autowired
	private PersonInfoService personInfoService;

	public void setPersonInfoService(PersonInfoService personInfoService) {
		this.personInfoService = personInfoService;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String load() throws Exception {
		UserBean userBean = (UserBean) session.getAttribute("userBean");
		String userid = userBean.getUserid();
		Map<String, String> paramMap = this.personInfoService.getUserParam(userid);
		paramMap.put("userid", userid);
		ActionContext context = ActionContext.getContext();
		ValueStack valueStack = context.getValueStack();
		if (paramMap != null) {
			valueStack.push(paramMap);
		}
		return "param";
	}

	public String save() throws Exception {
		String userid = request.getParameter("userid");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			this.personInfoService.save(userid, request);
			resultMap.put("opt_flag", true);
		} catch (Exception e) {
			e.printStackTrace();

			System.out.println("action中= " + e.getMessage());
			resultMap.put("opt_flag", false);
			resultMap.put("errorMsg", e.getMessage());
		}
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Boolean opt_flag = (Boolean) resultMap.get("opt_flag");
		if (opt_flag == true) {
			String loadURL = request.getContextPath()
					+ "/PersonInfoAction!load?userid=" + userid + "";
			out.println("<script language='javascript'>");
			out.println("window.alert('数据保存成功');");
			out.println("window.location.href = '" + loadURL + "';");
			out.println("</script>");
		} else {
			String errorMsg = String.valueOf(resultMap.get("errorMsg"));
			out.println("<script language='javascript'>");
			out.println("window.alert('操作失败，错误原因 = " + errorMsg + "');");
			out.println("window.history.back();");
			out.println("</script>");
		}

		return NONE;
	}
	
	
}
