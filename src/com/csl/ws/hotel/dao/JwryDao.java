package com.csl.ws.hotel.dao;

import com.csl.ws.hotel.po.JwryInfo;

public interface JwryDao {

	public JwryInfo selectById(String id);

	public int insertJwry(JwryInfo jwInfo);

	public int insertJwry4Out(JwryInfo jwInfo);

	public void deleteJwryById(String id);
}
