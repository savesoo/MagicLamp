package com.app.magiclamp.service.user;

import com.app.magiclamp.entity.User;
import com.app.magiclamp.entity.UserRole;
import com.app.magiclamp.model.AuthUserDTO;
import com.app.magiclamp.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class LoginService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("userLogin");

        Optional<User> result = userRepository.findByUsername(username);
        log.info("select by id : " + result.isEmpty());

        if(result.isEmpty()) {
            throw new UsernameNotFoundException("Check Email or Password");
        }
        User user = result.get();
        log.info(user);

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for(UserRole role : user.getRole()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.name()));
        }

        AuthUserDTO userDTO = new AuthUserDTO(
                user.getUsername(),
                user.getPassword(),
                authorities,
                user.getUserindex(),
                user.getName()
        );

        return userDTO;
    }
}
