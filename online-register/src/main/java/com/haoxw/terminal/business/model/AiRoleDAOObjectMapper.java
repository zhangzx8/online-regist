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
public class AiRoleDAOObjectMapper implements ObjectMapper {
	private static final Logger log = LoggerFactory
			.getLogger(AiFuncDAOObjectMapper.class);

	public Object mapping(ResultSet rs) {
		AiRole aiRole = new AiRole();
		try {
			aiRole.setId(rs.getInt("id"));
			aiRole.setTitle(rs.getString("title"));
			aiRole.setDescp(rs.getString("descp"));
			aiRole.setUptime(rs.getLong("uptime"));
			aiRole.setState(rs.getInt("state"));

		} catch (Exception ex) {
			log.error("", ex);
		}
		return aiRole;
	}

}