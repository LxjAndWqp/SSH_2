package com.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_menu")
public class MenuBean implements Serializable {
	@Id
	@Column(name = "menu_id")
	private String menuId;
	@Column(name = "menu_name")
	private String menuName;
	@Column(name = "menu_href")
	private String menuHref;
	@Column(name = "menu_target")
	private String menuTarget;
	private Integer parentid;
	private Integer grade;
	private Integer isleaf;

	public MenuBean() {
	}

	public MenuBean(String menuId) {
		this.menuId = menuId;
	}

	public MenuBean(String menuId, String menuName, String menuHref,
			String menuTarget, Integer parentid, Integer grade, Integer isleaf) {
		this.menuId = menuId;
		this.menuName = menuName;
		this.menuHref = menuHref;
		this.menuTarget = menuTarget;
		this.parentid = parentid;
		this.grade = grade;
		this.isleaf = isleaf;
	}

	public String getMenuId() {
		return this.menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuHref() {
		return this.menuHref;
	}

	public void setMenuHref(String menuHref) {
		this.menuHref = menuHref;
	}

	public String getMenuTarget() {
		return this.menuTarget;
	}

	public void setMenuTarget(String menuTarget) {
		this.menuTarget = menuTarget;
	}

	public Integer getParentid() {
		return this.parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

	public Integer getGrade() {
		return this.grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public Integer getIsleaf() {
		return this.isleaf;
	}

	public void setIsleaf(Integer isleaf) {
		this.isleaf = isleaf;
	}

}
