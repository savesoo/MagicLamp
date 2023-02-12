package com.app.magiclamp.controller.mypage;

import com.app.magiclamp.model.user.AuthUserDTO;
import com.app.magiclamp.model.mypage.CartRequestDTO;
import com.app.magiclamp.service.mypage.CartDeleteService;
import com.app.magiclamp.service.mypage.CartUpdateService;
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
public class MyPageCartRestController {

    @Autowired
    private CartDeleteService cartDeleteService;
    @Autowired
    private CartUpdateService cartUpdateService;

    @DeleteMapping("/{cartindex}")
    public ResponseEntity<Integer> deleteCartIndex(
            @PathVariable("cartindex") List<Integer> cartindex,
            @AuthenticationPrincipal AuthUserDTO user
    ) {

        log.info(cartindex);
        return new ResponseEntity<>(cartDeleteService.deleteCart(cartindex, user.getUserindex()), HttpStatus.OK);

    }
    @PutMapping
    public ResponseEntity<CartRequestDTO> uptdateCartBookCount(
            @RequestBody CartRequestDTO cartRequestDTO,
            @AuthenticationPrincipal AuthUserDTO user
    ) {

        log.info(cartRequestDTO.getCartindex());
        return new ResponseEntity<>(cartUpdateService.updateCartBookCount(cartRequestDTO.getBookcount(), cartRequestDTO.getCartindex(), cartRequestDTO.getIsbn(), user.getUserindex()), HttpStatus.OK);

    }
}
