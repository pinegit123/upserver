package com.csl.ws.hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csl.ws.hotel.dao.PhotoDao;
import com.csl.ws.hotel.po.PhotoInfo;

@Service
public class PhotoServiceImpl implements PhotoService {

	@Autowired
	private PhotoDao photoDao;
	
	public void setPhotoDao(PhotoDao photoDao) {
		this.photoDao = photoDao;
	}
	
	@Override
	public int savePhoto(PhotoInfo photoInfo) {
		return photoDao.insertPhoto(photoInfo);
	}

}
