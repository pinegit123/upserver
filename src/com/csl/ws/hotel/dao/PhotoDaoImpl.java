package com.csl.ws.hotel.dao;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.csl.ws.hotel.po.PhotoInfo;


public class PhotoDaoImpl extends SqlSessionDaoSupport implements PhotoDao {
	
	@Override
	public int insertPhoto(PhotoInfo photoInfo) {
		return getSqlSession().insert("com.csl.ws.hotel.po.photo.insertPhoto", photoInfo);
	}

}
