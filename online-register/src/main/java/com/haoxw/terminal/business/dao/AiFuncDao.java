package com.haoxw.terminal.business.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.haoxw.db.oracle.base.OpDao;
import com.haoxw.terminal.business.model.AiFunc;
import com.haoxw.terminal.business.model.AiFuncDAOObjectMapper;

/**
 * 功能dao
 * 
 * @author haoxw
 * 
 */
@Repository
public class AiFuncDao {
	private static OpDao opDao = new OpDao();
	private static boolean result = false;

	/**
	 * 添加功能
	 * 
	 * @param func
	 * @return
	 */
	public boolean saveFunc(AiFunc func) {
		String addsql = " insert into ai_func(id,title,url,state,uptime,parentid,rank) values(seq_ai.nextval,?,?,?,?,?,?) ";
		Object[] obj = { func.getTitle(), func.getUrl(), func.getState(),
				System.currentTimeMillis(), func.getParentid(), func.getRank()};
		return opDao.update(addsql, obj, false);
	}

	/**
	 * 修改功能
	 * 
	 * @param func
	 * @return
	 */
	public boolean updateFunc(AiFunc func) {
		String updatesql = "update ai_func set title=?,url=?,state=?,uptime=?,parentid=?,rank=? where id=?";
		Object[] obj = { func.getTitle(), func.getUrl(), func.getState(),
				System.currentTimeMillis(), func.getParentid(),func.getRank(),func.getId() };
		return opDao.update(updatesql, obj, false);
	}

	/**
	 * 查找单条功能
	 * 
	 * @param id
	 * @return
	 */
	public AiFunc getFuncById(int id) {
		String sql = "select * from ai_func where id=?";
		Object[] obj = { id };
		return (AiFunc) opDao.query(sql, obj, new AiFuncDAOObjectMapper()).get(
				0);
	}

	/**
	 * 查找全部功能列表
	 * 
	 * @return
	 * @throws DbException
	 */
	public List<AiFunc> allFunc() {
		String sql = "select * from ai_func ";
		Object[] obj = {};
		return (List<AiFunc>) opDao
				.query(sql, obj, new AiFuncDAOObjectMapper());
	}

	/**
	 * 根据某父节点获取下属功能列表
	 * 
	 * @param parentid
	 * @return
	 */
	public List<AiFunc> getFuncByParentid(int parentid) {
		String sql = "select * from ai_func where parentid = ? ";
		Object[] obj = { parentid };
		return (List<AiFunc>) opDao
				.query(sql, obj, new AiFuncDAOObjectMapper());
	}
	
	/**
	 * 获取
	 * @param guid
	 * @return
	 */
	public List<AiFunc> getFuncByUser(String guid) {
		String sql = " select f.* from AI_USER_ROLE t, AI_FUNC f,AI_ROLE_FUNC rf where t.rid=rf.rid and rf.fid=f.id and t.roleuid = ? ";
		Object[] obj = { guid };
		return (List<AiFunc>) opDao
				.query(sql, obj, new AiFuncDAOObjectMapper());
	}
	
	
	public List<AiFunc> getFuncByUserByRank(String guid) {
		String sql = " select f.* from AI_USER_ROLE t, AI_FUNC f,AI_ROLE_FUNC rf where t.rid=rf.rid and rf.fid=f.id and t.roleuid = ? order by rank ";
		Object[] obj = { guid };
		return (List<AiFunc>) opDao
				.query(sql, obj, new AiFuncDAOObjectMapper());
	}

	/**
	 * 获取某一角色下功能列表
	 * 
	 * @param id
	 *            角色id
	 * @return
	 */
	public List<AiFunc> getFuncByRole(int id) {
		String sql = "select f.* from AI_FUNC f,AI_ROLE_FUNC rf where rf.fid=f.id and rf.rid = ? ";
		Object[] obj = { id };
		return (List<AiFunc>) opDao
				.query(sql, obj, new AiFuncDAOObjectMapper());
	}

	/**
	 * 删除功能
	 * 
	 * @param func
	 * @return
	 */
	public boolean delFunc(int id) {
		String delsql = " delete from  ai_func where id = ?";
		Object[] obj = { id };
		String delrolefuncsql = " delete from  ai_role_func where fid = ?";
		opDao.update(delsql, obj, false);
		opDao.update(delrolefuncsql, obj, false);
		return result;
	}

	public static void main(String args[]) {
		AiFuncDao funcDao = new AiFuncDao();
		for (int i = 1; i < 1000; i++) {
			AiFunc f = new AiFunc();
			f.setId(i);
			f.setState(1);
			f.setTitle("123水电费");
			f.setUptime(System.currentTimeMillis());
			f.setUrl("http://baidu.com");
			funcDao.saveFunc(f);
		}

		// funcDao.updateFunc(f);

		System.out.println(funcDao.getFuncById(1).toString());

		System.out.println(funcDao.allFunc().size());
	}
}
