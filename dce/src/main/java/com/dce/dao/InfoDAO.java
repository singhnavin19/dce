package com.dce.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.dce.entity.Admission;

@Component
public class InfoDAO {

	@Autowired
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	public JdbcTemplate jdbcTemplate;

	public List<Admission> getDetailsById(String uid) {

		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("uid", uid);

		StringBuilder query = new StringBuilder();
		query.append("select * from ");
		query.append("tadmission ");
		query.append("where uid =:uid ");

		return this.namedParameterJdbcTemplate.query(query.toString(), param,
				new BeanPropertyRowMapper<Admission>(Admission.class));
	}
}
