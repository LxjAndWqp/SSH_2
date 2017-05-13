package com.action;

import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.bean.SurveyBean;
import com.bean.UserBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;
import com.service.SurveyService;
import com.util.BaseAction;
import com.util.Global;

@ParentPackage(value = "default")
@Namespace(value = "/")
@Action(value = "SurveyAction", results = {
		@Result(name = "list", location = "/admin/survey/list.jsp"),
		@Result(name = "add", location = "/admin/survey/add.jsp"),
		@Result(name = "edit", location = "/admin/survey/edit.jsp"),
		@Result(name = "result", location = "/admin/survey/result.jsp"),
		@Result(name = "vote", location = "/admin/survey/vote.jsp")

})
public class SurveyAction extends BaseAction implements ModelDriven<SurveyBean> {
	private SurveyBean surveyBean;

	public SurveyBean getModel() {
		if (surveyBean == null)
			surveyBean = new SurveyBean();
		return surveyBean;
	}

	@Autowired
	private SurveyService surveyService;

	@Override
	public String execute() throws Exception {
		return null;
	}

	public String list() throws Exception {
		ActionContext context = ActionContext.getContext();
		ValueStack valueStack = context.getValueStack();
		List<SurveyBean> surveyList = this.surveyService.getSurveyList();
		valueStack.set("surveyList", surveyList);
		return "list";
	}

	public String begin() throws Exception {
		String surveyId = request.getParameter("surveyId");
		surveyBean = this.surveyService.getSurveyBeanById(surveyId);
		ActionContext context = ActionContext.getContext();
		ValueStack valueStack = context.getValueStack();
		String date = Global.getSysDate();
		surveyBean.setBeginTime(date);
		this.surveyService.TimeUpdate(surveyBean);
		List<SurveyBean> surveyList = this.surveyService.getSurveyList();
		valueStack.set("surveyList", surveyList);
		return "list";
	}

	public String end() throws Exception {
		String surveyId = request.getParameter("surveyId");
		surveyBean = this.surveyService.getSurveyBeanById(surveyId);
		ActionContext context = ActionContext.getContext();
		ValueStack valueStack = context.getValueStack();
		String date = Global.getSysDate();
		surveyBean.setEndTime(date);
		;
		this.surveyService.TimeUpdate(surveyBean);
		List<SurveyBean> surveyList = this.surveyService.getSurveyList();
		valueStack.set("surveyList", surveyList);
		return "list";
	}

	public String add() throws Exception {
		return "add";
	}

	public String save() throws Exception {
		String[] optionsArray = request.getParameterValues("options");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			this.surveyService.save(surveyBean, optionsArray);
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
			String loadURL = request.getContextPath() + "/SurveyAction!list";
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

	public String deleteSurvey() throws Exception {
		String surveyId = request.getParameter("surveyId");
		boolean flag = false;
		try {
			this.surveyService.deleteSurvey(surveyId);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (flag == true) {
			return this.list();
		}
		return NONE;
	}

	public String edit() throws Exception {
		String surveyId = request.getParameter("surveyId");
		SurveyBean surveyBean = this.surveyService.getSurveyBeanById(surveyId);
		List<String> optionsArray = surveyService
				.getSurveyOptionsList(surveyId);
		ActionContext context = ActionContext.getContext();
		ValueStack valueStack = context.getValueStack();
		if (surveyBean != null) {
			valueStack.push(surveyBean);
		}
		if (optionsArray != null) {
			valueStack.set("options", optionsArray);
		}
		return "edit";
	}

	public String deleteOptions() throws Exception {
		String surveyId = request.getParameter("surveyId");
		String options = request.getParameter("options");
		ActionContext context = ActionContext.getContext();
		ValueStack valueStack = context.getValueStack();
		if (surveyBean != null) {
			valueStack.push(surveyBean);
		}
		boolean flag = false;
		try {
			this.surveyService.deleteOptions(options);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (flag == true) {
			return this.edit();
		}
		return NONE;
	}

	public String modify() throws Exception {
		boolean flag = false;
		String[] optionsArray = request.getParameterValues("options");
		try {
			this.surveyService.modify(surveyBean, optionsArray);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (flag == true) {
			return this.list();
		}
		return NONE;
	}

	public String vote() throws Exception {
		String surveyId = request.getParameter("surveyId");
		SurveyBean surveyBean = this.surveyService.getSurveyBeanById(surveyId);
		List<String> optionsArray = surveyService
				.getSurveyOptionsList(surveyId);
		ActionContext context = ActionContext.getContext();
		ValueStack valueStack = context.getValueStack();
		if (surveyBean != null) {
			valueStack.push(surveyBean);
		}
		if (optionsArray != null) {
			valueStack.set("optionsArray", optionsArray);
		}

		return "vote";
	}

	public String votesave() throws Exception {
		boolean flag = false;
		String[] optionsArray = request.getParameterValues("options");
		try {
			this.surveyService.votesave(surveyBean, optionsArray);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (flag == true) {
			return this.list();
		}
		return NONE;
	}

	public String result() throws Exception {
		DecimalFormat df = new DecimalFormat("0.00");
		DecimalFormat df0 = new DecimalFormat("0");
		float sum = 0;
		String surveyId = request.getParameter("surveyId");
		List<String> sumList = new ArrayList<String>();
		SurveyBean surveyBean = this.surveyService.getSurveyBeanById(surveyId);
		List<String> optionsArray = surveyService
				.getSurveyOptionsList(surveyId);
		List<String> number = surveyService.getOptionsNumberList(surveyId);
		for (String count : number) {
			float a = Float.parseFloat(count);
			sum += a;
		}
		if (sum != 0.0) {
			for (String count : number) {
				float a = Float.parseFloat(count);
				float per = (a / sum * 100);
				sumList.add(String.valueOf(df.format(per)));
			}
		} else {
			for (String count : number) {
				sumList.add("0.00");
			}
		}
		ActionContext context = ActionContext.getContext();
		ValueStack valueStack = context.getValueStack();
		if (surveyBean != null) {
			valueStack.push(surveyBean);
		}
		if (optionsArray != null) {
			valueStack.set("options", optionsArray);
		}
		if (number != null) {
			valueStack.set("number", number);
		}
		if (sumList != null) {
			valueStack.set("sumList", sumList);
		}
		if (String.valueOf(sum) != null) {
			valueStack.set("sum", df0.format(sum));
		}
		return "result";
	}

	public String search() throws Exception {
		String surveyTitle = request.getParameter("surveyTitleCondition");
		String beginTime = request.getParameter("beginTimeCondition");
		String endTime = request.getParameter("endTimeCondition");

		ActionContext context = ActionContext.getContext();
		ValueStack valueStack = context.getValueStack();
		List<SurveyBean> surveyList = this.surveyService.searchSurveyList(
				surveyTitle, beginTime, endTime);
		valueStack.set("surveyList", surveyList);
		request.setAttribute("surveyTitleCondition", surveyTitle);
		request.setAttribute("beginTimeCondition", beginTime);
		request.setAttribute("endTimeCondition", endTime);
		return "list";

	}
}
