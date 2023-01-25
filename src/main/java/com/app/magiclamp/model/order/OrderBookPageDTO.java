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

    private int totalBookCnt;

    // 현재 마일리지(DB에서 가져오기)
    private int mileage;

    // 배송정보(DB에서 가져오기)
    private String recipient;
    private String phone;
    private String postnum;
    private String address1;
    private String address2;

    // DB에 없는 data
    private int orderTotalPrice; // 주문시 총 결제금액
    private int totalSaveMileage; // 주문시 총 적립 마일리지


    // 주문페이지에 출력될 책 list에 추가
    public void addToBookInfoList(BookInfoDTO bookInfo){

        List<BookInfoDTO> list = new ArrayList<>();
        list.add(bookInfo);
        this.setBookInfos(list);

    }

    // 총 합산금액, 최종 적립 마일리지 계산 메서드
    public void calTotalprice(){

        for(BookInfoDTO order : bookInfos){
            totalBookCnt += order.getBookcount();
            orderTotalPrice += order.getTotalPrice();
            totalSaveMileage += order.getTotalMileage();
        }
    }


}
