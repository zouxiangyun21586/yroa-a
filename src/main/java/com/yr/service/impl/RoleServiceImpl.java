package com.yr.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yr.dao.RoleDao;
import com.yr.entity.Role;
import com.yr.service.RoleService;
import net.sf.json.JSONObject;

/**
 * 角色service 是实现类
 * @author 周业好
 * 2018年5月22日 上午10:53:21
 */
@Transactional
@Service
public class RoleServiceImpl implements RoleService {
	
	private static final int TWO = 2;
	@Autowired
	private RoleDao accDao;
	
	/**
	 * 添加
	 * @param emp 角色对象
	 * @return 操作是否成功
	 */
	public String addId(Role emp) {
		Map<String, Object> map = new HashMap<>(); 
		try {
			emp.setCreateTime(new Date()); //添加开始时间
			emp.setUpdateTime(new Date()); //添加最后修改时间
			emp.setUse(0);
			int z = accDao.addId(emp);
			if (1 == z) {
				map.put("code", 0);
				map.put("msg", "添加成功");
			} else if (TWO == z) {
				map.put("code", 1);
				map.put("msg", "失败,角色有人使用");
			} else {
				map.put("code", 1);
				map.put("msg", "错误,编号已存在");
			}
		} catch (Exception e) {
			map.put("code", 1);
			map.put("msg", "添加失败");
			e.printStackTrace();
		}
		
		return JSONObject.fromObject(map).toString();
	}
	/**
	 * 删除
	 * @param i 角色编号
	 * @return json
	 */
	public String del(String i) {
		Map<String, Object> map = new HashMap<>(); 
		int z = accDao.del(i);
		if (0 == z) {
			map.put("code", 0);
			map.put("msg", "删除成功");
		} else if (TWO == z) {
			map.put("code", 1);
			map.put("msg", "失败,角色有人使用");
		} else {
			map.put("code", 1);
			map.put("msg", "错误,编号不存在");
		}
		return JSONObject.fromObject(map).toString();
	}
	/**
	 * 修改
	 * @param emp 角色对象
	 * @return 操作是否成功
	 */
	public String upd(Role emp) {
		emp.setUpdateTime(new Date());
		int z = accDao.upd(emp);
		Map<String, Object> map = new HashMap<>(); 
		if (0 == z) {
			map.put("code", 0);
			map.put("msg", "修改成功");
		} else {
			map.put("code", 1);
			map.put("msg", "错误,编号不存在");
		}
		return JSONObject.fromObject(map).toString();
	}
	/**
	 * 查询单个
	 * @param code 角色code
	 * @return json
	 */
	@Override
	public String query(String code) {
		String z = accDao.query(code);
		Map<String, Object> map = new HashMap<>(); 
		if ("1".equals(z)) {
			map.put("code", 1);
			map.put("msg", "错误,编号不存在");
			return JSONObject.fromObject(map).toString();
		}
		return z;
	}
	/**
	 * 班某人的分页
	 * @param page 当前页 
	 * @param limit 一页显示多少条
	 * @param name 是否使用模糊搜索
	 * @return json
	 */
	@Override
	public String getFenye(int page, int limit, String name) {
		String json = accDao.getFenye(page, limit, name);
		return json;
	}
	 /**
     * 查询所有的角色
     * @author 周业好
     * @return json
     */
	@Override
	public String queryRoleAll() {
		String json = accDao.queryRoleAll();
		return json;
	}
	/**
     * 启用停用
     * @author 周业好
     * @param name 账号
     * @return json
     */
	@Override
	public String kaiguan(String name) {
		Map<String, Object> map = new HashMap<>();
		int i = accDao.kaiguan(name);
		if (1 == i) {
			map.put("code", 1);
			map.put("msg", "操作失败");
		} else if (TWO == i) {
			map.put("code", 1);
			map.put("msg", "失败,此角色被使用");
		} else {
			map.put("code", 0);
			map.put("msg", "操作成功");
		}
		return JSONObject.fromObject(map).toString();
	}
	/**
     * 保存 权限
     * @author 周业好
     * @param resourceId 选中的权限id
     * @param roleCode 角色code
     * @return json
     */
	@Override
	public String roleEmpowerment(String[] resourceId, String roleCode) {
		return accDao.roleEmpowerment(resourceId, roleCode);
	}
}
