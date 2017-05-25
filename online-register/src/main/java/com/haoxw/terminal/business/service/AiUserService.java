package com.haoxw.terminal.business.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.haoxw.terminal.business.dao.AiUserDao;
import com.haoxw.terminal.business.dao.AiUserRoleDao;
import com.haoxw.terminal.business.model.AiUser;
import com.haoxw.terminal.business.model.AiUserRole;

@Service("aiUserService")
public class AiUserService {
	
	@Resource
	private AiUserDao aiUserDao;
	@Resource
	private AiUserRoleDao aiUserRoleDao;
	
	public void saveUser(List<AiUser> list){
		
		
		if(list != null && list.size() > 0){
			AiUserRole role = null;
			
			for(AiUser user : list){
				String guid = user.getGuid();//获取用户guid
				int count = aiUserDao.getCountByGuid(guid);
				if(count == 0){
					aiUserDao.saveAiUser(user);//插入用户
					//初始化默认角色
					role = new AiUserRole();
					role.setRid(1);
					role.setRoleuid(user.getGuid());
					aiUserRoleDao.saveUserRole(role);
				}
			}
		}
		
	}
	
	
	

}
