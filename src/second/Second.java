package second;

import java.util.Date;

/**
 * 生产部二级权限
 * @author Administrator
 *
 */
public class Second {
	private String uuid;
	private String name;
	private Integer secondId;
	private String beizhu;
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
	
}
