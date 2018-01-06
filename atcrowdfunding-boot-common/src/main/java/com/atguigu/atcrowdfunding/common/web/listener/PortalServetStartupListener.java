package com.atguigu.atcrowdfunding.common.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.atguigu.atcrowdfunding.common.util.Const;


@WebListener
public class PortalServetStartupListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//1.获取上下文路径存放在application域中
		ServletContext application = sce.getServletContext();
		String contextPath = application.getContextPath();
		application.setAttribute(Const.APP_PATH, contextPath);
		System.out.println(contextPath);
		
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}
