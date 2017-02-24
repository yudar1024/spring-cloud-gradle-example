package com.common.business.repo;

import com.common.business.dto.ResUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by chenluo on 2017/1/20.
 */
@Repository
public interface UserRepo extends JpaRepository<ResUser, Long> {

    ResUser findByName(String name);
}
