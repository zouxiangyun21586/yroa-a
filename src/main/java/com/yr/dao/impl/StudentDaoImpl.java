package com.yr.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.yr.dao.StudentDao;

/**
 * 
 * @Date:2018年5月22日下午3:38:21	
 *
 * @author: 唐子壕
 * 
 * 学生管理dao层实现类
 */
@Repository
public class StudentDaoImpl implements StudentDao {
	
	@PersistenceContext
	private EntityManager entityManager;
}
