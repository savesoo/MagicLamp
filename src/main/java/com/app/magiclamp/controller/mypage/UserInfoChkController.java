package com.app.magiclamp.controller.mypage;

import com.app.magiclamp.entity.User;
import com.app.magiclamp.model.AuthUserDTO;
import com.app.magiclamp.service.mypage.UserInfoChkService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Log4j2
public class UserInfoChkController {

    @Autowired
    private UserInfoChkService userInfoChkService;

    @GetMapping("mypage/userinfo")
    public String checkUserInfo(Model model,
                                @AuthenticationPrincipal AuthUserDTO user)
    {
        model.addAttribute("name", user.getUsername());
        model.addAttribute("division", "userinfo");
        return "view/mypage/chkUser";
    }

    @GetMapping("mypage/userdel")
    public String checkUserDel(Model model,
                                @AuthenticationPrincipal AuthUserDTO user)
    {
        model.addAttribute("name", user.getUsername());
        model.addAttribute("division", "userdel");
        return "view/mypage/chkUser";
    }

    @PostMapping("mypage/chkUserAuth")
    public String chkUserAuth(
            @AuthenticationPrincipal AuthUserDTO user,
            Model model,
            @RequestParam("password") String password,
            @RequestParam("division") String division
    ){

        User userInfo = userInfoChkService.chkUserInfo(user, password);
        model.addAttribute("division", division);

        if(userInfo != null) {
            model.addAttribute("userInfo", userInfo);
            if (division.equals("userinfo")) {
                return "view/mypage/userUpdate";
            } else if (division.equals("userdel")) {
                return "view/mypage/userDelete";
            }
        }
        model.addAttribute("name", user.getUsername());
        return "view/mypage/chkUser";
    }

}
