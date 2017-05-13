package com.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;




@Entity
@Table(name = "t_role")
public class RoleBean implements java.io.Serializable {
	@Id
	@Column(name = "role_id")
	private String roleId;
	@Column(name = "role_name")
	private String roleName;
	@Column(name = "role_remark")
	private String roleRemark;

	public RoleBean() {
	}

	public RoleBean(String roleId) {
		this.roleId = roleId;
	}

	public RoleBean(String roleId, String roleName, String roleRemark) {
		this.roleId = roleId;
		this.roleName = roleName;
		this.roleRemark = roleRemark;
	}

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleRemark() {
		return this.roleRemark;
	}

	public void setRoleRemark(String roleRemark) {
		this.roleRemark = roleRemark;
	}

}
