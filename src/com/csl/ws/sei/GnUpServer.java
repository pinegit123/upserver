package com.csl.ws.sei;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface GnUpServer {

	/**
	 * 处理境内旅客入住信息上传
	 * @param cistr
	 * @return
	 */
	@WebMethod
	public String handCIinfo(String cistr);
	
	/**
	 * 处理境内旅客退宿信息上传
	 * @param ccstr
	 * @return
	 */
	@WebMethod
	public String handCCinfo(String ccstr);
	
	
	
}
