package com.roger.springcloud.services;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chenluo on 2016/8/8.
 */
@SpringCloudApplication
@RestController

//启用hystrix stream ，可以在此服务的url(localhost:9101)后加/hystrix.stream 才看stream 流的数据，完整URL=localhost:9101/hystrix.stream.此stream 只搜集被@HystrixCommand 注解标注的方法的调用情况
@EnableHystrix
public class CloudService1App {


    public static void main(String [] args){
        SpringApplication.run(CloudService1App.class,args);
    }






}
