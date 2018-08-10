package com.csl.ws.server;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.handler.Handler;

import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

import com.csl.ws.hotel.service.HandlerService;
import com.csl.ws.hotel.service.HandlerServiceImpl;
import com.csl.ws.hotel.service.HotelService;
import com.csl.ws.hotel.service.HotelServiceImpl;
import com.csl.ws.sei.GnUpServer;
import com.csl.ws.sei.GnUpServerImpl;

public class PublicService {

	/**
	public PublicService() {
		 System.out.println("Starting Server");
	        GnUpServer implementor = new GnUpServerImpl();
	        JaxWsServerFactoryBean svrFactory = new JaxWsServerFactoryBean();

	        svrFactory.setServiceClass();
	        svrFactory.setAddress("http://127.0.0.1:8088/ws/gnup");
	        svrFactory.setServiceBean(implementor);
	        svrFactory.getInInterceptors().add(new LoggingInInterceptor());
	        svrFactory.getOutInterceptors().add(new LoggingOutInterceptor());
	        svrFactory.create();
	}
	 * @throws InterruptedException 
	*/
	public static void main(String[] args) throws InterruptedException {
		    new PublicService();
	        System.out.println("Server ready...");

	        Thread.sleep(5 * 60 * 1000);
	        System.out.println("Server exiting");
	        System.exit(0);
	}

}
