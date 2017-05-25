package com.haoxw.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.haoxw.db.oracle.base.PageModel;
import com.haoxw.terminal.business.dao.AiFuncDao;
import com.haoxw.terminal.business.dao.AiUserDao;
import com.haoxw.terminal.business.dao.PriceMonitorDao;
import com.haoxw.terminal.business.model.AiFunc;
import com.haoxw.terminal.business.model.AiUser;
import com.haoxw.terminal.business.model.PriceMonitor;
import com.haoxw.terminal.business.service.OaService;
import com.haoxw.terminal.business.util.BeanJsonUtil;
import com.haoxw.terminal.business.util.DateUtil;
import com.haoxw.terminal.business.util.UserXml;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:applicationContext.xml")
public class PriceMontiorTest extends AbstractJUnit4SpringContextTests {

	@Resource
	private PriceMonitorDao priceMonitorDao;
	
	@Ignore
	@Test
	public void testquery() {
		String month = "201504";
		List<PriceMonitor> queryList = priceMonitorDao.queryGroupByType(month);
		System.out.println(queryList.size());
	}
	
	@Test
	public void JsonTest(){
		List<String> list = DateUtil.getLastMonthDays();
		System.out.println(list.toString());
	}

	
}
