package kqb;

import java.util.Date;

/**
 * 
 * @author Administrator
 *
 */
public class Kqbinfo {
	private String uuid;
	private String name;
	private String scz;//生产组名称
	private String sczid;//生产组编号
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	private String gw;
	private String todaykq;
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
	public String getGw() {
		return gw;
	}
	public void setGw(String gw) {
		this.gw = gw;
	}
	public String getTodaykq() {
		return todaykq;
	}
	public void setTodaykq(String todaykq) {
		this.todaykq = todaykq;
	}
	public String getSczid() {
		return sczid;
	}
	public void setSczid(String sczid) {
		this.sczid = sczid;
	}
	
	
	
}
