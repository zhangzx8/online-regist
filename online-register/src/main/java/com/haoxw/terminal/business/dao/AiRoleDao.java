package com.haoxw.terminal.business.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.haoxw.db.oracle.base.OpDao;
import com.haoxw.terminal.business.model.AiRole;
import com.haoxw.terminal.business.model.AiRoleDAOObjectMapper;

/**
 * 角色dao
 * 
 * @author haoxw
 *
 */
@Repository
public class AiRoleDao {
	private static OpDao opDao = new OpDao();

	/**
	 * 添加
	 * 
	 * @param role
	 * @return
	 */
	public boolean saveRole(AiRole role) {
		String addsql = " insert into ai_role(id,title,descp,state,uptime) values(seq_ai.nextval,?,?,?,?) ";
		Object[] obj = { role.getTitle(), role.getDescp(), role.getState(),
				System.currentTimeMillis() };
		return opDao.update(addsql, obj, false);
	}

	/**
	 * 修改角色
	 * 
	 * @param role
	 * @return
	 */
	public boolean updateD(AiRole role) {
		String updatesql = "update ai_role set title=?,descp=?,state=?,uptime=? where id=?";
		Object[] obj = { role.getTitle(), role.getDescp(), role.getState(),
				System.currentTimeMillis(), role.getId() };
		return opDao.update(updatesql, obj, false);
	}

	/**
	 * 查找单条角色
	 * 
	 * @param id
	 * @return
	 */
	public AiRole getAiRoleById(int id) {
		String sql = "select * from ai_role where id=?";
		Object[] obj = { id };
		return (AiRole) opDao.query(sql, obj, new AiRoleDAOObjectMapper()).get(
				0);
	}

	/**
	 * 查找全部功能列表
	 * 
	 * @return
	 * @throws DbException
	 */
	public List<AiRole> allAiRole() {
		String sql = "select * from ai_role ";
		Object[] obj = {};
		return (List<AiRole>) opDao
				.query(sql, obj, new AiRoleDAOObjectMapper());
	}

	/**
	 * 删除角色
	 * 
	 * @param id
	 *            角色主键id
	 * @return
	 */
	public boolean delFunc(int id) {
		String delsql = " delete from  ai_role where id = ?";
		Object[] obj = { id };
		String delrolefuncsql = " delete from  ai_role_func where rid = ?";
		opDao.update(delsql, obj, false);
		opDao.update(delrolefuncsql, obj, false);
		return true;
	}

	public static void main(String args[]) {
		AiRoleDao aiRoleDao = new AiRoleDao();
		for (int i = 1; i < 1000; i++) {
			AiRole f = new AiRole();
			f.setId(i);
			f.setState(1);
			f.setTitle("123水电费");
			f.setUptime(System.currentTimeMillis());
			f.setDescp("一二三12312312" + i);
			aiRoleDao.saveRole(f);
		}

		// funcDao.updateFunc(f);

		System.out.println(aiRoleDao.getAiRoleById(1).toString());

		System.out.println(aiRoleDao.allAiRole().size());
	}

}
