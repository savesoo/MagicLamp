package com.app.magiclamp.controller.administrator;

import com.app.magiclamp.model.administrator.BookModifyRequest;
import com.app.magiclamp.service.administrator.AdministratorModifyService;
import com.app.magiclamp.service.administrator.AdministratorReadService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/administrator/modify")
public class AdministratorModifyController {

    @Autowired
    private AdministratorReadService administratorReadService;

    @Autowired
    private AdministratorModifyService administratorModifyService;

    @GetMapping
    public String getModify(@RequestParam("isbn") String isbn, Model model) {

        model.addAttribute("administratorModify", administratorReadService.readBook(isbn));

        return "/view/administrator/modify";
    }

    @PostMapping
    public String postModify(BookModifyRequest bookModifyRequest, RedirectAttributes redirectAttributes) {

        redirectAttributes.addAttribute("isbn", bookModifyRequest.getIsbn());
        administratorModifyService.modify(bookModifyRequest);

        return "redirect:/administrator/list";
    }
}
