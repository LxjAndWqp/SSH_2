package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bean.SurveyBean;
import com.bean.SurveyOptionsBean;
import com.bean.SurveyOptionsIdBean;

@Repository(value = "surveyDao")
@Transactional(rollbackFor = java.lang.Exception.class)
public class SurveyDao {

	@Autowired
	private SessionFactory sessionFactory;

	public List<SurveyBean> getSurveyList() {
		List<SurveyBean> surveyList = null;
		Session session = null;
		Query query = null;
		String hql = "From SurveyBean order by surveyId asc";
		try {
			session = this.sessionFactory.getCurrentSession();
			query = session.createQuery(hql);
			surveyList = query.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return surveyList;
	}

	public SurveyBean getSurveyBeanById(String surveyId) {
		SurveyBean surveyBean = null;
		Session session = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			surveyBean = (SurveyBean) session.get(SurveyBean.class,
					new Integer(surveyId));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return surveyBean;

	}

	public void deleteSurvey(String surveyId) {
		Session session = null;
		String hql = "Select a From SurveyOptionsBean a where a.id.surveyId = '"
				+ surveyId + "'";
		Query query = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			SurveyBean surveyBean = (SurveyBean) session.get(SurveyBean.class,
					new Integer(surveyId));
			session.delete(surveyBean);
			query = session.createQuery(hql);
			List<SurveyOptionsBean> surveyOptionsList = query.list();
			for (SurveyOptionsBean surveyOptionsBean : surveyOptionsList) {
				session.delete(surveyOptionsBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}

	}

	public void save(SurveyBean surveyBean, String[] optionsArray) {
		Session session = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			session.save(surveyBean);
			if (optionsArray != null) {
				for (String options : optionsArray) {
					SurveyOptionsIdBean id = new SurveyOptionsIdBean();
					id.setSurveyId(surveyBean.getSurveyId());
					id.setOptions(options);

					SurveyOptionsBean surveyOptionsBean = new SurveyOptionsBean();
					surveyOptionsBean.setId(id);
					surveyOptionsBean.setNumber("0");
					session.save(surveyOptionsBean);
					session.flush();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public void modify(SurveyBean surveyBean, String[] optionsArray) {
		Session session = null;
		this.deleteSurvey(surveyBean.getSurveyId().toString());
		try {
			session = this.sessionFactory.getCurrentSession();
			session.save(surveyBean);
			for (String options : optionsArray) {
				SurveyOptionsIdBean id = new SurveyOptionsIdBean();
				id.setSurveyId(surveyBean.getSurveyId());
				id.setOptions(options);

				SurveyOptionsBean surveyOptionsBean = new SurveyOptionsBean();
				surveyOptionsBean.setId(id);
				surveyOptionsBean.setNumber("0");
				session.save(surveyOptionsBean);
				session.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	public List<String> getSurveyOptionsList(String surveyId) {
		Session session = null;
		Query query = null;
		List<String> optionsArray = new ArrayList<String>();
		String hql = "Select a From SurveyOptionsBean a where a.id.surveyId='"
				+ surveyId + "'";
		try {
			session = this.sessionFactory.getCurrentSession();
			query = session.createQuery(hql);
			List<SurveyOptionsBean> surveyOptionsBeanList = query.list();
			for (SurveyOptionsBean surveyOptionsBean : surveyOptionsBeanList) {
				String options = surveyOptionsBean.getId().getOptions();
				optionsArray.add(options);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return optionsArray;
	}

	public void votesave(SurveyBean surveyBean, String[] optionsArray) {
		Session session = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			for (String options : optionsArray) {
				String number = this.getOptionsNumber(options);
				int int_number = Integer.parseInt(number);
				SurveyOptionsIdBean id = new SurveyOptionsIdBean();
				id.setSurveyId(surveyBean.getSurveyId());
				id.setOptions(options);

				SurveyOptionsBean surveyOptionsBean = (SurveyOptionsBean) session
						.load(SurveyOptionsBean.class, id);
				surveyOptionsBean.setNumber(String.valueOf((int_number + 1)));
				session.update(surveyOptionsBean);
				session.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	public String getOptionsNumber(String options) {
		Session session = null;
		Query query = null;
		String number = null;
		String hql = "Select a From SurveyOptionsBean a where a.id.options = '"
				+ options + "' ";
		try {
			session = this.sessionFactory.getCurrentSession();
			query = session.createQuery(hql);
			List<SurveyOptionsBean> surveyOptionsBeanList = query.list();
			for (SurveyOptionsBean surveyOptionsBean : surveyOptionsBeanList) {
				number = surveyOptionsBean.getNumber();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return number;
	}

	public void TimeUpdate(SurveyBean surveyBean) {
		Session session = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			session.update(surveyBean);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}

	}

	public List<String> getOptionsNumberList(String surveyId) {
		Session session = null;
		Query query = null;
		List<String> numberList = new ArrayList<String>();
		String hql = "Select a From SurveyOptionsBean a where a.id.surveyId = '"
				+ surveyId + "' ";
		try {
			session = this.sessionFactory.getCurrentSession();
			query = session.createQuery(hql);
			List<SurveyOptionsBean> surveyOptionsBeanList = query.list();
			for (SurveyOptionsBean surveyOptionsBean : surveyOptionsBeanList) {
				String number = surveyOptionsBean.getNumber();
				numberList.add(number);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return numberList;
	}

	public void deleteOptions(String options) {
		Session session = null;
		String hql = "Select a From SurveyOptionsBean a where a.id.options = '"
				+ options + "'";
		Query query = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			query = session.createQuery(hql);
			List<SurveyOptionsBean> surveyOptionsList = query.list();
			for (SurveyOptionsBean surveyOptionsBean : surveyOptionsList) {
				session.delete(surveyOptionsBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}

	}

}
