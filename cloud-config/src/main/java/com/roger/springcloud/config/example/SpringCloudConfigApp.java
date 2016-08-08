package com.roger.springcloud.config.example;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Created by chenluo on 2016/8/8.
 */
@SpringCloudApplication
@EnableConfigServer
public class SpringCloudConfigApp {

    public static void main( String[] args )
    {

        SpringApplication.run(SpringCloudConfigApp.class, args);
    }
}
