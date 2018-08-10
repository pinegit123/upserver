package com.csl.ws.hotel.po;

/**
 * 认证授权信息
 * @author Administrator
 *
 */
public class AuthInfo {

	private String hotelId;
	private String authCode;
	private String authDate;
	private Integer authStatus=1; //1:已授权;2:授权错误;3:授权过期
	
	public AuthInfo() {
	}
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	public String getAuthCode() {
		return authCode;
	}
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	public String getAuthDate() {
		return authDate;
	}
	public void setAuthDate(String authDate) {
		this.authDate = authDate;
	}
	public Integer getAuthStatus() {
		return authStatus;
	}
	public void setAuthStatus(Integer authStatus) {
		this.authStatus = authStatus;
	}
	public AuthInfo(String hotelId, String authCode, String authDate,
			Integer authStatus) {
		super();
		this.hotelId = hotelId;
		this.authCode = authCode;
		this.authDate = authDate;
		this.authStatus = authStatus;
	}
	@Override
	public String toString() {
		return "AuthInfo [hotelId=" + hotelId + ", authCode=" + authCode
				+ ", authDate=" + authDate + ", authStatus=" + authStatus + "]";
	}
	
	
}
