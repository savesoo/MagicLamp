package com.app.magiclamp.service.user;

import com.app.magiclamp.entity.AddrBook;
import com.app.magiclamp.entity.User;
import com.app.magiclamp.entity.UserRole;
import com.app.magiclamp.model.UserRequest;
import com.app.magiclamp.model.mypage.AddrBookRequest;
import com.app.magiclamp.repository.AddrBookRepository;
import com.app.magiclamp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RegisterService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddrBookRepository addrBookRepository;

    public void registerUser(UserRequest userRequest) {
        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        Set<UserRole> userRoleSet = new HashSet<>();
        userRoleSet.add(UserRole.USER);

        userRequest.setUserRoleSet(userRoleSet);
        User user = userRepository.save(userRequest.toUser());

        // 회원 가입 시 기본 주소 주소록에 추가
        addrBookRepository.save(AddrBook.builder()
                    .userindex(user.getUserindex())
                    .recipient(user.getName())
                    .postnum(user.getPostnum())
                    .address1(user.getAddress1())
                    .address2(user.getAddress2())
                    .phone(user.getPhone())
                    .priority(1)
                .build());
    }
}
