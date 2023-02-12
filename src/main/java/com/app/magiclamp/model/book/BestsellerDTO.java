package com.app.magiclamp.model.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BestsellerDTO {

    private int totalcnt; // join 결과, 총 판매권수
    private String isbn;
    private String category;
    private String title;
    private int saleprice;
    private String bookimg;
}
