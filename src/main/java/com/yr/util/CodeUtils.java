package com.yr.util;

/**
 * 代码工具类
 * @author Administrator
 *
 * 2018年5月22日 上午9:09:05
 *
 */
public class CodeUtils {

	/**
	 * 
	 * @author zxy
	 * 
	 * 2018年5月22日 上午9:09:51
	 * 
	 * @return String
	 */
	public static String getStuCode() {
		String code = "S" + DateUtils.getCurrentYear() + getRound3();
		return code;
	}
	
	/**
	 * 
	 * @author zxy
	 * 
	 * 2018年5月22日 上午9:10:19
	 * 
	 * @param year ..
	 * @return String
	 */
	public static String getClasCode(String year) {
		String code = "C"  + DateUtils.getCurrentYear() + getRound3();
		return code;
	}
	
	/**
	 * 
	 * @author zxy
	 * 
	 * 2018年5月22日 上午9:10:46
	 * 
	 * @param year ..
	 * @return String
	 */
	public static String getTeacherCode(String year) {
		String code = "T" + DateUtils.getCurrentYear() + getRound3();
		return code;
	}
	
	/**
	 * 
	 * @author zxy
	 * 
	 * 2018年5月22日 上午9:11:03
	 * 
	 * @return long
	 */
	public static long getRound3() {
		int max = 9999;
		int min = 1000;
		Long a = Math.round(Math.random() * (max - min) + min);
		return a;
	}
	 
}
