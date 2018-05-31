package com.yr.dao;

import java.util.List;

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
	 * @param authcode 权限表 查询学生的code
	 * @return 操作是否成功
	 */
	int addId(Role emp, String authcode);
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
     * @param code 角色编号
     * @return json
     */
    int kaiguan(String code);
    
    /**
     * 保存 权限
     * @author 周业好
     * @param resourceId 选中的权限id
     * @param roleCode 角色code
     * @return json
     */
    String roleEmpowerment(String[] resourceId, String roleCode);
    
    /**
     * 权限表 查询学生的code
     * @author 周业好
     * @return code
     */
    String querydic();
    
    /**
	 * 查询角色全部信息  单个(根据账号)
	 * @param name 用户账号
	 * @return list
	 */
	List<Role> queryR(String name);
	
}
