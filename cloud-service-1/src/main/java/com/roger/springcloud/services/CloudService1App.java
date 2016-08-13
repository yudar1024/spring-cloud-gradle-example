package com.roger.springcloud.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chenluo on 2016/8/8.
 */
@SpringCloudApplication
@RestController
public class CloudService1App {

    @Value("${jdbc.url}")
    private String url;

    public static void main(String [] args){
        SpringApplication.run(CloudService1App.class,args);
    }

    @RequestMapping("/url")
    public String showUrl(){
        return url;
    }


}
