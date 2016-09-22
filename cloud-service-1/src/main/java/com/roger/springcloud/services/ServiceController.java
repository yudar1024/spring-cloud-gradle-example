package com.roger.springcloud.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;

/**
 * Created by chenluo on 2016/8/14.
 */
@RestController
//启用远程配置刷新功能
@RefreshScope
public class ServiceController {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Value("${jdbc.url:default}")
    private String url;

    @Autowired
    @LoadBalanced
    private RestTemplate restTemplate;

    @Autowired
    Tracer tracer;

    @RequestMapping("/url")
    @HystrixCommand(fallbackMethod = "defaultStores")
    public String showUrl(){
        tracer.addTag("trace1","test service 1");


        String url1=restTemplate.getForObject("http://cloud-service-2/url", String.class);
        return url+" "+url1;
    }

    @RequestMapping("/entity")
    public String entity(){

        System.out.println("entity service 1");
        Vo1 vo1= new Vo1();
        vo1.setAge(1);
        vo1.setUserName("tttt");
        Vo2 vo2 = new Vo2();
        vo2.setField1("field1");
        vo2.setField2("field2");
        vo1.setVo2(vo2);
        vo1.setVolist(new ArrayList<>());
        vo2= new Vo2();
        vo2.setField1("22");
        vo2.setField2("33");
        vo1.getVolist().add(vo2);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<Vo1> httpEntity = new HttpEntity<>(vo1,headers);
//        ResponseEntity<Vo2> responseEntity=restTemplate.exchange("http://cloud-service-2/entity", HttpMethod.POST,httpEntity,  new ParameterizedTypeReference<Vo2>() {
//        });
//        return responseEntity.getBody().getField1();
        vo2=restTemplate.postForObject("http://cloud-service-2/entity", vo1,Vo2.class);
        return vo2.getField1();

    }




    public String defaultStores(){
        return "defaultStores";
    }


}
