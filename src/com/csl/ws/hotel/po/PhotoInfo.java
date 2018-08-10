package com.csl.ws.hotel.po;


/**
 * 照片信息
 * @author Administrator
 *
 */
public class PhotoInfo {

	private String ryid="";
	private String photo=""; //BASE64 编码
	private String photoUrl="";
	private String photoUrl2=""; //现场照地址
	private Integer flag=1; //照片类型：1:证件照;2：现场照
	
	public PhotoInfo() {
	}
	
	public PhotoInfo(String ryid, String photo, String photoUrl,String photoUrl2) {
		super();
		this.ryid = ryid;
		this.photo = photo;
		this.photoUrl = photoUrl;
		this.photoUrl2 = photoUrl2;
	}
	public String getRyid() {
		return ryid;
	}
	public void setRyid(String ryid) {
		this.ryid = ryid;
	}
	
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	
	public String getPhotoUrl2() {
		return photoUrl2;
	}

	public void setPhotoUrl2(String photoUrl2) {
		this.photoUrl2 = photoUrl2;
	}

	@Override
	public String toString() {
		return "PhotoInfo [ryid=" + ryid + ", photo=" + photo + ", photoUrl="
				+ photoUrl + ", photoUrl2=" + photoUrl2 + "]";
	}
	
}
