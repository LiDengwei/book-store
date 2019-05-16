package com.book.web.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 添加拦截器
 */
@Configuration
@Order(value = 2)
public class WebAppConfigurer extends WebMvcConfigurerAdapter {

    @Bean
    public SecurityInterceptor securityInterceptor(){
        return new SecurityInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(securityInterceptor())
                .excludePathPatterns("/webjars/**")
                .excludePathPatterns("/v2/**")
                .excludePathPatterns("/swagger-resources")
                .excludePathPatterns("/configuration/**")
                .excludePathPatterns("/error")
                .addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
