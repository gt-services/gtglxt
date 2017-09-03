package roster;

import java.io.Serializable;
import java.util.Date;

/**
 * 花名册
 * @author Administrator
 */
public class Roster implements Serializable{
	private static final long serialVersionUID = 1L;

	public Roster(){}
	
	//社保
	private String uuid;//人员编号
	private String htNumber;//合同编号
	private String name;//姓名
	private String sex;//性别
	private String identityId;//身份证号
	private String bankCard;//银行卡号
	private String sbNumber;//社保编号
	private String address;//地址
	private Date htStart;//合同开始日期
	private Date htEnd;//合同结束日期
	private String jobType;//工种
	private String phone;//电话号码
	private Date lcTime;//离场日期
	private Date sbStart;//社保开始日期
	private Date sbEnd;//社保结束日期
	private int sbRemind;//用于社保预警
	private String bxType;//保险类型
	private String del;//在职1 /离职0
	private String gsqk;//工伤情况
	private Date gsStart;//工伤开始时间	
	private Date gsEnd;	//工伤结束时间
	private String source;//人事来源
	private String scz;//生产组
	private String addinkq="未添加";//否加入考勤
	private Date createDate;//创建日期
	private String beizhu;//备注
	private String image;//图片
	private String deal = String.valueOf(0);//离职人员已处理1 /未处理0
	
	//专做导入时的时间区别
	private Date createDate2;
	

	public Date getCreateDate2() {
		return createDate2;
	}

	public void setCreateDate2(Date createDate2) {
		this.createDate2 = createDate2;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getHtNumber() {
		return htNumber;
	}

	public void setHtNumber(String htNumber) {
		this.htNumber = htNumber;
	}

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

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getLcTime() {
		return lcTime;
	}

	public void setLcTime(Date lcTime) {
		this.lcTime = lcTime;
	}

	public Date getSbStart() {
		return sbStart;
	}

	public void setSbStart(Date sbStart) {
		this.sbStart = sbStart;
	}

	public Date getSbEnd() {
		return sbEnd;
	}

	public void setSbEnd(Date sbEnd) {
		this.sbEnd = sbEnd;
	}

	public String getBxType() {
		return bxType;
	}

	public void setBxType(String bxType) {
		this.bxType = bxType;
	}

	public String getBeizhu() {
		return beizhu;
	}

	public String getDel() {
		return del;
	}

	public void setDel(String del) {
		this.del = del;
	}

	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}

	public String getGsqk() {
		return gsqk;
	}

	public void setGsqk(String gsqk) {
		this.gsqk = gsqk;
	}

	public Date getGsStart() {
		return gsStart;
	}

	public void setGsStart(Date gsStart) {
		this.gsStart = gsStart;
	}

	public Date getGsEnd() {
		return gsEnd;
	}

	public void setGsEnd(Date gsEnd) {
		this.gsEnd = gsEnd;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getSbRemind() {
		return sbRemind;
	}

	public void setSbRemind(int sbRemind) {
		this.sbRemind = sbRemind;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getScz() {
		return scz;
	}

	public void setScz(String scz) {
		this.scz = scz;
	}

	public String getAddinkq() {
		return addinkq;
	}

	public void setAddinkq(String addinkq) {
		this.addinkq = addinkq;
	}

	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}


	public void setCreateDate(String format) {
	}

	public String getDeal() {
		return deal;
	}

	public void setDeal(String deal) {
		this.deal = deal;
	}
}
