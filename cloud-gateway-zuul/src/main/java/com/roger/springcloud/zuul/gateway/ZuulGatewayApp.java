package com.roger.springcloud.zuul.gateway;

import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.BestAvailableRule;
import com.netflix.loadbalancer.IPing;
import com.netflix.niws.loadbalancer.NIWSDiscoveryPing;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * Created by chenluo on 2016/8/15.
 */
@SpringCloudApplication
@EnableZuulProxy

public class ZuulGatewayApp {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ZuulGatewayApp.class).web(true).run(args);
    }

    @Bean
    public IPing getNIWSDiscoveryPing(){
        return new NIWSDiscoveryPing();
    }
    //load balance 的策略。
    @Bean
    public AbstractLoadBalancerRule getAbstractLoadBalancerRule(){
        return new BestAvailableRule();
    }
}
