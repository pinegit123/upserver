package com.csl.ws.sei;

import javax.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;

import com.csl.ws.hotel.service.HandlerService;

@WebService(endpointInterface="com.csl.ws.sei.GnUpServer" ,serviceName="gnupService")
public class GnUpServerImpl implements GnUpServer {

	@Autowired
	private HandlerService handlerService;
	
	public void setHandlerService(HandlerService handlerService) {
		this.handlerService = handlerService;
	}
	/**
	 * 处理境内入住上传
	 */
	@Override
	public String handCIinfo(String cistr) {
		System.out.println("handerservice:============"+handlerService);
		String ciresult=handlerService.handleCIinfo(cistr);
		return ciresult;
	}
	
	/**
	 * 处理境内退宿上传
	 */
	@Override
	public String handCCinfo(String ccstr) {
		String ccresult=handlerService.handleCCinfo(ccstr);
		return ccresult;
	}
	
}
