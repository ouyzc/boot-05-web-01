package com.oyzc.boot.config;

import com.oyzc.boot.interceptor.LoginInterceptor;
import com.oyzc.boot.interceptor.RedisUrlCountInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置类配置拦截器
 */
@Configuration
public class AdminWebConfig implements WebMvcConfigurer {

    RedisUrlCountInterceptor redisUrlCountInterceptor;

    @Autowired
    public void setRedisUrlCountInterceptor(RedisUrlCountInterceptor redisUrlCountInterceptor) {
        this.redisUrlCountInterceptor = redisUrlCountInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/","/login","/js/**","/images/**","/css/**");

        registry.addInterceptor(redisUrlCountInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/","/login","/js/**","/images/**","/css/**");
    }
}
