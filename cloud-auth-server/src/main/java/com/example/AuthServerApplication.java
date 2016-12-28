package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@SpringBootApplication
//@SpringCloudApplication
public class AuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthServerApplication.class, args);
    }

    @Bean(name = "basicAuthenticationFilter")
    public BasicAuthenticationFilter myBasicAuthenticationFilter(@Autowired AuthenticationManager authenticationManager) {
        MyBasicAuthenticationFilter2 myBasicAuthenticationFilter2= new MyBasicAuthenticationFilter2(authenticationManager);
        return myBasicAuthenticationFilter2;

    }
}
