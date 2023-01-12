package com.app.magiclamp.controller.user;

import com.app.magiclamp.model.UserRequest;
import com.app.magiclamp.service.user.RegisterService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Log4j2
@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    RegisterService registerService;

    @GetMapping
    public String getRegister(){
        return "view/user/register";
    }

    @PostMapping
    public ResponseEntity<String> signup(
            @RequestBody UserRequest userRequest
    ){
        // TODO: 보안코드 들어가야 합니다
        registerService.registerUser(userRequest);
        return new ResponseEntity<>("/", new HttpHeaders(), HttpStatus.OK);
    }

}
