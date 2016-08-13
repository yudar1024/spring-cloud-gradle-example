package com.roger.springcloud.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by chenluo on 2016/8/8.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaServer
public class RegistryServer2 {

    public static void main(String args[]){
        SpringApplication.run(RegistryServer2.class,args);
    }
}
