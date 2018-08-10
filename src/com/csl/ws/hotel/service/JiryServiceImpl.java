package com.csl.ws.hotel.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.csl.ws.hotel.dao.JiryDao;
import com.csl.ws.hotel.po.JiryInfo;
import com.csl.ws.hotel.po.RecInfo;
import com.csl.ws.hotel.util.MsgMap;
import com.csl.ws.hotel.util.PropertiesLoader;
import com.csl.ws.hotel.util.StringUtils;

/**
 * 服务实现
 * @author LIUCS
 *
 */
@Service
public class JiryServiceImpl implements JiryService {
	Logger logger=Logger.getLogger(JiryServiceImpl.class);
	@Autowired
	private JiryDao jiryDao;
	
	public void setJiryDao(JiryDao jiryDao) {
		this.jiryDao = jiryDao;
	}
	
	@Override
	public String saveJiry(JiryInfo jiInfo) {
		int ret=0;
		MsgMap msgMap=null;
		JiryInfo jiry=jiryDao.selectById(jiInfo.getId());
		if (null==jiry) {
			ret=jiryDao.insertJiry(jiInfo);
			if (ret>0) {
				msgMap=new MsgMap("OK", "旅客保存成功！");
			}else {
				msgMap=new MsgMap("ERR", "旅客保存失败！");
			}
		}else {
			msgMap=new MsgMap("REJ", "旅客已存在！");
		}
		
		String retJson=JSONObject.toJSONString(msgMap);
		return retJson;
	}

	@Override
	public JiryInfo getJiryById(String id) {
		
		return jiryDao.selectById(id);
	}

	/**
	 * 旅客退宿
	 */
	@Override
	public String saveGnOut(RecInfo recInfo) {
		int ret=0;
		MsgMap msgMap=null;
		if (null==recInfo) {
			msgMap=new MsgMap("ERR", "没有要退宿的旅客！");
		}else {
			//退宿操作:save to t_gn_rec
			ret=jiryDao.insertJiry4Out(recInfo);
			if (ret==1) {
				jiryDao.deleteJiryById(recInfo.getId()); //delete from t_gn_pre by id
				msgMap=new MsgMap("OK", "退宿旅客成功！"+ret);
			}
		}
		String retJson=JSONObject.toJSONString(msgMap);
		return retJson;
	}
	Map<String, String> mappath=PropertiesLoader.getUpPath(null);
	String trmPath=mappath.get("trm");
	/**
	 * 生成CI TRM 文件
	 */
	@Override
	public String createCITrmFile(JiryInfo ciInfo) {
		logger.debug("==============开始生成trm包====================");
			String guestid=ciInfo.getId();
			String name = "@CI" + guestid.substring(4, 22);
			
			String trm_Path=trmPath+name+".trm";
			logger.info("trm-path:"+trm_Path.replace("\"", ""));
			File file = new File(trm_Path.replace("\"", ""));
			FileOutputStream fos;
			try {
				fos = new FileOutputStream(file);
				fos.write("<?xml version=\"1.0\"?>".getBytes());
				fos.write("<root>".getBytes());
				fos.write("<fType>".getBytes());
				fos.write("H43H49".getBytes());
				fos.write("</fType>".getBytes());
				fos.write("<guestsId>".getBytes());
				fos.write(StringUtils.tohex(guestid).getBytes());
				fos.write("</guestsId>".getBytes());

				fos.write("<guestsName>".getBytes());
				fos.write(StringUtils.tohex(ciInfo.getName()).getBytes());
				fos.write("</guestsName>".getBytes());
				fos.write("<guestsSex>".getBytes());
				fos.write(StringUtils.tohex(ciInfo.getSex()).getBytes());
				fos.write("</guestsSex>".getBytes());
				fos.write("<guestsNation>".getBytes());
				fos.write(StringUtils.tohex(ciInfo.getNation()).getBytes());
				fos.write("</guestsNation>".getBytes());
				fos.write("<guestsBirthD>".getBytes());
				fos.write(StringUtils.tohex(ciInfo.getBdate()).getBytes());
				fos.write("</guestsBirthD>".getBytes());
				fos.write("<credentials>".getBytes());
				fos.write(StringUtils.tohex(ciInfo.getZjzl()).getBytes());
				fos.write("</credentials>".getBytes());
				fos.write("<credentialsNo>".getBytes());
				fos.write(StringUtils.tohex(ciInfo.getZjhm()).getBytes());
				fos.write("</credentialsNo>".getBytes());
				fos.write("<guestsCity>".getBytes());
				fos.write(StringUtils.tohex(ciInfo.getXzqh()).getBytes());
				fos.write("</guestsCity>".getBytes());
				fos.write("<guestsAddress>".getBytes());
				fos.write(StringUtils.tohex(ciInfo.getAddress()).getBytes());
				fos.write("</guestsAddress>".getBytes());
				fos.write("<guestsRoomNo>".getBytes());
				fos.write(StringUtils.tohex(ciInfo.getNoRoom()).getBytes());
				fos.write("</guestsRoomNo>".getBytes());
				fos.write("<guestsPym>".getBytes());
				fos.write("</guestsPym>".getBytes());
				fos.write("<creditCode>".getBytes());
				fos.write("</creditCode>".getBytes());
				fos.write("<creditNo>".getBytes());
				fos.write("</creditNo>".getBytes());
				fos.write("<groupNo>".getBytes());
				fos.write("</groupNo>".getBytes());
				fos.write("<dragomaName>".getBytes());
				fos.write("</dragomaName>".getBytes());
				fos.write("<dragomanPhone>".getBytes());
				fos.write("</dragomanPhone>".getBytes());
				fos.write("<memo>".getBytes());
				fos.write("</memo>".getBytes());
				fos.write("<guestsRInTime>".getBytes());
				fos.write(StringUtils.tohex(ciInfo.getInTime()).getBytes());
				fos.write("</guestsRInTime>".getBytes());
				fos.write("<opreator>".getBytes());
				fos.write("</opreator>".getBytes());
				fos.write("<guestsExitTime>".getBytes());
				fos.write("</guestsExitTime>".getBytes());
				fos.write("<stayF>".getBytes());
				fos.write("</stayF>".getBytes());
				fos.write("<photo>".getBytes());
				fos.write("</photo>".getBytes());
				fos.write("</root>".getBytes());
				fos.flush();
				fos.close();

				logger.debug(name+ "============= 国内入住生成trm结束=============");
			} catch (Exception e) {
				logger.debug("没有找到相关的文件");
			}
		return "create trm file ok!";
	}

	/**
	 * 生成退宿TRM文件
	 */
	@Override
	public void createCCTrmFile(RecInfo recInfo) {
		logger.debug("===========开始生成国内退宿trm包=============");
		String ccid=recInfo.getId();
		String name = "@CC" + ccid.substring(4, 22);

		String trm_Path=trmPath+name+".trm";
		File file = new File(trm_Path.replace("\"", ""));
		
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(file);
			fos.write("<?xml version=\"1.0\"?>".getBytes());
			fos.write("<root>".getBytes());
			fos.write("<fType>".getBytes());
			fos.write("H43H43".getBytes());
			fos.write("</fType>".getBytes());
			fos.write("<guestsId>".getBytes());
			fos.write(StringUtils.tohex(ccid).getBytes());
			fos.write("</guestsId>".getBytes());
			fos.write("<guestsExitTime>".getBytes());
			fos.write(StringUtils.tohex(recInfo.getTssj()).getBytes());
			fos.write("</guestsExitTime>".getBytes());
			fos.write("</root>".getBytes());
			fos.close();
			logger.debug(name+ "============国内退宿生成trm结束=================");

		} catch (Exception e) {
			logger.debug("国内退宿--没有找到相关的文件==检查存放文件路径");
		}
	}

}
