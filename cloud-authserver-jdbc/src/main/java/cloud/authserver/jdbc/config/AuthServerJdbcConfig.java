package cloud.authserver.jdbc.config;

import com.common.business.JPAConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

import javax.sql.DataSource;
import java.security.SecureRandom;
import java.util.Random;

/**
 * Created by chenluo on 2017/1/20.
 */
@Configuration
@EnableResourceServer
@EnableAuthorizationServer
@Import({JPAConfig.class})
public class AuthServerJdbcConfig extends WebSecurityConfigurerAdapter{

//    @Autowired
//    @Qualifier("customUserDetailService")
//    private UserDetailsService userDetailsService;
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//       auth.userDetailsService(userDetailsService);
//
//    }

    @Bean
    public static PasswordEncoder getPasswordEncod(){
        Integer seed= 9;
        return  new BCryptPasswordEncoder(8, new SecureRandom(seed.toString().getBytes()));
    }


//
//    public static void main(String [] test){
//        System.out.print(getPasswordEncod().encode("tester"));
//    }


}
