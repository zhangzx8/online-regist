package com.haoxw.terminal.business.dao;

import org.springframework.stereotype.Repository;

import com.haoxw.db.oracle.base.OpDao;
import com.haoxw.terminal.business.model.AiRoleFunc;

/**
 * 角色功能关联
 * 
 * @author zhang
 *
 */
@Repository
public class AiRoleFuncDao {

	private static OpDao opDao = new OpDao();

	/**
	 * 添加功能到角色
	 * 
	 * @param arf
	 * @return
	 */
	public boolean saveRoleFunc(AiRoleFunc arf) {
		String addsql = " insert into ai_role_func(id,rid,fid,uptime) values(seq_ai.nextval,?,?,?) ";
		Object[] obj = { arf.getRid(), arf.getFid(), System.currentTimeMillis() };
		return opDao.update(addsql, obj, false);
	}

	/**
	 * 更新功能到角色
	 * 
	 * @param role
	 * @return
	 */
	public boolean updateRoleFunc(AiRoleFunc arf) {
		String updatesql = "update ai_role_func set rid=?,fid=?,uptime=? where id=?";
		Object[] obj = { arf.getRid(), arf.getFid(),
				System.currentTimeMillis(), arf.getId() };
		return opDao.update(updatesql, obj, false);
	}

	/**
	 * 删除角色功能关联关系
	 * 
	 * @param id
	 * @return
	 */
	public boolean delRoleFunc(int id) {
		String delsql = " delete from  ai_role_func where id = ?";
		Object[] obj = { id };
		return opDao.update(delsql, obj, false);
	}

	/**
	 * 删除角色功能
	 * 
	 * @param rid
	 * @return
	 */
	public boolean delRoleFuncByRole(int rid) {
		String delsql = " delete from  ai_role_func where rid = ?";
		Object[] obj = { rid };
		return opDao.update(delsql, obj, false);
	}

}
