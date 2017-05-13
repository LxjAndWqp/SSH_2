package com.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.DataBean;
import com.dao.DataDao;

@Service(value = "dataService")
public class DataService {
	@Autowired
	private DataDao dataDao;

	public void save(DataBean dataBean) {
		if (dataBean == null) {
			throw new RuntimeException();
		}else{
			this.dataDao.save(dataBean);
		}
	}
	
	public void modify(DataBean dataBean) {
		if (dataBean == null) {
			throw new RuntimeException();
		}else {
			this.dataDao.modify(dataBean);
		}
	}
	
	public void delete(String dataId) {
		if (StringUtils.isEmpty(dataId)==true) {
			throw new RuntimeException("参数dataID为空");
		}else {
			this.dataDao.delete(dataId);
		}
	}
	
	public DataBean getDataBeanByID(String dataId) {
		if (StringUtils.isEmpty(dataId)==true) {
			throw new RuntimeException("参数dataID为空");
		}else {
			return this.dataDao.getDataBeanByID(dataId);
		}
	}
	
	public List<DataBean> getDataBeanList() {
		return this.dataDao.getDataBeanList();
	}

	public List<DataBean> getDataBeanListByName(String name) {
		List<DataBean> dataList = this.dataDao.getDataBeanList();
		List<DataBean> nameList = new ArrayList<>();
		if (StringUtils.isEmpty(name)) {
			throw new RuntimeException("name参数为空");
		}else {
			int pId = -1 ;
			for (DataBean dataBean : dataList) {
				if (dataBean.getDataName().equals(name)) {
					pId = dataBean.getDataId();
				}
			}
			for (DataBean dataBean : dataList) {
				if (dataBean.getParentId() == pId) {
					nameList.add(dataBean);
				}
			}
		}
		return nameList;
		
	}
	
	
	
	
}
