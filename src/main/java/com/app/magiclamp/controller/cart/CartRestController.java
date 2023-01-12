package com.app.magiclamp.controller.cart;

import com.app.magiclamp.entity.Cart;
import com.app.magiclamp.repository.CartRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@Log4j2
public class CartRestController {

    @Autowired
    private CartRepository cartRepository;

    @PostMapping
    public ResponseEntity<Cart> insertCart(
            @RequestParam("list") List list
    ) {

        log.info("select list >>>> " + list);


        return null;
    }

}
