package com.app.magiclamp.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@Table(name = "tbl_like")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@DynamicInsert
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    public Integer likeindex;

    @ManyToOne
    @JoinColumn(name = "reviewindex", columnDefinition = "int not null")
    private Review reviewindex;

    @ManyToOne
    @JoinColumn(name = "userindex", columnDefinition = "int not null")
    private User userindex;
}
