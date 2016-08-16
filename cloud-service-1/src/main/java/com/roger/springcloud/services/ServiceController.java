package com.roger.springcloud.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chenluo on 2016/8/14.
 */
@RestController
//启用远程配置刷新功能
@RefreshScope
public class ServiceController {

    @Value("${jdbc.url:default}")
    private String url;

    @RequestMapping("/url")
    @HystrixCommand(fallbackMethod = "defaultStores")
    public String showUrl(){
        return url;
    }

    public String defaultStores(){
        return "defaultStores";
    }
}
