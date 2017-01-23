package com.resourceserver.jdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import java.util.UUID;


/**
 * Created by chenluo on 2017/1/19.
 */
@SpringBootApplication
@EnableResourceServer
//prePostEnabled 启用spring express的语法进行访问限制
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan(basePackages={"com.resourceserver"})
//@ComponentScan(basePackages = {"com.resourceserver.controller"},useDefaultFilters = false,includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,classes ={Controller.class,RestController.class, ControllerAdvice.class, RestControllerAdvice.class})})
//@ComponentScan(basePackages = {"com.resourceserver.service"},excludeFilters = {@ComponentScan.Filter(type =FilterType.ANNOTATION,classes ={Controller.class,RestController.class, ControllerAdvice.class, RestControllerAdvice.class})} )
public class ResourceServerJdbc {


    public static void main(String[] args) {
        SpringApplication.run(ResourceServerJdbc.class, args);
    }
}
