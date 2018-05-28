package com.yr.dao;

import java.util.List;

import com.yr.entity.Auth;

/**
 * 用户dao接口
 * @author 周业好
 * 2018年5月22日 上午10:51:19
 */
public interface AuthDao {
	/**
	 * 添加
	 * @param emp 角色对象
	 * @return 操作是否成功
	 */
	int addId(Auth emp);
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
	int upd(Auth emp);
	
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
    String queryAuthAll();
    
    /**
     * 启用停用
     * @author 周业好
     * @param name 账号
     * @return 操作是否成功
     */
    int kaiguan(String name);
    
    
    /**
   	 * 根据账号查询对应权限 url
   	 * @author 周业好
   	 * @param userName 账号
   	 * @return 此账号的权限
   	 */
   	List<String> queryPermOne(String userName);
    
    //---------------------------------------------
    /**
     * 查询角色拥有的权限
     * @return String
     */
    List<Auth> getResource();
    /**
     * 根据角色 code 查出 对应角色的  权限name
     * @author 周业好
     * @param code 角色编号
     * @return list
     */
    List<Auth> codeTogetResource(String code);
    /**
     * 根据角色 code 查出 对应角色的 全部权限信息
     * @author 周业好
     * @param code 角色编号
     * @return list
     */
    List<Auth> roleCodeTogetResource(String code);
    /**
     * 查出权限表全部信息
     * @author 周业好
     * @return list 
     */
    List<Auth> findAll();
}
