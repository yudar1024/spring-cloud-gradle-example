package com.common.business;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by chenluo on 2017/1/20.
 */
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@EnableJpaRepositories(basePackages = {"com.common.business.repo"})
@ComponentScan(basePackages = {"com.common.business"})
@EntityScan(basePackages = {"com.common.business.dto"})
public class JPAConfig {
}
