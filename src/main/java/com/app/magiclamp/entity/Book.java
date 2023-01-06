package com.app.magiclamp.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "tbl_book")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Book {

    @Id
    @Column
    private String isbn; // 도서ISBN

    @Column
    private String title; // 도서명

    @Column
    private String author; // 저자

    @Column
    private String publisher; // 출판사

    @Column
    private String pubdate; // 출간일자

    @Column
    private String status; // 도서 상태 (정상, 절판, 품절)

    @Column
    private Integer price; // 정가

    @Column
    private Integer saleprice; // 판매가(보통 10%할인)

    @Column
    private Integer mileagerate; // 마일리지적립률(보통 5% 적립)

    @Column
    private Integer stock; // 재고 수량(시작 값은 10권)

    @Column
    private String binding; // 도서 형태(PB or HB)

    @Column
    private Integer pages; // 쪽수

    @Column
    private String size; // 사이즈 (가로mm, 세로mm)

    @Column
    private Integer weight; // 무게 (기본 500g)

    @Column
    private String category; // 카테고리

    @Column
    private String description; // 도서 소개

    @Column
    private String contents; // 도서 목차

    @Column
    private String bookimg; // 도서 이미지

    @Column(columnDefinition = "timestamp not null default current_timestamp()")
    private LocalDate regdate; // 등록일자

    @Column(columnDefinition = "timestamp not null default current_timestamp()")
    private LocalDate updatedate; // 수정일자

}
