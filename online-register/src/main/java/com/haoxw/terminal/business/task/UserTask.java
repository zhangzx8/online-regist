package com.haoxw.terminal.business.task;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.haoxw.terminal.business.dao.AiUserDao;
import com.haoxw.terminal.business.dao.AiUserRoleDao;
import com.haoxw.terminal.business.model.AiUser;
import com.haoxw.terminal.business.model.AiUserRole;
import com.haoxw.terminal.business.service.AiUserService;
import com.haoxw.terminal.business.service.OaService;
import com.haoxw.terminal.business.util.DateUtil;
import com.haoxw.terminal.business.util.UserXml;

@Repository
public class UserTask {

	private final static Logger logger = LoggerFactory
			.getLogger(UserTask.class);
	@Resource
	AiUserDao aiUesrDao;
	@Resource
	OaService oaService;
	@Resource 
	AiUserRoleDao aiUserRoleDao;
	@Resource
	AiUserService aiUserService;

	/**
	 * 同步用户
	 */
	public void sync() {
		Date now = new Date();
		String sdate = DateUtil.getLastDate(now, "yyyy-MM-dd");
		String edate = DateUtil.date2Str(now, "yyyy-MM-dd");
		logger.info("start task sdate=" + sdate + "  edate=" + edate);
		List<AiUser> list = UserXml.readStringXml(oaService, sdate, edate);
		aiUserService.saveUser(list);
//		int num = list.size();
//		AiUserRole role = null;
//		for (int i = 0; i < num; i++) {
//			aiUesrDao.saveAiUser(list.get(i));
//			//初始化默认角色
//			role = new AiUserRole();
//			role.setRid(1);
//			role.setRoleuid(list.get(i).getGuid());
//			aiUserRoleDao.saveUserRole(role);
//		}
		logger.info("end task sdate=" + sdate + "  edate=" + edate);
	}
}
