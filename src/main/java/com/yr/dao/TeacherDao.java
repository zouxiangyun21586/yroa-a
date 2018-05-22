package com.yr.dao;

import java.util.List;

import com.yr.entity.Teacher;

/**
 * 老师的  Dao 接口
 * 
 * @author zxy
 *
 */
public interface TeacherDao {
	
	/**
	 * 
	 * @author zxy
	 * 
	 * 2018年5月22日 上午8:52:09
	 * 
	 * @param teacher 老师对象
	 */
	void add(Teacher teacher);
	
	/**
	 * 
	 * @author zxy
	 * 
	 * 2018年5月22日 上午8:52:21
	 * 
	 * @param teacher 老师对象
	 */
	void update(Teacher teacher);
	
	/**
	 * 
	 * @author zxy
	 * 
	 * 2018年5月22日 上午8:52:33
	 * 
	 * @param teacher 老师对象
	 * @return Integer类型
	 */
	Integer delete(Teacher teacher);
	
	/**
	 * 
	 * @author zxy
	 * 
	 * 2018年5月22日 上午8:52:40
	 * 
	 * @return 老师对象
	 */
	List<Teacher> query();
	
	/**
	 * 
	 * @author zxy
	 * 
	 * 2018年5月22日 上午8:52:59
	 * 
	 * @param id 需回显的老师id
	 * @return 老师对象
	 */
	Teacher get(Integer id);
}
