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
}
