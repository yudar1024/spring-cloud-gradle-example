package com.common.business.service.impl;

import com.common.business.dto.ResUser;
import com.common.business.repo.UserRepo;
import com.common.business.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by chenluo on 2017/1/20.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public ResUser findById(Long id) {
        ResUser resUser= userRepo.findOne(id);
        System.out.println(resUser.toString());
        return resUser;
    }

    @Override
    public ResUser findByName(String name) {
        return userRepo.findByName(name);
    }
}
