package com.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.bean.MailBean;
import com.bean.NoticeBean;
import com.bean.UserBean;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;
import com.service.MailService;
import com.service.MainService;
import com.service.PersonInfoService;
import com.util.BaseAction;

@ParentPackage(value = "default")
@Namespace(value = "/")
@Action(value = "MainAction", results = {
		@Result(name = "list", location = "/admin/main/Main.jsp"),
		@Result(name = "top", location = "/admin/main/top.jsp"),
		@Result(name = "left", location = "/admin/main/left.jsp"),
		@Result(name = "right", location = "/admin/main/right.jsp") 
		})
public class MainAction extends BaseAction{
	@Autowired
	private MailService mailService;
	
	@Autowired
	private MainService mainService;
	
	@Autowired
	private PersonInfoService personInfoService;

	public void setPersonInfoService(PersonInfoService personInfoService) {
		this.personInfoService = personInfoService;
	}

	
	public void setMainService(MainService mainService) {
		this.mainService = mainService;
	}
	
	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}



	@Override
	public String execute() throws Exception {
		return "null";
	}

	public String list() throws Exception {
		
		String userid = (String)session.getAttribute("userid");
		if (userid != null) {
			session.setAttribute("userid", userid);
		}
		return "list";
	}

	public String left() throws Exception {
		UserBean userbean;
		String userid = "";
		if (session.getAttribute("userBean") != null) {
			userbean = (UserBean)session.getAttribute("userBean");
			userid = userbean.getUserid();
		}
		
		List<Map<String, Object>> menu_treeList = this.mainService.getMenu_TreeList(userid);
		Gson gson = new Gson();
		String jsonStr = gson.toJson(menu_treeList);
		request.setAttribute("jsonStr", jsonStr);
		return "left";
	}

	public String right() throws Exception {
		ActionContext context = ActionContext.getContext();
		ValueStack valueStack = context.getValueStack();
		
		UserBean loginUser = (UserBean) session.getAttribute("userBean");
		List<NoticeBean> noticeList = this.mainService.getNoticeBeanTime();
		List<MailBean> mailList = this.mailService.getReceiveMailList(loginUser.getUserid());
		Map<String, String> paramMap = this.personInfoService.getUserParam(loginUser.getUserid());
		paramMap.put("userid", loginUser.getUserid());
		if (paramMap != null) {
			valueStack.push(paramMap);
		}
		
		valueStack.set("noticeList",noticeList);
		valueStack.set("mailList",mailList);
		return "right";
	}
}
