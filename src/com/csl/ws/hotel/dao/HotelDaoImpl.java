package com.csl.ws.hotel.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.csl.ws.hotel.po.Hotel;

public class HotelDaoImpl extends SqlSessionDaoSupport implements HotelDao {

	@Override
	public List<Hotel> selectHotelByZt(String lgzt) {
		
		return getSqlSession().selectList("com.csl.ws.hotel.po.Hotel.selectHotelByZt", lgzt);
	}

	@Override
	public Hotel selectHotelById(String code) {
		return getSqlSession().selectOne("com.csl.ws.hotel.po.Hotel.selectHotelById", code);
	}

}
