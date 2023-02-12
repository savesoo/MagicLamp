package com.app.magiclamp.model.book;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class BookRequest {

    /*도서메일리스와 상세 페이지에 도서의 총 별점과 리뷰수을 표시하기 위해 기존 Book 엔티티에 내용에 별점,리뷰수 필드를 추가하였음*/

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
    // 카테고리 종류: 건강, 경제경영, 고전, 과학, 대학교재, 만화, 사회과학, 소설, 에세이, 여행, 역사, 인문학, 자기계발, 종교

    private String description; // 도서 소개

    private String contents; // 도서 목차

    private String bookimg; // 도서 이미지

    private double star; // 도서 별점
    
    private int cnt; // 도서 리뷰수

    private LocalDate regdate; // 등록일자

    private LocalDate updatedate; // 수정일자

}
