package org.tempuri.test;

import org.tempuri.user.ADXMLService;
import org.tempuri.user.ADXMLServiceSoap;

public class UserTest {
	public static void main(String[] args) {
		ADXMLService aDXMLService = new ADXMLService();
		ADXMLServiceSoap soap = aDXMLService.getADXMLServiceSoap();
		String res = soap.getADXmlDoc(1, "2014-03-10", "2015-03-26");
		System.out.println(res);

	}
}
