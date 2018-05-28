package com.yr.service;

import java.util.List;

import com.yr.entity.Clas;

/**
 * 届次 service接口
 * @author zxy
 *
 * 2018年5月22日 下午4:51:32
 *
 */
public interface ClasService {
	
	/**
	 * 
	 * @author zxy
	 * 
	 * 2018年5月22日 下午5:39:38
	 * 
	 * @param clas 届次实体类
	 * @return String
	 */
	String add(Clas clas);
	
	/**
	 * 
	 * @author zxy
	 * 
	 * 2018年5月22日 下午5:39:29
	 * 
	 * @param clas 届次对象
	 * @return String
	 */
	String update(Clas clas);
	
	/**
	 * 
	 * @author zxy
	 * 
	 * 2018年5月22日 下午5:39:17
	 * 
	 * @param clas 届次对象
	 * @return Boolean
	 */
	String delete(Clas clas);
	
	/**
	 * 
	 * @author zxy
	 * 
	 * 2018年5月22日 下午5:39:08
	 * 
	 * @param page 第几页
	 * @param limit 每页多少条
	 * @param name 分页条件
	 * @return Boolean
	 */
	String query(Integer page, Integer limit, String name);
	
	/**
	 * 不分页的查询所有
	 * @author zxy
	 * 
	 * 2018年5月24日 下午5:33:20
	 * 
	 * @return 届次集合
	 */
	List<Clas> query();
	
	/**
	 * 
	 * @author zxy
	 * 
	 * 2018年5月22日 下午5:39:00
	 * 
	 * @param id 获取页面上需回显的届次id
	 * @return 返回届次对象
	 */
	Clas get(Integer id);
	
	/**
	 * 毕业
	 * @author zxy
	 * 
	 * 2018年5月23日 上午9:46:35
	 * 
	 * @param code 届次
	 * @return String 返回消息
	 */
	String graduation(String code);
	
	/**
	 * 开课
	 * @author zxy
	 * 
	 * 2018年5月23日 上午10:28:54
	 * 
	 * @param code 届次
	 * @return String 是否成功
	 */
	String openClss(String code);
	
	/**
	 * 提供方法给添加假期模块
	 * @author zxy
	 * 
	 * 2018年5月22日 下午5:48:57
	 * 
	 * @param code 根据届次code查询届次数据
	 * @return 返回某届次的对象
	 */
	Clas getCode(String code);
	
	/**
	 * 
	 * @author zxy
	 * 
	 * 2018年5月24日 上午11:01:09
	 * 
	 * @param code 届次code
	 * @return 查询出来的批次
	 */
	String getOnly(String code);
	
	/**
	 * 
	 * @author zxy
	 * 
	 * 2018年5月28日 下午7:41:38
	 * 
	 * @param page 第几页
	 * @param limit 每页多少条
	 * @param name 分页条件
	 * @param code 查询某届批次的学生信息
	 * @return PageUtil 返回查询的结果,是一个集合
	 */
	String details(Integer page, Integer limit, String name, String code);
}
