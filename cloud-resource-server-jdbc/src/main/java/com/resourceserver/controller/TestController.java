package com.resourceserver.controller;

import com.resourceserver.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by chenluo on 2017/1/23.
 */
@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping("/")
    private String home() {
       return testService.getUUID();
    }

    @RequestMapping("/test")
    private String test() {
        return testService.getTesterUUID();
    }

    @PreAuthorize("hasRole(\"admin\")")
    private String admin() {
        return testService.getadminUUID();
    }
}
