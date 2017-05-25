package com.haoxw.terminal.business.model;

import java.util.List;

import com.haoxw.db.oracle.base.PageModel;

public class PageGrid implements java.io.Serializable{

	private static final long serialVersionUID = 2221861722220365843L;
	
	/**
	 * 分页数据
	 */
	private PageModel page;
	/**
	 * 表格标题
	 */
	private List<String> headers;

	/**
	 * 月份
	 */
	private String month;

	public PageModel getPage() {
		return page;
	}

	@Override
	public String toString() {
		return "PageGrid [page=" + page + ", headers=" + headers + ", month="
				+ month + "]";
	}

	public void setPage(PageModel page) {
		this.page = page;
	}

	public List<String> getHeaders() {
		return headers;
	}

	public void setHeaders(List<String> headers) {
		this.headers = headers;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
	
	

}
