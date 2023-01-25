package com.app.magiclamp.spring;

import com.app.magiclamp.model.AuthUserDTO;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        AuthUserDTO authUserDTO = (AuthUserDTO) authentication.getPrincipal();

        String password = request.getParameter("password");

        log.info(" pw : " + password);
        boolean passResult = passwordEncoder.matches(password, authUserDTO.getPassword());

        log.info("password Matches : " + passResult);

        if(passResult) {
            response.sendRedirect("/login?error");
            return;
        }

        List<String> roleNames = new ArrayList<>();
        for(GrantedAuthority authority : authUserDTO.getAuthorities()) {
            roleNames.add(authority.getAuthority());
        }

        HttpSession session = request.getSession();
        log.info("preUrl ===> " +  session.getAttribute("preUrl"));

        if (session.getAttribute("preUrl") != null){
            response.sendRedirect((String) session.getAttribute("preUrl"));
        }

        response.sendRedirect("/");
    }
}
