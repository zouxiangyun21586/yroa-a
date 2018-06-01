package com.yr.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yr.entity.Student;
import com.yr.entity.StudentCheck;
import com.yr.service.StCkService;
import com.yr.service.StudentService;
import com.yr.util.JsonUtils;

/**
 * 学生考勤Controller层
 * @author 林水桥
 * 2018年5月25日下午10:00:07
 */
@Controller("stCkController")
@RequestMapping("attendance")
public class StCkController {
	
	@Autowired
	private StCkService stCkService;
	@Autowired
	private StudentService studService;
	
	/**
     * 导出成Excel表
     * @param map 所有需要导出的数据
     * @return    导出当天考勤报告
     * String
     * 2018年3月1日上午8:24:18
     */
    @RequestMapping("/testExcel")
    public String testExcel(Map<String, Object> map) {
//        map.put("stuList", userService.getAll());
        return "Excels";
    }
	
	/**
	 * 查询所有签到人员数据
	 * @author 林水桥
	 * @return String 放回学生集合  json
	 * 2018年5月26日上午9:15:48
	 */
	@ResponseBody
	@RequestMapping(value = "/atteList", produces = "text/json;charset=UTF-8")
	public String atteList() {
		List<Student> student = studService.queryNoGre();
		return JsonUtils.beanListToJson(student);
	}
	
	/**
	 * 学生签到
	 * @author 林水桥
	 * @param code   学生code
	 * @return String 学生签到状态 0为成功
	 * 2018年5月26日下午2:54:25
	 */
	@ResponseBody
	@RequestMapping(value = "/signIn", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String signIn(String code) {
		String stuCk = stCkService.duty(code);
		return stuCk;
	}
	
	/**
	 * 查询考勤时间
	 * @author 林水桥
	 * @return String 返回考勤时间json数据
	 * 2018年5月28日下午9:30:09
	 */
	@ResponseBody
	@RequestMapping(value = "/checkTimeCode", produces = "text/json;charset=UTF-8")
	public String checkTimeCode() {
		
		return stCkService.checkTimeCode();
	}
	
	/**
	 * 查询所有考勤数据     带分页
	 * @author 林水桥
	 * @param page             当前页
	 * @param limit            每页多少条数据
	 * @param name             学生姓名模糊查询
	 * @param checkTimeCode    考勤时间编码 AM PM NT
	 * @param status           考勤状态 0没迟到 1迟到 2旷课 3请假 4早退
	 * @return String          返回学生考勤数据json格式
	 * 2018年5月28日下午10:18:33
	 */
	@ResponseBody
	@RequestMapping(value = "/getAttendance", produces = "text/json;charset=UTF-8")
	public String getAttendance(int page, int limit, String name, String checkTimeCode, Integer status) {
		String json = null;
		try {
			name = new String(name.getBytes("ISO-8859-1"), "utf-8");
			json = stCkService.getAttendance(page, limit, name, checkTimeCode, status);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * 当天考勤报告
	 * @author 林水桥
	 * @param page 分页当前页
	 * @param limit 每页多少条记录
	 * @param ckStatus 考勤时间状态 AM,PM,NT
	 * @return String 返回当天考勤数据 根据考勤日期倒序排序
	 * 2018年5月28日下午8:11:41
	 */
	@ResponseBody
	@RequestMapping(value = "/report", produces = "text/json;charset=UTF-8")
	public String report(int page, int limit, String ckStatus) {
		String a = stCkService.report(page, limit, ckStatus);
		return a;
	}
	
	/**
	 * 添加考勤,判断如果:
	 * 签到了显示 请假(事,病)或早退功能
	 * 未签到显示 补签,请假(事,病),旷课
	 * 
	 * @author zxy
	 * 
	 * 2018年5月31日 下午5:16:42
	 * 
	 * @param sc 学生考勤
	 * @return 判断是否执行成功
	 */
	@ResponseBody
	@RequestMapping(value = "/addAttendance", produces = "text/json;charset=UTF-8")
	public String addAttendance(StudentCheck sc) {
		stCkService.add(sc);
		return null;
	}
	
	/**
	 * 查询字典表中的考勤状态
	 * @author zxy
	 * 
	 * 2018年5月31日 下午6:04:18
	 * 
	 * @return strJson
	 */
	@ResponseBody
	@RequestMapping(value = "/stckDic", produces = "text/json;charset=UTF-8")
	public String stckDic() {
		String str = stCkService.stckDic();
		return str;
	}
	
}
