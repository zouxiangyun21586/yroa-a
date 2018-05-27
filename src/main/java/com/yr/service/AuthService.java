package com.yr.service;

import com.yr.entity.Auth;

/**
 * 用户service实现类
 * @author 周业好
 * 2018年5月22日 上午10:53:54
 */
public interface AuthService {
	/**
	 * 添加
	 * @param emp 权限对象
	 * @return 操作是否成功
	 */
	String addId(Auth emp);
	/**
	 * 删除
	 * @param i 权限编号
	 * @return 是否操作成功
	 */
	String del(String i);
	/**
	 * 修改
	 * @param emp 权限对象
	 * @return json
	 */
	String upd(Auth emp);
	
	/**
	 * 查询单个
	 * @param code 权限编号
	 * @return json
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
     * 查询所有的权限
     * @author 周业好
     * @return json
     */
    String queryAuthAll();
    
    /**
     * 启用停用
     * @author 周业好
     * @param code 权限编号
     * @return json
     */
    String kaiguan(String code);
    
    /**
     * 查询角色拥有的权限
     * @param code  角色code
     * @return String
     */
    String getResource(String code);
    
    /**
     * 根据角色 code 查出 对应角色的 权限name
     * @author 周业好
     * @param code 角色code
     * @return json
     */
	String lookResource(String code);
}
