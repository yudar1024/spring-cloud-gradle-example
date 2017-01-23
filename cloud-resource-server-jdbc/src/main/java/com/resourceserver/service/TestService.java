package com.resourceserver.service;


import org.springframework.security.access.prepost.PreAuthorize;

/**
 * Created by chenluo on 2017/1/23.
 */
public interface TestService {
    @PreAuthorize("isAuthenticated()")
    String getUUID();

    @PreAuthorize("hasRole(\"tester\")")
    String getTesterUUID();

    @PreAuthorize("hasRole(\"admin\")")
    String getadminUUID();
}
