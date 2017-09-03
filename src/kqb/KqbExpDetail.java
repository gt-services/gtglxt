package kqb;

import java.util.Date;

public class KqbExpDetail {
	private String name;
	private String sczname;
	private String jobname;
	private Integer hour;
	private String sizename;
	private Integer number;
	private Integer month;
	private Integer day;
	private String createDate;
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJobname() {
		return jobname;
	}
	public void setJobname(String jobname) {
		this.jobname = jobname;
	}
	public Integer getHour() {
		return hour;
	}
	public void setHour(Integer hour) {
		this.hour = hour;
	}
	public String getSizename() {
		return sizename;
	}
	public void setSizename(String sizename) {
		this.sizename = sizename;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}


	public String getSczname() {
		return sczname;
	}

	public void setSczname(String sczname) {
		this.sczname = sczname;
	}
}
