package com.app.magiclamp.controller.user;

import com.app.magiclamp.service.user.RegisterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register/idCheck")
public class IDCheckController {
    private final RegisterService registerService;

    public IDCheckController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping("/{username}")
    public ResponseEntity<Long> idCheck(@PathVariable("username") String username){
        return new ResponseEntity<>(registerService.idCheck(username), HttpStatus.OK);
    }
}
