package com.oyzc.boot.servlet;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.servlet.Servlet;
import java.util.Arrays;
import java.util.EventListener;

@Configuration
public class MyRegistrationConfig {

    @Bean
    public ServletRegistrationBean<Servlet> myServlet() {
        MyServlet myServlet = new MyServlet();
        return new ServletRegistrationBean<>(myServlet,"/servlet1","/servlet2");
    }

    @Bean
    public FilterRegistrationBean<Filter> myFilter() {
        MyFilter myFilter = new MyFilter();
        // 使用这种方式过滤myServlet方法定义的Servlet请求
        // return new FilterRegistrationBean<>(myFilter,myServlet());
        // 也可以使用这个方法自定义需要过滤的请求
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>(myFilter);
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/js/*","/images/*"));
        return filterRegistrationBean;
    }

    @Bean
    public ServletListenerRegistrationBean<EventListener> myListener() {
        MyServletContextListener myServletContextListener = new MyServletContextListener();
        return new ServletListenerRegistrationBean<>(myServletContextListener);
    }
}
