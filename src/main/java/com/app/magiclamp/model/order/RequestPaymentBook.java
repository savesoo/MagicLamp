package com.app.magiclamp.model.order;

import com.app.magiclamp.entity.Order;
import com.app.magiclamp.model.BookInfoDTO;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class RequestPaymentBook {

    // 주문서를 보낼 때 사용할 DTO(server 사용)
    // 사용한 마일리지,

    private int orderindex;

    // 책 정보를 갖는 List
    private List<BookInfoDTO> bookInfos;

    // 이전 페이지(도서 상세)에서 가지고 올 data
    private String isbn;

    //구매 하고 싶은 수량
    private int bookcount;

    // 필요한 data
    private String title;
    private int price;
    private Integer saleprice;

    // 구매액과 마일리지
    private int realprice;
    private int mileage;
    private int usemileage; // 사용 마일리지


    // 배송정보
    private String recipient;
    private String phone;
    private String postnum;
    private String address1;
    private String address2;


    // DB에 없는 data
    private int totalPrice; // 총 결제금액(최종 가격-realprice*수량)
    private int saveMileage; // 권당 적립 마일리지
    private int totalMileage; // 총 적립 마일리지(권당 적립 마일리지*수량)


    public void calPriceInfo(){

        this.totalPrice = this.saleprice*this.bookcount; // 총 가격 = 판매가 * 수량
        this.saveMileage = (int)(this.saleprice*0.05); // 권당 적립되는 마일리지(5%)
        this.totalMileage = this.saveMileage*this.bookcount; // 총 적립 마일리지

    }

    // DB에 없는 data
    private int orderTotalPrice; // 주문시 총 결제금액
    private int totalSaveMileage; // 주문시 총 적립 마일리지


    // 책끼리 금액 합산되게......
    // 주문시 실 결제금액 계산 메서드
    public void calTotalprice(){

        for(BookInfoDTO order : bookInfos){
            orderTotalPrice += order.getTotalPrice();
            totalSaveMileage += order.getTotalMileage();
        }

        realprice = orderTotalPrice - usemileage;

    }

    public Order toOrderEntity(){
        return Order.builder()
                .purchasestate(true)
                .build();
    }

}
