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

    private Integer userindex;

    private Integer bookcount;

    private String isbn;

    public Cart toCart(){
        return Cart.builder()
                .userindex(userindex)
                .bookcount(bookcount)
                .isbn(Book.builder().isbn(isbn).build())
                .build();
    }
}
