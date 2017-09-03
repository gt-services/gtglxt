package admin;

import second.SecondService;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * 管理员信息
 * @author Administrator
 */
public class Admin implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public Admin(){}
	
	private String uuid;//id
	private String username;//用户名
	private String password;//密码
	private String realname;//真实姓名
	private String phone;//电话
	private String email;//电子邮件
	private String beizhu;//备注
	/**
	 * 权限：1. 超级管理员 ,2.人事，3.财务，4培训，5生产。
	 */
	private String authority;//操作权限代号
	private Integer second;//二级生产权限
	private String sczname;//二级生产权限
	private Date createDate;//创建日期
	private String status="0";//生产部门延时上报权限
	//加载一个静态的生产组id 名称的map
	public static Map<String,String> MAP = SecondService.getSecondMap();
	
	//根据用户authority的值进行优化角色的识别
	public String getAuthorityName() {
		String sczname = MAP.get(second+"");
		if(authority.equals("1")){
			return "超级管理员";
		}else if (authority.equals("2")) {
			return "人事管理员";
		} else if (authority.equals("3")) {
			return "财务管理员";
		} else if(authority.equals("4")){
			return "培训管理员";
		}else{
			return sczname;
		}
	}
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public Integer getSecond() {
		return second;
	}

	public void setSecond(Integer second) {
		this.second = second;
	}

	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getBeizhu() {
		return beizhu;
	}

	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}



	public String getSczname() {
		return sczname;
	}

	public void setSczname(String sczname) {
		this.sczname = sczname;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
