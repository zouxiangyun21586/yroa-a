package com.yr.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yr.dao.HolidayDao;
import com.yr.entity.Holiday;
import com.yr.service.HolidayService;

/**
 * 假期ServiceImpl
 * @作者 林水桥
 * 2018年5月24日上午8:58:08
 */
@Transactional
@Service("holidayServiceImpl")
public class HolidayServiceImpl implements HolidayService {
	
	@Autowired
	private HolidayDao holidayDao;
	
	/**
	 * 添加假期
	 * @作者 林水桥
	 * @param holiday 假期实体类
	 * @return Integer 添加ID
	 * 2018年5月23日下午7:57:59
	 */
	public Integer add(Holiday holiday) {
		
//		holiday.setCreateTime(DateUtils.getCurrentDateTime());
		
		return holidayDao.add(holiday);
	}
	
	/**
	 * 分页查询假期
	 * @param page    当前页
	 * @param limit   每页多少条数据
	 * @param classCode	      是否使用模糊查询
	 * @return	String 返回json类型	  
	 * String
	 * @作者 林水桥2018年5月24日上午10:37:23
	 */
	public String getHoliday(int page, int limit, String classCode) {
		
		return holidayDao.getHoliday(page, limit, classCode);
		
	}
	
	/**
	 * 修改假期
	 * @作者 林水桥
	 * @param holiday 假期实体类
	 * @return Integer 返回1为修改成功，0为修改失败
	 * 2018年5月24日上午9:16:37
	 */
	public Integer update(Holiday holiday) {
		
		
		return holidayDao.update(holiday);
	}
	
	/**
	 * 数据回显
	 * @作者 林水桥
	 * @param id 根据假期ID回显数据
	 * @return Holiday 返回假期对象数据
	 * 2018年5月24日上午10:03:28
	 */
	public Holiday get(Integer id) {
		return holidayDao.get(id);
	}
	
}
