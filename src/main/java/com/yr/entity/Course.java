package com.yr.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 课程表
 * 
 * @author zxy
 *
 *  2018年5月30日 上午10:41:01
 *
 */
@Entity
@Table(name = "yr_course")
public class Course {
	private Integer id;
	private String name;
	private String code;
	private String level;
	private String content;
	private Date creatTime;
	private boolean checked;
	//子级数据
    private String[] data;

	@GeneratedValue
	@Id
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "create_time")
	public Date getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	@Transient
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
	@Transient
	public String[] getData() {
		return data;
	}
	public void setData(String[] data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", code=" 
				+ code + ", level=" + level + ", content=" + content
				+ ", creatTime=" + creatTime + "]";
	}
	
}
