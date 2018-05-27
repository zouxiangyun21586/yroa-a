package com.yr.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 假期实体类
 * @作者 林水桥
 * 2018年5月22日下午8:55:43
 */
@Entity
@Table(name = "yr_holiday")
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
	/**发布状态0未发布，1已发布*/
	private Integer status;
	/**发布状态名称,不映射到数据库*/
	private String statusName;
	/**发布时间*/
	private Date releaseTime;
	/**创建时间*/
	private Date createTime;
	/**修改时间*/
	private Date updateTime;
	/**所属批次编码。绑定届次，空表示所有的届次都施行*/
	private String classCode;
	/**批次名称*/
	private String className;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(length = 20)
	public String getName()	{
		return name;
	}
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "start_date", nullable = false, length = 10)
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "end_date", nullable = false, length = 10)
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	@Column(name = "start_time", length = 20)
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime == null ? null : startTime.trim();
	}
	@Column(name = "end_time", length = 20)
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime == null ? null : endTime.trim();
	}
	@Column(length = 255)
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info == null ? null : info.trim();
	}
	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Transient
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "release_time")
	public Date getReleaseTime() {
		return releaseTime;
	}
	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "create_time")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "update_time")
	public Date getUpdateTime()	{
		return updateTime;
	}
	public void setUpdateTime(Date updateTime)	{
		this.updateTime = updateTime;
	}
	@Column(name = "class_code")
	public String getClassCode() {
		return classCode;
	}
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	@Column(name = "class_name")
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
}
