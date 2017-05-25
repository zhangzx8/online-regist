package com.haoxw.terminal.business.model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.haoxw.db.oracle.base.ObjectMapper;

/**
 * 
 * @author haoxw
 * 
 */
public class PriceMonitorMapDAOObjectMapper implements ObjectMapper {
	private static final Logger log = LoggerFactory
			.getLogger(PriceMonitorMapDAOObjectMapper.class);

	public Object mapping(ResultSet rs) {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			for(int i=1;i<=rsmd.getColumnCount();i++){
				String key = rsmd.getColumnLabel(i);
				Object value = rs.getObject(i);
				map.put(key.toLowerCase(), value);
			}
			
//			map.put("p_id", rs.getInt("p_id"));
//			map.put("p_type", rs.getString("p_type"));
//			map.put("p_province", rs.getString("p_province"));
//			map.put("p_1", rs.getInt("p_1"));
//			map.put("p_2", rs.getInt("p_2"));
//			map.put("p_3", rs.getInt("p_3"));
//			map.put("p_4", rs.getInt("p_4"));
//			map.put("p_5", rs.getInt("p_5"));
//			map.put("p_6", rs.getInt("p_6"));
//			map.put("p_7", rs.getInt("p_7"));
//			map.put("p_8", rs.getInt("p_8"));
//			map.put("p_9", rs.getInt("p_9"));
//			map.put("p_10", rs.getInt("p_10"));
//			map.put("p_11", rs.getInt("p_11"));
//			map.put("p_12", rs.getInt("p_12"));
//			map.put("p_13", rs.getInt("p_13"));
//			map.put("p_14", rs.getInt("p_14"));
//			map.put("p_15", rs.getInt("p_15"));
//			map.put("p_16", rs.getInt("p_16"));
//			map.put("p_17", rs.getInt("p_17"));
//			map.put("p_18", rs.getInt("p_18"));
//			map.put("p_19", rs.getInt("p_19"));
//			map.put("p_20", rs.getInt("p_20"));
//			map.put("p_21", rs.getInt("p_21"));
//			map.put("p_22", rs.getInt("p_22"));
//			map.put("p_23", rs.getInt("p_23"));
//			map.put("p_24", rs.getInt("p_24"));
//			map.put("p_25", rs.getInt("p_25"));
//			map.put("p_26", rs.getInt("p_26"));
//			map.put("p_27", rs.getInt("p_27"));
//			map.put("p_28", rs.getInt("p_28"));
//			map.put("p_29", rs.getInt("p_29"));
//			map.put("p_30", rs.getInt("p_30"));
//			map.put("p_31", rs.getInt("p_31"));
//			map.put("p_month", rs.getInt("p_month"));
			
		

		} catch (Exception ex) {
			log.error("", ex);
		}
		return map;
	}

}