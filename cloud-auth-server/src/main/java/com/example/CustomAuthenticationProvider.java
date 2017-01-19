package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

/**
 * Created by chenluo on 2016/12/15.
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomAuthenticationProvider.class);
//
//    @Autowired
//    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
            if(authentication.getDetails() instanceof CustomWebAuthenticationDetailsSource.MyAuthenticationDetails){
                CustomWebAuthenticationDetailsSource.MyAuthenticationDetails myAuthenticationDetails = (CustomWebAuthenticationDetailsSource.MyAuthenticationDetails) authentication.getDetails();
                if(myAuthenticationDetails.getReferer().equals("1"));
                return authentication;
            }
        throw new BadCredentialsException("Bad Authentication");

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(CustomUsernamePasswordAuthenticationToken.class);

    }
}
