package com.bean;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_user_role")
public class UserRoleBean implements java.io.Serializable {
	@EmbeddedId
	private UserRoleId id;
	
	
	public UserRoleBean() {
	}

	public UserRoleBean(UserRoleId id) {
		this.id = id;
	}

	public UserRoleId getId() {
		return this.id;
	}

	public void setId(UserRoleId id) {
		this.id = id;
	}

}
