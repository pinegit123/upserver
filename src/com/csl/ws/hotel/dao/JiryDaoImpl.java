package com.csl.ws.hotel.dao;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.csl.ws.hotel.po.JiryInfo;
import com.csl.ws.hotel.po.RecInfo;

public class JiryDaoImpl extends SqlSessionDaoSupport implements JiryDao {

	@Override
	public JiryInfo selectById(String id) {
		return getSqlSession().selectOne("com.csl.ws.hotel.po.Jiry.selectJiryById",id);
	}

	@Override
	public int insertJiry(JiryInfo jiInfo) {
		return getSqlSession().insert("com.csl.ws.hotel.po.Jiry.insertJiry", jiInfo);
	}

	@Override
	public int insertJiry4Out(RecInfo recInfo) {
		System.out.println("gnry-rec:"+recInfo);
		return getSqlSession().insert("com.csl.ws.hotel.po.RecInfo.insertRecInfo", recInfo);
	}

	@Override
	public int deleteJiryById(String ryId) {
		return getSqlSession().delete("com.csl.ws.hotel.po.Jiry.deleteJiryById", ryId);
	}

}
