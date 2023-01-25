package com.app.magiclamp.service.mypage;

import com.app.magiclamp.entity.User;
import com.app.magiclamp.model.AuthUserDTO;
import com.app.magiclamp.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class UserInfoChkService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public User chkUserInfo(AuthUserDTO authUserDTO, String password){

        Optional<User> result = userRepository.findByUsername(authUserDTO.getUsername());
        User user = result.get();

        boolean pwdChk = passwordEncoder.matches(password, user.getPassword());

        if(!pwdChk){
            return null;
        }

        return user;

        }

}
