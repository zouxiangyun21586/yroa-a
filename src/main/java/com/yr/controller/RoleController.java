package com.yr.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
	 * @param ro 3
	 * @param result 2 
	 * @param map 1
	 * @return modelandview
	 */
	@RequestMapping(value = "upda", method = RequestMethod.PUT)
	public ModelAndView upd(@Valid @ModelAttribute("roleou")Role ro, BindingResult result, 
			Map<String, Object>map) {
		ModelAndView mv = new ModelAndView();
		if (result.getErrorCount() > 0) {
            List<ObjectError> allErrors = result.getAllErrors();
            if (!CollectionUtils.isEmpty(allErrors)) {
                for (ObjectError allError : allErrors) {
                	//allError.getDefaultMessage() 可看见错误
                	String er = allError.getDefaultMessage();
                }
//                map.put("user", y);
                map.put("roleou", ro);
                mv.setViewName("update");
                return mv;
            }
        }
		int i  = role.upd(ro);
		if (0 == i) {
			mv.addObject("z", "修改失败,检查输入");
		}
		mv.setViewName("redirect:/jsp/list.jsp");
		return mv;
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
