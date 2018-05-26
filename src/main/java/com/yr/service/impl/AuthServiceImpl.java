package com.yr.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yr.dao.AuthDao;
import com.yr.entity.Auth;
import com.yr.service.AuthService;
import com.yr.util.JsonUtils;

import net.sf.json.JSONObject;

/**
 * 用户service 是实现类
 * @author 周业好
 * 2018年5月22日 上午10:53:21
 */
@Transactional
@Service
public class AuthServiceImpl implements AuthService {
	private static final int TWO = 2;
	@Autowired
	private AuthDao accDao;
	
	/**
	 * 添加
	 * @param emp 权限对象
	 * @return 操作是否成功
	 */
	public String addId(Auth emp) {
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
				map.put("msg", "失败,权限有人使用");
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
	 * @param i 权限编号
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
			map.put("msg", "失败,权限有人使用");
		} else {
			map.put("code", 1);
			map.put("msg", "错误,编号不存在");
		}
		return JSONObject.fromObject(map).toString();
	}
	/**
	 * 修改
	 * @param emp 权限对象
	 * @return 操作是否成功
	 */
	public String upd(Auth emp) {
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
	 * @param code 权限code
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
     * 查询所有的权限
     * @author 周业好
     * @return json
     */
	@Override
	public String queryAuthAll() {
		String json = accDao.queryAuthAll();
		return json;
	}
	/**
     * 启用停用
     * @author 周业好
     * @param name 权限code
     * @return json
     */
	@Override
	public String kaiguan(String name) {
		Map<String, Object> map = new HashMap<>();
		int i = accDao.kaiguan(name);
		if (1 == i) {
			map.put("code", 1);
			map.put("msg", "操作失败");
		} else {
			map.put("code", 0);
			map.put("msg", "操作成功");
		}
		return JSONObject.fromObject(map).toString();
	}
	
	/**
     * 查询角色拥有的权限
     * @param code  角色code
     * @return json
     */
	@Override
	public String getResource(String code) {
		List<Auth> list = accDao.getResource();
		List object = accDao.codeTogetResource(code);
        String json = "[";
        for (Auth resource : list) {
            String d = null;
            for (int i = 0; i < object.size(); i++) {
                String values = (String) object.get(i);
                if (resource.getName().equals(values)) {
                    resource.setChecked(true);
                }
                d = ((i < object.size()) ? "," : "");
            }
            json += JsonUtils.sendObject(resource,
                    new String[] {"first", "last", "children", "perRoleItems" }) + d;
        }
        return json + "]";
	}

}
