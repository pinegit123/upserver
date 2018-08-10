package com.csl.ws.hotel.dao;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.csl.ws.hotel.po.AuthInfo;

public class AuthDaoImpl extends SqlSessionDaoSupport implements AuthDao {

	@Override
	public int insertAuth(AuthInfo authInfo) {
		return getSqlSession().insert("com.csl.ws.hotel.po.AuthInfo.insertAuth", authInfo);
	}

	@Override
	public int updateAuth(String hotelId) {
		return getSqlSession().update("com.csl.ws.hotel.po.AuthInfo.updateAuth", hotelId);
	}

}
