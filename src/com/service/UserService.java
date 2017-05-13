package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.UserBean;
import com.dao.UserDao;
@Service(value = "userService")
public class UserService {
	@Autowired
	private UserDao userDao;
	
	public List<UserBean> getUserList(){
		return this.userDao.getUserList();
	}
	
	public UserBean getUserBeanById(String userid) {
		if (StringUtils.isEmpty(userid)==true) {
			throw new RuntimeException("参数为空");
		}else{
			return this.userDao.getUserBeanById(userid);
		}
	}
	public List<String> getUserRoleBeanById(String userid) {
		if (StringUtils.isEmpty(userid)==true) {
			throw new RuntimeException("参数为空");
		}else{
			return this.userDao.getUserRoleSetById(userid);
		}
	}
	
	
	public void deleteuser(String userid) {
		if (StringUtils.isEmpty(userid)==true) {
			throw new RuntimeException("参数为空");
		}else{
			this.userDao.deleteUser(userid);
		}
	}

	public void save(UserBean userBean,String[] roleArray) {
		if (userBean==null) {
			throw new RuntimeException("参数为空");
		}else {
			this.userDao.save(userBean,roleArray);
		}
	}

	public void modify(UserBean userBean,String[] roleArray) {
		if (userBean==null) {
			throw new RuntimeException("参数为空");
		}else {
			this.userDao.modify(userBean,roleArray);
		}
	}

	public List<UserBean> searchUserList(String username, String truename, String dept) {
		boolean flag1 = false, flag2 = false, flag3 = false;
		if (username == null || "".equals(username))
			flag1 = true;
		if (truename == null || "".equals(truename))
			flag2 = true;
		if (dept == null || "".equals(dept))
			flag3 = true;
		List<UserBean> userDataList = this.userDao.getUserList();
		List<UserBean> userList = new ArrayList<>();
		for (UserBean userBean : userDataList) {
			if (flag1||userBean.getUsername().equals(username)) {
				if (flag2||userBean.getTruename().equals(truename)) {
					if (flag3||userBean.getDeptId().equals(dept)) {
						userList.add(userBean);
					}
				}
			}
		}	
		return userList;
	}
}
