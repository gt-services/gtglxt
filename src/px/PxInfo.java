package px;

import java.io.Serializable;
import java.util.Date;
/**
 * 培训信息
 * @author Administrator
 */
public class PxInfo implements Serializable{
	private static final long serialVersionUID = 1L;

	public PxInfo(){}
	
	private String uuid;
	private String signupDate;
	private String name;
	private String sex;
	private String idCard;
	private String levelEdu;
	private String unitOrPerson;
	private String telephone;
	private String contactOfUnit;
	private String unitPhone;
	private String trainPlace;
	private String trainType;
	private String test;
	private String standardAmount;
	private String discountAmount;
	private String payCost;//缴费情况
	private String payNumber;//收据号
	private Date dueToDate;//到期日期
	private Date testDate;//考试日期
	private String licenseStatus;//领证情况
	/*
	补考模块新增字段
	 */
	private Date CertificateUsedDate;//生效日期
	private String CertificateNum;//证书编号
	private String studentId;//学员编号
	private String FirstRetestFee;//第一次补考费用
	private String SecondRetestFee;
	private String ThirdRetestFee;
	private String FirstRetesPayNumber;//第一次补考收据号
	private String SecondRetestPayNumber;
	private String ThirdRetesPayNumber;
//	private int theoryResults;//理论成绩
//	private int actualResults; //实际操作成绩
//	private int FirstRetesttheoryResults;//第一次补考理论成绩
//	private int SecondRetesttheoryResults;
//	private int ThirdRetesttheoryResults;
//	private int FirstRetestactualResults;//第一次补考实操成绩
//	private int SecondRetestactualResults;
//	private int ThirdRetestactualResults;


	/**
	 *
	 * 培训新增考试状态 0未考;1通过；2未通过
	 */

	private Integer status =0;


	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getSignupDate() {
		return signupDate;
	}

	public void setSignupDate(String signupDate) {
		this.signupDate = signupDate;
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

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getLevelEdu() {
		return levelEdu;
	}

	public void setLevelEdu(String levelEdu) {
		this.levelEdu = levelEdu;
	}

	public String getUnitOrPerson() {
		return unitOrPerson;
	}

	public void setUnitOrPerson(String unitOrPerson) {
		this.unitOrPerson = unitOrPerson;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getContactOfUnit() {
		return contactOfUnit;
	}

	public void setContactOfUnit(String contactOfUnit) {
		this.contactOfUnit = contactOfUnit;
	}

	public String getUnitPhone() {
		return unitPhone;
	}

	public void setUnitPhone(String unitPhone) {
		this.unitPhone = unitPhone;
	}

	public String getTrainPlace() {
		return trainPlace;
	}

	public void setTrainPlace(String trainPlace) {
		this.trainPlace = trainPlace;
	}

	public String getTrainType() {
		return trainType;
	}

	public void setTrainType(String trainType) {
		this.trainType = trainType;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public String getStandardAmount() {
		return standardAmount;
	}

	public void setStandardAmount(String standardAmount) {
		this.standardAmount = standardAmount;
	}

	public String getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(String discountAmount) {
		this.discountAmount = discountAmount;
	}

	public String getPayCost() {
		return payCost;
	}

	public void setPayCost(String payCost) {
		this.payCost = payCost;
	}

	public String getPayNumber() {
		return payNumber;
	}

	public void setPayNumber(String payNumber) {
		this.payNumber = payNumber;
	}

	public Date getDueToDate() {
		return dueToDate;
	}

	public void setDueToDate(Date dueToDate) {
		this.dueToDate = dueToDate;
	}

	public Date getTestDate() {
		return testDate;
	}

	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}

	public String getLicenseStatus() {
		return licenseStatus;
	}

	public void setLicenseStatus(String licenseStatus) {
		this.licenseStatus = licenseStatus;
	}

	public Date getCertificateUsedDate() {
		return CertificateUsedDate;
	}

	public void setCertificateUsedDate(Date certificateUsedDate) {
		CertificateUsedDate = certificateUsedDate;
	}

	public String getCertificateNum() {
		return CertificateNum;
	}

	public void setCertificateNum(String certificateNum) {
		CertificateNum = certificateNum;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getFirstRetestFee() {
		return FirstRetestFee;
	}

	public void setFirstRetestFee(String firstRetestFee) {
		FirstRetestFee = firstRetestFee;
	}

	public String getSecondRetestFee() {
		return SecondRetestFee;
	}

	public void setSecondRetestFee(String secondRetestFee) {
		SecondRetestFee = secondRetestFee;
	}

	public String getThirdRetestFee() {
		return ThirdRetestFee;
	}

	public void setThirdRetestFee(String thirdRetestFee) {
		ThirdRetestFee = thirdRetestFee;
	}

	public String getFirstRetesPayNumber() {
		return FirstRetesPayNumber;
	}

	public void setFirstRetesPayNumber(String firstRetesPayNumber) {
		FirstRetesPayNumber = firstRetesPayNumber;
	}

	public String getSecondRetestPayNumber() {
		return SecondRetestPayNumber;
	}

	public void setSecondRetestPayNumber(String secondRetestPayNumber) {
		SecondRetestPayNumber = secondRetestPayNumber;
	}

	public String getThirdRetesPayNumber() {
		return ThirdRetesPayNumber;
	}

	public void setThirdRetesPayNumber(String thirdRetesPayNumber) {
		ThirdRetesPayNumber = thirdRetesPayNumber;
	}

}
