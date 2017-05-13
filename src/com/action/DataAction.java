package com.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.bean.DataBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;
import com.service.DataService;
import com.util.BaseAction;

@ParentPackage(value = "default")
@Namespace(value = "/")
@Action(value = "DataAction", results = {
		@Result(name = "add", location = "/admin/data/add.jsp"), 
		@Result(name = "edit", location = "/admin/data/edit.jsp"),
		@Result(name = "list", location = "/admin/data/list.jsp")
})
public class DataAction extends BaseAction implements ModelDriven<DataBean> {
	@Autowired
	private DataService dataService;

	public void setDataService(DataService dataService) {
		this.dataService = dataService;
	}
	
	private DataBean dataBean;
	
	@Override
	public DataBean getModel() {
		if (dataBean == null) {
			dataBean = new DataBean();
		}
		return dataBean;
	}

	@Override
	public String execute() throws Exception {
		return null;
	}
	
	public String list() throws Exception {
		List<DataBean> dataList = this.dataService.getDataBeanList();
		ActionContext context = ActionContext.getContext();
		ValueStack valueStack = context.getValueStack();
		valueStack.set("dataList", dataList);
		return "list";
	}
	
	public String add() throws Exception {
		List<DataBean> dataList = null;
		Map<Integer, String > map = new HashMap<>();
		dataList = this.dataService.getDataBeanList();
		map.put(0, 0+",无父类");
		for (DataBean db : dataList) {
			if(db.getParentId() == 0){
				map.put(db.getDataId(), db.getDataId()+","+db.getDataName());
			}
		}
		ActionContext context = ActionContext.getContext();
		context.put("map", map);
		return "add";
	}
	
	public String save() throws Exception {
		String name = this.dataBean.getDataName();
		int  parentId = this.dataBean.getParentId();
		List<DataBean> dataList = this.dataService.getDataBeanList();
		for (DataBean db : dataList) {
			if (db.getParentId() == parentId) {
				if (db.getDataName().equals(name)) {
					response.setContentType("text/html;charset=utf-8");
					PrintWriter writer = response.getWriter();
					writer.write("<script language='javascript'>");
					writer.write("window.alert('该数据名字在该父类下已经存在！请更换！');");
					writer.write("window.location.href='javascript:history.go(-1)'");
					writer.write("</script>");
					return NONE;
				}
			}
		}
		this.dataService.save(dataBean);
		return this.list();
		
	}
	
	public String deleteData() throws Exception {
		String dataId = request.getParameter("dataId");
		String errorMsg = null;
		if (dataId == null || dataId.equals("")) {
			errorMsg = "数据编码参数为空值，不能执行删除的操作";
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<script language='javascript'>");
			out.println("window.alert(\"" + errorMsg + "\");");
			out.println("window.history.back();");
			out.println("</script>");
		} else {
			boolean flag = false;
			try {
				this.dataService.delete(dataId);
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
		String dataId = request.getParameter("dataId");
		this.dataBean = this.dataService.getDataBeanByID(dataId);
		ActionContext context = ActionContext.getContext();
		ValueStack valueStack = context.getValueStack();
		List<DataBean> dataList = null;
		Map<Integer, String > map = new HashMap<>();
		dataList = this.dataService.getDataBeanList();
		map.put(0, 0+",无父类");
		for (DataBean db : dataList) {
			if(db.getParentId() == 0){
				map.put(db.getDataId(), db.getDataId()+","+db.getDataName());
			}
		}
		if (dataBean != null) {
			valueStack.push(dataBean);
			context.put("map", map);
		}
		return "edit";
	}

	public String modify() throws Exception {
		boolean flag = false;
		try {
			this.dataService.modify(dataBean);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (flag == true) {
			return this.list();
		}
		return NONE;
	}

}
