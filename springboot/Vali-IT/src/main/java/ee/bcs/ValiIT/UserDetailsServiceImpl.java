package ee.bcs.ValiIT;

import ee.bcs.ValiIT.controller.JdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    JdbcRepository jdbcRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return User.withUsername("tere")
                .password(passwordEncoder.encode("tore"))
                .roles("USER").build();
    }
}
//        return User.withUsername(username)
//                .password(jdbcRepository.getPassword(username))
//                .roles("USER").build();