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
public class AiUserDAOObjectMapper implements ObjectMapper {
	private static final Logger log = LoggerFactory
			.getLogger(AiUserDAOObjectMapper.class);

	public Object mapping(ResultSet rs) {
		AiUser aiUser = new AiUser();
		try {
			aiUser.setGuid(rs.getString("guid"));
			aiUser.setUsername(rs.getString("username"));
			aiUser.setName(rs.getString("name"));
			aiUser.setDepartment(rs.getString("department"));
			aiUser.setMobile(rs.getString("mobile"));
			aiUser.setMail(rs.getString("mail"));
			aiUser.setUserlevel(rs.getString("userlevel"));
			aiUser.setOrder_(rs.getInt("order_"));
			aiUser.setCardid(rs.getString("cardid"));
			aiUser.setUptime(rs.getLong("uptime"));
			aiUser.setGroupprovince(rs.getString("groupprovince"));
			aiUser.setState(rs.getInt("state"));
		} catch (Exception ex) {
			log.error("", ex);
		}

		return aiUser;
	}

}