package com.yr.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 届次实体类
 * @author Administrator
 *
 * 2018年5月22日 上午8:58:28
 *
 */
@Entity
@Table(name = "yr_clas")
public class Clas {
    private Integer id;

    private String year;

    private String name;

    private String code;

    private String teacherCode;
    
    private String teacherName;

    private String startTime;

    private Date createTime;

    private String isFinish;

    private String finishTime;

    @GeneratedValue
    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    @Column(name = "teacher_code")
    public String getTeacherCode() {
		return teacherCode;
	}

	public void setTeacherCode(String teacherCode) {
		this.teacherCode = teacherCode;
	}

	@Column(name = "teacher_name")
	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "start_time")
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "is_finish")
    public String getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(String isFinish) {
        this.isFinish = isFinish == null ? null : isFinish.trim();
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "finish_time")
    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

	@Override
	public String toString() {
		return "Clas [id=" + id + ", year=" + year 
				+ ", name=" + name + ", code=" + code 
				+ ", teacherCode="
				+ teacherCode + ", teacherName=" + teacherName 
				+ ", startTime=" + startTime + ", createTime="
				+ createTime + ", isFinish=" + isFinish + ", finishTime=" + finishTime + "]";
	}
}
