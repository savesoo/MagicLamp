package com.app.magiclamp.controller.mypage;

import com.app.magiclamp.entity.User;
import com.app.magiclamp.model.AuthUserDTO;
import com.app.magiclamp.model.mypage.UserUpdateRequest;
import com.app.magiclamp.service.mypage.UserInfoChkService;
import com.app.magiclamp.service.mypage.UserInfoUpdateService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
public class UserInfoUpdateController {

    @Autowired
    private UserInfoUpdateService userInfoUpdateService;

    @Autowired
    private UserInfoChkService userInfoChkService;

    @PostMapping("mypage/pwdUpdate")
    public ResponseEntity<Integer> updateUserPwd(
            @RequestBody UserUpdateRequest userUpdateRequest,
            @AuthenticationPrincipal AuthUserDTO user
            ){

        log.info("Controller Id >>> " + user.getUsername());
        log.info("Controller Pwd >>> " + userUpdateRequest.getPassword());
        int result = userInfoUpdateService.updateUserPwd(userUpdateRequest.getPassword(), user.getUsername());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("mypage/phoneUpdate")
    public ResponseEntity<Integer> updateUserPhone(
            @RequestBody UserUpdateRequest userUpdateRequest,
            @AuthenticationPrincipal AuthUserDTO user
    ){

        log.info("Controller Id >>> " + user.getUsername());
        log.info("Controller Phone >>> " + userUpdateRequest.getPhone());
        int result = userInfoUpdateService.updateUserPhone(userUpdateRequest.getPhone(), user.getUsername());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("mypage/addressUpdate")
    public ResponseEntity<Integer> updateUserAddress(
            @RequestBody UserUpdateRequest userUpdateRequest,
            @AuthenticationPrincipal AuthUserDTO user
    ){

        log.info("Controller Id >>> " + user.getUsername());
        log.info("Controller Postnum >>> " + userUpdateRequest.getPostnum());
        log.info("Controller Address >>> " + userUpdateRequest.getAddress1());
        log.info("Controller Address >>> " + userUpdateRequest.getAddress2());
        int result = userInfoUpdateService.updateUserAddress(
                userUpdateRequest.getPostnum(),
                userUpdateRequest.getAddress1(),
                userUpdateRequest.getAddress2(),
                user.getUsername());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
