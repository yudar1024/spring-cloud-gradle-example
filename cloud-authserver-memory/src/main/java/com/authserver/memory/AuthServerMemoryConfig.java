package com.authserver.memory;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * Created by chenluo on 2017/1/20.
 */
@Configuration
@EnableResourceServer
@EnableAuthorizationServer
public class AuthServerMemoryConfig {
}
