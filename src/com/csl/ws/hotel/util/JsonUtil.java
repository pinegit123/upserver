package com.csl.ws.hotel.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
/**
 * json 工具类
 * @author liucs
 * 
 */
public class JsonUtil {

	public JsonUtil() {
	}
	
	/**
	 * 实现字符串转换为json数据格式
	 * @param str 格式为："id=0dc78df03b204296800bb42c0b28afc3&sn=822491080210041&name=荥阳京城王宁牙科诊所"
	 * @return json 字符串格式
	 * @author liucs
	 */
	public static String parseStrToJson(String str){
		String[] strArr=str.split("&");
		StringBuffer sb=new StringBuffer("{");
		for(String str1:strArr){
			String jsonstr="\""+str1.split("=")[0]+"\":\""+str1.split("=")[1]+"\"";
			sb.append(jsonstr).append(",");
		}
		String retstr=sb.toString();
		String newstr=retstr.substring(0,retstr.length()-1);
		String retJson=newstr+"}";
		try {
			retJson=URLDecoder.decode(retJson, "UTF-8");
			System.out.println(retJson);
			//String newjson=retJson.replace("%5B%5D", "");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			System.err.println("解析字符串出错！");
		}
		return retJson;
	}

}
