package com.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.context.internal.ThreadLocalSessionContext;
import org.hibernate.hql.internal.ast.HqlASTFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bean.DataBean;

@Repository(value = "dataDao")
@Transactional(rollbackFor = java.lang.Exception.class)
public class DataDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public void save(DataBean dataBean) {
		Session session = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			session.save(dataBean);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	public void modify(DataBean dataBean) {
		Session session = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			session.update(dataBean);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	public DataBean getDataBeanByID(String dataId) {
		Session session = null;
		DataBean dataBean = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			dataBean = (DataBean) session.get(DataBean.class, new Integer(dataId));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return dataBean;
	}
	
	public void delete(String dataId) {
		Session session = null;
		DataBean dataBean = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			dataBean = this.getDataBeanByID(dataId);
			session.delete(dataBean);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	public List<DataBean> getDataBeanList() {
		Session session = null;
		List<DataBean> dataList = null;
		Query query = null;
		String hql = "From DataBean order by dataid asc";
		try {
			session = this.sessionFactory.getCurrentSession();
			query = session.createQuery(hql);
			dataList = query.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return dataList;
	}

	
}
