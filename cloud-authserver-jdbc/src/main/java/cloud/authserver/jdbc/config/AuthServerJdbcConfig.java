package cloud.authserver.jdbc.config;

import com.common.business.JPAConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * Created by chenluo on 2017/1/20.
 */
@Configuration
@EnableResourceServer
@EnableAuthorizationServer
@Import(JPAConfig.class)
public class AuthServerJdbcConfig extends WebSecurityConfigurerAdapter{

}
