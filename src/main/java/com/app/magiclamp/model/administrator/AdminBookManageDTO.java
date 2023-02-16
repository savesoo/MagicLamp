package com.app.magiclamp.model.administrator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Service
public class AdminBookManageDTO {

    private String isbn;
    private int stock;
    private int mileagerate;

}
