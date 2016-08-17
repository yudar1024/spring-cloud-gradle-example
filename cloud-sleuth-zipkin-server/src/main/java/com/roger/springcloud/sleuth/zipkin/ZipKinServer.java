package com.roger.springcloud.sleuth.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.zipkin.stream.EnableZipkinStreamServer;

/**
 * Created by chenluo on 2016/8/17.
 */
@SpringBootApplication
@EnableZipkinStreamServer
public class ZipKinServer {

    public static void main(String [] args){
        SpringApplication.run(ZipKinServer.class,args);
    }
}
