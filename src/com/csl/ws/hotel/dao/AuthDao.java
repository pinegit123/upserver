package com.csl.ws.hotel.dao;

import com.csl.ws.hotel.po.AuthInfo;

/**
 * 授权认证
 * @author LIUCS
 * @since 2018-01-12 
 * 
 */
public interface AuthDao {
 
	public int insertAuth(AuthInfo authInfo);
	
	public int updateAuth(String hotelId);
}
