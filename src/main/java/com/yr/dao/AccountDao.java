package com.yr.dao;

import com.yr.entity.Account;

/**
 * 用户dao接口
 * @author 周业好
 * 2018年5月22日 上午10:51:19
 */
public interface AccountDao {
	/**
	 * 添加
	 * @param emp 用户对象
	 * @param code 角色code
	 * @return 操作是否成功
	 */
	int addId(Account emp, String code);
	/**
	 * 删除
	 * @param i 用户编号
	 * @return 是否操作成功
	 */
	int del(Integer i);
	/**
	 * 修改
	 * @param emp 用户对象
	 * @return 操作是否成功
	 */
	int upd(Account emp);
	
	/**
	 * 修改密码
	 * @param id 账号id
	 * @param userN 账号
	 * @param oldpassword 旧密码
	 * @param passW 新密码
	 * @return 出错信息
	 */
	String updatePass(String oldpassword, String userN, Integer id, String passW);
	/**
	 * 查询单个
	 * @param i 用户id
	 * @return 查出的用户对象
	 */
	Account query(Integer i);
	
	/**
	 * 班某人的分页
	 * @param page 当前页 
	 * @param limit 一页显示多少条
	 * @param name 是否使用模糊搜索
	 * @return json
	 */
    String getFenye(int page, int limit, String name);
}
