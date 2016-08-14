package com.roger.springcloud.hystrix.dashboard;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * Created by chenluo on 2016/8/14.
 */
@SpringCloudApplication
//dashboard 必须是一个单独的应用，略坑爹
@EnableHystrixDashboard
public class DashBoardApp {

    public static void main(String[] args) {
        new SpringApplicationBuilder(DashBoardApp.class).web(true).run(args);
    }
}
