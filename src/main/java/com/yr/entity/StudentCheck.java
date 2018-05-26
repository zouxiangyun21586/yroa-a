package com.yr.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 学生考勤表
 * @author 林水桥
 * 2018年5月25日下午9:33:21
 */
@Entity
@Table(name = "yr_student_check")
public class StudentCheck {
	/**学生考勤表ID*/
    private Integer id;
    /**届次编码*/
    private String classCode;
	/**届次名称*/
    private String className;
    /**学生编码*/
    private String studentCode;
    /**学生姓名*/
    private String studentName;
    /**考勤时间编码*/
    private String checkTimeCode;
    /**考勤时间段名称,如：上午、下午、晚上*/
    private String checkTimeDesc;
    /**考勤日期*/
    private Date checkTime;
    /**标准上课时间*/
    private String startTime;
    /**实际到达时间*/
    private Date retyTime;
    /**迟到时间(分钟)*/
    private Integer lateTime;
    /**考勤状态(0没迟到,1迟到,2旷课,3请假,4早退)*/
    private Integer status;
    /**是否有请假条(1有假条,0没有假条属无故旷课或早退)*/
    private Integer isNote;
    /**创建时间*/
    private Date createTime;
    /**修改时间*/
    private Date updateTime;
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Column(name = "class_code", length = 10)
    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode == null ? null : classCode.trim();
    }
    @Column(name = "class_name", length = 20)
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }
    @Column(name = "student_code", length = 10)
    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode == null ? null : studentCode.trim();
    }
    @Column(name = "student_name", length = 20)
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName == null ? null : studentName.trim();
    }
    @Column(name = "check_time_code", length = 10)
    public String getCheckTimeCode() {
        return checkTimeCode;
    }

    public void setCheckTimeCode(String checkTimeCode) {
        this.checkTimeCode = checkTimeCode == null ? null : checkTimeCode.trim();
    }
    @Column(name = "check_time_name", length = 20)
    public String getCheckTimeDesc() {
        return checkTimeDesc;
    }

    public void setCheckTimeDesc(String checkTimeDesc) {
        this.checkTimeDesc = checkTimeDesc == null ? null : checkTimeDesc.trim();
    }
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "check_date")
    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }
    @Column(name = "start_time", length = 20)
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime == null ? null : startTime.trim();
    }
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "rety_time")
    public Date getRetyTime() {
        return retyTime;
    }

    public void setRetyTime(Date retyTime) {
        this.retyTime = retyTime;
    }
    @Column(name = "late_time")
    public Integer getLateTime() {
        return lateTime;
    }

    public void setLateTime(Integer lateTime) {
        this.lateTime = lateTime;
    }
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    @Column(name = "is_note")
    public Integer getIsNote() {
        return isNote;
    }

    public void setIsNote(Integer isNote) {
        this.isNote = isNote;
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
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
