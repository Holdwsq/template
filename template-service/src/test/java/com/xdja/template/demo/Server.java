package com.xdja.template.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Server {
	
	public static Logger log = LoggerFactory.getLogger(Server.class);
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new ClassPathXmlApplicationContext("classpath:applicationContext-dao.xml", "classpath:applicationContext-server.xml");
		log.info("server start...");
	}

}
