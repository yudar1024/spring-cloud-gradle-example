package com.example.config;

import feign.Feign;
import feign.RequestInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import sun.rmi.runtime.Log;

/**
 * Created by chenluo on 2016/12/14.
 */
@Configuration
@ConditionalOnClass({ Feign.class })
@ConditionalOnProperty(value = "feign.oauth2.enabled", matchIfMissing = true)
public class OAuth2FeignAutoConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(OAuth2FeignAutoConfiguration.class);

    @Bean
//    @ConditionalOnBean(OAuth2ClientContext.class)
    public RequestInterceptor oauth2FeignRequestInterceptor(OAuth2ClientContext oauth2ClientContext) {
        LOGGER.info("%%%%%%%%%%%%%");
//        return new OAuth2FeignRequestInterceptor(oauth2ClientContext);
        return new OAuth2FeignRequestInterceptor(oauth2ClientContext);
    }


}