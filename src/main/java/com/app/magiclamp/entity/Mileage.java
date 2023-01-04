package com.app.magiclamp.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="tbl_mileage")
public class Mileage {
    @Id
    @Column
    private Integer userindex;

    @Column(columnDefinition = "int default 0")
    private Integer mileage;
    @Column(columnDefinition = "timestamp not null default current_timestamp()")
    private LocalDate updatedate;
}
