package com.app.magiclamp.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="tbl_mileage")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@DynamicUpdate
@DynamicInsert
public class Mileage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer mileageindex;

    @Column(columnDefinition = "int not null")
    private Integer userindex;

    @Column(columnDefinition = "int default 0")
    private Integer mileage;

    @Column(columnDefinition = "int default 0")
    private Integer usemileage;

    @Column(columnDefinition = "timestamp not null default current_timestamp()")
    private LocalDate updatedate;

}