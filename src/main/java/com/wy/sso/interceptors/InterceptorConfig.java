package com.wy.sso.interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author wangyu
 * @title: InterceptorConfig
 * @projectName WyUpms
 * @description: 拦截器配置类
 * @date 2020/4/22 13:10
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private RquestInterceptor rquestInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(rquestInterceptor);
    }
}
