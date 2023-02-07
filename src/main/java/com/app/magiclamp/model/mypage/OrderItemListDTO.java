package com.app.magiclamp.model.mypage;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class OrderItemListDTO {

    private int orderitemindex;
    private int orderindex;
    private String isbn;
    private int bookcount;
    private String title;

}
