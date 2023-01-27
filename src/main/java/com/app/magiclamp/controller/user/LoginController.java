package com.app.magiclamp.controller.user;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j2
@Controller
public class LoginController {
    @GetMapping("/login")
    public String getLogin(HttpServletRequest request, HttpServletResponse response) {

        String referer = request.getHeader("Referer");

        referer = referer.replaceAll("http://localhost:8080", "");

        Cookie setCookie = new Cookie("prePath", referer); // 쿠키 이름을 name으로 생성
        setCookie.setMaxAge(60*60*24); // 기간을 하루로 지정(60초 * 60분 * 24시간)
        response.addCookie(setCookie);

        return "view/user/login";
    }
}
