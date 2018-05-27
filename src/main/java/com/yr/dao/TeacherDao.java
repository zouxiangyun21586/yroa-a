package com.yr.dao;

import java.util.List;

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
	 * 添加
	 * @author zxy
	 * 
	 * 2018年5月22日 上午8:52:09
	 * 
	 * @param teacher 老师对象
	 * @return String
	 */
	String add(Teacher teacher);
	
	/**
	 * 查询等级(数据回显使用)
	 * 
	 * @return List<Teacher>
	 * 2018年5月26日上午11:55:46
	 */
	List<Teacher> query();
	
	
	/**
	 * 查询老师状态(数据回显使用)
	 * 0 在职     1 离职     2 实习期
	 * 
	 * @return List<Teacher>
	 * 2018年5月26日下午21:55:46
	 */
	List<Teacher> queryIs();
	
	/**
	 * 修改
	 * @author zxy
	 * 
	 * 2018年5月22日 上午8:52:21
	 * 
	 * @param teacher 老师对象
	 */
	void update(Teacher teacher);
	
	/**
	 * 删除 (有届次的老师不能删除)
	 * @author zxy
	 * 
	 * 2018年5月22日 上午8:52:33
	 * 
	 * @param teacher 老师对象
	 * @return Integer类型
	 */
	Integer delete(Teacher teacher);
	
	/**
	 * 分页查询
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
	 * 数据回显
	 * @author zxy
	 * 
	 * 2018年5月22日 上午8:52:59
	 * 
	 * @param id 需回显的老师id
	 * @return 老师对象
	 */
	Teacher get(Integer id);
	
	/**
	 * 获取角色Code
	 * 
	 * @param keyv 键
	 * @return code
	 * String
	 * 2018年5月26日上午8:55:30
	 */
	String roleCode(String keyv);
}
