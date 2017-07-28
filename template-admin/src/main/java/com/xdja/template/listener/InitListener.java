package com.xdja.template.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.xdja.template.util.ConfigUtil;

public class InitListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {
	}

	public void contextInitialized(ServletContextEvent arg0) {
		ConfigUtil.init("/system.properties");
	}

}
