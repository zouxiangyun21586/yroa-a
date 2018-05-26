package com.yr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yr.entity.Role;
import com.yr.service.RoleService;

/**
 * 用户Conntroller
 * @author 周业好
 * 2018年5月22日 上午10:34:00
 */
@Controller
@RequestMapping(value  =  "role")
public class RoleController  {
	@Autowired
	private RoleService role;
	
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
	public String queryFenye(int page, int limit, String name) {
		String json = role.getFenye(page, limit, name);
		return json;
	}
	/**
	 * 查出所有的角色
	 * @author 周业好
	 * @return json
	 */
	@ResponseBody
	@RequestMapping(value = "qrole", produces = "text/json;charset=UTF-8")
	public String queryRoleAll() {
		String json = role.queryRoleAll();
		return json;
	}
	
	/**
	 * 添加  
	 * @param ro 角色实体类
	 * @return json
	 */
	@ResponseBody
	@RequestMapping(value = "add", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	//@Valid   BindingResult result
	public String addId(Role ro) {
		return role.addId(ro);
	}
	/**
	 * 删除
	 * @param code 角色编号
	 * @return json
	 */
	@ResponseBody
	@RequestMapping(value = "/del", method = RequestMethod.DELETE, produces = "text/json;charset=UTF-8")
	public String del(String code) {
		return role.del(code);
	}
	
	/**
	 * 修改角色信息
	 * @param ro 角色信息
	 * @return json
	 */
	@ResponseBody
	@RequestMapping(value = "/upda", method = RequestMethod.PUT, produces = "text/json;charset=UTF-8")
	public String upd(Role ro) {
		String i  = role.upd(ro);
		return i;
	}
	/**
	 * 查询单个(修改数据回显)
	 * @param code 角色编号
	 * @return 查出来的json
	 */ 
	@ResponseBody
	@RequestMapping(value = "upd_echo", method = RequestMethod.GET, produces = "text/json;charset=UTF-8")
	public String query(String code) {
		return role.query(code);
	}
	/**
     * 启用停用
     * @author 周业好
     * @param code 角色编号
     * @return json
     */
	@ResponseBody
	@RequestMapping(value = "switchs", method = RequestMethod.PUT, produces = "text/json;charset=UTF-8")
	public String kaiguan(String code) {
		return role.kaiguan(code);
	}
}
