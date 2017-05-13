package com.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;

import com.bean.NoticeBean;
import com.dao.NoticeDao;
import com.util.Global;

@Service(value = "noticeService")
public class NoticeService {
	@Autowired
	private NoticeDao noticeDao;

	public void save(NoticeBean noticeBean, File attch, String attchFileName,
			String uploadDir) {
		String filename = "";
		String filepath = "";
		int is_approve = 0;
		File destFile = null;
		boolean upload_succ = true;
		if (attchFileName != null && !attchFileName.equals("")) {
			//上传附件
			filename = attchFileName;
			filepath = Global.getSysTimeStamp() + "_" + attchFileName;
			destFile = new File(uploadDir + "/" + filepath);
			try {
				FileCopyUtils.copy(attch, destFile);
				filepath=destFile.toString();
				upload_succ = true;
			} catch (IOException e) {
				upload_succ = false;
				throw new RuntimeException(e);
			}
		}
		noticeBean.setFilename(filename);
		noticeBean.setFilepath(filepath);
		noticeBean.setIsApprove(is_approve);
		if (upload_succ == true) {
			this.noticeDao.save(noticeBean);
		}
	}

	public NoticeBean getNoticeBeanByID(String noticeId) {
		if (StringUtils.isEmpty(noticeId)==true) {
			throw new RuntimeException();
		}else {
			return this.noticeDao.getNoticeBeanByID(noticeId);
		}
		
		
	}
	
	public void modify(NoticeBean noticeBean, File attch, String attchFileName,
			String uploadDir) {
		String filepath = "";
		File destFile = null;
		boolean upload_succ = true;
		if (attchFileName != null && !attchFileName.equals("")) {
			/**
			 * 上传附件
			 */
			filepath = Global.getSysTimeStamp() + "_" + attchFileName;
			destFile = new File(uploadDir + "/" + filepath);
			
			try {
				FileCopyUtils.copy(attch, destFile);
				upload_succ = true;
			} catch (IOException e) {
				upload_succ = false;
				throw new RuntimeException(e);
			}
			noticeBean.setFilepath(destFile.toString());
			noticeBean.setFilename(attchFileName);
		}
		if (upload_succ == true) {
			this.noticeDao.modify(noticeBean);
		}
	}

	public List<NoticeBean> getNoticeBeanList() {
		return this.noticeDao.getNoticeBeanList();
	}

	public void check(NoticeBean noticeBean) {
		this.noticeDao.modify(noticeBean);
	}

	public void delete(String noticeId) {
		String filepath = "";
		File destFile = null;
		boolean flag = false;
		NoticeBean noticeBean = this.getNoticeBeanByID(noticeId);
		if (StringUtils.isEmpty(noticeBean.getFilename()) || noticeBean.getFilename().equals("")) {
			System.out.println("service");
			this.noticeDao.delete(noticeBean);
		}else{
			/**
			 * 删除附件
			 */
			filepath = noticeBean.getFilepath();
			destFile = new File(filepath);
			if (destFile.isFile()&&destFile.exists()) {
				destFile.delete();
				flag = true;
			}
			if (flag == true) {
				System.out.println("Fileservice");
				this.noticeDao.delete(noticeBean);
			}
			
		}
		
		
	}

	
	
	
}
