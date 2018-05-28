package com.yr.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yr.dao.AccountDao;
import com.yr.entity.Account;
import com.yr.service.AccountService;
import com.yr.util.EncryptUtils;
import net.sf.json.JSONObject;

/**
 * 用户service 是实现类
 * @author 周业好
 * 2018年5月22日 上午10:53:21
 */
@Transactional
@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	private AccountDao accDao;
	
	/**
	 * 添加
	 * @param emp 用户对象
	 * @param code 角色code
	 * @return 操作是否成功
	 */
	public String addId(Account emp, String code) {
		Map<String, Object> map = new HashMap<>(); 
		try {
			int z = accDao.addId(emp, code);
			if (1 == z) {
				map.put("code", 0);
				map.put("msg", "添加成功");
			} else {
				map.put("code", 1);
				map.put("msg", "错误,账户已存在");
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
	 * @param name 用户账号
	 */
	public void del(String name) {
		accDao.del(name);
	}
	/**
	 * 修改
	 * @param emp 用户对象
	 * @return 操作是否成功
	 */
	public int upd(Account emp) {
		int z = accDao.upd(emp);
		return z;
	}
	/**
	 * 修改密码
	 * @param id 账号id
	 * @param userN 账号
	 * @param oldpassword 旧密码
	 * @param passW 新密码
	 * @return 出错信息
	 */
	public String updatePass(String oldpassword, String userN, Integer id,
			String passW) {
		String val = accDao.updatePass(oldpassword, userN, id, passW);
		return val;
	}
	/**
	 * 查询单个
	 * @param i 用户账号
	 * @return 查出的用户对象
	 */
	public Account query(String i) {
		Account acc = accDao.query(i);
		return acc;
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
     * 重置密码
     * @author 周业好
     * @param name 账号
     * @return json
     */
	@Override
	public String resetPassWord(String name) {
		Map<String, Object> map = new HashMap<>();
		try {
			String breaMi = EncryptUtils.encryptBASE64("12345678".getBytes()); //BASE64位加密
			String mdFiveMi = EncryptUtils.encryptToMD5(breaMi); //密文再次 MD5加密
			String zhi = accDao.resetPassWord(name, mdFiveMi);
			if ("1".equals(zhi)) {
				map.put("code", 0);
				map.put("msg", "重置成功");
			}
		} catch (Exception e) {
			map.put("code", 1);
			map.put("msg", "重置失败");
			e.printStackTrace();
		}
		return JSONObject.fromObject(map).toString();
	}
	/**
     * 启用停用
     * @author 周业好
     * @param name 账户
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

}
