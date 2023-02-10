package com.app.magiclamp.model.order;

import lombok.Data;

import java.sql.Date;

@Data
public class KakaoPayReadyDTO {

    //response
    private String tid, next_redirect_pc_url;
    private Date created_at;

}
