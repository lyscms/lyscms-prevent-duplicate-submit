package com.lyscms.assembly.config;

import com.lyscms.assembly.interceptor.PreventRepeatInterceptor;
import com.lyscms.assembly.support.identifier.IdempotentUniquenessHandler;
import com.lyscms.assembly.support.identifier.impl.DefaultIdempotentUniquenessHandlerImpl;
import com.lyscms.assembly.support.lock.LockHandler;
import com.lyscms.assembly.support.lock.impl.DefaultLockHandlerImpl;
import com.lyscms.assembly.support.qualifier.PreventQualifierHandler;
import com.lyscms.assembly.support.response.ResponseHandler;
import com.lyscms.assembly.support.response.impl.DefaultResponseHandlerImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class PreventRepeatConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册自定义拦截器，添加拦截路径和排除拦截路径
        registry.addInterceptor(preventRepeatInterceptor()).addPathPatterns("/**");
    }

    @Bean
    public PreventQualifierHandler preventQualifierHandler() {
        return new PreventQualifierHandler();
    }

    @Bean
    public PreventRepeatInterceptor preventRepeatInterceptor() {
        return new PreventRepeatInterceptor();
    }

    @ConditionalOnMissingBean
    @Bean
    public IdempotentUniquenessHandler uniquenessHandler() {
        return new DefaultIdempotentUniquenessHandlerImpl();
    }

    @ConditionalOnMissingBean
    @Bean
    public LockHandler lockHandler() {
        return new DefaultLockHandlerImpl();
    }

    @ConditionalOnMissingBean
    @Bean
    public ResponseHandler responseHandler() {
        return new DefaultResponseHandlerImpl();
    }
}
