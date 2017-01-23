package cloud.authserver.jdbc.auth.service;

import com.common.business.dto.ResUser;
import com.common.business.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenluo on 2017/1/23.
 */
@Service("customUserDetailService")
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ResUser resUser = userService.findByName(username);
        List<GrantedAuthority> authorities = new ArrayList<>();
//      附上角色权限的时候，一定要加上ROLE_前缀，不然验证的时候不能匹配，角色格式为ROLE_<角色名>
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_tester");
        authorities.add(simpleGrantedAuthority);
        User user= new User(resUser.getName(),resUser.getPassword(),authorities);
        return user;
    }
}
