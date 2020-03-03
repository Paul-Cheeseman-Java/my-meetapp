package com.meetapp.appuser.dao;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import com.meetapp.appuser.model.AppUser;


@Repository
public class AppUserDAOImpl implements AppUserDAO{

	private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	public AppUserDAOImpl() {
		
	}

	@Override
	public void insertUser(AppUser user) {
		String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
		jdbcTemplate.update(sql, user.getUsername(), user.getPassword());
	}

	@Override
	public AppUser getUser(String username) {
		String sql = "SELECT * FROM users WHERE username='" + username +"';";
		return jdbcTemplate.query(sql, new ResultSetExtractor<AppUser>() {
			@Override
			public AppUser extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					AppUser user = new AppUser();
					user.setUsername(rs.getString("username"));
					return user;
				}
				return null;
			}
		});
	}
		
	/*
	@Override
	public boolean validateUser(AppUser user) {

		return true;
	}
	*/
	

}
