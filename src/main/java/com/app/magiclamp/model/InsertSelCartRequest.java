package com.app.magiclamp.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class InsertSelCartRequest {

    private Integer cartindex;

    private Integer bookcount;

    private String isbn;
}
