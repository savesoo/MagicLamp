package com.app.magiclamp.controller.cart;

import com.app.magiclamp.entity.Cart;
import com.app.magiclamp.model.InsertSelCartRequest;
import com.app.magiclamp.service.cart.CartInsertService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
@Log4j2
public class CartRestController {

    @Autowired(required = false)
    private CartInsertService cartInsertService;

    @PostMapping("/select")
    public ResponseEntity<Cart> insertSelCart(
            @RequestBody InsertSelCartRequest insertSelCartRequest
    ) {

        log.info("PostMapping 진입 insert 전 insertSelCartRequest ==> " + insertSelCartRequest);

        Cart result = cartInsertService.insertSelCart(insertSelCartRequest);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
