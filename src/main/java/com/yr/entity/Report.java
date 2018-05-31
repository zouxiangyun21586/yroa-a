package com.yr.entity;

/**
 * 当天考勤报告实体
 * @author 林水桥
 * 2018年5月30日下午9:44:51
 */
public class Report {
	/**考勤描述*/
	private String name;
	/**考勤时间状态*/
	private String checkStatus;
	/**考勤状态*/
	private String status;
	/**考勤状态描述*/
	private String statusDesc;
	
	public String getName()	{
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public String getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}
	
}
