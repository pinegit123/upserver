package com.csl.ws.hotel.service;

import java.util.List;

import com.csl.ws.hotel.po.Hotel;

public interface HotelService {

	public List<Hotel> findHotelsByZt(String lgzt);
	
	public Hotel getHotelById(String code);
}
