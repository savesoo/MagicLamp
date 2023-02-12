package com.app.magiclamp.controller.mypage;

import com.app.magiclamp.model.user.AuthUserDTO;
import com.app.magiclamp.service.mypage.CartListService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Log4j2
public class CartListController {

    @Autowired
    private CartListService cartListService;

    @GetMapping("/mypage/cart")
    public String selectCartList(
            @RequestParam(value = "sortOption", defaultValue = "1") int option,
            @AuthenticationPrincipal AuthUserDTO user,
            Model model
    ) {

        log.info("######################" + option);

        int userindex = user.getUserindex();

        model.addAttribute("cartList", cartListService.selectCartList(userindex, option));
        model.addAttribute("option", option);


        return "view/mypage/cart";

    }
}
