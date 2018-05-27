package com.yr.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 考勤时间
 * 
 * @author Administrator
 *
 * 2018年5月22日 上午8:57:46
 *
 */
@Entity
@Table(name = "yr_check_time")
public class CheckTime {
	/**考勤时间ID*/
    private Integer id;
    /**考勤时间段，上午，下午，晚上*/
    private String timeName;
    /**编码。AM，PM，NT*/
    private String code;
    /**标准上课时间*/
    private String startTime;
    /**标准下课时间*/
    private String endTime;
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Column(name = "time_name", length = 20)
    public String getTimeName() {
        return timeName;
    }

    public void setTimeName(String timeName) {
        this.timeName = timeName == null ? null : timeName.trim();
    }
    @Column(name = "code", length = 10)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
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
}
