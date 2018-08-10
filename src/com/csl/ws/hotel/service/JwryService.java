package com.csl.ws.hotel.service;

import com.csl.ws.hotel.po.JwryInfo;

public interface JwryService {

	//保存境外人员
	public String saveJwry(JwryInfo jwInfo);
	
	//取得单个境外人员
	public JwryInfo getJwryById(String id);
	
	//退宿
	public String saveJwOut(JwryInfo jwInfo);
		
	//生成入住包
	public String createJITrmFile(JwryInfo jwInfo);
	
	//生成退宿包
	public void createJCTrmFile(JwryInfo jwInfo);
	
}
