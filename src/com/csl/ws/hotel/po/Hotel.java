package com.csl.ws.hotel.po;

/**
 * 旅馆信息
 * @author Administrator
 *
 */
public class Hotel {

	private String code;  //旅馆代码
	private String called;  //旅馆名称
	private String status;  //旅馆状态：1正常;0注销
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCalled() {
		return called;
	}
	public void setCalled(String called) {
		this.called = called;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
