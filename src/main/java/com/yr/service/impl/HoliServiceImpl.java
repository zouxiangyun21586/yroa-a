package com.yr.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yr.dao.ClasDao;
import com.yr.dao.HoliDao;
import com.yr.entity.Clas;
import com.yr.entity.Holiday;
import com.yr.service.HoliService;
import com.yr.util.DateUtils;

import net.sf.json.JSONObject;

/**
 * 假期ServiceImpl
 * @作者 林水桥
 * 2018年5月24日上午8:58:08
 */
@Transactional
@Service("holiServiceImpl")
public class HoliServiceImpl implements HoliService {
	
	@Autowired
	private HoliDao holiDao;
	@Autowired
	private ClasDao clasDao;
	
	/**
	 * 添加假期
	 * @作者 林水桥
	 * @param holiday 假期实体类
	 * @return String 添加状态0是成功
	 * 2018年5月23日下午7:57:59
	 */
	public String add(Holiday holiday) {
		Map<String, Object> map = new HashMap<>();
		Clas clas = clasDao.getCode(holiday.getClassCode());
		Integer b = 0;
		if (null != clas) {
			holiday.setClassCode(clas.getCode());
			holiday.setClassName(clas.getName());
			holiday.setCreateTime(DateUtils.getCurretDateTimeA());
			holiday.setUpdateTime(DateUtils.getCurretDateTimeA());
			b = holiDao.add(holiday);
		}
		if (null == b || 0 == b) {
			map.put("code", 1);
			map.put("msg", "添加失败");
		} else {
			map.put("code", 0);
			map.put("msg", "添加成功");
		}
		return JSONObject.fromObject(map).toString();
	}
	
	/**
	 * 根据ID删除假期
	 * @param id   假期ID
	 * @return     0为未删除
	 * Integer
	 * @作者 林水桥2018年5月25日上午10:02:37
	 */
	public String delete(Integer id) {
		Map<String, Object> map = new HashMap<>();
		Holiday holiday = holiDao.get(id);
		Integer del = 0;
		if (null != holiday) {
			del = holiDao.delete(id);
		}
		if (0 == del) {
			map.put("code", 1);
			map.put("msg", "删除失败");
		} else {
			map.put("code", 0);
			map.put("msg", "删除成功");
		}
		return JSONObject.fromObject(map).toString();
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
		
		return holiDao.getHoliday(page, limit, classCode);
		
	}
	
	/**
	 * 修改假期
	 * @作者 林水桥
	 * @param holiday 假期实体类
	 * @return String 返回1为修改成功，0为修改失败
	 * 2018年5月24日上午9:16:37
	 */
	public String update(Holiday holiday) {
		Map<String, Object> map = new HashMap<>();
		Holiday holidays = holiDao.get(holiday.getId());
		Integer update = 0;
		if (null != holidays) {
			Clas clas = clasDao.getCode(holiday.getClassCode());
			holiday.setUpdateTime(DateUtils.getCurretDateTimeA());
			holiday.setClassName(clas.getName());
			update = holiDao.update(holiday);
		}
		if (0 == update) {
			map.put("code", 1);
			map.put("msg", "修改失败");
		} else {
			map.put("code", 0);
			map.put("msg", "修改成功");
		}
		return JSONObject.fromObject(map).toString();
	}
	
	/**
	 * 数据回显
	 * @作者 林水桥
	 * @param id 根据假期ID回显数据
	 * @return Holiday 返回假期对象数据
	 * 2018年5月24日上午10:03:28
	 */
	public Holiday get(Integer id) {
		return holiDao.get(id);
	}
	
}
