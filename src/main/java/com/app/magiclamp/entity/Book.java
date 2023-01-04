package com.app.magiclamp.entity;

import lombok.*;

import javax.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private char isbn;

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
    private int price;

    @Column
    private int saleprice;

    @Column
    private int mileagerate;

    @Column
    private int stock;

    @Column
    private String binding;

    @Column
    private int pages;

    @Column
    private String size;

    @Column
    private int weight;

    @Column
    private String category;

    @Column
    private String description;

    @Column
    private String contents;

    @Column
    private String bookimg;

    @Column
    private String regdate;

    @Column
    private String updatedate;

}
