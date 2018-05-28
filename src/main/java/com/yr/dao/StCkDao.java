package com.yr.dao;

import java.util.Date;

import com.yr.entity.CheckTime;
import com.yr.entity.StudentCheck;

/**
 * 学生考勤
 * @author 林水桥
 * 2018年5月25日下午9:43:14
 */
public interface StCkDao {
	
	/**
	 * 添加考勤
	 * @author 林水桥
	 * @param stCk     学生考勤实体数据
	 * @return Integer 返回添加ID
	 * 2018年5月25日下午10:03:49
	 */
	Integer add(StudentCheck stCk);
	
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
	
	/**
	 * 判断重复签到
	 * @author 林水桥
	 * @param stuCode  		学生编码
	 * @param checkTimeCode 考勤时间编码
	 * @param checkDate     考勤日期
	 * @return StudentCheck 返回考勤对象，null为无重复  
	 * 2018年5月26日下午8:45:53
	 */
	StudentCheck repeatSign(String stuCode, String checkTimeCode, Date checkDate);
	
	/**
	 * 根据考勤时间code，获取考勤时间对象
	 * @author 林水桥
	 * @param code     考勤时间code
	 * @return CheckTime 考勤时间数据
	 * 2018年5月26日下午9:50:44
	 */
	CheckTime getCheckTime(String code);
	
}
