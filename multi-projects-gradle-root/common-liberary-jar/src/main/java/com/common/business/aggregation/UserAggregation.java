package com.common.business.aggregation;

import com.common.business.dto.ResUser;
import org.springframework.stereotype.Service;

/**
 * Created by chenluo on 2017/1/20.
 */

public interface UserAggregation {

    ResUser findUserByid(Long id);

}
