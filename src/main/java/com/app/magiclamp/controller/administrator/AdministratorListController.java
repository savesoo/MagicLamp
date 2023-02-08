package com.app.magiclamp.controller.administrator;

import com.app.magiclamp.model.administrator.AdministratorSearchType;
import com.app.magiclamp.service.administrator.AdministratorListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class AdministratorListController {

    @Autowired
    private AdministratorListService administratorListService;

    @GetMapping("/administrator/list")
    public String administratorList(AdministratorSearchType administratorSearchType,
                                  @RequestParam(value = "p", defaultValue = "1") int pagenum,
                                  Model model
    ) {

        model.addAttribute("administratorPage", administratorListService.getPage(pagenum, administratorSearchType));

        return "view/administrator/list";
    }

}
