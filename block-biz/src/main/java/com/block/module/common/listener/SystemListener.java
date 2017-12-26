package com.block.module.common.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.block.core.module.quartzjob.service.QuartzCache;
import com.block.module.common.enums.FontCache;


/**
 * 系统配置加载监听器
 */
public class SystemListener implements  ServletRequestListener, ServletContextListener, HttpSessionListener{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	QuartzCache quartzCache =null;
	public void contextDestroyed(ServletContextEvent arg0) {
		if(null!=quartzCache)
			quartzCache.destroy();
	}

	public void contextInitialized(ServletContextEvent serletContext) {
		//初始化缓存
		WebApplicationContext app = WebApplicationContextUtils.getWebApplicationContext(serletContext.getServletContext());
		// 启动定时任务
		quartzCache = (QuartzCache) app.getBean("quartzCache");
		quartzCache.start();
		logger.info("容器启动");
		
		FontCache fontCache=(FontCache)app.getBean("fontCache");
		fontCache.refresh();
	}
 

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void requestInitialized(ServletRequestEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
