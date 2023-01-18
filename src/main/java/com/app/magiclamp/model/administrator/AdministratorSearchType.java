package com.app.magiclamp.model.administrator;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class AdministratorSearchType {

    private String searchoption; // 검색 옵션
    private String keyword;    // 키워드

}
