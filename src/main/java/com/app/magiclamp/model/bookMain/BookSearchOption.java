package com.app.magiclamp.model.bookMain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class BookSearchOption {

    private String searchType;

    private String keyword;

}
