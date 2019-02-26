package com.security.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.security.model.UserInfo;

public class LoginDaoImpl implements LoginDao {
	
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;			
	
	@Autowired
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate)throws DataAccessException
	{
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate; 
	}

	public UserInfo fineUserInfo(String username) {
		
		String query = "select * from user where username = :username";
		// TODO Auto-generated method stub
		try{
			UserInfo info   = namedParameterJdbcTemplate.queryForObject(query, getSqlParameterByModel(username, ""),new UserInfoMappper());
			return info;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public List<String> getUserRoles(String username) {
		String query = "select user_role from user_roles where username = :username";
		List<String> roles = namedParameterJdbcTemplate.queryForList(query, getSqlParameterByModel(username, ""),String.class);
		// TODO Auto-generated method stub
		return roles;
	}
	
	private SqlParameterSource getSqlParameterByModel(String username,String password)
	{
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("username", username);
		parameterSource.addValue("password", password);
		
		return parameterSource;
	}
	
	private static final class UserInfoMappper implements RowMapper<UserInfo>{
		
		public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException{
			
			String username  = rs.getString("username");
			String password = rs.getString("password");
			 
			return new UserInfo(username,password);
		}
	}

}
