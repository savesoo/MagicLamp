package com.app.magiclamp.controller.administrator;

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

    @GetMapping("/view/administrator/list")
    public void administratorList(@RequestParam(value = "p", defaultValue = "1") int pagenum, Model model) {

        model.addAttribute("administratorList", administratorListService.getBookList(pagenum));
        model.addAttribute("administratorPage", administratorListService.getPage(pagenum));

    }

//    @GetMapping("/view/administrator/search")
//    public String search(SearchType searchType, Model model) {
//
//        List<Book> searchList = administratorListService.search(searchType);
//
//        model.addAttribute("searchlist", searchList);
//
//        return "/view/administrator/searchlist";
//    }

}
