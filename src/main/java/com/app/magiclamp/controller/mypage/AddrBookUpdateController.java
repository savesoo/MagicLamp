package com.app.magiclamp.controller.mypage;

import com.app.magiclamp.entity.AddrBook;
import com.app.magiclamp.model.user.AuthUserDTO;
import com.app.magiclamp.model.mypage.AddrBookRequest;
import com.app.magiclamp.service.mypage.AddrBookUpdateService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("mypage/addrbook")
@Log4j2
public class AddrBookUpdateController {

    @Autowired
    private AddrBookUpdateService addrBookUpdateService;

    @PutMapping
    public ResponseEntity<AddrBook> updateAddrBook(
            @RequestBody AddrBookRequest addrBookRequest,
            @AuthenticationPrincipal AuthUserDTO user
    ){
        addrBookRequest.setUserindex(user.getUserindex());
        log.info("###############################" + addrBookRequest);
        return new ResponseEntity<>(addrBookUpdateService.updateAddrBook(addrBookRequest), HttpStatus.OK);
    }

}
