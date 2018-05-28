package com.yr.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yr.dao.ClasDao;
import com.yr.dao.HoliDao;
import com.yr.dao.StCkDao;
import com.yr.dao.StudentDao;
import com.yr.entity.CheckTime;
import com.yr.entity.Clas;
import com.yr.entity.Holiday;
import com.yr.entity.Student;
import com.yr.entity.StudentCheck;
import com.yr.service.StCkService;
import com.yr.util.DateUtils;

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
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private ClasDao clasDao;
	@Autowired
	private HoliDao holiDao;
	
	/**
	 * 签到
	 * @author 林水桥
	 * @param code     学生代码
	 * @return String 返回签到状态 0为签到成功
	 * 2018年5月25日下午10:26:04
	 */
	public String duty(String code) {
		Student student = studentDao.querytoCode(code); //根据学生编码查询学生
		Clas clas = clasDao.getCode(student.getClassCode()); //根据届次编码查询届次
		
		String checkTimeCode = DateUtils.getAmPmNt(); //获得当前考勤时间编码
		CheckTime checkTime = stCkDao.getCheckTime(checkTimeCode); //根据考勤时间编码查询考勤时间数据
		Date nowDay = DateUtils.getCurrentDateA(); //获取当前日期Date类型
		String nowsDay = DateUtils.getCurrentDate(); //获取当前日期String类型
		String nowTime = DateUtils.getCurrentTime(); //获取当前时间
		
		
		StudentCheck studentCheck = new StudentCheck(); //学生考勤实体
		Holiday holiday = holiDao.getDuty(clas.getCode()); //获取对应届次发布假期数据
//		if (null != holiday && nowsDay > DateUtils.getCurrentDateT(holiday.getStartDate())) {
//			
//		}
		
		Long lateTime = DateUtils.getDistanceYear(nowsDay + " " + checkTime.getStartTime(), 
				nowsDay + " " + nowTime);
		Integer status = 1;
		if (lateTime < 0) {
			status = 1;
		} else {
			status = 0;
		}
		
		studentCheck.setClassCode(clas.getCode());
		studentCheck.setClassName(clas.getName());
		studentCheck.setStudentCode(student.getCode());
		studentCheck.setStudentName(student.getName());
		studentCheck.setCheckTimeCode(checkTime.getCode());
		studentCheck.setCheckTimeDesc(checkTime.getTimeName());
		studentCheck.setCheckTime(nowDay);
		studentCheck.setStartTime(checkTime.getStartTime());
		studentCheck.setRetyTime(nowTime);
		studentCheck.setStatus(status);
		studentCheck.setCreateTime(DateUtils.getCurrentDateTimeA());
		
		
		stCkDao.add(studentCheck);
		
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
