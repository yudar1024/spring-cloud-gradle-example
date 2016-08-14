package com.roger.springcloud.services;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * Created by chenluo on 2016/8/8.
 */
@SpringCloudApplication
//启用hystrix stream ，可以在此服务的url(localhost:9101)后加/hystrix.stream 才看stream 流的数据，完整URL=localhost:9101/hystrix.stream.此stream 只搜集被@HystrixCommand 注解标注的方法的调用情况
@EnableHystrix
public class CloudService2App {



    public static void main(String [] args){
        SpringApplication.run(CloudService2App.class,args);
    }



}
