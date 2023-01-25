package com.app.magiclamp.service.mypage;

import com.app.magiclamp.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class UserInfoDeleteService {

    @Autowired
    private UserRepository userRepository;

    public int updateUserWithdraw(String username, int userindex){

        int result = userRepository.updateUserWithdraw(username, userindex);
        log.info("############### service" + result);

        return result;

    }

}
