package com.haoxw.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.haoxw.terminal.business.dao.AiFuncDao;
import com.haoxw.terminal.business.dao.AiUserDao;
import com.haoxw.terminal.business.model.AiFunc;
import com.haoxw.terminal.business.model.AiUser;
import com.haoxw.terminal.business.service.OaService;
import com.haoxw.terminal.business.util.UserXml;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:applicationContext.xml")
public class AiFuncTest extends AbstractJUnit4SpringContextTests {

	@Resource
	AiFuncDao aiFuncDao;
	
	@Test
	public void testquery() {
		List<AiFunc> list =  aiFuncDao.allFunc();
		for(AiFunc af : list){
			System.out.println(af.getUptime());
		}
	}
	
	
	@Test
	public void testgetFuncByUser() {
		String guid = "124154254";
		List<AiFunc> list =  aiFuncDao.getFuncByUser(guid);
		for(AiFunc af : list){
			System.out.println(af.getId());
		}
	}
	
	
	
}
