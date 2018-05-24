package com.yr.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @Date:2018年5月24日下午2:40:01	
 *
 * @author: 唐子壕
 * 
 * 
 */
public final class AgeUtils {
	
	private AgeUtils() {
		
	}
	
	/**
	 * 
	 * @Date : 2018年5月24日下午2:40:48
	 * 
	 * @author : 唐子壕
	 *	
	 * @return : int
	 *
	 * @param birthDay 日期
	 * 
	 * @describe : 根据传来的时间戳算出年龄 
	 * 
	 * @throws Exception 
	 */
	public static int getAge(Date birthDay) throws Exception {  
        // 获取当前系统时间  
        Calendar cal = Calendar.getInstance();  
        // 如果出生日期大于当前时间，则抛出异常  
        if (cal.before(birthDay)) {  
            throw new IllegalArgumentException("The birthDay is before Now.It's unbelievable!");  
        }  
        // 取出系统当前时间的年、月、日部分  
        int yearNow = cal.get(Calendar.YEAR);  
        int monthNow = cal.get(Calendar.MONTH);  
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);  
  
        // 将日期设置为出生日期  
        cal.setTime(birthDay);  
        // 取出出生日期的年、月、日部分  
        int yearBirth = cal.get(Calendar.YEAR);  
        int monthBirth = cal.get(Calendar.MONTH);  
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);  
        // 当前年份与出生年份相减，初步计算年龄  
        int age = yearNow - yearBirth;  
        // 当前月份与出生日期的月份相比，如果月份小于出生月份，则年龄上减1，表示不满多少周岁  
        if (monthNow <= monthBirth) {  
            // 如果月份相等，在比较日期，如果当前日，小于出生日，也减1，表示不满多少周岁  
            if (monthNow == monthBirth) {  
                if (dayOfMonthNow < dayOfMonthBirth)  {
                	age--;  
                }
            } else {  
                age--;  
            }  
        }  
        return age;  
    }  
	
	/**
	 * 
	 * @Date : 2018年5月24日下午2:47:14
	 * 
	 * @author : 唐子壕 
	 *	
	 * @return : int 
	 *
	 * @param birthTime 
	 * 
	 * @describe : 将年月日转为时间戳传入getAge方法里
	 * 
	 * @throws Exception 
	 */
	public static int birthTime(String birthTime) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
        Date birthdate = formatter.parse(birthTime); 
        return getAge(birthdate);
	}
}
