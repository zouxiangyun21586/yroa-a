package com.yr.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yr.dao.ClasDao;
import com.yr.dao.StCkDao;
import com.yr.dao.StudentDao;
import com.yr.entity.CheckTime;
import com.yr.entity.Clas;
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
	
	/**
	 * 签到
	 * @author 林水桥
	 * @param code     学生代码
	 * @return String 返回签到状态 0为签到成功
	 * 2018年5月25日下午10:26:04
	 */
	public String duty(String code) {
		Student student = studentDao.querytoCode(code);
		Clas clas = clasDao.getCode(student.getClassCode());
		
		String checkTimeCode = DateUtils.getAmPmNt();
		Date nowDay = DateUtils.getCurrentDateA();
		String nowsDay = DateUtils.getCurrentDate();
		CheckTime checkTime = stCkDao.getCheckTime(checkTimeCode);
		String nowTime = DateUtils.getCurrentTime();
		
		Long lateTime = DateUtils.getDistanceYear(nowsDay + " " + checkTime.getStartTime(), 
						nowsDay + " " + nowTime);
		Integer status = 1;
		if (lateTime < 0) {
			status = 1;
		} else {
			status = 0;
		}
		
		StudentCheck studentCheck = new StudentCheck();
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
