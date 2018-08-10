package com.csl.ws.hotel.po;

/**
 * 境外人员
 * @author liucs
 *
 */
public class JwryInfo {

	private String id;	//人员ID
	private String ywx;	//英文姓
	private String ywm;	//英文名
	private String zwxm; //中文名
	private String sex;	//性别
	private String csrq;	//出生日期
	private String gjdq; //国籍
	private String zjzl; //证件种类
	private String zjhm; //证件号码
	private String qzzl; //签证种类
	private String qzhm; //签证号码
	
	private String tlyxqz; //停留有效期至 :TLYXQZ
	private String qfd; //签发机关 QFD
	private String rjrq; //入境日期
	private String rjka; //入境口岸
	
	private String rzsj; //入住时间 RZSJ
	private String fjhm; //房间号码 FH
	
	private String lyd; //从哪儿来  LYD
	private String qwd; //到哪儿去 QWD
	private String tlsy; //停留是由 TLSY
	
	private String hotelId; //旅馆ID
	private String tssj; //退宿时间 TSSJ
	
	private String pcs; //派出所代码
	private String rksj;//入库时间  默认系统日期
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getYwx() {
		return ywx;
	}
	public void setYwx(String ywx) {
		this.ywx = ywx;
	}
	public String getYwm() {
		return ywm;
	}
	public void setYwm(String ywm) {
		this.ywm = ywm;
	}
	public String getZwxm() {
		return zwxm;
	}
	public void setZwxm(String zwxm) {
		this.zwxm = zwxm;
	}
	public String getCsrq() {
		return csrq;
	}
	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getGjdq() {
		return gjdq;
	}
	public void setGjdq(String gjdq) {
		this.gjdq = gjdq;
	}
	public String getRzsj() {
		return rzsj;
	}
	public void setRzsj(String rzsj) {
		this.rzsj = rzsj;
	}
	public String getFjhm() {
		return fjhm;
	}
	public void setFjhm(String fjhm) {
		this.fjhm = fjhm;
	}
	public String getTssj() {
		return tssj;
	}
	public void setTssj(String tssj) {
		this.tssj = tssj;
	}
	public String getPcs() {
		return pcs;
	}
	public void setPcs(String pcs) {
		this.pcs = pcs;
	}
	public String getRjrq() {
		return rjrq;
	}
	public void setRjrq(String rjrq) {
		this.rjrq = rjrq;
	}
	public String getRjka() {
		return rjka;
	}
	public void setRjka(String rjka) {
		this.rjka = rjka;
	}
	public String getTlsy() {
		return tlsy;
	}
	public void setTlsy(String tlsy) {
		this.tlsy = tlsy;
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
	public String getQzzl() {
		return qzzl;
	}
	public void setQzzl(String qzzl) {
		this.qzzl = qzzl;
	}
	public String getQzhm() {
		return qzhm;
	}
	public void setQzhm(String qzhm) {
		this.qzhm = qzhm;
	}
	public String getTlyxqz() {
		return tlyxqz;
	}
	public void setTlyxqz(String tlyxqz) {
		this.tlyxqz = tlyxqz;
	}
	public String getQfd() {
		return qfd;
	}
	public void setQfd(String qfd) {
		this.qfd = qfd;
	}
	public String getLyd() {
		return lyd;
	}
	public void setLyd(String lyd) {
		this.lyd = lyd;
	}
	public String getQwd() {
		return qwd;
	}
	public void setQwd(String qwd) {
		this.qwd = qwd;
	}
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	
	public String getRksj() {
		return rksj;
	}
	public void setRksj(String rksj) {
		this.rksj = rksj;
	}
	
	public JwryInfo() {
	}
	public JwryInfo(String id, String ywx, String ywm, String zwxm, String sex,
			String csrq, String gjdq, String zjzl, String zjhm, String qzzl,
			String qzhm, String tlyxqz, String qfd, String rjrq, String rjka,
			String rzsj, String fjhm, String lyd, String qwd, String tlsy,
			String hotelId, String tssj, String pcs) {
		super();
		this.id = id;
		this.ywx = ywx;
		this.ywm = ywm;
		this.zwxm = zwxm;
		this.sex = sex;
		this.csrq = csrq;
		this.gjdq = gjdq;
		this.zjzl = zjzl;
		this.zjhm = zjhm;
		this.qzzl = qzzl;
		this.qzhm = qzhm;
		this.tlyxqz = tlyxqz;
		this.qfd = qfd;
		this.rjrq = rjrq;
		this.rjka = rjka;
		this.rzsj = rzsj;
		this.fjhm = fjhm;
		this.lyd = lyd;
		this.qwd = qwd;
		this.tlsy = tlsy;
		this.hotelId = hotelId;
		this.tssj = tssj;
		this.pcs = pcs;
	}
	@Override
	public String toString() {
		return "JwryInfo [id=" + id + ", ywx=" + ywx + ", ywm=" + ywm
				+ ", zwxm=" + zwxm + ", sex=" + sex + ", csrq=" + csrq
				+ ", gjdq=" + gjdq + ", zjzl=" + zjzl + ", zjhm=" + zjhm
				+ ", qzzl=" + qzzl + ", qzhm=" + qzhm + ", tlyxqz=" + tlyxqz
				+ ", qfd=" + qfd + ", rjrq=" + rjrq + ", rjka=" + rjka
				+ ", rzsj=" + rzsj + ", fjhm=" + fjhm + ", lyd=" + lyd
				+ ", qwd=" + qwd + ", tlsy=" + tlsy + ", hotelId=" + hotelId
				+ ", tssj=" + tssj + ", pcs=" + pcs + ", rksj=" + rksj + "]";
	}
}
