package com.dao;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bean.UserParamBean;
import com.bean.UserParamId;

@Repository( value = "personInfoDao")
@Transactional(rollbackFor = java.lang.Exception.class)
public class PersonInfoDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public Map<String, String> getUserParmaMap(String userid) {
		Map<String, String> paramMap = new LinkedHashMap<String, String>();
		Session session = null;
		Query query = null;
		String hql = "Select a From UserParamBean a where a.id.userId = '"
				+ userid + "'";
		try {
			session = this.sessionFactory.getCurrentSession();
			query = session.createQuery(hql);
			List<UserParamBean> paramList = query.list();
			for (UserParamBean paramBean : paramList) {
				String param_key = paramBean.getId().getParameterKey();
				String param_value = paramBean.getParameterValue();
				paramMap.put(param_key, param_value);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return paramMap;
	}

	public void save(String userid, Map<String, String> key_valueMap) {
		Session session = null;
		try {
			session = this.sessionFactory.getCurrentSession();

			Set<String> keySet = key_valueMap.keySet();
			Iterator<String> iterator = keySet.iterator();
			while (iterator.hasNext()) {
				String key = iterator.next();
				if (key != null && !key.equals("")) {
					String value = key_valueMap.get(key);

					System.out.println("key = " + key);
					UserParamId id = new UserParamId();
					id.setUserId(userid);
					id.setParameterKey(key);
					
					UserParamBean paramBean = (UserParamBean) session.load(
							UserParamBean.class, id);
					paramBean.setParameterValue(value);
					session.update(paramBean);
					session.flush();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
}
