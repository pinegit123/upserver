package com.csl.ws.sei;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.csl.ws.hotel.service.HandlerService;

@WebService(endpointInterface="com.csl.ws.sei.JwUpServer",serviceName="jwupService")
public class JwUpServerImpl implements JwUpServer {
	@Autowired
	private HandlerService handlerService;
	public void setHandlerService(HandlerService handlerService) {
		this.handlerService = handlerService;
	}
	
	/**
	 * 处理境外旅客入住信息上传
	 * @param jwstr
	 * @return
	 */
	@Override
	public String handJWinfo(String jwstr) {
		String jiresult="";
		if (jwstr!=null) {
			jiresult=handlerService.handleJIinfo(jwstr);	
		}
		return jiresult;
	}
	
	/**
	 * 处理境外旅客退宿信息上传
	 * @param jwstr
	 * @return
	 */
	@Override
	public String handJCinfo(String jcstr) {
		String jiresult=handlerService.handleJCinfo(jcstr);
		return jiresult;
	}
}
