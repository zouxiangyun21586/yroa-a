package com.yr.dao;

import java.util.List;

import com.yr.entity.Clas;

/**
 * 届次 Dao接口
 * @author zxy
 *
 * 2018年5月22日 下午4:45:08
 *
 */
public interface ClasDao {
	/**
	 * 
	 * @author zxy
	 * 
	 * 2018年5月22日 上午8:52:09
	 * 
	 * @param clas 届次对象
	 */
	void add(Clas clas);
	
	/**
	 * 
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
	 * 
	 * @author zxy
	 * 
	 * 2018年5月22日 上午8:52:40
	 * 
	 * @return 届次对象
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
	 * @return Integer 用于判断
	 */
	Integer graduation(String code);
	
	/**
	 * 开课
	 * @author zxy
	 * 
	 * 2018年5月23日 上午10:24:42
	 * 
	 * @param code 届次
	 * @return Integer 判断
	 */
	Integer openClss(String code);
}
