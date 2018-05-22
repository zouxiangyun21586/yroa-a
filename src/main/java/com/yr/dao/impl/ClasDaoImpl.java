package com.yr.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.yr.dao.ClasDao;

/**
 * 届次 Dao 实现类
 * @author zxy
 *
 * 2018年5月22日 下午4:45:27
 *
 */
@Repository
public class ClasDaoImpl implements ClasDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
}
