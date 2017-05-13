package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bean.MailBean;

@Repository(value = "mailDao")
@Transactional(rollbackFor = java.lang.Exception.class)
public class MailDao {
	@Autowired
	private SessionFactory sessionFactory;

	public void save(MailBean mailBean) {
		Session session = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			session.save(mailBean);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	public List<MailBean> getMailList() {
		Session session = null;
		Query query = null;
		String hql = "From MailBean order by mailId asc";
		List<MailBean> mailList = new ArrayList<>();
		try {
			session = this.sessionFactory.getCurrentSession();
			query = session.createQuery(hql);
			mailList = query.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return mailList;
	}

	public MailBean getMailBeanByID(String mailId) {
		Session session = null;
		MailBean mailBean = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			mailBean = (MailBean)session.get(MailBean.class, Integer.valueOf(mailId));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return mailBean;
	}

	public void modify(MailBean mailBean) {
		Session session = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			session.update(mailBean);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("修改失败");
		}
		
	}

	public void delete(MailBean mailBean) {
		Session session = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			session.delete(mailBean);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("删除失败");
		}
	}

	

	
}
