package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bean.UserBean;
import com.bean.UserParamBean;
import com.bean.UserParamId;
import com.bean.UserRoleBean;
import com.bean.UserRoleId;
@Repository(value = "userDao")
@Transactional(rollbackFor = java.lang.Exception.class)
public class UserDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<UserBean> getUserList() {
		List<UserBean> userList = null;
		Session session = null;
		Query query = null;
		String hql = "From UserBean order by userid asc";
		try {
			session = this.sessionFactory.getCurrentSession();
			query = session.createQuery(hql);
			userList = query.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return userList;
	}
	
	public UserBean getUserBeanById(String userid) {
		UserBean userBean = null;
		Session session = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			userBean = (UserBean) session.get(UserBean.class, userid);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return userBean;

	}

	public void deleteUser(String userid) {
		Session session = null;
		String hql = "Select a From UserRoleBean a where a.id.userId = '"+userid+"'";
		Query query = null;
		try {
			session= this.sessionFactory.getCurrentSession();
			UserBean userBean = (UserBean) session.get(UserBean.class, userid);
			session.delete(userBean);
			query = session.createQuery(hql);
			List<UserRoleBean> userRoleBeanList = query.list();
			for (UserRoleBean userRoleBean : userRoleBeanList) {
				session.delete(userRoleBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	public void save(UserBean userBean,String[] roleArray) {
		Session session = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			session.save(userBean);
			
			String string = "notice,mail,display";
			String[] strings = string.split(",");
			for (String string2 : strings) {
				UserParamId param = new UserParamId();
				param.setUserId(userBean.getUserid());
				param.setParameterKey(string2);
				UserParamBean userParamBean = new UserParamBean();
				userParamBean.setId(param);
				userParamBean.setParameterValue("yes");
				session.save(userParamBean);
			}
			for (String roleId : roleArray) {
				UserRoleId id = new UserRoleId();
				id.setUserId(userBean.getUserid());
				id.setRoleId(roleId);
				UserRoleBean userRoleBean = new UserRoleBean();
				userRoleBean.setId(id);
				session.save(userRoleBean);
				session.flush();
			}
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	public void modify(UserBean userBean,String[] roleArray) {
		Session session = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			session.update(userBean);
			for (String roleId : roleArray) {
				UserRoleId id = new UserRoleId();
				id.setUserId(userBean.getUserid());
				id.setRoleId(roleId);
				UserRoleBean userRoleBean = new UserRoleBean();
				userRoleBean.setId(id);
				session.update(userRoleBean);
				session.flush();
			}
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	public List<String> getUserRoleSetById(String userid) {
		List<UserRoleBean> roleList = new ArrayList<>();
		List<String> rolelist = new ArrayList<>();
		String hql = "Select a From UserRoleBean a where a.id.userId = '" + userid + "' ";
		Session session =null;
		Query query = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			query = session.createQuery(hql);
			roleList = query.list();
			for (UserRoleBean userRoleBean : roleList) {
				rolelist.add(userRoleBean.getId().getRoleId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		} 
		return rolelist;
	}

}
