package com.roger.springcloud.services;

import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.BestAvailableRule;
import com.netflix.loadbalancer.IPing;
import com.netflix.niws.loadbalancer.NIWSDiscoveryPing;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.sleuth.Sampler;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by chenluo on 2016/8/8.
 */
@SpringCloudApplication
@RestController

//启用hystrix stream ，可以在此服务的url(localhost:9101)后加/hystrix.stream 才看stream 流的数据，完整URL=localhost:9101/hystrix.stream.此stream 只搜集被@HystrixCommand 注解标注的方法的调用情况
@EnableHystrix
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableAsync
public class CloudService1App {


    public static void main(String [] args){
        SpringApplication.run(CloudService1App.class,args);
    }


    // 配置RestTemplate 启用Ribbon 的lb， 在IOC 中，需要时用LB的RestTemplate 在字段上添加@LoadBalanced
    @LoadBalanced
    @Bean
    RestTemplate loadBalanced() {
        return new RestTemplate();
    }

    // 配置 正常的RestTemplate @Primary 用于消除歧义
    @Primary
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    //配置检测服务方式
    @Bean
    public IPing getNIWSDiscoveryPing(){
        return new NIWSDiscoveryPing();
    }
    //load balance 的策略。
    @Bean
    public AbstractLoadBalancerRule getAbstractLoadBalancerRule(){
        return new BestAvailableRule();
    }


    @Bean
    Sampler sampler() {
        return new AlwaysSampler();
    }




}
