package com.oyzc.boot.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Collections;

// @Configuration
public class MyDataSourceConfig {

    @ConfigurationProperties("spring.datasource")
    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        try {
            //加入监控功能和防火墙
            //这样的话sql的执行时间加上了监控消耗的时间，如果要去除监控消耗的时间，只需要调换一下位置：wall,stat
            dataSource.setFilters("stat,wall");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return dataSource;
    }

    /**
     * 配置druid的监控页
     * @return ServletRegistrationBean
     */
    @Bean
    public ServletRegistrationBean<StatViewServlet> druidStatView() {
        StatViewServlet statViewServlet = new StatViewServlet();
        ServletRegistrationBean<StatViewServlet> servletRegistrationBean = new ServletRegistrationBean<>(statViewServlet, "/druid/*");
        //配置监控页面访问密码
        servletRegistrationBean.addInitParameter("loginUsername","admin");
        servletRegistrationBean.addInitParameter("loginPassword","123456");
        //StatViewServlet展示出来的监控信息比较敏感，是系统运行的内部情况，如果你需要做访问控制，可以配置allow和deny这两个参数。
        //deny优先于allow，如果在deny列表中，就算在allow列表中，也会被拒绝。
        //如果allow没有配置或者为空，则允许所有访问
        servletRegistrationBean.addInitParameter("allow","192.168.1.3");  //允许访问的IP
        servletRegistrationBean.addInitParameter("deny","192.168.1.3");  //禁止访问的IP
        return servletRegistrationBean;
    }

    /**
     * Web关联监控配置
     * @return FilterRegistrationBean
     */
    @Bean
    public FilterRegistrationBean<WebStatFilter> webStatFilter() {
        WebStatFilter webStatFilter = new WebStatFilter();
        FilterRegistrationBean<WebStatFilter> filterRegistrationBean = new FilterRegistrationBean<>(webStatFilter);
        //配置监控哪些请求
        filterRegistrationBean.setUrlPatterns(Collections.singletonList("/*"));
        //排除掉一些不需要监控的请求
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}
