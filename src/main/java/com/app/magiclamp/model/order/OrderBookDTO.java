package com.app.magiclamp.model.order;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class OrderBookDTO {

    // 하나의 책 정보를 가진 주문서를 보낼 때 사용할 DTO(server 사용)

    private int orderindex;

    // 이전 페이지(도서 상세)에서 가지고 올 data
    private String isbn;

    //구매 가능한 재고
    private int stock;

    //구매 하고 싶은 수량
    private int bookcount;

    // 필요한 data
    private String title; // 책 이름
    private int price; // 책 정가
    private Integer saleprice; // 판매가

    // 구매액과 마일리지
    private int realprice;
    private int mileage; // 현재 마일리지


    // DB에 없는 data
    private int totalPrice; // 총 결제금액(최종 가격-realprice*수량)
    private int saveMileage; // 권당 적립 마일리지
    private int totalMileage; // 총 적립 마일리지(권당 적립 마일리지*수량)


    public void calPriceInfo(){

        this.totalPrice = this.saleprice*this.bookcount; // 총 가격 = 판매가 * 수량
        this.saveMileage = (int)(this.saleprice*0.05); // 권당 적립되는 마일리지(5%)
        this.totalMileage = this.saveMileage*this.bookcount; // 총 적립 마일리지

    }

}
