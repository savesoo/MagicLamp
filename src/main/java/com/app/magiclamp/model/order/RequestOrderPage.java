package com.app.magiclamp.model.order;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class RequestOrderPage {

    // 사용자가 선택한 상품이 여러 개인 경우(장바구니 사용시) 사용할 DTO (HTML에서 사용)

    private List<RequestOrderPageBook> orderBooks;

    public List<RequestOrderPageBook> getOrder(){
        return orderBooks;
    }

}
