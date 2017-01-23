package com.resourceserver.service.impl;

import com.resourceserver.service.TestService;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by chenluo on 2017/1/23.
 */
@Service
public class TestServiceImpl implements TestService{

    @Override
    public String getUUID() {
         return UUID.randomUUID().toString();
    }

    @Override
    public String getTesterUUID() {
        return "test "+UUID.randomUUID().toString();
    }

    @Override
    public String getadminUUID() {
        return "admin "+UUID.randomUUID().toString();
    }
}
