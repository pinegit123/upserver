package com.csl.ws.hotel.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.csl.ws.hotel.po.JwryInfo;
import com.csl.ws.hotel.service.JwryService;

public class App {

	public static void main(String[] args) {
		ApplicationContext ctx=new ClassPathXmlApplicationContext("beans.xml");
		System.out.println(ctx);
		JwryService jwryService=(JwryService) ctx.getBean("jwryService");
		System.out.println(jwryService);
		String id="4101050091160530232641";
		JwryInfo jwry=jwryService.getJwryById(id);
		System.out.println(jwry);
	}
}
