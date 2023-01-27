package com.app.magiclamp.model.mypage;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class OrderHistoryListDTO {

    private int orderindex;
    private int userindex;
    private String isbn;
    private int approvalnum;
    private LocalDate orderdate;
    private int bookcount;
    private int mileage;
    private int realprice;
    private int purchasestate;      // 0: 구매, 1: 반품
    private String name;
    private String postnum;
    private String address1;
    private String address2;
    private String phone;
    private String title;

}
