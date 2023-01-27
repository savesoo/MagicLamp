package com.app.magiclamp.model.mypage;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class OrderHistorySearchOption {

    private int searchOption;

    private String searchText;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate sDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate eDate;

}
