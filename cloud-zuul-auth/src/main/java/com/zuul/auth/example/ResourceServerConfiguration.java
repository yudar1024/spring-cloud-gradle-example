package com.zuul.auth.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * Created by chenluo on 2016/12/9.
 */
@Configuration
@EnableResourceServer
@Import(JwtConfiguration.class)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    Logger log = LoggerFactory.getLogger(ResourceServerConfiguration.class);
    @Autowired
    @Qualifier("tokenStore")
    private TokenStore tokenStore;



    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        log.info("Configuring ResourceServerSecurityConfigurer ");
        resources.tokenStore(tokenStore);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        // @formatter:off
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .requestMatchers()
                .antMatchers("/**")
                .and()
                .csrf().requireCsrfProtectionMatcher(csrfRequestMatcher()).csrfTokenRepository(this.getCSRFTokenRepository())
                .and()
                .authorizeRequests()
                .antMatchers("/service1/**").permitAll()
                .and()
                .addFilterAfter(this.createCSRFHeaderFilter(), CsrfFilter.class);

//                .antMatchers("/admin/**").access("hasRole('reader')");

        // @formatter:on
    }

    /**
     * Spring security offers in-built protection for cross site request forgery
     * (CSRF) by needing a custom token in the header for any requests that are
     * NOT safe i.e. modify the resources from the server e.g. POST, PUT & PATCH
     * etc.<br>
     * <br>
     *
     * This protection is achieved using cookies that send a custom value (would
     * remain same for the session) in the first request and then the front-end
     * would send back the value as a custom header.<br>
     * <br>
     *
     * In this method we create a filter that is applied to the web security as
     * follows:
     * <ol>
     * <li>Spring security provides the CSRF token value as a request attribute;
     * so we extract it from there.</li>
     * <li>If we have the token, Angular wants the cookie name to be
     * "XSRF-TOKEN". So we add the cookie if it's not there and set the path for
     * the cookie to be "/" which is root. In more complicated cases, this might
     * have to be the context root of the api gateway.</li>
     * <li>We forward the request to the next filter in the chain</li>
     * </ol>
     *
     * The request-to-cookie filter that we add needs to be after the
     * <code>csrf()</code> filter so that the request attribute for CsrfToken
     * has been already added before we start to process it.
     *
     * @return
     */
    private Filter createCSRFHeaderFilter() {
        return new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest request,
                                            HttpServletResponse response, FilterChain filterChain)
                    throws ServletException, IOException {
                CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class
                        .getName());
                if (csrf != null) {
                    Cookie cookie = WebUtils.getCookie(request, CSRF_COOKIE_NAME);
                    String token = csrf.getToken();
                    if (cookie == null || token != null
                            && !token.equals(cookie.getValue())) {
                        cookie = new Cookie(CSRF_COOKIE_NAME, token);
                        cookie.setPath("/");
                        response.addCookie(cookie);
                    }
                }
                filterChain.doFilter(request, response);
            }
        };
    }



    /**
     * Spring security offers in-built protection for cross site request forgery
     * (CSRF) by needing a custom token in the header for any requests that are
     * NOT safe i.e. modify the resources from the server e.g. POST, PUT & PATCH
     * etc.<br>
     * <br>
     *
     * This protection is achieved using cookies that send a custom value (would
     * remain same for the session) in the first request and then the front-end
     * would send back the value as a custom header.<br>
     * <br>
     *
     * In this method we create a filter that is applied to the web security as
     * follows:
     * <ol>
     * <li>Spring security provides the CSRF token value as a request attribute;
     * so we extract it from there.</li>
     * <li>If we have the token, Angular wants the cookie name to be
     * "XSRF-TOKEN". So we add the cookie if it's not there and set the path for
     * the cookie to be "/" which is root. In more complicated cases, this might
     * have to be the context root of the api gateway.</li>
     * <li>We forward the request to the next filter in the chain</li>
     * </ol>
     *
     * The request-to-cookie filter that we add needs to be after the
     * <code>csrf()</code> filter so that the request attribute for CsrfToken
     * has been already added before we start to process it.
     *
     * @return
     */
    private RequestMatcher csrfRequestMatcher() {
        return new RequestMatcher() {
            // Always allow the HTTP GET method
            private final Pattern allowedMethods = Pattern.compile("^(GET|HEAD|OPTIONS|TRACE|POST)$");

            // Disable CSFR protection on the following urls:
            private final AntPathRequestMatcher[] requestMatchers = { new AntPathRequestMatcher("/oauth/**") };

            @Override
            public boolean matches(HttpServletRequest request) {
                if (allowedMethods.matcher(request.getMethod()).matches()) {
                    return false;
                }

                for (AntPathRequestMatcher matcher : requestMatchers) {
                    if (matcher.matches(request)) {
                        return false;
                    }
                }
                return true;
            }
        };
    }

    /**
     * Angular sends the CSRF token in a custom header named "X-XSRF-TOKEN"
     * rather than the default "X-CSRF-TOKEN" that Spring security expects.
     * Hence we are now telling Spring security to expect the token in the
     * "X-XSRF-TOKEN" header.<br><br>
     *
     * This customization is added to the <code>csrf()</code> filter.
     *
     * @return
     */
    private CsrfTokenRepository getCSRFTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName(CSRF_ANGULAR_HEADER_NAME);
        return repository;
    }

    private static final String CSRF_COOKIE_NAME = "XSRF-TOKEN";
    private static final String CSRF_ANGULAR_HEADER_NAME = "X-XSRF-TOKEN";
}
