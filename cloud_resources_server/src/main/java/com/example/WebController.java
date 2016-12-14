package com.example;

import com.example.clients.ResourceServer2;
import com.example.config.OAuth2FeignRequestInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Created by on 01.02.16.
 *
 * @author David Steiman
 */
@RestController
public class WebController {
    @Autowired
    private ResourceServer2 resourceServer2;

    private static final Logger LOGGER = LoggerFactory.getLogger(OAuth2FeignRequestInterceptor.class);

    @RequestMapping(method = RequestMethod.GET,value = "/foo")
    public String readFoo() {
        return "read foo 1 " + UUID.randomUUID().toString();
    }

    @RequestMapping(method = RequestMethod.GET,value = "/test")
    public String readFoo2() {
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        LOGGER.debug("########### {}",details);
        return resourceServer2.readFoo();
    }

    @PreAuthorize("hasAuthority('FOO_WRITE')")
    @RequestMapping(method = RequestMethod.POST, value = "/foo")
    public String writeFoo() {
        return "write foo 1 " + UUID.randomUUID().toString();
    }
}
