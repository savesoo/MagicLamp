package com.app.magiclamp.controller.administrator;

import com.app.magiclamp.domain.administrator.BookInsertRequest;
import com.app.magiclamp.service.administrator.AdministratorInsertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/view/administrator/insert")
public class AdministratorInsertController {

    @Autowired
    private AdministratorInsertService administratorInsertService;

    @GetMapping
    public void getBookInsert() {

    }

    @PostMapping
    public String insertBook(BookInsertRequest bookInsertRequest) {

        administratorInsertService.checkIsbnDuplication(bookInsertRequest);
        administratorInsertService.bookInsert(bookInsertRequest);

        return "redirect:/view/administrator/list";
    }
}
