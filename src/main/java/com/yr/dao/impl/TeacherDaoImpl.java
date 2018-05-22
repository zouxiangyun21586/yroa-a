package com.yr.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yr.dao.TeacherDao;
import com.yr.entity.Teacher;

/**
 * 老师 Dao 的实现类(jpa)
 * 
 * @author zxy
 *
 */
@Repository
public class TeacherDaoImpl implements TeacherDao {

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
