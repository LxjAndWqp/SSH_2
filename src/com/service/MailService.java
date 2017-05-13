package com.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.util.UserDataAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.bean.MailBean;
import com.bean.NoticeBean;
import com.dao.MailDao;
import com.dao.UserDao;
import com.util.Global;

@Service(value = "mailService")
public class MailService {
	@Autowired
	private MailDao mailDao;
	@Autowired
	private UserDao userDao;

	public void save(MailBean mailBean, File file1, String file1FileName, File file2, String file2FileName, File file3,
			String file3FileName, String uploadDir) {
		String filename1 = "";
		String filepath1 = "";
		String filename2 = "";
		String filepath2 = "";
		String filename3 = "";
		String filepath3 = "";
		int isReaded = 0;
		int isDeleted = 0;
		File destFile = null;
		boolean upload_succ = true;
		if (file1FileName != null && !file1FileName.equals("")) {
			//上传附件
			filename1 = file1FileName;
			filepath1 = Global.getSysTimeStamp() + "_" + file1FileName;
			destFile = new File(uploadDir + "/" + filepath1);
			try {
				FileCopyUtils.copy(file1, destFile);
				filepath1=destFile.toString();
				upload_succ = true;
			} catch (IOException e) {
				upload_succ = false;
				throw new RuntimeException(e);
			}
		}
		if (file2FileName != null && !file2FileName.equals("")) {
			//上传附件
			filename2 = file2FileName;
			filepath2 = Global.getSysTimeStamp() + "_" + file2FileName;
			destFile = new File(uploadDir + "/" + filepath2);
			try {
				FileCopyUtils.copy(file2, destFile);
				filepath2=destFile.toString();
				upload_succ = true;
			} catch (IOException e) {
				upload_succ = false;
				throw new RuntimeException(e);
			}
		}
		if (file3FileName != null && !file3FileName.equals("")) {
			//上传附件
			filename3 = file3FileName;
			filepath3 = Global.getSysTimeStamp() + "_" + file3FileName;
			destFile = new File(uploadDir + "/" + filepath3);
			try {
				FileCopyUtils.copy(file3, destFile);
				filepath3=destFile.toString();
				upload_succ = true;
			} catch (IOException e) {
				upload_succ = false;
				throw new RuntimeException(e);
			}
		}
		mailBean.setFilename3(filename3);
		mailBean.setFilepath3(filepath3);
		mailBean.setFilename2(filename2);
		mailBean.setFilepath2(filepath2);
		mailBean.setFilename1(filename1);
		mailBean.setFilepath1(filepath1);
		mailBean.setReceiverName(this.userDao.getUserBeanById(String.valueOf(mailBean.getReceiverId())).getUsername());
		mailBean.setIsDeleted(isDeleted);
		mailBean.setIsReaded(isReaded);
		if (upload_succ == true) {
			this.mailDao.save(mailBean);
		}
	}

	public List<MailBean> getMailList() {
		return this.mailDao.getMailList();
	}

	public List<MailBean> getSendedMailList(String senderId) {
		List<MailBean> dataList = this.getMailList();
		List<MailBean> mailList = new ArrayList<>();
		for (MailBean mailBean : dataList) {
			if (mailBean.getStatus().equals(1)) {
				if (mailBean.getSenderId().equals(senderId)) {
					mailList.add(mailBean);
				}
			}
		}
		return mailList;
	}

	public List<MailBean> getReceiveMailList(String receiverId) {
		List<MailBean> dataList = this.getMailList();
		List<MailBean> mailList = new ArrayList<>();
		for (MailBean mailBean : dataList) {
			if (mailBean.getReceiverId().equals(receiverId)) {
				if (!mailBean.getIsDeleted().equals(1)) {
					mailList.add(mailBean);
				}
			}
		}
		return mailList;
	}

	public MailBean getMailBeanByID(String mailId) {
		if (mailId==null||mailId.equals("")) {
			throw new RuntimeException("mailId参数为空");
		}else {
			return this.mailDao.getMailBeanByID(mailId);
		}
		
	}

	public void modify(MailBean mailBean) {
		if (mailBean == null) {
			throw new RuntimeException("mailBean为空");
		}else {
			this.mailDao.modify(mailBean);
		}
		
	}

	public void delete(String mailId) {
		String filepath = "";
		File destFile = null;
		boolean flag1 = true,flag2 = true , flag3 = true;
		MailBean mailBean = this.getMailBeanByID(mailId);
		if (StringUtils.isEmpty(mailBean.getFilename1()) || mailBean.getFilename1().equals("")) {
			if (StringUtils.isEmpty(mailBean.getFilename2()) || mailBean.getFilename2().equals(""))
				if (StringUtils.isEmpty(mailBean.getFilename3()) || mailBean.getFilename3().equals(""))
							this.mailDao.delete(mailBean);
		}else{
			if (mailBean.getFilename1()!=null) {
				flag1 = false;
				filepath = mailBean.getFilepath1();
				destFile = new File(filepath);
				if (destFile.isFile()&&destFile.exists()) {
					destFile.delete();
					flag1 = true;
				}
			}
			if (mailBean.getFilename2()!=null) {
				flag2 = false;
				filepath = mailBean.getFilepath2();
				destFile = new File(filepath);
				if (destFile.isFile()&&destFile.exists()) {
					destFile.delete();
					flag2 = true;
				}
			}
			if (mailBean.getFilename3()!=null) {
				flag3 = false;
				filepath = mailBean.getFilepath3();
				destFile = new File(filepath);
				if (destFile.isFile()&&destFile.exists()) {
					destFile.delete();
					flag3 = true;
				}
			}
			this.mailDao.delete(mailBean);
		}
		
	}

	public List<MailBean> getRubbishMailList(String userid) {
		List<MailBean> dataList = this.getMailList();
		List<MailBean> mailList = new ArrayList<>();
		for (MailBean mailBean : dataList) {
			if (mailBean.getReceiverId().equals(userid)) {
				if (mailBean.getIsDeleted().equals(1)) {
					mailList.add(mailBean);
				}
			}
		}
		return mailList;
	}

	public List<MailBean> getDraftMailList(String userid) {
		List<MailBean> dataList = this.getMailList();
		List<MailBean> mailList = new ArrayList<>();
		for (MailBean mailBean : dataList) {
			if (mailBean.getSenderId().equals(userid)) {
				if (mailBean.getStatus().equals(0)) {
					mailList.add(mailBean);
				}
			}
		}
		return mailList;
	}
	
	
}
