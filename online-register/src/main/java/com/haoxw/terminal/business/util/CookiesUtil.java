package com.haoxw.terminal.business.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * cookies操作
 * @author xuewuhao
 *
 */
public class CookiesUtil {
	private static CookiesUtil instance = null;

	private CookiesUtil() {

	}

	public static CookiesUtil getInstance() {
		if (instance == null)
			instance = new CookiesUtil();
		return instance;
	}

	private final static Logger logger = LoggerFactory
			.getLogger(CookiesUtil.class);

	/**
	 * 获取当前用户cookies值
	 * 
	 * @param cookies
	 * @return
	 */
	public  String getCookieValue(String name, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		String result = null;
		if (cookies != null && cookies.length > 0)
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					try {
						result = cookie.getValue();
					} catch (Exception e) {
						logger.error("", e);
					}

					break;
				}
			}
		return result;
	}
	
	/**
	 * 添加登录用户email作为cookies
	 * 
	 * @param email
	 * @param response
	 * @return
	 */
	public Cookie addCookie(String name, String value, HttpServletResponse response) {
		Cookie cookie = null;
		try {
			cookie = new Cookie(name, value);
			cookie.setPath("/");
			//cookie.setMaxAge(30 * 60);// cookie30分钟天
			response.addCookie(cookie);
			logger.info("name:"+name+"|value:"+value);
		} catch (Exception e) {
			logger.error("", e);
		}
		return cookie;
	}

	/**
	 * 退出 移除cookies
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public boolean removeCookie(HttpServletRequest request,
			HttpServletResponse response) {
		boolean b = false;
		Cookie[] cookies = request.getCookies();
		// cookies不为空，则清除
		if (cookies != null) {
			try {
				for (int i = 0; i < cookies.length; i++) {
					Cookie cookie = new Cookie(cookies[i].getName(), null);
					cookie.setMaxAge(0);
					cookie.setPath("/");// 根据你创建cookie的路径进行填写
					response.addCookie(cookie);
				}
			} catch (Exception e) {
				logger.error("removeCookie", e);
			}
			b = true;
		}
		return b;
	}
}
