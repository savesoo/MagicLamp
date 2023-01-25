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
public class UserInfoUpdateService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public int updateUserPwd(String password, String username){

        password = passwordEncoder.encode(password);
        log.info(password);

        int result = userRepository.updatePasswordByUsername(password, username);
        log.info("############### service" + result);

        return result;

    }

    public int updateUserPhone(String phone, String username){

        int result = userRepository.updatePhoneByUsername(phone, username);
        log.info("############### service" + result);

        return result;

    }

    public int updateUserAddress(String postnum, String address1, String address2, String username){

        int result = userRepository.updateAddressByUsername(postnum, address1, address2, username);
        log.info("############### service" + result);

        return result;

    }

}
