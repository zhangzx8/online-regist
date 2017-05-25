package com.haoxw.terminal.business.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.haoxw.db.oracle.base.ObjectMapper;
import com.haoxw.db.oracle.base.PageModel;
import com.haoxw.terminal.business.dao.PriceMonitorDao;
import com.haoxw.terminal.business.model.PageGrid;
import com.haoxw.terminal.business.model.PriceMonitor;
import com.haoxw.terminal.business.util.DateUtil;
import com.haoxw.terminal.business.util.ExcelUtil;

@Service("priceMonitorService")
public class PriceMonitorService {
	
	@Resource
	private PriceMonitorDao priceMonitorDao;
	
	public void saveFromExcel(List<List<String>> list){
		if(list == null || list.size() < 1){
			return;
		}
		String month = "";
		PriceMonitor pm = null;
		List<PriceMonitor> listTmp = new ArrayList<PriceMonitor>();
		for(int i=1;i<list.size();i++){
			List<String> listLine = list.get(i);//获取excel每一行数据
			if(listLine == null || listLine.size() < 1){
				continue;//结束本次循环
			}
			if(month.equals("")){
				month = listLine.get(2);
			}
			pm = this.createPriceMontor(listLine);
			listTmp.add(pm);
		}
		
		this.dealDao(month, listTmp);
		
	}
	
	public PriceMonitor createPriceMontor(List<String> listLine){
		String yearmonth = listLine.get(2);
		int year = Integer.parseInt(yearmonth.substring(0, 4));
		int month = Integer.parseInt(yearmonth.substring(4));
		int days = DateUtil.getDays(year, month);
		
		
		PriceMonitor pm = new PriceMonitor();
		pm.setP_province(listLine.get(0));
		pm.setP_type(listLine.get(1));
		pm.setP_month(listLine.get(2));
		pm.setP_1(Integer.parseInt(listLine.get(3)));
		pm.setP_2(Integer.parseInt(listLine.get(4)));
		pm.setP_3(Integer.parseInt(listLine.get(5)));
		pm.setP_4(Integer.parseInt(listLine.get(6)));
		pm.setP_5(Integer.parseInt(listLine.get(7)));
		pm.setP_6(Integer.parseInt(listLine.get(8)));
		pm.setP_7(Integer.parseInt(listLine.get(9)));
		pm.setP_8(Integer.parseInt(listLine.get(10)));
		pm.setP_9(Integer.parseInt(listLine.get(11)));
		pm.setP_10(Integer.parseInt(listLine.get(12)));
		pm.setP_11(Integer.parseInt(listLine.get(13)));
		pm.setP_12(Integer.parseInt(listLine.get(14)));
		pm.setP_13(Integer.parseInt(listLine.get(15)));
		pm.setP_14(Integer.parseInt(listLine.get(16)));
		pm.setP_15(Integer.parseInt(listLine.get(17)));
		pm.setP_16(Integer.parseInt(listLine.get(18)));
		pm.setP_17(Integer.parseInt(listLine.get(19)));
		pm.setP_18(Integer.parseInt(listLine.get(20)));
		pm.setP_19(Integer.parseInt(listLine.get(21)));
		pm.setP_20(Integer.parseInt(listLine.get(22)));
		pm.setP_21(Integer.parseInt(listLine.get(23)));
		pm.setP_22(Integer.parseInt(listLine.get(24)));
		pm.setP_23(Integer.parseInt(listLine.get(25)));
		pm.setP_24(Integer.parseInt(listLine.get(26)));
		pm.setP_25(Integer.parseInt(listLine.get(27)));
		pm.setP_26(Integer.parseInt(listLine.get(28)));
		pm.setP_27(Integer.parseInt(listLine.get(29)));
		pm.setP_28(Integer.parseInt(listLine.get(30)));
		
		if(days==29){
			pm.setP_29(Integer.parseInt(listLine.get(31)));
		}
		if(days==30){
			pm.setP_29(Integer.parseInt(listLine.get(31)));
			pm.setP_30(Integer.parseInt(listLine.get(32)));
		}
		if(days==31){
			pm.setP_29(Integer.parseInt(listLine.get(31)));
			pm.setP_30(Integer.parseInt(listLine.get(32)));
			pm.setP_31(Integer.parseInt(listLine.get(33)));
		}
		
		
		return pm;
	}
	
	public void dealDao(String month, List<PriceMonitor> listTmp){
		
		if(listTmp.size() < 1){
			return;
		}
		if(!month.equals("")){
			priceMonitorDao.deleteByPMonth(month);//删除上传月份数据
		}
		
		//保存原始数据
		for(PriceMonitor pm : listTmp){
			priceMonitorDao.savePriceMonitor(pm);
		}
		
		//保存机型平均值
		List<PriceMonitor> queryList = priceMonitorDao.queryGroupByType(month);
		for(PriceMonitor queryPM : queryList){
			queryPM.setP_month(month);
			queryPM.setP_province("平均值");
			priceMonitorDao.savePriceMonitor(queryPM);
		}
		
	}
	
	
	public PageGrid queryByDays(String p_province,String p_month, int pageNo, int pageSize){
		
		int year = Integer.parseInt(p_month.substring(0, 4));
		int month = Integer.parseInt(p_month.substring(4));
		int days = DateUtil.getDays(year, month);
		
	
		//PageModel pm = priceMonitorDao.queryByDays(p_province, p_month, pageNo, pageSize, days);
		int totalCount = priceMonitorDao.getTotalCount(p_province, p_month);
		List<Map<String,Object>> list = priceMonitorDao.queryForMapPage(p_province, p_month,pageNo,pageSize);
		
		List<Map<String,Object>> newList = new ArrayList<Map<String,Object>>();
		Map<String,Object> newMap = null;
		for(int i=0;i<list.size();i++){
			newMap = new LinkedHashMap<String,Object>();
			Map<String,Object> map = list.get(i);//每一条记录
			newMap.put("p_province", map.get("p_province"));
			newMap.put("p_type", map.get("p_type"));
			newMap.put("p_1", map.get("p_1"));
			newMap.put("p_2", map.get("p_2"));
			newMap.put("p_3", map.get("p_3"));
			newMap.put("p_4", map.get("p_4"));
			newMap.put("p_5", map.get("p_5"));
			newMap.put("p_6", map.get("p_6"));
			newMap.put("p_7", map.get("p_7"));
			newMap.put("p_8", map.get("p_8"));
			newMap.put("p_9", map.get("p_9"));
			newMap.put("p_10", map.get("p_10"));
			newMap.put("p_11", map.get("p_11"));
			newMap.put("p_12", map.get("p_12"));
			newMap.put("p_13", map.get("p_13"));
			newMap.put("p_14", map.get("p_14"));
			newMap.put("p_15", map.get("p_15"));
			newMap.put("p_16", map.get("p_16"));
			newMap.put("p_17", map.get("p_17"));
			newMap.put("p_18", map.get("p_18"));
			newMap.put("p_19", map.get("p_19"));
			newMap.put("p_20", map.get("p_20"));
			newMap.put("p_21", map.get("p_21"));
			newMap.put("p_22", map.get("p_22"));
			newMap.put("p_23", map.get("p_23"));
			newMap.put("p_24", map.get("p_24"));
			newMap.put("p_25", map.get("p_25"));
			newMap.put("p_26", map.get("p_26"));
			newMap.put("p_27", map.get("p_27"));
			newMap.put("p_28", map.get("p_28"));
			
			if(days==29){
				newMap.put("p_29", map.get("p_29"));
			}
			if(days==30){
				newMap.put("p_29", map.get("p_29"));
				newMap.put("p_30", map.get("p_30"));
			}
			if(days==31){
				newMap.put("p_29", map.get("p_29"));
				newMap.put("p_30", map.get("p_30"));
				newMap.put("p_31", map.get("p_31"));
			}

			newList.add(newMap);
		}
		
		PageModel pm = new PageModel();
		pm.setPageNo(pageNo);
		pm.setPageSize(pageSize);
		pm.setList(newList);
		pm.setTotalRecords(totalCount);
		//PageModel pm = priceMonitorDao.queryByDays(p_province, p_month, pageNo, pageSize, days);
		List<String> headers = new ArrayList<String>();
		headers.add("地域");
		headers.add("机型");
		for(int i=1;i<=days;i++){
			headers.add(month+"月"+i+"号");
		}

		
		PageGrid pg = new PageGrid();
		pg.setPage(pm);
		pg.setHeaders(headers);
		pg.setMonth(p_month);
		return pg;
		
	}
	
	
	public void downPriceMonitor(String p_province, String p_month, HttpServletResponse response) throws IOException {
		int year = Integer.parseInt(p_month.substring(0, 4));
		int month = Integer.parseInt(p_month.substring(4));
		int days = DateUtil.getDays(year, month);
		
		List<String> headers = new ArrayList<String>();
		headers.add("地域");
		headers.add("机型");
		for(int i=1;i<=days;i++){
			headers.add(month+"月"+i+"号");
		}
		String[] title = headers.toArray(new String[days+2]);
		List<Object[]> listAll = new ArrayList<Object[]>();
		List<Object> listCell = null;
		//List list = priceMonitorDao.queryForDownload(p_province, p_month, days);
		//List<String>
		List<Map<String,Object>> list = priceMonitorDao.queryForMapDownLoad(p_province, p_month);
		
		for(int i=0;i<list.size();i++){
			listCell = new ArrayList<Object>();
			Map<String,Object> map = list.get(i);//每一条记录
			listCell.add(map.get("p_province"));
			listCell.add(map.get("p_type"));
			listCell.add(map.get("p_1"));
			listCell.add(map.get("p_2"));
			listCell.add(map.get("p_3"));
			listCell.add(map.get("p_4"));
			listCell.add(map.get("p_5"));
			listCell.add(map.get("p_6"));
			listCell.add(map.get("p_7"));
			listCell.add(map.get("p_8"));
			listCell.add(map.get("p_9"));
			listCell.add(map.get("p_10"));
			listCell.add(map.get("p_11"));
			listCell.add(map.get("p_12"));
			listCell.add(map.get("p_13"));
			listCell.add(map.get("p_14"));
			listCell.add(map.get("p_15"));
			listCell.add(map.get("p_16"));
			listCell.add(map.get("p_17"));
			listCell.add(map.get("p_18"));
			listCell.add(map.get("p_19"));
			listCell.add(map.get("p_20"));
			listCell.add(map.get("p_21"));
			listCell.add(map.get("p_22"));
			listCell.add(map.get("p_23"));
			listCell.add(map.get("p_24"));
			listCell.add(map.get("p_25"));
			listCell.add(map.get("p_26"));
			listCell.add(map.get("p_27"));
			listCell.add(map.get("p_28"));
			if(days==29){
				listCell.add(map.get("p_29"));
			}
			if(days==30){
				listCell.add(map.get("p_29"));
				listCell.add(map.get("p_20"));
			}
			if(days==31){
				listCell.add(map.get("p_29"));
				listCell.add(map.get("p_20"));
				listCell.add(map.get("p_31"));
			}

			listAll.add(listCell.toArray());
		}
		
		response.setHeader("Content-Disposition", "attachment;filename="+ p_month+".xls");
		// 设定输出文件头
		response.setContentType("application/vnd.ms-excel;charset=UTF-8");
		ExcelUtil.writeExcel(response.getOutputStream(), listAll, title);
		
	}

}
