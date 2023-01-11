package com.app.magiclamp.domain.administrator;

import com.app.magiclamp.entity.Book;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class BookInsertRequest {

    private String isbn; // 도서ISBN

    private String title; // 도서명

    private String author; // 저자

    private String publisher; // 출판사

    private String pubdate; // 출간일자

    private String status; // 도서 상태 (정상, 절판, 품절)

    private Integer price; // 정가

    private Integer saleprice; // 판매가(보통 10%할인)

    private Integer mileagerate; // 마일리지적립률(보통 5% 적립)

    private Integer stock; // 재고 수량(시작 값은 10권)

    private String binding; // 도서 형태(PB or HB)

    private Integer pages; // 쪽수

    private String size; // 사이즈 (가로mm, 세로mm)

    private Integer weight; // 무게 (기본 500g)

    private String category; // 카테고리

    private String description; // 도서 소개

    private String contents; // 도서 목차

    private MultipartFile bookimg; // 도서 이미지

    private LocalDate regdate; // 등록일자


    public Book toBookEntity() {
        return Book.builder()
                .isbn(isbn)
                .title(title)
                .author(author)
                .publisher(publisher)
                .pubdate(pubdate)
                .status(status)
                .price(price)
                .saleprice(saleprice)
                .mileagerate(mileagerate)
                .stock(stock)
                .binding(binding)
                .pages(pages)
                .size(size)
                .weight(weight)
                .category(category)
                .description(description)
                .contents(contents)
                .build();
    }

}
