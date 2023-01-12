package com.app.magiclamp.service.cart;

import com.app.magiclamp.entity.Cart;
import com.app.magiclamp.model.InsertSelCartRequest;
import com.app.magiclamp.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CartInsertService {

    @Autowired
    private CartRepository cartRepository;

    public Cart insertSelCart(InsertSelCartRequest insertSelCartRequest) {

        Cart cart = insertSelCartRequest.toCart();

        Cart result = cartRepository.save(cart);

        return result;
    }
}
