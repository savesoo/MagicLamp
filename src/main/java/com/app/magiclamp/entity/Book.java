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
    private String isbn;

    @Column
    private String title;

    @Column
    private String author;

    @Column
    private String publisher;

    @Column
    private Date pubdate;

    @Column
    private String status;

    @Column
    private Integer price;

    @Column
    private Integer saleprice;

    @Column
    private Integer mileagerate;

    @Column
    private Integer stock;

    @Column
    private String binding;

    @Column
    private Integer pages;

    @Column
    private String size;

    @Column
    private Integer weight;

    @Column
    private String category;

    @Column
    private String description;

    @Column
    private String contents;

    @Column
    private String bookimg;

    @Column(columnDefinition = "timestamp not null default current_timestamp()")
    private LocalDate regdate;

    @Column
    private LocalDate updatedate;

}
