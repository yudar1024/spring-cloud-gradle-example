package com.common.business.service;

import com.common.business.dto.ResUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by chenluo on 2017/1/20.
 */
public interface UserService {
    ResUser findById(Long id);

    ResUser findByName(String name);


}
