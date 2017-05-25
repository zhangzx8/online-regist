package org.tempuri.test;

import org.tempuri.login.SSOUserLogin;
import org.tempuri.login.SSOUserLoginSoap;

public class LoginTest {
	public static void main(String[] args) {
		SSOUserLogin ssoLogin = new SSOUserLogin();
		SSOUserLoginSoap spt = ssoLogin.getSSOUserLoginSoap();
		String res = spt.ssoLogin("757FFC1D948D3E4DBAFB560E0AA7D013");
		if (res.isEmpty()) {
			System.out.println("失败");
		} else {
			System.out.println("成功");
		}

	}
}
