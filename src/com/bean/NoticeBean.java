package com.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_notice")
public class NoticeBean implements java.io.Serializable {
	@Id
	@Column(name = "notice_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer noticeId;
	@Column(name = "notice_title", nullable = false)
	private String noticeTitle;
	@Column(name = "notice_adduser", nullable = false)
	private String noticeAdduser;
	@Column(name = "notice_addtime", nullable = false)
	private String noticeAddtime;
	@Column(name = "notice_content", nullable = false)
	private String noticeContent;
	private String filename;
	private String filepath;
	@Column(name = "is_approve")
	private Integer isApprove;
	@Column(name = "approve_user")
	private String approveUser;
	@Column(name = "approve_time")
	private String approveTime;

	public NoticeBean() {
	}

	public NoticeBean(String noticeTitle, String noticeAdduser,
			String noticeAddtime, String noticeContent) {
		this.noticeTitle = noticeTitle;
		this.noticeAdduser = noticeAdduser;
		this.noticeAddtime = noticeAddtime;
		this.noticeContent = noticeContent;
	}

	public NoticeBean(String noticeTitle, String noticeAdduser,
			String noticeAddtime, String noticeContent, String filename,
			String filepath, Integer isApprove, String approveUser,
			String approveTime) {
		this.noticeTitle = noticeTitle;
		this.noticeAdduser = noticeAdduser;
		this.noticeAddtime = noticeAddtime;
		this.noticeContent = noticeContent;
		this.filename = filename;
		this.filepath = filepath;
		this.isApprove = isApprove;
		this.approveUser = approveUser;
		this.approveTime = approveTime;
	}

	public Integer getNoticeId() {
		return this.noticeId;
	}

	public void setNoticeId(Integer noticeId) {
		this.noticeId = noticeId;
	}

	public String getNoticeTitle() {
		return this.noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeAdduser() {
		return this.noticeAdduser;
	}

	public void setNoticeAdduser(String noticeAdduser) {
		this.noticeAdduser = noticeAdduser;
	}

	public String getNoticeAddtime() {
		return this.noticeAddtime;
	}

	public void setNoticeAddtime(String noticeAddtime) {
		this.noticeAddtime = noticeAddtime;
	}

	public String getNoticeContent() {
		return this.noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilepath() {
		return this.filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public Integer getIsApprove() {
		return this.isApprove;
	}

	public void setIsApprove(Integer isApprove) {
		this.isApprove = isApprove;
	}

	public String getApproveUser() {
		return this.approveUser;
	}

	public void setApproveUser(String approveUser) {
		this.approveUser = approveUser;
	}

	public String getApproveTime() {
		return this.approveTime;
	}

	public void setApproveTime(String approveTime) {
		this.approveTime = approveTime;
	}

}
