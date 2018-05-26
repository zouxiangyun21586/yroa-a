package com.yr.dao;

import com.yr.entity.Role;

/**
 * 角色dao接口
 * @author 周业好
 * 2018年5月22日 上午10:51:19
 */
public interface RoleDao {
	/**
	 * 添加
	 * @param emp 角色对象
	 * @return 操作是否成功
	 */
	int addId(Role emp);
	/**
	 * 删除
	 * @param code 角色编号
	 * @return 是否操作成功
	 */
	int del(String code);
	/**
	 * 修改
	 * @param emp 角色对象
	 * @return 操作是否成功
	 */
	int upd(Role emp);
	
	/**
	 * 查询单个
	 * @param code 角色编号
	 * @return 1 角色编号不存在,json 成功
	 */
	String query(String code);
	
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
     * 启用停用
     * @author 周业好
     * @param name 账号
     * @return 操作是否成功
     */
    int kaiguan(String name);
}
