package com.yr.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yr.dao.AccountDao;
import com.yr.dao.StudentDao;
import com.yr.entity.Account;
import com.yr.entity.Clas;
import com.yr.entity.Student;
import com.yr.service.StudentService;
import com.yr.util.HanyuPinyinHelper;
import com.yr.util.JsonUtils;
import com.yr.util.PageUtil;

import net.sf.json.JSONObject;

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
	
	@Autowired
	private AccountDao accountDao;
	
	
	/**查询学生信息
	 * 
	 * @Date : 2018年5月22日下午7:15:23
	 * 
	 * @author : 唐子壕
	 *	
	 * @param page 第几页
	 * 
	 * @param limit 每页多少条
	 * 
	 * @param name 搜索条件
	 * 
	 * @param modules 搜索条件 
	 *
	 * @return : PageUtil 返回分页查询或模糊查询或根据已毕业,未毕业查询的结果,是一个集合
	 * 
	 * @see com.yr.service.StudentService#queryStudent(java.lang.Integer, java.lang.Integer, java.lang.String)
	 */
	public String queryStudent(Integer page, Integer limit, String name, String modules) {
		PageUtil pageUtil = studentDao.queryStudent(page, limit, name, modules); //返回查询的结果
		String result = JsonUtils.beanToJson(pageUtil); //将结果转为json格式
		return result;
	}
	
	/**添加学生信息
	 * 
	 * @Date : 2018年5月22日下午5:40:11
	 * 
	 * @author : 唐子壕
	 * 
	 * @param student 方法重写，带参数
	 *
	 * @return : String 返回一个提示
	 * 
	 * @describe : 添加学生：添加成功后去自动生成一个学生账号用于登入,账号为学生姓名的拼音字母,密码默认12345678
	 *  
	 * @see com.yr.service.StudentService#addStudent(com.yr.entity.Student)
	 */
	@Transactional
	public String addStudent(Student student) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		String resutl1 = studentDao.addStudent(student); //获取添加是否成功的信息
		if ("addSuccess".equals(resutl1)) { //表示成功
			Account account = addAccount(student); // 得到要添加到账号表中的默认信息
			String studentCode = studentDao.queryRoleCod(); //从角色表中查询出学生的code
			accountDao.addId(account, studentCode); //添加成功后去账号表中添加默认信息和从角色表中查询出学生的code
			map.put("code", 0);
			map.put("msg", "添加成功"); //返回成功信息
		} else if ("addFail".equals(resutl1)) { //表示添加失败
			map.put("code", 1);
			map.put("msg", "添加失败");
		} else if ("alreadyExisted".equals(resutl1)) { //表示该学生已添加
			map.put("code", 1);
			map.put("msg", "该学生已经添加过了");
		} else if ("telformattingError".equals(resutl1)) { //表示校验学生电话不通过
			map.put("code", 1);
			map.put("msg", "学生电话格式不对");
		} else if ("homeTelformattingError".equals(resutl1)) { //表示校验家长电话不通过
			map.put("code", 1);
			map.put("msg", "家长电话格式不对");
		} else { //表示校验参数不为空不通过
			map.put("code", 1);
			map.put("msg", resutl1 + "不能为空");
		}
		String result = JSONObject.fromObject(map).toString(); //将要返回到页面的信息放map转为json格式
		return result;
	}

	/**删除学生信息
	 * 
	 * @Date : 2018年5月23日下午3:08:34
	 * 
	 * @author : 唐子壕
	 *	
	 * @param id 学生id
	 * 
	 * @return : String
	 * 
	 * @describe  将删除是否成功信息放进map里转为json格式返回到controller层
	 * 
	 * @see com.yr.service.StudentService#deleteStudent(java.lang.Integer)
	 */
	@Transactional
	public String deleteStudent(Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String account = studentDao.deleteStudent(id); //得到删除是否成功的信息
			accountDao.del(account); //删除学生信息后,将该学生的账号信息也删掉
			map.put("code", 0);
			map.put("msg", "删除成功");
		} catch (Exception e) {
			map.put("code", 1);
			map.put("msg", "删除失败");
			map.put("error", e);
		}
		String result = JSONObject.fromObject(map).toString();
		return result;
	}
	
	/**修改学生信息
	 * 
	 * @Date : 2018年5月24日下午8:23:07
	 * 
	 * @author : 唐子壕
	 * 
	 * @param student 
	 * 
	 * @return String 
	 * 
	 * @describe 将修改是否成功信息放进map里转为json格式返回到controller层
	 *	
	 * @see com.yr.service.StudentService#updateStudent(java.lang.Integer)
	 */
	@Transactional
	public String updateStudent(Student student) {
		Map<String, Object> map = new HashMap<String, Object>();
		String value = studentDao.updateStudent(student);
		if ("updateSuccess".equals(value)) {
			map.put("code", 0);
			map.put("msg", "修改成功");
		} else if ("addFail".equals(value)) {
			map.put("code", 1);
			map.put("msg", "添加失败");
		} else if ("telformattingError".equals(value)) {
			map.put("code", 1);			
			map.put("msg", "学生电话格式不对");
		} else if ("homeTelformattingError".equals(value)) {
			map.put("code", 1);
			map.put("msg", "家长电话格式不对");
		} else {
			map.put("code", 1);
			map.put("msg", value + "不能为空");
		}
		String result = JSONObject.fromObject(map).toString(); //将要返回到页面的信息放map转为json格式
		return result;
	}
	/**
	 * 
	 * @Date : 2018年5月26日上午11:13:58
	 * 
	 * @author : 唐子壕
	 * 
	 * @return List<Clas> 届次所有信息
	 * 
	 * @describe 将查询出的所有信息转为json格式返回给controller层
	 * 
	 * @see com.yr.service.StudentService#queryCls()
	 */
	public String queryCls() {
		List<Clas> clasList =  studentDao.queryCls(); //获取查询到的届次表的结果集
		String result = JsonUtils.beanListToJson(clasList); //将要返回到页面的信息放map转为json格式
		return result;
	}
	
	
	/**
	 * 
	 * @Date : 2018年5月23日下午8:47:35
	 * 
	 * @author : 唐子壕
	 *	
	 * @param student 学生实体
	 * 
	 * @return : Account 
	 * 
	 * @describe 添加学生时该学生的账号信息(传入账号模块所提供的方法内),
	 * 
	 */
	public Account addAccount(Student student) {
		Account account = new Account();
		HanyuPinyinHelper hanyuPinyinHelper = new HanyuPinyinHelper();
        String username = hanyuPinyinHelper.toHanyuPinyin(student.getName()); //学生账号 默认是该学生的名字拼音
        String password = "12345678"; //学生密码 默认12345678
		String isAdmin = "否"; //是否是管理员 默认是否
		String tel = student.getTel(); //学生电话
		account.setUserName(username);
		account.setIsAdmin(isAdmin);
		account.setPassWord(password);
		account.setTel(tel);
		return account;
	}
	
	/**修改数据回显
	 * 
	 * @Date : 2018年5月24日下午8:22:51
	 * 
	 * @author : 唐子壕
	 *	 
	 * @param id 根据id查询出一个学生对象用于数据回显 
	 * 
	 * @return Student 学生对象
	 * 
	 * @see com.yr.service.StudentService#updateDisplay(java.lang.Integer)
	 */
	public Student updateDisplay(Integer id) {
		Student student = studentDao.updateDisplay(id);
		return student;
	}
	
	/**
	 * 
	 * @Date : 2018年5月26日上午11:44:22
	 * 
	 * @author : 唐子壕
	 *	
	 * @return : List<Student>
	 *
	 * @see com.yr.service.StudentService#queryNoGre()
	 */
	public List<Student> queryNoGre() {
		List<Student> studentList = studentDao.queryNoGre();
		return studentList;
	}

	/**
	 * 
	 * @Date : 2018年5月26日上午11:47:54
	 * 
	 * @author : 唐子壕
	 *	
	 * @param code 
	 * 
	 * @return : Student
	 * 
	 * @see com.yr.service.StudentService#querytoCode()
	 */
	public Student querytoCode(String code) {
		Student studentList = studentDao.querytoCode(code);
		return studentList;
	}

	/**
	 * 
	 * @Date : 2018年5月31日下午3:18:57
	 * 
	 * @author : 唐子壕
	 *	
	 * @param id 学生id
	 * 
	 * @return String  
	 * 
	 * @see com.yr.service.StudentService#employment(java.lang.Integer)
	 */
	public String employment(Integer id) {
		Student student = studentDao.employment(id);
		String result = JsonUtils.beanToJson(student);
		return result;
	}
	
	/**
	 * 
	 * @Date : 2018年6月1日下午2:47:11
	 * 
	 * @author : 唐子壕
	 *	
	 * @param id 学生id
	 *
	 * @return Student 
	 *  
	 * @see com.yr.service.StudentService#getPic(java.lang.Integer)
	 */
	public Student getPic(Integer id) {
		Student student = studentDao.employment(id);
		return student;
	}
	
	/**
	 * 根据账号获取学生数据
	 * @author 林水桥
	 * @param userName 学生账号
	 * @return Student 返回学生数据
	 * 2018年5月31日下午10:15:29
	 */
	public Student getAccount(String userName) {
		return studentDao.getAccount(userName);
	}
	
}
