package com.haoxw.terminal.business.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.tempuri.login.SSOUserLogin;
import org.tempuri.login.SSOUserLoginSoap;
import org.tempuri.user.ADXMLService;
import org.tempuri.user.ADXMLServiceSoap;

/**
 * oa接口实现
 * 
 * @author haoxw
 * 
 */
@Service
public class OaService {

	/**
	 * 增量方式同步 start到end的用户信息
	 * 
	 * @param start
	 *            yyyy-mm-dd
	 * @param end
	 *            yyyy-mm-dd
	 * @return
	 */
	public String getUesr(String start, String end) {
		ADXMLService aDXMLService = new ADXMLService();
		ADXMLServiceSoap soap = aDXMLService.getADXMLServiceSoap();
		return soap.getADXmlDoc(1, start, end);
	}

	/**
	 * 某用户单点登录
	 * 
	 * @param guid
	 * @return
	 */
	public String login(String guid) {
		SSOUserLogin ssoLogin = new SSOUserLogin();
		SSOUserLoginSoap spt = ssoLogin.getSSOUserLoginSoap();
		String res = spt.ssoLogin(guid);
		System.out.println("webservice response:::::::"+res);
		
		return res;
//		if (StringUtils.isBlank(res)) {
//			//登录失败
//			return "";
//		} else {
//			//登录成功
//			return res;
//		}
	}
}
