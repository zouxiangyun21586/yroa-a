package com.yr.util;

import java.util.Date;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonBeanProcessor;

/**
 * json  Date时间转换
 * @作者 林水桥
 * 2018年5月24日下午8:43:09
 */
public class DateJsonValueProcessor implements JsonBeanProcessor {

	/**
	 * jsonDate时间转换
	 * @param bean 对象
	 * @param arg1 json
	 * @return 返回json
	 */
	public JSONObject processBean(Object bean, JsonConfig arg1) {
		JSONObject jsonObject = null;
		if (bean instanceof java.sql.Date) {
			bean = new Date(((java.sql.Date) bean).getTime());
		}
		if (bean instanceof java.sql.Timestamp) {
			bean = new Date(((java.sql.Timestamp) bean).getTime());
		}
		if (bean instanceof Date) {
			jsonObject = new JSONObject();
			jsonObject.element("time", ((Date) bean).getTime());
		} else {
			jsonObject = new JSONObject(true);
		}
		return jsonObject;
	}
}
