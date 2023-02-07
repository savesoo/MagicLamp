package com.app.magiclamp.model.order;

import com.app.magiclamp.entity.Order;
import com.app.magiclamp.entity.OrderItem;
import com.app.magiclamp.model.AuthUserDTO;
import com.app.magiclamp.model.BookInfoDTO;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class RequestPaymentBook {

    // 주문서를 보낼 때 사용할 DTO(server 사용)
    private int userindex;

    // 책 정보를 갖는 List
    private List<BookInfoDTO> bookInfos;

    // 이전 페이지(도서 상세)에서 가지고 올 data
    private String isbn;

    //구매 하고 싶은 수량
    private int bookcount;
    private int totalBookCnt;

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
    private int orderTotalPrice; // 주문시 총 결제금액
    private int totalSaveMileage; // 주문시 총 적립 마일리지

    public Order toOrderEntity(){
        return Order.builder()
                .isbn(isbn)
                .bookcount(bookcount)
                .realprice(realprice)
                .mileage(totalSaveMileage)
                .name(recipient)
                .phone(phone)
                .postnum(postnum)
                .address1(address1)
                .address2(address2)
                .userindex(userindex)
                .build();
    }

    public OrderItem toOrderItemEntity(int orderindex){
        return OrderItem.builder()
                .orderindex(orderindex)
                .bookcount(bookcount)
                .isbn(isbn)
                .build();
    }

}
