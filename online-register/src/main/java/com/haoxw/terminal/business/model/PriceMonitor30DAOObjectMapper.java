package com.haoxw.terminal.business.model;

import java.sql.ResultSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.haoxw.db.oracle.base.ObjectMapper;

/**
 * 
 * @author haoxw
 * 
 */
public class PriceMonitor30DAOObjectMapper implements ObjectMapper {
	private static final Logger log = LoggerFactory
			.getLogger(PriceMonitor30DAOObjectMapper.class);

	public Object mapping(ResultSet rs) {
		PriceMonitor30 priceMonitor = new PriceMonitor30();
		try {
			priceMonitor.setP_province(rs.getString("p_province"));
			priceMonitor.setP_type(rs.getString("p_type"));
			priceMonitor.setP_1(rs.getInt("p_1"));
			priceMonitor.setP_2(rs.getInt("p_2"));
			priceMonitor.setP_3(rs.getInt("p_3"));
			priceMonitor.setP_4(rs.getInt("p_4"));
			priceMonitor.setP_5(rs.getInt("p_5"));
			priceMonitor.setP_6(rs.getInt("p_6"));
			priceMonitor.setP_7(rs.getInt("p_7"));
			priceMonitor.setP_8(rs.getInt("p_8"));
			priceMonitor.setP_9(rs.getInt("p_9"));
			priceMonitor.setP_10(rs.getInt("p_10"));
			priceMonitor.setP_11(rs.getInt("p_11"));
			priceMonitor.setP_12(rs.getInt("p_12"));
			priceMonitor.setP_13(rs.getInt("p_13"));
			priceMonitor.setP_14(rs.getInt("p_14"));
			priceMonitor.setP_15(rs.getInt("p_15"));
			priceMonitor.setP_16(rs.getInt("p_16"));
			priceMonitor.setP_17(rs.getInt("p_17"));
			priceMonitor.setP_18(rs.getInt("p_18"));
			priceMonitor.setP_19(rs.getInt("p_19"));
			priceMonitor.setP_20(rs.getInt("p_20"));
			priceMonitor.setP_21(rs.getInt("p_21"));
			priceMonitor.setP_22(rs.getInt("p_22"));
			priceMonitor.setP_23(rs.getInt("p_23"));
			priceMonitor.setP_24(rs.getInt("p_24"));
			priceMonitor.setP_25(rs.getInt("p_25"));
			priceMonitor.setP_26(rs.getInt("p_26"));
			priceMonitor.setP_27(rs.getInt("p_27"));
			priceMonitor.setP_28(rs.getInt("p_28"));
			priceMonitor.setP_29(rs.getInt("p_29"));
			priceMonitor.setP_30(rs.getInt("p_30"));


		} catch (Exception ex) {
			log.error("", ex);
		}
		return priceMonitor;
	}

}