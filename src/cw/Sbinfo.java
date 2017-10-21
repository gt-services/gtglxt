package cw;

import java.util.Date;

/**
 *完整社保信息
 * @author Administrator
 *
 */
public class Sbinfo {
	private	Long uuid;
	private String name;
	private String scz;//生产组
	private String identityId;//身份证号
	private String sbinfo;//社保信息
	private Date createDate;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getScz() {
		return scz;
	}
	public void setScz(String scz) {
		this.scz = scz;
	}
	public String getIdentityId() {
		return identityId;
	}
	public void setIdentityId(String identityId) {
		this.identityId = identityId;
	}
	public String getSbinfo() {
		return sbinfo;
	}
	public void setSbinfo(String sbinfo) {
		this.sbinfo = sbinfo;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Long getUuid() {
		return uuid;
	}
	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}

	
}
