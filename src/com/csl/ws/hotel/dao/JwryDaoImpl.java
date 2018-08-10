package com.csl.ws.hotel.dao;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.csl.ws.hotel.po.JwryInfo;


public class JwryDaoImpl extends SqlSessionDaoSupport implements JwryDao {

	@Override
	public JwryInfo selectById(String id) {
		return getSqlSession().selectOne("com.csl.ws.hotel.po.Jwry.selectJwryById",id);
	}

	@Override
	public int insertJwry(JwryInfo jwInfo) {
		return getSqlSession().insert("com.csl.ws.hotel.po.Jwry.insertJwry", jwInfo);
	}

	@Override
	public int insertJwry4Out(JwryInfo jwInfo) {
		return getSqlSession().insert("com.csl.ws.hotel.po.Jwry.insertJwryOut", jwInfo);
	}

	@Override
	public void deleteJwryById(String id) {
		getSqlSession().delete("com.csl.ws.hotel.po.Jwry.deleteJwry", id);
		
	}

}
