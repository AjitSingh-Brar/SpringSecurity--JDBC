package ca.sheridancollege.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.Bean.User;

@Repository

public class DataAccess {
	
	@Autowired 
	private NamedParameterJdbcTemplate jdbc;
	
	public User findUserAccount(String userName)
	{
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		System.out.println(userName);
		String query = "select * from sec_user where userName = :userName";
		parameter.addValue("userName",userName);
		ArrayList<User> user = (ArrayList<User>) jdbc.query(query, parameter,new BeanPropertyRowMapper<User>(User.class));
		
		if(user.size()> 0 )
		{
			return user.get(0);
			
		}
		return null;
	}
	
	public List<String> getRolesById(long userId)
	{
		ArrayList<String> roles = new ArrayList<String>();
	    MapSqlParameterSource parameter = new MapSqlParameterSource();
	    String query = "select user_role.userId,sec_role.roleName from user_role,sec_role where user_role.roleId = sec_role.roleId and userId =:userId";
	    parameter.addValue("userId", userId);
	    List<Map<String,Object>> rows = jdbc.queryForList(query, parameter);
	    
	    for(Map<String,Object> row: rows)
	    {
	    	roles.add((String)row.get("roleName"));
	    	
	    }
	    return roles;
	}

}
