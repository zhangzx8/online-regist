package com.haoxw.terminal.business.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.haoxw.db.oracle.base.ObjectMapper;
import com.haoxw.db.oracle.base.OpDao;
import com.haoxw.db.oracle.base.PageModel;
import com.haoxw.terminal.business.model.AiRole;
import com.haoxw.terminal.business.model.AiRoleDAOObjectMapper;
import com.haoxw.terminal.business.model.PriceMonitor;
import com.haoxw.terminal.business.model.PriceMonitor28DAOObjectMapper;
import com.haoxw.terminal.business.model.PriceMonitor29DAOObjectMapper;
import com.haoxw.terminal.business.model.PriceMonitor30DAOObjectMapper;
import com.haoxw.terminal.business.model.PriceMonitor31DAOObjectMapper;
import com.haoxw.terminal.business.model.PriceMonitorDAOObjectMapper;
import com.haoxw.terminal.business.model.PriceMonitorMapDAOObjectMapper;

/**
 * 用户管理dao
 * 
 * @author haoxw
 * 
 */
@Repository
public class PriceMonitorDao {

	private static OpDao opDao = new OpDao();

	/**
	 * 添加记录
	 * 
	 * @param priceMonitor
	 * @return
	 */
	public boolean savePriceMonitor(PriceMonitor priceMonitor) {
		String addsql = " insert into price_monitor(p_id,p_province,p_type,p_month,p_1,p_2,p_3,p_4,p_5,p_6,p_7,p_8,p_9,p_10,p_11,p_12,p_13,p_14,p_15,p_16,p_17,p_18,p_19,p_20,p_21,p_22,p_23,p_24,p_25,p_26,p_27,p_28,p_29,p_30,p_31) values(seq_ai.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
		Object[] obj = { priceMonitor.getP_province(),
				priceMonitor.getP_type(), priceMonitor.getP_month(),
				priceMonitor.getP_1(), priceMonitor.getP_2(),
				priceMonitor.getP_3(), priceMonitor.getP_4(),
				priceMonitor.getP_5(), priceMonitor.getP_6(),
				priceMonitor.getP_7(), priceMonitor.getP_8(),
				priceMonitor.getP_9(), priceMonitor.getP_10(),
				priceMonitor.getP_11(), priceMonitor.getP_12(),
				priceMonitor.getP_13(), priceMonitor.getP_14(),
				priceMonitor.getP_15(), priceMonitor.getP_16(),
				priceMonitor.getP_17(), priceMonitor.getP_18(),
				priceMonitor.getP_19(), priceMonitor.getP_20(),
				priceMonitor.getP_21(), priceMonitor.getP_22(),
				priceMonitor.getP_23(), priceMonitor.getP_24(),
				priceMonitor.getP_25(), priceMonitor.getP_26(),
				priceMonitor.getP_27(), priceMonitor.getP_28(),
				priceMonitor.getP_29(), priceMonitor.getP_30(),
				priceMonitor.getP_31() };
		return opDao.update(addsql, obj, false);
	}

	/**
	 * 删除该月信息
	 * 
	 * @param month
	 * @return
	 */
	public boolean deleteByPMonth(String month) {
		String delsql = " delete from price_monitor where p_month = ?";
		Object[] obj = { month };
		opDao.update(delsql, obj, false);
		return true;
	}

	/**
	 * 根据省份和月份查询报表
	 * 
	 * @param p_province
	 * @param p_month
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageModel query(String p_province, String p_month, int pageNo,
			int pageSize) {
		String sql1 = "select count(*) from price_monitor where p_province=? and p_month=?";
		Object[] obj1 = { p_province, p_month };
		int i = opDao.count(sql1, obj1);
		String sql = "select * from (select j.*,rownum rn from (select * from price_monitor where p_province=? and p_month=?) j where rownum<=?) where rn>?";
		Object[] obj = { p_province, p_month, pageNo * pageSize,
				(pageNo - 1) * pageSize };
		List<PriceMonitor> list = (List<PriceMonitor>) opDao.query(sql, obj,
				new PriceMonitorDAOObjectMapper());
		PageModel pagemodel = new PageModel();
		pagemodel.setPageNo(pageNo);
		pagemodel.setPageSize(pageSize);
		pagemodel.setList(list);
		pagemodel.setTotalRecords(i);
		return pagemodel;
	}
	
	
	public int getTotalCount(String p_province, String p_month){
		int i =0;
		if(StringUtils.isEmpty(p_province)){
			return i;
		}
		
		if(p_province.equals("总部")){
			String sql1 = "select count(*) from price_monitor where p_month=?";
			Object[] obj1 = {p_month };
			i = opDao.count(sql1, obj1);
		}else{
			String sql1 = "select count(*) from price_monitor where p_province=? and p_month=?";
			Object[] obj1 = { p_province, p_month };
			i = opDao.count(sql1, obj1);
		}
		
		return i;
		
	}

	
	
	public List<Map<String,Object>> queryForMapDownLoad(String p_province, String p_month) {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		if (!StringUtils.isEmpty(p_province)) {
			StringBuffer buffer = new StringBuffer();
			 if(p_province.equals("总部")){
				//查询该月全部
				buffer.append("select *  from price_monitor where p_month=?");
				Object[] obj = {p_month};
				list = (List<Map<String,Object>>) opDao.query(buffer.toString(), obj, new PriceMonitorMapDAOObjectMapper());
			}else{
				//查询相应省份
				buffer.append("select *  from price_monitor where p_province=? and p_month=?");
				Object[] obj = { p_province, p_month};
				list = (List<Map<String,Object>>) opDao.query(buffer.toString(), obj, new PriceMonitorMapDAOObjectMapper());
			}
			
		}

		return list;
	}
	
	
	
	public List<Map<String,Object>> queryForMapPage(String p_province, String p_month, int pageNo, int pageSize) {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		if (!StringUtils.isEmpty(p_province)) {
			StringBuffer buffer = new StringBuffer();
			 if(p_province.equals("总部")){
				//查询该月全部
				buffer.append("select * from (select j.*,rownum rn from (select * from price_monitor where p_month=?) j where rownum<=?) where rn>?");
				Object[] obj = {p_month,pageNo * pageSize,(pageNo - 1) * pageSize};
				list = (List<Map<String,Object>>) opDao.query(buffer.toString(), obj, new PriceMonitorMapDAOObjectMapper());
			}else{
				//查询相应省份
				buffer.append("select * from (select j.*,rownum rn from (select * from price_monitor where p_province=? and p_month=?) j where rownum<=?) where rn>?");
				Object[] obj = { p_province, p_month,pageNo * pageSize,(pageNo - 1) * pageSize };
				list = (List<Map<String,Object>>) opDao.query(buffer.toString(), obj, new PriceMonitorMapDAOObjectMapper());
			}
			
		}

		return list;
	}
	
	

	public List<PriceMonitor> queryGroupByType(String month) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("select avg(p_1)p_id,p_type p_type,p_type p_province,avg(p_1)p_1,avg(p_2)p_2,avg(p_3)p_3,avg(p_4)p_4,avg(p_5)p_5,avg(p_6)p_6,avg(p_7)p_7,avg(p_8)p_8,avg(p_9)p_9,avg(p_10)p_10,avg(p_11)p_11,avg(p_12)p_12,avg(p_13)p_13,avg(p_14)p_14,avg(p_15)p_15,");
		buffer.append("avg(p_16)p_16,avg(p_17)p_17,avg(p_18)p_18,avg(p_19)p_19,avg(p_20)p_20,avg(p_21)p_21,avg(p_22)p_22,avg(p_23)p_23,avg(p_24)p_24,avg(p_25)p_25,avg(p_26)p_26,avg(p_27)p_27,avg(p_28)p_28,avg(p_29)p_29,avg(p_30)p_30,avg(p_31)p_31");
		buffer.append(" from price_monitor where p_month=? group by p_type");
		Object[] obj = { month };
		return (List<PriceMonitor>) opDao.query(buffer.toString(), obj,
				new PriceMonitorDAOObjectMapper());
	}

	public static void main(String args[]) {
		PriceMonitorDao aud = new PriceMonitorDao();

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
	}

}
