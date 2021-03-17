package com.oyzc.boot.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

// @WebListener
public class MyServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("监听到项目初始化完成");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("监听到项目销毁");
    }
}
