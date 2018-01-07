package roster;

import java.io.Serializable;
import java.util.Date;

/**
 * 花名册
 * @author Administrator
 */
public class SocialSecurity implements Serializable{
	private static final long serialVersionUID = 1L;

	
	//社保
	private String name;//姓名
	private String sex;//性别
	private String age;//年龄
	private String identityId;//身份证
	private String sbNumber;//社保编号
	private String address;//地址
	private String phone;//电话
	private Date htStart;//合同开始日期
	private Date htEnd;//合同结束日期
	private Date payEnd;//缴费截止日期
	private String introducePrs;//联系人
	private String introducePrsTel;//联系人电话号码
	private String payType;//缴费类型
	private String gjjNumber;//公积金账号
	private Date sbEnd;//社保结束日期
	private String hujiType;//户籍类型
	private String beizhu;//备注
	private Date createDate;//更新日期

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getIdentityId() {
		return identityId;
	}

	public void setIdentityId(String identityId) {
		this.identityId = identityId;
	}

	public String getSbNumber() {
		return sbNumber;
	}

	public void setSbNumber(String sbNumber) {
		this.sbNumber = sbNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getHtStart() {
		return htStart;
	}

	public void setHtStart(Date htStart) {
		this.htStart = htStart;
	}

	public Date getHtEnd() {
		return htEnd;
	}

	public void setHtEnd(Date htEnd) {
		this.htEnd = htEnd;
	}

	public Date getPayEnd() {
		return payEnd;
	}

	public void setPayEnd(Date payEnd) {
		this.payEnd = payEnd;
	}

	public String getIntroducePrs() {
		return introducePrs;
	}

	public void setIntroducePrs(String introducePrs) {
		this.introducePrs = introducePrs;
	}

	public String getIntroducePrsTel() {
		return introducePrsTel;
	}

	public void setIntroducePrsTel(String introducePrsTel) {
		this.introducePrsTel = introducePrsTel;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getGjjNumber() {
		return gjjNumber;
	}

	public void setGjjNumber(String gjjNumber) {
		this.gjjNumber = gjjNumber;
	}

	public Date getSbEnd() {
		return sbEnd;
	}

	public void setSbEnd(Date sbEnd) {
		this.sbEnd = sbEnd;
	}

	public String getHujiType() {
		return hujiType;
	}

	public void setHujiType(String hujiType) {
		this.hujiType = hujiType;
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
