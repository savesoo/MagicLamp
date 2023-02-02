package com.app.magiclamp.controller.administrator;

import com.app.magiclamp.model.administrator.BookInsertRequest;
import com.app.magiclamp.service.administrator.AdministratorInsertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/administrator/insert")
public class AdministratorInsertController {

    @Autowired
    private AdministratorInsertService administratorInsertService;

    @GetMapping
    public String getBookInsert() {

        return "/view/administrator/insert";
    }

    @PostMapping
    public String insertBook(BookInsertRequest bookInsertRequest) {

        administratorInsertService.checkIsbnDuplication(bookInsertRequest);
        administratorInsertService.bookInsert(bookInsertRequest);

        return "redirect:/administrator/list";
    }
}
