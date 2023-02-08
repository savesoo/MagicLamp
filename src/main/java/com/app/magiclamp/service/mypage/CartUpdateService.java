package com.app.magiclamp.service.mypage;

import com.app.magiclamp.model.mypage.CartRequestDTO;
import com.app.magiclamp.repository.BookRepository;
import com.app.magiclamp.repository.CartRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
public class CartUpdateService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private BookRepository bookRepository;

    @Transactional
    public CartRequestDTO updateCartBookCount(int bookcount, int cartindex, String isbn, int userindex){

    int result = 0;
    int stock = bookRepository.selectStockByIsbn(isbn);

        if(bookcount <= stock){
            result = cartRepository.updateBookcountByCartindex(bookcount, cartindex, userindex);
            log.info("########################################### update bookcount : " + bookcount +"result : "+ result);
        }

        return CartRequestDTO.builder().result(result).stock(stock).build();
    }
}
