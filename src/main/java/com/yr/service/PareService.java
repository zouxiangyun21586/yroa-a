package com.yr.service;

import com.yr.entity.Parents;

/**
 * 家长Service接口类
 * @作者 林水桥
 * 2018年6月4日下午2:49:54
 */
public interface PareService {
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
	 * @param stuName    学生姓名
	 * @return Integer   返回添加ID
	 * 2018年6月4日上午10:20:53
	 */
	Integer add(Parents parents, String stuName);
	
	/**
	 * 修改家长
	 * @author 林水桥
	 * @param parents   要修改的家长数据
	 * @return String  0为修改失败
	 * 2018年6月4日上午10:26:21
	 */
	String update(Parents parents);
	
	/**
	 * 数据回显
	 * @author  林水桥
	 * @param id      家长ID
	 * @return Parents 家长数据
	 * 2018年6月5日下午4:40:22
	 */
	Parents getSingle(Integer id);
}
