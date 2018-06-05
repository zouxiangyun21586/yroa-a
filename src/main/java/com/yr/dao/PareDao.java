package com.yr.dao;

import com.yr.entity.Parents;

/**
 * 家长Dao抽象类
 * @作者 林水桥
 * 2018年6月4日上午10:17:22
 */
public interface PareDao {
	/**
	 * 家长分页查询
	 * @author 林水桥
	 * @param page   当前页
	 * @param limit  每页多少条数据
	 * @param name   家长姓名 模糊查询
	 * @return String 返回json数据
	 * 2018年6月4日下午2:44:57
	 */
	String getParents(int page, int limit, String name);
	
	/**
	 * 添加家长
	 * @author 林水桥
	 * @param parents    添加的家长数据
	 * @return Integer   返回家长ID
	 * 2018年6月4日上午10:20:53
	 */
	Integer add(Parents parents);
	
	/**
	 * 修改家长
	 * @author 林水桥
	 * @param parents   要修改的家长数据
	 * @return Integer  0为修改失败
	 * 2018年6月4日上午10:26:21
	 */
	Integer update(Parents parents);
	
	/**
	 * 获取家长code
	 * @author 林水桥
	 * @return String 家长code
	 * 2018年6月4日下午7:37:12
	 */
	String getCode();
	
	/**
	 * 到字典表查出key对应得value，根据角色名到角色表查出角色code
	 * @author 林水桥
	 * @return String 角色code
	 * 2018年6月4日下午7:51:58
	 */
	String roleCode();
	
	/**
	 * 数据回显
	 * @author  林水桥
	 * @param id      家长ID
	 * @return Parents 家长数据
	 * 2018年6月5日下午4:40:22
	 */
	Parents getSingle(Integer id);
	
}
