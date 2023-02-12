package com.app.magiclamp.controller.cart;

import com.app.magiclamp.entity.Cart;
import com.app.magiclamp.model.cart.InsertSelCartRequest;
import com.app.magiclamp.service.cart.CartInsertService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cart")
@Log4j2
public class CartRestController {

    @Autowired
    private CartInsertService cartInsertService;

    @PostMapping("/select")
    public ResponseEntity<Cart> insertSelCart(
            @RequestBody List<InsertSelCartRequest> insertSelCartRequest
    ) {

        for (InsertSelCartRequest req : insertSelCartRequest){

            log.info("req.............." + req);

        }

        log.info("PostMapping 진입 insert 전 insertSelCartRequest ==> " + insertSelCartRequest);


        Cart result = cartInsertService.insertSelCart(insertSelCartRequest);


//        log.info("PostMapping 진입.... insert 후 insertSelCartRequest ==> " + result);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public  ResponseEntity<Cart> insertOneCart(
            @RequestBody InsertSelCartRequest insertSelCartRequest
    ){

        Cart result = cartInsertService.insertOneCart(insertSelCartRequest);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
