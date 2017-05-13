package com.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.el.ArrayELResolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.MenuBean;
import com.bean.NoticeBean;
import com.dao.NoticeDao;
import com.dao.RoleDao;
import com.dao.UserDao;
@Service(value = "mainService")
public class MainService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private NoticeDao noticeDao;
	
	public List<Map<String, Object>> getMenu_TreeList(String userid) {
		List<Map<String, Object>> Menu_treeList = new ArrayList<>();
		List<String> user_role_list = this.userDao.getUserRoleSetById(userid);
		Set<String> role_menu_set =null;
		List<MenuBean> menuList = this.roleDao.getDataMenuList(); 
		for (String roleId : user_role_list) {
			role_menu_set = this.roleDao.getRoleMunuSetById(roleId);
		}
		for (MenuBean menuBean : menuList) {
			Map<String, Object> treeNodeMap = new LinkedHashMap<String,Object>();
			treeNodeMap.put("id", (String)menuBean.getMenuId());
			treeNodeMap.put("name", (String)menuBean.getMenuName());
			treeNodeMap.put("pId", String.valueOf(menuBean.getParentid()));
			treeNodeMap.put("isLeaf", String.valueOf(menuBean.getIsleaf()));
			if (menuBean.getParentid().equals(0)) {
				treeNodeMap.put("open", true);
			}
			if (menuBean.getIsleaf().equals(1)) {
				treeNodeMap.put("menu_href", menuBean.getMenuHref());
			}
			if (role_menu_set.contains(menuBean.getMenuId())) {
				Menu_treeList.add(treeNodeMap);
			}
		}
		return Menu_treeList;
	}
	
	public List<NoticeBean> getNoticeBeanTime() {
		List<NoticeBean> noticeList = this.noticeDao.getNoticeBeanByTime();
		List<NoticeBean> noticeListBytime = new ArrayList<>();
		for (NoticeBean noticeBean : noticeList) {
			if (noticeListBytime.size()<10) {
				noticeListBytime.add(noticeBean);
			}
		}
		return noticeListBytime;
	}
}
