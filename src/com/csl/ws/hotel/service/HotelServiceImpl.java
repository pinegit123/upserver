package com.csl.ws.hotel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csl.ws.hotel.dao.HotelDao;
import com.csl.ws.hotel.po.Hotel;

/**
 * 旅馆服务实现
 * @author Administrator
 *
 */
@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	private HotelDao hotelDao;
	
	public void setHotelDao(HotelDao hotelDao) {
		this.hotelDao = hotelDao;
	}
	
	@Override
	public List<Hotel> findHotelsByZt(String lgzt) {
		return hotelDao.selectHotelByZt(lgzt);
	}

	@Override
	public Hotel getHotelById(String code) {
		return hotelDao.selectHotelById(code);
	}

}
