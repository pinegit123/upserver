package com.csl.ws.hotel.po;

/**
 * 退宿信息
 * @author Administrator
 *
 */
public class RecInfo {
	private String id;
	private String xm;
	private String sex;
	private String mz;
	private String csrq;
	private String zjzl;
	private String zjhm;
	private String xzqh;
	private String xxdz;
	private String rzsj; //入住时间 yyyyMMddHHmm
	private String tssj; //退宿时间 yyyyMMddHHmm
	private String fjhm; //房间号
	private String hotelId; //旅馆ID
	private String pcs;  //所属派出所
	
	private String traTime; //传送时间
	private Integer transferFlag=0;//传送标识 默认:0 传送:1
	private String rksj;//入库时间  默认系统日期
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getMz() {
		return mz;
	}
	public void setMz(String mz) {
		this.mz = mz;
	}
	public String getCsrq() {
		return csrq;
	}
	public void setCsrq(String csrq) {
		this.csrq = csrq;
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
	public String getXxdz() {
		return xxdz;
	}
	public void setXxdz(String xxdz) {
		this.xxdz = xxdz;
	}
	public String getRzsj() {
		return rzsj;
	}
	public void setRzsj(String rzsj) {
		this.rzsj = rzsj;
	}
	public String getTssj() {
		return tssj;
	}
	public void setTssj(String tssj) {
		this.tssj = tssj;
	}
	public String getFjhm() {
		return fjhm;
	}
	public void setFjhm(String fjhm) {
		this.fjhm = fjhm;
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
	public RecInfo(String id, String xm, String sex, String mz, String csrq,
			String zjzl, String zjhm, String xzqh, String xxdz,String rzsj, String tssj,
			String fjhm, String hotelId,String pcs) {
		super();
		this.id = id;
		this.xm = xm;
		this.sex = sex;
		this.mz = mz;
		this.csrq = csrq;
		this.zjzl = zjzl;
		this.zjhm = zjhm;
		this.xzqh = xzqh;
		this.xxdz=xxdz;
		this.rzsj = rzsj;
		this.tssj = tssj;
		this.fjhm = fjhm;
		this.hotelId = hotelId;
		this.pcs=pcs;
	}
	@Override
	public String toString() {
		return "RecInfo [id=" + id + ", xm=" + xm + ", sex=" + sex + ", mz="
				+ mz + ", csrq=" + csrq + ", zjzl=" + zjzl + ", zjhm=" + zjhm
				+ ", xzqh=" + xzqh + ", xxdz=" + xxdz + ", rzsj=" + rzsj
				+ ", tssj=" + tssj + ", fjhm=" + fjhm + ", hotelId=" + hotelId
				+ ", pcs=" + pcs + ", traTime=" + traTime + ", transferFlag="
				+ transferFlag + ", rksj=" + rksj + "]";
	}
}
