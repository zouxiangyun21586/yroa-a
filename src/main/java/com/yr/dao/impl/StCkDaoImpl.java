package com.yr.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.yr.dao.StCkDao;
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
	
}
