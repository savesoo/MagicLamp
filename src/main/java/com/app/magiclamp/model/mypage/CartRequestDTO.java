package com.app.magiclamp.model.mypage;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class CartRequestDTO {

    private int cartindex;
    private String isbn;
    private int bookcount;
    private int result;
    private int stock;

}
