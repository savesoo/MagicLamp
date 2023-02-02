package com.app.magiclamp.controller.administrator;

import com.app.magiclamp.service.administrator.AdministratorDeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdministratorDeleteController {

    @Autowired
    private AdministratorDeleteService administratorDeleteService;

    @GetMapping("/administrator/delete")
    public String deleteBook(@Param("isbn") String isbn) {

        administratorDeleteService.deleteBook(isbn);

        return "redirect:/administrator/list";
    }
}
