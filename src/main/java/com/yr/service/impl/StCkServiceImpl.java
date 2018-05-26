package com.yr.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yr.dao.StCkDao;
import com.yr.entity.StudentCheck;
import com.yr.service.StCkService;

/**
 * 学生考勤Service层
 * @author 林水桥
 * 2018年5月25日下午9:58:18
 */
@Transactional
@Service("stCkServiceImpl")
public class StCkServiceImpl implements StCkService {
	
	@Autowired
	private StCkDao stCkDao;
	
	/**
	 * 签到
	 * @author 林水桥
	 * @param stCk     学生考勤实体数据
	 * @return Integer 返回签到ID
	 * 2018年5月25日下午10:26:04
	 */
	public Integer duty(StudentCheck stCk) {
		
		stCkDao.add(stCk);
		
		return null;
	}
	
	/**
	 * 添加考勤
	 * @author 林水桥
	 * @param stCk     学生考勤实体数据
	 * @return Integer 返回添加ID
	 * 2018年5月25日下午10:03:49
	 */
	public Integer add(StudentCheck stCk) {
		
		stCkDao.add(stCk);
		
		return null;
	}
	
	/**
	 * 删除考勤数据
	 * @author 林水桥
	 * @param id     考勤表ID
	 * @return Integer 返回删除状态 0为未删除
	 * 2018年5月25日下午10:06:02
	 */
	public Integer delete(Integer id) {
		
		stCkDao.delete(id);
		
		return null;
	}
	
	/**
	 * 修改考勤数据
	 * @author 林水桥
	 * @param stCk    学生考勤修改数据
	 * @return Integer  返回修改状态 0为为修改 
	 * 2018年5月25日下午10:11:21
	 */
	public Integer update(StudentCheck stCk) {
		
		stCkDao.update(stCk);
		
		return null;
	}
	
	/**
	 * 学生考勤数据回显
	 * @author 林水桥
	 * @param id     学生考勤ID
	 * @return  StudentCheck   学生考勤数据
	 * 2018年5月25日下午10:19:01
	 */
	public StudentCheck get(Integer id) {
		
		stCkDao.get(id);
		
		return null;
	}
	
}
