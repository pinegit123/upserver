package com.csl.ws.hotel.service;

import java.io.File;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.csl.ws.hotel.po.AuthInfo;
import com.csl.ws.hotel.po.JiryInfo;
import com.csl.ws.hotel.po.JwryInfo;
import com.csl.ws.hotel.po.PhotoInfo;
import com.csl.ws.hotel.po.RecInfo;
import com.csl.ws.hotel.util.Config;
import com.csl.ws.hotel.util.Cryptos;
import com.csl.ws.hotel.util.DateUtils;
import com.csl.ws.hotel.util.Encodes;
import com.csl.ws.hotel.util.MsgMap;
import com.csl.ws.hotel.util.PropertiesLoader;
import com.csl.ws.hotel.util.SysCheck;

/**
 * 处理（酒店/旅馆）上传旅客信息
 * @author LIUCS
 *
 */
@Service
public class HandlerServiceImpl implements HandlerService {
	Logger logger=Logger.getLogger(HandlerServiceImpl.class);
	
	@Autowired
	private JiryService jiryService;
	
	@Autowired
	private JwryService jwryService;
	
	@Autowired
	private PhotoService photoService;
	
	public void setJiryService(JiryService jiryService) {
		this.jiryService = jiryService;
	}
	public void setPhotoService(PhotoService photoService) {
		this.photoService = photoService;
	}
	public void setJwryService(JwryService jwryService) {
		this.jwryService = jwryService;
	}
	
	ObjectMapper mapper=new ObjectMapper();
	/**
	 * 校验国内旅客上传信息
	 */
	@Override
	public boolean validateUpinfo(String upjsonstr) {
		Object gnry=JSONObject.parse(upjsonstr);
		
		JSONObject jsonObject=(JSONObject) JSONObject.toJSON(gnry);
		//取得传送对象
		Object ryInfo = jsonObject.get("ryxx");
		Object auInfo = jsonObject.get("auth");
		JSONArray photoArr = jsonObject.getJSONArray("tpxx");
	
		Object photo1=null;
		Object photo2=null;
		int type=1;
		if(photoArr.size()==2){
		 photo1=photoArr.get(0); //证件照
		 photo2=photoArr.get(1); //现场照
		 type=1;
		}
		if(photoArr.size()==1){
			 photo1=photoArr.get(0); //证件照
			 type=2;
		}
		
		boolean flag=false;
		
		//验证对象是否存在，不存在返回错误
		if (null==auInfo) {
			logger.error(Config.ERROR_NO_AUTH);
			return flag;
		}
		if (null==ryInfo) {
			logger.error(Config.ERROR_NO_RYXX);
			return flag;
		}
		//验证图片是否完整
		if (photoArr.size()==0) {
			logger.error(Config.ERROR_NO_PHOTO);
			return flag;
		}
		
		//取得授权认证信息
		AuthInfo authInfo1=mapper.convertValue(auInfo, AuthInfo.class);
		logger.info("获取授权码："+authInfo1.getAuthCode());
		
		//解析授权码
		String destr=Cryptos.aesDecrypt(authInfo1.getAuthCode());
		String[] strarr=destr.split("&");
		
		//得到人员入住信息
		JiryInfo ryInfo1=mapper.convertValue(ryInfo, JiryInfo.class);
		
		String ryId=ryInfo1.getId();  //人员ID
		String ryName=ryInfo1.getName();  //人员姓名
		String rySex=ryInfo1.getSex();//性别
		String hotelId=ryInfo1.getHotelId();//旅店ID
		//对传送内容进行验证
		if (ryId!=null && ryId.length()!=22) {
			logger.error(Config.ERROR_ID+Config.ERROR_ID_MSG);
			return flag;
		}
		if (!SysCheck.isNumeric(ryId)) {
			logger.error("人员ID必须是数字！");
			return flag;
		}
		if (ryName.isEmpty()) {
			logger.error("人员*姓名*不能为空！");
			return flag;
		}
		if (rySex.isEmpty() || !SysCheck.isNumeric(rySex)) {
			logger.error("人员*性别*不能为空！且必须是数字！");
			return flag;
		}
		if (ryInfo1.getNation().isEmpty()) {
			logger.error("人员*民族*不能为空！");
			return flag;
		}
		String csrq=ryInfo1.getBdate();
		if (csrq.isEmpty() || !SysCheck.isNumeric(csrq)) {
			logger.error("人员*出生日期*不能为空！且必现是数字！");
			return flag;
		}
		String zjzl = ryInfo1.getZjzl();
		String zjhm = ryInfo1.getZjhm();
		if (zjzl.isEmpty() || !SysCheck.isNumeric(zjzl)) {
			logger.error("人员*证件种类*不能为空！且必现是数字！");
			return flag;
		}
		if (zjhm.isEmpty()) {
			logger.error("人员*证件号码*不能为空！");
			return flag;
		}
		if (zjzl.contentEquals("11") && zjhm.length()!=18) {
			logger.error("人员身份证*证件号码*长度必须为18位！");
			return flag;
		}
		String xzqh=ryInfo1.getXzqh();
		if (xzqh.isEmpty()) {
			logger.error("人员*行政区划*不能为空！");
			return flag;
		}else if(!SysCheck.isNumeric(xzqh)){
			logger.error("人员*行政区划*代码必须是数字！");
			return flag;
		}
		//入住时间：yyyyMMddHHmm
		String rzsj=ryInfo1.getInTime();
		if (rzsj.isEmpty()) {
			logger.error("人员*入住时间*不能为空！");
			return flag;
		}else if (!SysCheck.isValidDatet(rzsj)) {
			logger.error("人员*入住时间*格式有误！");
			return flag;
		}
		if (ryInfo1.getNoRoom().isEmpty()) {
			logger.error("人员*入住房间号*不能为空！");
			return flag;
		}
		//add hotel valid
		if (ryInfo1.getHotelId().isEmpty()) {
			logger.error("人员*入住旅店*不能为空！");
			return flag;
		}
		
		if (strarr[0].equals(hotelId) && hotelId.equals(ryId.subSequence(0,10))) {
		    logger.info("正常授权,数据可以解析");
		    String base64str="";
		    String base64str2="";
		    String ryzpid="";
		    String ryzpid2="";
		    if(type==1){ //国内
				//解析图片1
				PhotoInfo zpInfo1=mapper.convertValue(photo1, PhotoInfo.class);
				 base64str=zpInfo1.getPhoto();//取得照片的base64编码
				 ryzpid=zpInfo1.getRyid();
				 if (zpInfo1.getFlag()==null) {
						logger.error("*照片类型*不能为空！");
						return flag;
				 }
				//解析图片2
				PhotoInfo zpInfo2=mapper.convertValue(photo2, PhotoInfo.class);
				 base64str2=zpInfo2.getPhoto();//取得照片的base64编码
				 ryzpid2=zpInfo2.getRyid();
				 if (zpInfo2.getFlag()==null) {
						logger.error("*照片类型*不能为空！");
						return flag;
				}
				 if (ryzpid2.isEmpty()) {
						logger.error("*照片的人员ID*不能为空！");
						return flag;
				 }
				 if (base64str2.isEmpty()) {
						logger.error("*照片数据*不能为空！");
						return flag;
				 }
		    }
		    if(type==2){ //国外
		    	//解析图片1
				PhotoInfo zpInfo1=mapper.convertValue(photo1, PhotoInfo.class);
				 base64str=zpInfo1.getPhoto();//取得照片的base64编码
				 ryzpid=zpInfo1.getRyid();
				 if (zpInfo1.getFlag()==null) {
						logger.error("*照片类型*不能为空！");
						return flag;
				 }
		    }
			
			if (ryzpid.isEmpty() || ryzpid2.isEmpty()) {
				logger.error("*照片的人员ID*不能为空！");
				return flag;
			}
			/**
			if(ryzpid!=ryzpid2){
				logger.error("*照片人员ID不一致！");
				return flag;
			}
			*/
			if (base64str.isEmpty() || base64str2.isEmpty()) {
				logger.error("*照片数据*不能为空！");
				return flag;
			}
		}else {
			logger.error("非法授权,解析错误,数据存在问题，请检查！");
			return flag;
		}
		return !flag;
	}

	/**
	 * 处理国内旅客上传数据
	 * @author LIUCS
	 * @param upstr 要处理的JSON格式数据
	 */
	@Override
	public String handleCIinfo(String upstr) {
		String retMsg="";
		MsgMap msgMap=null;
		//验证格式数据
		if (validateUpinfo(upstr)) {
				logger.info("恭喜！你可以上传数据了^_^");
				
			 	Object gnry=JSONObject.parse(upstr);
				JSONObject jsonObject=(JSONObject) JSONObject.toJSON(gnry);
				
				//取得传送对象
				Object ryInfo = jsonObject.get("ryxx");
				JSONArray photoArr = jsonObject.getJSONArray("tpxx");
				
				JiryInfo jiryInfo=mapper.convertValue(ryInfo, JiryInfo.class);
				String result=jiryService.saveJiry(jiryInfo);
				logger.info("========国内旅客上传结果：==="+result+"=================\r\n");
				boolean b=false;
				String[] imgPaths=new String[2];
				String base64str="";
				String zpryid="";
				//解析图片并上传
				for(int i=0;i<photoArr.size();i++){
					Object photo=photoArr.get(i);
					PhotoInfo zpInfo=mapper.convertValue(photo, PhotoInfo.class);
				
					if (!jiryInfo.getId().equals(zpInfo.getRyid())) {
						logger.error("*照片ID*与人员ID不一致！");
						zpInfo.setRyid(jiryInfo.getId());
					}
					base64str=zpInfo.getPhoto();//取得照片的base64编码
					
					StringBuffer imgpath=new StringBuffer(uppath).append(DateUtils.getDate("yyyyMMdd")).append(File.separator);
					String photoUrl=imgpath.toString().replace("\"", "");
					logger.info("====up photo path====:"+photoUrl);
					
					File file=new File(photoUrl);
					
					StringBuffer sb=new StringBuffer();
					if(!file.exists()){
							file.mkdirs();
					}
				//	imgPathStr=file.getAbsolutePath();
					zpryid=zpInfo.getRyid();//照片人员id
					sb=new StringBuffer(photoUrl);
					sb.append("\\"+(zpryid)).append("-").append(zpInfo.getFlag()).append(".jpg");
					String imgPath=sb.toString();
					logger.info("图片-全路径："+imgPath);
					imgPaths[i]=imgPath;
			}
				for(String imgPath: imgPaths){
					b=Encodes.generateImage(base64str,imgPath);
				}
				if (b) {
					logger.info("生成图片并上传成功！");
					//判断上传图片大小
					/*
					file2=new File(imgPath);
					long imgsize=file2.length();
					logger.info("文件大小："+imgsize+"字节，");
					logger.info("文件大小："+(imgsize/1024)+"KB");
					 */
					//保存到图片表
					PhotoInfo photoInfo=new PhotoInfo(zpryid, null, imgPaths[0],imgPaths[1]);
					int ret=photoService.savePhoto(photoInfo);
					if (ret==1) {
						//生成旅客入住的trm文件
						jiryService.createCITrmFile(jiryInfo);
						msgMap=new MsgMap("OK","旅客照片上传成功！");
					}else{
						msgMap=new MsgMap("ERR02-ZP","旅客照片上传出错！");
					}
			}else {
				logger.error("生成图片错误！");
				msgMap=new MsgMap("ERR01-ZP","生成旅客照片出错！");
			}
				
				
		}else {
			msgMap=new MsgMap("ERR-01", "处理上传数据发生错误！请查看日志！");
			logger.error("数据验证有误！请检查传送的数据是否符合要求！");
		}
		retMsg =JSON.toJSONString(msgMap);
		logger.info(msgMap);
		return retMsg;
	}
	
	/**
	 * 处理国内旅客退宿信息
	 */
	@Override
	public String handleCCinfo(String ccstr) {
		String retjson="";
		Object gnry=JSONObject.parse(ccstr);
		String  jsonstr=JSON.toJSONString(gnry);
		//JSONObject jsonObject=(JSONObject) JSONObject.toJSON(jsonstr);
		JSONObject jsonObject=JSONObject.parseObject(jsonstr);
		MsgMap msgMap=null;
		if(!jsonObject.containsKey("tsxx")){
			logger.error("上传的JSON数据出错！未包含有tsxx的键！");
			msgMap=new MsgMap("ERR01-CC","未找到包含有'tsxx'的键值！");
			retjson=JSON.toJSONString(msgMap);
			return retjson;
		};
		String tsxxStr=jsonObject.getString("tsxx");
		
		JSONObject jsonObject1=JSONObject.parseObject(tsxxStr);
		if(!jsonObject1.containsKey("id")){
			logger.error("上传的JSON数据出错！未包含有id的键！");
			msgMap=new MsgMap("ERR02-CC","未找到包含有'id'的键值！");
			retjson=JSON.toJSONString(msgMap);
			return retjson;
		};
		String idStr=jsonObject1.getString("id");
		if(!jsonObject1.containsKey("tssj")){
			logger.error("上传的JSON数据出错！未包含有tssj的键！");
			msgMap=new MsgMap("ERR03-CC","未找到包含有'tssj'的键值！");
			retjson=JSON.toJSONString(msgMap);
			return retjson;
		};
		String outTimeStr=jsonObject1.getString("tssj");
		
		//进行退宿操作
		JiryInfo jiryInfo=jiryService.getJiryById(idStr);
		
		String csrqStr=jiryInfo.getBdate();
		String csrq=csrqStr.substring(0,10);
		StringBuffer sb=new StringBuffer();
		String csrq1=csrq.substring(0,4);
		String csrq2=csrq.substring(5,7);
		String csrq3=csrq.substring(8,10);
		String csrqformat=sb.append(csrq1).append(csrq2).append(csrq3).toString();
		
		String rzsj=jiryInfo.getInTime();
		
		StringBuffer rzsb=new StringBuffer();
		String rzsj1=rzsj.substring(0,4);
		String rzsj2=rzsj.substring(5,7);
		String rzsj3=rzsj.substring(8,10);
		String rzsj4=rzsj.substring(11,13);
		String rzsj5=rzsj.substring(14,16);
	//	String rzsj6=rzsj.substring(17,19);
		rzsb.append(rzsj1).append(rzsj2).append(rzsj3).append(rzsj4).append(rzsj5);
		String formatRzsj=rzsb.toString();	
		
		RecInfo recInfo=new RecInfo(idStr, jiryInfo.getName(), jiryInfo.getSex(),
				jiryInfo.getNation(), csrqformat, jiryInfo.getZjzl(),jiryInfo.getZjhm(),
				jiryInfo.getXzqh(), jiryInfo.getAddress(),formatRzsj, outTimeStr, jiryInfo.getNoRoom(), 
				jiryInfo.getHotelId(),jiryInfo.getPcs());
			
		String ret=jiryService.saveGnOut(recInfo);
		logger.info("处理退宿返回结果："+ret);
		
		//创建退宿trm文件
		jiryService.createCCTrmFile(recInfo);
		
		//执行退宿处理
		 retjson=jiryService.saveGnOut(recInfo);
		 return retjson;
	}
	String uppath=PropertiesLoader.getUpPath(null).get("photo");
	
	
	/**
	 * 处理境外旅客入住上传数据
	 */
	@Override
	public String handleJIinfo(String jistr) {
		String retMsg="";
		MsgMap msgMap=null;
		//验证格式数据
		if (validateUpinfo(jistr)) {
			logger.info("恭喜！你可以上传数据了^_^");
		 	Object jwry=JSONObject.parse(jistr);
			JSONObject jsonObject=(JSONObject) JSONObject.toJSON(jwry);
			//取得传送对象
			Object ryInfo = jsonObject.get("ryxx");
			JSONArray photoArr = jsonObject.getJSONArray("tpxx");
			
			JwryInfo jwryInfo=mapper.convertValue(ryInfo, JwryInfo.class);
			String result=jwryService.saveJwry(jwryInfo);
			logger.info("保存境外旅客信息成功"+result);
			String ryId=jwryInfo.getId();  //人员ID
		//	String hotelId=jwryInfo.getHotelId();//旅店ID
			
			//解析图片
				Object photo=photoArr.get(0);
				PhotoInfo zpInfo=mapper.convertValue(photo, PhotoInfo.class);
				if (!ryId.equals(zpInfo.getRyid())) {
					logger.error("*照片ID*与人员ID不一致！");
					zpInfo.setRyid(ryId);
				}
				String base64str=zpInfo.getPhoto();//取得照片的base64编码
				
				StringBuffer imgpath=new StringBuffer(uppath).append(DateUtils.getDate("yyyyMMdd")).append(File.separator);
				String photoUrl=imgpath.toString().replace("\"", "");
				logger.info("====up photo path====:"+photoUrl);
				
				File file=new File(photoUrl);
				File file2=null;
				
				StringBuffer sb=new StringBuffer();
				if(!file.exists()){
						file.mkdirs();
				}
			//	imgPathStr=file.getAbsolutePath();
				sb=new StringBuffer(photoUrl);
			    sb.append((zpInfo.getRyid())).append("-").append(zpInfo.getFlag()).append(".jpg");
				
				String imgPath=sb.toString();
				//logger.info("图片全路径："+imgPath);
				boolean b=Encodes.generateImage(base64str,imgPath);
				if (b) {
					logger.info("生成图片并上传成功！");
					//判断上传图片大小
					file2=new File(imgPath);
					long imgsize=file2.length();
					logger.info("文件大小："+imgsize+"字节，");
					//logger.info("文件大小："+(imgsize/1024)+"KB");
					
					//保存到图片表
					PhotoInfo photoInfo=new PhotoInfo(zpInfo.getRyid(), null, imgPath,null);
					int ret=photoService.savePhoto(photoInfo);
					if (ret==1) {
						//生成旅客入住的trm文件
						jwryService.createJITrmFile(jwryInfo);
						msgMap=new MsgMap("OK","境外旅客照片上传成功！");
					}else{
						msgMap=new MsgMap("ERR02-ZP","旅客照片上传出错！");
					}
			}else {
				logger.error("生成图片错误！");
				msgMap=new MsgMap("ERR01-ZP","生成旅客照片出错！");
			}
	}else {
		msgMap=new MsgMap("ERR-01", "处理上传数据发生错误！请查看日志！");
		logger.error("数据验证有误！请检查传送的数据是否符合要求！");
	}
			retMsg =JSON.toJSONString(msgMap);
			logger.info(msgMap);
			return retMsg;
}
	
	/**
	 * 处理境外旅客退宿上传数据
	 */
	@Override
	public String handleJCinfo(String jcstr) {
		Object jwry=JSONObject.parse(jcstr);
		String  jsonstr=JSON.toJSONString(jwry);
		
		JSONObject jsonObject=JSONObject.parseObject(jsonstr);
		String tsxxStr=jsonObject.getString("tsxx");
		
		JSONObject jsonObject1=JSONObject.parseObject(tsxxStr);
		String idStr=jsonObject1.getString("id");
		String tssj=jsonObject1.getString("tssj");
		
		//进行退宿操作
		JwryInfo jwryInfo=jwryService.getJwryById(idStr);
		
		String csrqStr=jwryInfo.getCsrq();
		String csrq=csrqStr.substring(0,10);
		StringBuffer sb=new StringBuffer();
		String csrq1=csrq.substring(0,4);
		String csrq2=csrq.substring(5,7);
		String csrq3=csrq.substring(8,10);
		String csrqformat=sb.append(csrq1).append(csrq2).append(csrq3).toString();
		
		String rzsj=jwryInfo.getRzsj();
		
		StringBuffer rzsb=new StringBuffer();
		String rzsj1=rzsj.substring(0,4);
		String rzsj2=rzsj.substring(5,7);
		String rzsj3=rzsj.substring(8,10);
		String rzsj4=rzsj.substring(11,13);
		String rzsj5=rzsj.substring(14,16);
		rzsb.append(rzsj1).append(rzsj2).append(rzsj3).append(rzsj4).append(rzsj5);
		String formatRzsj=rzsb.toString();	
		
		String tlqz=jwryInfo.getTlyxqz();
		StringBuffer tlqzsb=new StringBuffer();
		String tlqz1=tlqz.substring(0,4);
		String tlqz2=tlqz.substring(5,7);
		String tlqz3=tlqz.substring(8,10);
		String tlqzformat=tlqzsb.append(tlqz1).append(tlqz2).append(tlqz3).toString();
		//入境日期
		String rjrq=jwryInfo.getRjrq();
		StringBuffer rjrqsb=new StringBuffer();
		String rjrq1=rjrq.substring(0,4);
		String rjrq2=rjrq.substring(5,7);
		String rjrq3=rjrq.substring(8,10);
		String rjrqformat=rjrqsb.append(rjrq1).append(rjrq2).append(rjrq3).toString();
		
		JwryInfo recInfo=new JwryInfo(jwryInfo.getId(),
				jwryInfo.getYwx(),jwryInfo.getYwm(), jwryInfo.getZwxm(), 
				jwryInfo.getSex(), csrqformat,jwryInfo.getGjdq(),
				jwryInfo.getZjzl(), jwryInfo.getZjhm(), jwryInfo.getQzzl(),
				jwryInfo.getQzhm(),tlqzformat,jwryInfo.getQfd(),
				rjrqformat,jwryInfo.getRjka(), formatRzsj,
				jwryInfo.getFjhm(),jwryInfo.getLyd(),jwryInfo.getQwd(),
				jwryInfo.getTlsy(),jwryInfo.getHotelId(),tssj, jwryInfo.getPcs());
		String result="";
		try{
			result=jwryService.saveJwOut(recInfo);
			logger.info("境外退宿返回："+result);
			jwryService.createJCTrmFile(recInfo);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return result;
	}
}
