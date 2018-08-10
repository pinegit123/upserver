package com.csl.ws.hotel.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.csl.ws.hotel.dao.JwryDao;
import com.csl.ws.hotel.po.JwryInfo;
import com.csl.ws.hotel.util.MsgMap;
import com.csl.ws.hotel.util.PropertiesLoader;
import com.csl.ws.hotel.util.StringUtils;

/**
 * 境外服务
 * @author liucs
 *
 */
@Service
public class JwryServiceImpl implements JwryService {
	Logger logger=Logger.getLogger(JwryServiceImpl.class);
	@Autowired
	private JwryDao jwryDao;
	
	public void setJwryDao(JwryDao jwryDao) {
		this.jwryDao = jwryDao;
	}

	/**
	 * save JwryInfo
	 */
	@Override
	public String saveJwry(JwryInfo jwInfo) {
		String count=jwryDao.insertJwry(jwInfo)+"";
		return count;
	}

	
	/**
	 * get one JwryInfo by id
	 */
	@Override
	public JwryInfo getJwryById(String id) {
		JwryInfo jwry=jwryDao.selectById(id);
		return jwry;
	}

	/**
	 * save JwryInfo out
	 */
	@Override
	public String saveJwOut(JwryInfo jwInfo) {
		
		int ret=0;
		MsgMap msgMap=null;
		if (null==jwInfo) {
			msgMap=new MsgMap("ERR", "没有要退宿的旅客！");
		}else {
			//退宿操作:save to t_gn_rec
			ret=jwryDao.insertJwry4Out(jwInfo);
			if (ret==1) {
				jwryDao.deleteJwryById(jwInfo.getId()); //delete from t_jw_pre by id
				msgMap=new MsgMap("OK", "境外旅客退宿成功！"+ret);
			}
		}
		String retJson=JSONObject.toJSONString(msgMap);
		return retJson;
	}
	Map<String, String> mappath=PropertiesLoader.getUpPath(null);
	String trmPath=mappath.get("trm");
	/**
	 * create JI trm file
	 */
	@Override
	public String createJITrmFile(JwryInfo jwInfo) {
		
		logger.debug("==============开始生成trm包====================");
		String guestid=jwInfo.getId();
		String name = "@JI" + guestid.substring(4, 22);

		String trm_Path=trmPath+name+".trm";
		logger.info("trm-path:"+trm_Path.replace("\"", ""));
		File file = new File(trm_Path.replace("\"", ""));

		FileOutputStream fos;
		try {
			fos = new FileOutputStream(file);
			fos.write("<?xml version=\"1.0\"?>".getBytes());
			fos.write("<root>".getBytes());
			fos.write("<fType>".getBytes());
			fos.write("H4AH49".getBytes());
			fos.write("</fType>".getBytes());
			fos.write("<guestsId>".getBytes());
			fos.write(StringUtils.tohex(guestid).getBytes());
			fos.write("</guestsId>".getBytes());

			fos.write("<guestsXSN>".getBytes());
			fos.write(StringUtils.tohex(jwInfo.getYwx()).getBytes());
			fos.write("</guestsXSN>".getBytes());

			fos.write("<guestsMFN>".getBytes());
			fos.write(StringUtils.tohex(jwInfo.getYwm()).getBytes());
			fos.write("</guestsMFN>".getBytes());

			fos.write("<guestsCHNN>".getBytes());
			fos.write(StringUtils.tohex(jwInfo.getZwxm()).getBytes());
			fos.write("</guestsCHNN>".getBytes());

			fos.write("<guestsSex>".getBytes());
			fos.write(StringUtils.tohex(jwInfo.getSex()).getBytes());
			fos.write("</guestsSex>".getBytes());

			fos.write("<guestsBirthD>".getBytes());
			fos.write(StringUtils.tohex(jwInfo.getCsrq()).getBytes());
			fos.write("</guestsBirthD>".getBytes());

			fos.write("<guestsCountry>".getBytes());
			fos.write(StringUtils.tohex(jwInfo.getGjdq()).getBytes());
			fos.write("</guestsCountry>".getBytes());

			fos.write("<credentials>".getBytes());
			fos.write(StringUtils.tohex(jwInfo.getZjzl()).getBytes());
			fos.write("</credentials>".getBytes());

			fos.write("<credentialsNo>".getBytes());
			fos.write(StringUtils.tohex(jwInfo.getZjhm()).getBytes());
			fos.write("</credentialsNo>".getBytes());

			fos.write("<visaT>".getBytes());
			fos.write(StringUtils.tohex(jwInfo.getQzzl()).getBytes());
			fos.write("</visaT>".getBytes());

			fos.write("<visaNo>".getBytes());
			fos.write(StringUtils.tohex(jwInfo.getQzhm()).getBytes());
			fos.write("</visaNo>".getBytes());

			fos.write("<stayDate>".getBytes());
			fos.write(StringUtils.tohex(jwInfo.getTlyxqz()).getBytes());
			fos.write("</stayDate>".getBytes());

			fos.write("<signDepartment>".getBytes());
			fos.write("H7E".getBytes());
			fos.write("</signDepartment>".getBytes());

			fos.write("<enterDate>".getBytes());
			fos.write(StringUtils.tohex(jwInfo.getRjrq()).getBytes());
			fos.write("</enterDate>".getBytes());

			fos.write("<enterPort>".getBytes());
			fos.write(StringUtils.tohex(jwInfo.getRjka()).getBytes());
			fos.write("</enterPort>".getBytes());

			fos.write("<receiveUnit>".getBytes());
			// /接待单位
			fos.write("H7E".getBytes());
			fos.write("</receiveUnit>".getBytes());

			fos.write("<arriveTime>".getBytes());
			fos.write(StringUtils.tohex(jwInfo.getRjrq()).getBytes());// 跟in_time相同
			fos.write("</arriveTime>".getBytes());

			fos.write("<guestsRoomNo>".getBytes());
			fos.write(StringUtils.tohex(jwInfo.getFjhm()).getBytes());
			fos.write("</guestsRoomNo>".getBytes());

			fos.write("<place>".getBytes());
			fos.write(StringUtils.tohex(jwInfo.getLyd()).getBytes());
			fos.write("H2D".getBytes());
			fos.write(StringUtils.tohex(jwInfo.getQwd()).getBytes());// 何处来去
			fos.write("</place>".getBytes());

			fos.write("<guestsPym>".getBytes());
			fos.write("H7E".getBytes());
			fos.write("</guestsPym>".getBytes());
			fos.write("<creditNo>".getBytes());
			fos.write("H7E".getBytes());
			fos.write("</creditNo>".getBytes());

			fos.write("<groupNo>".getBytes());
			fos.write("</groupNo>".getBytes());

			fos.write("<dragomaName>".getBytes());
			fos.write("H7E".getBytes());
			fos.write("</dragomaName>".getBytes());
			fos.write("<dragomanPhone>".getBytes());
			fos.write("H7E".getBytes());
			fos.write("</dragomanPhone>".getBytes());
			fos.write("<memo>".getBytes());
			fos.write("</memo>".getBytes());

			fos.write("<guestsRInTime>".getBytes());
			fos.write(StringUtils.tohex(jwInfo.getRzsj()).getBytes());
			fos.write("</guestsRInTime>".getBytes());

			fos.write("<reason>".getBytes());
			fos.write(StringUtils.tohex(jwInfo.getTlsy()).getBytes());
			// /停留事由
			fos.write("</reason>".getBytes());

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
			logger.debug(name+ "===========境外入住生成trm结束===========");
			
			return "create JI trm file ok!";
		} catch (Exception e) {
			logger.debug("生成trm文件出错"+e.getMessage());
		}
		return null;
	}

	/**
	 * create CI trm file
	 */
	@Override
	public void createJCTrmFile(JwryInfo jwInfo) {
		logger.debug("===========开始生成境外退宿trm包=============");
		String jcid=jwInfo.getId();
		String name = "@JC" + jcid.substring(4, 22);

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
			fos.write(StringUtils.tohex(jcid).getBytes());
			fos.write("</guestsId>".getBytes());
			fos.write("<guestsExitTime>".getBytes());
			fos.write(StringUtils.tohex(jwInfo.getTssj()).getBytes());
			fos.write("</guestsExitTime>".getBytes());
			fos.write("</root>".getBytes());
			fos.close();
			logger.debug(name+ "============境外退宿生成trm结束=================");

		} catch (Exception e) {
			logger.debug("境外退宿trm文件生成错误："+e.getMessage());
		}
	}
}
