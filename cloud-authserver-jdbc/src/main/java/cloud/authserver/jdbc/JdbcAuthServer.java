package cloud.authserver.jdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * Created by chenluo on 2017/1/19.
 */
@SpringBootApplication
@EnableAuthorizationServer
public class JdbcAuthServer {
    public static void main(String[] args) {
        SpringApplication.run(JdbcAuthServer.class, args);
    }
}
