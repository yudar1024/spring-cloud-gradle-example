package com.zuul.auth.example;

import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.BestAvailableRule;
import com.netflix.loadbalancer.IPing;
import com.netflix.niws.loadbalancer.NIWSDiscoveryPing;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

/**
 * Created by chenluo on 2016/12/9.
 */

@SpringCloudApplication
@EnableZuulProxy
@EnableOAuth2Client
@Import(ResourceServerConfiguration.class)
public class ZuulAuthApp {
    public static void main(String[] args){
        new SpringApplicationBuilder(ZuulAuthApp.class).web(true).run(args);
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
