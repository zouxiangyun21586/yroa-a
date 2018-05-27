package com.yr.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

/**
 * Json与javaBean之间的转换工具类
 * 
 * @author liucong
 *
 * @date 2017年8月4日
 */
public final class JsonUtils {

	private JsonUtils() {
		
	}
	
	/**
	 * 从一个JSON 对象字符格式中得到一个java对象
	 * 
	 * @param jsonString String
	 * @param beanCalss Class<T>
	 * @param <T> 泛型
	 * @return <T>
	 */
	@SuppressWarnings("unchecked")
	public static <T> T jsonToBean(String jsonString, Class<T> beanCalss) {

		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		T bean = (T) JSONObject.toBean(jsonObject, beanCalss);

		return bean;

	}

	/**
	 * 将java对象转换成json字符串
	 *
	 * @param bean Object
	 * @return String
	 */
	public static String beanToJson(Object bean) {

		JSONObject json = JSONObject.fromObject(bean);

		return json.toString();

	}

	/**
	 * 将java对象转换成json字符串
	 * @param bean 要转换的java对象
	 * @param noryChanges 放字段数组
	 * @param nory true:只查字段数组中有的字段   
	 * 			   false:不查字段数组中有的字段
	 * @return String
	 */
	public static String beanToJson(Object bean, String[] noryChanges, boolean nory) {

		JSONObject json = null;

		if (nory) { // 转换_nory_changes里的属性

			Field[] fields = bean.getClass().getDeclaredFields();
			String str = "";
			for (Field field : fields) {
 				str += (":" + field.getName());
			}
			fields = bean.getClass().getSuperclass().getDeclaredFields();
			for (Field field : fields) {
				str += (":" + field.getName());
			}
			str += ":";
			for (String s : noryChanges) {
				str = str.replace(":" + s + ":", ":");
			}
			json = JSONObject.fromObject(bean, configJson(str.split(":")));

		} else { // 转换除了_nory_changes里的属性

			json = JSONObject.fromObject(bean, configJson(noryChanges));
		}
		return json.toString();

	}

	/**
	 * 
	 * @author zxy
	 * 
	 * 2018年5月22日 上午9:21:13
	 * 
	 * @param excludes String数组
	 * @return JsonConfig
	 */
	private static JsonConfig configJson(String[] excludes) {

		JsonConfig jsonConfig = new JsonConfig();

		jsonConfig.setExcludes(excludes);
		//
		jsonConfig.setIgnoreDefaultExcludes(false);
		//
		// jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);

		// jsonConfig.registerJsonValueProcessor(Date.class,
		//
		// new DateJsonValueProcessor(datePattern));

		return jsonConfig;

	}

	/**
	 * 将java对象List集合转换成json字符串
	 * 
	 * @param beans java对象List集合
	 * @return String
	 */
	public static String beanListToJson(List<?> beans) {

		StringBuffer rest = new StringBuffer();

		rest.append("[");

		int size = beans.size();

		for (int i = 0; i < size; i++) {

			rest.append(beanToJson(beans.get(i)) + ((i < size - 1) ? "," : ""));

		}

		rest.append("]");

		return rest.toString();

	}

	/**
	 * 将java对象List集合转换成json字符串
	 * @param beans java对象List集合
	 * @param noryChanges 放字段数组
	 * @param nory true:只查字段数组中有的字段   
	 * 			   false:不查字段数组中有的字段
	 * @return String
	 */
	public static String beanListToJson(List<?> beans, String[] noryChanges, boolean nory) {

		StringBuffer rest = new StringBuffer();

		rest.append("[");

		int size = beans.size();

		for (int i = 0; i < size; i++) {
			try {
				rest.append(beanToJson(beans.get(i), noryChanges, nory));
				if (i < size - 1) {
					rest.append(",");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		rest.append("]");

		return rest.toString();

	}

	/**
	 * 从json HASH表达式中获取一个map，改map支持嵌套功能
	 *
	 * @param jsonString JSONSTRING
	 * @return Map<S,O>
	 */
	public static Map<String, Object> jsonToMap(String jsonString) {

		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		Iterator<?> keyIter = jsonObject.keys();
		String key;
		Object value;
		Map<String, Object> valueMap = new HashMap<String, Object>();
		while (keyIter.hasNext()) {
			key = (String) keyIter.next();
			value = jsonObject.get(key).toString();
			valueMap.put(key, value);
		}

		return valueMap;
	}

	/**
	 * map集合转换成json格式数据
	 * @param map map集合
	 * @param noryChanges 放字段数组
	 * @param nory true:只查字段数组中有的字段   
	 * 			   false:不查字段数组中有的字段
	 * @return String json格式数据
	 */
	public static String mapToJson(Map<String, ?> map, String[] noryChanges, boolean nory) {

		String sJson = "{";

		Set<String> key = map.keySet();
		for (Iterator<?> it = key.iterator(); it.hasNext();) {
			String s = (String) it.next();
			if (map.get(s) == null) {
				int aa = 0;
			} else if (map.get(s) instanceof List<?>) {
				sJson += (s + ":" 
						+ JsonUtils.beanListToJson((List<?>) map.get(s), noryChanges, nory));

			} else {
				JSONObject json = JSONObject.fromObject(map);
				sJson += (s + ":" + json.toString());
			}

			if (it.hasNext()) {
				sJson += ",";
			}
		}

		sJson += "}";
		return sJson;
	}

	/**
	 * 从json数组中得到相应java数组
	 *
	 * @param jsonString json数组
	 * @return Object 数组
	 */
	public static Object[] jsonToObjectArray(String jsonString) {

		JSONArray jsonArray = JSONArray.fromObject(jsonString);

		return jsonArray.toArray();

	}

	/**
	 * 
	 * @author zxy
	 * 
	 * 2018年5月22日 上午9:18:22
	 * 
	 * @param list 泛型List
	 * @return String
	 */
	public static String listToJson(List<?> list) {

		JSONArray jsonArray = JSONArray.fromObject(list);

		return jsonArray.toString();

	}

	/**
	 * 从json对象集合表达式中得到一个java对象列表
	 *
	 * @param jsonString json数组
	 * @param beanClass 实体类class
	 * @param <T> 泛型
	 * @return 泛型数组
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> jsonToBeanList(String jsonString, Class<T> beanClass) {
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		JSONObject jsonObject;
		T bean;
		int size = jsonArray.size();
		List<T> list = new ArrayList<T>(size);

		for (int i = 0; i < size; i++) {

			jsonObject = jsonArray.getJSONObject(i);
			bean = (T) JSONObject.toBean(jsonObject, beanClass);
			list.add(bean);
		}
		return list;
	}

	/**
	 * 从json数组中解析出java字符串数组
	 *
	 * @param jsonString json数组
	 * @return String 数组
	 */
	public static String[] jsonToStringArray(String jsonString) {

		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		String[] stringArray = new String[jsonArray.size()];
		int size = jsonArray.size();

		for (int i = 0; i < size; i++) {

			stringArray[i] = jsonArray.getString(i);

		}

		return stringArray;
	}

	/**
	 * 从json数组中解析出javaLong型对象数组
	 *
	 * @param jsonString json数组
	 * @return Long 数组
	 */
	public static Long[] jsonToLongArray(String jsonString) {

		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		int size = jsonArray.size();
		Long[] longArray = new Long[size];

		for (int i = 0; i < size; i++) {

			longArray[i] = jsonArray.getLong(i);

		}

		return longArray;

	}

	/**
	 * 从json数组中解析出java Integer型对象数组
	 *
	 * @param jsonString json数组
	 * @return Integer数组
	 */
	public static Integer[] jsonToIntegerArray(String jsonString) {

		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		int size = jsonArray.size();
		Integer[] integerArray = new Integer[size];

		for (int i = 0; i < size; i++) {

			integerArray[i] = jsonArray.getInt(i);

		}

		return integerArray;

	}

	/**
	 * 从json数组中解析出java Double型对象数组
	 *
	 * @param jsonString json数组
	 * @return Double数组
	 */
	public static Double[] jsonToDoubleArray(String jsonString) {
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		int size = jsonArray.size();
		Double[] doubleArray = new Double[size];
		for (int i = 0; i < size; i++) {
			doubleArray[i] = jsonArray.getDouble(i);
		}
		return doubleArray;
	}
	
	/**
	 * 
	 * @author 周业好
	 * @param object 权限对象
	 * @param gl 不需要转为json的字段
	 * @return json
	 */
	public static String sendObject(Object object, String[] gl) {
        JsonConfig jsonConfig = new JsonConfig(); // 建立配置文件
        jsonConfig.setExcludes(gl); // 只要将所需忽略字段加到数组中即可
        jsonConfig.setIgnoreDefaultExcludes(false); // 设置默认忽略
        jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
        JSONObject json = JSONObject.fromObject(object, jsonConfig);
        return json.toString();
    }
}
