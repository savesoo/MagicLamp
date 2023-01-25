package com.app.magiclamp.model.mypage;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class UserUpdateRequest {

    private String username;
    private String password;
    private String phone;
    private String postnum;
    private String address1;
    private String address2;
    private String comment;

}
