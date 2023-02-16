package com.app.magiclamp.controller.administrator;

import com.app.magiclamp.model.administrator.AdminBookManageSearchOption;
import com.app.magiclamp.service.administrator.AdminBookManageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Log4j2
public class AdminBookManageController {

    @Autowired
    private AdminBookManageService adminBookManageService;

    @GetMapping("/administrator/bookmanage")
    public String selectBookStockList(
            @RequestParam(value="stockInput", defaultValue = "0") int stock,
            @RequestParam(value="isbnInput", defaultValue = "") String isbn,
            Model model
    ) {

        log.info("######################" + stock);
        log.info("######################" + isbn);

        model.addAttribute("list", adminBookManageService.selectBookManageList(stock, isbn));
        model.addAttribute("stock", stock);
        model.addAttribute("isbn", isbn);

        return "view/administrator/bookmanage";

    }

}
