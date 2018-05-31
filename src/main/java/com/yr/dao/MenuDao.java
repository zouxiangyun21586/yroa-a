package com.yr.dao;

import java.util.List;

import com.yr.entity.Menu;

/**
 * 菜单Dao接口
 * @author 周业好
 * 2018年5月31日 上午11:44:05
 */
public interface MenuDao {
	/**
	 * 页面初始化菜单方法
	 * @author 周业好
	 * @param name 账号
	 * @return 所显示的菜单 json
	 */
	List<Menu> query(String name);
	
	/**
     * 获取子级菜单
     * 
     * @param parentId 父ID
     * @param name 账号
     * @return list
     */
	List<Menu> getSonNavs(String parentId, String name);
	
	/**
	 * 查出被屏蔽的菜单
	 * @author 周业好
	 * @param name 账号
	 * @return code 集合
	 */
	List<String> fangfa(String name);
}
