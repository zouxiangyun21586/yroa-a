package com.yr.service;

/**
 * 菜单service接口
 * @author 周业好
 * 2018年5月31日 上午11:44:05
 */
public interface MenuService {
	/**
	 * 页面初始化菜单方法
	 * @author 周业好
	 * @param name 账号
	 * @return 所显示的菜单 json
	 */
	String query(String name);
}
