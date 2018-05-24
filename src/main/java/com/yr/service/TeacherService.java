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
	 * @return Boolean值  判断添加是否成功  true: 成功  false: 失败回滚
	 */
	Boolean add(Teacher teacher);
	
	/**
	 * 
	 * @author zxy
	 * 
	 * 2018年5月22日 上午8:46:11
	 * 
	 * @param teacher 老师对象
	 * @return Boolean值 便于判断是否修改成功
	 */
	Boolean update(Teacher teacher);
	
	/**
	 * 
	 * @author zxy
	 * 
	 * 2018年5月22日 上午8:47:17
	 * 
	 * @param teacher 老师对象
	 * @return Boolean值 便于判断是否删除成功
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
	 * @param code 获取页面上需回显的老师code
	 * @return 返回老师对象
	 */
	Teacher get(String code);
}
