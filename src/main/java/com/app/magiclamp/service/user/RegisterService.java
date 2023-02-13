package com.app.magiclamp.service.user;

import com.app.magiclamp.entity.AddrBook;
import com.app.magiclamp.entity.User;
import com.app.magiclamp.entity.UserRole;
import com.app.magiclamp.model.user.UserRequest;
import com.app.magiclamp.repository.AddrBookRepository;
import com.app.magiclamp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Random;
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

        String addrname = defaultAddressRandom();

        // 회원 가입 시 기본 주소 주소록에 추가
        addrBookRepository.save(AddrBook.builder()
                    .userindex(user.getUserindex())
                    .addrname(addrname)
                    .recipient(user.getName())
                    .postnum(user.getPostnum())
                    .address1(user.getAddress1())
                    .address2(user.getAddress2())
                    .phone(user.getPhone())
                    .priority(1)
                .build());
    }

    public long idCheck(String userid){
        long cnt = userRepository.countByUsername(userid);
        return cnt;
    }

//    public String defaultAddress(){
//        String randomAddr = RandomStringUtils.randomAlphanumeric(5)
//        System.out.println(randomAddr);
//
//        return randomAddr;
//    }

    public String defaultAddressRandom() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 5;
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        System.out.println(generatedString);

        return "기본배송지"+generatedString;
    }
}
