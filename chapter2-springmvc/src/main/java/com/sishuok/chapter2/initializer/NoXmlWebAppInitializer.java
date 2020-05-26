/**
 * Copyright (c) 2005-2012 https://github.com/zhangkaitao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.sishuok.chapter2.initializer;

import com.sishuok.chapter2.config.RootConfiguration;
import com.sishuok.chapter2.config.SpringMvcConfiguration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 13-6-21 下午9:23
 * <p>Version: 1.0
 */
public class NoXmlWebAppInitializer implements WebApplicationInitializer {

    /**
     * 将spring配置，springmvc配置放到了两个配置类里面进行加载
     * 分别加载spring根容器和springmvc容器
     * 然后还在dispatcherservlet
     * @param sc
     * @throws ServletException
     */
    @Override
    public void onStartup(final ServletContext sc) throws ServletException {

        //1、注册根上下文
        AnnotationConfigWebApplicationContext  rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(RootConfiguration.class);
        sc.addListener(new ContextLoaderListener(rootContext));

        //2、注册springmvc上下文
        AnnotationConfigWebApplicationContext springMvcContext = new AnnotationConfigWebApplicationContext();
        springMvcContext.register(SpringMvcConfiguration.class);

        //3、注册DispatcherServlet
        DispatcherServlet dispatcherServlet = new DispatcherServlet(springMvcContext);

        ServletRegistration.Dynamic dynamic = sc.addServlet("dispatcherServlet", dispatcherServlet);
        dynamic.setLoadOnStartup(1);
        dynamic.addMapping("/");

    }
}
