package com.app.magiclamp.controller.mypage;

import com.app.magiclamp.model.user.AuthUserDTO;
import com.app.magiclamp.service.mypage.AddrBookDeleteService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("mypage/addrbook")
@Log4j2
public class AddrBookDeleteController {

    @Autowired
    private AddrBookDeleteService addrBookDeleteService;

    @DeleteMapping("/{addrindex}")
    public ResponseEntity<Integer> deleteAddrBook(
            @PathVariable("addrindex") List<Integer> addrindex,
            @AuthenticationPrincipal AuthUserDTO user
    ){
        return new ResponseEntity<>(addrBookDeleteService.deleteAddrBook(addrindex, user.getUserindex()), HttpStatus.OK);
    }
}
