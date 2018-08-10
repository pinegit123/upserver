package com.csl.ws.hotel.service;

import com.csl.ws.hotel.po.AuthInfo;

public interface AuthService {

	public int saveAuthInfo(AuthInfo authInfo);
	
	public int updateAuthInfo4Hotel(String hotelId);
}
