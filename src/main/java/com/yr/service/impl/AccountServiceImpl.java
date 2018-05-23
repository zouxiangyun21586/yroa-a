package com.yr.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yr.dao.AccountDao;
import com.yr.entity.Account;
import com.yr.service.AccountService;

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
	public int addId(Account emp, String code) {
		int z = accDao.addId(emp, code);
		return z;
	}
	/**
	 * 删除
	 * @param i 用户编号
	 * @return 是否操作成功
	 */
	public int del(Integer i) {
		int z = accDao.del(i);
		return z;
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
	 * @param i 用户id
	 * @return 查出的用户对象
	 */
	public Account query(Integer i) {
		Account acc = accDao.query(i);
		return acc;
	}

}
