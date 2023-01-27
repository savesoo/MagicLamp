package com.app.magiclamp.service.mypage;

import com.app.magiclamp.entity.User;
import com.app.magiclamp.model.AuthUserDTO;
import com.app.magiclamp.repository.AddrBookRepository;
import com.app.magiclamp.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Log4j2
public class UserInfoUpdateService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AddrBookRepository addrBookRepository;

    public int updateUserPwd(String password, String username){

        password = passwordEncoder.encode(password);
        log.info(password);

        int result = userRepository.updatePasswordByUsername(password, username);
        log.info("############### service" + result);

        return result;

    }

    @Transactional
    public int updateUserPhone(String phone, String username, int userindex){

        int result = userRepository.updatePhoneByUsername(phone, username);
        log.info("############### service" + result);

        if(result == 1){
            addrBookRepository.updateAddrBookUserInfoPhone(phone, userindex);
        }

        return result;

    }

    @Transactional
    public int updateUserAddress(String postnum, String address1, String address2, int userindex){

        int result = userRepository.updateAddressByUsername(postnum, address1, address2, userindex);
        log.info("############### service" + result);

        if(result == 1){
            addrBookRepository.updateAddrBookUserInfoAddress(postnum, address1, address2, userindex);
        }

        return result;

    }

}
