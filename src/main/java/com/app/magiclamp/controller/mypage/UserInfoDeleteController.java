package com.app.magiclamp.controller.mypage;

import com.app.magiclamp.entity.User;
import com.app.magiclamp.model.AuthUserDTO;
import com.app.magiclamp.model.mypage.UserUpdateRequest;
import com.app.magiclamp.service.mypage.UserInfoChkService;
import com.app.magiclamp.service.mypage.UserInfoDeleteService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@Log4j2
public class UserInfoDeleteController {

    @Autowired
    private UserInfoChkService userInfoChkService;

    @Autowired
    private UserInfoDeleteService userInfoDeleteService;

    @GetMapping("mypage/userWithdraw")
    public String checkUserInfo(Model model,
                                @AuthenticationPrincipal AuthUserDTO user)
    {
        model.addAttribute("name", user.getUsername());
        return "view/mypage/userDelete";
    }

    @PostMapping("mypage/updateUserWithdraw")
    public ResponseEntity<Integer> userWithdraw(
            @RequestBody UserUpdateRequest userUpdateRequest,
            @AuthenticationPrincipal AuthUserDTO user
    ){

        int result = 0;
        log.info("Controller Id >>> " + user.getUsername());
        log.info("Controller Password >>> " + userUpdateRequest.getPassword());
        log.info("Controller Comment >>> " + userUpdateRequest.getComment());

        User chkUser = userInfoChkService.chkUserInfo(user, userUpdateRequest.getPassword());
        if(chkUser != null){
            result = userInfoDeleteService.updateUserWithdraw(chkUser.getUsername(), chkUser.getUserindex());
        }

        return new ResponseEntity<>(result, HttpStatus.OK);

    }

}
