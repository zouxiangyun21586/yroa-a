package com.yr.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yr.dao.ClasDao;
import com.yr.service.ClasService;

/**
 * 届次 Service实现类
 * @author zxy
 *
 * 2018年5月22日 下午4:51:56
 *
 */
@Service
public class ClasServiceImpl implements ClasService {
	
	@Autowired
	private ClasDao clasDao;
}
