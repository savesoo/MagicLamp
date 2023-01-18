package com.app.magiclamp.model.order;

import com.app.magiclamp.entity.Book;
import com.app.magiclamp.model.BookInfoDTO;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class OrderBookPageDTO {

    // 사용자가 선택한 상품이 여러 개인 경우 주문 페이지에 출력(반환) DTO

    // 책 정보를 갖는 List
    private List<BookInfoDTO> bookInfos;

    // 이전 페이지(도서 상세)에서 가지고 올 data
    private String isbn;
    private int bookcount;

    private Integer saleprice; // 판매가(보통 10%할인)


    // 구매액과 마일리지
    private int realprice;
    private int mileage; // 현재 마일리지


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


    public void addToBookInfoList(BookInfoDTO bookInfo){

        List<BookInfoDTO> list = new ArrayList<>();
        list.add(bookInfo);
        this.setBookInfos(list);

    }


}
