package com.csl.ws.hotel.dao;


import java.util.List;

import com.csl.ws.hotel.po.Hotel;

/**
 * 旅馆DAO
 * @author Administrator
 *
 */
public interface HotelDao {

	public List<Hotel> selectHotelByZt(String lgzt);
	
	public Hotel selectHotelById(String code);
	
}
