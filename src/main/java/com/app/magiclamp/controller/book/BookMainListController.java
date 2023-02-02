package com.app.magiclamp.controller.book;

import com.app.magiclamp.model.AuthUserDTO;
import com.app.magiclamp.model.bookMain.BookSearchOption;
import com.app.magiclamp.service.book.BookMainListService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/main")
@Log4j2
public class BookMainListController {

    @Autowired
    private BookMainListService bookMainListService;

    @GetMapping
    public String getBookMainList(
            @RequestParam(value = "p", defaultValue = "1") int pagenum,
            @RequestParam(value = "category", defaultValue = "") String category,
            BookSearchOption searchOption,
            HttpServletRequest request,
            @AuthenticationPrincipal AuthUserDTO authUserDTO,
            Model model
    ) {

        model.addAttribute("loginInfo", authUserDTO == null ? -1 : authUserDTO.getUserindex());

        String searchType = searchOption.getSearchType();
        String keyword = searchOption.getKeyword();

        model.addAttribute("BookMainListPage", bookMainListService.getBookMainPageSearchList(pagenum, searchOption, category));
        log.info("getKeyword() 값 있음. pagenum, searchType, keyword................... ===> " + pagenum, searchType, keyword);

        model.addAttribute("category", bookMainListService.getCategory());

        return "/view/book/bookMainList";

    }
}
