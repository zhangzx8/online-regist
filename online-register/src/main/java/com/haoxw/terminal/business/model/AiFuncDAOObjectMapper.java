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
public class AiFuncDAOObjectMapper implements ObjectMapper {
	private static final Logger log = LoggerFactory.getLogger(AiFuncDAOObjectMapper.class);
	public Object mapping(ResultSet rs) {
		AiFunc func = new AiFunc();
		try {
			func.setId(rs.getInt("id"));
			func.setTitle(rs.getString("title"));
			func.setUrl(rs.getString("url"));
			func.setUptime(rs.getLong("uptime"));
			func.setState(rs.getInt("state"));
			func.setParentid(rs.getInt("parentid"));
			func.setRank(rs.getInt("rank"));
			
		} catch (Exception ex) {
			log.error("", ex);
		}
		return func;
	}

}