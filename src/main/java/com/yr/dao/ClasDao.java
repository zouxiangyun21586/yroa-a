package com.yr.dao;

import java.util.List;

import com.yr.entity.Clas;
import com.yr.util.PageUtil;

/**
 * 届次 Dao接口
 * @author zxy
 *
 * 2018年5月22日 下午4:45:08
 *
 */
public interface ClasDao {
	/**
	 * 添加
	 * @author zxy
	 * 
	 * 2018年5月22日 上午8:52:09
	 * 
	 * @param clas 届次对象
	 * @return String
	 */
	String add(Clas clas);
	
	/**
	 * 修改
	 * @author zxy
	 * 
	 * 2018年5月22日 上午8:52:21
	 * 
	 * @param clas 届次对象
	 */
	void update(Clas clas);
	
	/**
	 * 
	 * @author zxy
	 * 
	 * 2018年5月22日 上午8:52:33
	 * 
	 * @param clas 届次对象
	 * @return Integer类型
	 */
	Integer delete(Clas clas);
	
	/**
	 * 查询所有 带分页
	 * @author zxy
	 * 
	 * 2018年5月22日 上午8:52:40
	 * 
	 * @param page 第几页
	 * @param limit 每页多少条
	 * @param year 分页条件
	 * @return PageUtil 返回查询的结果,是一个集合
	 */
	PageUtil query(Integer page, Integer limit, String year);
	
	/**
	 * 不分页的查询所有
	 * @author zxy
	 * 
	 * 2018年5月24日 下午5:29:50
	 * 
	 * @return 届次集合
	 */
	List<Clas> query();
	
	/**
	 * 
	 * @author zxy
	 * 
	 * 2018年5月22日 上午8:52:59
	 * 
	 * @param id 需回显的届次id
	 * @return 届次对象
	 */
	Clas get(Integer id);
	
	/**
	 * 毕业
	 * @author zxy
	 * 
	 * 2018年5月23日 上午9:34:37
	 * 
	 * @param code 届次Code
	 * @return String str
	 */
	String graduation(String code);
	
	/**
	 * 开课
	 * @author zxy
	 * 
	 * 2018年5月23日 上午10:24:42
	 * 
	 * @param code 届次
	 * @return String 判断
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
	 * 查询指定届次下的所有批次
	 * @author zxy
	 * 
	 * 2018年5月24日 上午10:56:02
	 * 
	 * @param code 指定届次code
	 * @return Clas 所查询出来的批次
	 */
	String getOnly(String code);
	
	/**
	 * 查看某届次详情
	 * @author zxy
	 * 
	 * 2018年5月28日 下午7:16:07
	 * 
	 * @param page 第几页
	 * @param limit 每页多少条
	 * @param name 分页条件
	 * @param code 查询某届批次的学生信息
	 * @return PageUtil 返回查询的结果,是一个集合
	 */
	PageUtil details(Integer page, Integer limit, String name, String code);
}
