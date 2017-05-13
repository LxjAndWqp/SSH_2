package com.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_userinfo")
public class UserBean implements java.io.Serializable {
	@Id
	private String userid;
	private String username;
	private String password;
	private String truename;
	private String usersex;
	private String userage;
	@Column(name = "dept_id")
	private String deptId;
	private String salary;
	private String telphone;
	private String address;
	private String birthday;
	private String mail;

	public UserBean() {
	}

	public UserBean(String userid) {
		this.userid = userid;
	}

	public UserBean(String userid, String username, String password,
			String truename, String usersex, String userage, String deptId,
			String salary, String telphone, String address, String birthday,
			String mail) {
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.truename = truename;
		this.usersex = usersex;
		this.userage = userage;
		this.deptId = deptId;
		this.salary = salary;
		this.telphone = telphone;
		this.address = address;
		this.birthday = birthday;
		this.mail = mail;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTruename() {
		return this.truename;
	}

	public void setTruename(String truename) {
		this.truename = truename;
	}

	public String getUsersex() {
		return this.usersex;
	}

	public void setUsersex(String usersex) {
		this.usersex = usersex;
	}

	public String getUserage() {
		return this.userage;
	}

	public void setUserage(String userage) {
		this.userage = userage;
	}

	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getSalary() {
		return this.salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getTelphone() {
		return this.telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBirthday() {
		return this.birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

}
