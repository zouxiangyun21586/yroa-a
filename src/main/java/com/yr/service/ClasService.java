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
	 * @return Boolean值  判断添加是否成功  true: 成功  false: 失败回滚
	 */
	Boolean add(Clas clas);
	
	/**
	 * 
	 * @author zxy
	 * 
	 * 2018年5月22日 下午5:39:29
	 * 
	 * @param clas 届次对象
	 * @return Boolean值 便于判断是否修改成功
	 */
	Boolean update(Clas clas);
	
	/**
	 * 
	 * @author zxy
	 * 
	 * 2018年5月22日 下午5:39:17
	 * 
	 * @param clas 届次对象
	 * @return Boolean值 便于判断是否删除成功
	 */
	Boolean delete(Clas clas);
	
	/**
	 * 
	 * @author zxy
	 * 
	 * 2018年5月22日 下午5:39:08
	 * 
	 * @return 返回届次集合
	 */
	List<Clas> query();
	
	/**
	 * 
	 * @author zxy
	 * 
	 * 2018年5月22日 下午5:39:00
	 * 
	 * @param code 获取页面上需回显的届次code
	 * @return 返回届次对象
	 */
	Clas get(String code);
	
	/**
	 * 毕业
	 * @author zxy
	 * 
	 * 2018年5月23日 上午9:46:35
	 * 
	 * @param code 届次
	 * @return Integer 用于判断
	 */
	Integer graduation(String code);
	
	/**
	 * 开课
	 * @author zxy
	 * 
	 * 2018年5月23日 上午10:28:54
	 * 
	 * @param code 届次
	 * @return Integer 判断
	 */
	Integer openClss(String code);
}
