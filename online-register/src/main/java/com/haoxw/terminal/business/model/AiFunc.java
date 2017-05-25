package com.haoxw.terminal.business.model;

/**
 * 功能实体类
 * 
 * @author haoxw
 * 
 */
@SuppressWarnings("serial")
public class AiFunc implements java.io.Serializable {

	// Fields
	private int id;
	private String title;
	private String url;
	private int state;
	private long uptime;
	/**
	 * 父节点id,默认为0没有父节点
	 */
	private int parentid;

	/**
	 * 排名
	 */
	private int rank;

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public long getUptime() {
		return uptime;
	}

	public void setUptime(long uptime) {
		this.uptime = uptime;
	}

	public int getParentid() {
		return parentid;
	}

	public void setParentid(int parentid) {
		this.parentid = parentid;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		return "AiFunc [id=" + id + ", title=" + title + ", url=" + url
				+ ", state=" + state + ", uptime=" + uptime + ", parentid="
				+ parentid + "]";
	}

}