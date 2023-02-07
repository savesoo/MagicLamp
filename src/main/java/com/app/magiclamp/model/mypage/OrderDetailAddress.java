package com.app.magiclamp.model.mypage;

import com.app.magiclamp.entity.AddrBook;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class OrderDetailAddress {

    private String name;
    private String postnum;
    private String address1;
    private String address2;
    private String phone;

}
