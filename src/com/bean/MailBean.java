package com.bean;
// Generated 2017-4-13 14:07:50 by Hibernate Tools 3.5.0.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_mail" )
public class MailBean implements java.io.Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer mailId;
	@Column(nullable = false)
	private String mailTitle;
	@Column(nullable = false)
	private String senderId;
	@Column(nullable = false)
	private String senderName;
	@Column(nullable = false)
	private String receiverId;
	@Column(nullable = false)
	private String receiverName;
	private String mailContent;
	@Column(name = "filename_1")
	private String filename1;
	@Column(name = "filepath_1")
	private String filepath1;
	@Column(name = "filename_2")
	private String filename2;
	@Column(name = "filepath_2")
	private String filepath2;
	@Column(name = "filename_3")
	private String filename3;
	@Column(name = "filepath_3")
	private String filepath3;
	private Integer status;
	private Integer isReaded;
	private Integer isDeleted;

	public MailBean() {
	}

	public MailBean(String mailTitle, String senderId, String senderName, String receiverId, String receiverName) {
		this.mailTitle = mailTitle;
		this.senderId = senderId;
		this.senderName = senderName;
		this.receiverId = receiverId;
		this.receiverName = receiverName;
	}

	public MailBean(String mailTitle, String senderId, String senderName, String receiverId, String receiverName,
			String mailContent, String filename1, String filepath1, String filename2, String filepath2,
			String filename3, String filepath3, Integer status, Integer isReaded, Integer isDeleted) {
		this.mailTitle = mailTitle;
		this.senderId = senderId;
		this.senderName = senderName;
		this.receiverId = receiverId;
		this.receiverName = receiverName;
		this.mailContent = mailContent;
		this.filename1 = filename1;
		this.filepath1 = filepath1;
		this.filename2 = filename2;
		this.filepath2 = filepath2;
		this.filename3 = filename3;
		this.filepath3 = filepath3;
		this.status = status;
		this.isReaded = isReaded;
		this.isDeleted = isDeleted;
	}

	public Integer getMailId() {
		return this.mailId;
	}

	public void setMailId(Integer mailId) {
		this.mailId = mailId;
	}

	public String getMailTitle() {
		return this.mailTitle;
	}

	public void setMailTitle(String mailTitle) {
		this.mailTitle = mailTitle;
	}

	public String getSenderId() {
		return this.senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getSenderName() {
		return this.senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getReceiverId() {
		return this.receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	public String getReceiverName() {
		return this.receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getMailContent() {
		return this.mailContent;
	}

	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}

	public String getFilename1() {
		return this.filename1;
	}

	public void setFilename1(String filename1) {
		this.filename1 = filename1;
	}

	public String getFilepath1() {
		return this.filepath1;
	}

	public void setFilepath1(String filepath1) {
		this.filepath1 = filepath1;
	}

	public String getFilename2() {
		return this.filename2;
	}

	public void setFilename2(String filename2) {
		this.filename2 = filename2;
	}

	public String getFilepath2() {
		return this.filepath2;
	}

	public void setFilepath2(String filepath2) {
		this.filepath2 = filepath2;
	}

	public String getFilename3() {
		return this.filename3;
	}

	public void setFilename3(String filename3) {
		this.filename3 = filename3;
	}

	public String getFilepath3() {
		return this.filepath3;
	}

	public void setFilepath3(String filepath3) {
		this.filepath3 = filepath3;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsReaded() {
		return this.isReaded;
	}

	public void setIsReaded(Integer isReaded) {
		this.isReaded = isReaded;
	}

	public Integer getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

}
