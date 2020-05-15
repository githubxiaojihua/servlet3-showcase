/**
 * Copyright (c) 2005-2012 https://github.com/zhangkaitao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.sishuok.chapter2.web.listener;

import com.sishuok.chapter2.web.filter.DynamicFilter;
import com.sishuok.chapter2.web.servlet.DynamicServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebListener;

/**
 * 动态注册listener,filter以及servlet
 */
@WebListener
public class DynamicInitListener implements ServletContextListener {

    /**
     * 需要在容器的时候，通过监听器来初始化。
     * 通过ServletContext来注册
     * @param sce
     */
    @Override
    public void contextInitialized(final ServletContextEvent sce) {
        // 获得servletContext
        ServletContext sc = sce.getServletContext();
        // 注册listener
        //sc.addListener("com.sishuok.chapter2.web.listener.DynamicServletContextListener");
        // 注册filter
        sc.addFilter("dynamicFilter", DynamicFilter.class);
        // 注册Servlet方式1
        ServletRegistration.Dynamic dynamic1 = sc.addServlet("dynamicServlet1", DynamicServlet.class);
        dynamic1.setLoadOnStartup(1);
        dynamic1.addMapping("/dynamic1");
        // 注册Servlet方式2
        ServletRegistration.Dynamic dynamic2 = sc.addServlet("dynamicServlet2", "com.sishuok.chapter2.web.servlet.DynamicServlet");
        dynamic2.addMapping("/dynamic2");
        // 注册Servlet方式3
        ServletRegistration.Dynamic dynamic3 = sc.addServlet("dynamicServlet3", new DynamicServlet());
        dynamic3.addMapping("/dynamic3");

    }

    @Override
    public void contextDestroyed(final ServletContextEvent sce) {
    }
}
