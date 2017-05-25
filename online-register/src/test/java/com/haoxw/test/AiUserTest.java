package com.haoxw.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.haoxw.terminal.business.dao.AiUserDao;
import com.haoxw.terminal.business.dao.AiUserRoleDao;
import com.haoxw.terminal.business.model.AiUser;
import com.haoxw.terminal.business.model.AiUserRole;
import com.haoxw.terminal.business.service.AiUserService;
import com.haoxw.terminal.business.service.OaService;
import com.haoxw.terminal.business.util.UserXml;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:applicationContext.xml")
public class AiUserTest extends AbstractJUnit4SpringContextTests {

	@Resource
	AiUserDao aiUesrDao;
	@Resource
	OaService oaService;
	@Resource
	AiUserRoleDao aiUserRoleDao;
	@Resource
	AiUserService aiUserService;

	//初始化用户信息
	//@Ignore
	@Test
	public void testsyn() {
		List<AiUser> list = UserXml.readStringXml(oaService, "2014-07-01",
				"2015-04-20");
		aiUserService.saveUser(list);
//		int num = list.size();
//		for (int i = 0; i < num; i++) {
//			aiUesrDao.saveAiUser(list.get(i));
//			System.out.println(i);
//		}
	}
	
	
	@Test
	public void testsyn2() {
		List<AiUser> list = new ArrayList<AiUser>();;
		aiUserService.saveUser(list);
//		int num = list.size();
//		for (int i = 0; i < num; i++) {
//			aiUesrDao.saveAiUser(list.get(i));
//			System.out.println(i);
//		}
	}
	
	
	
	//设置默认角色
	@Ignore
	@Test
	public void initRoleToUser() {
		List<AiUser> list = aiUesrDao.allAiUser();

		AiUserRole role = null;
		for (AiUser user : list) {
			role = new AiUserRole();
			role.setRid(1);
			role.setRoleuid(user.getGuid());
			aiUserRoleDao.saveUserRole(role);
		}
	}
	
	//初始化相关用户角色
	//@Ignore
	@Test
	public void intRoleByTxt() {
		//按行读取
		   FileInputStream fis = null;
		   InputStreamReader isr = null;
		   BufferedReader br = null; //用于包装InputStreamReader,提高处理性能。因为BufferedReader有缓冲的，而InputStreamReader没有。
		   try {
		    String str = "";
		    String str1 = "";
		    fis = new FileInputStream("C:\\Users\\zhangzx8\\Desktop\\123.txt");// FileInputStream
		    // 从文件系统中的某个文件中获取字节
		     isr = new InputStreamReader(fis);// InputStreamReader 是字节流通向字符流的桥梁,
		     br = new BufferedReader(isr);// 从字符输入流中读取文件中的内容,封装了一个new InputStreamReader的对象
		    while ((str = br.readLine()) != null) {
		    	System.out.println("str:"+str);
		    }
		   } catch (FileNotFoundException e) {
		    System.out.println("找不到指定文件");
		   } catch (IOException e) {
		    System.out.println("读取文件失败");
		   } finally {
		    try {
		      br.close();
		      isr.close();
		      fis.close();
		     // 关闭的时候最好按照先后顺序关闭最后开的先关闭所以先关s,再关n,最后关m
		    } catch (IOException e) {
		     e.printStackTrace();
		    }
		   }
		   
		   
		String userName="admin";
		AiUser user = aiUesrDao.getAiUserByUserName(userName);
		if(user == null){
			System.out.println(userName);
		}
		

	}
	
	
	
	
	//修改角色
	private void updateRole(AiUser user){
		int rid=2;
		aiUserRoleDao.updateUserRoleByUser(rid, user.getGuid());
	}
	
	/**
	 * 修改地域
	 */
	private void updateGroupProvince(AiUser user){
		user.setGroupprovince("10");
		aiUesrDao.updateAiUser(user);
	}
}
