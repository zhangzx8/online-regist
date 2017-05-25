package com.haoxw.terminal.business.model;

/**
 * 用户角色中间表
 * 
 * @author haoxw
 * 
 */
@SuppressWarnings("serial")
public class AiUserRole implements java.io.Serializable {
	private int id;
	private String roleuid;
	private int rid;
	private long uptime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRoleuid() {
		return roleuid;
	}
	public void setRoleuid(String roleuid) {
		this.roleuid = roleuid;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public long getUptime() {
		return uptime;
	}
	public void setUptime(long uptime) {
		this.uptime = uptime;
	}
	@Override
	public String toString() {
		return "UserRole [id=" + id + ", roleuid=" + roleuid + ", rid=" + rid
				+ ", uptime=" + uptime + "]";
	}
	
	
}