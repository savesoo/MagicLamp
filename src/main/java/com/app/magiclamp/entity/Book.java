package com.app.magiclamp.entity;

import com.app.magiclamp.model.BookInfoDTO;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "tbl_book")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@DynamicInsert
@DynamicUpdate
public class Book {

    @Id
    @Column(columnDefinition = "char(13) not null", updatable = false)
    private String isbn; // 도서ISBN

    @Column(columnDefinition = "varchar(200) not null")
    private String title; // 도서명

    @Column(columnDefinition = "varchar(100) not null")
    private String author; // 저자

    @Column(columnDefinition = "varchar(100) not null")
    private String publisher; // 출판사

    @Column
    private String pubdate; // 출간일자

    @Column(columnDefinition = "varchar(10) not null default '정상'", updatable = false)
    private String status; // 도서 상태 (정상, 절판, 품절)

    @Column(columnDefinition = "int not null")
    private Integer price; // 정가

    @Column(columnDefinition = "int not null")
    private Integer saleprice; // 판매가(보통 10%할인)

    @Column(columnDefinition = "int not null")
    private Integer mileagerate; // 마일리지적립률(보통 5% 적립)

    @Column(columnDefinition = "int not null")
    private Integer stock; // 재고 수량(시작 값은 10권)

    @Column(columnDefinition = "varchar(10) not null")
    private String binding; // 도서 형태(PB or HB)

    @Column
    private Integer pages; // 쪽수

    @Column
    private String size; // 사이즈 (가로mm, 세로mm)

    @Column
    private Integer weight; // 무게 (기본 500g)

    @Column
    private String category; // 카테고리
    // 카테고리 종류: 건강, 경제경영, 고전, 과학, 대학교재, 만화, 사회과학, 소설, 에세이, 여행, 역사, 인문학, 자기계발, 종교

    @Column(columnDefinition = "TEXT NULL DEFAULT NULL")
    private String description; // 도서 소개

    @Column(columnDefinition = "TEXT NULL DEFAULT NULL")
    private String contents; // 도서 목차

    @Column
    private String bookimg; // 도서 이미지

    @Column(columnDefinition = "timestamp not null default current_timestamp", updatable = false)
    private LocalDate regdate; // 등록일자

    @Column(columnDefinition = "timestamp not null default current_timestamp on update current_timestamp", updatable = false)
    private LocalDate updatedate; // 수정일자

    public Book(String isbn, String bookimg, String title, String author) {
        this.isbn = isbn;
        this.bookimg = bookimg;
        this.title = title;
        this.author = author;
    }

    public Book(String title, String bookimg){
        this.title = title;
        this.bookimg = bookimg;
    }

    public BookInfoDTO toBookInfo(){

        return BookInfoDTO.builder()
                .isbn(isbn)
                .title(title)
                .author(author)
                .publisher(publisher)
                .price(price)
                .saleprice(saleprice)
                .mileagerate(mileagerate)
                .stock(stock)
                .category(category)
                .bookimg(bookimg)
                .build();
    }
}
