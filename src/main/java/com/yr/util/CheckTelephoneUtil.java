package com.yr.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @Date:2018年5月30日上午8:13:16	
 *
 * @author: 唐子壕
 *
 */
public final class CheckTelephoneUtil {
	
	
	
	
	
	private CheckTelephoneUtil() {
		
	}
	/**
	 * 
	 * @Date : 2018年5月30日上午8:25:18
	 * 
	 * @author : 唐子壕
	 *	
	 * @return : boolean 
	 *
	 * @param str 
	 */
	 public static boolean isMobile(String str) {  
	     Pattern p = null;  
	     Matcher m = null;  
	     boolean b = false;  
	     p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号  
	     m = p.matcher(str);  
	     b = m.matches();  
	     return b;  
	 }  
	 
}
