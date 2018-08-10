package com.csl.ws.hotel.service;

/**
 * 服务处理接口
 * @author Administrator
 *
 */
public interface HandlerService {

	//验证上传信息
	public boolean validateUpinfo(String upstr);
	
	//处理上传国内旅客信息
	public String handleCIinfo(String upstr);
	
	//处理国内退宿上传
	public String handleCCinfo(String ccstr);
	
	
	//处理境外入住上传
	public String handleJIinfo(String jistr);
	
	//处理境外退宿上传
	public String handleJCinfo(String jcstr);
	
}
