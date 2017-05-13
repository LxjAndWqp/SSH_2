package com.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.SurveyBean;
import com.bean.UserBean;
import com.dao.SurveyDao;

@Service(value = "surveyService")
public class SurveyService {
	@Autowired
	private SurveyDao surveyDao;

	public List<SurveyBean> getSurveyList() {
		return this.surveyDao.getSurveyList();
	}

	public SurveyBean getSurveyBeanById(String surveyId) {
		if (StringUtils.isEmpty(surveyId) == true) {
			throw new RuntimeException("参数为空");
		} else {
			return surveyDao.getSurveyBeanById(surveyId);
		}
	}

	public void deleteSurvey(String surveyId) {
		if (StringUtils.isEmpty(surveyId) == true) {
			throw new RuntimeException("参数为空");
		} else {
			this.surveyDao.deleteSurvey(surveyId);
		}
	}

	public void save(SurveyBean surveyBean, String[] optionsArray) {
		if (surveyBean == null) {
			throw new RuntimeException("参数为空");
		} else {
			this.surveyDao.save(surveyBean, optionsArray);
		}
	}

	public void modify(SurveyBean surveyBean, String[] optionsArray) {
		if (surveyBean == null) {
			throw new RuntimeException("参数为空");
		} else {
			this.surveyDao.modify(surveyBean, optionsArray);
		}
	}

	public List<String> getSurveyOptionsList(String surveyId) {

		return surveyDao.getSurveyOptionsList(surveyId);
	}

	public void votesave(SurveyBean surveyBean, String[] optionsArray) {
		if (surveyBean == null) {
			throw new RuntimeException("参数为空");
		} else {
			this.surveyDao.votesave(surveyBean, optionsArray);
		}

	}

	public List<String> getOptionsNumberList(String surveyId) {
		return this.surveyDao.getOptionsNumberList(surveyId);
	}

	public void TimeUpdate(SurveyBean surveyBean) {
		if (surveyBean == null) {
			throw new RuntimeException("参数为空");
		} else {
			this.surveyDao.TimeUpdate(surveyBean);
		}

	}

	public void deleteOptions(String options) {
		this.surveyDao.deleteOptions(options);

	}

	public List<SurveyBean> searchSurveyList(String surveyTitle,
			String beginTime, String endTime) {
		boolean flag1 = false, flag2 = false, flag3 = false;
		if (surveyTitle == null || "".equals(surveyTitle))
			flag1 = true;
		if (beginTime == null || "".equals(beginTime))
			flag2 = true;
		if (endTime == null || "".equals(endTime))
			flag3 = true;
		List<SurveyBean> surveyDataList = this.surveyDao.getSurveyList();
		List<SurveyBean> surveyList = new ArrayList<>();
		for (SurveyBean surveyBean : surveyDataList) {
			if (flag1 || surveyBean.getSurveyTitle().equals(surveyTitle)) {
				if (flag2 || surveyBean.getBeginTime().equals(beginTime)) {
					if (flag3 || surveyBean.getEndTime().equals(endTime)) {
						surveyList.add(surveyBean);
					}
				}
			}
		}
		return surveyList;
	}

}
