package com.roger.springcloud.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.lang.invoke.MethodHandles;

/**
 * Created by chenluo on 2016/8/14.
 */
@RestController
//启用远程配置刷新功能
@RefreshScope
public class ServiceController {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Value("${jdbc.url:default}")
    private String url;

    @Autowired
    @LoadBalanced
    private RestTemplate restTemplate;

    @Autowired
    Tracer tracer;

    @RequestMapping("/url")
    @HystrixCommand(fallbackMethod = "defaultStores")
    public String showUrl(){
        tracer.addTag("trace1","test service 1");
        String url1=restTemplate.getForObject("http://cloud-service-2/url", String.class);
        return url+" "+url1;
    }

    public String defaultStores(){
        return "defaultStores";
    }


}
