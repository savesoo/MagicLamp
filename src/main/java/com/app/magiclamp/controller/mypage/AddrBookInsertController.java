package com.app.magiclamp.controller.mypage;

import com.app.magiclamp.entity.AddrBook;
import com.app.magiclamp.model.AuthUserDTO;
import com.app.magiclamp.model.mypage.AddrBookRequest;
import com.app.magiclamp.service.mypage.AddrBookInsertService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("mypage/addrbook")
@Log4j2
public class AddrBookInsertController {

    @Autowired
    private AddrBookInsertService addrBookInsertService;

    @PostMapping
    public ResponseEntity<Integer> insertAddrBook(
            @RequestBody AddrBookRequest addrBookRequest,
            @AuthenticationPrincipal AuthUserDTO user
    ){
        log.info("###############################" + addrBookRequest);
        addrBookRequest.setUserindex(user.getUserindex());
        return new ResponseEntity<>(addrBookInsertService.insertAddrBook(addrBookRequest), HttpStatus.OK);
    }

}
