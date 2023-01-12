package com.app.magiclamp.controller.cart;

import com.app.magiclamp.repository.cart.CartRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
@Log4j2
public class CartRestController {

    @Autowired
    private CartRepository cartRepository;

}
