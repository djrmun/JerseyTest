package com.akkineni.rest.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.akkineni.rest.domain.User;

@Repository
public class JdbcWebphoneDaoImpl implements JdbcWebphoneDao {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
				dataSource);

	}

	@Override
	public User fetchUserFromWebPhone(final String uid) {
		String sql = "select * from all_web_phone_view where sbcuid= :uid";

		SqlParameterSource namedParameters = new MapSqlParameterSource("uid",
				uid);
		User userval = this.namedParameterJdbcTemplate.queryForObject(sql,
				namedParameters, new RowMapper<User>() {

					@Override
					public User mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						User user = new User();
						user.setCn(rs.getString("FIRST_NAME") + " "
								+ rs.getString("LAST_NAME"));
						user.setSn(rs.getString("LAST_NAME"));
						user.setGivenName(rs.getString("FIRST_NAME"));
						user.setTelephoneNumber(rs.getString("WORK_PHONE"));
						user.setMail(rs.getString("SMTP_ADDRESS"));
						user.setManager(rs.getString("SUPERVISOR_ID"));
						user.setL(rs.getString("WORK_CITY"));
						user.setUid(uid);
						return user;
					}
				});

		return userval;

	}
}
