package com.app.magiclamp.controller.book;

import com.app.magiclamp.service.book.BookViewService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/book/view")
@Log4j2
public class BookViewController {

    @Autowired
    private BookViewService bookViewService;

    @GetMapping
    public void getBookView(
            @RequestParam("isbn") String isbn,
            @RequestParam("p") int pageNum,
            Model model,
            HttpServletRequest request
    ) {

//        HttpSession session = request.getSession();

        model.addAttribute("pageNum", pageNum);
        model.addAttribute("bookView", bookViewService.selectBook(isbn));

    }

}
