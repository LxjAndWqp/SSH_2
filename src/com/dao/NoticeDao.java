package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bean.NoticeBean;

@Repository(value = "noticeDao")
@Transactional(rollbackFor = java.lang.Exception.class)
public class NoticeDao {
	@Autowired
	private SessionFactory sessionFactory;

	public void save(NoticeBean noticeBean) {
		Session session = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			session.save(noticeBean);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	public NoticeBean getNoticeBeanByID(String noticeId) {
		Session session = null;
		NoticeBean noticeBean = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			noticeBean = (NoticeBean) session.get(NoticeBean.class, new Integer(noticeId));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return noticeBean;
	}

	public void modify(NoticeBean noticeBean) {
		Session session = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			session.update(noticeBean);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	public void delete(NoticeBean noticeBean) {
		Session session = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			session.delete(noticeBean);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
	}

	public List<NoticeBean> getNoticeBeanList() {
		Session session = null;
		List<NoticeBean> noticeList = new ArrayList<>();
		String hql = "From NoticeBean order by noticeId asc";
		Query query = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			query = session.createQuery(hql);
			noticeList = query.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return noticeList;
	}
	
	public List<NoticeBean> getNoticeBeanByTime() {
		Session session = null;
		List<NoticeBean> noticeList = new ArrayList<>();
		String hql = "From NoticeBean order by noticeAddtime desc";
		Query query = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			query = session.createQuery(hql);
			noticeList = query.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return noticeList;
	}

	
}
