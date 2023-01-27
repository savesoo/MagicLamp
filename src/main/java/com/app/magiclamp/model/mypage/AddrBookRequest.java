package com.app.magiclamp.model.mypage;

import com.app.magiclamp.entity.AddrBook;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class AddrBookRequest {

    private int addrindex;
    private int userindex;
    private String addrname;
    private String recipient;
    private String postnum;
    private String address1;
    private String address2;
    private String phone;

    private int priority;

    public AddrBook toAddrBookEntity(){
        return AddrBook.builder()
                .addrindex(addrindex)
                .userindex(userindex)
                .addrname(addrname)
                .recipient(recipient)
                .postnum(postnum)
                .address1(address1)
                .address2(address2)
                .phone(phone)
                .build();
    }
}
