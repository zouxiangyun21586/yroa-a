package com.yr.service;

import com.yr.entity.Teacher;

/**
 * 老师的 service 接口
 * 
 * @author zxy
 *
 */
public interface TeacherService {
	
	/**
	 * 
	 * @author zxy
	 * 
	 * 2018年5月22日 上午8:42:21
	 * 
	 * @param teacher 老师实体类
	 * @return Boolean
	 */
	Boolean add(Teacher teacher);
	
	/**
	 * 查询所有(数据回显使用)
	 * 
	 * @return Boolean
	 * 2018年5月26日上午11:56:58
	 */
	Boolean query();
	
	/**
	 * 
	 * @author zxy
	 * 
	 * 2018年5月22日 上午8:46:11
	 * 
	 * @param teacher 老师对象
	 * @return Boolean
	 */
	Boolean update(Teacher teacher);
	
	/**
	 * 
	 * @author zxy
	 * 
	 * 2018年5月22日 上午8:47:17
	 * 
	 * @param teacher 老师对象
	 * @return Boolean
	 */
	Boolean delete(Teacher teacher);
	
	/**
	 * 
	 * @author zxy
	 * 
	 * 2018年5月22日 上午8:49:02
	 * 
	 * @param page 第几页
	 * @param limit 每页多少条
	 * @param name 分页条件
	 * @return String
	 */
	String query(Integer page, Integer limit, String name);
	
	/**
	 * 
	 * @author zxy
	 * 
	 * 2018年5月22日 上午8:50:01
	 * 
	 * @param id 获取页面上需回显的老师id
	 * @return 返回老师对象
	 */
	Teacher get(Integer id);
}
