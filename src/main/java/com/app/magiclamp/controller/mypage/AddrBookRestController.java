package com.app.magiclamp.controller.mypage;

import com.app.magiclamp.model.user.AuthUserDTO;
import com.app.magiclamp.service.mypage.AddrBookListService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/mypage/addrchk")
public class AddrBookRestController {

    @Autowired
    private AddrBookListService addrBookListService;

    @GetMapping("/{addrname}")
    public ResponseEntity<Integer> addrnameChk(
            @PathVariable("addrname") String addrname,
            @AuthenticationPrincipal AuthUserDTO user){
       log.info("##############################addrname > " + addrname);
        return new ResponseEntity<>(addrBookListService.addrnameChk(addrname, user.getUserindex()), HttpStatus.OK);
    }
}
