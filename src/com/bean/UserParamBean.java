package com.bean;
// Generated 2017-4-12 17:05:44 by Hibernate Tools 3.5.0.Final

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_user_parameter")
public class UserParamBean implements java.io.Serializable {
	@EmbeddedId
	private UserParamId id;
	@Column(name = "parameter_value")
	private String parameterValue;

	public UserParamBean() {
	}

	public UserParamBean(UserParamId id) {
		this.id = id;
	}

	public UserParamBean(UserParamId id, String parameterValue) {
		this.id = id;
		this.parameterValue = parameterValue;
	}

	public UserParamId getId() {
		return this.id;
	}

	public void setId(UserParamId id) {
		this.id = id;
	}

	public String getParameterValue() {
		return this.parameterValue;
	}

	public void setParameterValue(String parameterValue) {
		this.parameterValue = parameterValue;
	}

}
