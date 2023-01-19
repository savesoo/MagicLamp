package com.app.magiclamp.entity;

import lombok.*;
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
public class Mileage {
    @Id
    @Column
    private Integer userindex;

    @Column(columnDefinition = "int default 0")
    private Integer mileage;

    @Column(columnDefinition = "int default 0")
    private Integer usemileage;

    @Column(columnDefinition = "timestamp not null default current_timestamp()")
    private LocalDate updatedate;

}