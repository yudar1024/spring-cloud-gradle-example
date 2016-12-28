package com.example;

import com.ctc.wstx.io.BaseInputSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by on 28.01.16.
 *
 * @author David Steiman
 */
@Configuration
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    Logger log = LoggerFactory.getLogger(WebSecurityConfig.class);



    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    private CustomWebAuthenticationDetailsSource customWebAuthenticationDetailsSource;





    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.headers().cacheControl().and().and()
                .csrf().disable()
//                .exceptionHandling()
//                .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
//                .and()
                .authorizeRequests()
// .antMatchers("/**").permitAll()
                .antMatchers("/**").authenticated().and()
//                .and().addFilterAfter(new MyBasicAuthenticationFilter(),BasicAuthenticationFilter.class)
                //BasicAuthenticationFilter 的相关设置
                .httpBasic().authenticationDetailsSource(customWebAuthenticationDetailsSource);
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        CustomDaoAuthenticationProvider customDaoAuthenticationProvider = new CustomDaoAuthenticationProvider();
        auth.inMemoryAuthentication()
                .withUser("reader")
                .password("reader")
                .authorities("FOO_READ")
                .and()
                .withUser("writer")
                .password("writer")
                .authorities("FOO_READ", "FOO_WRITE");
    }

//    @Bean
//    public BasicAuthenticationFilter configBasicAuthenticationFilter() throws Exception {
//        BasicAuthenticationFilter basicAuthenticationFilter= new BasicAuthenticationFilter(authenticationManager());
//        basicAuthenticationFilter.setAuthenticationDetailsSource(new CustomWebAuthenticationDetailsSource());
//        return basicAuthenticationFilter;
//    }
}
