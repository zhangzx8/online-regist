package com.haoxw.terminal.business.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.haoxw.db.oracle.base.OpDao;
import com.haoxw.terminal.business.model.AiFunc;
import com.haoxw.terminal.business.model.AiFuncDAOObjectMapper;
import com.haoxw.terminal.business.model.AiRole;
import com.haoxw.terminal.business.model.AiRoleDAOObjectMapper;
import com.haoxw.terminal.business.model.AiUserRole;
import com.haoxw.terminal.business.model.AiUserRoleDAOObjectMapper;

/**
 * 用户角色
 * 
 * @author zhang
 *
 */
@Repository
public class AiUserRoleDao {

	private static OpDao opDao = new OpDao();

	/**
	 * 保存用户角色关联关系
	 * 
	 * @param aur
	 * @return
	 */
	public boolean saveUserRole(AiUserRole aur) {
		String addsql = " insert into ai_user_role(id,roleuid,rid,uptime) values(seq_ai.nextval,?,?,?) ";
		Object[] obj = { aur.getRoleuid(), aur.getRid(),
				System.currentTimeMillis() };
		return opDao.update(addsql, obj, false);
	}

	/**
	 * 修改用户权限关系
	 * 
	 * @param aur
	 * @return
	 */
	public boolean updateUserRole(AiUserRole aur) {
		String updatesql = "update ai_user_role set roleuid=?,rid=?,uptime=? where id=?";
		Object[] obj = { aur.getRoleuid(), aur.getRid(),
				System.currentTimeMillis(), aur.getId() };
		return opDao.update(updatesql, obj, false);
	}

	public boolean delUserRole(String uid) {
		String delsql = " delete from  ai_user_role where roleuid = ?";
		Object[] obj = { uid };
		return opDao.update(delsql, obj, false);
	}

	public List<AiRole> getRoleByUser(String uid) {
		String sql = "select f.* from AI_ROLE f,AI_USER_ROLE rf where rf.rid=f.id and rf.roleuid = ? ";
		Object[] obj = { uid };
		return (List<AiRole>) opDao
				.query(sql, obj, new AiRoleDAOObjectMapper());
	}

	public List<AiUserRole> getUserRoleByUser(String uid) {
		String sql = "select * from AI_USER_ROLE where roleuid=?";
		Object[] obj = { uid };
		return (List<AiUserRole>) opDao.query(sql, obj,
				new AiUserRoleDAOObjectMapper());
	}

	public boolean updateUserRoleByUser(int rid, String uid) {
		String updatesql = "update ai_user_role set rid=?,uptime=? where roleuid=?";
		Object[] obj = { rid, System.currentTimeMillis(), uid };
		return opDao.update(updatesql, obj, false);
	}
}
