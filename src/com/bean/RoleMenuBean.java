package com.bean;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_role_menu")
public class RoleMenuBean implements java.io.Serializable {
	@EmbeddedId
	private RoleMenuId id;

	public RoleMenuBean() {
	}

	public RoleMenuBean(RoleMenuId id) {
		this.id = id;
	}

	public RoleMenuId getId() {
		return this.id;
	}

	public void setId(RoleMenuId id) {
		this.id = id;
	}

}
