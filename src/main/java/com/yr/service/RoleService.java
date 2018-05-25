package com.yr.service;

import com.yr.entity.Role;

/**
 * 角色service实现类
 * @author 周业好
 * 2018年5月22日 上午10:53:54
 */
public interface RoleService {
	/**
	 * 添加
	 * @param emp 角色对象
	 * @return 操作是否成功
	 */
	String addId(Role emp);
	/**
	 * 删除
	 * @param i 角色编号
	 * @return 是否操作成功
	 */
	int del(Integer i);
	/**
	 * 修改
	 * @param emp 角色对象
	 * @return 操作是否成功
	 */
	int upd(Role emp);
	
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
	 * @param i 角色id
	 * @return 查出的角色对象
	 */
	Role query(Integer i);
	
	/**
	 * 班某人的分页
	 * @param page 当前页 
	 * @param limit 一页显示多少条
	 * @param name 是否使用模糊搜索
	 * @return json
	 */
    String getFenye(int page, int limit, String name);
    
    /**
     * 查询所有的角色
     * @author 周业好
     * @return json
     */
    String queryRoleAll();
    
    /**
     * 重置密码
     * @author 周业好
     * @param name 账号
     * @return json
     */
    String resetPassWord(String name);
    
    /**
     * 启用停用
     * @author 周业好
     * @param name 账号
     * @return json
     */
    String kaiguan(String name);
}
