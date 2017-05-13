package com.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.bean.MenuBean;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;
import com.service.MenuService;
import com.util.BaseAction;

@ParentPackage(value = "default")
@Namespace(value = "/")
@Action(value = "MenuAction", results = { 
		@Result(name = "list", location = "/admin/menu/list.jsp"),
		@Result(name = "add", location = "/admin/menu/add.jsp"),
		@Result(name = "edit", location = "/admin/menu/edit.jsp") })
public class MenuAction extends BaseAction implements ModelDriven<MenuBean> {

	private MenuBean menuBean;

	@Override
	public MenuBean getModel() {
		if (menuBean == null) {
			menuBean = new MenuBean();
		}
		return menuBean;
	}

	@Autowired
	private MenuService menuService;

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	@Override
	public String execute() throws Exception {
		return null;
	}

	public String list() throws Exception {
		List<MenuBean> menuList = this.menuService.getMenuMap();
		ActionContext context = ActionContext.getContext();
		ValueStack valueStack = context.getValueStack();
		Map<String, Integer> id_rowMap = new LinkedHashMap<String, Integer>();
		List<Integer> rowList = new ArrayList<Integer>();
		int rowNum = 1;
		for (MenuBean menuBean : menuList) {
			String iden_str = "";
			for (int i = 1; i < menuBean.getGrade(); i++) {
				iden_str = iden_str + "&nbsp;&nbsp;&nbsp;&nbsp;";
			}
			menuBean.setMenuName(iden_str + menuBean.getMenuName());
			id_rowMap.put(menuBean.getMenuId(), rowNum);
			rowNum++;
			if (menuBean.getParentid().equals(0)) {
				rowList.add(0);
			} else {
				int tempNo = id_rowMap.get(String.valueOf(menuBean.getParentid()));
				rowList.add(tempNo);
			}
		}
		Gson gson = new Gson();
		String josnStr = gson.toJson(rowList);

		context.put("jsonStr", josnStr);
		context.put("menuList", menuList);
		return "list";
	}

	public String add() throws Exception {
		return "add";
	}

	public String save() throws Exception {
		boolean flag = false;
		try {
			menuService.save(menuBean);
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
		String menuId = request.getParameter("menuId");
		MenuBean menuBean = this.menuService.getMenuBeanByID(menuId);
		ActionContext context = ActionContext.getContext();
		ValueStack valueStack = context.getValueStack();
		valueStack.push(menuBean);
		return "edit";
	}

	public String modify() throws Exception {
		boolean flag = false;
		try {
			this.menuService.modify(menuBean);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (flag == true) {
			return this.list();
		}
		return NONE;
	}

	public String deleteMenu() throws Exception {
		String menuId = request.getParameter("menuId");
		boolean flag = false;
		String errMsg = null;
		try {
			flag = this.menuService.delete(menuId);
		} catch (Exception e) {
			e.printStackTrace();
			errMsg = e.getMessage();
		}
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		if (flag == true) {
		
			String loadURL = request.getContextPath() + "/MenuAction!list";
			out.println("<script language='javascript'>");
			out.println("window.alert('数据保存成功');");
			out.println("window.location.href = '" + loadURL + "';");
			out.println("</script>");

		} else {
			out.println("<script language='javascript'>");
			out.println("window.alert('操作失败，错误原因 = " + errMsg + "');");
			out.println("window.history.back();");
			out.println("</script>");
		}
		return NONE;

	}

}
