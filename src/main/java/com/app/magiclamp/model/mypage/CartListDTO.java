package com.app.magiclamp.model.mypage;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class CartListDTO {

    private int cartindex;
    private String isbn;
    private String bookimg;
    private String title;
    private int price;
    private int saleprice;
    private int saveMileage;
    private int bookcount;
    private int mileagerate;

}
