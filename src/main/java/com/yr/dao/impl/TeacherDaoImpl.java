package com.yr.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.yr.dao.TeacherDao;
import com.yr.entity.Teacher;

/**
 * 鑰佸笀 Dao 鐨勫疄鐜扮被(jpa)
 * 
 * @author zxy
 *
 */
@Repository
public class TeacherDaoImpl implements TeacherDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void add(Teacher teacher) {
		
	}

	@Override
	public void update(Teacher teacher) {
		
	}

	@Override
	public int delete(Teacher teacher) {
		return 0;
	}

	@Override
	public List<Teacher> query() {
		return null;
	}

	@Override
	public Teacher get(Long id) {
		return null;
	}

}
