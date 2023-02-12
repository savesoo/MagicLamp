package com.app.magiclamp.model.book;

import com.app.magiclamp.entity.OrderItem;
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


    // DB에 없는 data
    private int totalPrice; // 총 결제금액(최종 가격-realprice*수량)
    private int saveMileage; // 권당 적립 마일리지
    private int totalMileage; // 총 적립 마일리지(권당 적립 마일리지*수량)

    public void calPriceInfo(){

        this.totalPrice = this.saleprice*this.bookcount; // 총 가격 = 판매가 * 수량
        this.saveMileage = (int)(this.price*this.mileagerate/100); // 권당 적립되는 마일리지(정가*적립률)
        this.totalMileage = this.saveMileage*this.bookcount; // 총 적립 마일리지

    }

    public OrderItem toOrderItemEntity(int orderindex){
        return OrderItem.builder()
                .orderindex(orderindex)
                .bookcount(bookcount)
                .isbn(isbn)
                .build();
    }

}
