package com.app.magiclamp.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tbl_addrbook")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class AddrBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int addrindex;

    @Column(columnDefinition = "int not null", updatable = false)
    private int userindex;

    @Column
    private String addrname;

    @Column(columnDefinition = "varchar(20) not null")
    private String recipient;

    @Column(columnDefinition = "char(11) not null")
    private String phone;

    @Column(columnDefinition = "char(5) not null")
    private String postnum;

    @Column(columnDefinition = "varchar(200) not null")
    private String address1;

    @Column(columnDefinition = "varchar(100) not null")
    private String address2;

    @Column(columnDefinition = "int default 0", updatable = false)
    private int priority;
}
