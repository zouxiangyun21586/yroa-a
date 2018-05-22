package com.yr.entity;

import java.util.Date;

/**
 * 参考数据库(审计)
 * 
 * @author Administrator
 *
 * 2018年5月22日 上午8:55:30
 *
 */
public class Audit {
    private Integer id;

    private Integer leaveId;

    private String auditAccount;

    private String isAudit;

    private String auditDesc;

    private String retyAuditAccount;

    private Date auditTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(Integer leaveId) {
        this.leaveId = leaveId;
    }

    public String getAuditAccount() {
        return auditAccount;
    }

    public void setAuditAccount(String auditAccount) {
        this.auditAccount = auditAccount == null ? null : auditAccount.trim();
    }

    public String getIsAudit() {
        return isAudit;
    }

    public void setIsAudit(String isAudit) {
        this.isAudit = isAudit == null ? null : isAudit.trim();
    }

    public String getAuditDesc() {
        return auditDesc;
    }

    public void setAuditDesc(String auditDesc) {
        this.auditDesc = auditDesc == null ? null : auditDesc.trim();
    }

    public String getRetyAuditAccount() {
        return retyAuditAccount;
    }

    public void setRetyAuditAccount(String retyAuditAccount) {
        this.retyAuditAccount = retyAuditAccount == null ? null : retyAuditAccount.trim();
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }
}
