package com.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.bean.MailBean;
import com.bean.NoticeBean;
import com.bean.UserBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;
import com.service.MailService;
import com.service.NoticeService;
import com.service.UserService;
import com.util.BaseAction;
import com.util.Global;

import sun.security.util.PropertyExpander.ExpandException;

@ParentPackage(value = "default")
@Namespace(value = "/")
@Action(value = "MailAction", results = {
		@Result(name = "sendedlist", location = "/admin/mail/sendedlist.jsp"),
		@Result(name = "receivelist", location = "/admin/mail/receivelist.jsp"),
		@Result(name = "draftlist", location = "/admin/mail/draftlist.jsp"),
		@Result(name = "add", location = "/admin/mail/add.jsp"),
		@Result(name = "look", location = "/admin/mail/look.jsp"),
		@Result(name = "reply", location = "/admin/mail/reply.jsp"),
		@Result(name = "rubbishlist", location = "/admin/mail/rubbishlist.jsp") })
public class MailAction extends BaseAction implements ModelDriven<MailBean> {
	@Autowired
	private MailService mailService;
	
	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}
	
	@Autowired
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	private MailBean mailBean;
	/**
	 * 上传附件时，使用
	 */
	private File file1;
	private String file1FileName;
	private File file2;
	private String file2FileName;
	private File file3;
	private String file3FileName;
	
	public File getFile1() {
		return file1;
	}

	public void setFile1(File file1) {
		this.file1 = file1;
	}


	public File getFile2() {
		return file2;
	}

	public void setFile2(File file2) {
		this.file2 = file2;
	}


	public File getFile3() {
		return file3;
	}

	public void setFile3(File file3) {
		this.file3 = file3;
	}


	public String getFile1FileName() {
		return file1FileName;
	}

	public void setFile1FileName(String file1FileName) {
		this.file1FileName = file1FileName;
	}

	public String getFile2FileName() {
		return file2FileName;
	}

	public void setFile2FileName(String file2FileName) {
		this.file2FileName = file2FileName;
	}

	public String getFile3FileName() {
		return file3FileName;
	}

	public void setFile3FileName(String file3FileName) {
		this.file3FileName = file3FileName;
	}

	@Override
	public MailBean getModel() {
		if (mailBean == null) {
			mailBean = new MailBean();
		}
		return mailBean;
	}

	@Override
	public String execute() throws Exception {
		return null;
	}

	public String add() throws Exception {
		UserBean LoginUser = (UserBean) session.getAttribute("userBean");
		List<UserBean> dataList = this.userService.getUserList();
		List<UserBean> userList = new ArrayList<>();
		for (UserBean userBean : dataList ) {
			if (!userBean.getUserid().equals(LoginUser.getUserid())) {
				userList.add(userBean);
			}
		}
		request.setAttribute("userList", userList);
		return "add";
	}

	public String save() throws Exception {
		String uploadDir = this.servletContext.getRealPath("/upload/mail");
		boolean flag = false;
		UserBean LoginUser = (UserBean) session.getAttribute("userBean");
		String status = request.getParameter("status");
		this.mailBean.setStatus(Integer.valueOf(status));
		this.mailBean.setSenderId(LoginUser.getUserid());
		this.mailBean.setSenderName(LoginUser.getUsername());
		try {
			this.mailService.save(this.mailBean, this.file1,
					this.file1FileName,this.file2,
					this.file2FileName,this.file3,
					this.file3FileName, uploadDir);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (flag==true) {
			return this.add();
		}
		return NONE;
	}

	public String sendedList () throws Exception{
		UserBean LoginUser = (UserBean) session.getAttribute("userBean");
		List<MailBean> mailList = this.mailService.getSendedMailList(LoginUser.getUserid());
		ActionContext context = ActionContext.getContext();
		ValueStack valueStack = context.getValueStack();
		
		context.put("mailList", mailList);
		return "sendedlist";
	}
	
	public String receiveList () throws Exception{
		UserBean LoginUser = (UserBean) session.getAttribute("userBean");
		List<MailBean> mailList = this.mailService.getReceiveMailList(LoginUser.getUserid());
		ActionContext context = ActionContext.getContext();
		ValueStack valueStack = context.getValueStack();
		context.put("mailList", mailList);
		return "receivelist";
	}
	
	public String rubbishList () throws Exception{
		UserBean LoginUser = (UserBean) session.getAttribute("userBean");
		List<MailBean> mailList = this.mailService.getRubbishMailList(LoginUser.getUserid());
		ActionContext context = ActionContext.getContext();
		ValueStack valueStack = context.getValueStack();
		context.put("mailList", mailList);
		return "rubbishlist";
	}
	public String draftList () throws Exception{
		UserBean LoginUser = (UserBean) session.getAttribute("userBean");
		List<MailBean> mailList = this.mailService.getDraftMailList(LoginUser.getUserid());
		ActionContext context = ActionContext.getContext();
		context.put("mailList", mailList);
		return "draftlist";
	}
	
	public String look() throws Exception {
		String mailId = request.getParameter("mailId");
		UserBean LoginUser = (UserBean) session.getAttribute("userBean");
		this.mailBean = this.mailService.getMailBeanByID(mailId);
		if (mailBean.getIsReaded().equals(0)) {
			this.mailBean.setIsReaded(1);
			this.mailService.modify(this.mailBean);
		}
		ActionContext context = ActionContext.getContext();
		ValueStack valueStack = context.getValueStack();
		context.put("userid", LoginUser.getUserid());
		if (this.mailBean!=null) {
			valueStack.push(this.mailBean);
		}
		return "look";
	}
	
	public String download() throws Exception {
		String filename  = request.getParameter("filename");
		String filepath  = request.getParameter("filepath");
		try {
			File downloadFile = new File(filepath);
			if (downloadFile.exists() == true ) {
				response.setContentType("application/octet-stream");
				
				String encode_filename = new String(filename.getBytes("UTF-8"),"ISO-8859-1");
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
			throw new RuntimeException("文件为空");
		}
		return this.look();
	}
	
	public String del() throws Exception {
		String mailId = request.getParameter("mailId");

		boolean flag = false;
		try {
			this.mailService.delete(mailId);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (flag == true) {
			return this.receiveList();
		}
		return this.receiveList();
	}
	
	public String rubbish() throws Exception {
		String mailId = request.getParameter("mailId");
		this.mailBean = this.mailService.getMailBeanByID(mailId);
		boolean flag = false;
		try {
			this.mailBean.setIsDeleted(1);
			this.mailService.modify(mailBean);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (flag == true) {
			return this.receiveList();
		}
		return this.receiveList();
	}
	
	public String back() throws Exception {
		String mailId = request.getParameter("mailId");
		this.mailBean = this.mailService.getMailBeanByID(mailId);
		boolean flag = false;
		try {
			this.mailBean.setIsDeleted(0);
			this.mailService.modify(mailBean);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (flag == true) {
			return this.rubbishList();
		}
		return this.rubbishList();
	}
	
	public String sendDel() throws Exception {
		String mailId = request.getParameter("mailId");
		this.mailBean = this.mailService.getMailBeanByID(mailId);
		boolean flag = false;
		try {
			this.mailBean.setStatus(2);
			this.mailService.modify(mailBean);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (flag == true) {
			return this.sendedList();
		}
		return this.sendedList();
	}
	
	public String reply() throws Exception{
		String mailId = request .getParameter("mailId");
		this.mailBean = this.mailService.getMailBeanByID(mailId);
		ActionContext context = ActionContext.getContext();
		ValueStack valueStack = context.getValueStack();
		String mailTitle = "回复："+this.mailBean.getMailTitle();
		String receiverId = this.mailBean.getSenderId();
		String receiverName = this.mailBean.getSenderName();
		String mailContent = this.mailBean.getMailContent() +"<p>回复:</p>";
		valueStack.set("mailTitle", mailTitle);
		valueStack.set("receiverId", receiverId);
		valueStack.set("receiverName", receiverName);
		valueStack.set("mailContent", mailContent);
		return "reply";
	}
	
	public String again() throws Exception{
		String mailId = request .getParameter("mailId");
		this.mailBean = this.mailService.getMailBeanByID(mailId);
		ActionContext context = ActionContext.getContext();
		ValueStack valueStack = context.getValueStack();
		String mailTitle = this.mailBean.getMailTitle();
		String receiverId = this.mailBean.getReceiverId();
		String receiverName = this.mailBean.getReceiverName();
		String mailContent = this.mailBean.getMailContent();
		valueStack.set("mailTitle", mailTitle);
		valueStack.set("receiverId", receiverId);
		valueStack.set("receiverName", receiverName);
		valueStack.set("mailContent", mailContent);
		return "reply";
	}
}
