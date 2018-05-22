package com.yr.entity;

import java.util.Date;

/**
 * 假期实体类
 * @作者 林水桥
 * 2018年5月22日下午8:55:43
 */
public class Holiday {
	/**假期ID*/
	private Integer id;
	/**假期名称*/
	private String name;
	/**放假开始日期*/
	private Date startDate;
	/**放假结束日期*/
	private Date endDate;
	/**放假开始时间*/
	private String startTime;
	/**放假结束时间*/
	private String endTime;
	/**备注（注意事项）*/
	private String info;
	/**创建时间*/
	private Date createTime;
	/**所属批次编码。绑定届次，空表示所有的届次都施行*/
	private String classCode;
	/**批次名称*/
	private String className;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName()	{
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getClassCode() {
		return classCode;
	}
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
}
