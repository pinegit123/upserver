package com.csl.ws.server;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.JettyWebXmlConfiguration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.webapp.WebXmlConfiguration;

public class Server {
	 protected Server() throws Exception {
	        System.out.println("Starting Server");

	        /**
	         * Important: This code simply starts up a servlet container and adds
	         * the web application in src/webapp to it. Normally you would be using
	         * Jetty or Tomcat and have the webapp packaged as a WAR. This is simply
	         * as a convenience so you do not need to configure your servlet
	         * container to see CXF in action!
	         */
	        org.eclipse.jetty.server.Server server = new  org.eclipse.jetty.server.Server();

	        SelectChannelConnector connector = new SelectChannelConnector();
	        connector.setPort(9002);
	        server.setConnectors(new Connector[] {connector});

	        WebAppContext webappcontext = new WebAppContext();
	       Configuration conf=new JettyWebXmlConfiguration();
	       Configuration conf1=new WebXmlConfiguration();
	       conf.configure(webappcontext);
	       Configuration[] confs={conf,conf1};
	       webappcontext.setConfigurations(confs);
	     //   webappcontext.setContextPath("/");

	       // webappcontext.setWar("target/JavaFirstSpringSupport.war");

	        HandlerCollection handlers = new HandlerCollection();
	        handlers.setHandlers(new Handler[] {new DefaultHandler()});
	        server.setHandler(handlers.getHandlers()[0]);
	      // server.setHandler(handlers);
//	        server.start();
	        System.out.println("Server ready...");
	        server.join();
	    }
	 
	public static void main(String[] args) {

	}

}
