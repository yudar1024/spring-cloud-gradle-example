package com.example;

import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by chenluo on 2016/12/15.
 */
@Component
public class CustomWebAuthenticationDetailsSource extends WebAuthenticationDetailsSource {

    private String portal;

    @Override
    public WebAuthenticationDetails buildDetails(HttpServletRequest context) {
        return new MyAuthenticationDetails(context);
    }

    @SuppressWarnings("serial")
    class MyAuthenticationDetails extends WebAuthenticationDetails {

        private String portal;

        public MyAuthenticationDetails(HttpServletRequest request) {
            super(request);
            this.portal = request.getHeader("portal");
        }

        public String getReferer() {
            return portal;
        }
    }
}
