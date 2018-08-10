package com.csl.ws.hotel.service;

import com.csl.ws.hotel.po.JiryInfo;
import com.csl.ws.hotel.po.RecInfo;

/**
 * 国内旅客服务
 * @author LIUCS
 *
 */
public interface JiryService {
	
	//入住
	public String saveJiry(JiryInfo jiInfo);
	
	public JiryInfo getJiryById(String id);
	
	//退宿
	public String saveGnOut(RecInfo recInfo);
	
	//生成入住包
	public String createCITrmFile(JiryInfo jiInfo);
	
	public void createCCTrmFile(RecInfo recInfo);
	
	//public String saveJiryOut(String id);

}
