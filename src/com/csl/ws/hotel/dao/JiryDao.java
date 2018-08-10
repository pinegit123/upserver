package com.csl.ws.hotel.dao;

import com.csl.ws.hotel.po.JiryInfo;
import com.csl.ws.hotel.po.RecInfo;

public interface JiryDao {

	public JiryInfo selectById(String id);

	public int insertJiry(JiryInfo jiInfo);

	public int insertJiry4Out(RecInfo recInfo);

	public int deleteJiryById(String ryId);
}
