package com.roger.springcloud.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chenluo on 2016/8/14.
 */
@RestController
public class ServiceController {

    @Value("${jdbc.url:default}")
    private String url;

    @Autowired
    Tracer tracer;

    @RequestMapping("/url")
    @HystrixCommand(fallbackMethod = "defaultStores")
    public String showUrl(){
        tracer.addTag("service2","excuete service 2");
        return url;
    }

    @RequestMapping(value = "/entity", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Vo2 postVO( @RequestBody Vo1 vo){
        System.out.println("entity service 2");
        return vo.getVo2();
    }


    public String defaultStores(){
        return "defaultStores";
    }
}
