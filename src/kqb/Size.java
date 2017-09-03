package kqb;

import java.util.Date;

/**
 * 按件数的规格
 * @author Administrator
 *
 */
public class Size {
	private String uuid;
	private String name;
	private Integer sizeid;
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
	public Integer getSizeid() {
		return sizeid;
	}
	public void setSizeid(Integer sizeid) {
		this.sizeid = sizeid;
	}
	public Integer getScz() {
		return scz;
	}
	public void setScz(Integer scz) {
		this.scz = scz;
	}
	
}
