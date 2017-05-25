package com.haoxw.terminal.business.model;

/**
 * 用户实体
 * 
 * @author haoxw
 * 
 */
@SuppressWarnings("serial")
public class AiUser implements java.io.Serializable {

	private String guid;
	private String username;
	private String name;
	private String department;
	private String mobile;
	private String mail;
	private String userlevel;
	private int order_;
	private String cardid;
	private long uptime;
	private String groupprovince;
	private int state;
	private int roleid;

	@Override
	public String toString() {
		return "User [guid=" + guid + ", username=" + username + ", name="
				+ name + ", department=" + department + ", mobile=" + mobile
				+ ", mail=" + mail + ", userlevel=" + userlevel + ", order_="
				+ order_ + ", cardid=" + cardid + ", uptime=" + uptime
				+ ", groupprovince=" + groupprovince + ", state=" + state + "]";
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getUserlevel() {
		return userlevel;
	}

	public void setUserlevel(String userlevel) {
		this.userlevel = userlevel;
	}

	public int getOrder_() {
		return order_;
	}

	public void setOrder_(int order_) {
		this.order_ = order_;
	}

	public String getCardid() {
		return cardid;
	}

	public void setCardid(String cardid) {
		this.cardid = cardid;
	}

	public long getUptime() {
		return uptime;
	}

	public void setUptime(long uptime) {
		this.uptime = uptime;
	}

	public String getGroupprovince() {
		return groupprovince;
	}

	public void setGroupprovince(String groupprovince) {
		this.groupprovince = groupprovince;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getRoleid() {
		return roleid;
	}

	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}

}