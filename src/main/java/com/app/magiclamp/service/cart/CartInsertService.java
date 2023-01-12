package com.app.magiclamp.service.cart;

import com.app.magiclamp.entity.Cart;
import com.app.magiclamp.model.InsertSelCartRequest;
import com.app.magiclamp.repository.CartRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class CartInsertService {

    @Autowired
    private CartRepository cartRepository;

    public Cart insertSelCart(InsertSelCartRequest insertSelCartRequest) {

        Cart cart = insertSelCartRequest.toCart();

        Cart result = cartRepository.save(cart);

        return result;
    }
}
