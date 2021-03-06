package com.csl.ws.hotel.test;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.csl.ws.hotel.dao.JwryDao;
import com.csl.ws.hotel.po.JwryInfo;
import com.csl.ws.hotel.service.JwryService;

public class TestJwry {

	ApplicationContext ctx=null;
	JwryDao jwryDao=null;
	JwryService jwryService=null;
	Logger logger=Logger.getLogger(TestJwry.class);
	
	@Before
	public void setUp() throws Exception {
		ctx=new ClassPathXmlApplicationContext("beans.xml");
		System.out.println("context:"+ctx);
		
		jwryDao=(JwryDao) ctx.getBean("jwryDao");
		jwryService=(JwryService) ctx.getBean("jwryService");
		System.out.println("jwryservice:"+jwryService);
		//jwryDao=(JwryDao) ctx.getBean("jwryDao");
		
	}

	

	@Test
	public void testSelectJwry(){
		String id="4101052039160530232614";
		JwryInfo jwry=jwryService.getJwryById(id);
		//System.out.println(jwry);
		logger.info(jwry);
	}
	
	
	
	/**
	 * 测试境外退宿
	 */
	@Test
	public void testCheckOut(){
		String outjson="{"
				+ "\"tsxx\":{\"id\":\"4101052039160530232615\",\"tssj\":\"201802090958\"}"
				+ "}";
		Object jwry=JSONObject.parse(outjson);
		String  jsonstr=JSON.toJSONString(jwry);
//		System.out.println(jsonstr);
		
		JSONObject jsonObject=JSONObject.parseObject(jsonstr);
		String tsxxStr=jsonObject.getString("tsxx");
		
		JSONObject jsonObject1=JSONObject.parseObject(tsxxStr);
		String idStr=jsonObject1.getString("id");
		String tssj=jsonObject1.getString("tssj");
		System.out.println("退宿时间："+tssj);
		
		//进行退宿操作
		JwryInfo jwryInfo=jwryService.getJwryById(idStr);
		
		String csrqStr=jwryInfo.getCsrq();
		String csrq=csrqStr.substring(0,10);
		StringBuffer sb=new StringBuffer();
		String csrq1=csrq.substring(0,4);
		String csrq2=csrq.substring(5,7);
		String csrq3=csrq.substring(8,10);
		String csrqformat=sb.append(csrq1).append(csrq2).append(csrq3).toString();
		System.out.println("格式化出生日期："+csrqformat);
		
		String rzsj=jwryInfo.getRzsj();
		System.out.println("入住时间："+rzsj);
		
		StringBuffer rzsb=new StringBuffer();
		String rzsj1=rzsj.substring(0,4);
		String rzsj2=rzsj.substring(5,7);
		String rzsj3=rzsj.substring(8,10);
		String rzsj4=rzsj.substring(11,13);
		String rzsj5=rzsj.substring(14,16);
		rzsb.append(rzsj1).append(rzsj2).append(rzsj3).append(rzsj4).append(rzsj5);
		String formatRzsj=rzsb.toString();	
		System.out.println("格式化入住时间："+formatRzsj);
		
		String tlqz=jwryInfo.getTlyxqz();
		StringBuffer tlqzsb=new StringBuffer();
		String tlqz1=tlqz.substring(0,4);
		String tlqz2=tlqz.substring(5,7);
		String tlqz3=tlqz.substring(8,10);
		String tlqzformat=tlqzsb.append(tlqz1).append(tlqz2).append(tlqz3).toString();
		System.out.println("格式化停留期："+tlqzformat);
		//
		String rjrq=jwryInfo.getRjrq();
		System.out.println("入境日期："+rjrq);
		StringBuffer rjrqsb=new StringBuffer();
		String rjrq1=tlqz.substring(0,4);
		String rjrq2=tlqz.substring(5,7);
		String rjrq3=tlqz.substring(8,10);
		String rjrqformat=rjrqsb.append(rjrq1).append(rjrq2).append(rjrq3).toString();
		System.out.println("格式化入境日期："+rjrqformat);
		
		JwryInfo recInfo=new JwryInfo(jwryInfo.getId(),
				jwryInfo.getYwx(),jwryInfo.getYwm(), jwryInfo.getZwxm(), 
				jwryInfo.getSex(), csrqformat,jwryInfo.getGjdq(),
				jwryInfo.getZjzl(), jwryInfo.getZjhm(), jwryInfo.getQzzl(),
				jwryInfo.getQzhm(),tlqzformat,jwryInfo.getQfd(),
				rjrqformat,jwryInfo.getRjka(), formatRzsj,
				jwryInfo.getFjhm(),jwryInfo.getLyd(),jwryInfo.getQwd(),
				jwryInfo.getTlsy(),jwryInfo.getHotelId(),tssj, jwryInfo.getPcs());
			
		System.out.println(recInfo);
		String ret=jwryService.saveJwOut(recInfo);
		System.out.println("退宿返回："+ret);
		jwryService.createJCTrmFile(recInfo);
	}
	/**
	 * 测试境外入住
	 */
	@Test
	public void testInsert() {
		/*
		 * ID,YWX,YWM,ZWXM,SEX,XBCODE,CSRQ,GJDQ,ZJZL,ZJHM,QZZL,QZHM,QFD,TLYXQZ,
		   RJRQ,RJKA,RZSJ,FH,LYD,QWD,TLSY,HOTELID,PCS,RKSJ
		 * */
		//入住JSON
		String jsonStr="{"
				+ "\"ryxx\":{\"id\":\"4101052039160530232615\",\"ywx\":\"TOM\",\"ywm\":\"crusy\",\"zwxm\":\"汤姆克鲁斯\","
				+ "\"sex\":\"1\",\"gjdq\":\"USA\", \"csrq\":\"19760809\",\"zjzl\":\"14\","
				+ "\"zjhm\":\"214356744948\",\"qzzl\":\"L\",\"qzhm\":\"K3615890\","
				+ "\"tlyxqz\":\"20180216\",\"qfd\":\"AUSA\",\"rjrq\":\"20180205\",\"rjka\":\"024\","
				+ "\"rzsj\":\"201802052034\",\"fjhm\":\"7708\","
				+ "\"lyd\":\"310115\",\"qwd\":\"410100\",\"tlsy\":\"05\","
				+ "\"hotelId\":\"4101052039\",\"pcs\":\"1521\"},"
				+ "\"auth\":{\"hotelId\":\"4101052039\","
					+ "\"authCode\":\"4eb863f30e1778f97269374e4ceab17b197d6911c4a3185b460857c9ec773585\"},"
				+ "\"tpxx\": {"
					+ "\"ryid\":\"4101052039160530232648\","
					+ "\"photo\": \"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"
					+ "\","
					+ "\"flag\": 1}"
				+ "}";
		JSON jwry=JSONObject.parseObject(jsonStr);
		System.out.println(jwry);
		JSONObject jsonObject=(JSONObject) JSONObject.toJSON(jwry);
		JSONObject jObject=jsonObject.getJSONObject("ryxx");
		
		System.out.println("ryxx:"+jObject);
		
		Object ryInfo = jsonObject.get("ryxx");
		Object auInfo = jsonObject.get("auth");
		Object zpInfo = jsonObject.get("tpxx");
		
	//	System.out.println("auInfo======:"+auInfo);
		System.out.println("境外人员：==="+ryInfo);
		
		ObjectMapper mapper=new ObjectMapper();
		JwryInfo ryInfo1=mapper.convertValue(ryInfo, JwryInfo.class);
		System.out.println("境外数据："+ryInfo1);
		
		String rsult=jwryService.saveJwry(ryInfo1);
		System.out.println("保存数据："+rsult);
		
		//创建境内入住trm文件
		jwryService.createJITrmFile(ryInfo1);
	}
	
	/**
	 * 
	 */
	@Test
	public void testJson2Obj(){
		String jsonStr="{\"id\":\"4101050091160530232638\",\"xsn\":\"JACK\",\"chName\":\"江贵成\","
				+ "\"national\":\"TWN\",\"sex\":1,\"zjzl\":\"14\"}";
		JSON json=JSONObject.parseObject(jsonStr);
		JwryInfo jwInfo=JSONObject.toJavaObject(json, JwryInfo.class);
		
		System.out.println(jwInfo);
	}
	
	@Test
	public void testUpdate(){
	}
	@After
	public void tearDown() throws Exception {
		
	}
}
