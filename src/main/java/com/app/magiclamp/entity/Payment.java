package com.app.magiclamp.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "tbl_payment")
@DynamicInsert
public class Payment {

    // 결제 정보 table

    @Id
    @Column(columnDefinition = "not null")
    private Integer approvalnum; // 결제 승인번호

    @Column(columnDefinition = "not null")
    private Integer orderindex; // 주문번호(결제 금액 등 join)

    @Column(columnDefinition = "char(13) not null")
    private String card; // 결제한 카드 정보

    @Column(columnDefinition = "timestamp not null default current_timestamp()")
    private LocalDate approvaldate; // 결제승인일자

}
