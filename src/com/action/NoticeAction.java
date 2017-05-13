package com.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.bean.NoticeBean;
import com.bean.UserBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;
import com.service.NoticeService;
import com.util.BaseAction;
import com.util.Global;

@ParentPackage(value = "default")
@Namespace(value = "/")
@Action(value = "NoticeAction", results = {
		@Result(name = "list", location = "/admin/notice/list.jsp"),
		@Result(name = "look", location = "/admin/notice/look.jsp"),
		@Result(name = "add", location = "/admin/notice/add.jsp"),
		@Result(name = "check", location = "/admin/notice/check.jsp"),
		@Result(name = "edit", location = "/admin/notice/edit.jsp") })
public class NoticeAction extends BaseAction implements ModelDriven<NoticeBean> {
	
	@Autowired
	private NoticeService noticeService;

	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}

	private NoticeBean noticeBean;

	/**
	 * 上传附件时，使用
	 */
	private File attch;
	private String attchFileName;

	public File getAttch() {
		return attch;
	}

	public void setAttch(File attch) {
		this.attch = attch;
	}

	public String getAttchFileName() {
		return attchFileName;
	}

	public void setAttchFileName(String attchFileName) {
		this.attchFileName = attchFileName;
	}

	@Override
	public NoticeBean getModel() {
		if (noticeBean == null) {
			noticeBean = new NoticeBean();
		}
		return noticeBean;
	}

	@Override
	public String execute() throws Exception {
		return null;
	}

	/**
	 * 公告添加的页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		/**
		 * 将时间默认为当前的系统时间
		 */
		ActionContext context = ActionContext.getContext();
		ValueStack valueStack = context.getValueStack();
		String noticeAddtime = Global.getSysDate();
		UserBean userBean = (UserBean) session.getAttribute("userBean");
		valueStack.set("noticeAdduser", userBean.getUsername());
		context.put("noticeAddtime", noticeAddtime);
		return "add";
	}

	public String save() throws Exception {
		String uploadDir = this.servletContext.getRealPath("/upload/notice");
		boolean flag = false;
		try {
			this.noticeService.save(this.noticeBean, this.attch,
					this.attchFileName, uploadDir);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (flag==true) {
			return this.list();
		}
		return NONE;
	}

	public String edit() throws Exception {
		ActionContext context = ActionContext.getContext();
		ValueStack valueStack = context.getValueStack();
		String noticeId = request.getParameter("noticeId");
		this.noticeBean = this.noticeService.getNoticeBeanByID(noticeId);
		this.attch = new File(this.noticeBean.getFilepath());
		this.attchFileName = this.noticeBean.getFilename();
		if (this.noticeBean != null) {
			valueStack.push(this.noticeBean);
		}
		return "edit";
	}
	
	public String modify() throws Exception{
		String uploadDir = this.servletContext.getRealPath("/upload/notice");
		boolean flag= false;
		try {
			this.noticeService.modify(this.noticeBean, this.attch, this.attchFileName, uploadDir);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (flag== true) {
			return this.list();
		}
		return NONE;
	}
	
	public String del() throws Exception {
		String noticeId = request.getParameter("noticeId");
		
		boolean flag = false;
		try {
			this.noticeService.delete(noticeId);
			System.out.println("Action");
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (flag == true) {
			return this.list();
		}
		return this.list();
		
	}

	public String list()throws Exception {
		List<NoticeBean> noticeList = this.noticeService.getNoticeBeanList();
		ActionContext context = ActionContext.getContext();
		ValueStack valueStack = context.getValueStack();
		valueStack.set("noticeList",noticeList);
		return "list";
	}
	public String look() throws Exception {
		String noticeId = request.getParameter("noticeId");
		this.noticeBean = this.noticeService.getNoticeBeanByID(noticeId);
		ActionContext context = ActionContext.getContext();
		ValueStack valueStack = context.getValueStack();
		if (this.noticeBean!=null) {
			valueStack.push(this.noticeBean);
		}
		return "look";
	}
	public String check() throws Exception {
		ActionContext context = ActionContext.getContext();
		ValueStack valueStack = context.getValueStack();
		String noticeId = request.getParameter("noticeId");
		Map<Integer, String> stateMap = new HashMap<>();
		stateMap.put(1, "审核已通过");
		stateMap.put(2, "审核未通过");
		this.noticeBean = this.noticeService.getNoticeBeanByID(noticeId);
		this.attch = new File(this.noticeBean.getFilepath());
		this.attchFileName = this.noticeBean.getFilename();
		if (this.noticeBean != null) {
			valueStack.push(this.noticeBean);
			context.put("attch", this.attch);
			context.put("state", stateMap);
			//valueStack.push(this.attch);
			//valueStack.push(this.attchFileName);
		}
		return "check";
	}
	public String checkSave() throws Exception{
		boolean flag= false;
		String state = request.getParameter("state");
		UserBean LoginUser = (UserBean) request.getSession().getAttribute("userBean");
		this.noticeBean.setApproveUser(LoginUser.getUsername());//LoginUser.getUsername()
		this.noticeBean.setApproveTime(Global.getSysTimeStamp());
		this.noticeBean.setIsApprove(Integer.valueOf(state));
		try {
			
			this.noticeService.check(this.noticeBean);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (flag== true) {
			return this.list();
		}
		return NONE;
	}
	
	public String download() throws Exception {
		String noticeId  = request.getParameter("noticeId");
		try {
			this.noticeBean = this.noticeService.getNoticeBeanByID(noticeId);
			File downloadFile = new File(noticeBean.getFilepath());
			if (downloadFile.exists() == true ) {
				response.setContentType("application/octet-stream");
				
				String encode_filename = new String(noticeBean.getFilename().getBytes("UTF-8"),"ISO-8859-1");
				response.setHeader("Content-Disposition",
						"attachment;filename=\"" + encode_filename + "\"");

				InputStream inputStream = new FileInputStream(downloadFile);

				OutputStream outputStream = response.getOutputStream();

				IOUtils.copy(inputStream, outputStream);

				IOUtils.closeQuietly(inputStream);
				IOUtils.closeQuietly(outputStream);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("noticeId为空");
		}
		return this.look();
	}
	

}
