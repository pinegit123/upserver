package com.csl.ws.hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csl.ws.hotel.dao.AuthDao;
import com.csl.ws.hotel.po.AuthInfo;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private AuthDao authDao;
	
	public void setAuthDao(AuthDao authDao) {
		this.authDao = authDao;
	}
	
	@Override
	public int saveAuthInfo(AuthInfo authInfo) {
		return authDao.insertAuth(authInfo);
	}

	@Override
	public int updateAuthInfo4Hotel(String hotelId) {
		return authDao.updateAuth(hotelId);
	}

}
