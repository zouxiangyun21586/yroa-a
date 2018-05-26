package com.yr.service;

import com.yr.entity.StudentCheck;

/**
 * 学生考勤
 * @author 林水桥
 * 2018年5月25日下午9:59:12
 */
public interface StCkService {
	
	/**
	 * 签到
	 * @author 林水桥
	 * @param code     学生代码
	 * @return String 返回签到状态 0为签到成功
	 * 2018年5月25日下午10:26:04
	 */
	String duty(String code);
	
	/**
	 * 添加考勤
	 * @author 林水桥
	 * @param stCk     学生考勤实体数据
	 * @return Integer 返回添加ID
	 * 2018年5月25日下午10:03:49
	 */
	Integer add(StudentCheck stCk);
	
	/**
	 * 删除考勤数据
	 * @author 林水桥
	 * @param id     考勤表ID
	 * @return Integer 返回删除状态 0为未删除
	 * 2018年5月25日下午10:06:02
	 */
	Integer delete(Integer id);
	
	/**
	 * 修改考勤数据
	 * @author 林水桥
	 * @param stCk    学生考勤修改数据
	 * @return Integer  返回修改状态 0为为修改 
	 * 2018年5月25日下午10:11:21
	 */
	Integer update(StudentCheck stCk);
	
	/**
	 * 学生考勤数据回显
	 * @author 林水桥
	 * @param id     学生考勤ID
	 * @return  StudentCheck   学生考勤数据
	 * 2018年5月25日下午10:19:01
	 */
	StudentCheck get(Integer id);
	
}
