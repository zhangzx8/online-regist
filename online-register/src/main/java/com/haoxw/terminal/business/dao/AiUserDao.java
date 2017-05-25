package com.haoxw.terminal.business.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.haoxw.db.oracle.base.OpDao;
import com.haoxw.db.oracle.base.PageModel;
import com.haoxw.terminal.business.model.AiUser;
import com.haoxw.terminal.business.model.AiUserDAOObjectMapper;

/**
 * 用户管理dao
 * 
 * @author haoxw
 * 
 */
@Repository
public class AiUserDao {

	private static OpDao opDao = new OpDao();

	/**
	 * 添加用户
	 * 
	 * @param user
	 * @return
	 */
	public boolean saveAiUser(AiUser user) {
		String addsql = " insert into ai_user(guid,username,name,department,mobile,mail,userlevel,order_,cardid,uptime,groupprovince,state) values(?,?,?,?,?,?,?,?,?,?,?,?) ";
		Object[] obj = { user.getGuid(), user.getUsername(), user.getName(),
				user.getDepartment(), user.getMobile(), user.getMail(),
				user.getUserlevel(), user.getOrder_(), user.getCardid(),
				System.currentTimeMillis(), user.getGroupprovince(),
				user.getState() };
		return opDao.update(addsql, obj, false);
	}

	/**
	 * 修改用户
	 * 
	 * @param user
	 * @return
	 */
	public boolean updateAiUser(AiUser user) {
		String updatesql = "update  ai_user set username=?,name=?,department=?,mobile=?,mail=?,userlevel=?,order_=?,cardid=?,uptime=?,groupprovince=?,state=? where guid=?";
		Object[] obj = { user.getUsername(), user.getName(),
				user.getDepartment(), user.getMobile(), user.getMail(),
				user.getUserlevel(), user.getOrder_(), user.getCardid(),
				user.getUptime(), user.getGroupprovince(), user.getState(),
				user.getGuid() };
		return opDao.update(updatesql, obj, false);
	}

	/**
	 * 查找用户
	 * 
	 * @param guid
	 * @return
	 */
	public AiUser getAiUserById(String guid) {
		String sql = "select * from ai_user where guid=?";
		Object[] obj = { guid };
		List objTmp = opDao.query(sql, obj, new AiUserDAOObjectMapper());
		if (objTmp != null && objTmp.size() > 0) {
			return (AiUser) objTmp.get(0);
		} else
			return null;
	}
	
	
	/**
	 * 查找用户
	 * 不区分大小写匹配
	 * @param userName  用户名称
	 * @return
	 */
	public AiUser getAiUserByUserName(String userName) {
		String sql = "select * from ai_user where lower(username)=?";
		Object[] obj = {userName.toLowerCase()};
		List objTmp = opDao.query(sql, obj, new AiUserDAOObjectMapper());
		if (objTmp != null && objTmp.size() > 0) {
			return (AiUser) objTmp.get(0);
		} else
			return null;
	}
	
	
	
	/**
	 * 查询用户用于插入时判断
	 * @param guid
	 * @return
	 */
	public int getCountByGuid(String guid) {
		String sql = "select count(1) from ai_user where guid=?";
		Object[] obj = { guid };
		int count = opDao.count(sql, obj);
		return count;
	}

	/**
	 * 翻页查询
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageModel query(int pageNo, int pageSize) {
		String sql1 = "select count(*) from ai_user";
		Object[] obj1 = {};
		int i = opDao.count(sql1, obj1);
		String sql = "select * from (select j.*,rownum rn from (select * from ai_user ) j where rownum<=?) where rn>?";
		Object[] obj = { pageNo * pageSize, (pageNo - 1) * pageSize };
		List<AiUser> list = (List<AiUser>) opDao.query(sql, obj,
				new AiUserDAOObjectMapper());
		PageModel pagemodel = new PageModel();
		pagemodel.setPageNo(pageNo);
		pagemodel.setPageSize(pageSize);
		pagemodel.setList(list);
		pagemodel.setTotalRecords(i);
		return pagemodel;
	}
	
	
	public PageModel query(int pageNo, int pageSize, String userName) {
		String sql1 = "select count(1) from ai_user where username=?";
		Object[] obj1 = {userName};
		int i = opDao.count(sql1, obj1);
		String sql = "select * from (select j.*,rownum rn from (select * from ai_user where username=? or name=? ) j where rownum<=?) where rn>?";
		Object[] obj = {userName, userName, pageNo * pageSize, (pageNo - 1) * pageSize };
		List<AiUser> list = (List<AiUser>) opDao.query(sql, obj,
				new AiUserDAOObjectMapper());
		PageModel pagemodel = new PageModel();
		pagemodel.setPageNo(pageNo);
		pagemodel.setPageSize(pageSize);
		pagemodel.setList(list);
		pagemodel.setTotalRecords(i);
		return pagemodel;
	}

	/**
	 * 查找所有用户列表
	 * 
	 * @return
	 * @throws DbException
	 */
	public List<AiUser> allAiUser() {
		String sql = "select * from ai_user  ";
		Object[] obj = {};
		return (List<AiUser>) opDao
				.query(sql, obj, new AiUserDAOObjectMapper());
	}

	public static void main(String args[]) {
		AiUserDao aud = new AiUserDao();

		// for(int i=1;i<100;i++){
		// AiUser au = new AiUser();
		// au.setGuid("111"+i);
		// au.setUsername("郝学武"+i);
		// au.setName("test"+i);
		// au.setDepartment("123");
		// au.setMobile("13311111111");
		// au.setMail("123123");
		// au.setGroupprovince("123123");
		// au.setUserlevel("2");
		// au.setOrder_(i);
		// au.setCardid("111111111111111111111");
		// au.setState(0);
		// au.setUptime(System.currentTimeMillis());
		//
		//
		// aud.updateAiUser(au);
		// }
		System.out.print(aud.query(1, 10));
	}

}
