package com.bean;

import javax.persistence.Column;
import javax.persistence.Embeddable;

// Generated 2017-4-11 19:08:18 by Hibernate Tools 4.0.0.Final

@Embeddable
public class UserParamId implements java.io.Serializable {
	
	@Column(name = "user_id")
	private String userId;
	@Column(name = "parameter_key")
	private String parameterKey;
	

	public UserParamId() {
	}

	public UserParamId(String userId, String parameterKey) {
		this.userId = userId;
		this.parameterKey = parameterKey;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getParameterKey() {
		return this.parameterKey;
	}

	public void setParameterKey(String parameterKey) {
		this.parameterKey = parameterKey;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((parameterKey == null) ? 0 : parameterKey.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserParamId other = (UserParamId) obj;
		if (parameterKey == null) {
			if (other.parameterKey != null)
				return false;
		} else if (!parameterKey.equals(other.parameterKey))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}


	
}
