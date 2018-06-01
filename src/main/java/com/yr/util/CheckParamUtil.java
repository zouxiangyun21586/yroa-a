package com.yr.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 
 * @Date:2018年5月28日下午10:00:52	
 *
 * @author: 唐子壕
 *
 * @param <T>
 */
public final class CheckParamUtil<T> {
	public CheckParamUtil() {
		
		
	}
    /**
     * 
     * @Title:checkParam
     * @Description:(该方法用来校验对象及其属性是否为空)
     * @param t 
     * @param args 
     * @author 
     * @return String 
     * @throws InvocationTargetException 
     * @throws IllegalArgumentException 
     * @throws IllegalAccessException 
     * @修改时间：2017年6月9日 下午2:18:54
     * @修改内容：创建 
     */
    public String checkParam(T t, String... args) throws IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
    	
    	final Integer a = 4;
    	final Integer b = 3;
    	
        // 如果传入的对象为空，则直接抛出异常
        if (t == null) {
            throw new IllegalArgumentException("This object cann't be empty!");
        }
        Class<? extends Object> clazz = t.getClass();
        // 定义属性列表
        List<String> argsList = new ArrayList<String>();
        // 如果传入的属性名不为空，则将传入的属性名放入属性列表
        if (args != null && args.length > 0) {
            argsList = Arrays.asList(args);
        } else { // 如果传入的属性名为空，则将所有属性名放入属性列表
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
            	if ("id".equals(field.getName())) {
            		System.out.print("跳过id判断是否为空");
            	} else if ("inImgUrl".equals(field.getName())) {
            		System.out.print("跳过inImgUrl判断是否为空");
            	} else if ("finishTime".equals(field.getName())) {
            		System.out.print("跳过finishTime判断是否为空");
            	} else if ("offerTime".equals(field.getName())) {
            		System.out.print("跳过offerTime判断是否为空");
            	} else if ("offerIncome".equals(field.getName())) {
            		System.out.print("跳过offerIncome判断是否为空");
            	} else if ("finishImgUrl".equals(field.getName())) {
            		System.out.print("跳过finishImgUrl判断是否为空");
            	} else {
            		argsList.add(field.getName());
            	}
            }
        }
        // 获取该类自定义的方法数组
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            // 方法名
            String methodName = method.getName();
            // 获取方法对应的属性名
            String fieldName = "";
            if (methodName.length() >= a) {
                fieldName = methodName.substring(b, a).toLowerCase()
                        + methodName.substring(a);
                // 如果方法是“get方法”，并且属性列表中包含该方法对应的属性名
                if (methodName.startsWith("get")
                        && argsList.contains(fieldName)) {
                    if (method.invoke(t) == null) {
                    		if ("name".equals(fieldName)) {
                    			fieldName = "学生姓名";
                    		} else if ("tel".equals(fieldName)) {
                    			fieldName = "学生电话";
                    		} else if ("homeTel".equals(fieldName)) {
                    			fieldName = "家长电话";
                    		} else if ("sex".equals(fieldName)) {
                    			fieldName = "性别";
                    		} else if ("birth".equals(fieldName)) {
                    			fieldName = "出生年月";
                    		} else if ("year".equals(fieldName)) {
                    			fieldName = "届次";
                    		} else if ("addr".equals(fieldName)) {
                    			fieldName = "地址";
                    		} else if ("inTime".equals(fieldName)) {
                    			fieldName = "入学日期";
                    		} else if ("isItDisplayed".equals(fieldName)) {
                    			fieldName = "是否展示该学生";
                    		} else {
                    			fieldName = "是否毕业";
                    		}
                    		return fieldName;
                    }
                    // 如果该方法返回类型为String,返回结果为空字符串，抛出异常。
                    Class<?> returnType = method.getReturnType();
                    String returnTypeName = returnType.getSimpleName();
                    if (returnTypeName.equals("String")
                            && "".equals(((String) (method.invoke(t))).trim())) {
                    	return fieldName;
                    }
                }
            }
        }
		return "checkSuccess";
    }
    
    
    
    /**
     * 
     * @Title:checkParam
     * @Description:(该方法用来校验对象及其属性是否为空)
     * @param t 
     * @param args 
     * @author 
     * @return String 
     * @throws InvocationTargetException 
     * @throws IllegalArgumentException 
     * @throws IllegalAccessException 
     * @修改时间：2017年6月9日 下午2:18:54
     * @修改内容：创建 
     */
    public String checkParam1(T t, String... args) throws IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
    	
    	final Integer a = 4;
    	final Integer b = 3;
    	
        // 如果传入的对象为空，则直接抛出异常
        if (t == null) {
            throw new IllegalArgumentException("This object cann't be empty!");
        }
        Class<? extends Object> clazz = t.getClass();
        // 定义属性列表
        List<String> argsList = new ArrayList<String>();
        // 如果传入的属性名不为空，则将传入的属性名放入属性列表
        if (args != null && args.length > 0) {
            argsList = Arrays.asList(args);
        } else { // 如果传入的属性名为空，则将所有属性名放入属性列表
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
            	if ("id".equals(field.getName())) {
            		System.out.print("跳过id判断是否为空");
            	} else if ("inImgUrl".equals(field.getName())) {
            		System.out.print("跳过inImgUrl判断是否为空");
            	} else if ("finishImgUrl".equals(field.getName())) {
            		System.out.print("跳过finishImgUrl判断是否为空");
            	} else {
            		argsList.add(field.getName());
            	}
            }
        }
        // 获取该类自定义的方法数组
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            // 方法名
            String methodName = method.getName();
            // 获取方法对应的属性名
            String fieldName = "";
            if (methodName.length() >= a) {
                fieldName = methodName.substring(b, a).toLowerCase()
                        + methodName.substring(a);
                // 如果方法是“get方法”，并且属性列表中包含该方法对应的属性名
                if (methodName.startsWith("get")
                        && argsList.contains(fieldName)) {
                    if (method.invoke(t) == null) {
                    	if ("name".equals(fieldName)) {
                			fieldName = "学生姓名";
                		} else if ("tel".equals(fieldName)) {
                			fieldName = "学生电话";
                		} else if ("homeTel".equals(fieldName)) {
                			fieldName = "家长电话";
                		} else if ("sex".equals(fieldName)) {
                			fieldName = "性别";
                		} else if ("birth".equals(fieldName)) {
                			fieldName = "出生年月";
                		} else if ("year".equals(fieldName)) {
                			fieldName = "届次";
                		} else if ("addr".equals(fieldName)) {
                			fieldName = "地址";
                		} else if ("inTime".equals(fieldName)) {
                			fieldName = "入学日期";
                		} else if ("isItDisplayed".equals(fieldName)) {
                			fieldName = "是否展示该学生";
                		} else if ("isFinish".equals(fieldName)) {
                			fieldName = "是否毕业";
                		} else if ("finishTime".equals(fieldName)) {
                			fieldName = "毕业时间";
                		} else if ("offerTime".equals(fieldName)) {
                			fieldName = "入职日期";
                		} else if ("offerIncome".equals(fieldName)) {
                			fieldName = "工资";
                		}
                    	
                    		return fieldName;
                    }
                    // 如果该方法返回类型为String,返回结果为空字符串，抛出异常。
                    Class<?> returnType = method.getReturnType();
                    String returnTypeName = returnType.getSimpleName();
                    if (returnTypeName.equals("String")
                            && "".equals(((String) (method.invoke(t))).trim())) {
                    	return fieldName;
                    }
                }
            }
        }
		return "checkSuccess";
    }
}
