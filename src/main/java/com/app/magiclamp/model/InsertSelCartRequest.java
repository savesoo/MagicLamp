package com.app.magiclamp.model;

import com.app.magiclamp.entity.Book;
import com.app.magiclamp.entity.Cart;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class InsertSelCartRequest {

    private Integer cartindex;

    private Integer bookcount;

    private Book isbn;

    public Cart toCart(){
        return Cart.builder()
                .cartindex(cartindex)
                .bookcount(bookcount)
                .isbn(isbn)
                .build();
    }
}
