package com.app.magiclamp.controller.book;

import com.app.magiclamp.model.bookMain.BookSearchOption;
import com.app.magiclamp.service.book.BookMainListService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/view/book/bookMainList")
@Log4j2
public class BookMainListController {

    @Autowired
    private BookMainListService bookMainListService;

    @GetMapping
    public void getBookMainList(
            @RequestParam(value = "p", defaultValue = "1") int pagenum,
            BookSearchOption searchOption,
            HttpServletRequest request,
            Model model
    ) {

        HttpSession session = request.getSession();

        model.addAttribute("loginInfo", session.getAttribute("loginInfo"));

        String searchType = searchOption.getSearchType();
        String keyword = searchOption.getKeyword();

        if (searchOption.getKeyword() == null) {

            model.addAttribute("BookMainListPage", bookMainListService.getBookMainPageList(pagenum, searchType, keyword));

        } else {

            model.addAttribute("BookMainListPage", bookMainListService.getBookMainPageSearchList(pagenum, searchOption));

        }
    }

    @PostMapping
    public void postBookMainList(
            @RequestParam(value = "p", defaultValue = "1") int pagenum,
            BookSearchOption searchOption,
            HttpServletRequest request,
            Model model
    ) {
        log.info("PostMapping 진입............");

        HttpSession session = request.getSession();

        model.addAttribute("loginInfo", session.getAttribute("loginInfo"));

        model.addAttribute("BookMainListPage", bookMainListService.getBookMainPageSearchList(pagenum, searchOption));
    }

}
