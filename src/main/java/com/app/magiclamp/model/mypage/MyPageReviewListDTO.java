package com.app.magiclamp.model.mypage;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class MyPageReviewListDTO {

    private int reviewindex;
    private LocalDate regdate;
    private String reviewcontent;
    private int star;
    private String isbn;
    private int reviewer;
    private String title;
    private String bookimg;

}
