package com.app.magiclamp.controller.administrator;

import com.app.magiclamp.service.administrator.AdministratorReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdministratorReadController {

    @Autowired
    private AdministratorReadService administratorReadService;

    @GetMapping("/administrator/read")
    public String getRead(@RequestParam("isbn") String isbn, Model model) {

        model.addAttribute("administratorRead", administratorReadService.readBook(isbn));

        return "/view/administrator/read";
    }

}
