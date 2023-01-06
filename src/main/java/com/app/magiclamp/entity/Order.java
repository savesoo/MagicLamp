package com.app.magiclamp.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@DynamicInsert
@Table(name="tbl_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer orderindex; // 주문 번호

    @Column(nullable = false)
    private Integer userindex; // 사용자 index

    @Column(columnDefinition = "char(13) not null")
    private String isbn; // isbn

    @Column(nullable = false)
    private Integer realprice; // 실구매가(판매가-마일리지)
    @Column(nullable = false)
    private Integer mileage; // 적립되는 마일리지

    @Column(columnDefinition = "varchar(20) not null")
    private String name;
    @Column(columnDefinition = "char(11) not null")
    private String phone;

    @Column(columnDefinition = "char(5) not null")
    private String postnum;
    @Column(columnDefinition = "varchar(200) not null")
    private String address1;
    @Column(columnDefinition = "varchar(100) not null")
    private String address2;

    @Column(columnDefinition = "timestamp not null default current_timestamp()")
    private LocalDate orderdate; // 주문일

    @Column(columnDefinition = "tinyint default 0")
    private Boolean purchasestate; // 0:구매 1:반품

    @Column
    private Integer approvalnum; // 결제 완료시 승인 번호

}
