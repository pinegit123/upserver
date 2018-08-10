package com.csl.ws.hotel.test;

import java.io.File;
import java.text.ParseException;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.csl.ws.hotel.dao.JiryDao;
import com.csl.ws.hotel.po.AuthInfo;
import com.csl.ws.hotel.po.JiryInfo;
import com.csl.ws.hotel.po.PhotoInfo;
import com.csl.ws.hotel.po.RecInfo;
import com.csl.ws.hotel.service.HandlerService;
import com.csl.ws.hotel.service.JiryService;
import com.csl.ws.hotel.service.PhotoService;
import com.csl.ws.hotel.util.Config;
import com.csl.ws.hotel.util.Cryptos;
import com.csl.ws.hotel.util.DateUtils;
import com.csl.ws.hotel.util.Encodes;
import com.csl.ws.hotel.util.SysCheck;

public class JiryTest {

	ApplicationContext ctx=null;
	JiryDao jiryDao=null;
	JiryService jiryService=null;
	PhotoService photoService=null;
	HandlerService handlerService=null;
	Logger logger=Logger.getLogger(JiryTest.class);
	@Before
	public void setUp() throws Exception {
		ctx=new ClassPathXmlApplicationContext("beans.xml");
		photoService=(PhotoService) ctx.getBean("photoService");
		jiryDao=(JiryDao) ctx.getBean("jiryDao");
		jiryService=(JiryService) ctx.getBean("jiryService");
		handlerService=(HandlerService) ctx.getBean("handlerService");
		System.out.println("handlerService:=====>"+handlerService);
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testHandService(){
		System.out.println(handlerService);
		String upjsonStr = "{"
				+ "\"tsxx\":{\"id\":\"4101030292160530232635\",\"tssj\":\"201802191028\"}"
				+ "}";
		handlerService.handleCCinfo(upjsonStr);
	}
	@Test
	public void testSelectJiry(){
		String id="4101026003160530232647";
		JiryInfo jiry=jiryService.getJiryById(id);
		//System.out.println(jwry);
		logger.info("旅客姓名："+jiry.getName());
		
		jiryService.createCITrmFile(jiry);
	}
	
	//退宿
	@Test
	public void testCheckout() throws ParseException{
		String upjsonStr = "{"
				+ "\"tsxx\":{\"id\":\"4101030292160530232642\",\"tssj\":\"201802091028\"}"
				+ "}";
		Object gnry=JSONObject.parse(upjsonStr);
		String  jsonstr=JSON.toJSONString(gnry);
//		System.out.println(jsonstr);
		
		JSONObject jsonObject=JSONObject.parseObject(jsonstr);
		String tsxxStr=jsonObject.getString("tsxx");
		
		JSONObject jsonObject1=JSONObject.parseObject(tsxxStr);
		String idStr=jsonObject1.getString("id");
//		System.out.println(idStr);
		String outTimeStr=jsonObject1.getString("tssj");
		System.out.println("退宿时间："+outTimeStr);
		
		//进行退宿操作
		JiryInfo jiryInfo=jiryService.getJiryById(idStr);
		
		String csrqStr=jiryInfo.getBdate();
		String csrq=csrqStr.substring(0,10);
		StringBuffer sb=new StringBuffer();
		String csrq1=csrq.substring(0,4);
		String csrq2=csrq.substring(5,7);
		String csrq3=csrq.substring(8,10);
		String csrqformat=sb.append(csrq1).append(csrq2).append(csrq3).toString();
		System.out.println("格式化出生日期："+csrqformat);
		
		String rzsj=jiryInfo.getInTime();
		System.out.println("入住时间："+rzsj);
		
		StringBuffer rzsb=new StringBuffer();
		String rzsj1=rzsj.substring(0,4);
		String rzsj2=rzsj.substring(5,7);
		String rzsj3=rzsj.substring(8,10);
		String rzsj4=rzsj.substring(11,13);
		String rzsj5=rzsj.substring(14,16);
	//	String rzsj6=rzsj.substring(17,19);
		rzsb.append(rzsj1).append(rzsj2).append(rzsj3).append(rzsj4).append(rzsj5);
		String formatRzsj=rzsb.toString();	
		System.out.println("格式化入住时间："+formatRzsj);
		
		RecInfo recInfo=new RecInfo(idStr, jiryInfo.getName(), jiryInfo.getSex(),
				jiryInfo.getNation(), csrqformat, jiryInfo.getZjzl(),jiryInfo.getZjhm(),
				jiryInfo.getXzqh(), jiryInfo.getAddress(),formatRzsj, outTimeStr, jiryInfo.getNoRoom(), 
				jiryInfo.getHotelId(),jiryInfo.getPcs());
			
		System.out.println(recInfo);
		String ret=jiryService.saveGnOut(recInfo);
		System.out.println("退宿返回："+ret);
		jiryService.createCCTrmFile(recInfo);
	}
	@Test
	public void testInsert() {
		/*
		 ID,XM,XBCODE,MZ,CSRQ,ZJZLCODE,ZJHM,XZQH,XXDZ,RZSJ,FJHM,DJSJ,
		  RKSJ,HOTELID,PCS,TRANSFERFLAG
		  pcs: select * from t_xpcs for update  -410185650000,410184790000
		 * */
		String jsonStr="{"
				+ "\"ryxx\":{\"id\":\"4101052039160530232615\",\"name\":\"许晴\","
				+ "\"sex\":\"1\",\"nation\":\"HA\", \"bdate\":\"19780809\",\"zjzl\":\"11\","
				+ "\"zjhm\":\"410119714356744938\",\"xzqh\":\"410105\",\"address\":\"郑州市金水区东风路蓝宝万144\","
				+ "\"inTime\":\"201801072034\",\"noRoom\":\"6608\","
				+ "\"traTime\":\"\","
				+ "\"hotelId\":\"4101052039\",\"pcs\":\"1521\"},"
				+ "\"auth\":{\"hotelId\":\"4101052039\","
					+ "\"authCode\":\"4eb863f30e1778f97269374e4ceab17b197d6911c4a3185b460857c9ec773585\"},"
				+ "\"tpxx\": [{"
					+ "\"ryid\":\"4101052039160530232648\","
					+ "\"photo\": \"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"
					+ "\","
					+ "\"flag\": 1},"
					+ "{"
							+ "\"ryid\":\"4101052039160530232648\","
							+ "\"photo\": \"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"
							+ "\","
							+ "\"flag\": 2}"
				+ "]}";
		
		Object gnry=JSONObject.parse(jsonStr);
	//	String  jsonstr=JSON.toJSONString(gnry);
		
		JSONObject jsonObject=(JSONObject) JSONObject.toJSON(gnry);
		JSONObject jObject=jsonObject.getJSONObject("ryxx");
		System.out.println("ryxx:"+jObject);
		
		
	//	String rsult=jiryService.saveJiry(jiInfo);
		Object ryInfo = jsonObject.get("ryxx");
		Object auInfo = jsonObject.get("auth");
		Object zpInfo = jsonObject.get("tpxx");
		System.out.println("auInfo======:"+auInfo);
		ObjectMapper mapper=new ObjectMapper();
		
		String[] strarr=null;
		if (null==auInfo) {
			logger.error(Config.ERROR_NO_AUTH);
			return;
		}
		if (null==ryInfo) {
			logger.error(Config.ERROR_NO_RYXX);
			return;
		}
		if (null==zpInfo) {
			logger.error(Config.ERROR_NO_PHOTO);
			return;
		}
		
		AuthInfo authInfo1=mapper.convertValue(auInfo, AuthInfo.class);
		logger.info("获取授权码："+authInfo1.getAuthCode());
		//解析授权码
		String destr=Cryptos.aesDecrypt(authInfo1.getAuthCode());
		strarr=destr.split("&");
		
		JiryInfo ryInfo1=mapper.convertValue(ryInfo, JiryInfo.class);
		
		String ryId=ryInfo1.getId();  //人员ID
		String ryName=ryInfo1.getName();  //人员姓名
		String rySex=ryInfo1.getSex();//性别
		String hotelId=ryInfo1.getHotelId();//旅店ID
		if (ryId!=null && ryId.length()!=22) {
			logger.error(Config.ERROR_ID+Config.ERROR_ID_MSG);
			return;
		}
		if (!SysCheck.isNumeric(ryId)) {
			logger.error("人员ID必须是数字！");
			return;
		}
		if (ryName.isEmpty()) {
			logger.error("人员*姓名*不能为空！");
			return;
		}
		if (rySex.isEmpty() || !SysCheck.isNumeric(rySex)) {
			logger.error("人员*性别*不能为空！且必须是数字！");
			return;
		}
		if (ryInfo1.getNation().isEmpty()) {
			logger.error("人员*民族*不能为空！");
			return;
		}
		String csrq=ryInfo1.getBdate();
		if (csrq.isEmpty() || !SysCheck.isNumeric(csrq)) {
			logger.error("人员*出生日期*不能为空！且必现是数字！");
			return;
		}
		String zjzl = ryInfo1.getZjzl();
		String zjhm = ryInfo1.getZjhm();
		if (zjzl.isEmpty() || !SysCheck.isNumeric(zjzl)) {
			logger.error("人员*证件种类*不能为空！且必现是数字！");
			return;
		}
		if (zjhm.isEmpty() || !SysCheck.isNumeric(zjhm) || zjhm.length()!=18) {
			logger.error("人员*证件号码*不能为空！且必现是数字，长度为18位！");
			return;
		}
		String xzqh=ryInfo1.getXzqh();
		if (xzqh.isEmpty()) {
			logger.error("人员*行政区划*不能为空！");
			return;
		}else if(!SysCheck.isNumeric(xzqh)){
			logger.error("人员*行政区划*代码必须是数字！");
			return;
		}
		//入住时间：yyyyMMddHHmm
		String rzsj=ryInfo1.getInTime();
		if (rzsj.isEmpty()) {
			logger.error("人员*入住时间*不能为空！");
			return;
		}else if (!SysCheck.isValidDatet(rzsj)) {
			logger.error("人员*入住时间*格式有误！");
			return;
		}
		if (ryInfo1.getNoRoom().isEmpty()) {
			logger.error("人员*入住房间号*不能为空！");
			return;
		}
		//add hotel valid
		if (ryInfo1.getHotelId().isEmpty()) {
			logger.error("人员*入住旅店*不能为空！");
			return;
		}
		
		//System.out.println(strarr[0]+":"+strarr[1]);
		if (strarr[0].equals(hotelId) && hotelId.equals(ryId.subSequence(0,10))) {
		    logger.info("正常授权,可以解析");
		   //验证数据规范并保存
			jiryService.saveJiry(ryInfo1);
			logger.info("保存国内旅客信息成功");
			
			//解析图片
			PhotoInfo zpInfo1=mapper.convertValue(zpInfo, PhotoInfo.class);
			String base64str=zpInfo1.getPhoto();//取得照片的base64编码
			StringBuffer imgpath=new StringBuffer("f:/upimage/");
			imgpath.append(DateUtils.getDate("yyyyMMdd")).append(File.separator);
			
			String photoUrl=imgpath.toString();
			logger.info("图片上传路径："+photoUrl);
			
			File file=new File(photoUrl);
			File file2=null;
		//	System.out.println(file.exists());
			
			StringBuffer sb=new StringBuffer();
			String imgPathStr=null;
			if(!file.exists()){
					file.mkdirs();
			}
			imgPathStr=file.getAbsolutePath();
			sb=new StringBuffer(imgPathStr);
		    sb.append("\\"+(zpInfo1.getRyid())).append(".jpg");
			
			String imgPath=sb.toString();
			logger.info("图片全路径："+imgPath);
			boolean b=Encodes.generateImage(base64str,imgPath);
			if (b) {
				logger.info("生成图片并上传成功！");
				//判断上传图片大小
				file2=new File(imgPath);
				long imgsize=file2.length();
				logger.info("文件大小："+imgsize+"字节，");
				logger.info("文件大小："+(imgsize/1024)+"KB");
				
				//保存到图片表
				PhotoInfo photoInfo=new PhotoInfo(zpInfo1.getRyid(), null, imgPath,null);
				photoService.savePhoto(photoInfo);
				
				logger.info("保存图片成功！");
			}else {
				logger.error("生成图片错误！");
			}
		}else {
			logger.error("非法授权,解析错误");
			return;
		}
	}
	
	
	@Test
	public void testJson2Obj(){
		String jsonStr="{\"id\":\"4101050091160530232638\",\"xsn\":\"JACK\",\"chName\":\"江贵成\","
				+ "\"national\":\"TWN\",\"sex\":1,\"zjzl\":\"14\"}";
		JSON json=JSONObject.parseObject(jsonStr);
		JiryInfo jiInfo=JSONObject.toJavaObject(json, JiryInfo.class);
		
		System.out.println(jiInfo);
	}
	
	@Test
	public void testUpdate(){
	}

}
