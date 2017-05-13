package com.service;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.MenuBean;
import com.dao.MenuDao;

@Service(value = "menuService")
public class MenuService {
	@Autowired
	private MenuDao menuDao;

	public List<MenuBean> getMenuMap() {
		return menuDao.getMenuMap();
	}

	public void save(MenuBean menuBean) {
		if (StringUtils.isEmpty(menuBean.getMenuId()) == true) {
			throw new RuntimeException("参数为空");
		} else {
			menuDao.save(menuBean);
		}
	}

	public MenuBean getMenuBeanByID(String menuId) {
		if (menuId==null||menuId.equals("")) {
			throw new RuntimeException("参数为空");
		}else {
			return menuDao.getMenuBeanByID(menuId);
		}
	}

	public void modify(MenuBean menuBean) {
		if (StringUtils.isEmpty(menuBean.getMenuId()) == true) {
			throw new RuntimeException("参数为空");
		}else {
			menuDao.modify(menuBean);
		}
	}

	public boolean delete(String menuId) {
		boolean  flag = false;
		if (menuId==null||menuId.equals("")) {
			throw new RuntimeException("参数为空");
		}else {
			int useMenu = this.menuDao.getUseMenuNumber(menuId);
			if (useMenu>0) {
				throw new RuntimeException("菜单正在被使用中");
			}else {
				flag = menuDao.delete(menuId);
			}
		}
		return flag;
	}

	

}
