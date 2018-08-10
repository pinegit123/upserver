package com.csl.ws.sei;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface JwUpServer {
	/**
	 * 处理境外旅客入住信息上传
	 * @param jwstr
	 * @return
	 */
	@WebMethod
	public String handJWinfo(String jwstr);
	
	/**
	 *  处理境外旅客退宿信息上传
	 * @param jcstr
	 * @return
	 */
	@WebMethod
	public String handJCinfo(String jcstr);
}
