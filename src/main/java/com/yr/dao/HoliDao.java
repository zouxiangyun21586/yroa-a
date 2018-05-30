package com.yr.dao;

import com.yr.entity.Holiday;

/**
 * 假期Dao
 * @作者 林水桥
 * 2018年5月22日下午10:26:49
 */
public interface HoliDao {
	
	/**
	 * 添加假期
	 * @作者 林水桥
	 * @param holiday 假期实体类
	 * @return Integer 添加ID
	 * 2018年5月23日下午7:57:59
	 */
	Integer add(Holiday holiday);
	
	/**
	 * 根据ID删除假期
	 * @param id   假期ID
	 * @return     0为未删除
	 * Integer
	 * @作者 林水桥2018年5月25日上午10:02:37
	 */
	Integer delete(Integer id);
	
	/**
	 * 分页查询假期
	 * @param page    当前页
	 * @param limit   每页多少条数据
	 * @param classCode	      是否使用模糊查询
	 * @return	String 返回json类型	  
	 * String
	 * @作者 林水桥2018年5月24日上午10:37:23
	 */
	String getHoliday(int page, int limit, String classCode);
	
	/**
	 * 修改假期
	 * @作者 林水桥
	 * @param holiday 假期实体类
	 * @return Integer 返回1为修改成功，0为修改失败
	 * 2018年5月24日上午9:16:37
	 */
	Integer update(Holiday holiday);
	
	/**
	 * 发布假期
	 * @author 林水桥
	 * @param holiday    假期数据
	 * @return Integer 返回发布状态 0为发布成功
	 * 2018年5月28日上午11:22:15
	 */
	Integer release(Holiday holiday);
	
	/**
	 * 数据回显
	 * @作者 林水桥
	 * @param id 根据假期ID回显数据
	 * @return Holiday 返回假期对象数据
	 * 2018年5月24日上午10:03:28
	 */
	Holiday get(Integer id);
	
	/**
	 * 根据届次编码，假期状态，发布时间 查询假期
	 * @author 林水桥
	 * @param clasCode 届次编码
	 * @return Holiday 假期数据
	 * 2018年5月27日上午10:27:34
	 */
	Holiday getDuty(String clasCode);
	
}
