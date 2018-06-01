package com.yr.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.yr.dao.MenuDao;
import com.yr.entity.Menu;

/**
 * 菜单 Dao
 * @author 周业好
 * 2018年5月31日 上午11:41:54
 */
@Repository
public class MenuDaoImpl implements MenuDao {
	private static final int TWO = 2;
	@PersistenceContext
	private EntityManager em;
	/**
	 * 页面初始化菜单方法
	 * @author 周业好
	 * @param name 账号
	 * @return 所显示的菜单 json
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Menu> query(String name) {
		List<Menu> list = em.createNativeQuery("select DISTINCT m.* from yr_account a,yr_account_role ar,"
			+ "yr_role r,yr_role_menu rm,yr_menu m where r.code=rm.role_code and m.code=rm.menu_code and "
			+ "a.username=ar.username and r.code=ar.role_code and m.p_code is null or m.p_code='' "
			+ "and a.username=? or a.tel=? ORDER BY code asc", Menu.class).setParameter(1, name)
			.setParameter(TWO, name).getResultList();
		return list;
	}
	/**
	 * 查询子级
	 * parentId 父级code
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Menu> getSonNavs(String parentId, String code) {
		List<Menu> list = em.createQuery("from Menu m where m.pcode=? and m.code not in(?)")
				.setParameter(0, parentId).setParameter(1, code).getResultList();
		return list;
	}
	
	
	/**
	 * 查出被屏蔽的菜单
	 * @author 周业好
	 * @param name 账号
	 * @return code 集合
	 */
	@SuppressWarnings("unchecked")
	public List<String> fangfa(String name) {
		List<String> noList = em.createNativeQuery("select code from yr_menu where `code` not in(select m.code "
			+ "from yr_account a,yr_account_role ar,yr_role r,yr_role_menu rm,yr_menu m where r.code="
			+ "rm.role_code and m.code=rm.menu_code and a.username=ar.username and r.code=ar.role_code and "
			+ "a.username=? or a.tel=?)").setParameter(1, name).setParameter(TWO, name).getResultList();
		return noList;
	}
}
