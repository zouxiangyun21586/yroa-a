package com.yr.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 请假实体类
 * @author zxy
 *
 * 2018年5月22日 上午9:04:35
 *
 */
@Entity
@Table(name = "yr_leave")
public class Leave {
    private Integer id;

    private String classCode;

    private String className;

    private String studentCode;

    private String studentName;

    private String leaveDate;

    private String leaveHour;

    private Integer leaveTimeLong;

    private String leaveDesc;

    private String leaveAccount;

    private String isAudit;

    private String leaveType;

    private Date createTime;
    
    private String imgUrl;
    
    private String auditTime;
    

    @GeneratedValue
    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "class_code")
    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode == null ? null : classCode.trim();
    }

    @Column(name = "class_name")
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    @Column(name = "student_code")
    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode == null ? null : studentCode.trim();
    }

    @Column(name = "student_name")
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName == null ? null : studentName.trim();
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "leave_date")
    public String getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(String leaveDate) {
        this.leaveDate = leaveDate;
    }

    @Column(name = "leave_hour")
    public String getLeaveHour() {
        return leaveHour;
    }

    public void setLeaveHour(String leaveHour) {
        this.leaveHour = leaveHour == null ? null : leaveHour.trim();
    }

    @Column(name = "leave_time_long")
    public Integer getLeaveTimeLong() {
        return leaveTimeLong;
    }

    public void setLeaveTimeLong(Integer leaveTimeLong) {
        this.leaveTimeLong = leaveTimeLong;
    }

    @Column(name = "leave_desc")
    public String getLeaveDesc() {
        return leaveDesc;
    }

    public void setLeaveDesc(String leaveDesc) {
        this.leaveDesc = leaveDesc == null ? null : leaveDesc.trim();
    }

    @Column(name = "leave_account")
    public String getLeaveAccount() {
        return leaveAccount;
    }

    public void setLeaveAccount(String leaveAccount) {
        this.leaveAccount = leaveAccount == null ? null : leaveAccount.trim();
    }

    @Column(name = "is_audit")
    public String getIsAudit() {
        return isAudit;
    }

    public void setIsAudit(String isAudit) {
        this.isAudit = isAudit == null ? null : isAudit.trim();
    }

    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "leave_type")
	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	@Column(name = "img_url")
	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	@Column(name = "audit_time")
	public String getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}
    
}
