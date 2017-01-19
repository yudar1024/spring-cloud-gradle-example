package com.resourceserver.jdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


/**
 * Created by chenluo on 2017/1/19.
 */
@SpringBootApplication
@RestController
@EnableResourceServer
public class ResourceServerJdbc {
    @RequestMapping("/")
    private String home() {
        return UUID.randomUUID().toString();
    }

    public static void main(String[] args) {
        SpringApplication.run(ResourceServerJdbc.class, args);
    }
}
