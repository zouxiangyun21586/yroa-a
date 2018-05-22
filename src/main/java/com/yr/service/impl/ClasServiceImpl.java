package com.yr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yr.dao.ClasDao;
import com.yr.entity.Clas;
import com.yr.service.ClasService;

/**
 * 届次 Service实现类
 * @author zxy
 *
 * 2018年5月22日 下午4:51:56
 *
 */
@Transactional
@Service
public class ClasServiceImpl implements ClasService {
	
	@Autowired
	private ClasDao clasDao;
	final Integer number = 2;

	/**
	 * 
	 * @author zxy
	 * 
	 * 2018年5月22日 下午2:58:15
	 * 
	 * @param clas 届次对象
	 * @return 返回boolean 判断是否成功
	 */
	public Boolean add(Clas clas) {
		try {
			clasDao.add(clas);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 
	 * @author zxy
	 * 
	 * 2018年5月22日 下午2:58:56
	 * 
	 * @param clas 届次对象
	 * @return 返回boolean 判断是否成功
	 */
	public Boolean update(Clas clas) {
		try {
			clasDao.update(clas);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 
	 * @author zxy
	 * 
	 * 2018年5月22日 下午2:59:01
	 * 
	 * @param clas 届次对象
	 * @return 返回boolean 判断是否成功
	 */
	public Boolean delete(Clas clas) {
//		try {
//			Integer code = clasDao.delete(clas);
//			if (code == number) { // 如果返回的是 2 那么代表可以删除
//				return true;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
		return false;
	}

	/**
	 * 
	 * @author zxy
	 * 
	 * 2018年5月22日 下午2:59:07
	 * 
	 * @return 返回届次集合
	 */
	public List<Clas> query() {
		List<Clas> listClas = clasDao.query();
		return listClas;
	}

	/**
	 * 
	 * @author zxy
	 * 
	 * 2018年5月22日 下午2:59:21
	 * 
	 * @param id 某届次的id
	 * @return 届次对象,用于数据回显
	 */
	public Clas get(Integer id) {
		Clas listClas = clasDao.get(id);
		return listClas;
	}
}
