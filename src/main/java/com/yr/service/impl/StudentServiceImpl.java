package com.yr.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yr.dao.StudentDao;
import com.yr.service.StudentService;

/**
 * 
 * @Date:2018年5月22日下午3:36:12	
 *
 * @author: 唐子壕
 * 
 * 学生管理service层实现类
 */
@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentDao studentDao;
	
}
