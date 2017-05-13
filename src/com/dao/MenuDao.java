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

@Repository(value = "menuDao")
@Transactional(rollbackFor = java.lang.Exception.class)
public class MenuDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	public  List<MenuBean> getMenuMap() {
		List<MenuBean> menuList = null;
		Session session = null;
		Query query = null;
		String hql = "From MenuBean order by menuId asc";
		try {
			session = this.sessionFactory.getCurrentSession();
			query = session.createQuery(hql);
			menuList = query.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return menuList;
	}

	public void save(MenuBean menuBean) {
		Session session = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			session.save(menuBean);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
	}
	public void modify(MenuBean menuBean) {
		Session session = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			session.update(menuBean);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	public MenuBean getMenuBeanByID(String menuId) {
		MenuBean menuBean = null;
		Session session = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			menuBean = (MenuBean) session.get(MenuBean.class, menuId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return menuBean;
	}

	public boolean delete(String menuId) {
		Session session = null;
		boolean flag = false;
		try {
			session = this.sessionFactory.getCurrentSession();
			MenuBean menuBean = (MenuBean)session.get(MenuBean.class,menuId);
			session.delete(menuBean);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return flag;
	}

	public Integer getUseMenuNumber(String menuId) {
		Session session = null;
		List<RoleMenuBean> countUseMenu = new ArrayList<>();
		String hql = "Select a From RoleMenuBean a where a.id.menuid = '"+menuId+"'";
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
