package com.cloud.feign.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * Feign基础配置类
 */
@Slf4j
public class BaseFeignConfig {

    /**
     * 注入Feign 拦截器
     */
    @Bean
    @ConditionalOnMissingBean(FeignRequestInterceptor.class)
    public RequestInterceptor feignRequestInterceptor() {
        FeignRequestInterceptor interceptor = new FeignRequestInterceptor();
        log.info("FeignRequestInterceptor [{}]", interceptor);
        return interceptor;
    }

    /**
     * 自定义 Feign请求拦截器
     */
    public static class FeignRequestInterceptor implements RequestInterceptor {
        @Override
        public void apply(RequestTemplate template) {

            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (servletRequestAttributes != null) {
                HttpServletRequest request = (servletRequestAttributes).getRequest();

                //  传递发起调用客户端的所有请求头
                Enumeration<String> headerNames = request.getHeaderNames();
                if (headerNames != null) {
                    String key;
                    while (headerNames.hasMoreElements()) {
                        key = headerNames.nextElement();
                        if (key.equalsIgnoreCase("content-length") || key.equalsIgnoreCase("content-type")) {
                            continue;
                        }
                        template.header(key, request.getHeader(key));
                    }
                }
                // 传递发起调用客户端的所有请求参数
                Enumeration<String> parameterNames = request.getParameterNames();
                if (parameterNames != null) {
                    String key;
                    while (parameterNames.hasMoreElements()) {
                        key = parameterNames.nextElement();
                        template.query(key, request.getParameter(key));
                    }
                }
            }
        }
    }
}