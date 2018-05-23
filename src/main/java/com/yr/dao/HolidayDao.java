package com.yr.dao;

import com.yr.entity.Holiday;

/**
 * 假期Dao
 * @作者 林水桥
 * 2018年5月22日下午10:26:49
 */
public interface HolidayDao {
	
	/**
	 * 添加假期
	 * @param holiday 假期实体类
	 * @return Integer 添加ID
	 * 2018年5月23日下午7:57:59
	 */
	Integer addHoliday(Holiday holiday);
	
}
