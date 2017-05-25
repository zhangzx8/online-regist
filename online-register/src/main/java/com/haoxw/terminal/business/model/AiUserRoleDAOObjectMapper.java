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
public class AiUserRoleDAOObjectMapper implements ObjectMapper {
	private static final Logger log = LoggerFactory
			.getLogger(AiUserRoleDAOObjectMapper.class);

	public Object mapping(ResultSet rs) {
		AiUserRole aiUserRole = new AiUserRole();
		try {
			aiUserRole.setId(rs.getInt("id"));
			aiUserRole.setRoleuid(rs.getString("roleuid"));
			aiUserRole.setRid(rs.getInt("rid"));
			aiUserRole.setUptime(rs.getLong("uptime"));
		} catch (Exception ex) {
			log.error("", ex);
		}
		return aiUserRole;
	}

}