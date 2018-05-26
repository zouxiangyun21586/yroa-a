package com.yr.dao;

import java.util.List;

import com.yr.entity.Account;
import com.yr.entity.Auth;

/**
 * 用户dao接口
 * @author 周业好
 * 2018年5月22日 上午10:51:19
 */
public interface AuthDao {
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
	 * @param id 权限名id
	 * @param userN 权限名
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
    
    /**
     * 查询所有的角色
     * @author 周业好
     * @return json
     */
    String queryRoleAll();
    
    /**
     * 重置密码
     * @author 周业好
     * @param name 权限名
     * @param newPass 新密码
     * @return json
     */
    String resetPassWord(String name, String newPass);
    
    /**
     * 启用停用
     * @author 周业好
     * @param name 权限名
     * @return 操作是否成功
     */
    int kaiguan(String name);
    
    
    //---------------------------------------------
    /**
     * 查询权限
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
