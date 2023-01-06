package com.app.magiclamp.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tbl_cart")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int cartindex;

    @ManyToOne
    @JoinColumn(name = "isbn", columnDefinition = "char(13) not null")
    private Book isbn;

    @Column(columnDefinition = "int default 1")
    private int bookcount;

}
