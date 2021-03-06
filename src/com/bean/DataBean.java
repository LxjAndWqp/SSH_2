package com.bean;
// Generated 2017-4-4 12:35:16 by Hibernate Tools 4.0.0.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * TData generated by hbm2java
 */


@Entity
@Table(name = "t_data")
public class DataBean implements java.io.Serializable {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer dataId;
	@Column(nullable = false)
	private String dataName;
	@Column(nullable = false)
	private String dataType;
	@Column(nullable = false)
	private int dataLength;
	@Column(nullable = false)
	private int isEmpty;
	@Column(nullable = false)
	private String isScope;
	@Column(nullable = false)
	private int parentId;

	public DataBean() {
	}

	public DataBean(String dataName, String dataType, int dataLength, int isEmpty, String isScope, int parentId) {
		this.dataName = dataName;
		this.dataType = dataType;
		this.dataLength = dataLength;
		this.isEmpty = isEmpty;
		this.isScope = isScope;
		this.parentId = parentId;
	}

	public Integer getDataId() {
		return this.dataId;
	}

	public void setDataId(Integer dataId) {
		this.dataId = dataId;
	}

	public String getDataName() {
		return this.dataName;
	}

	public void setDataName(String dataName) {
		this.dataName = dataName;
	}

	public String getDataType() {
		return this.dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public int getDataLength() {
		return this.dataLength;
	}

	public void setDataLength(int dataLength) {
		this.dataLength = dataLength;
	}

	public int getIsEmpty() {
		return this.isEmpty;
	}

	public void setIsEmpty(int isEmpty) {
		this.isEmpty = isEmpty;
	}

	public String getIsScope() {
		return this.isScope;
	}

	public void setIsScope(String isScope) {
		this.isScope = isScope;
	}

	public int getParentId() {
		return this.parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

}
