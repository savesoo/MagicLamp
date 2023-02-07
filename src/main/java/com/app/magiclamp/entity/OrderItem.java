package com.app.magiclamp.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@DynamicInsert
@Table(name="tbl_orderitem")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer orderitemindex; // 주문아이템 index

    @Column(columnDefinition = "char(13) not null")
    private String isbn; // isbn

    @Column(columnDefinition = "int default 1")
    private Integer bookcount;

    @Column
    private Integer orderindex; // 주문 번호

}
