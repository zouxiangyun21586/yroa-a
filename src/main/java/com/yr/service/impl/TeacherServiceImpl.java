package com.yr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yr.dao.TeacherDao;
import com.yr.entity.Teacher;
import com.yr.service.TeacherService;

/**
 * 
 * @author zxy
 *
 * 2018年5月22日 上午8:40:18
 *
 */
@Service
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	private TeacherDao teacherDao;
	
	@Override
	public Boolean add(Teacher teacher) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean update(Teacher teacher) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(Teacher teacher) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Teacher> query() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Teacher get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
