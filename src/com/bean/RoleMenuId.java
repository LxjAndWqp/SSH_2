package com.bean;

import javax.persistence.Embeddable;

@Embeddable
public class RoleMenuId implements java.io.Serializable {

	private String roleid;
	private String menuid;

	public RoleMenuId() {
	}

	public RoleMenuId(String roleid, String menuid) {
		this.roleid = roleid;
		this.menuid = menuid;
	}

	public String getRoleid() {
		return this.roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getMenuid() {
		return this.menuid;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof RoleMenuId))
			return false;
		RoleMenuId castOther = (RoleMenuId) other;

		return ((this.getRoleid() == castOther.getRoleid()) || (this
				.getRoleid() != null && castOther.getRoleid() != null && this
				.getRoleid().equals(castOther.getRoleid())))
				&& ((this.getMenuid() == castOther.getMenuid()) || (this
						.getMenuid() != null && castOther.getMenuid() != null && this
						.getMenuid().equals(castOther.getMenuid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getRoleid() == null ? 0 : this.getRoleid().hashCode());
		result = 37 * result
				+ (getMenuid() == null ? 0 : this.getMenuid().hashCode());
		return result;
	}

}
