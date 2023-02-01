package com.app.magiclamp;

import com.app.magiclamp.entity.*;
import com.app.magiclamp.model.order.OrderBookPageDTO;
import com.app.magiclamp.model.order.RequestOrderBook;
import com.app.magiclamp.model.order.RequestPaymentBook;
import com.app.magiclamp.repository.*;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


@SpringBootTest
@Log4j2
public class LampTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // 암호화 필요하므로 주입

    @Test
    public void insertAdmin(){

        User user = User.builder()
                .name("admin")
                .role(new HashSet<UserRole>()) // 저장할 때 권한 넣어주기 위해
                .password(passwordEncoder.encode("1111"))
                .username("admin")
                .postnum("34567")
                .address1("기본 주소")
                .address2("상세 주소")
                .phone("01033330000")
                .build();

        // 기본 권한 부여(ADMIN, USER)
        user.addUserRole(UserRole.ADMIN);

        userRepository.save(user);

    }

}