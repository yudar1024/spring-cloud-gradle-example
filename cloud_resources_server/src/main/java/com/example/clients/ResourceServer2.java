package com.example.clients;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

/**
 * Created by chenluo on 2016/12/14.
 */
@FeignClient(value = "res-server-2")
public interface ResourceServer2 {

    @RequestMapping(method = RequestMethod.GET,value = "/foo" )
    public String readFoo();
}
