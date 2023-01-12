package com.app.magiclamp.service.user;

import com.app.magiclamp.entity.UserRole;
import com.app.magiclamp.model.UserRequest;
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

    public void registerUser(UserRequest userRequest) {
        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        Set<UserRole> userRoleSet = new HashSet<>();
        userRoleSet.add(UserRole.USER);

        userRequest.setUserRoleSet(userRoleSet);
        userRepository.save(userRequest.toUser());
    }
}
