package com.haoxw.terminal.business.model;


/**
 * 角色 功能中间表
 * 
 * @author haoxw
 * 
 */
@SuppressWarnings("serial")
public class AiRoleFunc implements java.io.Serializable {
	private int id;
	private int rid;
	private int fid;
	private long uptime;
	@Override
	public String toString() {
		return "RoleFunc [id=" + id + ", rid=" + rid + ", fid=" + fid
				+ ", uptime=" + uptime + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public long getUptime() {
		return uptime;
	}
	public void setUptime(long uptime) {
		this.uptime = uptime;
	}
	

}