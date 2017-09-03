package kqb;

import java.util.Date;

/**
 * test
 * @author Administrator
 *
 */
public class Todaykq {

	private Integer id;
	private String userid;//工人的id编号
	private String sczname;
	private Integer jobid;//JOB的编号
	private Integer hours;//job工作时长
	
	private Integer sizeid;//按件数 产品规格编号
	private Integer number;//数量
	private Integer month;
	private Integer day;
	private String createDate;//人员考勤添加日期

	public Todaykq() {
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public Integer getJobid() {
		return jobid;
	}
	public void setJobid(Integer jobid) {
		this.jobid = jobid;
	}
	public Integer getHours() {
		return hours;
	}
	public void setHours(Integer hours) {
		this.hours = hours;
	}
	public Integer getSizeid() {
		return sizeid;
	}
	public void setSizeid(Integer sizeid) {
		this.sizeid = sizeid;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public Integer getDay() {
		return day;
	}
	public void setDay(Integer day) {
		this.day = day;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}


	public String getSczname() {
		return sczname;
	}

	public void setSczname(String sczname) {
		this.sczname = sczname;
	}
}
