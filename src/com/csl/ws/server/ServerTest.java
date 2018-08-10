package com.csl.ws.server;

import javax.xml.ws.Endpoint;

import com.csl.ws.sei.GnUpServerImpl;
import com.csl.ws.sei.JwUpServerImpl;

/**
 * 发布web service
 * @author Administrator
 *
 */
public class ServerTest {

	public static void main(String[] args) {
//		String address="http://61.163.108.195:8088/upws/hotelws";
		String address="http://127.0.0.1:8088/ws/gnup";
		Endpoint.publish(address, new GnUpServerImpl());
		System.out.println("发布 国内 webservice 成功！");
		
		String address1="http://127.0.0.1:8089/ws/jwup";
		Endpoint.publish(address1, new JwUpServerImpl());
		
		System.out.println("发布 境外 webservice 成功！");
	}
}
