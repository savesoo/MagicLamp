package com.app.magiclamp.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@DynamicInsert
@Table(name="tbl_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer userindex;

    @Column(columnDefinition = "varchar(20) unique not null")
    private String username;
    @Column(columnDefinition = "char(100) not null")
    private String password;

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

    @Column(columnDefinition = "timestamp not null default current_timestamp()", updatable = false)
    private LocalDate joindate;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<UserRole> role;
    @Column(columnDefinition = "tinyint default 0")
    private Boolean deleted;

    public void addUserRole(UserRole r){
        role.add(r);
    }

}
