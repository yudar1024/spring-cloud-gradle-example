package com.authserver.memory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by chenluo on 2017/1/19.
 */
@SpringBootApplication
@RestController
public class MemoryAuthServer {
    public static void main(String[] args) {
        SpringApplication.run(MemoryAuthServer.class, args);
    }

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }
}
