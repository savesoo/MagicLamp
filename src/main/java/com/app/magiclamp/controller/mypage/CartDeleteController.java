package com.app.magiclamp.controller.mypage;

import com.app.magiclamp.model.AuthUserDTO;
import com.app.magiclamp.service.mypage.CartDeleteService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("mypage/cart")
@Log4j2
public class CartDeleteController {

    @Autowired
    private CartDeleteService cartDeleteService;

    @DeleteMapping("/{cartindex}")
    public ResponseEntity<Integer> deleteCartIndex(
            @PathVariable("cartindex") List<Integer> cartindex,
            @AuthenticationPrincipal AuthUserDTO user
    ) {

        log.info(cartindex);
        return new ResponseEntity<>(cartDeleteService.deleteCart(cartindex, user.getUserindex()), HttpStatus.OK);

    }
}
