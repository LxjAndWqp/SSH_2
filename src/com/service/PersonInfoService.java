package com.service;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.PersonInfoDao;

@Service(value = "personInfoService")
public class PersonInfoService {
	
	@Autowired
	private PersonInfoDao personInfoDao;

	public Map<String, String> getUserParam(String userid) {
		if (StringUtils.isEmpty(userid) == true) {
			throw new RuntimeException("传递的参数为空");
		} else {
			return personInfoDao.getUserParmaMap(userid);
		}
	}
	
	public void save(String userid, HttpServletRequest request) {
		if (StringUtils.isEmpty(userid) == true) {
			throw new RuntimeException("传递的参数为空");
		} else {
			Map<String, String> key_valueMap = new HashMap<String, String>();
			Enumeration<String> enumer = request.getParameterNames();
			while (enumer.hasMoreElements()) {
				String param_name = enumer.nextElement();
				String param_value = request.getParameter(param_name);
				if (param_name.equals("userid")) {
					continue;
				} else {
					key_valueMap.put(param_name, param_value);
				}
			}
			personInfoDao.save(userid, key_valueMap);
		}
	}
	
}
