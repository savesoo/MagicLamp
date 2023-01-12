package com.app.magiclamp.model.order;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class RequestOrderPagebook {

    // 사용자가 선택한 상품 data를 주문 페이지로 보내줄 DTO (HTML에서 사용)

    // 이전 페이지(도서 상세)에서 가지고 올 data
    private String isbn;
    private int bookcount; // 책 수량

    // 필요한 data
    private String title; // 책 이름
    private int price; // 책 정가
    private Integer saleprice; // 판매가

    // 구매액과 마일리지
    private int realprice;
    private int mileage;

    // DB에 없는 data
    private int totalprice; // 결제금액(최종 가격-realprice*수량)
    private int totalmileage; // 적립 마일리지(권당 적립 마일리지*수량)



    // 구매가격 및 마일리지 계산 메서드
    // 마일리지 차감은 구매시에 이루어지므로 아직 반영 X
    private void calPriceInfo(){

        this.totalprice = this.saleprice*this.bookcount; // 가격 = 판매가 * 수량
        this.mileage = (int)(this.saleprice*0.05); // 권당 적립되는 마일리지(5%)
        this.totalmileage = this.mileage*this.bookcount; // 합산 적립 마일리지

    }

}
