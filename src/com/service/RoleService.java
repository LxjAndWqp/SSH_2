package com.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.MenuBean;
import com.bean.RoleBean;
import com.dao.RoleDao;

@Service(value = "roleService")
public class RoleService {
	@Autowired
	private RoleDao roleDao;

	public List<RoleBean> getRoleList() {
		List<RoleBean> roleList = this.roleDao.getRoleList();
		return roleList;
	}
	
	public RoleBean getRoleBeanById(String roleId) {
		if (StringUtils.isEmpty(roleId)==true) {
			throw new RuntimeException("参数为空");
		}else{
			return this.roleDao.getRoleById(roleId);
		}
	}

	public void deleteRole(String roleId) {
		if (StringUtils.isEmpty(roleId)==true) {
			throw new RuntimeException("参数为空");
		}else{
			int useRole = this.roleDao.getUseRoleNumber(roleId);
			if (useRole>0) {
				throw new RuntimeException("角色正在被使用中");
			}else {
				this.roleDao.delete(roleId);
			}
		}
		
	}

	public void save(RoleBean roleBean , String role_select_menuid) {
		if (StringUtils.isEmpty(role_select_menuid)==true) {
			throw new RuntimeException("参数为空");
		}else{
			this.roleDao.save(roleBean, role_select_menuid);
		}
	}
	
	public void modify(RoleBean roleBean, String role_select_menuid) {
		if (StringUtils.isEmpty(role_select_menuid)==true) {
			throw new RuntimeException("参数为空");
		}else{
			this.roleDao.modify(roleBean, role_select_menuid);
		}
	}
	
	public List<Map<String, Object>> getMenu_TreeList(String role_id,String view_type) {
		List<Map<String,Object>> Menu_treeList = new ArrayList<>();
		List<MenuBean> menuList = this.roleDao.getDataMenuList();
		
		if (view_type.equals("add")) {
			for (MenuBean menuBean : menuList) {
				Map<String, Object> treeNodeMap = new LinkedHashMap<String,Object>();
				treeNodeMap.put("id", (String)menuBean.getMenuId());
				treeNodeMap.put("name", (String)menuBean.getMenuName());
				treeNodeMap.put("pId", String.valueOf(menuBean.getParentid()));
				if (menuBean.getParentid().equals(0)) {
					treeNodeMap.put("open", true);
				}
				Menu_treeList.add(treeNodeMap);
			}
		}else if (view_type.equals("edit")) {
			Set<String> role_menuSet = this.roleDao.getRoleMunuSetById(role_id);
			for (MenuBean menuBean : menuList) {
				Map<String, Object> treeNodeMap = new LinkedHashMap<String,Object>();
				treeNodeMap.put("id", (String)menuBean.getMenuId());
				treeNodeMap.put("name", (String)menuBean.getMenuName());
				treeNodeMap.put("pId", String.valueOf(menuBean.getParentid()));
				if (menuBean.getParentid().equals(0)) {
					treeNodeMap.put("open", true);
				}
				if (role_menuSet.contains(menuBean.getMenuId())) {
					treeNodeMap.put("checked", true);
				}
				Menu_treeList.add(treeNodeMap);
			}
		}else {
			throw new RuntimeException("未知查询菜单类型");
		}	
		return Menu_treeList;
		
	}

	public List<RoleBean> searchRoleList(String rolename) {
		boolean flag = false;
		if (rolename == null || "".equals(rolename))
			flag = true;
		List<RoleBean> roleDataList = this.roleDao.getRoleList();
		List<RoleBean> roleList = new ArrayList<>();
		for (RoleBean roleBean : roleDataList) {
			if (flag||roleBean.getRoleName().equals(rolename)) {
				roleList.add(roleBean);
			}
		}
		return roleList;
	}

	

}
