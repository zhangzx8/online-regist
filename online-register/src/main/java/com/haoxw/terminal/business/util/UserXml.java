package com.haoxw.terminal.business.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.haoxw.terminal.business.model.AiUser;
import com.haoxw.terminal.business.service.OaService;

/**
 * 
 * @author haoxw
 * 
 */
public class UserXml {
	private static final Logger logger = LoggerFactory.getLogger(UserXml.class);

//	public static List<AiUser> readStringXml(OaService oaService, String start,
//			String end) {
//		List<AiUser> listAiUser = new ArrayList<AiUser>();
//		Map<String, String> provMap = ProvinceCode.provinceCode;
//		String res = oaService.getUesr(start, end);
//		if (StringUtils.isEmpty(res)) {
//			logger.info("webservice 返回为null");
//		} else {
//			Document doc = null;
//			try {
//				doc = DocumentHelper.parseText(res); // 将字符串转为XML
//				Element rootElt = doc.getRootElement(); // 获取根节点
//				Iterator iter = rootElt.elementIterator("OU"); // 获取所有子节点
//				// 遍历OU节点
//				while (iter.hasNext()) {
//					Element recordEle = (Element) iter.next();
//					Iterator iters = recordEle.elementIterator("USER"); // 获取该节点下的子节点USER
//
//					
//					
//					// 遍历Header节点下的Response节点
//					while (iters.hasNext()) {
//						AiUser au = new AiUser();
//						Element itemEle = (Element) iters.next();
//						String USERNAME = itemEle.attributeValue("USERNAME"); // 获取该节点其他属性值
//						String GUID = itemEle.attributeValue("GUID");
//						String NAME = itemEle.attributeValue("NAME");
//						String DEPARTMETN = itemEle
//								.attributeValue("DEPARTMETN");
//						String Mobile = itemEle.attributeValue("Mobile");
//						String MAIL = itemEle.attributeValue("MAIL");
//						String TITLENUM = itemEle.attributeValue("TITLENUM");
//						String ORDER = itemEle.attributeValue("ORDER");
//						String PATH = itemEle.attributeValue("PATH");
//						au.setDepartment(DEPARTMETN);
//						au.setGuid(GUID);
//						au.setMail(MAIL);
//						au.setMobile(Mobile);
//						au.setName(NAME);
//						au.setUsername(USERNAME);
//						if (!StringUtils.isEmpty(ORDER))
//							au.setOrder_(Integer.parseInt(ORDER));
//						else
//							au.setOrder_(-1);
//						au.setState(0);
//						au.setUptime(System.currentTimeMillis());
//
//						if (!StringUtils.isBlank(USERNAME)) {
//
//							if (USERNAME.indexOf(".") != -1) {
//								// 带。的为分公司
//								String province = USERNAME.substring(USERNAME
//										.indexOf(".") + 1);// 获取省份简码
//								au.setGroupprovince(provMap.get(province));
//							} else {
//								// 总部人员
//								au.setGroupprovince(provMap.get("ZB"));
//							}
//
//						} else {
//							au.setGroupprovince("0");
//						}
//
//						listAiUser.add(au);
//					}
//				}
//
//			} catch (Exception e) {
//				logger.error("", e);
//			}
//		}
//		return listAiUser;
//	}
	
	
	
	
	public static List<AiUser> readStringXml(OaService oaService, String start,
			String end) {
		List<AiUser> listAiUser = new ArrayList<AiUser>();
		String res = oaService.getUesr(start, end);
		System.out.println("webservice response ::::"+res);
		if (StringUtils.isEmpty(res)) {
			logger.info("webservice 返回为null");
		} else {
			Document doc = null;
			try {
				doc = DocumentHelper.parseText(res); // 将字符串转为XML
				Element rootElt = doc.getRootElement(); // 获取根节点
				parseChildElement(listAiUser, rootElt);

			} catch (Exception e) {
				logger.error("", e);
			}
		}
		return listAiUser;
	}
	
	
	public static void parseChildElement(List<AiUser> list, Element rootElement){
		
		if(rootElement.elements().size() != 0){
			
		//有子节点
		
		//List<Element> eleList =	rootElement.elements();
		//Iterator<Element> eleIter = eleList.iterator();
		Iterator<Element> eleIter = rootElement.elementIterator("OU");
		while(eleIter.hasNext()){
			Element ele = eleIter.next();
			Iterator  userIter = ele.elementIterator("USER");//获取所有user节点
			createUserByElement(list, userIter);//创建user对象且加入到list里面
			
			parseChildElement(list, ele);//递归遍历
			}
		}
		
		
	}
	
	
	public static void createUserByElement(List<AiUser> list, Iterator userIter){
		
		Map<String, String> provMap = ProvinceCode.provinceCode;//省份编码
		
		// 遍历Header节点下的Response节点
		while (userIter.hasNext()) {
			AiUser au = new AiUser();
			Element itemEle = (Element) userIter.next();
			String USERNAME = itemEle.attributeValue("USERNAME"); // 获取该节点其他属性值
			String GUID = itemEle.attributeValue("GUID");
			String NAME = itemEle.attributeValue("NAME");
			String DEPARTMETN = itemEle
					.attributeValue("DEPARTMETN");
			String Mobile = itemEle.attributeValue("Mobile");
			String MAIL = itemEle.attributeValue("MAIL");
			String TITLENUM = itemEle.attributeValue("TITLENUM");
			String ORDER = itemEle.attributeValue("ORDER");
			String PATH = itemEle.attributeValue("PATH");
			au.setDepartment(DEPARTMETN);
			au.setGuid(GUID);
			au.setMail(MAIL);
			au.setMobile(Mobile);
			au.setName(NAME);
			au.setUsername(USERNAME);
			if (!StringUtils.isEmpty(ORDER))
				au.setOrder_(Integer.parseInt(ORDER));
			else
				au.setOrder_(-1);
			au.setState(0);
			au.setUptime(System.currentTimeMillis());

			if (!StringUtils.isBlank(USERNAME)) {

				if (USERNAME.indexOf(".") != -1) {
					// 带。的为分公司
					String province = USERNAME.substring(USERNAME
							.indexOf(".") + 1);// 获取省份简码
					au.setGroupprovince(provMap.get(province));
				} else {
					// 总部人员
					au.setGroupprovince(provMap.get("ZB"));
				}

			} else {
				au.setGroupprovince("0");
			}

			list.add(au);
		}
		
		
		
	}
	
	

}
