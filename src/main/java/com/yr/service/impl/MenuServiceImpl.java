package com.yr.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yr.dao.MenuDao;
import com.yr.entity.Menu;
import com.yr.service.MenuService;
import com.yr.util.JsonUtils;

/**
 * 菜单 Service
 * @author 周业好
 * 2018年5月31日 上午11:41:54
 */
@Service
public class MenuServiceImpl implements MenuService {
	@Autowired
	private MenuDao menu;
	/**
	 * 页面初始化菜单方法
	 * @author 周业好
	 * @param name 账号
	 * @return 所显示的菜单 json
	 */
	@Override
	public String query(String name) {
		Menu aa = new Menu();
		List<Menu> pMenu = new ArrayList<>();
		
		List<Menu> list = menu.query(name);
		List<String> nolist = menu.fangfa(name); //得到被屏蔽的菜单code   
		String code = ""; //1001,1002
		for (String role : nolist) {
			code = code + role + ",";
		}
		if (code != null && !code.equals("")) {
			code = code.substring(0, (code.length() - 1));
		}
		System.err.println(code);
		for (Menu me : list) {
			if ("".equals(me.getPcode()) || null == me.getPcode()) {
				me.setChildren(menu.getSonNavs(me.getCode(), code));
			}
		}
		return JsonUtils.beanListToJson(list, new String[]{"menuRoleItems", "createTime", "updateTime"}, false);
	}
}
