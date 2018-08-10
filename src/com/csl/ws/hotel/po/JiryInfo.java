package com.csl.ws.hotel.po;

/**
 * 境内人员
 * @author liucs
 *
 */
public class JiryInfo {
	
	private String id;
	private String name;
	private String sex;
	private String nation;
	private String bdate;
	private String zjzl;
	private String zjhm;
	private String xzqh;
	
	private String inTime; //入住时间 yyyyMMddHHmm
	private String tssj; //退宿时间 yyyyMMddHHmm
	private String noRoom; //房间号
	private String address;
	
	private String traTime; //传送时间
	private Integer transferFlag=0;//传送标识 默认:0 传送:1
	private String rksj;//入库时间  默认系统日期
	private String hotelId; //旅馆ID
	private String pcs;  //所属派出所
	
//	private String authCode;//授权码：hotelId+年份 md5摘要
	
	private AuthInfo authInfo;
	
	private PhotoInfo photoInfo;
	
	public JiryInfo() {
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	
	public String getBdate() {
		return bdate;
	}
	public void setBdate(String bdate) {
		this.bdate = bdate;
	}
	public void setInTime(String inTime) {
		this.inTime = inTime;
	}
	public String getZjzl() {
		return zjzl;
	}
	public void setZjzl(String zjzl) {
		this.zjzl = zjzl;
	}
	public String getZjhm() {
		return zjhm;
	}
	public void setZjhm(String zjhm) {
		this.zjhm = zjhm;
	}
	public String getXzqh() {
		return xzqh;
	}
	public void setXzqh(String xzqh) {
		this.xzqh = xzqh;
	}
	public String getNoRoom() {
		return noRoom;
	}
	public void setNoRoom(String noRoom) {
		this.noRoom = noRoom;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTraTime() {
		return traTime;
	}
	public void setTraTime(String traTime) {
		this.traTime = traTime;
	}
	public Integer getTransferFlag() {
		return transferFlag;
	}
	public void setTransferFlag(Integer transferFlag) {
		this.transferFlag = transferFlag;
	}
	public String getRksj() {
		return rksj;
	}
	public void setRksj(String rksj) {
		this.rksj = rksj;
	}
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	public String getPcs() {
		return pcs;
	}
	public void setPcs(String pcs) {
		this.pcs = pcs;
	}
	public AuthInfo getAuthInfo() {
		return authInfo;
	}
	public void setAuthInfo(AuthInfo authInfo) {
		this.authInfo = authInfo;
	}
	public PhotoInfo getPhotoInfo() {
		return photoInfo;
	}
	public void setPhotoInfo(PhotoInfo photoInfo) {
		this.photoInfo = photoInfo;
	}
	public String getInTime() {
		return inTime;
	}
	
	public String getTssj() {
		return tssj;
	}
	public void setTssj(String tssj) {
		this.tssj = tssj;
	}
	public JiryInfo(String id, String name, String sex, String inTime,String tssj,
			String noRoom, String hotelId, AuthInfo authInfo,
			PhotoInfo photoInfo) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.inTime = inTime;
		this.tssj=tssj;
		this.noRoom = noRoom;
		this.hotelId = hotelId;
		this.authInfo = authInfo;
		this.photoInfo = photoInfo;
	}
	@Override
	public String toString() {
		return "JiryInfo [id=" + id + ", name=" + name + ", sex=" + sex
				+ ", nation=" + nation + ", bdate=" + bdate + ", zjzl=" + zjzl
				+ ", zjhm=" + zjhm + ", xzqh=" + xzqh + ", inTime=" + inTime
				+ ", tssj=" + tssj + ", noRoom=" + noRoom + ", rksj="
				+ rksj + ", hotelId=" + hotelId + "]";
	}
}
