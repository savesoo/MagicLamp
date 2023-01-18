package com.app.magiclamp.service.cart;

import com.app.magiclamp.entity.Cart;
import com.app.magiclamp.mapper.CartMapper;
import com.app.magiclamp.model.InsertSelCartRequest;
import com.app.magiclamp.repository.CartRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class CartInsertService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartMapper cartMapper;

    public Cart insertSelCart(List<InsertSelCartRequest> insertSelCartRequest) {

        log.info("insertSelCartRequest................. ==> " + insertSelCartRequest);

        Cart result = null;

        for (InsertSelCartRequest istReq : insertSelCartRequest) {

            String isbnCart = istReq.getIsbn();
            Integer userindexCart = istReq.getUserindex();

            Cart searchResult = cartMapper.searchIsbn(isbnCart, userindexCart);

            if (searchResult == null) {

                Cart cart = istReq.toCart();

                log.info("CartInsert 진입 전 cart  ==> " + cart);

                result = cartRepository.save(cart);

                log.info("CartInsert 진입 후 result ==> " + result);

            }
        }

        return result;
    }

    public Cart insertOneCart(InsertSelCartRequest insertSelCartRequest) {

        Cart result = null;

        String isbnCart = insertSelCartRequest.getIsbn();

        Integer userindexCart = insertSelCartRequest.getUserindex();

        Cart searchResult = cartMapper.searchIsbn(isbnCart, userindexCart);

        if (searchResult == null) {

            Cart cart = insertSelCartRequest.toCart();

            log.info("CartInsert 진입 전 cart  ==> " + cart);

            result = cartRepository.save(cart);

            log.info("CartInsert 진입 후 result ==> " + result);

        }

        return result;
    }
}
