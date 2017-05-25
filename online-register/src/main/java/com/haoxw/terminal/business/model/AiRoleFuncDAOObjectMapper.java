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
public class AiRoleFuncDAOObjectMapper implements ObjectMapper {
	private static final Logger log = LoggerFactory
			.getLogger(AiRoleFuncDAOObjectMapper.class);

	public Object mapping(ResultSet rs) {
		AiRoleFunc aiRoleFunc = new AiRoleFunc();
		try {
			aiRoleFunc.setId(rs.getInt("id"));
			aiRoleFunc.setFid(rs.getInt("fid"));
			aiRoleFunc.setRid(rs.getInt("rid"));
			aiRoleFunc.setUptime(rs.getLong("uptime"));
		} catch (Exception ex) {
			log.error("", ex);
		}
		return aiRoleFunc;
	}

}