package com.common.business.aggregation.impl;

import com.common.business.aggregation.UserAggregation;
import com.common.business.dto.ResUser;
import com.common.business.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by chenluo on 2017/2/24.
 */
@Service
public class UserAggregationImpl implements UserAggregation {

    @Autowired
    private UserService userService;

    public ResUser findUserByid(Long id) {
        return userService.findById(id);
    }
}
