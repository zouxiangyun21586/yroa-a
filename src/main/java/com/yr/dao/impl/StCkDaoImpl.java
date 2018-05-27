package com.yr.dao.impl;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.yr.dao.StCkDao;
import com.yr.entity.CheckTime;
import com.yr.entity.StudentCheck;

/**
 * 学生考勤Dao层
 * @author 林水桥
 * 2018年5月25日下午9:44:35
 */
@Repository("stCkDaoImpl")
public class StCkDaoImpl implements StCkDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * 添加考勤
	 * @author 林水桥
	 * @param stCk     学生考勤实体数据
	 * @return Integer 返回添加ID
	 * 2018年5月25日下午10:03:49
	 */
	public Integer add(StudentCheck stCk) {
		entityManager.persist(stCk);
		return stCk.getId();
	}
	
	/**
	 * 删除考勤数据
	 * @author 林水桥
	 * @param id     考勤表ID
	 * @return Integer 返回删除状态 0为未删除
	 * 2018年5月25日下午10:06:02
	 */
	public Integer delete(Integer id) {
		
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
		
		return null;
	}
	
	/**
	 * 判断重复签到
	 * @author 林水桥
	 * @param stuCode  		学生编码
	 * @param checkTimeCode 考勤时间编码
	 * @param checkDate     考勤日期
	 * @return StudentCheck 返回考勤对象，null为无重复  
	 * 2018年5月26日下午8:45:53
	 */
	public StudentCheck repeatSign(String stuCode, String checkTimeCode, Date checkDate) {
		
		StudentCheck stuCk = (StudentCheck) entityManager.createQuery("from StudentCheck where "
						  + "studentCode = :studentCode and checkTimeCode = :checkTimeCode and "
						  + "checkTime = :checkTime")
						 .setParameter("studentCode", stuCode)
						 .setParameter("checkTimeCode", checkTimeCode)
						 .setParameter("checkTime", checkDate)
						 .getSingleResult();
		
		return stuCk;
	}
	
	/**
	 * 根据考勤时间code，获取考勤时间对象
	 * @author 林水桥
	 * @param code     考勤时间code
	 * @return CheckTime 考勤时间数据
	 * 2018年5月26日下午9:50:44
	 */
	public CheckTime getCheckTime(String code) {
		CheckTime checkTime = (CheckTime) entityManager.createQuery("from CheckTime where code = :code")
						       .getSingleResult();
		return checkTime;
	}
	
}
