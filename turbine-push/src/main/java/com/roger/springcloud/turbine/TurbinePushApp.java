package com.roger.springcloud.turbine;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.turbine.stream.EnableTurbineStream;

/**
 * Created by chenluo on 2016/8/14.
 */
@SpringCloudApplication
@EnableTurbineStream
public class TurbinePushApp {
    public static void main(String[] args) {
        SpringApplication.run(TurbinePushApp.class, args);
    }
}
