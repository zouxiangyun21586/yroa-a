package com.yr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.yr.service.ClasService;

/**
 * 届次 controller
 * @author zxy
 *
 * 2018年5月22日 下午4:44:24
 *
 */
@Controller
public class ClasController {
	
	@Autowired
	private ClasService clasService;
	
}
