package com.resourceserver2.example.config;

import feign.Feign;
import feign.RequestInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;

/**
 * Created by chenluo on 2016/12/14.
 */
@Configuration
@ConditionalOnClass({ Feign.class })
@ConditionalOnProperty(value = "feign.oauth2.enabled", matchIfMissing = true)
//@AutoConfigureAfter(ResourceServerConfiguration.class)
public class OAuth2FeignAutoConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(OAuth2FeignAutoConfiguration.class);

    @Bean
//    @ConditionalOnBean(OAuth2ClientContext.class)
    @Autowired
    public RequestInterceptor oauth2FeignRequestInterceptor(OAuth2ClientContext oauth2ClientContext) {
        LOGGER.info("%%%%%%%%%%%%%");
        return new OAuth2FeignRequestInterceptor(oauth2ClientContext);
    }
}