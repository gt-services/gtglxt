package kqb;

import java.util.Date;

/**
 * 工作种类
 * @author Administrator
 *
 */
public class Job {
	private String uuid;
	private String name;
	private Integer secondId;
	private String beizhu;
	private Integer scz;
	private Date createDate;
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getSecondId() {
		return secondId;
	}
	public void setSecondId(Integer secondId) {
		this.secondId = secondId;
	}
	public String getBeizhu() {
		return beizhu;
	}
	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getScz() {
		return scz;
	}
	public void setScz(Integer scz) {
		this.scz = scz;
	}
	
}
