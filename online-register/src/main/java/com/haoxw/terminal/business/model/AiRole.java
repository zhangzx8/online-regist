package com.haoxw.terminal.business.model;

/**
 * 角色实体类.
 * 
 * @author haoxw
 * 
 */
@SuppressWarnings("serial")
public class AiRole implements java.io.Serializable {

	// Fields

	private int id;
	private String title;
	private String descp;
	private long uptime;
	private int state;
	private String updateTime;// 前台展示用

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescp() {
		return descp;
	}

	public void setDescp(String descp) {
		this.descp = descp;
	}

	public Long getUptime() {
		return uptime;
	}

	public void setUptime(long uptime) {
		this.uptime = uptime;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", title=" + title + ", descp=" + descp
				+ ", uptime=" + uptime + ", state=" + state + "]";
	}
}