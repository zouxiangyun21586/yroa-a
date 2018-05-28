package com.yr.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yr.entity.Auth;
import com.yr.service.AuthService;

/**
 * 用户Conntroller
 * @author 周业好
 * 2018年5月22日 上午10:34:00
 */
@Controller
@RequestMapping(value  =  "auth")
public class AuthController  {
	@Autowired
	private AuthService aut;
	
	private static final int THREE = 3;
	/**
	 * 
	 * @author 周业好
	 * 分页方法
	 * @param page 当前第几页
	 * @param limit 每页多少条
	 * @param name 搜索条件
	 * @return x
	 */
	@ResponseBody
	@RequestMapping(value = "/queryfy", produces = "text/json;charset=UTF-8")
	public String queryfy(int page, int limit, String name) {
		String json = aut.getFenye(page, limit, name);
		return json;
	}
	/**
	 * 查出所有的权限
	 * @author 周业好
	 * @return json
	 */
	@ResponseBody
	@RequestMapping(value = "/qaut", produces = "text/json;charset=UTF-8")
	public String qaut() {
		String json = aut.queryAuthAll();
		return json;
	}
	
	/**
	 * 添加  
	 * @param ro 权限实体类
	 * @return json
	 */
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	//@Valid   BindingResult result
	public String add(Auth ro) {
		return aut.addId(ro);
	}
	/**
	 * 删除
	 * @param code 权限编号
	 * @return json
	 */
	@ResponseBody
	@RequestMapping(value = "/del", method = RequestMethod.DELETE, produces = "text/json;charset=UTF-8")
	public String del(String code) {
		return aut.del(code);
	}
	
	/**
	 * 修改权限信息
	 * @param ro 权限信息
	 * @return json
	 */
	@ResponseBody
	@RequestMapping(value = "/upda", method = RequestMethod.PUT, produces = "text/json;charset=UTF-8")
	public String upda(Auth ro) {
		String i  = aut.upd(ro);
		return i;
	}
	/**
	 * 查询单个(修改数据回显)
	 * @param code 权限编号
	 * @return 查出来的json
	 */ 
	@ResponseBody
	@RequestMapping(value = "/upd_echo", method = RequestMethod.GET, produces = "text/json;charset=UTF-8")
	public String updEcho(String code) {
		return aut.query(code);
	}
	/**
     * 启用停用
     * @author 周业好
     * @param code 权限编号
     * @return json
     */
	@ResponseBody
	@RequestMapping(value = "/switchs", method = RequestMethod.PUT, produces = "text/json;charset=UTF-8")
	public String switchs(String code) {
		return aut.kaiguan(code);
	}
	
	/**
     * 查询角色拥有的权限 (用于复选框)
     * @param code  角色code
     * @return json
     */
	@ResponseBody
	@RequestMapping(value = "/getResource", method = RequestMethod.GET, produces = "text/json;charset=UTF-8")
	public String getResource(String code) {
		String json = aut.getResource(code);
		return json;
	}
	
	/**
     * 查询角色拥有的权限 (用于查看)
     * @author 周业好
     * @param code 角色code
     * @return json
     */
	@ResponseBody
	@RequestMapping(value = "/lookResource", method = RequestMethod.GET, produces = "text/json;charset=UTF-8")
	public String lookResource(String code) {
		return aut.lookResource(code);
	}
	
}
