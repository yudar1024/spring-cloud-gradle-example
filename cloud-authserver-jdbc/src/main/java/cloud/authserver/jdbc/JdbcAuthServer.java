package cloud.authserver.jdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by chenluo on 2017/1/19.
 */
@SpringBootApplication
@RestController
@EnableResourceServer
@EnableAuthorizationServer
public class JdbcAuthServer {
    public static void main(String[] args) {
        SpringApplication.run(JdbcAuthServer.class, args);
    }

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }
}
