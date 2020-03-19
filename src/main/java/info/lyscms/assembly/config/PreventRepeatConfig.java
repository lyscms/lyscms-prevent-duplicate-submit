package info.lyscms.assembly.config;

import info.lyscms.assembly.interceptor.PreventRepeatInterceptor;
import info.lyscms.assembly.support.identifier.IdempotentUniquenessHandler;
import info.lyscms.assembly.support.identifier.impl.DefaultIdempotentUniquenessHandlerImpl;
import info.lyscms.assembly.support.lock.LockHandler;
import info.lyscms.assembly.support.lock.impl.DefaultLockHandlerImpl;
import info.lyscms.assembly.support.qualifier.PreventQualifierHandler;
import info.lyscms.assembly.support.response.ResponseHandler;
import info.lyscms.assembly.support.response.impl.DefaultResponseHandlerImpl;
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
