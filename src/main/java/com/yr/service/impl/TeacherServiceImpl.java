package com.yr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional
@Service
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	private TeacherDao teacherDao;
	final Integer number = 2;

	/**
	 * 
	 * @author zxy
	 * 
	 * 2018年5月22日 下午2:58:15
	 * 
	 * @param teacher 老师对象
	 * @return 返回boolean 判断是否成功
	 */
	public Boolean add(Teacher teacher) {
		try {
			teacherDao.add(teacher);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 
	 * @author zxy
	 * 
	 * 2018年5月22日 下午2:58:56
	 * 
	 * @param teacher 老师对象
	 * @return 返回boolean 判断是否成功
	 */
	public Boolean update(Teacher teacher) {
		try {
			teacherDao.update(teacher);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 
	 * @author zxy
	 * 
	 * 2018年5月22日 下午2:59:01
	 * 
	 * @param teacher 老师对象
	 * @return 返回boolean 判断是否成功
	 */
	public Boolean delete(Teacher teacher) {
		try {
			Integer code = teacherDao.delete(teacher);
			if (code == number) { // 如果返回的是 2 那么代表可以删除
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	/**
	 * 
	 * @author zxy
	 * 
	 * 2018年5月22日 下午2:59:07
	 * 
	 * @return 返回老师集合
	 */
	public List<Teacher> query() {
		List<Teacher> listTeacher = teacherDao.query();
		return listTeacher;
	}

	/**
	 * 
	 * @author zxy
	 * 
	 * 2018年5月22日 下午2:59:21
	 * 
	 * @param code 某老师的code
	 * @return 老师对象,用于数据回显
	 */
	public Teacher get(String code) {
		Teacher listTeacher = teacherDao.get(code);
		return listTeacher;
	}

}
