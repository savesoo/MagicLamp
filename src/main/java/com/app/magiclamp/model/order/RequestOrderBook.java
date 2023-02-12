package com.app.magiclamp.model.order;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class RequestOrderBook {

    // 사용자가 선택한 상품 data를 주문 페이지로 보내줄 DTO (HTML에서 사용)

    // 이전 페이지(도서 상세)에서 가지고 올 data        뷰->서버
    private String isbn;
    private int bookcount; // 책 수량


}
