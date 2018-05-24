package com.yr.dao;

import com.yr.entity.Teacher;
import com.yr.util.PageUtil;

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
	 *  @param page 第几页
	 * @param limit 每页多少条
	 * @param name 分页条件
	 * @return PageUtil 返回查询的结果,是一个集合
	 */
	PageUtil query(Integer page, Integer limit, String name);
	
	/**
	 * 
	 * @author zxy
	 * 
	 * 2018年5月22日 上午8:52:59
	 * 
	 * @param code 需回显的老师code
	 * @return 老师对象
	 */
	Teacher get(String code);
}
