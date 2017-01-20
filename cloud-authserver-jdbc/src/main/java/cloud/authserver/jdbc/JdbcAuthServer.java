package cloud.authserver.jdbc;

import cloud.authserver.jdbc.config.AuthServerJdbcConfig;
import com.common.business.aggregation.UserAggregation;
import com.common.business.dto.ResUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
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
public class JdbcAuthServer {
    public static void main(String[] args) {
        SpringApplication.run(JdbcAuthServer.class, args);
    }

    @Autowired
    private UserAggregation userAggregation;

    @RequestMapping("/user")
    public Principal user(Principal user) {
        ResUser resUser= userAggregation.findUserByid(1L);

        return user;
    }
}
