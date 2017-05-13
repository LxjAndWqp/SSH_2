package com.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bean.MenuBean;
import com.bean.RoleBean;
import com.bean.RoleMenuBean;
import com.bean.RoleMenuId;

@Repository(value = "roleDao")
@Transactional(rollbackFor = java.lang.Exception.class)
public class RoleDao {
	@Autowired
	private SessionFactory sessionFactory;

	public void save(RoleBean roleBean, String role_menuid) {
		Session session = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			session.save(roleBean);
			String[] menuid_array = role_menuid.split(",");
			for (String menuId : menuid_array) {
				RoleMenuId id = new RoleMenuId();
				id.setRoleid(roleBean.getRoleId());
				id.setMenuid(menuId);
				RoleMenuBean roleMenuBean = new RoleMenuBean();
				roleMenuBean.setId(id);
				session.save(roleMenuBean);
				session.flush();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		
	}

	public void modify(RoleBean roleBean, String role_menuid) {
		Session session = null;
		Query query = null;
		this.delete(roleBean.getRoleId());
		try {
			session = this.sessionFactory.getCurrentSession();
			session.save(roleBean);
			String[] menuid_array = role_menuid.split(",");
			for (String string : menuid_array) {
				RoleMenuId id = new RoleMenuId();
				id.setRoleid(roleBean.getRoleId());
				id.setMenuid(string);
				RoleMenuBean roleMenuBean = new RoleMenuBean();
				roleMenuBean.setId(id);
				session.save(roleMenuBean);
				session.flush();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
	}

	public void delete(String roleId) {
		Session session = null;
		String hql = "Select a From RoleMenuBean a where a.id.roleid = '"+roleId+"'";
		Query query = null;
		try {
			session= this.sessionFactory.getCurrentSession();
			RoleBean roleBean = (RoleBean) session.get(RoleBean.class, roleId);
			session.delete(roleBean);
			query = session.createQuery(hql);
			List<RoleMenuBean> roleMenuBeanList = query.list();
			for (RoleMenuBean roleMenuBean : roleMenuBeanList) {
				session.delete(roleMenuBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}

	}

	public RoleBean getRoleById(String roleId) {
		RoleBean roleBean = null;
		Session session = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			roleBean = (RoleBean) session.get(RoleBean.class, roleId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return roleBean;
	}

	public List<RoleBean> getRoleList() {
		Session session = null;
		List<RoleBean> roleList = null;
		Query query = null;
		String hql = "From RoleBean order by roleId asc";
		try {
			session = this.sessionFactory.getCurrentSession();
			query = session.createQuery(hql);
			roleList = query.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return roleList;
	}

	

	public List<MenuBean> getDataMenuList() {
		List<MenuBean> menuList =null;
		Session session = null;
		Query query = null;
		String hql = "From MenuBean order by menuId asc";
		try {
			session = this.sessionFactory.getCurrentSession();
			query = session.createQuery(hql);
			menuList = query.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		return menuList;
	}

	public Set<String> getRoleMunuSetById(String role_id) {
		List<RoleMenuBean> menuList = new ArrayList<>();
		Set<String> menuSet = new HashSet<>();
		String hql = "Select a From RoleMenuBean a where a.id.roleid = '" + role_id + "' ";
		Session session =null;
		Query query = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			query = session.createQuery(hql);
			menuList = query.list();
			for (RoleMenuBean menuBean : menuList) {
				menuSet.add(menuBean.getId().getMenuid());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		} 
		return menuSet;
	}

	public int getUseRoleNumber(String roleId) {
		Session session = null;
		List<RoleMenuBean> countUseMenu = new ArrayList<>();
		String hql = "Select a From UserRoleBean a where a.id.roleId = '"+roleId+"'";
		Query query = null;
		try {
			session= this.sessionFactory.getCurrentSession();
			query = session.createQuery(hql);
			countUseMenu = query.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}

		return countUseMenu.size();
	}
	

}
