package com.app.magiclamp.controller;

import com.app.magiclamp.model.AuthUserDTO;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class IndexController {
    @GetMapping
    public String index(
            HttpServletRequest request,
            HttpServletResponse response,
            @AuthenticationPrincipal AuthUserDTO authUserDTO,
            Model model
    ) {

        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("prePath")) {
                    c.setMaxAge(0);
                    response.addCookie(c);
                }
            }
        }

        model.addAttribute("loginInfo", authUserDTO == null ? -1 : authUserDTO.getUserindex());

        return "view/index";
    }
}
