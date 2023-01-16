package com.app.magiclamp.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class BookInfoDTO {

    // 구매할 수량을 포함해 넘겨줄 data

    private String isbn; // 도서ISBN

    private int bookcount;

    private String title; // 도서명
    private String author; // 저자
    private String publisher; // 출판사

    private int price; // 정가
    private int saleprice; // 판매가(보통 10%할인)
    private int mileagerate; // 마일리지적립률(보통 5% 적립)

    private int stock; // 재고 수량(시작 값은 10권)

    private String category; // 카테고리

    private String bookimg; // 도서 이미지

}
