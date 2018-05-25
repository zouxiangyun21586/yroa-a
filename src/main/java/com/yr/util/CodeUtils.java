package com.yr.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 代码工具类
 * @author Administrator
 *
 * 2018年5月22日 上午9:09:05
 *
 */
public final class CodeUtils {

	private static final Integer T9999 = 9999;
	private static final Integer T1000 = 1000;
	
	private CodeUtils() {
		
	}
	
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
		int max = T9999;
		int min = T1000;
		Long a = Math.round(Math.random() * (max - min) + min);
		return a;
	}
	
	/**
	 * 编号 +1 工具方法
	 * @author 周业好
	 * @param code 你要 +1的编号
	 * @return +1后的结果编号
	 */
	public static String codeConvert(String code) {
		String regEx = "[^0-9]";  
		String re = "[^A-Z]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(code);
		Pattern p1 = Pattern.compile(re);
		Matcher m1 = p1.matcher(code);
		String zimu = m1.replaceAll("").trim();
		Integer i = Integer.valueOf(m.replaceAll("").trim());
		i = i + 1;
		return zimu + i;
	}
}
